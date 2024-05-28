package org.apache.commons.codec.language.p451bm;

import java.io.InputStream;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.apache.commons.codec.language.bm.Languages */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Languages {
    public static final String ANY = "any";
    public static final LanguageSet ANY_LANGUAGE;
    private static final Map<NameType, Languages> LANGUAGES = new EnumMap(NameType.class);
    public static final LanguageSet NO_LANGUAGES;
    private final Set<String> languages;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Languages$LanguageSet */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class LanguageSet {
        public abstract boolean contains(String str);

        public abstract String getAny();

        public abstract boolean isEmpty();

        public abstract boolean isSingleton();

        public abstract LanguageSet restrictTo(LanguageSet languageSet);

        public static LanguageSet from(Set<String> set) {
            return set.isEmpty() ? Languages.NO_LANGUAGES : new SomeLanguages(set);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Languages$SomeLanguages */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class SomeLanguages extends LanguageSet {
        private final Set<String> languages;

        private SomeLanguages(Set<String> set) {
            this.languages = Collections.unmodifiableSet(set);
        }

        @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
        public boolean contains(String str) {
            return this.languages.contains(str);
        }

        @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
        public String getAny() {
            return this.languages.iterator().next();
        }

        public Set<String> getLanguages() {
            return this.languages;
        }

        @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
        public boolean isEmpty() {
            return this.languages.isEmpty();
        }

        @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
        public boolean isSingleton() {
            return this.languages.size() == 1;
        }

        @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
        public LanguageSet restrictTo(LanguageSet languageSet) {
            if (languageSet == Languages.NO_LANGUAGES) {
                return languageSet;
            }
            if (languageSet == Languages.ANY_LANGUAGE) {
                return this;
            }
            SomeLanguages someLanguages = (SomeLanguages) languageSet;
            if (someLanguages.languages.containsAll(this.languages)) {
                return this;
            }
            HashSet hashSet = new HashSet(this.languages);
            hashSet.retainAll(someLanguages.languages);
            return from(hashSet);
        }

        public String toString() {
            return "Languages(" + this.languages.toString() + ")";
        }
    }

    static {
        NameType[] values;
        for (NameType nameType : NameType.values()) {
            LANGUAGES.put(nameType, getInstance(langResourceName(nameType)));
        }
        NO_LANGUAGES = new LanguageSet() { // from class: org.apache.commons.codec.language.bm.Languages.1
            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean contains(String str) {
                return false;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean isEmpty() {
                return true;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean isSingleton() {
                return false;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public LanguageSet restrictTo(LanguageSet languageSet) {
                return this;
            }

            public String toString() {
                return "NO_LANGUAGES";
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public String getAny() {
                throw new NoSuchElementException("Can't fetch any language from the empty language set.");
            }
        };
        ANY_LANGUAGE = new LanguageSet() { // from class: org.apache.commons.codec.language.bm.Languages.2
            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean contains(String str) {
                return true;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean isEmpty() {
                return false;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public boolean isSingleton() {
                return false;
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public LanguageSet restrictTo(LanguageSet languageSet) {
                return languageSet;
            }

            public String toString() {
                return "ANY_LANGUAGE";
            }

            @Override // org.apache.commons.codec.language.p451bm.Languages.LanguageSet
            public String getAny() {
                throw new NoSuchElementException("Can't fetch any language from the any language set.");
            }
        };
    }

    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get(nameType);
    }

    public static Languages getInstance(String str) {
        HashSet hashSet = new HashSet();
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to resolve required resource: " + str);
        }
        Scanner scanner = new Scanner(resourceAsStream, "UTF-8");
        boolean z = false;
        while (scanner.hasNextLine()) {
            String trim = scanner.nextLine().trim();
            if (z) {
                if (trim.endsWith("*/")) {
                    z = false;
                }
            } else if (trim.startsWith("/*")) {
                z = true;
            } else if (trim.length() > 0) {
                hashSet.add(trim);
            }
        }
        return new Languages(Collections.unmodifiableSet(hashSet));
    }

    private static String langResourceName(NameType nameType) {
        return String.format("org/apache/commons/codec/language/bm/%s_languages.txt", nameType.getName());
    }

    private Languages(Set<String> set) {
        this.languages = set;
    }

    public Set<String> getLanguages() {
        return this.languages;
    }
}
