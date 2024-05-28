package cn.sharesdk.onekeyshare;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import com.mob.tools.FakeActivity;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OnekeySharePage extends FakeActivity {
    private OnekeyShareThemeImpl impl;

    public OnekeySharePage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        this.impl = onekeyShareThemeImpl;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isDialogMode() {
        return this.impl.dialogMode;
    }

    protected final HashMap<String, Object> getShareParamsMap() {
        return this.impl.shareParamsMap;
    }

    public final boolean isSilent() {
        return this.impl.silent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ArrayList<CustomerLogo> getCustomerLogos() {
        return this.impl.customerLogos;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final HashMap<String, String> getHiddenPlatforms() {
        return this.impl.hiddenPlatforms;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PlatformActionListener getCallback() {
        return this.impl.callback;
    }

    public final ShareContentCustomizeCallback getCustomizeCallback() {
        return this.impl.customizeCallback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isDisableSSO() {
        return this.impl.disableSSO;
    }

    public final void shareSilently(Platform platform) {
        this.impl.shareSilently(platform);
    }

    public final Platform.ShareParams formateShareData(Platform platform) {
        if (this.impl.formateShareData(platform)) {
            return this.impl.shareDataToShareParams(platform);
        }
        return null;
    }

    public final boolean isUseClientToShare(Platform platform) {
        return this.impl.isUseClientToShare(platform);
    }
}
