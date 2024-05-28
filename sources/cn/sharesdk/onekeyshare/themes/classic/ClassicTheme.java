package cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.land.EditPageLand;
import cn.sharesdk.onekeyshare.themes.classic.land.PlatformPageLand;
import cn.sharesdk.onekeyshare.themes.classic.port.EditPagePort;
import cn.sharesdk.onekeyshare.themes.classic.port.PlatformPagePort;
import com.mob.tools.FakeActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ClassicTheme extends OnekeyShareThemeImpl {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastTime;

    @Override // cn.sharesdk.onekeyshare.OnekeyShareThemeImpl
    public void showPlatformPage(Context context) {
        FakeActivity platformPageLand;
        if (context.getResources().getConfiguration().orientation == 1) {
            platformPageLand = new PlatformPagePort(this);
        } else {
            platformPageLand = new PlatformPageLand(this);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastTime >= 1000) {
            platformPageLand.show(context, null);
        }
        lastTime = currentTimeMillis;
    }

    @Override // cn.sharesdk.onekeyshare.OnekeyShareThemeImpl
    public void showEditPage(Context context, Platform platform, Platform.ShareParams shareParams) {
        EditPage editPageLand;
        if (context.getResources().getConfiguration().orientation == 1) {
            editPageLand = new EditPagePort(this);
        } else {
            editPageLand = new EditPageLand(this);
        }
        editPageLand.setPlatform(platform);
        editPageLand.setShareParams(shareParams);
        editPageLand.show(context, null);
    }
}
