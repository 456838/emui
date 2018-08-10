package com.salton123.emuilib.util;

import android.view.View;

import com.github.florent37.viewanimator.ViewAnimator;

/**
 * User: newSalton@outlook.com
 * Date: 2018/1/21 21:35
 * ModifyTime: 21:35
 * Description:
 */
public class SaltonAnimationUtil {
    public static void viewScaleAnimation(View... view) {
        ViewAnimator.animate(view).scale(1, 1.05f, 1).duration(3000)
                .repeatCount(-1).start();
    }

    public static void viewTranslateY(View... view) {
        ViewAnimator.animate(view).translationY(10, -10, 10).dp()
                .duration(3000)
                .repeatCount(-1).start();
    }

    public static void viewTranslateX(View... view) {
        ViewAnimator.animate(view).translationX(10, -10, 10).dp()
                .duration(3000)
                .repeatCount(-1).start();
    }


    public static void viewScaleButtonAnimation(View... view) {
        ViewAnimator.animate(view).scale(1, 1.05f, 1).duration(3000)
                .repeatCount(-1).start();
    }

}
