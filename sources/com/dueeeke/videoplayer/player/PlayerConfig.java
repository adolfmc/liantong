package com.dueeeke.videoplayer.player;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PlayerConfig {
    public boolean addToPlayerManager;
    public boolean enableMediaCodec;
    public boolean isCache;
    public boolean isLooping;
    public boolean mAutoRotate;
    public BaseMediaEngine mBaseMediaEngine;
    public boolean usingAndroidMediaPlayer;
    public boolean usingSurfaceView;

    private PlayerConfig(PlayerConfig playerConfig) {
        this.mBaseMediaEngine = null;
        this.usingAndroidMediaPlayer = playerConfig.usingAndroidMediaPlayer;
        this.isLooping = playerConfig.isLooping;
        this.mAutoRotate = playerConfig.mAutoRotate;
        this.isCache = playerConfig.isCache;
        this.addToPlayerManager = playerConfig.addToPlayerManager;
        this.usingSurfaceView = playerConfig.usingSurfaceView;
        this.enableMediaCodec = playerConfig.enableMediaCodec;
        this.mBaseMediaEngine = playerConfig.mBaseMediaEngine;
    }

    private PlayerConfig() {
        this.mBaseMediaEngine = null;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private PlayerConfig target = new PlayerConfig();

        public Builder enableCache() {
            this.target.isCache = true;
            return this;
        }

        public Builder addToPlayerManager() {
            this.target.addToPlayerManager = true;
            return this;
        }

        public Builder usingSurfaceView() {
            this.target.usingSurfaceView = true;
            return this;
        }

        public Builder usingAndroidMediaPlayer() {
            this.target.usingAndroidMediaPlayer = true;
            return this;
        }

        public Builder autoRotate() {
            this.target.mAutoRotate = true;
            return this;
        }

        public Builder setLooping() {
            this.target.isLooping = true;
            return this;
        }

        public Builder enableMediaCodec() {
            this.target.enableMediaCodec = true;
            return this;
        }

        public Builder setCustomMediaEngine(BaseMediaEngine baseMediaEngine) {
            this.target.mBaseMediaEngine = baseMediaEngine;
            return this;
        }

        public PlayerConfig build() {
            return new PlayerConfig();
        }
    }
}
