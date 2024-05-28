package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserLibaoEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLibao;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import com.sinovatech.unicom.separatemodule.user.view.GiftTextView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserLibaoHolderVH extends RVItemViewHolder {
    private GiftTextView giftTextView1;
    private GiftTextView giftTextView2;
    private GiftTextView giftTextView3;
    private GiftTextView giftTextView4;
    private boolean isLibaoChangeState;
    private TextView libaoTitle;
    private UserLibaoEntity.ResultDTO mResult;
    private ManagerUserLibao managerUserLibao;

    public UserLibaoHolderVH(Activity activity, View view) {
        super(activity, view);
        this.isLibaoChangeState = true;
        this.managerUserLibao = new ManagerUserLibao((AppCompatActivity) activity);
        this.giftTextView1 = (GiftTextView) view.findViewById(2131299401);
        this.giftTextView2 = (GiftTextView) view.findViewById(2131299402);
        this.giftTextView3 = (GiftTextView) view.findViewById(2131299403);
        this.giftTextView4 = (GiftTextView) view.findViewById(2131299404);
        this.libaoTitle = (TextView) view.findViewById(2131297598);
        Typeface createFromAsset = Typeface.createFromAsset(activity.getAssets(), "bebas_regular.otf");
        this.giftTextView1.setTypeface(createFromAsset);
        this.giftTextView2.setTypeface(createFromAsset);
        this.giftTextView3.setTypeface(createFromAsset);
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            this.isLibaoChangeState = rVItemEntity.isLibaoChangeState;
            if (!rVItemEntity.refresh) {
                if (this.mResult != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new UserLightEntity("5020101", this.mResult.getTelVoucherTitle()));
                    arrayList.add(new UserLightEntity("5020102", this.mResult.getTrafficPacketTitle()));
                    arrayList.add(new UserLightEntity("5020103", this.mResult.getVoicePackageTitle()));
                    arrayList.add(new UserLightEntity("5020104", this.mResult.getAllGiftTitle()));
                    ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.LIBAO, arrayList);
                    return;
                }
                return;
            }
            rVItemEntity.refresh = false;
            if (this.isLibaoChangeState) {
                setVisibility(false);
                loadData("2", false);
            }
            loadData("1", false);
            ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.LIBAO, this.itemView);
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.user.viewholder.UserLibaoHolderVH$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C95881 implements Consumer<UserLibaoEntity> {
        final /* synthetic */ String val$type;

        C95881(String str) {
            UserLibaoHolderVH.this = r1;
            this.val$type = str;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(UserLibaoEntity userLibaoEntity) throws Exception {
            if ("0000".equals(userLibaoEntity.getRespCode())) {
                UserLibaoEntity.ResultDTO result = userLibaoEntity.getResult();
                if (result == null) {
                    if ("1".equals(this.val$type)) {
                        UserLibaoHolderVH.this.loadData("2", true);
                        return;
                    } else {
                        UserLibaoHolderVH.this.setVisibility(false);
                        return;
                    }
                }
                if ("1".equals(result.getMyGiftFloorSwitch())) {
                    UserLibaoHolderVH.this.setVisibility(true);
                } else {
                    UserLibaoHolderVH.this.setVisibility(false);
                }
                UserLibaoHolderVH.this.mResult = result;
                UserLibaoHolderVH.this.libaoTitle.setText(result.getTitle());
                UserLibaoHolderVH.this.itemView.setOnClickListener(new $$Lambda$UserLibaoHolderVH$1$CW39xmUaw4o2NMSrAXwUgknio5M(this, result));
                if ("2".equals(this.val$type)) {
                    result.setTelVoucherData("-");
                    result.setTrafficPacketData("-");
                    result.setVoicePackageData("-");
                }
                UserLibaoHolderVH.this.giftTextView1.setData(1, result, this.val$type);
                UserLibaoHolderVH.this.giftTextView2.setData(2, result, this.val$type);
                UserLibaoHolderVH.this.giftTextView3.setData(3, result, this.val$type);
                UserLibaoHolderVH.this.giftTextView4.setData(4, result, this.val$type);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new UserLightEntity("5020101", UserLibaoHolderVH.this.mResult.getTelVoucherTitle()));
                arrayList.add(new UserLightEntity("5020102", UserLibaoHolderVH.this.mResult.getTrafficPacketTitle()));
                arrayList.add(new UserLightEntity("5020103", UserLibaoHolderVH.this.mResult.getVoicePackageTitle()));
                arrayList.add(new UserLightEntity("5020104", UserLibaoHolderVH.this.mResult.getAllGiftTitle()));
                ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.LIBAO, arrayList);
            } else if ("1".equals(this.val$type)) {
                UserLibaoHolderVH.this.loadData("2", true);
            } else {
                UserLibaoHolderVH.this.setVisibility(false);
            }
        }

        public static /* synthetic */ void lambda$accept$0(C95881 c95881, final UserLibaoEntity.ResultDTO resultDTO, View view) {
            if (App.hasLogined()) {
                return;
            }
            new AvoidOnResult(UserLibaoHolderVH.this.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserLibaoHolderVH.1.1
                {
                    C95881.this = c95881;
                }

                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        IntentManager.gotoWebViewActivity(UserLibaoHolderVH.this.activityContext, resultDTO.getAllGiftUrl(), "");
                    }
                }
            });
        }
    }

    public void loadData(final String str, boolean z) {
        this.managerUserLibao.getLibao(str).subscribe(new C95881(str), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserLibaoHolderVH.2
            {
                UserLibaoHolderVH.this = this;
            }

            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                if ("1".equals(str)) {
                    UserLibaoHolderVH.this.loadData("2", true);
                }
            }
        });
    }
}
