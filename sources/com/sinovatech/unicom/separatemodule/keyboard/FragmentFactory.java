package com.sinovatech.unicom.separatemodule.keyboard;

import android.os.Bundle;
import android.support.p083v4.app.Fragment;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FragmentFactory {
    public static final String EMOTION_MAP_TYPE = "EMOTION_MAP_TYPE";
    private static FragmentFactory factory;

    private FragmentFactory() {
    }

    public static FragmentFactory getSingleFactoryInstance() {
        if (factory == null) {
            synchronized (FragmentFactory.class) {
                if (factory == null) {
                    factory = new FragmentFactory();
                }
            }
        }
        return factory;
    }

    public Fragment getFragment(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(EMOTION_MAP_TYPE, i);
        return (EmotiomComplateFragment) EmotiomComplateFragment.newInstance(EmotiomComplateFragment.class, bundle);
    }
}
