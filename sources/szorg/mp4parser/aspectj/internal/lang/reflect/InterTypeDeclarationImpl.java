package szorg.mp4parser.aspectj.internal.lang.reflect;

import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.InterTypeDeclaration;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class InterTypeDeclarationImpl implements InterTypeDeclaration {
    private AjType<?> declaringType;
    private int modifiers;
    private AjType<?> targetType;
    protected String targetTypeName;

    public InterTypeDeclarationImpl(AjType<?> ajType, String str, int i) {
        this.declaringType = ajType;
        this.targetTypeName = str;
        this.modifiers = i;
        try {
            this.targetType = (AjType) StringToType.stringToType(str, ajType.getJavaClass());
        } catch (ClassNotFoundException unused) {
        }
    }

    public InterTypeDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, int i) {
        this.declaringType = ajType;
        this.targetType = ajType2;
        this.targetTypeName = ajType2.getName();
        this.modifiers = i;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeDeclaration
    public AjType<?> getDeclaringType() {
        return this.declaringType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeDeclaration
    public int getModifiers() {
        return this.modifiers;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeDeclaration
    public AjType<?> getTargetType() {
        AjType<?> ajType = this.targetType;
        if (ajType != null) {
            return ajType;
        }
        throw new ClassNotFoundException(this.targetTypeName);
    }
}
