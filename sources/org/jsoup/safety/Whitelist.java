package org.jsoup.safety;

import com.example.asus.detectionandalign.animation.C4280b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Whitelist {
    private Set<TagName> tagNames = new HashSet();
    private Map<TagName, Set<AttributeKey>> attributes = new HashMap();
    private Map<TagName, Map<AttributeKey, AttributeValue>> enforcedAttributes = new HashMap();
    private Map<TagName, Map<AttributeKey, Set<Protocol>>> protocols = new HashMap();
    private boolean preserveRelativeLinks = false;

    public static Whitelist none() {
        return new Whitelist();
    }

    public static Whitelist simpleText() {
        return new Whitelist().addTags(C4280b.f10047a, "em", "i", "strong", "u");
    }

    public static Whitelist basic() {
        return new Whitelist().addTags("a", C4280b.f10047a, "blockquote", "br", "cite", "code", "dd", "dl", "dt", "em", "i", "li", "ol", "p", "pre", "q", "small", "strike", "strong", "sub", "sup", "u", "ul").addAttributes("a", "href").addAttributes("blockquote", "cite").addAttributes("q", "cite").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("cite", "cite", "http", "https").addEnforcedAttribute("a", "rel", "nofollow");
    }

    public static Whitelist basicWithImages() {
        return basic().addTags("img").addAttributes("img", "align", "alt", "height", "src", "title", "width").addProtocols("img", "src", "http", "https");
    }

    public static Whitelist relaxed() {
        return new Whitelist().addTags("a", C4280b.f10047a, "blockquote", "br", "caption", "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small", "strike", "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u", "ul").addAttributes("a", "href", "title").addAttributes("blockquote", "cite").addAttributes("col", "span", "width").addAttributes("colgroup", "span", "width").addAttributes("img", "align", "alt", "height", "src", "title", "width").addAttributes("ol", "start", "type").addAttributes("q", "cite").addAttributes("table", "summary", "width").addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width").addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").addAttributes("ul", "type").addProtocols("a", "href", "ftp", "http", "https", "mailto").addProtocols("blockquote", "cite", "http", "https").addProtocols("img", "src", "http", "https").addProtocols("q", "cite", "http", "https");
    }

    public Whitelist addTags(String... strArr) {
        Validate.notNull(strArr);
        for (String str : strArr) {
            Validate.notEmpty(str);
            this.tagNames.add(TagName.valueOf(str));
        }
        return this;
    }

    public Whitelist addAttributes(String str, String... strArr) {
        Validate.notEmpty(str);
        Validate.notNull(strArr);
        Validate.isTrue(strArr.length > 0, "No attributes supplied.");
        TagName valueOf = TagName.valueOf(str);
        if (!this.tagNames.contains(valueOf)) {
            this.tagNames.add(valueOf);
        }
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.notEmpty(str2);
            hashSet.add(AttributeKey.valueOf(str2));
        }
        if (this.attributes.containsKey(valueOf)) {
            this.attributes.get(valueOf).addAll(hashSet);
        } else {
            this.attributes.put(valueOf, hashSet);
        }
        return this;
    }

    public Whitelist addEnforcedAttribute(String str, String str2, String str3) {
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notEmpty(str3);
        TagName valueOf = TagName.valueOf(str);
        if (!this.tagNames.contains(valueOf)) {
            this.tagNames.add(valueOf);
        }
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        AttributeValue valueOf3 = AttributeValue.valueOf(str3);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            this.enforcedAttributes.get(valueOf).put(valueOf2, valueOf3);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(valueOf2, valueOf3);
            this.enforcedAttributes.put(valueOf, hashMap);
        }
        return this;
    }

    public Whitelist preserveRelativeLinks(boolean z) {
        this.preserveRelativeLinks = z;
        return this;
    }

    public Whitelist addProtocols(String str, String str2, String... strArr) {
        Map<AttributeKey, Set<Protocol>> map;
        Set<Protocol> set;
        Validate.notEmpty(str);
        Validate.notEmpty(str2);
        Validate.notNull(strArr);
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(str2);
        if (this.protocols.containsKey(valueOf)) {
            map = this.protocols.get(valueOf);
        } else {
            HashMap hashMap = new HashMap();
            this.protocols.put(valueOf, hashMap);
            map = hashMap;
        }
        if (map.containsKey(valueOf2)) {
            set = map.get(valueOf2);
        } else {
            HashSet hashSet = new HashSet();
            map.put(valueOf2, hashSet);
            set = hashSet;
        }
        for (String str3 : strArr) {
            Validate.notEmpty(str3);
            set.add(Protocol.valueOf(str3));
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isSafeTag(String str) {
        return this.tagNames.contains(TagName.valueOf(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSafeAttribute(String str, Element element, Attribute attribute) {
        TagName valueOf = TagName.valueOf(str);
        AttributeKey valueOf2 = AttributeKey.valueOf(attribute.getKey());
        if (!this.attributes.containsKey(valueOf) || !this.attributes.get(valueOf).contains(valueOf2)) {
            return !str.equals(":all") && isSafeAttribute(":all", element, attribute);
        } else if (this.protocols.containsKey(valueOf)) {
            Map<AttributeKey, Set<Protocol>> map = this.protocols.get(valueOf);
            return !map.containsKey(valueOf2) || testValidProtocol(element, attribute, map.get(valueOf2));
        } else {
            return true;
        }
    }

    private boolean testValidProtocol(Element element, Attribute attribute, Set<Protocol> set) {
        String absUrl = element.absUrl(attribute.getKey());
        if (absUrl.length() == 0) {
            absUrl = attribute.getValue();
        }
        if (!this.preserveRelativeLinks) {
            attribute.setValue(absUrl);
        }
        Iterator<Protocol> it = set.iterator();
        while (it.hasNext()) {
            if (absUrl.toLowerCase().startsWith(it.next().toString() + ":")) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Attributes getEnforcedAttributes(String str) {
        Attributes attributes = new Attributes();
        TagName valueOf = TagName.valueOf(str);
        if (this.enforcedAttributes.containsKey(valueOf)) {
            for (Map.Entry<AttributeKey, AttributeValue> entry : this.enforcedAttributes.get(valueOf).entrySet()) {
                attributes.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return attributes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName valueOf(String str) {
            return new TagName(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey valueOf(String str) {
            return new AttributeKey(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue valueOf(String str) {
            return new AttributeValue(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol valueOf(String str) {
            return new Protocol(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class TypedValue {
        private String value;

        TypedValue(String str) {
            Validate.notNull(str);
            this.value = str;
        }

        public int hashCode() {
            String str = this.value;
            return 31 + (str == null ? 0 : str.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                TypedValue typedValue = (TypedValue) obj;
                String str = this.value;
                if (str == null) {
                    if (typedValue.value != null) {
                        return false;
                    }
                } else if (!str.equals(typedValue.value)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public String toString() {
            return this.value;
        }
    }
}
