package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmPlatformAnnotations;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m1890d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: kotlin.text.ScreenFloatValueRegEx */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class StringNumberConversionsJVM {
    public static final StringNumberConversionsJVM INSTANCE = new StringNumberConversionsJVM();
    @JvmPlatformAnnotations
    @NotNull
    public static final Regex value;

    static {
        String str = "[eE][+-]?(\\p{Digit}+)";
        value = new Regex("[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + ("((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)(" + str + ")?)|(\\.((\\p{Digit}+))(" + str + ")?)|((" + ("(0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+))") + ")[pP][+-]?(\\p{Digit}+))") + ")[fFdD]?))[\\x00-\\x20]*");
    }

    private StringNumberConversionsJVM() {
    }
}
