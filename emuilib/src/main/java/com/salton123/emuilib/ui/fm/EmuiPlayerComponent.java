package com.salton123.emuilib.ui.fm;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.salton123.base.BaseSupportFragment;
import com.salton123.base.FragmentDelegate;
import com.salton123.emuilib.R;
import com.salton123.emuilib.bean.VideoBean;
import com.salton123.emuilib.niceplayer.controller.NakedController;
import com.salton123.emuilib.niceplayer.player.NiceVideoPlayer;
import com.salton123.emuilib.util.EmuiUtil;
import com.salton123.emuilib.util.NiceUtil;
import com.salton123.util.RxUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/24 上午9:34
 * ModifyTime: 上午9:34
 * Description:
 */
public class EmuiPlayerComponent extends BaseSupportFragment {

    private NiceVideoPlayer nicePlayer;
    NakedController controller;
    VideoBean mVideoBean;

    @Override
    public int getLayout() {
        return R.layout.cp_nice_player;
    }

    @Override
    public void initVariable(Bundle savedInstanceState) {
        mVideoBean = getArguments().getParcelable(FragmentDelegate.Companion.getARG_ITEM());
    }

    @Override
    public void initViewAndData() {
        nicePlayer = f(R.id.nicePlayer);
        nicePlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE);
        // nicePlayer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        controller = new NakedController(_mActivity);
        nicePlayer.setController(controller);
        controller.setImage(mVideoBean.resId);
        nicePlayer.setUp(mVideoBean.url, null);
        // updateFirstFrame(mVideoBean.url);
    }

    private void updateFirstFrame(final String url) {
        Observable.just(url)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        Bitmap bitmap = EmuiUtil.getBitMap(getActivity(), Uri.parse(url));
                        return bitmap;
                    }
                }).compose(RxUtils.<Bitmap>rxSchedulerHelper())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        controller.imageView().setImageBitmap(bitmap);
                    }
                });
    }

    @Override
    public void initListener() {
        // nicePlayer.setOnTouchListener(new View.OnTouchListener() {
        //     @Override
        //     public boolean onTouch(View view, MotionEvent motionEvent) {
        //         nicePlayer.start();
        //         return false;
        //     }
        // });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (nicePlayer != null && (nicePlayer.isPlaying() || nicePlayer.isBufferingPlaying())) {
            nicePlayer.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (nicePlayer != null && (nicePlayer.isPaused() || nicePlayer.isBufferingPaused())) {
            nicePlayer.restart();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (nicePlayer != null) {
            nicePlayer.release();
            nicePlayer = null;
        }
    }


    public void start() {
        if (nicePlayer.isCompleted()) {
            restart();
        } else {
            nicePlayer.start();
        }
    }

    public void restart() {
        nicePlayer.restart();
    }

    public void updatePlayUrl(VideoBean videoBean) {
        mVideoBean = videoBean;
        controller = new NakedController(_mActivity);
        nicePlayer.setController(controller);
        controller.setImage(mVideoBean.resId);
        NiceUtil.savePlayPosition(_mActivity, mVideoBean.url, 0);
        nicePlayer.updatePlayUrl(mVideoBean.url, null);
    }
}
