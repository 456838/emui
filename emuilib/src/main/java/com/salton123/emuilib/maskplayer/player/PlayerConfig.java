package com.salton123.emuilib.maskplayer.player;

/**
 * 播放器配置类
 * Created by xinyu on 2018/4/3.
 */

public class PlayerConfig {

    public boolean isLooping;//是否循环播放
    public boolean mAutoRotate;//是否旋转屏幕
    public boolean usingSurfaceView;//是否使用TextureView
    public boolean savingProgress;//是否保存进度
    public AbstractPlayer mAbstractPlayer = null;//自定义播放核心
    public boolean disableAudioFocus;//关闭AudioFocus监听


    private PlayerConfig(PlayerConfig origin) {
        this.isLooping = origin.isLooping;
        this.mAutoRotate = origin.mAutoRotate;
        this.usingSurfaceView = origin.usingSurfaceView;
        this.mAbstractPlayer = origin.mAbstractPlayer;
        this.savingProgress = origin.savingProgress;
        this.disableAudioFocus = origin.disableAudioFocus;
    }

    private PlayerConfig() {

    }

    public static class Builder {

        private PlayerConfig target;

        public Builder() {
            target = new PlayerConfig();
        }

        /**
         * 启用SurfaceView
         */
        public Builder usingSurfaceView() {
            target.usingSurfaceView = true;
            return this;
        }

        /**
         * 设置自动旋转
         */
        public Builder autoRotate() {
            target.mAutoRotate = true;
            return this;
        }

        /**
         * 开启循环播放
         */
        public Builder setLooping() {
            target.isLooping = true;
            return this;
        }


        /**
         * 设置自定义播放核心
         */
        public Builder setCustomMediaPlayer(AbstractPlayer abstractPlayer) {
            target.mAbstractPlayer = abstractPlayer;
            return this;
        }

        /**
         * 保存播放进度
         */
        public Builder savingProgress() {
            target.savingProgress = true;
            return this;
        }

        /**
         * 关闭AudioFocus监听
         */
        public Builder disableAudioFocus() {
            target.disableAudioFocus = true;
            return this;
        }

        public PlayerConfig build() {
            return new PlayerConfig(target);
        }
    }
}
