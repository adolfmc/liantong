package szorg.mp4parser.aspectj.runtime.reflect;

import szorg.mp4parser.aspectj.lang.reflect.CatchClauseSignature;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
class CatchClauseSignatureImpl extends SignatureImpl implements CatchClauseSignature {
    String parameterName;
    Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CatchClauseSignatureImpl(Class cls, Class cls2, String str) {
        super(0, "catch", cls);
        this.parameterType = cls2;
        this.parameterName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CatchClauseSignatureImpl(String str) {
        super(str);
    }

    @Override // szorg.mp4parser.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("catch(");
        stringBuffer.append(stringMaker.makeTypeName(getParameterType()));
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.CatchClauseSignature
    public String getParameterName() {
        if (this.parameterName == null) {
            this.parameterName = extractString(4);
        }
        return this.parameterName;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.CatchClauseSignature
    public Class getParameterType() {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return this.parameterType;
    }
}
