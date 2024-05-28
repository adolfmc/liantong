package org.apache.commons.codec.language.p451bm;

import com.sdk.p285a.C6960d;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.codec.language.p451bm.Languages;
import org.apache.commons.codec.language.p451bm.Rule;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.apache.commons.codec.language.bm.PhoneticEngine */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PhoneticEngine {
    private static final Map<NameType, Set<String>> NAME_PREFIXES = new EnumMap(NameType.class);
    private final boolean concat;
    private final Lang lang;
    private final NameType nameType;
    private final RuleType ruleType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.PhoneticEngine$PhonemeBuilder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class PhonemeBuilder {
        private final Set<Rule.Phoneme> phonemes;

        public static PhonemeBuilder empty(Languages.LanguageSet languageSet) {
            return new PhonemeBuilder(Collections.singleton(new Rule.Phoneme("", languageSet)));
        }

        private PhonemeBuilder(Set<Rule.Phoneme> set) {
            this.phonemes = set;
        }

        public PhonemeBuilder append(CharSequence charSequence) {
            HashSet hashSet = new HashSet();
            for (Rule.Phoneme phoneme : this.phonemes) {
                hashSet.add(phoneme.append(charSequence));
            }
            return new PhonemeBuilder(hashSet);
        }

        public PhonemeBuilder apply(Rule.PhonemeExpr phonemeExpr) {
            HashSet hashSet = new HashSet();
            for (Rule.Phoneme phoneme : this.phonemes) {
                for (Rule.Phoneme phoneme2 : phonemeExpr.getPhonemes()) {
                    Rule.Phoneme join = phoneme.join(phoneme2);
                    if (!join.getLanguages().isEmpty()) {
                        hashSet.add(join);
                    }
                }
            }
            return new PhonemeBuilder(hashSet);
        }

        public Set<Rule.Phoneme> getPhonemes() {
            return this.phonemes;
        }

        public String makeString() {
            StringBuilder sb = new StringBuilder();
            for (Rule.Phoneme phoneme : this.phonemes) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(phoneme.getPhonemeText());
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.apache.commons.codec.language.bm.PhoneticEngine$RulesApplication */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class RulesApplication {
        private final List<Rule> finalRules;
        private boolean found;

        /* renamed from: i */
        private int f26254i;
        private final CharSequence input;
        private PhonemeBuilder phonemeBuilder;

        public RulesApplication(List<Rule> list, CharSequence charSequence, PhonemeBuilder phonemeBuilder, int i) {
            if (list == null) {
                throw new NullPointerException("The finalRules argument must not be null");
            }
            this.finalRules = list;
            this.phonemeBuilder = phonemeBuilder;
            this.input = charSequence;
            this.f26254i = i;
        }

        public int getI() {
            return this.f26254i;
        }

        public PhonemeBuilder getPhonemeBuilder() {
            return this.phonemeBuilder;
        }

        public RulesApplication invoke() {
            int i = 0;
            this.found = false;
            Iterator<Rule> it = this.finalRules.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Rule next = it.next();
                int length = next.getPattern().length();
                if (next.patternAndContextMatches(this.input, this.f26254i)) {
                    this.phonemeBuilder = this.phonemeBuilder.apply(next.getPhoneme());
                    this.found = true;
                    i = length;
                    break;
                }
                i = length;
            }
            if (!this.found) {
                i = 1;
            }
            this.f26254i += i;
            return this;
        }

        public boolean isFound() {
            return this.found;
        }
    }

    static {
        NAME_PREFIXES.put(NameType.ASHKENAZI, Collections.unmodifiableSet(new HashSet(Arrays.asList("bar", "ben", "da", "de", "van", "von"))));
        NAME_PREFIXES.put(NameType.SEPHARDIC, Collections.unmodifiableSet(new HashSet(Arrays.asList("al", "el", "da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
        NAME_PREFIXES.put(NameType.GENERIC, Collections.unmodifiableSet(new HashSet(Arrays.asList("da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
    }

    private static CharSequence cacheSubSequence(final CharSequence charSequence) {
        final CharSequence[][] charSequenceArr = (CharSequence[][]) Array.newInstance(CharSequence.class, charSequence.length(), charSequence.length());
        return new CharSequence() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine.1
            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return charSequence.charAt(i);
            }

            @Override // java.lang.CharSequence
            public int length() {
                return charSequence.length();
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                if (i == i2) {
                    return "";
                }
                int i3 = i2 - 1;
                CharSequence charSequence2 = charSequenceArr[i][i3];
                if (charSequence2 == null) {
                    CharSequence subSequence = charSequence.subSequence(i, i2);
                    charSequenceArr[i][i3] = subSequence;
                    return subSequence;
                }
                return charSequence2;
            }
        };
    }

    private static String join(Iterable<String> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = iterable.iterator();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
        }
        return sb.toString();
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean z) {
        if (ruleType == RuleType.RULES) {
            throw new IllegalArgumentException("ruleType must not be " + RuleType.RULES);
        }
        this.nameType = nameType;
        this.ruleType = ruleType;
        this.concat = z;
        this.lang = Lang.instance(nameType);
    }

    private PhonemeBuilder applyFinalRules(PhonemeBuilder phonemeBuilder, List<Rule> list) {
        if (list == null) {
            throw new NullPointerException("finalRules can not be null");
        }
        if (list.isEmpty()) {
            return phonemeBuilder;
        }
        TreeSet treeSet = new TreeSet(Rule.Phoneme.COMPARATOR);
        for (Rule.Phoneme phoneme : phonemeBuilder.getPhonemes()) {
            PhonemeBuilder empty = PhonemeBuilder.empty(phoneme.getLanguages());
            CharSequence cacheSubSequence = cacheSubSequence(phoneme.getPhonemeText());
            int i = 0;
            while (i < cacheSubSequence.length()) {
                RulesApplication invoke = new RulesApplication(list, cacheSubSequence, empty, i).invoke();
                boolean isFound = invoke.isFound();
                PhonemeBuilder phonemeBuilder2 = invoke.getPhonemeBuilder();
                PhonemeBuilder append = !isFound ? phonemeBuilder2.append(cacheSubSequence.subSequence(i, i + 1)) : phonemeBuilder2;
                i = invoke.getI();
                empty = append;
            }
            treeSet.addAll(empty.getPhonemes());
        }
        return new PhonemeBuilder(treeSet);
    }

    public String encode(String str) {
        return encode(str, this.lang.guessLanguages(str));
    }

    public String encode(String str, Languages.LanguageSet languageSet) {
        String str2;
        String substring;
        String substring2;
        List<Rule> rule = Rule.getInstance(this.nameType, RuleType.RULES, languageSet);
        List<Rule> rule2 = Rule.getInstance(this.nameType, this.ruleType, "common");
        List<Rule> rule3 = Rule.getInstance(this.nameType, this.ruleType, languageSet);
        String trim = str.toLowerCase(Locale.ENGLISH).replace('-', ' ').trim();
        int i = 0;
        if (this.nameType == NameType.GENERIC) {
            if (trim.length() >= 2 && trim.substring(0, 2).equals("d'")) {
                String str3 = C6960d.f18019d + trim.substring(2);
                return "(" + encode(substring2) + ")-(" + encode(str3) + ")";
            }
            for (String str4 : NAME_PREFIXES.get(this.nameType)) {
                if (trim.startsWith(str4 + " ")) {
                    String str5 = str4 + trim.substring(str4.length() + 1);
                    return "(" + encode(substring) + ")-(" + encode(str5) + ")";
                }
            }
        }
        List<String> asList = Arrays.asList(trim.split("\\s+"));
        ArrayList<String> arrayList = new ArrayList();
        switch (this.nameType) {
            case SEPHARDIC:
                for (String str6 : asList) {
                    String[] split = str6.split("'");
                    arrayList.add(split[split.length - 1]);
                }
                arrayList.removeAll(NAME_PREFIXES.get(this.nameType));
                break;
            case ASHKENAZI:
                arrayList.addAll(asList);
                arrayList.removeAll(NAME_PREFIXES.get(this.nameType));
                break;
            case GENERIC:
                arrayList.addAll(asList);
                break;
            default:
                throw new IllegalStateException("Unreachable case: " + this.nameType);
        }
        if (this.concat) {
            str2 = join(arrayList, " ");
        } else if (arrayList.size() == 1) {
            str2 = (String) asList.iterator().next();
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str7 : arrayList) {
                sb.append("-");
                sb.append(encode(str7));
            }
            return sb.substring(1);
        }
        PhonemeBuilder empty = PhonemeBuilder.empty(languageSet);
        CharSequence cacheSubSequence = cacheSubSequence(str2);
        while (i < cacheSubSequence.length()) {
            RulesApplication invoke = new RulesApplication(rule, cacheSubSequence, empty, i).invoke();
            i = invoke.getI();
            empty = invoke.getPhonemeBuilder();
        }
        return applyFinalRules(applyFinalRules(empty, rule2), rule3).makeString();
    }

    public Lang getLang() {
        return this.lang;
    }

    public NameType getNameType() {
        return this.nameType;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public boolean isConcat() {
        return this.concat;
    }
}
