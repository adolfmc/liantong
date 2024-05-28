package cn.sharesdk.onekeyshare.themes.classic.port;

import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
import cn.sharesdk.onekeyshare.themes.classic.PlatformPageAdapter;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlatformPagePort extends PlatformPage {
    public PlatformPagePort(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    @Override // cn.sharesdk.onekeyshare.themes.classic.PlatformPage, com.mob.tools.FakeActivity
    public void onCreate() {
        requestSensorPortraitOrientation();
        super.onCreate();
    }

    @Override // cn.sharesdk.onekeyshare.themes.classic.PlatformPage
    public PlatformPageAdapter newAdapter(ArrayList<Object> arrayList) {
        return new PlatformPageAdapterPort(this, arrayList);
    }
}
