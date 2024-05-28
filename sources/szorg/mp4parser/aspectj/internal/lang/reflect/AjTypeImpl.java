package szorg.mp4parser.aspectj.internal.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcDeclareEoW;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcDeclareParents;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcDeclarePrecedence;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcDeclareSoft;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcITD;
import szorg.mp4parser.aspectj.internal.lang.annotation.ajcPrivileged;
import szorg.mp4parser.aspectj.lang.annotation.After;
import szorg.mp4parser.aspectj.lang.annotation.AfterReturning;
import szorg.mp4parser.aspectj.lang.annotation.AfterThrowing;
import szorg.mp4parser.aspectj.lang.annotation.Around;
import szorg.mp4parser.aspectj.lang.annotation.Aspect;
import szorg.mp4parser.aspectj.lang.annotation.Before;
import szorg.mp4parser.aspectj.lang.annotation.DeclareError;
import szorg.mp4parser.aspectj.lang.annotation.DeclareWarning;
import szorg.mp4parser.aspectj.lang.reflect.Advice;
import szorg.mp4parser.aspectj.lang.reflect.AdviceKind;
import szorg.mp4parser.aspectj.lang.reflect.AjType;
import szorg.mp4parser.aspectj.lang.reflect.AjTypeSystem;
import szorg.mp4parser.aspectj.lang.reflect.DeclareAnnotation;
import szorg.mp4parser.aspectj.lang.reflect.DeclareErrorOrWarning;
import szorg.mp4parser.aspectj.lang.reflect.DeclareParents;
import szorg.mp4parser.aspectj.lang.reflect.DeclarePrecedence;
import szorg.mp4parser.aspectj.lang.reflect.DeclareSoft;
import szorg.mp4parser.aspectj.lang.reflect.InterTypeConstructorDeclaration;
import szorg.mp4parser.aspectj.lang.reflect.InterTypeFieldDeclaration;
import szorg.mp4parser.aspectj.lang.reflect.InterTypeMethodDeclaration;
import szorg.mp4parser.aspectj.lang.reflect.NoSuchAdviceException;
import szorg.mp4parser.aspectj.lang.reflect.NoSuchPointcutException;
import szorg.mp4parser.aspectj.lang.reflect.PerClause;
import szorg.mp4parser.aspectj.lang.reflect.PerClauseKind;
import szorg.mp4parser.aspectj.lang.reflect.Pointcut;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AjTypeImpl<T> implements AjType<T> {
    private static final String ajcMagic = "ajc$";
    private Class<T> clazz;
    private Pointcut[] declaredPointcuts = null;
    private Pointcut[] pointcuts = null;
    private Advice[] declaredAdvice = null;
    private Advice[] advice = null;
    private InterTypeMethodDeclaration[] declaredITDMethods = null;
    private InterTypeMethodDeclaration[] itdMethods = null;
    private InterTypeFieldDeclaration[] declaredITDFields = null;
    private InterTypeFieldDeclaration[] itdFields = null;
    private InterTypeConstructorDeclaration[] itdCons = null;
    private InterTypeConstructorDeclaration[] declaredITDCons = null;

    public AjTypeImpl(Class<T> cls) {
        this.clazz = cls;
    }

    private void addAnnotationStyleDeclareParents(List<DeclareParents> list) {
        Field[] declaredFields;
        for (Field field : this.clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(szorg.mp4parser.aspectj.lang.annotation.DeclareParents.class) && field.getType().isInterface()) {
                list.add(new DeclareParentsImpl(((szorg.mp4parser.aspectj.lang.annotation.DeclareParents) field.getAnnotation(szorg.mp4parser.aspectj.lang.annotation.DeclareParents.class)).value(), field.getType().getName(), false, this));
            }
        }
    }

    private void addAnnotationStyleITDFields(List<InterTypeFieldDeclaration> list, boolean z) {
    }

    private void addAnnotationStyleITDMethods(List<InterTypeMethodDeclaration> list, boolean z) {
        Field[] declaredFields;
        Method[] declaredMethods;
        if (isAspect()) {
            for (Field field : this.clazz.getDeclaredFields()) {
                if (field.getType().isInterface() && field.isAnnotationPresent(szorg.mp4parser.aspectj.lang.annotation.DeclareParents.class) && ((szorg.mp4parser.aspectj.lang.annotation.DeclareParents) field.getAnnotation(szorg.mp4parser.aspectj.lang.annotation.DeclareParents.class)).defaultImpl() != szorg.mp4parser.aspectj.lang.annotation.DeclareParents.class) {
                    for (Method method : field.getType().getDeclaredMethods()) {
                        if (Modifier.isPublic(method.getModifiers()) || !z) {
                            list.add(new InterTypeMethodDeclarationImpl(this, AjTypeSystem.getAjType(field.getType()), method, 1));
                        }
                    }
                }
            }
        }
    }

    private Advice asAdvice(Method method) {
        if (method.getAnnotations().length == 0) {
            return null;
        }
        Before before = (Before) method.getAnnotation(Before.class);
        if (before != null) {
            return new AdviceImpl(method, before.value(), AdviceKind.BEFORE);
        }
        After after = (After) method.getAnnotation(After.class);
        if (after != null) {
            return new AdviceImpl(method, after.value(), AdviceKind.AFTER);
        }
        AfterReturning afterReturning = (AfterReturning) method.getAnnotation(AfterReturning.class);
        if (afterReturning != null) {
            String pointcut = afterReturning.pointcut();
            if (pointcut.equals("")) {
                pointcut = afterReturning.value();
            }
            return new AdviceImpl(method, pointcut, AdviceKind.AFTER_RETURNING, afterReturning.returning());
        }
        AfterThrowing afterThrowing = (AfterThrowing) method.getAnnotation(AfterThrowing.class);
        if (afterThrowing != null) {
            String pointcut2 = afterThrowing.pointcut();
            if (pointcut2 == null) {
                pointcut2 = afterThrowing.value();
            }
            return new AdviceImpl(method, pointcut2, AdviceKind.AFTER_THROWING, afterThrowing.throwing());
        }
        Around around = (Around) method.getAnnotation(Around.class);
        if (around != null) {
            return new AdviceImpl(method, around.value(), AdviceKind.AROUND);
        }
        return null;
    }

    private Pointcut asPointcut(Method method) {
        int indexOf;
        szorg.mp4parser.aspectj.lang.annotation.Pointcut pointcut = (szorg.mp4parser.aspectj.lang.annotation.Pointcut) method.getAnnotation(szorg.mp4parser.aspectj.lang.annotation.Pointcut.class);
        if (pointcut != null) {
            String name = method.getName();
            if (name.startsWith(ajcMagic) && (indexOf = (name = name.substring(name.indexOf("$$") + 2, name.length())).indexOf("$")) != -1) {
                name = name.substring(0, indexOf);
            }
            return new PointcutImpl(name, pointcut.value(), method, AjTypeSystem.getAjType(method.getDeclaringClass()), pointcut.argNames());
        }
        return null;
    }

    private Advice[] getAdvice(Set set) {
        Advice[] adviceArr;
        if (this.advice == null) {
            initAdvice();
        }
        ArrayList arrayList = new ArrayList();
        for (Advice advice : this.advice) {
            if (set.contains(advice.getKind())) {
                arrayList.add(advice);
            }
        }
        Advice[] adviceArr2 = new Advice[arrayList.size()];
        arrayList.toArray(adviceArr2);
        return adviceArr2;
    }

    private Advice[] getDeclaredAdvice(Set set) {
        Advice[] adviceArr;
        if (this.declaredAdvice == null) {
            initDeclaredAdvice();
        }
        ArrayList arrayList = new ArrayList();
        for (Advice advice : this.declaredAdvice) {
            if (set.contains(advice.getKind())) {
                arrayList.add(advice);
            }
        }
        Advice[] adviceArr2 = new Advice[arrayList.size()];
        arrayList.toArray(adviceArr2);
        return adviceArr2;
    }

    private void initAdvice() {
        Method[] methods = this.clazz.getMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : methods) {
            Advice asAdvice = asAdvice(method);
            if (asAdvice != null) {
                arrayList.add(asAdvice);
            }
        }
        this.advice = new Advice[arrayList.size()];
        arrayList.toArray(this.advice);
    }

    private void initDeclaredAdvice() {
        Method[] declaredMethods = this.clazz.getDeclaredMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : declaredMethods) {
            Advice asAdvice = asAdvice(method);
            if (asAdvice != null) {
                arrayList.add(asAdvice);
            }
        }
        this.declaredAdvice = new Advice[arrayList.size()];
        arrayList.toArray(this.declaredAdvice);
    }

    private boolean isReallyAMethod(Method method) {
        if (method.getName().startsWith(ajcMagic)) {
            return false;
        }
        if (method.getAnnotations().length == 0) {
            return true;
        }
        return (method.isAnnotationPresent(szorg.mp4parser.aspectj.lang.annotation.Pointcut.class) || method.isAnnotationPresent(Before.class) || method.isAnnotationPresent(After.class) || method.isAnnotationPresent(AfterReturning.class) || method.isAnnotationPresent(AfterThrowing.class) || method.isAnnotationPresent(Around.class)) ? false : true;
    }

    private AjType<?>[] toAjTypeArray(Class<?>[] clsArr) {
        AjType<?>[] ajTypeArr = new AjType[clsArr.length];
        for (int i = 0; i < ajTypeArr.length; i++) {
            ajTypeArr[i] = AjTypeSystem.getAjType(clsArr[i]);
        }
        return ajTypeArr;
    }

    private Class<?>[] toClassArray(AjType<?>[] ajTypeArr) {
        Class<?>[] clsArr = new Class[ajTypeArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr[i] = ajTypeArr[i].getJavaClass();
        }
        return clsArr;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AjTypeImpl) {
            return ((AjTypeImpl) obj).clazz.equals(this.clazz);
        }
        return false;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Advice getAdvice(String str) {
        Advice[] adviceArr;
        if (str.equals("")) {
            throw new IllegalArgumentException("use getAdvice(AdviceType...) instead for un-named advice");
        }
        if (this.advice == null) {
            initAdvice();
        }
        for (Advice advice : this.advice) {
            if (advice.getName().equals(str)) {
                return advice;
            }
        }
        throw new NoSuchAdviceException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Advice[] getAdvice(AdviceKind... adviceKindArr) {
        EnumSet enumSet;
        if (adviceKindArr.length == 0) {
            enumSet = EnumSet.allOf(AdviceKind.class);
        } else {
            EnumSet noneOf = EnumSet.noneOf(AdviceKind.class);
            noneOf.addAll(Arrays.asList(adviceKindArr));
            enumSet = noneOf;
        }
        return getAdvice(enumSet);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<?>[] getAjTypes() {
        return toAjTypeArray(this.clazz.getClasses());
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return (A) this.clazz.getAnnotation(cls);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        return this.clazz.getAnnotations();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Constructor getConstructor(AjType<?>... ajTypeArr) {
        return this.clazz.getConstructor(toClassArray(ajTypeArr));
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Constructor[] getConstructors() {
        return this.clazz.getConstructors();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public DeclareAnnotation[] getDeclareAnnotations() {
        Method[] declaredMethods;
        Annotation annotation;
        ArrayList arrayList = new ArrayList();
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareAnnotation.class)) {
                ajcDeclareAnnotation ajcdeclareannotation = (ajcDeclareAnnotation) method.getAnnotation(ajcDeclareAnnotation.class);
                Annotation[] annotations = method.getAnnotations();
                int length = annotations.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        annotation = null;
                        break;
                    }
                    Annotation annotation2 = annotations[i];
                    if (annotation2.annotationType() != ajcDeclareAnnotation.class) {
                        annotation = annotation2;
                        break;
                    }
                    i++;
                }
                arrayList.add(new DeclareAnnotationImpl(this, ajcdeclareannotation.kind(), ajcdeclareannotation.pattern(), annotation, ajcdeclareannotation.annotation()));
            }
        }
        if (getSupertype().isAspect()) {
            arrayList.addAll(Arrays.asList(getSupertype().getDeclareAnnotations()));
        }
        DeclareAnnotation[] declareAnnotationArr = new DeclareAnnotation[arrayList.size()];
        arrayList.toArray(declareAnnotationArr);
        return declareAnnotationArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public DeclareErrorOrWarning[] getDeclareErrorOrWarnings() {
        Field[] declaredFields;
        Method[] declaredMethods;
        DeclareErrorOrWarningImpl declareErrorOrWarningImpl;
        ArrayList arrayList = new ArrayList();
        for (Field field : this.clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DeclareWarning.class)) {
                DeclareWarning declareWarning = (DeclareWarning) field.getAnnotation(DeclareWarning.class);
                if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                    declareErrorOrWarningImpl = new DeclareErrorOrWarningImpl(declareWarning.value(), (String) field.get(null), false, this);
                }
            } else {
                if (field.isAnnotationPresent(DeclareError.class)) {
                    DeclareError declareError = (DeclareError) field.getAnnotation(DeclareError.class);
                    if (Modifier.isPublic(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                        declareErrorOrWarningImpl = new DeclareErrorOrWarningImpl(declareError.value(), (String) field.get(null), true, this);
                    }
                }
            }
            arrayList.add(declareErrorOrWarningImpl);
        }
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareEoW.class)) {
                ajcDeclareEoW ajcdeclareeow = (ajcDeclareEoW) method.getAnnotation(ajcDeclareEoW.class);
                arrayList.add(new DeclareErrorOrWarningImpl(ajcdeclareeow.pointcut(), ajcdeclareeow.message(), ajcdeclareeow.isError(), this));
            }
        }
        DeclareErrorOrWarning[] declareErrorOrWarningArr = new DeclareErrorOrWarning[arrayList.size()];
        arrayList.toArray(declareErrorOrWarningArr);
        return declareErrorOrWarningArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public DeclareParents[] getDeclareParents() {
        Method[] declaredMethods;
        List<DeclareParents> arrayList = new ArrayList<>();
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareParents.class)) {
                ajcDeclareParents ajcdeclareparents = (ajcDeclareParents) method.getAnnotation(ajcDeclareParents.class);
                arrayList.add(new DeclareParentsImpl(ajcdeclareparents.targetTypePattern(), ajcdeclareparents.parentTypes(), ajcdeclareparents.isExtends(), this));
            }
        }
        addAnnotationStyleDeclareParents(arrayList);
        if (getSupertype().isAspect()) {
            arrayList.addAll(Arrays.asList(getSupertype().getDeclareParents()));
        }
        DeclareParents[] declareParentsArr = new DeclareParents[arrayList.size()];
        arrayList.toArray(declareParentsArr);
        return declareParentsArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public DeclarePrecedence[] getDeclarePrecedence() {
        Method[] declaredMethods;
        ArrayList arrayList = new ArrayList();
        if (this.clazz.isAnnotationPresent(szorg.mp4parser.aspectj.lang.annotation.DeclarePrecedence.class)) {
            arrayList.add(new DeclarePrecedenceImpl(((szorg.mp4parser.aspectj.lang.annotation.DeclarePrecedence) this.clazz.getAnnotation(szorg.mp4parser.aspectj.lang.annotation.DeclarePrecedence.class)).value(), this));
        }
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclarePrecedence.class)) {
                arrayList.add(new DeclarePrecedenceImpl(((ajcDeclarePrecedence) method.getAnnotation(ajcDeclarePrecedence.class)).value(), this));
            }
        }
        if (getSupertype().isAspect()) {
            arrayList.addAll(Arrays.asList(getSupertype().getDeclarePrecedence()));
        }
        DeclarePrecedence[] declarePrecedenceArr = new DeclarePrecedence[arrayList.size()];
        arrayList.toArray(declarePrecedenceArr);
        return declarePrecedenceArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public DeclareSoft[] getDeclareSofts() {
        Method[] declaredMethods;
        ArrayList arrayList = new ArrayList();
        for (Method method : this.clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ajcDeclareSoft.class)) {
                ajcDeclareSoft ajcdeclaresoft = (ajcDeclareSoft) method.getAnnotation(ajcDeclareSoft.class);
                arrayList.add(new DeclareSoftImpl(this, ajcdeclaresoft.pointcut(), ajcdeclaresoft.exceptionType()));
            }
        }
        if (getSupertype().isAspect()) {
            arrayList.addAll(Arrays.asList(getSupertype().getDeclareSofts()));
        }
        DeclareSoft[] declareSoftArr = new DeclareSoft[arrayList.size()];
        arrayList.toArray(declareSoftArr);
        return declareSoftArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Advice getDeclaredAdvice(String str) {
        Advice[] adviceArr;
        if (str.equals("")) {
            throw new IllegalArgumentException("use getAdvice(AdviceType...) instead for un-named advice");
        }
        if (this.declaredAdvice == null) {
            initDeclaredAdvice();
        }
        for (Advice advice : this.declaredAdvice) {
            if (advice.getName().equals(str)) {
                return advice;
            }
        }
        throw new NoSuchAdviceException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Advice[] getDeclaredAdvice(AdviceKind... adviceKindArr) {
        EnumSet enumSet;
        if (adviceKindArr.length == 0) {
            enumSet = EnumSet.allOf(AdviceKind.class);
        } else {
            EnumSet noneOf = EnumSet.noneOf(AdviceKind.class);
            noneOf.addAll(Arrays.asList(adviceKindArr));
            enumSet = noneOf;
        }
        return getDeclaredAdvice(enumSet);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<?>[] getDeclaredAjTypes() {
        return toAjTypeArray(this.clazz.getDeclaredClasses());
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return this.clazz.getDeclaredAnnotations();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Constructor getDeclaredConstructor(AjType<?>... ajTypeArr) {
        return this.clazz.getDeclaredConstructor(toClassArray(ajTypeArr));
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Constructor[] getDeclaredConstructors() {
        return this.clazz.getDeclaredConstructors();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Field getDeclaredField(String str) {
        Field declaredField = this.clazz.getDeclaredField(str);
        if (declaredField.getName().startsWith(ajcMagic)) {
            throw new NoSuchFieldException(str);
        }
        return declaredField;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Field[] getDeclaredFields() {
        Field[] declaredFields = this.clazz.getDeclaredFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : declaredFields) {
            if (!field.getName().startsWith(ajcMagic) && !field.isAnnotationPresent(DeclareWarning.class) && !field.isAnnotationPresent(DeclareError.class)) {
                arrayList.add(field);
            }
        }
        Field[] fieldArr = new Field[arrayList.size()];
        arrayList.toArray(fieldArr);
        return fieldArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeConstructorDeclaration getDeclaredITDConstructor(AjType<?> ajType, AjType<?>... ajTypeArr) {
        InterTypeConstructorDeclaration[] declaredITDConstructors;
        for (InterTypeConstructorDeclaration interTypeConstructorDeclaration : getDeclaredITDConstructors()) {
            try {
                if (interTypeConstructorDeclaration.getTargetType().equals(ajType)) {
                    AjType<?>[] parameterTypes = interTypeConstructorDeclaration.getParameterTypes();
                    if (parameterTypes.length == ajTypeArr.length) {
                        for (int i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes[i].equals(ajTypeArr[i])) {
                                break;
                            }
                        }
                        return interTypeConstructorDeclaration;
                    }
                    continue;
                } else {
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeConstructorDeclaration[] getDeclaredITDConstructors() {
        Method[] declaredMethods;
        if (this.declaredITDCons == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.clazz.getDeclaredMethods()) {
                if (method.getName().contains("ajc$postInterConstructor") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    arrayList.add(new InterTypeConstructorDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), method));
                }
            }
            this.declaredITDCons = new InterTypeConstructorDeclaration[arrayList.size()];
            arrayList.toArray(this.declaredITDCons);
        }
        return this.declaredITDCons;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeFieldDeclaration getDeclaredITDField(String str, AjType<?> ajType) {
        InterTypeFieldDeclaration[] declaredITDFields;
        for (InterTypeFieldDeclaration interTypeFieldDeclaration : getDeclaredITDFields()) {
            if (interTypeFieldDeclaration.getName().equals(str)) {
                try {
                    if (interTypeFieldDeclaration.getTargetType().equals(ajType)) {
                        return interTypeFieldDeclaration;
                    }
                } catch (ClassNotFoundException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchFieldException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeFieldDeclaration[] getDeclaredITDFields() {
        Method[] declaredMethods;
        List<InterTypeFieldDeclaration> arrayList = new ArrayList<>();
        if (this.declaredITDFields == null) {
            for (Method method : this.clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ajcITD.class) && method.getName().contains("ajc$interFieldInit")) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    try {
                        Method declaredMethod = this.clazz.getDeclaredMethod(method.getName().replace("FieldInit", "FieldGetDispatch"), method.getParameterTypes());
                        arrayList.add(new InterTypeFieldDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), AjTypeSystem.getAjType(declaredMethod.getReturnType()), declaredMethod.getGenericReturnType()));
                    } catch (NoSuchMethodException unused) {
                        throw new IllegalStateException("Can't find field get dispatch method for " + method.getName());
                    }
                }
            }
            addAnnotationStyleITDFields(arrayList, false);
            this.declaredITDFields = new InterTypeFieldDeclaration[arrayList.size()];
            arrayList.toArray(this.declaredITDFields);
        }
        return this.declaredITDFields;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeMethodDeclaration getDeclaredITDMethod(String str, AjType<?> ajType, AjType<?>... ajTypeArr) {
        InterTypeMethodDeclaration[] declaredITDMethods;
        for (InterTypeMethodDeclaration interTypeMethodDeclaration : getDeclaredITDMethods()) {
            try {
                if (interTypeMethodDeclaration.getName().equals(str) && interTypeMethodDeclaration.getTargetType().equals(ajType)) {
                    AjType<?>[] parameterTypes = interTypeMethodDeclaration.getParameterTypes();
                    if (parameterTypes.length == ajTypeArr.length) {
                        for (int i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes[i].equals(ajTypeArr[i])) {
                                break;
                            }
                        }
                        return interTypeMethodDeclaration;
                    }
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeMethodDeclaration[] getDeclaredITDMethods() {
        Method[] declaredMethods;
        if (this.declaredITDMethods == null) {
            List<InterTypeMethodDeclaration> arrayList = new ArrayList<>();
            for (Method method : this.clazz.getDeclaredMethods()) {
                if (method.getName().contains("ajc$interMethodDispatch1$") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    arrayList.add(new InterTypeMethodDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), method));
                }
            }
            addAnnotationStyleITDMethods(arrayList, false);
            this.declaredITDMethods = new InterTypeMethodDeclaration[arrayList.size()];
            arrayList.toArray(this.declaredITDMethods);
        }
        return this.declaredITDMethods;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Method getDeclaredMethod(String str, AjType<?>... ajTypeArr) {
        Method declaredMethod = this.clazz.getDeclaredMethod(str, toClassArray(ajTypeArr));
        if (isReallyAMethod(declaredMethod)) {
            return declaredMethod;
        }
        throw new NoSuchMethodException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Method[] getDeclaredMethods() {
        Method[] declaredMethods = this.clazz.getDeclaredMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : declaredMethods) {
            if (isReallyAMethod(method)) {
                arrayList.add(method);
            }
        }
        Method[] methodArr = new Method[arrayList.size()];
        arrayList.toArray(methodArr);
        return methodArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Pointcut getDeclaredPointcut(String str) {
        Pointcut[] declaredPointcuts;
        for (Pointcut pointcut : getDeclaredPointcuts()) {
            if (pointcut.getName().equals(str)) {
                return pointcut;
            }
        }
        throw new NoSuchPointcutException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Pointcut[] getDeclaredPointcuts() {
        Pointcut[] pointcutArr = this.declaredPointcuts;
        if (pointcutArr != null) {
            return pointcutArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Method method : this.clazz.getDeclaredMethods()) {
            Pointcut asPointcut = asPointcut(method);
            if (asPointcut != null) {
                arrayList.add(asPointcut);
            }
        }
        Pointcut[] pointcutArr2 = new Pointcut[arrayList.size()];
        arrayList.toArray(pointcutArr2);
        this.declaredPointcuts = pointcutArr2;
        return pointcutArr2;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<?> getDeclaringType() {
        Class<?> declaringClass = this.clazz.getDeclaringClass();
        if (declaringClass != null) {
            return new AjTypeImpl(declaringClass);
        }
        return null;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Constructor getEnclosingConstructor() {
        return this.clazz.getEnclosingConstructor();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Method getEnclosingMethod() {
        return this.clazz.getEnclosingMethod();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<?> getEnclosingType() {
        Class<?> enclosingClass = this.clazz.getEnclosingClass();
        if (enclosingClass != null) {
            return new AjTypeImpl(enclosingClass);
        }
        return null;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public T[] getEnumConstants() {
        return this.clazz.getEnumConstants();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Field getField(String str) {
        Field field = this.clazz.getField(str);
        if (field.getName().startsWith(ajcMagic)) {
            throw new NoSuchFieldException(str);
        }
        return field;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Field[] getFields() {
        Field[] fields = this.clazz.getFields();
        ArrayList arrayList = new ArrayList();
        for (Field field : fields) {
            if (!field.getName().startsWith(ajcMagic) && !field.isAnnotationPresent(DeclareWarning.class) && !field.isAnnotationPresent(DeclareError.class)) {
                arrayList.add(field);
            }
        }
        Field[] fieldArr = new Field[arrayList.size()];
        arrayList.toArray(fieldArr);
        return fieldArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Type getGenericSupertype() {
        return this.clazz.getGenericSuperclass();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeConstructorDeclaration getITDConstructor(AjType<?> ajType, AjType<?>... ajTypeArr) {
        InterTypeConstructorDeclaration[] iTDConstructors;
        for (InterTypeConstructorDeclaration interTypeConstructorDeclaration : getITDConstructors()) {
            try {
                if (interTypeConstructorDeclaration.getTargetType().equals(ajType)) {
                    AjType<?>[] parameterTypes = interTypeConstructorDeclaration.getParameterTypes();
                    if (parameterTypes.length == ajTypeArr.length) {
                        for (int i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes[i].equals(ajTypeArr[i])) {
                                break;
                            }
                        }
                        return interTypeConstructorDeclaration;
                    }
                    continue;
                } else {
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeConstructorDeclaration[] getITDConstructors() {
        Method[] methods;
        if (this.itdCons == null) {
            ArrayList arrayList = new ArrayList();
            for (Method method : this.clazz.getMethods()) {
                if (method.getName().contains("ajc$postInterConstructor") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (Modifier.isPublic(ajcitd.modifiers())) {
                        arrayList.add(new InterTypeConstructorDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), method));
                    }
                }
            }
            this.itdCons = new InterTypeConstructorDeclaration[arrayList.size()];
            arrayList.toArray(this.itdCons);
        }
        return this.itdCons;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeFieldDeclaration getITDField(String str, AjType<?> ajType) {
        InterTypeFieldDeclaration[] iTDFields;
        for (InterTypeFieldDeclaration interTypeFieldDeclaration : getITDFields()) {
            if (interTypeFieldDeclaration.getName().equals(str)) {
                try {
                    if (interTypeFieldDeclaration.getTargetType().equals(ajType)) {
                        return interTypeFieldDeclaration;
                    }
                } catch (ClassNotFoundException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchFieldException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeFieldDeclaration[] getITDFields() {
        Method[] methods;
        List<InterTypeFieldDeclaration> arrayList = new ArrayList<>();
        if (this.itdFields == null) {
            for (Method method : this.clazz.getMethods()) {
                if (method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (method.getName().contains("ajc$interFieldInit") && Modifier.isPublic(ajcitd.modifiers())) {
                        try {
                            Method declaredMethod = method.getDeclaringClass().getDeclaredMethod(method.getName().replace("FieldInit", "FieldGetDispatch"), method.getParameterTypes());
                            arrayList.add(new InterTypeFieldDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), AjTypeSystem.getAjType(declaredMethod.getReturnType()), declaredMethod.getGenericReturnType()));
                        } catch (NoSuchMethodException unused) {
                            throw new IllegalStateException("Can't find field get dispatch method for " + method.getName());
                        }
                    }
                }
            }
            addAnnotationStyleITDFields(arrayList, true);
            this.itdFields = new InterTypeFieldDeclaration[arrayList.size()];
            arrayList.toArray(this.itdFields);
        }
        return this.itdFields;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeMethodDeclaration getITDMethod(String str, AjType<?> ajType, AjType<?>... ajTypeArr) {
        InterTypeMethodDeclaration[] iTDMethods;
        for (InterTypeMethodDeclaration interTypeMethodDeclaration : getITDMethods()) {
            try {
                if (interTypeMethodDeclaration.getName().equals(str) && interTypeMethodDeclaration.getTargetType().equals(ajType)) {
                    AjType<?>[] parameterTypes = interTypeMethodDeclaration.getParameterTypes();
                    if (parameterTypes.length == ajTypeArr.length) {
                        for (int i = 0; i < parameterTypes.length; i++) {
                            if (!parameterTypes[i].equals(ajTypeArr[i])) {
                                break;
                            }
                        }
                        return interTypeMethodDeclaration;
                    }
                    continue;
                }
            } catch (ClassNotFoundException unused) {
            }
        }
        throw new NoSuchMethodException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public InterTypeMethodDeclaration[] getITDMethods() {
        Method[] declaredMethods;
        if (this.itdMethods == null) {
            List<InterTypeMethodDeclaration> arrayList = new ArrayList<>();
            for (Method method : this.clazz.getDeclaredMethods()) {
                if (method.getName().contains("ajc$interMethod$") && method.isAnnotationPresent(ajcITD.class)) {
                    ajcITD ajcitd = (ajcITD) method.getAnnotation(ajcITD.class);
                    if (Modifier.isPublic(ajcitd.modifiers())) {
                        arrayList.add(new InterTypeMethodDeclarationImpl(this, ajcitd.targetType(), ajcitd.modifiers(), ajcitd.name(), method));
                    }
                }
            }
            addAnnotationStyleITDMethods(arrayList, true);
            this.itdMethods = new InterTypeMethodDeclaration[arrayList.size()];
            arrayList.toArray(this.itdMethods);
        }
        return this.itdMethods;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<?>[] getInterfaces() {
        return toAjTypeArray(this.clazz.getInterfaces());
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Class<T> getJavaClass() {
        return this.clazz;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Method getMethod(String str, AjType<?>... ajTypeArr) {
        Method method = this.clazz.getMethod(str, toClassArray(ajTypeArr));
        if (isReallyAMethod(method)) {
            return method;
        }
        throw new NoSuchMethodException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Method[] getMethods() {
        Method[] methods = this.clazz.getMethods();
        ArrayList arrayList = new ArrayList();
        for (Method method : methods) {
            if (isReallyAMethod(method)) {
                arrayList.add(method);
            }
        }
        Method[] methodArr = new Method[arrayList.size()];
        arrayList.toArray(methodArr);
        return methodArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public int getModifiers() {
        return this.clazz.getModifiers();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public String getName() {
        return this.clazz.getName();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Package getPackage() {
        return this.clazz.getPackage();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public PerClause getPerClause() {
        if (isAspect()) {
            String value = ((Aspect) this.clazz.getAnnotation(Aspect.class)).value();
            if (value.equals("")) {
                return getSupertype().isAspect() ? getSupertype().getPerClause() : new PerClauseImpl(PerClauseKind.SINGLETON);
            } else if (value.startsWith("perthis(")) {
                return new PointcutBasedPerClauseImpl(PerClauseKind.PERTHIS, value.substring(8, value.length() - 1));
            } else {
                if (value.startsWith("pertarget(")) {
                    return new PointcutBasedPerClauseImpl(PerClauseKind.PERTARGET, value.substring(10, value.length() - 1));
                }
                if (value.startsWith("percflow(")) {
                    return new PointcutBasedPerClauseImpl(PerClauseKind.PERCFLOW, value.substring(9, value.length() - 1));
                }
                if (value.startsWith("percflowbelow(")) {
                    return new PointcutBasedPerClauseImpl(PerClauseKind.PERCFLOWBELOW, value.substring(14, value.length() - 1));
                }
                if (value.startsWith("pertypewithin")) {
                    return new TypePatternBasedPerClauseImpl(PerClauseKind.PERTYPEWITHIN, value.substring(14, value.length() - 1));
                }
                throw new IllegalStateException("Per-clause not recognized: " + value);
            }
        }
        return null;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Pointcut getPointcut(String str) {
        Pointcut[] pointcuts;
        for (Pointcut pointcut : getPointcuts()) {
            if (pointcut.getName().equals(str)) {
                return pointcut;
            }
        }
        throw new NoSuchPointcutException(str);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public Pointcut[] getPointcuts() {
        Pointcut[] pointcutArr = this.pointcuts;
        if (pointcutArr != null) {
            return pointcutArr;
        }
        ArrayList arrayList = new ArrayList();
        for (Method method : this.clazz.getMethods()) {
            Pointcut asPointcut = asPointcut(method);
            if (asPointcut != null) {
                arrayList.add(asPointcut);
            }
        }
        Pointcut[] pointcutArr2 = new Pointcut[arrayList.size()];
        arrayList.toArray(pointcutArr2);
        this.pointcuts = pointcutArr2;
        return pointcutArr2;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public AjType<? super T> getSupertype() {
        Class<? super T> superclass = this.clazz.getSuperclass();
        if (superclass == null) {
            return null;
        }
        return new AjTypeImpl(superclass);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public TypeVariable<Class<T>>[] getTypeParameters() {
        return this.clazz.getTypeParameters();
    }

    public int hashCode() {
        return this.clazz.hashCode();
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
        return this.clazz.isAnnotationPresent(cls);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isArray() {
        return this.clazz.isArray();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isAspect() {
        return this.clazz.getAnnotation(Aspect.class) != null;
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isEnum() {
        return this.clazz.isEnum();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isInstance(Object obj) {
        return this.clazz.isInstance(obj);
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isInterface() {
        return this.clazz.isInterface();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isLocalClass() {
        return this.clazz.isLocalClass() && !isAspect();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isMemberAspect() {
        return this.clazz.isMemberClass() && isAspect();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isMemberClass() {
        return this.clazz.isMemberClass() && !isAspect();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isPrimitive() {
        return this.clazz.isPrimitive();
    }

    @Override // szorg.mp4parser.aspectj.lang.reflect.AjType
    public boolean isPrivileged() {
        return isAspect() && this.clazz.isAnnotationPresent(ajcPrivileged.class);
    }

    public String toString() {
        return getName();
    }
}
