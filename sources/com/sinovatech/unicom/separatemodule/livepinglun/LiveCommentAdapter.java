package com.sinovatech.unicom.separatemodule.livepinglun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.keyboard.SpanStringUtils;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity;
import io.reactivex.functions.Consumer;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveCommentAdapter extends RecyclerView.Adapter<LiveHolder> {
    private AppCompatActivity activityContext;
    private IHuifuClickInterface anInterface;
    private List<LiveCommentEntity.CommentListBean> list;
    private IHuifuDelInterface mHuifuDel;
    private ManagerPinglun managerPinglun;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IHuifuClickInterface {
        void clickHuuifu();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IHuifuDelInterface {
        void delHuifu();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onBindViewHolder$1(String str) throws Exception {
    }

    public LiveCommentAdapter(AppCompatActivity appCompatActivity, List<LiveCommentEntity.CommentListBean> list) {
        this.activityContext = appCompatActivity;
        this.list = list;
        this.managerPinglun = new ManagerPinglun(appCompatActivity);
    }

    public void setHuiFuClickListener(IHuifuClickInterface iHuifuClickInterface) {
        this.anInterface = iHuifuClickInterface;
    }

    public void update(List<LiveCommentEntity.CommentListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<LiveCommentEntity.CommentListBean> getList() {
        return this.list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public LiveHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LiveHolder(LayoutInflater.from(this.activityContext).inflate(2131493318, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @SuppressLint({"SetTextI18n"})
    public void onBindViewHolder(@NonNull final LiveHolder liveHolder, final int i) {
        final LiveCommentEntity.CommentListBean commentListBean = this.list.get(i);
        GlideApp.with((FragmentActivity) this.activityContext).load(commentListBean.getHeadPhoto()).placeholder(2131232482).into(liveHolder.icon);
        if (TextUtils.isEmpty(commentListBean.getHeadPhoto()) || commentListBean.getHeadPhoto().endsWith("user.png")) {
            liveHolder.vipImage.setVisibility(8);
        } else {
            liveHolder.vipImage.setVisibility(0);
        }
        int i2 = 2131232326;
        if (!"1".equals(commentListBean.getUserStar())) {
            if ("2".equals(commentListBean.getUserStar())) {
                i2 = 2131232327;
            } else if ("3".equals(commentListBean.getUserStar())) {
                i2 = 2131232328;
            } else if ("4".equals(commentListBean.getUserStar())) {
                i2 = 2131232329;
            } else if ("5".equals(commentListBean.getUserStar())) {
                i2 = 2131232330;
            }
        }
        liveHolder.star.setImageResource(i2);
        liveHolder.name.setText(commentListBean.getUserNickReal());
        liveHolder.taocan.setText(commentListBean.getProductImg());
        liveHolder.address.setText(commentListBean.getCityName());
        try {
            if (commentListBean.getCommentContent().length() > 200) {
                commentListBean.setCommentContent(commentListBean.getCommentContent().substring(0, 200));
            }
            liveHolder.content.setText(SpanStringUtils.getEmotionContent(1, this.activityContext, liveHolder.content, commentListBean.getCommentContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        liveHolder.date.setText(commentListBean.getCommentTime());
        TextView textView = liveHolder.huifu;
        textView.setText(commentListBean.getReplyNum() + "回复");
        TextView textView2 = liveHolder.dianzanText;
        textView2.setText(commentListBean.getPageGoodNum() + "");
        if ("1".equals(commentListBean.getGoodFlag())) {
            liveHolder.dianzanImage.setImageResource(2131231807);
            liveHolder.dianzanText.setTextColor(-1703897);
        } else {
            liveHolder.dianzanImage.setImageResource(2131231808);
            liveHolder.dianzanText.setTextColor(-10066330);
        }
        if (TextUtils.isEmpty(commentListBean.getUploadImg()) || (!commentListBean.getUploadImg().startsWith("data") && !commentListBean.getUploadImg().endsWith("jpeg") && !commentListBean.getUploadImg().endsWith("png"))) {
            liveHolder.picture.setVisibility(8);
        } else {
            liveHolder.picture.setVisibility(0);
            GlideApp.with((FragmentActivity) this.activityContext).load(commentListBean.getUploadImg()).into(liveHolder.picture);
        }
        liveHolder.picture.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$ebv2Gw7DLec0Ix7zs_SRty_rbDI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveCommentAdapter.lambda$onBindViewHolder$0(LiveCommentAdapter.this, commentListBean, view);
            }
        });
        liveHolder.dianzanLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$_3bEfasNmKpq_UlyZ7UYoupy-0w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveCommentAdapter.lambda$onBindViewHolder$2(LiveCommentAdapter.this, commentListBean, liveHolder, view);
            }
        });
        liveHolder.huifu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$LUSuEcbT5mmzLCBPaq3RcCopWz4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveCommentAdapter.lambda$onBindViewHolder$3(LiveCommentAdapter.this, commentListBean, view);
            }
        });
        if ("1".equals(commentListBean.getIsDelete())) {
            liveHolder.del.setVisibility(0);
        } else {
            liveHolder.del.setVisibility(8);
        }
        liveHolder.del.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$O6WZ9ZKIr7Tdtqufj4zaMH-IH1I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                r0.managerPinglun.getDel("01", commentListBean.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$GnCF3Lq6QeXNhgCjLsmtZy64IE0
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        LiveCommentAdapter.lambda$onBindViewHolder$4(LiveCommentAdapter.this, r2, (String) obj);
                    }
                });
            }
        });
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(LiveCommentAdapter liveCommentAdapter, LiveCommentEntity.CommentListBean commentListBean, View view) {
        Intent intent = new Intent(liveCommentAdapter.activityContext, LivePreViewActivity.class);
        intent.putExtra("image", commentListBean.getUploadImg());
        liveCommentAdapter.activityContext.startActivity(intent);
    }

    public static /* synthetic */ void lambda$onBindViewHolder$2(LiveCommentAdapter liveCommentAdapter, LiveCommentEntity.CommentListBean commentListBean, LiveHolder liveHolder, View view) {
        String str;
        if ("1".equals(commentListBean.getGoodFlag())) {
            str = "02";
            commentListBean.setGoodFlag("0");
        } else {
            str = "01";
            commentListBean.setGoodFlag("1");
        }
        if ("1".equals(commentListBean.getGoodFlag())) {
            liveHolder.dianzanImage.setImageResource(2131231807);
            commentListBean.setPageGoodNum(Integer.valueOf(commentListBean.getPageGoodNum().intValue() + 1));
            liveHolder.dianzanText.setTextColor(-1703897);
        } else {
            liveHolder.dianzanImage.setImageResource(2131231808);
            liveHolder.dianzanText.setTextColor(-10066330);
            commentListBean.setPageGoodNum(Integer.valueOf(Math.max(commentListBean.getPageGoodNum().intValue() - 1, 0)));
        }
        TextView textView = liveHolder.dianzanText;
        textView.setText(commentListBean.getPageGoodNum() + "");
        liveCommentAdapter.managerPinglun.getDianzan("02", str, commentListBean.getId(), commentListBean.getNickName()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveCommentAdapter$8KmIU-de1cxXifcbOIo98QSqv8k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveCommentAdapter.lambda$onBindViewHolder$1((String) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onBindViewHolder$3(LiveCommentAdapter liveCommentAdapter, LiveCommentEntity.CommentListBean commentListBean, View view) {
        if ("01".equals(commentListBean.getCheckFlag())) {
            VideoDetailsActivity.isHuifu = true;
            Intent intent = new Intent(liveCommentAdapter.activityContext, LiveReplayActivity.class);
            intent.putExtra("commentId", commentListBean.getId());
            new AvoidOnResult(liveCommentAdapter.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentAdapter.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent2) {
                    LiveCommentAdapter.this.anInterface.clickHuuifu();
                }
            });
        } else if ("00".equals(commentListBean.getCheckFlag())) {
            UIUtils.toast("正在审核中");
        } else if ("02".equals(commentListBean.getCheckFlag())) {
            UIUtils.toast("审核未通过");
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$4(LiveCommentAdapter liveCommentAdapter, int i, String str) throws Exception {
        liveCommentAdapter.list.remove(i);
        IHuifuDelInterface iHuifuDelInterface = liveCommentAdapter.mHuifuDel;
        if (iHuifuDelInterface != null) {
            iHuifuDelInterface.delHuifu();
        }
        liveCommentAdapter.notifyDataSetChanged();
    }

    public void setmHuifuDel(IHuifuDelInterface iHuifuDelInterface) {
        this.mHuifuDel = iHuifuDelInterface;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveHolder extends RecyclerView.ViewHolder {
        private final TextView address;
        private final TextView content;
        private final TextView date;
        private final TextView del;
        private final ImageView dianzanImage;
        private final LinearLayout dianzanLayout;
        private final TextView dianzanText;
        private final TextView huifu;
        private final ImageView icon;
        private final TextView name;
        private final ImageView picture;
        private final ImageView star;
        private final TextView taocan;
        private final ImageView vipImage;

        public LiveHolder(@NonNull View view) {
            super(view);
            ((LinearLayout) view.findViewById(2131297649)).setVisibility(8);
            this.icon = (ImageView) view.findViewById(2131297653);
            this.star = (ImageView) view.findViewById(2131297654);
            this.name = (TextView) view.findViewById(2131297651);
            this.taocan = (TextView) view.findViewById(2131297657);
            this.address = (TextView) view.findViewById(2131297643);
            this.content = (TextView) view.findViewById(2131297644);
            this.date = (TextView) view.findViewById(2131297645);
            this.huifu = (TextView) view.findViewById(2131297652);
            this.del = (TextView) view.findViewById(2131297646);
            this.dianzanImage = (ImageView) view.findViewById(2131297650);
            this.dianzanText = (TextView) view.findViewById(2131297648);
            this.dianzanLayout = (LinearLayout) view.findViewById(2131297647);
            this.picture = (ImageView) view.findViewById(2131297656);
            this.vipImage = (ImageView) view.findViewById(2131297655);
        }
    }
}
