package com.salton123.splash;

import com.salton123.emuilib.util.RxUtils;
import com.salton123.util.EventUtil;
import com.salton123.util.MLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * User: newSalton@outlook.com
 * Date: 2018/2/11 下午6:23
 * ModifyTime: 下午6:23
 * Description:分享弹窗实验需求
 */
public enum SplashPromoteInjector {
    INSTANCE;

    private static final String TAG = "SplashPromoteInjector";
    protected Disposable mTimerDisposable;
    private static final int second =120;

    public boolean auth() {
        return true;
    }

    public void create() {
        if (!auth()) {
            return;
        }
        MLog.info(TAG, "create timer");
        if (mTimerDisposable != null && !mTimerDisposable.isDisposed()) {
            mTimerDisposable.dispose();
        }
        mTimerDisposable = Flowable.timer(second, TimeUnit.SECONDS).takeUntil(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Exception {
                return false;
            }
        }).compose(RxUtils.<Long>schedulersTransformerForFlowable()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                EventUtil.sendEvent(new SplashPromoteEventArgs());
                MLog.debug(TAG, "post SplashPromoteEventArgs aLong=" + aLong);
            }
        });
    }

    public void destory() {
        MLog.info(TAG, "destory timer");
        if (mTimerDisposable != null && !mTimerDisposable.isDisposed()) {
            mTimerDisposable.dispose();
        }
    }
}
