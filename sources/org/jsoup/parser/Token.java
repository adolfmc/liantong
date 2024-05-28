package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Token {
    TokenType type;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    private Token() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String tokenType() {
        return getClass().getSimpleName();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Doctype extends Token {
        boolean forceQuirks;
        final StringBuilder name;
        final StringBuilder publicIdentifier;
        final StringBuilder systemIdentifier;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Doctype() {
            super();
            this.name = new StringBuilder();
            this.publicIdentifier = new StringBuilder();
            this.systemIdentifier = new StringBuilder();
            this.forceQuirks = false;
            this.type = TokenType.Doctype;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getName() {
            return this.name.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getPublicIdentifier() {
            return this.publicIdentifier.toString();
        }

        public String getSystemIdentifier() {
            return this.systemIdentifier.toString();
        }

        public boolean isForceQuirks() {
            return this.forceQuirks;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static abstract class Tag extends Token {
        Attributes attributes;
        private String pendingAttributeName;
        private StringBuilder pendingAttributeValue;
        boolean selfClosing;
        protected String tagName;

        Tag() {
            super();
            this.selfClosing = false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void newAttribute() {
            Attribute attribute;
            if (this.attributes == null) {
                this.attributes = new Attributes();
            }
            String str = this.pendingAttributeName;
            if (str != null) {
                StringBuilder sb = this.pendingAttributeValue;
                if (sb == null) {
                    attribute = new Attribute(str, "");
                } else {
                    attribute = new Attribute(str, sb.toString());
                }
                this.attributes.put(attribute);
            }
            this.pendingAttributeName = null;
            StringBuilder sb2 = this.pendingAttributeValue;
            if (sb2 != null) {
                sb2.delete(0, sb2.length());
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void finaliseTag() {
            if (this.pendingAttributeName != null) {
                newAttribute();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String name() {
            Validate.isFalse(this.tagName.length() == 0);
            return this.tagName;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Tag name(String str) {
            this.tagName = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isSelfClosing() {
            return this.selfClosing;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Attributes getAttributes() {
            return this.attributes;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendTagName(String str) {
            String str2 = this.tagName;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.tagName = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendTagName(char c) {
            appendTagName(String.valueOf(c));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendAttributeName(String str) {
            String str2 = this.pendingAttributeName;
            if (str2 != null) {
                str = str2.concat(str);
            }
            this.pendingAttributeName = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendAttributeName(char c) {
            appendAttributeName(String.valueOf(c));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendAttributeValue(String str) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendAttributeValue(char c) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(c);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void appendAttributeValue(char[] cArr) {
            ensureAttributeValue();
            this.pendingAttributeValue.append(cArr);
        }

        private final void ensureAttributeValue() {
            if (this.pendingAttributeValue == null) {
                this.pendingAttributeValue = new StringBuilder();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class StartTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag() {
            this.attributes = new Attributes();
            this.type = TokenType.StartTag;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag(String str) {
            this();
            this.tagName = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public StartTag(String str, Attributes attributes) {
            this();
            this.tagName = str;
            this.attributes = attributes;
        }

        public String toString() {
            if (this.attributes != null && this.attributes.size() > 0) {
                return "<" + name() + " " + this.attributes.toString() + ">";
            }
            return "<" + name() + ">";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class EndTag extends Tag {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EndTag() {
            this.type = TokenType.EndTag;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public EndTag(String str) {
            this();
            this.tagName = str;
        }

        public String toString() {
            return "</" + name() + ">";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Comment extends Token {
        boolean bogus;
        final StringBuilder data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Comment() {
            super();
            this.data = new StringBuilder();
            this.bogus = false;
            this.type = TokenType.Comment;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getData() {
            return this.data.toString();
        }

        public String toString() {
            return "<!--" + getData() + "-->";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class Character extends Token {
        private final String data;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Character(String str) {
            super();
            this.type = TokenType.Character;
            this.data = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public String getData() {
            return this.data;
        }

        public String toString() {
            return getData();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class EOF extends Token {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EOF() {
            super();
            this.type = TokenType.EOF;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isDoctype() {
        return this.type == TokenType.Doctype;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Doctype asDoctype() {
        return (Doctype) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isStartTag() {
        return this.type == TokenType.StartTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartTag asStartTag() {
        return (StartTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEndTag() {
        return this.type == TokenType.EndTag;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EndTag asEndTag() {
        return (EndTag) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isComment() {
        return this.type == TokenType.Comment;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Comment asComment() {
        return (Comment) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCharacter() {
        return this.type == TokenType.Character;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Character asCharacter() {
        return (Character) this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEOF() {
        return this.type == TokenType.EOF;
    }
}
