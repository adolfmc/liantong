package com.baidu.p120ar;

import com.baidu.p120ar.audio.IEasyAudio;
import com.baidu.p120ar.imu.IImu;
import com.baidu.p120ar.record.IMovieRecorder;
import com.baidu.p120ar.utils.ReflectionUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ARPluginBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPluginBuilder {
    public static IImu buildIMUController() {
        return (IImu) ReflectionUtils.getNewInstance("com.baidu.ar.imu.IMUController");
    }

    public static IMovieRecorder buildMovieRecorder() {
        return (IMovieRecorder) ReflectionUtils.getSingleInstance("com.baidu.ar.recorder.MovieRecorder", "getInstance");
    }

    public static IEasyAudio buildEasyAudio() {
        return (IEasyAudio) ReflectionUtils.getSingleInstance("com.baidu.ar.audio.EasyAudio", "getInstance");
    }
}
