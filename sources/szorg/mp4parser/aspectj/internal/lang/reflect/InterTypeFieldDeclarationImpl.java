package szorg.mp4parser.aspectj.internal.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import szorg.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class InterTypeFieldDeclarationImpl extends InterTypeDeclarationImpl implements InterTypeFieldDeclaration {
    private Type genericType;
    private String name;
    private AjType<?> type;

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, String str, int i, String str2, AjType<?> ajType2, Type type) {
        super(ajType, str, i);
        this.name = str2;
        this.type = ajType2;
        this.genericType = type;
    }

    public InterTypeFieldDeclarationImpl(AjType<?> ajType, AjType<?> ajType2, Field field) {
        super(ajType, ajType2, field.getModifiers());
        this.name = field.getName();
        this.type = AjTypeSystem.getAjType(field.getType());
        Type genericType = field.getGenericType();
        this.genericType = genericType instanceof Class ? AjTypeSystem.getAjType((Class) genericType) : genericType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public Type getGenericType() {
        return this.genericType;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public String getName() {
        return this.name;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration
    public AjType<?> getType() {
        return this.type;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(Modifier.toString(getModifiers()));
        stringBuffer.append(" ");
        stringBuffer.append(getType().toString());
        stringBuffer.append(" ");
        stringBuffer.append(this.targetTypeName);
        stringBuffer.append(".");
        stringBuffer.append(getName());
        return stringBuffer.toString();
    }
}
