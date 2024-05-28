package com.huawei.secure.android.common.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DocumentBuilderFactorySecurity {
    public static DocumentBuilderFactory getInstance() throws ParserConfigurationException, NullPointerException {
        DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
        newInstance.setFeature("http://xml.org/sax/features/namespaces", true);
        newInstance.setFeature("http://xml.org/sax/features/validation", false);
        newInstance.setExpandEntityReferences(false);
        return newInstance;
    }
}
