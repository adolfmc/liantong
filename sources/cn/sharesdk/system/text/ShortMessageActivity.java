package cn.sharesdk.system.text;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.LinearLayout;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.UIHandler;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShortMessageActivity extends FakeActivity {
    private ActionListener actionListener;
    private int isFinish = 0;
    private Platform.ShareParams params;

    public void setParams(Platform.ShareParams shareParams) {
        this.params = shareParams;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m21740b().m21742a(e);
        }
        UIHandler.sendEmptyMessage(1, new Handler.Callback() { // from class: cn.sharesdk.system.text.ShortMessageActivity.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                try {
                    ShortMessageActivity.this.startShare();
                    return true;
                } catch (Throwable th) {
                    if (ShortMessageActivity.this.actionListener != null) {
                        ShortMessageActivity.this.actionListener.onError(new Throwable(th));
                        return true;
                    }
                    return true;
                }
            }
        });
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        super.onStop();
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        this.isFinish++;
        if (this.isFinish == 2) {
            ActionListener actionListener = this.actionListener;
            if (actionListener != null) {
                actionListener.onComplete(new HashMap<>());
            }
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startShare() {
        Helper m21579a = Helper.m21579a();
        String text = this.params.getText();
        String title = this.params.getTitle();
        String address = this.params.getAddress();
        String imagePath = this.params.getImagePath();
        int shareType = this.params.getShareType();
        String filePath = this.params.getFilePath();
        if (shareType == 6 && !TextUtils.isEmpty(filePath)) {
            if (address == null) {
                address = "";
            }
            m21579a.m21575a(address, title, text, filePath, this.actionListener);
            return;
        }
        if (address == null) {
            address = "";
        }
        m21579a.m21572b(address, title, text, imagePath, this.actionListener);
    }
}
