package org.apache.commons.codec.language.p451bm;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.p451bm.Languages;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.apache.commons.codec.language.bm.Rule */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Rule {
    public static final String ALL = "ALL";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String HASH_INCLUDE = "#include";
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.1
        @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }
    };
    private static final Map<NameType, Map<RuleType, Map<String, List<Rule>>>> RULES = new EnumMap(NameType.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Rule$PhonemeExpr */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Rule$RPattern */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Rule$Phoneme */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator<Phoneme>() { // from class: org.apache.commons.codec.language.bm.Rule.Phoneme.1
            @Override // java.util.Comparator
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.phonemeText.length(); i++) {
                    if (i >= phoneme2.phonemeText.length()) {
                        return 1;
                    }
                    int charAt = phoneme.phonemeText.charAt(i) - phoneme2.phonemeText.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                return phoneme.phonemeText.length() < phoneme2.phonemeText.length() ? -1 : 0;
            }
        };
        private final Languages.LanguageSet languages;
        private final CharSequence phonemeText;

        public Phoneme(CharSequence charSequence, Languages.LanguageSet languageSet) {
            this.phonemeText = charSequence;
            this.languages = languageSet;
        }

        public Phoneme append(CharSequence charSequence) {
            return new Phoneme(this.phonemeText.toString() + charSequence.toString(), this.languages);
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        @Override // org.apache.commons.codec.language.p451bm.Rule.PhonemeExpr
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        public Phoneme join(Phoneme phoneme) {
            return new Phoneme(this.phonemeText.toString() + phoneme.phonemeText.toString(), this.languages.restrictTo(phoneme.languages));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.Rule$PhonemeList */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemes;

        public PhonemeList(List<Phoneme> list) {
            this.phonemes = list;
        }

        @Override // org.apache.commons.codec.language.p451bm.Rule.PhonemeExpr
        public List<Phoneme> getPhonemes() {
            return this.phonemes;
        }
    }

    static {
        NameType[] values;
        RuleType[] values2;
        for (NameType nameType : NameType.values()) {
            EnumMap enumMap = new EnumMap(RuleType.class);
            for (RuleType ruleType : RuleType.values()) {
                HashMap hashMap = new HashMap();
                for (String str : Languages.getInstance(nameType).getLanguages()) {
                    try {
                        hashMap.put(str, parseRules(createScanner(nameType, ruleType, str), createResourceName(nameType, ruleType, str)));
                    } catch (IllegalStateException e) {
                        throw new IllegalStateException("Problem processing " + createResourceName(nameType, ruleType, str), e);
                    }
                }
                if (!ruleType.equals(RuleType.RULES)) {
                    hashMap.put("common", parseRules(createScanner(nameType, ruleType, "common"), createResourceName(nameType, ruleType, "common")));
                }
                enumMap.put((EnumMap) ruleType, (RuleType) Collections.unmodifiableMap(hashMap));
            }
            RULES.put(nameType, Collections.unmodifiableMap(enumMap));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean contains(CharSequence charSequence, char c) {
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    private static String createResourceName(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), ruleType.getName(), str);
    }

    private static Scanner createScanner(NameType nameType, RuleType ruleType, String str) {
        String createResourceName = createResourceName(nameType, ruleType, str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(createResourceName);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: " + createResourceName);
        }
        return new Scanner(resourceAsStream, "UTF-8");
    }

    private static Scanner createScanner(String str) {
        String format = String.format("org/apache/commons/codec/language/bm/%s.txt", str);
        InputStream resourceAsStream = Languages.class.getClassLoader().getResourceAsStream(format);
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Unable to load resource: " + format);
        }
        return new Scanner(resourceAsStream, "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean endsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        return getInstance(nameType, ruleType, languageSet.isSingleton() ? languageSet.getAny() : "any");
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        List<Rule> list = RULES.get(nameType).get(ruleType).get(str);
        if (list != null) {
            return list;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", nameType.getName(), ruleType.getName(), str));
    }

    private static Phoneme parsePhoneme(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf >= 0) {
            if (!str.endsWith("]")) {
                throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
            }
            return new Phoneme(str.substring(0, indexOf), Languages.LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
        }
        return new Phoneme(str, Languages.ANY_LANGUAGE);
    }

    private static PhonemeExpr parsePhonemeExpr(String str) {
        if (str.startsWith("(")) {
            if (!str.endsWith(")")) {
                throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
            }
            ArrayList arrayList = new ArrayList();
            String substring = str.substring(1, str.length() - 1);
            for (String str2 : substring.split("[|]")) {
                arrayList.add(parsePhoneme(str2));
            }
            if (substring.startsWith("|") || substring.endsWith("|")) {
                arrayList.add(new Phoneme("", Languages.ANY_LANGUAGE));
            }
            return new PhonemeList(arrayList);
        }
        return parsePhoneme(str);
    }

    private static List<Rule> parseRules(Scanner scanner, final String str) {
        ArrayList arrayList = new ArrayList();
        final int i = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            i++;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith("*/")) {
                    z = false;
                }
            } else if (nextLine.startsWith("/*")) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf("//");
                String trim = (indexOf >= 0 ? nextLine.substring(0, indexOf) : nextLine).trim();
                if (trim.length() == 0) {
                    continue;
                } else if (trim.startsWith("#include")) {
                    String trim2 = trim.substring(8).trim();
                    if (trim2.contains(" ")) {
                        System.err.println("Warining: malformed import statement: " + nextLine);
                    } else {
                        arrayList.addAll(parseRules(createScanner(trim2), str + "->" + trim2));
                    }
                } else {
                    String[] split = trim.split("\\s+");
                    if (split.length != 4) {
                        System.err.println("Warning: malformed rule statement split into " + split.length + " parts: " + nextLine);
                    } else {
                        try {
                            arrayList.add(new Rule(stripQuotes(split[0]), stripQuotes(split[1]), stripQuotes(split[2]), parsePhonemeExpr(stripQuotes(split[3]))) { // from class: org.apache.commons.codec.language.bm.Rule.2
                                private final String loc;
                                private final int myLine;

                                {
                                    this.myLine = i;
                                    this.loc = str;
                                }

                                public String toString() {
                                    return "Rule{line=" + this.myLine + ", loc='" + this.loc + "'}";
                                }
                            });
                        } catch (IllegalArgumentException e) {
                            throw new IllegalStateException("Problem parsing line " + i, e);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static RPattern pattern(final String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        final String substring = str.substring(startsWith ? 1 : 0, endsWith ? str.length() - 1 : str.length());
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                final String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    final boolean z = true ^ startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.7
                            @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() == 1 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.8
                            @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(0)) == z;
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.9
                            @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                            public boolean isMatch(CharSequence charSequence) {
                                return charSequence.length() > 0 && Rule.contains(substring2, charSequence.charAt(charSequence.length() - 1)) == z;
                            }
                        };
                    }
                }
            }
        } else if (startsWith && endsWith) {
            if (substring.length() == 0) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.3
                    @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return charSequence.length() == 0;
                    }
                };
            }
            return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.4
                @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                public boolean isMatch(CharSequence charSequence) {
                    return charSequence.equals(substring);
                }
            };
        } else if ((startsWith || endsWith) && substring.length() == 0) {
            return ALL_STRINGS_RMATCHER;
        } else {
            if (startsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.5
                    @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.startsWith(charSequence, substring);
                    }
                };
            }
            if (endsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.6
                    @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
                    public boolean isMatch(CharSequence charSequence) {
                        return Rule.endsWith(charSequence, substring);
                    }
                };
            }
        }
        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.10
            Pattern pattern;

            {
                this.pattern = Pattern.compile(str);
            }

            @Override // org.apache.commons.codec.language.p451bm.Rule.RPattern
            public boolean isMatch(CharSequence charSequence) {
                return this.pattern.matcher(charSequence).find();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean startsWith(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i = 0; i < charSequence2.length(); i++) {
            if (charSequence.charAt(i) != charSequence2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.pattern = str;
        this.lContext = pattern(str2 + "$");
        this.rContext = pattern("^" + str3);
        this.phoneme = phonemeExpr;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
        }
        int length = this.pattern.length() + i;
        if (length > charSequence.length()) {
            return false;
        }
        return charSequence.subSequence(i, length).equals(this.pattern) && this.rContext.isMatch(charSequence.subSequence(length, charSequence.length())) && this.lContext.isMatch(charSequence.subSequence(0, i));
    }
}
