package com.gmrz.android.uaf.framework.service;

import android.text.TextUtils;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import java.util.ArrayList;
import java.util.List;
import org.fidoalliance.uaf.client.AdditionData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AdditionHelper {
    public static List<AdditionData> parseAdditionData(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("tag");
                String optString2 = jSONObject.optString("data");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2)) {
                    AdditionData additionData = new AdditionData();
                    additionData.setData(optString2);
                    additionData.setId(optString);
                    arrayList.add(additionData);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Extension convert2Extension(AdditionData additionData) {
        if (additionData != null) {
            String id = additionData.getId();
            char c = 65535;
            int hashCode = id.hashCode();
            if (hashCode != 516913366) {
                if (hashCode != 1079570286) {
                    if (hashCode == 1805886909 && id.equals("GESTURE_UVT")) {
                        c = 1;
                    }
                } else if (id.equals("MATCH_UI")) {
                    c = 0;
                }
            } else if (id.equals("USERNAME")) {
                c = 2;
            }
            switch (c) {
                case 0:
                    Extension extension = new Extension();
                    extension.data = additionData.getData();
                    extension.f10152id = "EXTENSION_ID_UI";
                    extension.fail_if_unknown = false;
                    return extension;
                case 1:
                    Extension extension2 = new Extension();
                    extension2.data = additionData.getData();
                    extension2.f10152id = "EXTENSION_ID_GESTURE";
                    extension2.fail_if_unknown = false;
                    return extension2;
                case 2:
                    Extension extension3 = new Extension();
                    extension3.data = additionData.getData();
                    extension3.f10152id = "EXTENSION_ID_USERNAME";
                    extension3.fail_if_unknown = false;
                    return extension3;
            }
        }
        return null;
    }

    public static List<Extension> convert2Extensions(List<AdditionData> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(convert2Extension(list.get(i)));
            }
            return arrayList;
        }
        return null;
    }
}
