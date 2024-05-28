package org.jsoup.parser;

import com.example.asus.detectionandalign.DetectionAuthentic;
import com.example.asus.detectionandalign.animation.C4280b;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.helper.Validate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tag {
    private String tagName;
    private static final Map<String, Tag> tags = new HashMap();
    private static final String[] blockTags = {"html", "head", "body", "frameset", "script", "noscript", "style", "meta", "link", "title", DetectionAuthentic.FRAME, "noframes", "section", "nav", "aside", "hgroup", "header", "footer", "p", "h1", "h2", "h3", "h4", "h5", "h6", "ul", "ol", "pre", "div", "blockquote", "hr", "address", "figure", "figcaption", "form", "fieldset", "ins", "del", "s", "dl", "dt", "dd", "li", "table", "caption", "thead", "tfoot", "tbody", "colgroup", "col", "tr", "th", "td", "video", "audio", "canvas", "details", "menu", "plaintext"};
    private static final String[] inlineTags = {"object", "base", "font", "tt", "i", C4280b.f10047a, "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", "img", "br", "wbr", "map", "q", "sub", "sup", "bdo", "iframe", "embed", "span", "input", "select", "textarea", "label", "button", "optgroup", "option", "legend", "datalist", "keygen", "output", "progress", "meter", "area", "param", "source", "track", "summary", "command", "device"};
    private static final String[] emptyTags = {"meta", "link", "base", DetectionAuthentic.FRAME, "img", "br", "wbr", "embed", "hr", "input", "keygen", "col", "command", "device"};
    private static final String[] formatAsInlineTags = {"title", "a", "p", "h1", "h2", "h3", "h4", "h5", "h6", "pre", "address", "li", "th", "td", "script", "style", "ins", "del", "s"};
    private static final String[] preserveWhitespaceTags = {"pre", "plaintext", "title", "textarea"};
    private boolean isBlock = true;
    private boolean formatAsBlock = true;
    private boolean canContainBlock = true;
    private boolean canContainInline = true;
    private boolean empty = false;
    private boolean selfClosing = false;
    private boolean preserveWhitespace = false;

    static {
        for (String str : blockTags) {
            register(new Tag(str));
        }
        for (String str2 : inlineTags) {
            Tag tag = new Tag(str2);
            tag.isBlock = false;
            tag.canContainBlock = false;
            tag.formatAsBlock = false;
            register(tag);
        }
        for (String str3 : emptyTags) {
            Tag tag2 = tags.get(str3);
            Validate.notNull(tag2);
            tag2.canContainBlock = false;
            tag2.canContainInline = false;
            tag2.empty = true;
        }
        for (String str4 : formatAsInlineTags) {
            Tag tag3 = tags.get(str4);
            Validate.notNull(tag3);
            tag3.formatAsBlock = false;
        }
        for (String str5 : preserveWhitespaceTags) {
            Tag tag4 = tags.get(str5);
            Validate.notNull(tag4);
            tag4.preserveWhitespace = true;
        }
    }

    private Tag(String str) {
        this.tagName = str.toLowerCase();
    }

    public String getName() {
        return this.tagName;
    }

    public static Tag valueOf(String str) {
        Validate.notNull(str);
        Tag tag = tags.get(str);
        if (tag == null) {
            String lowerCase = str.trim().toLowerCase();
            Validate.notEmpty(lowerCase);
            Tag tag2 = tags.get(lowerCase);
            if (tag2 == null) {
                Tag tag3 = new Tag(lowerCase);
                tag3.isBlock = false;
                tag3.canContainBlock = true;
                return tag3;
            }
            return tag2;
        }
        return tag;
    }

    public boolean isBlock() {
        return this.isBlock;
    }

    public boolean formatAsBlock() {
        return this.formatAsBlock;
    }

    public boolean canContainBlock() {
        return this.canContainBlock;
    }

    public boolean isInline() {
        return !this.isBlock;
    }

    public boolean isData() {
        return (this.canContainInline || isEmpty()) ? false : true;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public boolean isSelfClosing() {
        return this.empty || this.selfClosing;
    }

    public boolean isKnownTag() {
        return tags.containsKey(this.tagName);
    }

    public static boolean isKnownTag(String str) {
        return tags.containsKey(str);
    }

    public boolean preserveWhitespace() {
        return this.preserveWhitespace;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Tag setSelfClosing() {
        this.selfClosing = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Tag) {
            Tag tag = (Tag) obj;
            return this.canContainBlock == tag.canContainBlock && this.canContainInline == tag.canContainInline && this.empty == tag.empty && this.formatAsBlock == tag.formatAsBlock && this.isBlock == tag.isBlock && this.preserveWhitespace == tag.preserveWhitespace && this.selfClosing == tag.selfClosing && this.tagName.equals(tag.tagName);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.tagName.hashCode() * 31) + (this.isBlock ? 1 : 0)) * 31) + (this.formatAsBlock ? 1 : 0)) * 31) + (this.canContainBlock ? 1 : 0)) * 31) + (this.canContainInline ? 1 : 0)) * 31) + (this.empty ? 1 : 0)) * 31) + (this.selfClosing ? 1 : 0)) * 31) + (this.preserveWhitespace ? 1 : 0);
    }

    public String toString() {
        return this.tagName;
    }

    private static void register(Tag tag) {
        tags.put(tag.tagName, tag);
    }
}
