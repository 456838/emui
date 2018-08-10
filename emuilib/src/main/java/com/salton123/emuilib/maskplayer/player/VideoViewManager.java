package com.salton123.emuilib.maskplayer.player;

import com.salton123.emuilib.widget.MaskVideoView;

/**
 * 视频播放器管理器.
 */
public class VideoViewManager {

    private MaskVideoView mPlayer;

    private VideoViewManager() {
    }

    private static VideoViewManager sInstance;

    public static VideoViewManager instance() {
        if (sInstance == null) {
            synchronized (VideoViewManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoViewManager();
                }
            }
        }
        return sInstance;
    }

    public void setCurrentVideoPlayer(MaskVideoView player) {
        mPlayer = player;
    }

    public MaskVideoView getCurrentVideoPlayer() {
        return mPlayer;
    }

    public void releaseVideoPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void stopPlayback() {
        if (mPlayer != null) mPlayer.stopPlayback();
    }

    public boolean onBackPressed() {
        return mPlayer != null && mPlayer.onBackPressed();
    }
}
