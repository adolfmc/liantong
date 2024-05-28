package com.bytedance.pangle;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Keep;
import android.util.Log;
import com.bytedance.pangle.receiver.PluginBroadcastReceiver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Keep
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final Context mAppContext;
    private final Handler mHandler;
    private final HashMap<PluginBroadcastReceiver, ArrayList<C3766b>> mReceivers = new HashMap<>();
    private final HashMap<String, ArrayList<C3766b>> mActions = new HashMap<>();
    private final ArrayList<C3765a> mPendingBroadcasts = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.LocalBroadcastManager$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class C3766b {

        /* renamed from: a */
        final IntentFilter f9010a;

        /* renamed from: b */
        final PluginBroadcastReceiver f9011b;

        /* renamed from: c */
        boolean f9012c;

        /* renamed from: d */
        boolean f9013d;

        C3766b(IntentFilter intentFilter, PluginBroadcastReceiver pluginBroadcastReceiver) {
            this.f9010a = intentFilter;
            this.f9011b = pluginBroadcastReceiver;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.f9011b);
            sb.append(" filter=");
            sb.append(this.f9010a);
            if (this.f9013d) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.LocalBroadcastManager$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C3765a {

        /* renamed from: a */
        final Intent f9008a;

        /* renamed from: b */
        final ArrayList<C3766b> f9009b;

        C3765a(Intent intent, ArrayList<C3766b> arrayList) {
            this.f9008a = intent;
            this.f9009b = arrayList;
        }
    }

    public static LocalBroadcastManager getInstance(Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) { // from class: com.bytedance.pangle.LocalBroadcastManager.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                if (message.what == 1) {
                    LocalBroadcastManager.this.executePendingBroadcasts();
                } else {
                    super.handleMessage(message);
                }
            }
        };
    }

    public final void registerReceiver(PluginBroadcastReceiver pluginBroadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            C3766b c3766b = new C3766b(intentFilter, pluginBroadcastReceiver);
            ArrayList<C3766b> arrayList = this.mReceivers.get(pluginBroadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList<>(1);
                this.mReceivers.put(pluginBroadcastReceiver, arrayList);
            }
            arrayList.add(c3766b);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList<C3766b> arrayList2 = this.mActions.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>(1);
                    this.mActions.put(action, arrayList2);
                }
                arrayList2.add(c3766b);
            }
        }
    }

    public final void unregisterReceiver(PluginBroadcastReceiver pluginBroadcastReceiver) {
        synchronized (this.mReceivers) {
            ArrayList<C3766b> remove = this.mReceivers.remove(pluginBroadcastReceiver);
            if (remove == null) {
                return;
            }
            for (int size = remove.size() - 1; size >= 0; size--) {
                C3766b c3766b = remove.get(size);
                c3766b.f9013d = true;
                for (int i = 0; i < c3766b.f9010a.countActions(); i++) {
                    String action = c3766b.f9010a.getAction(i);
                    ArrayList<C3766b> arrayList = this.mActions.get(action);
                    if (arrayList != null) {
                        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                            C3766b c3766b2 = arrayList.get(size2);
                            if (c3766b2.f9011b == pluginBroadcastReceiver) {
                                c3766b2.f9013d = true;
                                arrayList.remove(size2);
                            }
                        }
                        if (arrayList.size() <= 0) {
                            this.mActions.remove(action);
                        }
                    }
                }
            }
        }
    }

    public final boolean sendBroadcast(Intent intent) {
        String str;
        ArrayList arrayList;
        int i;
        ArrayList<C3766b> arrayList2;
        String str2;
        String str3;
        synchronized (this.mReceivers) {
            String action = intent.getAction();
            String resolveTypeIfNeeded = intent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
            Uri data = intent.getData();
            String scheme = intent.getScheme();
            Set<String> categories = intent.getCategories();
            boolean z = (intent.getFlags() & 8) != 0;
            if (z) {
                Log.v(TAG, "Resolving type " + resolveTypeIfNeeded + " scheme " + scheme + " of intent " + intent);
            }
            ArrayList<C3766b> arrayList3 = this.mActions.get(intent.getAction());
            if (arrayList3 != null) {
                if (z) {
                    Log.v(TAG, "Action list: ".concat(String.valueOf(arrayList3)));
                }
                ArrayList arrayList4 = null;
                int i2 = 0;
                while (i2 < arrayList3.size()) {
                    C3766b c3766b = arrayList3.get(i2);
                    if (z) {
                        Log.v(TAG, "Matching against filter " + c3766b.f9010a);
                    }
                    if (!c3766b.f9012c) {
                        str = action;
                        arrayList = arrayList4;
                        i = i2;
                        arrayList2 = arrayList3;
                        str2 = resolveTypeIfNeeded;
                        int match = c3766b.f9010a.match(action, resolveTypeIfNeeded, scheme, data, categories, TAG);
                        if (match >= 0) {
                            if (z) {
                                Log.v(TAG, "  Filter matched!  match=0x" + Integer.toHexString(match));
                            }
                            arrayList4 = arrayList == null ? new ArrayList() : arrayList;
                            arrayList4.add(c3766b);
                            c3766b.f9012c = true;
                            i2 = i + 1;
                            action = str;
                            arrayList3 = arrayList2;
                            resolveTypeIfNeeded = str2;
                        } else if (z) {
                            switch (match) {
                                case -4:
                                    str3 = "category";
                                    break;
                                case -3:
                                    str3 = "action";
                                    break;
                                case -2:
                                    str3 = "data";
                                    break;
                                case -1:
                                    str3 = "type";
                                    break;
                                default:
                                    str3 = "unknown reason";
                                    break;
                            }
                            Log.v(TAG, "  Filter did not match: ".concat(String.valueOf(str3)));
                        }
                    } else if (z) {
                        Log.v(TAG, "  Filter's target already added");
                        i = i2;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList4;
                    } else {
                        i = i2;
                        arrayList2 = arrayList3;
                        str = action;
                        str2 = resolveTypeIfNeeded;
                        arrayList = arrayList4;
                    }
                    arrayList4 = arrayList;
                    i2 = i + 1;
                    action = str;
                    arrayList3 = arrayList2;
                    resolveTypeIfNeeded = str2;
                }
                ArrayList arrayList5 = arrayList4;
                if (arrayList5 != null) {
                    for (int i3 = 0; i3 < arrayList5.size(); i3++) {
                        ((C3766b) arrayList5.get(i3)).f9012c = false;
                    }
                    this.mPendingBroadcasts.add(new C3765a(intent, arrayList5));
                    if (!this.mHandler.hasMessages(1)) {
                        this.mHandler.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public final void sendBroadcastSync(Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    final void executePendingBroadcasts() {
        C3765a[] c3765aArr;
        while (true) {
            synchronized (this.mReceivers) {
                int size = this.mPendingBroadcasts.size();
                if (size <= 0) {
                    return;
                }
                c3765aArr = new C3765a[size];
                this.mPendingBroadcasts.toArray(c3765aArr);
                this.mPendingBroadcasts.clear();
            }
            for (C3765a c3765a : c3765aArr) {
                int size2 = c3765a.f9009b.size();
                for (int i = 0; i < size2; i++) {
                    C3766b c3766b = c3765a.f9009b.get(i);
                    if (!c3766b.f9013d) {
                        c3766b.f9011b.onReceive(this.mAppContext, c3765a.f9008a);
                    }
                }
            }
        }
    }
}
