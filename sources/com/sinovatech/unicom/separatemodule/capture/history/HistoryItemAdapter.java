package com.sinovatech.unicom.separatemodule.capture.history;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.google.zxing.Result;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HistoryItemAdapter extends BaseAdapter {
    private Activity context;
    private List<HistoryItem> list;

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    public List<HistoryItem> getList() {
        return this.list;
    }

    public void setList(List<HistoryItem> list) {
        this.list = list;
    }

    HistoryItemAdapter(Activity activity, List<HistoryItem> list) {
        this.context = activity;
        this.list = list;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        final String string;
        String string2;
        final String str;
        if (view == null) {
            ViewHolder viewHolder2 = new ViewHolder();
            View inflate = this.context.getLayoutInflater().inflate(2131493413, (ViewGroup) null);
            viewHolder2.history_img = (ImageView) inflate.findViewById(2131298464);
            viewHolder2.history_title = (TextView) inflate.findViewById(2131298466);
            viewHolder2.history_detail = (TextView) inflate.findViewById(2131298463);
            viewHolder2.history_time = (TextView) inflate.findViewById(2131298465);
            inflate.setTag(viewHolder2);
            viewHolder = viewHolder2;
            view = inflate;
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        HistoryItem historyItem = this.list.get(i);
        Result result = historyItem.getResult();
        if (result != null) {
            string = result.getText();
            str = historyItem.getDisplayAndDetails();
            if (URLUtil.isValidUrl(str)) {
                if (str.contains("10010.com")) {
                    string = "中国联通";
                    viewHolder.history_img.setImageResource(2131232137);
                } else {
                    string = "二维码内容";
                    viewHolder.history_img.setImageResource(2131232136);
                }
                view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.capture.history.HistoryItemAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        if (str.contains("10010nm.com/automaticSecurity/selfHelp-cowcatcher")) {
                            String valueByName = IntentManager.getValueByName(str, "random");
                            IntentManager.generateIntentAndGo(HistoryItemAdapter.this.context, "https://wx.10010nm.com/nmweb/nmwx-gyf/sk-nmltNewUI/band-3rd.html?random=" + valueByName, "内蒙古联通", false, "get");
                        } else {
                            IntentManager.generateIntentAndGo(HistoryItemAdapter.this.context, str, string, false, "get");
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
            string2 = getStrTime(result.getTimestamp());
        } else {
            Resources resources = this.context.getResources();
            string = resources.getString(2131886280);
            String string3 = resources.getString(2131886281);
            string2 = resources.getString(2131886282);
            str = string3;
        }
        viewHolder.history_title.setText(string);
        viewHolder.history_detail.setText(str);
        viewHolder.history_time.setText(string2);
        return view;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHolder {
        private TextView history_detail;
        private ImageView history_img;
        private TextView history_time;
        private TextView history_title;

        private ViewHolder() {
        }
    }

    public static String getStrTime(long j) {
        return new SimpleDateFormat("yy-MM-dd HH:mm").format(new Date(j));
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.list.size();
    }
}
