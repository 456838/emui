package com.salton123.emuilib.maskplayer.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.salton123.emuilib.R;
import com.salton123.emuilib.widget.MaskVideoView;
import com.salton123.util.MLog;

public class MaskController extends BaseVideoController {

    private ImageView ivThumbnail;

    public MaskController(@NonNull Context context) {
        super(context);
    }

    public MaskController(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_mask_controller;
    }

    @Override
    protected void initView() {
        super.initView();
        ivThumbnail = controllerView.findViewById(R.id.ivThumbnail);
    }

    @Override
    public void setPlayState(int playState) {
        super.setPlayState(playState);

        switch (playState) {
            case MaskVideoView.STATE_IDLE:
                MLog.e("STATE_IDLE");
                ivThumbnail.setVisibility(VISIBLE);
                break;
            case MaskVideoView.STATE_PLAYING:
                MLog.e("STATE_PLAYING");
                ivThumbnail.setVisibility(GONE);
                break;
            case MaskVideoView.STATE_PREPARED:
                MLog.e("STATE_PREPARED");
                break;
        }
    }

    public ImageView getIvThumbnail() {
        return ivThumbnail;
    }
}
