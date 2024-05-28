package org.codehaus.jackson.map.introspect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.introspect.VisibilityChecker;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface VisibilityChecker<T extends VisibilityChecker<T>> {
    boolean isCreatorVisible(Member member);

    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isFieldVisible(Field field);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isGetterVisible(Method method);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(Method method);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(Method method);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    T with(JsonAutoDetect.Visibility visibility);

    T with(JsonAutoDetect jsonAutoDetect);

    T withCreatorVisibility(JsonAutoDetect.Visibility visibility);

    T withFieldVisibility(JsonAutoDetect.Visibility visibility);

    T withGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withIsGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withSetterVisibility(JsonAutoDetect.Visibility visibility);

    T withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    @JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility = JsonAutoDetect.Visibility.ANY)
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Std implements VisibilityChecker<Std> {
        protected static final Std DEFAULT = new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
        protected final JsonAutoDetect.Visibility _creatorMinLevel;
        protected final JsonAutoDetect.Visibility _fieldMinLevel;
        protected final JsonAutoDetect.Visibility _getterMinLevel;
        protected final JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final JsonAutoDetect.Visibility _setterMinLevel;

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect jsonAutoDetect) {
            JsonMethod[] value = jsonAutoDetect.value();
            this._getterMinLevel = hasMethod(value, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._isGetterMinLevel = hasMethod(value, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._setterMinLevel = hasMethod(value, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE;
            this._creatorMinLevel = hasMethod(value, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE;
            this._fieldMinLevel = hasMethod(value, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE;
        }

        public Std(JsonAutoDetect.Visibility visibility, JsonAutoDetect.Visibility visibility2, JsonAutoDetect.Visibility visibility3, JsonAutoDetect.Visibility visibility4, JsonAutoDetect.Visibility visibility5) {
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility2;
            this._setterMinLevel = visibility3;
            this._creatorMinLevel = visibility4;
            this._fieldMinLevel = visibility5;
        }

        public Std(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                Std std = DEFAULT;
                this._getterMinLevel = std._getterMinLevel;
                this._isGetterMinLevel = std._isGetterMinLevel;
                this._setterMinLevel = std._setterMinLevel;
                this._creatorMinLevel = std._creatorMinLevel;
                this._fieldMinLevel = std._fieldMinLevel;
                return;
            }
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility;
            this._setterMinLevel = visibility;
            this._creatorMinLevel = visibility;
            this._fieldMinLevel = visibility;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std with(JsonAutoDetect jsonAutoDetect) {
            if (jsonAutoDetect == null) {
                return this;
            }
            JsonMethod[] value = jsonAutoDetect.value();
            return withGetterVisibility(hasMethod(value, JsonMethod.GETTER) ? jsonAutoDetect.getterVisibility() : JsonAutoDetect.Visibility.NONE).withIsGetterVisibility(hasMethod(value, JsonMethod.IS_GETTER) ? jsonAutoDetect.isGetterVisibility() : JsonAutoDetect.Visibility.NONE).withSetterVisibility(hasMethod(value, JsonMethod.SETTER) ? jsonAutoDetect.setterVisibility() : JsonAutoDetect.Visibility.NONE).withCreatorVisibility(hasMethod(value, JsonMethod.CREATOR) ? jsonAutoDetect.creatorVisibility() : JsonAutoDetect.Visibility.NONE).withFieldVisibility(hasMethod(value, JsonMethod.FIELD) ? jsonAutoDetect.fieldVisibility() : JsonAutoDetect.Visibility.NONE);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std with(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                return DEFAULT;
            }
            return new Std(visibility);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withVisibility(JsonMethod jsonMethod, JsonAutoDetect.Visibility visibility) {
            switch (jsonMethod) {
                case GETTER:
                    return withGetterVisibility(visibility);
                case SETTER:
                    return withSetterVisibility(visibility);
                case CREATOR:
                    return withCreatorVisibility(visibility);
                case FIELD:
                    return withFieldVisibility(visibility);
                case IS_GETTER:
                    return withIsGetterVisibility(visibility);
                case ALL:
                    return with(visibility);
                default:
                    return this;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withGetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._getterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._getterMinLevel == visibility2 ? this : new Std(visibility2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withIsGetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._isGetterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._isGetterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, visibility2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withSetterVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._setterMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._setterMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, visibility2, this._creatorMinLevel, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withCreatorVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._creatorMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._creatorMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, visibility2, this._fieldMinLevel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public Std withFieldVisibility(JsonAutoDetect.Visibility visibility) {
            if (visibility == JsonAutoDetect.Visibility.DEFAULT) {
                visibility = DEFAULT._fieldMinLevel;
            }
            JsonAutoDetect.Visibility visibility2 = visibility;
            return this._fieldMinLevel == visibility2 ? this : new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, visibility2);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(Member member) {
            return this._creatorMinLevel.isVisible(member);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isCreatorVisible(AnnotatedMember annotatedMember) {
            return isCreatorVisible(annotatedMember.getMember());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(Field field) {
            return this._fieldMinLevel.isVisible(field);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isFieldVisible(AnnotatedField annotatedField) {
            return isFieldVisible(annotatedField.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(Method method) {
            return this._getterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isGetterVisible(AnnotatedMethod annotatedMethod) {
            return isGetterVisible(annotatedMethod.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(Method method) {
            return this._isGetterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isIsGetterVisible(AnnotatedMethod annotatedMethod) {
            return isIsGetterVisible(annotatedMethod.getAnnotated());
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(Method method) {
            return this._setterMinLevel.isVisible(method);
        }

        @Override // org.codehaus.jackson.map.introspect.VisibilityChecker
        public boolean isSetterVisible(AnnotatedMethod annotatedMethod) {
            return isSetterVisible(annotatedMethod.getAnnotated());
        }

        private static boolean hasMethod(JsonMethod[] jsonMethodArr, JsonMethod jsonMethod) {
            for (JsonMethod jsonMethod2 : jsonMethodArr) {
                if (jsonMethod2 == jsonMethod || jsonMethod2 == JsonMethod.ALL) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "[Visibility: getter: " + this._getterMinLevel + ", isGetter: " + this._isGetterMinLevel + ", setter: " + this._setterMinLevel + ", creator: " + this._creatorMinLevel + ", field: " + this._fieldMinLevel + "]";
        }
    }
}
