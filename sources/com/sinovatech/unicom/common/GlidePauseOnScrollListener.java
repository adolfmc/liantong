package com.sinovatech.unicom.common;

import cn.finalteam.galleryfinal.PauseOnScrollListener;
import com.bumptech.glide.Glide;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GlidePauseOnScrollListener extends PauseOnScrollListener {
    public GlidePauseOnScrollListener(boolean z, boolean z2) {
        super(z, z2);
    }

    @Override // cn.finalteam.galleryfinal.PauseOnScrollListener
    public void resume() {
        Glide.with(getActivity()).resumeRequests();
    }

    @Override // cn.finalteam.galleryfinal.PauseOnScrollListener
    public void pause() {
        Glide.with(getActivity()).pauseRequests();
    }
}
