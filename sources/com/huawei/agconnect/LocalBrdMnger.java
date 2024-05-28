package com.huawei.agconnect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LocalBrdMnger {
    private static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final Object M_LOCK = new Object();
    private static LocalBrdMnger mInstance;
    private final Context mAppContext;
    private final Handler mHandler;
    private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers = new HashMap<>();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap<>();
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent intent, ArrayList<ReceiverRecord> arrayList) {
            this.intent = intent;
            this.receivers = arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ReceiverRecord {
        boolean broadcasting;

        /* renamed from: filter  reason: collision with root package name */
        final IntentFilter f27858filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.f27858filter = intentFilter;
            this.receiver = broadcastReceiver;
        }

        public String toString() {
            return "Receiver{" + this.receiver + " filter=" + this.f27858filter + "}";
        }
    }

    private LocalBrdMnger(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) { // from class: com.huawei.agconnect.LocalBrdMnger.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    LocalBrdMnger.this.executePendingBroadcasts();
                } else {
                    super.handleMessage(message);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executePendingBroadcasts() {
        BroadcastRecord[] broadcastRecordArr;
        while (true) {
            synchronized (this.mReceivers) {
                int size = this.mPendingBroadcasts.size();
                if (size <= 0) {
                    return;
                }
                broadcastRecordArr = new BroadcastRecord[size];
                this.mPendingBroadcasts.toArray(broadcastRecordArr);
                this.mPendingBroadcasts.clear();
            }
            for (BroadcastRecord broadcastRecord : broadcastRecordArr) {
                for (int i = 0; i < broadcastRecord.receivers.size(); i++) {
                    broadcastRecord.receivers.get(i).receiver.onReceive(this.mAppContext, broadcastRecord.intent);
                }
            }
        }
    }

    public static LocalBrdMnger getInstance(Context context) {
        LocalBrdMnger localBrdMnger;
        synchronized (M_LOCK) {
            if (mInstance == null) {
                mInstance = new LocalBrdMnger(context.getApplicationContext());
            }
            localBrdMnger = mInstance;
        }
        return localBrdMnger;
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList<IntentFilter> arrayList = this.mReceivers.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.mReceivers.put(broadcastReceiver, arrayList);
            }
            arrayList.add(intentFilter);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<ReceiverRecord> arrayList2 = this.mActions.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.mActions.put(action, arrayList2);
                }
                arrayList2.add(receiverRecord);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [int] */
    public boolean sendBroadcast(Intent intent) {
        boolean z;
        String str;
        String str2;
        ArrayList arrayList;
        int i;
        Uri uri;
        boolean z2;
        synchronized (this.mReceivers) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            ArrayList<ReceiverRecord> arrayList2 = this.mActions.get(intent.getAction());
            boolean z3 = false;
            if (arrayList2 != null) {
                ArrayList arrayList3 = null;
                int i2 = 0;
                while (i2 < arrayList2.size()) {
                    ReceiverRecord receiverRecord = arrayList2.get(i2);
                    if (receiverRecord.broadcasting) {
                        i = i2;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        uri = data;
                        arrayList = arrayList3;
                        z2 = z3;
                    } else {
                        str = action;
                        String str3 = resolveTypeIfNeeded;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList3;
                        Uri uri2 = data;
                        i = i2;
                        uri = data;
                        z2 = z3;
                        if (receiverRecord.f27858filter.match(action, str3, scheme, uri2, categories, "LocalBroadcastManager") >= 0) {
                            arrayList3 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList3.add(receiverRecord);
                            receiverRecord.broadcasting = true;
                            i2 = i + 1;
                            z3 = z2;
                            action = str;
                            resolveTypeIfNeeded = str2;
                            data = uri;
                        }
                    }
                    arrayList3 = arrayList;
                    i2 = i + 1;
                    z3 = z2;
                    action = str;
                    resolveTypeIfNeeded = str2;
                    data = uri;
                }
                ArrayList arrayList4 = arrayList3;
                z = z3;
                if (arrayList4 != null) {
                    for (int i3 = z; i3 < arrayList4.size(); i3++) {
                        ((ReceiverRecord) arrayList4.get(i3)).broadcasting = z;
                    }
                    this.mPendingBroadcasts.add(new BroadcastRecord(intent, arrayList4));
                    if (!this.mHandler.hasMessages(1)) {
                        this.mHandler.sendEmptyMessage(1);
                    }
                    return true;
                }
            } else {
                z = false;
            }
            return z;
        }
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        synchronized (this.mReceivers) {
            ArrayList<IntentFilter> remove = this.mReceivers.remove(broadcastReceiver);
            if (remove == null) {
                return;
            }
            for (int i = 0; i < remove.size(); i++) {
                IntentFilter intentFilter = remove.get(i);
                for (int i2 = 0; i2 < intentFilter.countActions(); i2++) {
                    String action = intentFilter.getAction(i2);
                    ArrayList<ReceiverRecord> arrayList = this.mActions.get(action);
                    if (arrayList != null) {
                        int i3 = 0;
                        while (i3 < arrayList.size()) {
                            if (arrayList.get(i3).receiver == broadcastReceiver) {
                                arrayList.remove(i3);
                                i3--;
                            }
                            i3++;
                        }
                        if (arrayList.size() <= 0) {
                            this.mActions.remove(action);
                        }
                    }
                }
            }
        }
    }
}
