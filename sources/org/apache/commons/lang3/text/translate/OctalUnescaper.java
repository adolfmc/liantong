package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex */
public class OctalUnescaper extends CharSequenceTranslator {
    private boolean isOctalDigit(char c) {
        return c >= '0' && c <= '7';
    }

    private boolean isZeroToThree(char c) {
        return c >= '0' && c <= '3';
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence charSequence, int i, Writer writer) throws IOException {
        int length = (charSequence.length() - i) - 1;
        StringBuilder sb = new StringBuilder();
        if (charSequence.charAt(i) != '\\' || length <= 0) {
            return 0;
        }
        int i2 = i + 1;
        if (isOctalDigit(charSequence.charAt(i2))) {
            int i3 = i + 2;
            int i4 = i + 3;
            sb.append(charSequence.charAt(i2));
            if (length > 1 && isOctalDigit(charSequence.charAt(i3))) {
                sb.append(charSequence.charAt(i3));
                if (length > 2 && isZeroToThree(charSequence.charAt(i2)) && isOctalDigit(charSequence.charAt(i4))) {
                    sb.append(charSequence.charAt(i4));
                }
            }
            writer.write(Integer.parseInt(sb.toString(), 8));
            return sb.length() + 1;
        }
        return 0;
    }
}
