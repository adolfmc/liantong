package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import java.util.Iterator;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AILPChatListRecyclerViewAdapter extends BaseChatListRecyclerViewAdapter<AlivcLiveMessageInfo, ChatViewHolder> implements View.OnClickListener {
    private Animation animation;
    private Bitmap giftBitMap1;
    private Bitmap giftBitMap2;
    private Bitmap giftBitMap20001;
    private Bitmap giftBitMap20002;
    private Bitmap giftBitMap20003;
    private Bitmap giftBitMap20004;
    private Bitmap giftBitMap20005;
    private Bitmap giftBitMap20006;
    private Bitmap giftBitMap3;
    private Bitmap giftBitMap4;
    private Bitmap giftBitMap5;
    private Bitmap giftBitMap6;
    private Bitmap giftBitMap7;
    private Bitmap giftBitMap8;
    private List<GiftEntity> giftList;
    private Bitmap levelBitMap1;
    private Bitmap levelBitMap2;
    private Bitmap levelBitMap3;
    private Bitmap levelBitMap4;
    private Bitmap levelBitMap5;
    private OnItemLongClickedListener listener;
    private Context mContext;
    private Bitmap mgrBitMap;
    private int size;
    private Bitmap zhongJiangBitpmap1;
    private Bitmap zhongJiangBitpmap2;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemLongClickedListener {
        void onItemClicked(String str, String str2, int i);

        void onItemLongClicked(String str, String str2);
    }

    private boolean canClick(int i) {
        return i == 6;
    }

    /*  JADX ERROR: Failed to decode insn: 0x040E: UNKNOWN(0x937A), method: com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter$ChatViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x040E: UNKNOWN(0x937A)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter.ChatViewHolder r17, int r18) {
        /*
            Method dump skipped, instructions count: 1200
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.AILPChatListRecyclerViewAdapter$ChatViewHolder, int):void");
    }

    public AILPChatListRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.size = (UIUtils.dip2px((Activity) context, 11.0f) * 13) / 10;
        int i = (this.size * 5) / 2;
        Bitmap decodeResource = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230924);
        int i2 = this.size;
        this.levelBitMap1 = Bitmap.createScaledBitmap(decodeResource, i2, i2, true);
        Bitmap decodeResource2 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230925);
        int i3 = this.size;
        this.levelBitMap2 = Bitmap.createScaledBitmap(decodeResource2, i3, i3, true);
        Bitmap decodeResource3 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230926);
        int i4 = this.size;
        this.levelBitMap3 = Bitmap.createScaledBitmap(decodeResource3, i4, i4, true);
        Bitmap decodeResource4 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230927);
        int i5 = this.size;
        this.levelBitMap4 = Bitmap.createScaledBitmap(decodeResource4, i5, i5, true);
        Bitmap decodeResource5 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230928);
        int i6 = this.size;
        this.levelBitMap5 = Bitmap.createScaledBitmap(decodeResource5, i6, i6, true);
        Bitmap decodeResource6 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230939);
        int i7 = this.size;
        this.mgrBitMap = Bitmap.createScaledBitmap(decodeResource6, i7 * 2, i7, true);
        this.giftBitMap1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230947), i, i, true);
        this.giftBitMap2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230948), i, i, true);
        this.giftBitMap3 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230949), i, i, true);
        this.giftBitMap4 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230950), i, i, true);
        this.giftBitMap5 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230951), i, i, true);
        this.giftBitMap6 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230952), i, i, true);
        this.giftBitMap7 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230953), i, i, true);
        this.giftBitMap8 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230954), i, i, true);
        this.giftBitMap20001 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230955), i, i, true);
        this.giftBitMap20002 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230956), i, i, true);
        this.giftBitMap20003 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230957), i, i, true);
        this.giftBitMap20004 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230958), i, i, true);
        this.giftBitMap20005 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230959), i, i, true);
        this.giftBitMap20006 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.mContext.getResources(), 2131230960), i, i, true);
        Bitmap decodeResource7 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131232110);
        int i8 = this.size;
        this.zhongJiangBitpmap1 = Bitmap.createScaledBitmap(decodeResource7, i8, i8, true);
        Bitmap decodeResource8 = BitmapFactory.decodeResource(this.mContext.getResources(), 2131230912);
        int i9 = this.size;
        this.zhongJiangBitpmap2 = Bitmap.createScaledBitmap(decodeResource8, i9, i9, true);
        this.giftList = CacheDataCenter.getInstance().getGiftList();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChatViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131492970, viewGroup, false));
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(AILPChatListRecyclerViewAdapter aILPChatListRecyclerViewAdapter, AlivcLiveMessageInfo alivcLiveMessageInfo, int i, View view) {
        if (OptionValveUtil.INSTENCE.isShowLiveReport() && aILPChatListRecyclerViewAdapter.listener != null && aILPChatListRecyclerViewAdapter.canClick(alivcLiveMessageInfo.getType())) {
            aILPChatListRecyclerViewAdapter.listener.onItemClicked(alivcLiveMessageInfo.getSendName(), alivcLiveMessageInfo.getDataContent(), i);
        }
    }

    public static /* synthetic */ boolean lambda$onBindViewHolder$1(AILPChatListRecyclerViewAdapter aILPChatListRecyclerViewAdapter, AlivcLiveMessageInfo alivcLiveMessageInfo, View view) {
        if (aILPChatListRecyclerViewAdapter.listener == null || !aILPChatListRecyclerViewAdapter.canClick(alivcLiveMessageInfo.getType())) {
            return false;
        }
        aILPChatListRecyclerViewAdapter.listener.onItemLongClicked(alivcLiveMessageInfo.getSendName(), alivcLiveMessageInfo.getUserId());
        return false;
    }

    private void fetchKickOutInfo(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (int i = 0; i < getLiveCommentItem().size(); i++) {
            if (str.equals(getLiveCommentItem().get(i).getUserId())) {
                getLiveCommentItem().get(i).setKickout(z);
            }
        }
    }

    private boolean checkKickout(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < getLiveCommentItem().size(); i++) {
            if (str.equals(getLiveCommentItem().get(i).getUserId()) && getLiveCommentItem().get(i).isKickout()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getLiveCommentItem().get(i).getType();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGiftImg;
        LinearLayout llComment;
        TextView tvComment;
        TextView tvCopy;
        TextView tvGiftNum;
        TextView tvImgCopy;
        TextView tvNumCopy;

        public ChatViewHolder(View view) {
            super(view);
            this.tvComment = (TextView) view.findViewById(2131298915);
            this.tvGiftNum = (TextView) view.findViewById(2131298961);
            this.ivGiftImg = (ImageView) view.findViewById(2131298959);
            this.tvCopy = (TextView) view.findViewById(2131298916);
            this.tvImgCopy = (TextView) view.findViewById(2131298960);
            this.tvNumCopy = (TextView) view.findViewById(2131298962);
            this.llComment = (LinearLayout) view.findViewById(2131297704);
        }
    }

    private Bitmap getLevelBitMap(String str) {
        Bitmap bitmap = this.levelBitMap1;
        if ("2".equals(str)) {
            bitmap = this.levelBitMap2;
        }
        if ("3".equals(str)) {
            bitmap = this.levelBitMap3;
        }
        if ("4".equals(str)) {
            bitmap = this.levelBitMap4;
        }
        return "5".equals(str) ? this.levelBitMap5 : bitmap;
    }

    private int getLevelColor(String str) {
        int i = "3".equals(str) ? -6070 : -9240582;
        if ("4".equals(str)) {
            i = -4859649;
        }
        if ("5".equals(str)) {
            return -27919;
        }
        return i;
    }

    private Bitmap getGiftBitMap(String str) {
        Bitmap bitmap = "10001".equals(str) ? this.giftBitMap1 : null;
        if ("10002".equals(str)) {
            bitmap = this.giftBitMap2;
        }
        if ("10003".equals(str)) {
            bitmap = this.giftBitMap3;
        }
        if ("10004".equals(str)) {
            bitmap = this.giftBitMap4;
        }
        if ("10005".equals(str)) {
            bitmap = this.giftBitMap5;
        }
        if ("10006".equals(str)) {
            bitmap = this.giftBitMap6;
        }
        if ("10007".equals(str)) {
            bitmap = this.giftBitMap7;
        }
        if ("10008".equals(str)) {
            bitmap = this.giftBitMap8;
        }
        if ("20001".equals(str)) {
            bitmap = this.giftBitMap20001;
        }
        if ("20002".equals(str)) {
            bitmap = this.giftBitMap20002;
        }
        if ("20003".equals(str)) {
            bitmap = this.giftBitMap20003;
        }
        if ("20004".equals(str)) {
            bitmap = this.giftBitMap20004;
        }
        if ("20005".equals(str)) {
            bitmap = this.giftBitMap20005;
        }
        return "20006".equals(str) ? this.giftBitMap20006 : bitmap;
    }

    private String getGiftIcon(String str) {
        GiftEntity giftEntity;
        Iterator<GiftEntity> it = this.giftList.iterator();
        while (true) {
            if (!it.hasNext()) {
                giftEntity = null;
                break;
            }
            giftEntity = it.next();
            if (giftEntity.getGiftCode().equals(str)) {
                break;
            }
        }
        if (giftEntity != null) {
            if (TextUtils.isEmpty(giftEntity.getImgFileChat())) {
                return giftEntity.getGiftImgSrc();
            }
            return giftEntity.getImgFileChat();
        }
        return str;
    }

    private Bitmap getJiangpinBitmap(int i) {
        if (i == 0) {
            return this.zhongJiangBitpmap1;
        }
        return this.zhongJiangBitpmap2;
    }

    public void setListener(OnItemLongClickedListener onItemLongClickedListener) {
        this.listener = onItemLongClickedListener;
    }
}
