package cn.sharesdk.framework.authorize;

import android.content.Context;
import android.content.Intent;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AbstractAuthorizeActivity extends FakeActivity {
    protected AuthorizeHelper helper;

    public void show(AuthorizeHelper authorizeHelper) {
        this.helper = authorizeHelper;
        super.show(MobSDK.getContext(), null);
    }

    @Override // com.mob.tools.FakeActivity
    public void show(Context context, Intent intent) {
        throw new RuntimeException("This method is deprecated, use show(AuthorizeHelper, Intent) instead");
    }

    public AuthorizeHelper getHelper() {
        return this.helper;
    }
}
