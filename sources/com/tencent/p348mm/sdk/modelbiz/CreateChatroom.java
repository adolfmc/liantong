package com.tencent.p348mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;
import com.tencent.p348mm.sdk.p354b.C10398f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CreateChatroom {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom$Req */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Req extends BaseReq {
        public String chatroomName;
        public String chatroomNickName;
        public String extMsg;
        public String groupId;

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public boolean checkArgs() {
            return !C10398f.m6184c(this.groupId);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public int getType() {
            return 14;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseReq
        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_create_chatroom_group_id", this.groupId);
            bundle.putString("_wxapi_create_chatroom_chatroom_name", this.chatroomName);
            bundle.putString("_wxapi_create_chatroom_chatroom_nickname", this.chatroomNickName);
            bundle.putString("_wxapi_create_chatroom_ext_msg", this.extMsg);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.modelbiz.CreateChatroom$Resp */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Resp extends BaseResp {
        public String extMsg;

        public Resp(Bundle bundle) {
            fromBundle(bundle);
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public boolean checkArgs() {
            return true;
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.extMsg = bundle.getString("_wxapi_create_chatroom_ext_msg");
        }

        @Override // com.tencent.p348mm.sdk.modelbase.BaseResp
        public int getType() {
            return 14;
        }
    }

    private CreateChatroom() {
    }
}
