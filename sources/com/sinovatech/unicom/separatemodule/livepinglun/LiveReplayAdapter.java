package com.sinovatech.unicom.separatemodule.livepinglun;

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
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.keyboard.SpanStringUtils;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveReplayEntity;
import io.reactivex.functions.Consumer;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveReplayAdapter extends RecyclerView.Adapter<LiveTopicHolder> {
    private AppCompatActivity activityContext;
    private IReplayInterface anInterface;
    private List<LiveReplayEntity.ReplyListEntity> list;
    private ManagerPinglun managerPinglun;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IReplayInterface {
        void onHuifuClick(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onBindViewHolder$1(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onBindViewHolder$3(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onBindViewHolder$6(String str) throws Exception {
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    public LiveReplayAdapter(AppCompatActivity appCompatActivity, List<LiveReplayEntity.ReplyListEntity> list) {
        this.activityContext = appCompatActivity;
        this.list = list;
        this.managerPinglun = new ManagerPinglun(appCompatActivity);
    }

    public void update(List<LiveReplayEntity.ReplyListEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setHuifuClickInterface(IReplayInterface iReplayInterface) {
        this.anInterface = iReplayInterface;
    }

    public List<LiveReplayEntity.ReplyListEntity> getList() {
        return this.list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public LiveTopicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new LiveHeaderHolder(LayoutInflater.from(this.activityContext).inflate(2131493316, viewGroup, false));
        }
        return new LiveCommentHolder(LayoutInflater.from(this.activityContext).inflate(2131493318, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull LiveTopicHolder liveTopicHolder, final int i) {
        int i2;
        int i3;
        if (liveTopicHolder instanceof LiveHeaderHolder) {
            final LiveHeaderHolder liveHeaderHolder = (LiveHeaderHolder) liveTopicHolder;
            final LiveReplayEntity.ReplyListEntity replyListEntity = this.list.get(i);
            GlideApp.with((FragmentActivity) this.activityContext).load(replyListEntity.getReplyedHeadImg()).placeholder(2131231245).into(liveHeaderHolder.icon);
            if (TextUtils.isEmpty(replyListEntity.getReplyerHeadImg()) || replyListEntity.getReplyerHeadImg().endsWith("user.png")) {
                liveHeaderHolder.vipImage.setVisibility(8);
            } else {
                liveHeaderHolder.vipImage.setVisibility(0);
            }
            if ("1".equals(replyListEntity.getUserStar())) {
                i3 = 2131232326;
            } else if ("2".equals(replyListEntity.getUserStar())) {
                i3 = 2131232327;
            } else if ("3".equals(replyListEntity.getUserStar())) {
                i3 = 2131232328;
            } else if ("4".equals(replyListEntity.getUserStar())) {
                i3 = 2131232329;
            } else {
                i3 = "5".equals(replyListEntity.getUserStar()) ? 2131232330 : 2131232326;
            }
            liveHeaderHolder.star.setImageResource(i3);
            liveHeaderHolder.name.setText(replyListEntity.getReplyerNick());
            liveHeaderHolder.taocan.setText(replyListEntity.getProductImg());
            liveHeaderHolder.address.setText(replyListEntity.getCityName());
            try {
                if (replyListEntity.getReplyContent().length() > 200) {
                    replyListEntity.setReplyContent(replyListEntity.getReplyContent().substring(0, 200));
                }
                liveHeaderHolder.content.setText(SpanStringUtils.getEmotionContent(1, this.activityContext, liveHeaderHolder.content, replyListEntity.getReplyContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            liveHeaderHolder.date.setText(replyListEntity.getReplyTime());
            TextView textView = liveHeaderHolder.huifu;
            textView.setText(replyListEntity.getRepalyNum() + "回复");
            TextView textView2 = liveHeaderHolder.dianzanText;
            textView2.setText(replyListEntity.getPageGoodNum() + "");
            if (TextUtils.isEmpty(replyListEntity.getUploadImg())) {
                liveHeaderHolder.picture.setVisibility(8);
            } else {
                liveHeaderHolder.picture.setVisibility(0);
                GlideApp.with((FragmentActivity) this.activityContext).load(replyListEntity.getUploadImg()).into(liveHeaderHolder.picture);
            }
            if ("1".equals(replyListEntity.getGoodFlag())) {
                liveHeaderHolder.dianzanImage.setImageResource(2131231807);
                liveHeaderHolder.dianzanText.setTextColor(-1703897);
            } else {
                liveHeaderHolder.dianzanImage.setImageResource(2131231808);
                liveHeaderHolder.dianzanText.setTextColor(-10066330);
            }
            liveHeaderHolder.picture.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$kRzL3jg6o-YM3TJ7K6bj5tOq_-Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.lambda$onBindViewHolder$0(LiveReplayAdapter.this, replyListEntity, view);
                }
            });
            liveHeaderHolder.dianzanLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$rvvtV5fU6cp2_SYnEONJnUlZ6B0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.lambda$onBindViewHolder$2(LiveReplayAdapter.this, replyListEntity, liveHeaderHolder, view);
                }
            });
            if ("1".equals(replyListEntity.getIsDelete())) {
                liveHeaderHolder.del.setVisibility(0);
            } else {
                liveHeaderHolder.del.setVisibility(8);
            }
            liveHeaderHolder.del.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$Fdlpvf-dSA_IZei9K4JbaV0xhuc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.this.managerPinglun.getDel("01", replyListEntity.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$-wRnVcqyJGb2biVoxY_wlZmPElA
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            LiveReplayAdapter.lambda$onBindViewHolder$3((String) obj);
                        }
                    });
                }
            });
        }
        if (liveTopicHolder instanceof LiveCommentHolder) {
            final LiveCommentHolder liveCommentHolder = (LiveCommentHolder) liveTopicHolder;
            final LiveReplayEntity.ReplyListEntity replyListEntity2 = this.list.get(i);
            GlideApp.with((FragmentActivity) this.activityContext).load(replyListEntity2.getReplyerHeadImg()).placeholder(2131231245).into(liveCommentHolder.icon);
            if (TextUtils.isEmpty(replyListEntity2.getReplyerHeadImg()) || replyListEntity2.getReplyerHeadImg().endsWith("user.png")) {
                liveCommentHolder.vipImage.setVisibility(8);
            } else {
                liveCommentHolder.vipImage.setVisibility(0);
            }
            if ("1".equals(replyListEntity2.getUserStar())) {
                i2 = 2131232326;
            } else if ("2".equals(replyListEntity2.getUserStar())) {
                i2 = 2131232327;
            } else if ("3".equals(replyListEntity2.getUserStar())) {
                i2 = 2131232328;
            } else if ("4".equals(replyListEntity2.getUserStar())) {
                i2 = 2131232329;
            } else {
                i2 = "5".equals(replyListEntity2.getUserStar()) ? 2131232330 : 2131232326;
            }
            liveCommentHolder.star.setImageResource(i2);
            liveCommentHolder.name.setText(replyListEntity2.getReplyerNick());
            liveCommentHolder.taocan.setText(replyListEntity2.getProductImg());
            liveCommentHolder.address.setText(replyListEntity2.getCityName());
            try {
                if (replyListEntity2.getReplyContent().length() > 200) {
                    replyListEntity2.setReplyContent(replyListEntity2.getReplyContent().substring(0, 200));
                }
                liveCommentHolder.content.setText(SpanStringUtils.getEmotionContent(1, this.activityContext, liveCommentHolder.content, replyListEntity2.getReplyContent()));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            liveCommentHolder.date.setText(replyListEntity2.getReplyTime());
            liveCommentHolder.huifu.setText("回复");
            TextView textView3 = liveCommentHolder.dianzanText;
            textView3.setText(replyListEntity2.getPageGoodNum() + "");
            liveCommentHolder.toPeople.setText(replyListEntity2.getReplyedNick());
            if ("1".equals(replyListEntity2.getGoodFlag())) {
                liveCommentHolder.dianzanImage.setImageResource(2131231807);
                liveCommentHolder.dianzanText.setTextColor(-1703897);
            } else {
                liveCommentHolder.dianzanImage.setImageResource(2131231808);
                liveCommentHolder.dianzanText.setTextColor(-10066330);
            }
            if (TextUtils.isEmpty(replyListEntity2.getUploadImg()) || (!replyListEntity2.getUploadImg().startsWith("data") && !replyListEntity2.getUploadImg().endsWith("jpeg"))) {
                liveCommentHolder.picture.setVisibility(8);
            } else {
                liveCommentHolder.picture.setVisibility(0);
                GlideApp.with((FragmentActivity) this.activityContext).load(replyListEntity2.getUploadImg()).into(liveCommentHolder.picture);
            }
            liveCommentHolder.picture.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$xFZ-PE4_8VQ8HzVF1Rg2aQ6zYMM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.lambda$onBindViewHolder$5(LiveReplayAdapter.this, replyListEntity2, view);
                }
            });
            liveCommentHolder.dianzanLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$L4xMLhwb0EMO-CVwgEnZXPrdi_I
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.lambda$onBindViewHolder$7(LiveReplayAdapter.this, replyListEntity2, liveCommentHolder, view);
                }
            });
            liveCommentHolder.huifu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$IRVqS7bXtXIlyfkrERLqvGlU5Os
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LiveReplayAdapter.lambda$onBindViewHolder$8(LiveReplayAdapter.this, i, view);
                }
            });
            if ("1".equals(replyListEntity2.getIsDelete())) {
                liveCommentHolder.del.setVisibility(0);
            } else {
                liveCommentHolder.del.setVisibility(8);
            }
            liveCommentHolder.del.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$omYdqvI-uaYa9aXARbmPmsCPEIA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.managerPinglun.getDel("02", replyListEntity2.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$6Oj3m00PMG_jPda9OI3jT5UayDE
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            LiveReplayAdapter.lambda$onBindViewHolder$9(LiveReplayAdapter.this, r2, (String) obj);
                        }
                    });
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(LiveReplayAdapter liveReplayAdapter, LiveReplayEntity.ReplyListEntity replyListEntity, View view) {
        Intent intent = new Intent(liveReplayAdapter.activityContext, LivePreViewActivity.class);
        intent.putExtra("image", replyListEntity.getUploadImg());
        liveReplayAdapter.activityContext.startActivity(intent);
    }

    public static /* synthetic */ void lambda$onBindViewHolder$2(LiveReplayAdapter liveReplayAdapter, LiveReplayEntity.ReplyListEntity replyListEntity, LiveHeaderHolder liveHeaderHolder, View view) {
        String str;
        if ("1".equals(replyListEntity.getGoodFlag())) {
            str = "02";
            replyListEntity.setGoodFlag("0");
        } else {
            str = "01";
            replyListEntity.setGoodFlag("1");
        }
        if ("1".equals(replyListEntity.getGoodFlag())) {
            liveHeaderHolder.dianzanImage.setImageResource(2131231807);
            replyListEntity.setPageGoodNum(Integer.valueOf(replyListEntity.getPageGoodNum().intValue() + 1));
            liveHeaderHolder.dianzanText.setTextColor(-1703897);
        } else {
            liveHeaderHolder.dianzanImage.setImageResource(2131231808);
            liveHeaderHolder.dianzanText.setTextColor(-10066330);
            replyListEntity.setPageGoodNum(Integer.valueOf(Math.max(replyListEntity.getPageGoodNum().intValue() - 1, 0)));
        }
        TextView textView = liveHeaderHolder.dianzanText;
        textView.setText(replyListEntity.getPageGoodNum() + "");
        liveReplayAdapter.managerPinglun.getDianzan("02", str, replyListEntity.getId(), replyListEntity.getReplyerMobile()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$DCt6s3hqI_TExsjcRZqqdrxpRjs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayAdapter.lambda$onBindViewHolder$1((String) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onBindViewHolder$5(LiveReplayAdapter liveReplayAdapter, LiveReplayEntity.ReplyListEntity replyListEntity, View view) {
        Intent intent = new Intent(liveReplayAdapter.activityContext, LivePreViewActivity.class);
        intent.putExtra("image", replyListEntity.getUploadImg());
        liveReplayAdapter.activityContext.startActivity(intent);
    }

    public static /* synthetic */ void lambda$onBindViewHolder$7(LiveReplayAdapter liveReplayAdapter, LiveReplayEntity.ReplyListEntity replyListEntity, LiveCommentHolder liveCommentHolder, View view) {
        String str;
        if ("1".equals(replyListEntity.getGoodFlag())) {
            str = "02";
            replyListEntity.setGoodFlag("0");
        } else {
            str = "01";
            replyListEntity.setGoodFlag("1");
        }
        if ("1".equals(replyListEntity.getGoodFlag())) {
            liveCommentHolder.dianzanImage.setImageResource(2131231807);
            replyListEntity.setPageGoodNum(Integer.valueOf(replyListEntity.getPageGoodNum().intValue() + 1));
            liveCommentHolder.dianzanText.setTextColor(-1703897);
        } else {
            liveCommentHolder.dianzanImage.setImageResource(2131231808);
            liveCommentHolder.dianzanText.setTextColor(-10066330);
            replyListEntity.setPageGoodNum(Integer.valueOf(Math.max(replyListEntity.getPageGoodNum().intValue() - 1, 0)));
        }
        TextView textView = liveCommentHolder.dianzanText;
        textView.setText(replyListEntity.getPageGoodNum() + "");
        liveReplayAdapter.managerPinglun.getDianzan("03", str, replyListEntity.getId(), replyListEntity.getReplyerMobile()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$LiveReplayAdapter$LnJwctDbswabDWhAC5T8ADLqd6M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReplayAdapter.lambda$onBindViewHolder$6((String) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onBindViewHolder$8(LiveReplayAdapter liveReplayAdapter, int i, View view) {
        IReplayInterface iReplayInterface = liveReplayAdapter.anInterface;
        if (iReplayInterface != null) {
            iReplayInterface.onHuifuClick(i);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$9(LiveReplayAdapter liveReplayAdapter, int i, String str) throws Exception {
        liveReplayAdapter.list.remove(i);
        liveReplayAdapter.notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class LiveTopicHolder extends RecyclerView.ViewHolder {
        public LiveTopicHolder(@NonNull View view) {
            super(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveHeaderHolder extends LiveTopicHolder {
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

        public LiveHeaderHolder(@NonNull View view) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveCommentHolder extends LiveTopicHolder {
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
        private final TextView toPeople;
        private final ImageView vipImage;

        public LiveCommentHolder(@NonNull View view) {
            super(view);
            ((LinearLayout) view.findViewById(2131297649)).setVisibility(0);
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
            this.toPeople = (TextView) view.findViewById(2131297658);
            this.picture = (ImageView) view.findViewById(2131297656);
            this.vipImage = (ImageView) view.findViewById(2131297655);
        }
    }
}
