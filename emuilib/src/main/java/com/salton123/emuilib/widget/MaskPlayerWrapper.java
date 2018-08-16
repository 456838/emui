package com.salton123.emuilib.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.salton123.emuilib.R;
import com.salton123.emuilib.bean.VideoBean;
import com.salton123.emuilib.listener.IVideoListener;
import com.salton123.emuilib.maskplayer.controller.MaskController;
import com.salton123.emuilib.maskplayer.player.AndroidMediaPlayer;
import com.salton123.emuilib.maskplayer.player.PlayerConfig;

/**
 * User: newSalton@outlook.com
 * Date: 2018/7/6 下午4:40
 * ModifyTime: 下午4:40
 * Description:
 */
public class MaskPlayerWrapper extends FrameLayout {
    private MaskVideoView mMaskVideoView;
    private MaskController mDouYinController;
    private VideoBean mVideoBean;
    private PlayerConfig mPlayerConfig;

    public MaskPlayerWrapper(@NonNull Context context) {
        super(context);
        initView();
    }

    public MaskPlayerWrapper(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MaskPlayerWrapper(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.mask_player_wrapper, this, true);
        mMaskVideoView = findViewById(R.id.maskVideoView);
        mDouYinController = new MaskController(getContext());
        mPlayerConfig = new PlayerConfig.Builder()
                .usingSurfaceView()//使用SurfaceView
                .setCustomMediaPlayer(new AndroidMediaPlayer(getContext()))
                .build();
        mMaskVideoView.setPlayerConfig(mPlayerConfig);
    }

    /**
     * 设置播放器监听，用于外部监听播放器的各种状态
     */
    public void setVideoListener(IVideoListener listener) {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.setVideoListener(listener);
    }

    public void updateConfig(PlayerConfig playerConfig) {
        if (isNull(mMaskVideoView)) return;
        mPlayerConfig = playerConfig;
        mMaskVideoView.setPlayerConfig(playerConfig);
    }

    public PlayerConfig getConfig() {
        return mPlayerConfig;
    }

    public void onPause() {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.pause();
    }

    public void onResume() {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.resume();
    }

    public void onDestroy() {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.release();
    }

    public void start() {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.start();
    }

    public void updatePlayUrl(VideoBean videoBean) {
        if (isNull(mMaskVideoView)) return;
        mVideoBean = videoBean;
        mDouYinController.getIvThumbnail().setImageResource(mVideoBean.resId);
        mMaskVideoView.setUrl(mVideoBean.url);
        mMaskVideoView.setVideoController(mDouYinController);
    }

    private boolean isNull(Object obj) {
        return obj == null;
    }

    public void restart() {
        if (isNull(mMaskVideoView)) return;
        mMaskVideoView.retry();
    }
}
