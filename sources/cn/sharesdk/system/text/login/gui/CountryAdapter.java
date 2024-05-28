package cn.sharesdk.system.text.login.gui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.SizeHelper;
import cn.sharesdk.system.text.login.gui.GroupListView;
import cn.sharesdk.system.text.login.utils.SearchEngine;
import com.mob.MobSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.gui.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CountryAdapter extends GroupListView.AbstractC1834a {

    /* renamed from: b */
    private HashMap<Character, ArrayList<String[]>> f3123b;

    /* renamed from: c */
    private ArrayList<String> f3124c;

    /* renamed from: d */
    private ArrayList<ArrayList<String[]>> f3125d;

    /* renamed from: e */
    private SearchEngine f3126e;

    /* renamed from: f */
    private String f3127f;

    /* renamed from: g */
    private HashMap<Character, ArrayList<String[]>> f3128g;

    public CountryAdapter(GroupListView groupListView) {
        super(groupListView);
        this.f3123b = m21468b();
        m21465d();
        m21469a((String) null);
    }

    /* renamed from: d */
    private void m21465d() {
        this.f3126e = new SearchEngine();
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<Character, ArrayList<String[]>> entry : this.f3123b.entrySet()) {
            Iterator<String[]> it = entry.getValue().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next()[0]);
            }
        }
        this.f3126e.m21452a(arrayList);
    }

    /* renamed from: a */
    public void m21469a(String str) {
        boolean z;
        ArrayList<String> m21453a = this.f3126e.m21453a(str);
        if (m21453a == null || m21453a.size() <= 0) {
            m21453a = new ArrayList<>();
            z = true;
        } else {
            z = false;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> it = m21453a.iterator();
        while (it.hasNext()) {
            String next = it.next();
            hashMap.put(next, next);
        }
        this.f3124c = new ArrayList<>();
        this.f3125d = new ArrayList<>();
        for (Map.Entry<Character, ArrayList<String[]>> entry : this.f3123b.entrySet()) {
            ArrayList<String[]> arrayList = new ArrayList<>();
            Iterator<String[]> it2 = entry.getValue().iterator();
            while (it2.hasNext()) {
                String[] next2 = it2.next();
                if (z || hashMap.containsKey(next2[0])) {
                    arrayList.add(next2);
                }
            }
            if (arrayList.size() > 0) {
                this.f3124c.add(String.valueOf(entry.getKey()));
                this.f3125d.add(arrayList);
            }
        }
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public int mo21475a() {
        ArrayList<String> arrayList = this.f3124c;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public int mo21474a(int i) {
        ArrayList<String[]> arrayList;
        ArrayList<ArrayList<String[]>> arrayList2 = this.f3125d;
        if (arrayList2 == null || (arrayList = arrayList2.get(i)) == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: b */
    public String mo21467b(int i) {
        if (this.f3124c.size() != 0) {
            return this.f3124c.get(i).toString();
        }
        return null;
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public String[] mo21466b(int i, int i2) {
        if (this.f3125d.size() != 0) {
            try {
                return this.f3125d.get(i).get(i2);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    public HashMap<Character, ArrayList<String[]>> m21468b() {
        ArrayList arrayList;
        String appLanguage = DeviceHelper.getInstance(MobSDK.getContext()).getAppLanguage();
        SSDKLog.m21740b().m21744a("appLanguage:" + appLanguage, new Object[0]);
        if (appLanguage != null && !appLanguage.equals(this.f3127f)) {
            this.f3127f = appLanguage;
            this.f3128g = null;
        }
        HashMap<Character, ArrayList<String[]>> hashMap = this.f3128g;
        if (hashMap != null && hashMap.size() > 0) {
            return this.f3128g;
        }
        LinkedHashMap linkedHashMap = null;
        for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
            int stringArrayRes = ResHelper.getStringArrayRes(MobSDK.getContext(), "smssdk_country_group_" + Character.toLowerCase(c));
            if (stringArrayRes > 0) {
                String[] stringArray = MobSDK.getContext().getResources().getStringArray(stringArrayRes);
                if (stringArray != null) {
                    arrayList = null;
                    for (String str : stringArray) {
                        String[] split = str.split(",");
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(split);
                    }
                } else {
                    arrayList = null;
                }
                if (arrayList != null) {
                    if (linkedHashMap == null) {
                        linkedHashMap = new LinkedHashMap();
                    }
                    linkedHashMap.put(Character.valueOf(c), arrayList);
                }
            }
        }
        this.f3128g = linkedHashMap;
        return this.f3128g;
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public View mo21471a(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout = view;
        if (view == null) {
            LinearLayout linearLayout2 = new LinearLayout(viewGroup.getContext());
            linearLayout2.setOrientation(1);
            linearLayout2.setBackgroundColor(-1);
            SizeHelper.m21680a(viewGroup.getContext());
            TextView textView = new TextView(viewGroup.getContext());
            textView.setTextSize(0, SizeHelper.m21679b(16));
            textView.setTextColor(-6710887);
            int m21679b = SizeHelper.m21679b(14);
            textView.setPadding(0, m21679b, 0, m21679b);
            textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            linearLayout2.addView(textView);
            View view2 = new View(viewGroup.getContext());
            view2.setBackgroundColor(-1842205);
            linearLayout2.addView(view2, new LinearLayout.LayoutParams(-1, 1));
            linearLayout = linearLayout2;
        }
        ((TextView) ((LinearLayout) linearLayout).getChildAt(0)).setText(mo21467b(i));
        return linearLayout;
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public void mo21470a(View view, String str) {
        ((TextView) ((LinearLayout) view).getChildAt(0)).setText(str);
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.AbstractC1834a
    /* renamed from: a */
    public View mo21472a(int i, int i2, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout = view;
        if (view == null) {
            LinearLayout linearLayout2 = new LinearLayout(viewGroup.getContext());
            linearLayout2.setBackgroundColor(-1);
            SizeHelper.m21680a(viewGroup.getContext());
            TextView textView = new TextView(viewGroup.getContext());
            textView.setTextColor(-13290187);
            textView.setTextSize(0, SizeHelper.m21679b(24));
            int m21679b = SizeHelper.m21679b(30);
            textView.setPadding(0, m21679b, 0, m21679b);
            linearLayout2.addView(textView, new LinearLayout.LayoutParams(-1, -2));
            linearLayout = linearLayout2;
        }
        String[] mo21466b = mo21466b(i, i2);
        if (mo21466b != null) {
            ((TextView) ((LinearLayout) linearLayout).getChildAt(0)).setText(mo21466b[0]);
        }
        return linearLayout;
    }
}
