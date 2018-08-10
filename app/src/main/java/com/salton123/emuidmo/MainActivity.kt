package com.salton123.emuidmo

import android.os.Bundle
import com.salton123.emuilib.ui.aty.EmuiBaseActivity
import com.salton123.emuilib.util.UriProvider
import com.salton123.emuilib.bean.VideoBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : EmuiBaseActivity() {
    override fun getLayout(): Int = R.layout.activity_main

    override fun initVariable(savedInstanceState: Bundle?) {
        mVideoBean = VideoBean(R.mipmap.video_bg_multitask
            , UriProvider.getVideoPathStr("speed_up.mp4"))
    }

    override fun initViewAndData() {
        videoPlayer.updatePlayUrl(mVideoBean)
        videoPlayer.start()
//        videoPlayer.postDelayed({
//            openActivity(IndexPlayerAty::class.java,Bundle().also {
//                it.putParcelable("arg_item",mVideoBean)
//            })
//        },5000)
    }

    lateinit var mVideoBean: VideoBean

    override fun onPause() {
        super.onPause()
        videoPlayer.onPause()
    }

    override fun onResume() {
        super.onResume()
        videoPlayer.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayer.onDestroy()
    }
}
