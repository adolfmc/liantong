package cn.sharesdk.onekeyshare.themes.classic.port;

import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.FriendListPage;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FriendListPagePort extends FriendListPage {
    private static final int DESIGN_SCREEN_WIDTH = 720;
    private static final int DESIGN_TITLE_HEIGHT = 96;

    @Override // cn.sharesdk.onekeyshare.themes.classic.FriendListPage
    public int getDesignTitleHeight() {
        return 96;
    }

    public FriendListPagePort(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    @Override // cn.sharesdk.onekeyshare.themes.classic.FriendListPage
    public float getRatio() {
        return ResHelper.getScreenWidth(this.activity) / 720.0f;
    }
}
