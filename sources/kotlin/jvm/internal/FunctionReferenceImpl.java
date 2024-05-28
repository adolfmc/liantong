package kotlin.jvm.internal;

import kotlin.reflect.KDeclarationContainer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FunctionReferenceImpl extends FunctionReference {
    private final String name;
    private final KDeclarationContainer owner;
    private final String signature;

    public FunctionReferenceImpl(int i, KDeclarationContainer kDeclarationContainer, String str, String str2) {
        super(i);
        this.owner = kDeclarationContainer;
        this.name = str;
        this.signature = str2;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return this.owner;
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return this.signature;
    }
}
