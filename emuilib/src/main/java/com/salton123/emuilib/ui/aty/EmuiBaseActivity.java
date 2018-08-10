package com.salton123.emuilib.ui.aty;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;
import com.salton123.base.BaseSupportActivity;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;

/**
 * User: newSalton@outlook.com
 * Date: 2017/12/4 20:17
 * ModifyTime: 20:17
 * Description:
 */
public abstract class EmuiBaseActivity extends BaseSupportActivity
        implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "EmuiBaseActivity";
    ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏w
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mImmersionBar = ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .transparentBar().transparentNavigationBar();
        mImmersionBar.init();
        super.onCreate(savedInstanceState);
        // setFragmentAnimator(new DefaultNoAnimator());
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void setListener(int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
            findViewById(id).setOnTouchListener(this);
        }
    }

    @Override
    public void onClick(View view) {

    }

    // @Override
    // public boolean dispatchTouchEvent(MotionEvent ev) {
    //     // MLog.info(TAG, "[dispatchTouchEvent] ev=" + ev.getAction());
    //     if (ev.getAction() ==MotionEvent.ACTION_DOWN) {
    //         SplashPromoteInjector.INSTANCE.create();
    //     }
    //     return super.dispatchTouchEvent(ev);
    //
    // }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }
}
