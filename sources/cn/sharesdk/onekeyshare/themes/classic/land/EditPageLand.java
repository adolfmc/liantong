package cn.sharesdk.onekeyshare.themes.classic.land;

import android.graphics.Bitmap;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import cn.sharesdk.onekeyshare.themes.classic.EditPage;
import cn.sharesdk.onekeyshare.themes.classic.XView;
import com.mob.tools.gui.AsyncImageView;
import com.mob.tools.utils.ResHelper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EditPageLand extends EditPage implements TextWatcher, View.OnClickListener, Runnable {
    private static final int DESIGN_BOTTOM_HEIGHT = 75;
    private static final int DESIGN_LEFT_PADDING = 40;
    private static final int DESIGN_REMOVE_THUMB_HEIGHT_L = 60;
    private static final int DESIGN_SCREEN_WIDTH = 720;
    private static final int DESIGN_THUMB_HEIGHT_L = 280;
    private static final int DESIGN_TITLE_HEIGHT_L = 70;

    public EditPageLand(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    @Override // cn.sharesdk.onekeyshare.themes.classic.EditPage, com.mob.tools.FakeActivity
    public void onCreate() {
        super.onCreate();
        float screenHeight = ResHelper.getScreenHeight(this.activity) / 720.0f;
        this.maxBodyHeight = 0;
        this.llPage = new LinearLayout(this.activity);
        this.llPage.setOrientation(1);
        this.activity.setContentView(this.llPage);
        this.rlTitle = new RelativeLayout(this.activity);
        this.rlTitle.setBackgroundColor(-1644052);
        this.llPage.addView(this.rlTitle, new LinearLayout.LayoutParams(-1, (int) (70.0f * screenHeight)));
        initTitle(this.rlTitle, screenHeight);
        RelativeLayout relativeLayout = new RelativeLayout(this.activity);
        relativeLayout.setBackgroundColor(-1);
        this.llPage.addView(relativeLayout, new LinearLayout.LayoutParams(-1, -2));
        initBody(relativeLayout, screenHeight);
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        relativeLayout.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        initShadow(linearLayout, screenHeight);
        this.llBottom = new LinearLayout(this.activity);
        this.llBottom.setOrientation(1);
        this.llPage.addView(this.llBottom, new LinearLayout.LayoutParams(-1, -2));
        initBottom(this.llBottom, screenHeight);
    }

    private void initTitle(RelativeLayout relativeLayout, float f) {
        this.tvCancel = new TextView(this.activity);
        this.tvCancel.setTextColor(-12895429);
        this.tvCancel.setTextSize(2, 18.0f);
        this.tvCancel.setGravity(17);
        int stringRes = ResHelper.getStringRes(this.activity, "ssdk_oks_cancel");
        if (stringRes > 0) {
            this.tvCancel.setText(stringRes);
        }
        int i = (int) (f * 40.0f);
        this.tvCancel.setPadding(i, 0, i, 0);
        relativeLayout.addView(this.tvCancel, new RelativeLayout.LayoutParams(-2, -1));
        this.tvCancel.setOnClickListener(this);
        TextView textView = new TextView(this.activity);
        textView.setTextColor(-12895429);
        textView.setTextSize(2, 22.0f);
        textView.setGravity(17);
        int stringRes2 = ResHelper.getStringRes(this.activity, "ssdk_oks_multi_share");
        if (stringRes2 > 0) {
            textView.setText(stringRes2);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(13);
        relativeLayout.addView(textView, layoutParams);
        this.tvShare = new TextView(this.activity);
        this.tvShare.setTextColor(-37615);
        this.tvShare.setTextSize(2, 18.0f);
        this.tvShare.setGravity(17);
        int stringRes3 = ResHelper.getStringRes(this.activity, "ssdk_oks_share");
        if (stringRes3 > 0) {
            this.tvShare.setText(stringRes3);
        }
        this.tvShare.setPadding(i, 0, i, 0);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams2.addRule(11);
        relativeLayout.addView(this.tvShare, layoutParams2);
        this.tvShare.setOnClickListener(this);
    }

    private void initBody(RelativeLayout relativeLayout, float f) {
        this.svContent = new ScrollView(this.activity);
        relativeLayout.addView(this.svContent, new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(0);
        this.svContent.addView(linearLayout, new FrameLayout.LayoutParams(-1, -2));
        this.etContent = new EditText(this.activity);
        int i = (int) (40.0f * f);
        this.etContent.setPadding(i, i, i, i);
        this.etContent.setBackgroundDrawable(null);
        this.etContent.setTextColor(-12895429);
        this.etContent.setTextSize(2, 21.0f);
        this.etContent.setText(this.f2983sp.getText());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        linearLayout.addView(this.etContent, layoutParams);
        this.etContent.addTextChangedListener(this);
        this.rlThumb = new RelativeLayout(this.activity);
        this.rlThumb.setBackgroundColor(-13553359);
        int i2 = (int) (280.0f * f);
        int i3 = (int) (60.0f * f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i2);
        layoutParams2.topMargin = i;
        layoutParams2.bottomMargin = i;
        layoutParams2.rightMargin = i;
        linearLayout.addView(this.rlThumb, layoutParams2);
        this.aivThumb = new AsyncImageView(this.activity) { // from class: cn.sharesdk.onekeyshare.themes.classic.land.EditPageLand.1
            @Override // com.mob.tools.gui.AsyncImageView, com.mob.tools.gui.BitmapProcessor.BitmapCallback
            public void onImageGot(String str, Bitmap bitmap) {
                EditPageLand.this.thumb = bitmap;
                super.onImageGot(str, bitmap);
            }
        };
        this.aivThumb.setScaleToCropCenter(true);
        this.rlThumb.addView(this.aivThumb, new RelativeLayout.LayoutParams(i2, i2));
        this.aivThumb.setOnClickListener(this);
        initThumb(this.aivThumb);
        this.xvRemove = new XView(this.activity);
        this.xvRemove.setRatio(f);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i3, i3);
        layoutParams3.addRule(10);
        layoutParams3.addRule(11);
        this.rlThumb.addView(this.xvRemove, layoutParams3);
        this.xvRemove.setOnClickListener(this);
    }

    private void initBottom(LinearLayout linearLayout, float f) {
        LinearLayout linearLayout2 = new LinearLayout(this.activity);
        linearLayout2.setPadding(0, 0, 0, 5);
        linearLayout2.setBackgroundColor(-1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, (int) (75.0f * f)));
        this.tvAt = new TextView(this.activity);
        this.tvAt.setTextColor(-12895429);
        this.tvAt.setTextSize(2, 21.0f);
        this.tvAt.setGravity(80);
        this.tvAt.setText("@");
        int i = (int) (40.0f * f);
        this.tvAt.setPadding(i, 0, i, 0);
        linearLayout2.addView(this.tvAt, new LinearLayout.LayoutParams(-2, -1));
        this.tvAt.setOnClickListener(this);
        if (isShowAtUserLayout(this.platform.getName())) {
            this.tvAt.setVisibility(0);
        } else {
            this.tvAt.setVisibility(4);
        }
        this.tvTextCouter = new TextView(this.activity);
        this.tvTextCouter.setTextColor(-12895429);
        this.tvTextCouter.setTextSize(2, 18.0f);
        this.tvTextCouter.setGravity(85);
        onTextChanged(this.etContent.getText(), 0, 0, 0);
        this.tvTextCouter.setPadding(i, 0, i, 0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        layoutParams.weight = 1.0f;
        linearLayout2.addView(this.tvTextCouter, layoutParams);
        View view = new View(this.activity);
        view.setBackgroundColor(-3355444);
        linearLayout.addView(view, new LinearLayout.LayoutParams(-1, f > 1.0f ? (int) f : 1));
    }

    private void initShadow(LinearLayout linearLayout, float f) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, f > 1.0f ? (int) f : 1);
        View view = new View(this.activity);
        view.setBackgroundColor(687865856);
        linearLayout.addView(view, layoutParams);
        View view2 = new View(this.activity);
        view2.setBackgroundColor(335544320);
        linearLayout.addView(view2, layoutParams);
        View view3 = new View(this.activity);
        view3.setBackgroundColor(117440512);
        linearLayout.addView(view3, layoutParams);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0066  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initThumb(com.mob.tools.gui.AsyncImageView r7) {
        /*
            r6 = this;
            cn.sharesdk.framework.Platform$ShareParams r0 = r6.f2983sp
            java.lang.String r0 = r0.getImageUrl()
            cn.sharesdk.framework.Platform$ShareParams r1 = r6.f2983sp
            java.lang.String r1 = r1.getImagePath()
            cn.sharesdk.framework.Platform$ShareParams r2 = r6.f2983sp
            java.lang.String[] r2 = r2.getImageArray()
            android.widget.RelativeLayout r3 = r6.rlThumb
            r4 = 0
            r3.setVisibility(r4)
            boolean r3 = android.text.TextUtils.isEmpty(r1)
            if (r3 != 0) goto L32
            java.io.File r3 = new java.io.File
            r3.<init>(r1)
            boolean r3 = r3.exists()
            if (r3 == 0) goto L32
            android.graphics.Bitmap r3 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch: java.lang.Throwable -> L2e
            goto L33
        L2e:
            r3 = move-exception
            r3.printStackTrace()
        L32:
            r3 = 0
        L33:
            if (r3 == 0) goto L3b
            r6.thumb = r3
            r7.setBitmap(r3)
            goto L5e
        L3b:
            if (r2 == 0) goto L5e
            int r5 = r2.length
            if (r5 <= 0) goto L5e
            r5 = r2[r4]
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L5e
            java.io.File r5 = new java.io.File
            r2 = r2[r4]
            r5.<init>(r2)
            boolean r2 = r5.exists()
            if (r2 == 0) goto L5e
            android.graphics.Bitmap r3 = com.mob.tools.utils.BitmapHelper.getBitmap(r1)     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r1 = move-exception
            r1.printStackTrace()
        L5e:
            if (r3 == 0) goto L66
            r6.thumb = r3
            r7.setBitmap(r3)
            goto L79
        L66:
            if (r3 != 0) goto L72
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L72
            r7.execute(r0, r4)
            goto L79
        L72:
            android.widget.RelativeLayout r7 = r6.rlThumb
            r0 = 8
            r7.setVisibility(r0)
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.onekeyshare.themes.classic.land.EditPageLand.initThumb(com.mob.tools.gui.AsyncImageView):void");
    }
}
