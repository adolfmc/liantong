package com.sinovatech.unicom.separatemodule.gaodedache;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JSInterfaceConfigParser {
    private static String TAG = "JSInterfaceConfigParser";
    private static int index = 7000;
    private boolean insideFeature = false;
    private String actionName = "";
    private String pluginClass = "";
    private Map<String, YHXXJSPlugin> pluginMap = new HashMap();
    private Map<String, Integer> pluginRequestCodeMap = new HashMap();

    public Map<String, YHXXJSPlugin> getPluginMap() {
        return this.pluginMap;
    }

    public Map<String, Integer> getPluginRequestCodeMap() {
        return this.pluginRequestCodeMap;
    }

    public void parse(Context context) {
        try {
            int identifier = context.getResources().getIdentifier("yhxxjsinterface_config", "xml", context.getClass().getPackage().getName());
            if (identifier == 0 && (identifier = context.getResources().getIdentifier("yhxxjsinterface_config", "xml", context.getPackageName())) == 0) {
                throw new RuntimeException("res/xml/yhxxjsinterface_config.xml is missing!");
            }
            parse(context.getResources().getXml(identifier));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parse(XmlPullParser xmlPullParser) throws Exception {
        int i = -1;
        while (i != 1) {
            if (i == 2) {
                handleStartTag(xmlPullParser);
            } else if (i == 3) {
                handleEndTag(xmlPullParser);
            }
            i = xmlPullParser.next();
        }
    }

    private void handleStartTag(XmlPullParser xmlPullParser) throws Exception {
        String name = xmlPullParser.getName();
        if (name.equals("feature")) {
            this.insideFeature = true;
            this.actionName = xmlPullParser.getAttributeValue(null, "name");
        } else if (this.insideFeature && name.equals("param")) {
            this.pluginClass = xmlPullParser.getAttributeValue(null, "value");
        }
    }

    private void handleEndTag(XmlPullParser xmlPullParser) throws Exception {
        if (xmlPullParser.getName().equals("feature")) {
            Object newInstance = Class.forName(this.pluginClass).newInstance();
            if (newInstance != null && (newInstance instanceof YHXXJSPlugin)) {
                this.pluginMap.put(this.actionName, (YHXXJSPlugin) newInstance);
                Map<String, Integer> map = this.pluginRequestCodeMap;
                String str = this.actionName;
                int i = index;
                index = i + 1;
                map.put(str, Integer.valueOf(i));
            }
            this.actionName = "";
            this.pluginClass = "";
            this.insideFeature = false;
        }
    }
}
