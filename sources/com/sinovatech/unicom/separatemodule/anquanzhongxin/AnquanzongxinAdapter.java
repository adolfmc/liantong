package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AnquanzongxinAdapter extends BaseAdapter {
    private Activity activityContext;
    private List<AnquanWhiteEntity> list = new ArrayList();
    private ToggleClick toggleClick;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ToggleClick {
        void click(AnquanWhiteEntity anquanWhiteEntity);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public AnquanzongxinAdapter(Activity activity) {
        this.activityContext = activity;
    }

    public void updateList(List<AnquanWhiteEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setToggleClick(ToggleClick toggleClick) {
        this.toggleClick = toggleClick;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.activityContext).inflate(2131492965, viewGroup, false);
        TextView textView = (TextView) inflate.findViewById(2131296371);
        ToggleButton toggleButton = (ToggleButton) inflate.findViewById(2131296364);
        View findViewById = inflate.findViewById(2131297621);
        if (i + 1 == this.list.size()) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
        }
        final AnquanWhiteEntity anquanWhiteEntity = this.list.get(i);
        textView.setText(anquanWhiteEntity.getName());
        toggleButton.setChecked(anquanWhiteEntity.isSelected());
        toggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.anquanzhongxin.-$$Lambda$AnquanzongxinAdapter$0J_nPujbNT5lLHhOQvp2EIBTWSA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AnquanzongxinAdapter.lambda$getView$0(AnquanzongxinAdapter.this, anquanWhiteEntity, view2);
            }
        });
        return inflate;
    }

    public static /* synthetic */ void lambda$getView$0(AnquanzongxinAdapter anquanzongxinAdapter, AnquanWhiteEntity anquanWhiteEntity, View view) {
        anquanWhiteEntity.setSelected(((ToggleButton) view).isChecked());
        anquanzongxinAdapter.toggleClick.click(anquanWhiteEntity);
    }
}
