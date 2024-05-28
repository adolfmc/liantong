package cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeySharePage;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.land.FriendListPageLand;
import cn.sharesdk.onekeyshare.themes.classic.port.FriendListPagePort;
import com.bytedance.applog.tracker.Tracker;
import com.mob.MobSDK;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EditPage extends OnekeySharePage implements TextWatcher, View.OnClickListener, Runnable {
    protected AsyncImageView aivThumb;
    protected EditText etContent;
    private OnekeyShareThemeImpl impl;
    protected LinearLayout llBottom;
    protected LinearLayout llPage;
    protected int maxBodyHeight;
    protected Platform platform;
    protected RelativeLayout rlThumb;
    protected RelativeLayout rlTitle;

    /* renamed from: sp */
    protected Platform.ShareParams f2983sp;
    protected ScrollView svContent;
    public Bitmap thumb;
    protected TextView tvAt;
    protected TextView tvCancel;
    protected TextView tvShare;
    protected TextView tvTextCouter;
    protected XView xvRemove;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public EditPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
        this.impl = onekeyShareThemeImpl;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setShareParams(Platform.ShareParams shareParams) {
        this.f2983sp = shareParams;
    }

    @Override // com.mob.tools.FakeActivity
    public int onSetTheme(int i, boolean z) {
        if (isDialogMode()) {
            this.activity.requestWindowFeature(1);
            if (Build.VERSION.SDK_INT >= 11) {
                try {
                    ReflectHelper.invokeInstanceMethod(this.activity, "setFinishOnTouchOutside", false);
                    return 16973835;
                } catch (Throwable unused) {
                    return 16973835;
                }
            }
            return 16973835;
        }
        this.activity.getWindow().setSoftInputMode(37);
        return super.onSetTheme(i, z);
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-789517));
    }

    private void cancelAndFinish() {
        ShareSDK.logDemoEvent(5, this.platform);
        finish();
    }

    private void shareAndFinish() {
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_sharing");
        if (stringRes > 0) {
            Toast.makeText(this.activity, stringRes, 0).show();
        }
        if (isDisableSSO()) {
            this.platform.SSOSetting(true);
        }
        this.platform.setPlatformActionListener(getCallback());
        this.platform.share(this.f2983sp);
        this.impl.callback = null;
        finish();
    }

    private void showThumb(Bitmap bitmap) {
        PicViewerPage picViewerPage = new PicViewerPage(this.impl);
        picViewerPage.setImageBitmap(bitmap);
        picViewerPage.show(this.activity, null);
    }

    private void removeThumb() {
        this.f2983sp.setImageArray(null);
        this.f2983sp.setImageData(null);
        this.f2983sp.setImagePath(null);
        this.f2983sp.setImageUrl(null);
    }

    private void showFriendList() {
        FriendListPage friendListPageLand;
        if (this.activity.getResources().getConfiguration().orientation == 1) {
            friendListPageLand = new FriendListPagePort(this.impl);
        } else {
            friendListPageLand = new FriendListPageLand(this.impl);
        }
        friendListPageLand.setPlatform(this.platform);
        friendListPageLand.showForResult(MobSDK.getContext(), null, this);
    }

    @Override // com.mob.tools.FakeActivity
    public void onResult(HashMap<String, Object> hashMap) {
        String joinSelectedUser = getJoinSelectedUser(hashMap);
        if (TextUtils.isEmpty(joinSelectedUser)) {
            return;
        }
        this.etContent.append(joinSelectedUser);
    }

    private String getJoinSelectedUser(HashMap<String, Object> hashMap) {
        if (hashMap == null || !hashMap.containsKey("selected")) {
            return null;
        }
        ArrayList arrayList = (ArrayList) hashMap.get("selected");
        if ("FacebookMessenger".equals(((Platform) hashMap.get("platform")).getName())) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append('@');
            sb.append((String) it.next());
            sb.append(' ');
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isShowAtUserLayout(String str) {
        return "SinaWeibo".equals(str) || "TencentWeibo".equals(str) || "Facebook".equals(str) || "Twitter".equals(str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view.equals(this.tvCancel)) {
            cancelAndFinish();
        } else if (view.equals(this.tvShare)) {
            this.f2983sp.setText(this.etContent.getText().toString().trim());
            shareAndFinish();
        } else if (view.equals(this.aivThumb)) {
            showThumb(this.thumb);
        } else if (view.equals(this.xvRemove)) {
            this.maxBodyHeight = 0;
            this.rlThumb.setVisibility(8);
            this.llPage.measure(0, 0);
            onTextChanged(this.etContent.getText(), 0, 0, 0);
            removeThumb();
        } else if (view.equals(this.tvAt)) {
            showFriendList();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.tvTextCouter.setText(String.valueOf(charSequence.length()));
        if (this.maxBodyHeight == 0) {
            this.maxBodyHeight = (this.llPage.getHeight() - this.rlTitle.getHeight()) - this.llBottom.getHeight();
        }
        if (this.maxBodyHeight > 0) {
            this.svContent.post(this);
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        int height = this.svContent.getChildAt(0).getHeight();
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ResHelper.forceCast(this.svContent.getLayoutParams());
        if (height > this.maxBodyHeight) {
            int i = layoutParams.height;
            int i2 = this.maxBodyHeight;
            if (i != i2) {
                layoutParams.height = i2;
                this.svContent.setLayoutParams(layoutParams);
                return;
            }
        }
        if (height >= this.maxBodyHeight || layoutParams.height != this.maxBodyHeight) {
            return;
        }
        layoutParams.height = -2;
        this.svContent.setLayoutParams(layoutParams);
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        hideSoftInput(getContentView());
        super.onPause();
    }

    public void hideSoftInput(View view) {
        Object systemService = this.activity.getSystemService("input_method");
        if (systemService == null) {
            return;
        }
        ((InputMethodManager) systemService).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
