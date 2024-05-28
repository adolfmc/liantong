package szorg.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Field;
import szorg.mp4parser.aspectj.lang.reflect.FieldSignature;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FieldSignatureImpl extends MemberSignatureImpl implements FieldSignature {
    private Field field;
    Class fieldType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldSignatureImpl(int i, String str, Class cls, Class cls2) {
        super(i, str, cls);
        this.fieldType = cls2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldSignatureImpl(String str) {
        super(str);
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        if (stringMaker.includeArgs) {
            stringBuffer.append(stringMaker.makeTypeName(getFieldType()));
        }
        if (stringMaker.includeArgs) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(".");
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.FieldSignature
    public Field getField() {
        if (this.field == null) {
            try {
                this.field = getDeclaringType().getDeclaredField(getName());
            } catch (Exception unused) {
            }
        }
        return this.field;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.FieldSignature
    public Class getFieldType() {
        if (this.fieldType == null) {
            this.fieldType = extractType(3);
        }
        return this.fieldType;
    }
}
