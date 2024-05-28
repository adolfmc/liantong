package cn.sharesdk.framework.loopshare.watermark;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface WaterMarkListener {
    void onCancel();

    void onEnd(int i);

    void onFailed(String str, int i);

    void onProgress(int i);

    void onStart();
}
