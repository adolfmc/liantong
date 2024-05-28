package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.PerClauseKind;
import szorg.mp4parser.aspectj.lang.reflect.TypePattern;
import szorg.mp4parser.aspectj.lang.reflect.TypePatternBasedPerClause;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TypePatternBasedPerClauseImpl extends PerClauseImpl implements TypePatternBasedPerClause {
    private TypePattern typePattern;

    public TypePatternBasedPerClauseImpl(PerClauseKind perClauseKind, String str) {
        super(perClauseKind);
        this.typePattern = new TypePatternImpl(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.TypePatternBasedPerClause
    public TypePattern getTypePattern() {
        return this.typePattern;
    }

    @Override // szorg.mp4parser.aspectj.internal.lang.reflect.PerClauseImpl
    public String toString() {
        return "pertypewithin(" + this.typePattern.asString() + ")";
    }
}
