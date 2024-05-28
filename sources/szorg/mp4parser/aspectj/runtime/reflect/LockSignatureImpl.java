package szorg.mp4parser.aspectj.runtime.reflect;

import szorg.mp4parser.aspectj.lang.reflect.LockSignature;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
class LockSignatureImpl extends SignatureImpl implements LockSignature {
    private Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LockSignatureImpl(Class cls) {
        super(8, "lock", cls);
        this.parameterType = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LockSignatureImpl(String str) {
        super(str);
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("lock(");
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
