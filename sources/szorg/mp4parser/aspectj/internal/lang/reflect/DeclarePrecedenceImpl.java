package szorg.mp4parser.aspectj.internal.lang.reflect;

import java.util.StringTokenizer;
import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.DeclarePrecedence;
import szorg.mp4parser.aspectj.lang.reflect.TypePattern;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DeclarePrecedenceImpl implements DeclarePrecedence {
    private AjType<?> declaringType;
    private TypePattern[] precedenceList;
    private String precedenceString;

    public DeclarePrecedenceImpl(String str, AjType ajType) {
        this.declaringType = ajType;
        this.precedenceString = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str.startsWith("(") ? str.substring(1, str.length() - 1) : str, ",");
        this.precedenceList = new TypePattern[stringTokenizer.countTokens()];
        int i = 0;
        while (true) {
            TypePattern[] typePatternArr = this.precedenceList;
            if (i >= typePatternArr.length) {
                return;
            }
            typePatternArr[i] = new TypePatternImpl(stringTokenizer.nextToken().trim());
            i++;
        }
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclarePrecedence
    public AjType getDeclaringType() {
        return this.declaringType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.DeclarePrecedence
    public TypePattern[] getPrecedenceOrder() {
        return this.precedenceList;
    }

    public String toString() {
        return "declare precedence : " + this.precedenceString;
    }
}
