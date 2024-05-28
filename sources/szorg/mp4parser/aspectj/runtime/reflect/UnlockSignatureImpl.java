package szorg.mp4parser.aspectj.runtime.reflect;

import szorg.mp4parser.aspectj.lang.reflect.UnlockSignature;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
class UnlockSignatureImpl extends SignatureImpl implements UnlockSignature {
    private Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnlockSignatureImpl(Class cls) {
        super(8, "unlock", cls);
        this.parameterType = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnlockSignatureImpl(String str) {
        super(str);
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("unlock(");
        stringBuffer.append(stringMaker.makeTypeName(this.parameterType));
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public Class getParameterType() {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return this.parameterType;
    }
}
