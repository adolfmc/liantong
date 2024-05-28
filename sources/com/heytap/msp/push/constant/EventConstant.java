package com.heytap.msp.push.constant;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EventConstant {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface EventId {
        public static final String EVENT_ID_PUSH_APP_NO_SHOW = "push_app_no_show";
        public static final String EVENT_ID_PUSH_CHANNEL_NONE_IMPORTANCE = "push_channel_none_importance";
        public static final String EVENT_ID_PUSH_CLICK = "push_click";
        public static final String EVENT_ID_PUSH_DELETE = "push_delete";
        public static final String EVENT_ID_PUSH_NO_SHOW = "push_no_show";
        public static final String EVENT_ID_PUSH_REGISTER = "push_register";
        public static final String EVENT_ID_PUSH_SHOW = "push_show";
        public static final String EVENT_ID_READ_MESSAGE = "push_read_message";
    }
}
