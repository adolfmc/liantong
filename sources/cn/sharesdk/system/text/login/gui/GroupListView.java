package cn.sharesdk.system.text.login.gui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GroupListView extends RelativeLayout {

    /* renamed from: a */
    private ListView f3100a;

    /* renamed from: b */
    private C1835b f3101b;

    /* renamed from: c */
    private AbstractC1834a f3102c;

    /* renamed from: d */
    private View f3103d;

    /* renamed from: e */
    private int f3104e;

    /* renamed from: f */
    private int f3105f;

    /* renamed from: g */
    private AbsListView.OnScrollListener f3106g;

    /* renamed from: h */
    private OnItemClickListener f3107h;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick(GroupListView groupListView, View view, int i, int i2);
    }

    public GroupListView(Context context) {
        super(context);
        m21495a(context);
    }

    public GroupListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m21495a(context);
    }

    public GroupListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m21495a(context);
    }

    /* renamed from: a */
    private void m21495a(Context context) {
        this.f3100a = new ListView(context);
        this.f3100a.setCacheColorHint(0);
        this.f3100a.setSelector(new ColorDrawable());
        this.f3100a.setVerticalScrollBarEnabled(false);
        this.f3100a.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: cn.sharesdk.system.text.login.gui.GroupListView.1
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (GroupListView.this.f3106g != null) {
                    GroupListView.this.f3106g.onScrollStateChanged(absListView, i);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                GroupListView.this.f3104e = i;
                if (GroupListView.this.f3103d != null) {
                    GroupListView.this.m21486c();
                }
                if (GroupListView.this.f3106g != null) {
                    GroupListView.this.f3106g.onScroll(absListView, i, i2, i3);
                }
            }
        });
        this.f3100a.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cn.sharesdk.system.text.login.gui.GroupListView.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                NBSActionInstrumentation.onItemClickEnter(view, i, this);
                Tracker.onItemClick(adapterView, view, i, j);
                if (GroupListView.this.f3107h != null) {
                    int m21479a = GroupListView.this.f3101b.m21479a(i);
                    GroupListView.this.f3107h.onItemClick(GroupListView.this, view, m21479a, (i - ((Integer) GroupListView.this.f3101b.f3113c.get(m21479a)).intValue()) - 1);
                }
                NBSActionInstrumentation.onItemClickExit();
            }
        });
        this.f3100a.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(this.f3100a);
    }

    /* renamed from: a */
    public void m21497a(int i) {
        this.f3100a.setDividerHeight(i);
    }

    /* renamed from: a */
    public void m21494a(Drawable drawable) {
        this.f3100a.setDivider(drawable);
    }

    /* renamed from: a */
    public void m21492a(AbstractC1834a abstractC1834a) {
        this.f3102c = abstractC1834a;
        this.f3101b = new C1835b(abstractC1834a);
        this.f3100a.setAdapter((ListAdapter) this.f3101b);
        m21489b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21498a() {
        this.f3101b.notifyDataSetChanged();
        m21489b();
    }

    /* renamed from: b */
    private void m21489b() {
        View view = this.f3103d;
        if (view != null) {
            removeView(view);
        }
        if (this.f3101b.getCount() == 0) {
            return;
        }
        this.f3103d = this.f3101b.getView(((Integer) this.f3101b.f3113c.get(this.f3101b.m21479a(this.f3104e))).intValue(), null, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(9);
        layoutParams.addRule(10);
        addView(this.f3103d, layoutParams);
        this.f3103d.measure(0, 0);
        this.f3105f = this.f3103d.getMeasuredHeight();
        m21486c();
    }

    /* renamed from: b */
    public void m21488b(int i) {
        m21496a(i, -1);
    }

    /* renamed from: a */
    public void m21496a(int i, int i2) {
        this.f3100a.setSelection(((Integer) this.f3101b.f3113c.get(i)).intValue() + i2 + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m21486c() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f3103d.getLayoutParams();
        if (this.f3101b.m21476c(this.f3104e)) {
            this.f3102c.mo21470a(this.f3103d, this.f3102c.mo21467b(this.f3101b.m21479a(this.f3104e)));
            int top = this.f3100a.getChildAt(1).getTop();
            int i = this.f3105f;
            if (top < i) {
                layoutParams.setMargins(0, top - i, 0, 0);
                this.f3103d.setLayoutParams(layoutParams);
                return;
            }
        }
        layoutParams.topMargin = 0;
        this.f3103d.setLayoutParams(layoutParams);
        if (this.f3101b.m21477b(this.f3104e)) {
            this.f3102c.mo21470a(this.f3103d, this.f3102c.mo21467b(this.f3101b.m21479a(this.f3104e)));
        }
    }

    /* renamed from: a */
    public void m21493a(OnItemClickListener onItemClickListener) {
        this.f3107h = onItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.sharesdk.system.text.login.gui.GroupListView$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1835b extends BaseAdapter {

        /* renamed from: a */
        private AbstractC1834a f3111a;

        /* renamed from: b */
        private ArrayList<Object> f3112b = new ArrayList<>();

        /* renamed from: c */
        private ArrayList<Integer> f3113c = new ArrayList<>();

        /* renamed from: d */
        private ArrayList<Integer> f3114d = new ArrayList<>();

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 2;
        }

        public C1835b(AbstractC1834a abstractC1834a) {
            this.f3111a = abstractC1834a;
            m21480a();
        }

        /* renamed from: a */
        private void m21480a() {
            this.f3112b.clear();
            this.f3113c.clear();
            this.f3114d.clear();
            int mo21475a = this.f3111a.mo21475a();
            for (int i = 0; i < mo21475a; i++) {
                int mo21474a = this.f3111a.mo21474a(i);
                if (mo21474a > 0) {
                    this.f3113c.add(Integer.valueOf(this.f3112b.size()));
                    this.f3112b.add(this.f3111a.mo21467b(i));
                    for (int i2 = 0; i2 < mo21474a; i2++) {
                        this.f3112b.add(this.f3111a.mo21466b(i, i2));
                    }
                    this.f3114d.add(Integer.valueOf(this.f3112b.size() - 1));
                }
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.f3112b.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return this.f3112b.get(i);
        }

        /* renamed from: a */
        public int m21479a(int i) {
            int size = this.f3113c.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i < this.f3113c.get(i2).intValue()) {
                    return i2 - 1;
                }
            }
            return size - 1;
        }

        /* renamed from: b */
        public boolean m21477b(int i) {
            int size = this.f3113c.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f3113c.get(i2).intValue() == i) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return !m21477b(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int m21479a = m21479a(i);
            if (!m21477b(i)) {
                return this.f3111a.mo21472a(m21479a, (i - this.f3113c.get(m21479a).intValue()) - 1, view, viewGroup);
            } else if (view != null) {
                return this.f3111a.mo21471a(m21479a, view, viewGroup);
            } else {
                return this.f3111a.mo21471a(m21479a, null, viewGroup);
            }
        }

        @Override // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            m21480a();
            super.notifyDataSetChanged();
        }

        /* renamed from: c */
        public boolean m21476c(int i) {
            int size = this.f3114d.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.f3114d.get(i2).intValue() == i) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.sharesdk.system.text.login.gui.GroupListView$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractC1834a {

        /* renamed from: a */
        protected final GroupListView f3110a;

        /* renamed from: a */
        public abstract int mo21475a();

        /* renamed from: a */
        public abstract int mo21474a(int i);

        /* renamed from: a */
        public abstract View mo21472a(int i, int i2, View view, ViewGroup viewGroup);

        /* renamed from: a */
        public abstract View mo21471a(int i, View view, ViewGroup viewGroup);

        /* renamed from: a */
        public abstract void mo21470a(View view, String str);

        /* renamed from: b */
        public abstract Object mo21466b(int i, int i2);

        /* renamed from: b */
        public abstract String mo21467b(int i);

        public AbstractC1834a(GroupListView groupListView) {
            this.f3110a = groupListView;
        }

        /* renamed from: c */
        public void m21481c() {
            this.f3110a.m21498a();
        }
    }
}
