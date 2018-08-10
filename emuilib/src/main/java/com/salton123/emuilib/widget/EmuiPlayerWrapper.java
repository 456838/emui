package com.salton123.emuilib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.salton123.emuilib.R;
import com.salton123.emuilib.bean.VideoBean;
import com.salton123.emuilib.niceplayer.controller.NakedController;
import com.salton123.emuilib.niceplayer.player.NiceVideoPlayer;
import com.salton123.emuilib.niceplayer.player.OnPlayStateChanged;
import com.salton123.emuilib.util.EmuiUtil;
import com.salton123.emuilib.util.NiceUtil;
import com.salton123.util.RxUtils;
import com.salton123.util.ViewUtils;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * User: newSalton@outlook.com
 * Date: 2018/3/18 14:51
 * ModifyTime: 14:51
 * Description:
 */
public class EmuiPlayerWrapper extends FrameLayout {
    private NiceVideoPlayer nicePlayer;
    NakedController controller;
    VideoBean mVideoBean;

    public EmuiPlayerWrapper(@NonNull Context context) {
        this(context, null, 0);
    }

    public EmuiPlayerWrapper(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmuiPlayerWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.cp_nice_player, this);
        nicePlayer = ViewUtils.f(this, R.id.nicePlayer);
        if (nicePlayer != null) {
            nicePlayer.setPlayerType(NiceVideoPlayer.TYPE_NATIVE);
        }
    }

    public void setOnPlayStateChanged(OnPlayStateChanged onPlayStateChanged) {
        if (controller != null) {
            controller.setOnPlayStateChanged(onPlayStateChanged);
        }
    }

    OnPlayStateChanged mOnPlayStateChanged;

    public void updateFirstFrame(final String url) {
        Observable.just(url)
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        Bitmap bitmap = EmuiUtil.getBitMap(getContext(), Uri.parse(url));
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

    public void onPause() {
        if (nicePlayer != null && (nicePlayer.isPlaying() || nicePlayer.isBufferingPlaying())) {
            nicePlayer.pause();
        }
    }


    public void onResume() {
        if (nicePlayer != null) {
            nicePlayer.restart();
        }
    }

    public void onDestroy() {
        if (nicePlayer != null) {
            nicePlayer.release();
            nicePlayer = null;
        }
    }

    public void start() {
        if (nicePlayer != null) {
            if (nicePlayer.isCompleted()) {
                restart();
            } else {
                nicePlayer.start();
            }
        }
    }

    public void restart() {
        if (nicePlayer != null) {
            nicePlayer.restart();
        }
    }

    public void updatePlayUrl(VideoBean videoBean) {
        mVideoBean = videoBean;
        controller = new NakedController(getContext());
        if (nicePlayer != null) {
            nicePlayer.setController(controller);
            controller.setImage(mVideoBean.resId);
            NiceUtil.savePlayPosition(getContext(), mVideoBean.url, 0);
            nicePlayer.updatePlayUrl(mVideoBean.url, null);
        }
    }

    public void fullScreen() {
        if (nicePlayer != null) {
            nicePlayer.enterFullScreen();
        }
    }

}
