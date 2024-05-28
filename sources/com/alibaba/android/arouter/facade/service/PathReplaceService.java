package com.alibaba.android.arouter.facade.service;

import android.net.Uri;
import com.alibaba.android.arouter.facade.template.IProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface PathReplaceService extends IProvider {
    String forString(String str);

    Uri forUri(Uri uri);
}
