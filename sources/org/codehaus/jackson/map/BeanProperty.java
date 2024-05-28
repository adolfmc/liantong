package org.codehaus.jackson.map;

import java.lang.annotation.Annotation;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.map.util.Named;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface BeanProperty extends Named {
    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    @Override // org.codehaus.jackson.map.util.Named
    String getName();

    JavaType getType();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;

        public Std(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember) {
            this._name = str;
            this._type = javaType;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        public Std withType(JavaType javaType) {
            return new Std(this._name, javaType, this._contextAnnotations, this._member);
        }

        @Override // org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            return (A) this._member.getAnnotation(cls);
        }

        @Override // org.codehaus.jackson.map.BeanProperty
        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            Annotations annotations = this._contextAnnotations;
            if (annotations == null) {
                return null;
            }
            return (A) annotations.get(cls);
        }

        @Override // org.codehaus.jackson.map.BeanProperty, org.codehaus.jackson.map.util.Named
        public String getName() {
            return this._name;
        }

        @Override // org.codehaus.jackson.map.BeanProperty
        public JavaType getType() {
            return this._type;
        }

        @Override // org.codehaus.jackson.map.BeanProperty
        public AnnotatedMember getMember() {
            return this._member;
        }
    }
}
