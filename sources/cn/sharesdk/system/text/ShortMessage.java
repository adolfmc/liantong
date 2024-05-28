package cn.sharesdk.system.text;

import android.text.TextUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.system.text.login.LoginActionListener;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import java.io.File;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShortMessage extends Platform {
    public static final int ACTION_SEND = Integer.MAX_VALUE;
    public static final String NAME = "ShortMessage";

    @Override // cn.sharesdk.framework.Platform
    public boolean checkAuthorize(int i, Object obj) {
        return true;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> filterFriendshipInfo(int i, HashMap<String, Object> hashMap) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getBilaterals(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowers(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public HashMap<String, Object> getFollowings(int i, int i2, String str) {
        return null;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getPlatformId() {
        return 19;
    }

    @Override // cn.sharesdk.framework.Platform
    public int getVersion() {
        return 1;
    }

    @Override // cn.sharesdk.framework.Platform
    public boolean hasShareCallback() {
        return false;
    }

    @Override // cn.sharesdk.framework.Platform
    public void initDevInfo(String str) {
    }

    @Override // cn.sharesdk.framework.Platform
    public void setNetworkDevinfo() {
    }

    @Override // cn.sharesdk.framework.Platform
    public String getName() {
        return NAME;
    }

    @Override // cn.sharesdk.framework.Platform
    public void doAuthorize(String[] strArr) {
        try {
            Helper.m21579a().m21578a(new LoginActionListener() { // from class: cn.sharesdk.system.text.ShortMessage.1
                @Override // cn.sharesdk.system.text.login.LoginActionListener
                public void onFail(Throwable th) {
                    if (ShortMessage.this.listener != null) {
                        ShortMessage.this.listener.onError(ShortMessage.this, 1, th);
                    }
                }

                @Override // cn.sharesdk.system.text.login.LoginActionListener
                public void onSuccess(HashMap<String, Object> hashMap) {
                    if (ShortMessage.this.listener != null) {
                        ShortMessage.this.listener.onComplete(ShortMessage.this, 1, hashMap);
                    }
                }

                @Override // cn.sharesdk.system.text.login.LoginActionListener
                public void onCancel() {
                    if (ShortMessage.this.listener != null) {
                        ShortMessage.this.listener.onCancel(ShortMessage.this, 1);
                    }
                }
            }, strArr);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doShare(final Platform.ShareParams shareParams) {
        ActionListener actionListener = new ActionListener() { // from class: cn.sharesdk.system.text.ShortMessage.2
            @Override // cn.sharesdk.system.text.ActionListener
            public void onStart(HashMap<String, Object> hashMap) {
                hashMap.put("ShareParams", shareParams);
                if (ShortMessage.this.listener != null) {
                    ShortMessage.this.listener.onComplete(ShortMessage.this, 9, hashMap);
                }
            }

            @Override // cn.sharesdk.system.text.ActionListener
            public void onError(Throwable th) {
                if (ShortMessage.this.listener != null) {
                    ShortMessage.this.listener.onError(ShortMessage.this, 9, th);
                }
            }

            @Override // cn.sharesdk.system.text.ActionListener
            public void onComplete(HashMap<String, Object> hashMap) {
                hashMap.put("ShareParams", shareParams);
                if (ShortMessage.this.listener != null) {
                    ShortMessage.this.listener.onComplete(ShortMessage.this, 9, hashMap);
                }
            }
        };
        String text = shareParams.getText();
        String imagePath = shareParams.getImagePath();
        String imageUrl = shareParams.getImageUrl();
        shareParams.setText(getShortLintk(text, false));
        try {
            if (!TextUtils.isEmpty(imagePath) || !TextUtils.isEmpty(imageUrl)) {
                if (TextUtils.isEmpty(imagePath)) {
                    imagePath = BitmapHelper.downloadBitmap(MobSDK.getContext(), imageUrl);
                }
                File file = new File(imagePath);
                if (file.exists()) {
                    shareParams.setImagePath(file.getAbsolutePath());
                }
            }
        } catch (Throwable th) {
            if (this.listener != null) {
                this.listener.onError(this, 9, th);
            }
        }
        ShortMessageActivity shortMessageActivity = new ShortMessageActivity();
        shortMessageActivity.setActionListener(actionListener);
        shortMessageActivity.setParams(shareParams);
        shortMessageActivity.show(MobSDK.getContext(), null);
    }

    @Override // cn.sharesdk.framework.Platform
    public void follow(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 6);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void timeline(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 7);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void userInfor(String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 8);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void getFriendList(int i, int i2, String str) {
        if (this.listener != null) {
            this.listener.onCancel(this, 2);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public void doCustomerProtocol(String str, String str2, int i, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) {
        if (this.listener != null) {
            this.listener.onCancel(this, i);
        }
    }

    @Override // cn.sharesdk.framework.Platform
    public ShareEvent.C1746a filterShareContent(Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
        ShareEvent.C1746a c1746a = new ShareEvent.C1746a();
        c1746a.f2816b = shareParams.getText();
        String imagePath = shareParams.getImagePath();
        if (imagePath != null) {
            c1746a.f2819e.add(imagePath);
        }
        return c1746a;
    }
}
