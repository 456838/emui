package com.salton123.emuilib.niceplayer.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.salton123.emuilib.R;
import com.salton123.emuilib.bean.PlayStateChangedEventArgs;
import com.salton123.emuilib.niceplayer.player.NiceVideoPlayer;
import com.salton123.emuilib.niceplayer.player.OnPlayStateChanged;
import com.salton123.util.EventUtil;


/**
 * User: newSalton@outlook.com
 * Date: 2018/2/24 上午9:57
 * ModifyTime: 上午9:57
 * Description:
 */
public class NakedController extends NiceVideoPlayerController {
    private ImageView mImageView;

    public NakedController(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.naked_palyer_controller, this, true);
        mImageView = findViewById(R.id.thumbnail);
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void setImage(int resId) {
        mImageView.setImageResource(resId);
    }

    @Override
    public ImageView imageView() {
        return mImageView;
    }

    @Override
    public void setLenght(long length) {

    }

    @Override
    public void onPlayStateChanged(int playState) {
        if (mOnPlayStateChanged != null) {
            mOnPlayStateChanged.OnPlayStateChanged(playState);
        }
        switch (playState) {
            case NiceVideoPlayer.STATE_IDLE:
                mImageView.setVisibility(View.VISIBLE);
                break;
            case NiceVideoPlayer.STATE_PREPARING:
                mImageView.setVisibility(View.VISIBLE);
                break;
            case NiceVideoPlayer.STATE_PREPARED:
                mImageView.setVisibility(View.VISIBLE);
                startUpdateProgressTimer();
                break;
            case NiceVideoPlayer.STATE_PLAYING:
                mImageView.setVisibility(View.GONE);
                break;
            case NiceVideoPlayer.STATE_PAUSED:

                break;
            case NiceVideoPlayer.STATE_BUFFERING_PLAYING:

                break;
            case NiceVideoPlayer.STATE_BUFFERING_PAUSED:

                break;
            case NiceVideoPlayer.STATE_ERROR:
                cancelUpdateProgressTimer();
                break;
            case NiceVideoPlayer.STATE_COMPLETED:
                cancelUpdateProgressTimer();
                break;
        }
    }

    @Override
    public void onPlayModeChanged(int playMode) {

    }

    @Override
    public void reset() {

    }

    @Override
    public void updateProgress() {

    }

    @Override
    public void showChangePosition(long duration, int newPositionProgress) {

    }

    @Override
    public void hideChangePosition() {

    }

    @Override
    public void showChangeVolume(int newVolumeProgress) {

    }

    @Override
    public void hideChangeVolume() {

    }

    @Override
    public void showChangeBrightness(int newBrightnessProgress) {

    }

    @Override
    public void hideChangeBrightness() {

    }

    public void start() {
        if (mNiceVideoPlayer.isIdle()) {
            mNiceVideoPlayer.start();
        }
    }

    public void retry() {
        mNiceVideoPlayer.restart();
    }

    public void setOnPlayStateChanged(OnPlayStateChanged onPlayStateChanged) {
        mOnPlayStateChanged = onPlayStateChanged;
    }

    OnPlayStateChanged mOnPlayStateChanged;

}
