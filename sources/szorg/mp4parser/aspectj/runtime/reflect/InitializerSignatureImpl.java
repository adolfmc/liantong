package szorg.mp4parser.aspectj.runtime.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import szorg.mp4parser.aspectj.lang.reflect.InitializerSignature;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
class InitializerSignatureImpl extends CodeSignatureImpl implements InitializerSignature {
    private Constructor constructor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitializerSignatureImpl(int i, Class cls) {
        super(i, Modifier.isStatic(i) ? "<clinit>" : "<init>", cls, SignatureImpl.EMPTY_CLASS_ARRAY, SignatureImpl.EMPTY_STRING_ARRAY, SignatureImpl.EMPTY_CLASS_ARRAY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitializerSignatureImpl(String str) {
        super(str);
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(stringMaker.makeModifiersString(getModifiers()));
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(".");
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InitializerSignature
    public Constructor getInitializer() {
        if (this.constructor == null) {
            try {
                this.constructor = getDeclaringType().getDeclaredConstructor(getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.constructor;
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl, szorg.mp4parser.aspectj.lang.Signature
    public String getName() {
        return Modifier.isStatic(getModifiers()) ? "<clinit>" : "<init>";
    }
}
