package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TYCJImageParseUtils {
    private static TYCJImageParseUtils tycjImageParseUtils;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ImageChannelType {
        public static String fiveEnter = "五入口";
        public static String pubuliu = "瀑布流";
    }

    private TYCJImageParseUtils() {
    }

    public static TYCJImageParseUtils getInstance() {
        if (tycjImageParseUtils == null) {
            tycjImageParseUtils = new TYCJImageParseUtils();
        }
        return tycjImageParseUtils;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectFiveEnterSucessImage(DataSource dataSource, Activity activity, String str, String str2, String str3, String str4, long j) {
        try {
            if (dataSource.equals(DataSource.REMOTE)) {
                TYCJBoxManager.getInstance().collectUnicomImage(activity, str, str2, "1", null, String.valueOf(SystemTimeUtil.currentTimeMillis() - j), str3, str4, String.valueOf(j));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectFiveEnterFailImage(Activity activity, String str, String str2, String str3, String str4, String str5, long j) {
        String str6;
        try {
            long currentTimeMillis = SystemTimeUtil.currentTimeMillis() - j;
            if (TextUtils.isEmpty(str3)) {
                str6 = str3;
            } else {
                try {
                    str6 = deleteHuanHang(str3);
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return;
                }
            }
            TYCJBoxManager.getInstance().collectUnicomImage(activity, str, str2, "0", str6, String.valueOf(currentTimeMillis), str4, str5, String.valueOf(j));
        } catch (Exception e2) {
            e = e2;
        }
    }

    public void loadFiveEnterCollectImage(final Activity activity, final String str, final String str2, final String str3, int i, int i2, ImageView imageView) {
        final long currentTimeMillis = SystemTimeUtil.currentTimeMillis();
        GlideApp.with(activity).load(str).placeholder(i).error(i2).addListener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJImageParseUtils.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                try {
                    if (TYCJConfigUtil.isOpen("imgLoading")) {
                        TYCJImageParseUtils.this.collectFiveEnterFailImage(activity, str2, str, glideException != null ? glideException.getMessage() : "-", ImageChannelType.fiveEnter, str3, currentTimeMillis);
                        return false;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                try {
                    if (TYCJConfigUtil.isOpen("imgLoading")) {
                        TYCJImageParseUtils.this.collectFiveEnterSucessImage(dataSource, activity, str2, str, ImageChannelType.fiveEnter, str3, currentTimeMillis);
                        return false;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }).into(imageView);
    }

    public void collectH5Image(Activity activity, String str, String str2) {
        TYCJBoxManager.getInstance().collectUnicomImage(activity, str, str2, "0", null, null, isPuBuLiu(str) ? ImageChannelType.pubuliu : "", null, String.valueOf(SystemTimeUtil.currentTimeMillis()));
    }

    private String deleteHuanHang(String str) {
        return !TextUtils.isEmpty(str) ? str.replaceAll("\r|\n", " ") : "";
    }

    private boolean isPuBuLiu(String str) {
        return str.contains("https://img.client.10010.com/homejingxuan");
    }
}
