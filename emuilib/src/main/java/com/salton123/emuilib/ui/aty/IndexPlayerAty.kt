package com.salton123.opan.ui.aty

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.salton123.emuilib.R
import com.salton123.emuilib.bean.VideoBean
import com.salton123.emuilib.listener.IVideoListener
import com.salton123.emuilib.ui.aty.EmuiBaseActivity
import com.salton123.emuilib.util.RxUtils
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.cp_video_play.*
import java.util.concurrent.TimeUnit

/**
 * User: newSalton@outlook.com
 * Date: 2018/3/26 14:07
 * ModifyTime: 14:07
 * Description:
 */
class IndexPlayerAty : EmuiBaseActivity() {
    override fun getLayout(): Int = R.layout.cp_video_play

    override fun initVariable(savedInstanceState: Bundle?) {
        mVideoBean = intent.extras.getParcelable("arg_item")
        var ori = intent.extras.getInt("ori", ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        if (ori == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    override fun initViewAndData() {
        titleBar.findViewById<View>(R.id.title_left_iv).setOnClickListener { finish() }
        videoPlayer.updatePlayUrl(mVideoBean)
        videoPlayer.setOnTouchListener({ _: View, _: MotionEvent ->
            finish()
            false
        })
        videoPlayer.setVideoListener(object : IVideoListener {
            override fun onVideoStarted() {

            }

            override fun onVideoPaused() {
            }

            override fun onComplete() {
            }

            override fun onPrepared() {
            }

            override fun onError() {
                finish()
                Toast.makeText(this@IndexPlayerAty, "资源找不到", Toast.LENGTH_SHORT).show()
            }

            override fun onInfo(what: Int, extra: Int) {
            }

        })
    }

    lateinit var mVideoBean: VideoBean
    override fun initListener() {
        Flowable.timer(500, TimeUnit.MILLISECONDS).takeUntil { true }
                .compose(RxUtils.schedulersTransformerForFlowable())
                .subscribe(Consumer { aLong ->
                    println("hello,along=" + aLong!!)
                    if (videoPlayer != null) {
                        videoPlayer!!.start()
                    }
                }, RxUtils.errorConsumer())
    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.onResume()
    }

    override fun onDestroy() {
        videoPlayer?.onDestroy()
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        videoPlayer?.onPause()
    }
}