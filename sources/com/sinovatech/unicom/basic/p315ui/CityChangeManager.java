package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.CityChangeManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CityChangeManager {
    static final int BUFFER_SIZE = 4096;
    private static final String CITY_JSON_FILENAME = "province_city_code.json";
    public static final String DEFAULT_SELECT_CITY = "北京";
    public static final String DEFAULT_SELECT_CITY_CODE = "110";
    public static final String DEFAULT_SELECT_CITY_PROVINCE_CODE = "011";
    public static final String PREFERENCE_SELECT_KEY = "city_select_name";
    public static final String PREFERENCE_location_KEY = "PREFERENCE_location_KEY";
    private Activity context;
    private String curSelProvince;
    private String defaultProvince;
    private Dialog dialog;
    private OnCityChangeManagerListener listener;
    private CheckedTextView city1;
    private CheckedTextView city2;
    private CheckedTextView city3;
    private CheckedTextView city4;
    private CheckedTextView city5;
    private CheckedTextView city6;
    private CheckedTextView city7;
    private CheckedTextView city8;
    private CheckedTextView city9;
    private CheckedTextView city10;
    private CheckedTextView city11;
    private CheckedTextView city12;
    private CheckedTextView city13;
    private CheckedTextView city14;
    private CheckedTextView city15;
    private CheckedTextView city16;
    private CheckedTextView city17;
    private CheckedTextView city18;
    private CheckedTextView city19;
    private CheckedTextView city20;
    private CheckedTextView city21;
    private CheckedTextView city22;
    private CheckedTextView city23;
    private CheckedTextView city24;
    private CheckedTextView city25;
    private CheckedTextView city26;
    private CheckedTextView city27;
    private CheckedTextView city28;
    private CheckedTextView city29;
    private CheckedTextView city30;
    private CheckedTextView city31;
    private CheckedTextView[] cities = {this.city1, this.city2, this.city3, this.city4, this.city5, this.city6, this.city7, this.city8, this.city9, this.city10, this.city11, this.city12, this.city13, this.city14, this.city15, this.city16, this.city17, this.city18, this.city19, this.city20, this.city21, this.city22, this.city23, this.city24, this.city25, this.city26, this.city27, this.city28, this.city29, this.city30, this.city31};
    private int[] ids = {2131296621, 2131296632, 2131296643, 2131296646, 2131296647, 2131296648, 2131296649, 2131296650, 2131296651, 2131296622, 2131296623, 2131296624, 2131296625, 2131296626, 2131296627, 2131296628, 2131296629, 2131296630, 2131296631, 2131296633, 2131296634, 2131296635, 2131296636, 2131296637, 2131296638, 2131296639, 2131296640, 2131296641, 2131296642, 2131296644, 2131296645};
    private int type = 1;
    View.OnClickListener cityOnClickListener = new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.CityChangeManager.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            String str = "";
            int i = 0;
            while (true) {
                try {
                    if (i >= CityChangeManager.this.cities.length) {
                        break;
                    }
                    CheckedTextView checkedTextView = CityChangeManager.this.cities[i];
                    if (view.getId() == checkedTextView.getId()) {
                        str = checkedTextView.getText().toString().replace("·", "");
                        break;
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                    UIUtils.toast("抱歉 切换地区错误！");
                }
            }
            if (CityChangeManager.this.type != 1) {
                if (!CityChangeManager.this.curSelProvince.equals(str)) {
                    CityChangeManager.this.queryProvinceCode(str);
                    CityChangeManager.this.curSelProvince = str;
                    CityChangeManager.this.cityChecked(str);
                }
            } else if (!CityChangeManager.this.preference.getString(CityChangeManager.PREFERENCE_SELECT_KEY).equals(str)) {
                CityChangeManager.this.queryProvinceCode(str);
                CityChangeManager.this.preference.putString(CityChangeManager.PREFERENCE_SELECT_KEY, str);
                CityChangeManager.this.cityChecked();
            }
            CityChangeManager.this.dialog.cancel();
            NBSActionInstrumentation.onClickEventExit();
        }
    };
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.CityChangeManager$OnCityChangeManagerListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnCityChangeManagerListener {
        void onCitySelect(String str, String str2, String str3);

        void onDismiss();
    }

    public static String PREFERENCE_CITY_SELECT_KEY_LOCATION() {
        return UserManager.getInstance().getCurrentPhoneNumber() + "PREFERENCE_CITY_SELECT_KEY_LOCATION";
    }

    public static String PREFERENCE_SELECTCITYTIME_KEY() {
        return UserManager.getInstance().getCurrentPhoneNumber() + "PREFERENCE_SELECTCITYTIME_KEY";
    }

    public CityChangeManager(Activity activity) {
        this.context = activity;
    }

    public void setListener(OnCityChangeManagerListener onCityChangeManagerListener) {
        this.listener = onCityChangeManagerListener;
    }

    public void showCity(int i) {
        this.type = i;
        if (TextUtils.isEmpty(this.defaultProvince)) {
            this.defaultProvince = this.preference.getString(PREFERENCE_SELECT_KEY);
        }
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            this.dialog = new Dialog(this.context, 2131952207);
            this.dialog.setContentView(2131493037);
            this.dialog.findViewById(2131296674).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.CityChangeManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    CityChangeManager.this.dialog.dismiss();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            int i2 = 0;
            while (true) {
                CheckedTextView[] checkedTextViewArr = this.cities;
                if (i2 >= checkedTextViewArr.length) {
                    break;
                }
                checkedTextViewArr[i2] = (CheckedTextView) this.dialog.findViewById(this.ids[i2]);
                this.cities[i2].setOnClickListener(this.cityOnClickListener);
                i2++;
            }
            if (Build.VERSION.SDK_INT >= 11) {
                this.dialog.findViewById(2131296812).setLayerType(1, null);
                this.dialog.findViewById(2131296813).setLayerType(1, null);
                this.dialog.findViewById(2131296814).setLayerType(1, null);
            }
            WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
            Window window = this.dialog.getWindow();
            switch (i) {
                case 1:
                    attributes.width = -1;
                    attributes.height = -2;
                    window.setGravity(48);
                    window.setAttributes(attributes);
                    if (Build.VERSION.SDK_INT >= 21) {
                        window.clearFlags(67108864);
                        window.getDecorView().setSystemUiVisibility(1280);
                        window.addFlags(Integer.MIN_VALUE);
                        window.addFlags(256);
                        window.setStatusBarColor(0);
                        break;
                    } else if (Build.VERSION.SDK_INT >= 19) {
                        window.addFlags(67108864);
                        break;
                    }
                    break;
                case 2:
                    attributes.width = -1;
                    attributes.height = -2;
                    window.setGravity(48);
                    window.setAttributes(attributes);
                    break;
            }
            window.setWindowAnimations(2131952206);
            cityChecked();
            this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.basic.ui.CityChangeManager.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    CityChangeManager.this.listener.onDismiss();
                }
            });
            this.dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryProvinceCode(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(getProvinceData(this.context)).getJSONObject(str);
        cityChanged(str, jSONObject.getString("province"), jSONObject.getString("code"));
    }

    private String getProvinceData(Activity activity) throws Exception {
        InputStream open = activity.getAssets().open(CITY_JSON_FILENAME);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = open.read(bArr, 0, 4096);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return new String(byteArrayOutputStream.toByteArray(), "utf-8");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cityChecked() {
        String string = this.preference.getString(PREFERENCE_SELECT_KEY);
        switch (this.type) {
            case 1:
                cityChecked(string);
                return;
            case 2:
                if (TextUtils.isEmpty(this.curSelProvince) || !this.defaultProvince.equals(string)) {
                    this.curSelProvince = string;
                    this.defaultProvince = string;
                    cityChecked(string);
                    return;
                }
                cityChecked(this.curSelProvince);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cityChecked(String str) {
        int i;
        int i2 = 0;
        while (true) {
            CheckedTextView[] checkedTextViewArr = this.cities;
            if (i2 >= checkedTextViewArr.length) {
                return;
            }
            CheckedTextView checkedTextView = checkedTextViewArr[i2];
            if (str.startsWith(checkedTextViewArr[i2].getText().toString().replace("·", ""))) {
                i = str.length() > 2 ? 2131231097 : 2131231098;
            } else {
                i = 2131231099;
            }
            checkedTextView.setBackgroundResource(i);
            i2++;
        }
    }

    public void cityChanged(String str, String str2, String str3) {
        this.listener.onCitySelect(str, str2, str3);
    }

    public String getCurSelProvince() {
        if (TextUtils.isEmpty(this.curSelProvince)) {
            return this.preference.getString(PREFERENCE_SELECT_KEY);
        }
        return this.curSelProvince;
    }

    public String getDefaultProvince() {
        return this.defaultProvince;
    }
}
