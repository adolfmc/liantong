package org.jsoup.nodes;

import java.util.Map;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Attribute implements Cloneable, Map.Entry<String, String> {
    private String key;
    private String value;

    public Attribute(String str, String str2) {
        Validate.notEmpty(str);
        Validate.notNull(str2);
        this.key = str.trim().toLowerCase();
        this.value = str2;
    }

    @Override // java.util.Map.Entry
    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        Validate.notEmpty(str);
        this.key = str.trim().toLowerCase();
    }

    @Override // java.util.Map.Entry
    public String getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public String setValue(String str) {
        Validate.notNull(str);
        String str2 = this.value;
        this.value = str;
        return str2;
    }

    public String html() {
        return this.key + "=\"" + Entities.escape(this.value, new Document("").outputSettings()) + "\"";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void html(StringBuilder sb, Document.OutputSettings outputSettings) {
        sb.append(this.key);
        sb.append("=\"");
        sb.append(Entities.escape(this.value, outputSettings));
        sb.append("\"");
    }

    public String toString() {
        return html();
    }

    public static Attribute createFromEncoded(String str, String str2) {
        return new Attribute(str, Entities.unescape(str2, true));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isDataAttribute() {
        return this.key.startsWith("data-") && this.key.length() > 5;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Attribute) {
            Attribute attribute = (Attribute) obj;
            String str = this.key;
            if (str == null ? attribute.key == null : str.equals(attribute.key)) {
                String str2 = this.value;
                return str2 == null ? attribute.value == null : str2.equals(attribute.value);
            }
            return false;
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        String str = this.key;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.value;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
