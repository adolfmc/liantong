package org.codehaus.jackson.map.introspect;

import org.codehaus.jackson.map.BeanPropertyDefinition;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected Node<AnnotatedParameter> _ctorParameters;
    protected Node<AnnotatedField> _fields;
    protected Node<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected Node<AnnotatedMethod> _setters;

    public POJOPropertyBuilder(String str) {
        this._internalName = str;
        this._name = str;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        this._internalName = pOJOPropertyBuilder._internalName;
        this._name = str;
        this._fields = pOJOPropertyBuilder._fields;
        this._ctorParameters = pOJOPropertyBuilder._ctorParameters;
        this._getters = pOJOPropertyBuilder._getters;
        this._setters = pOJOPropertyBuilder._setters;
    }

    public POJOPropertyBuilder withName(String str) {
        return new POJOPropertyBuilder(this, str);
    }

    @Override // java.lang.Comparable
    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder.getName());
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition, org.codehaus.jackson.map.util.Named
    public String getName() {
        return this._name;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public String getInternalName() {
        return this._internalName;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean isExplicitlyIncluded() {
        return anyExplicitNames();
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasGetter() {
        return this._getters != null;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasSetter() {
        return this._setters != null;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasField() {
        return this._fields != null;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getAccessor() {
        AnnotatedMethod getter = getGetter();
        return getter == null ? getField() : getter;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMember getMutator() {
        AnnotatedParameter constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            AnnotatedMethod setter = getSetter();
            return setter == null ? getField() : setter;
        }
        return constructorParameter;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getGetter() {
        Node<AnnotatedMethod> node = this._getters;
        if (node == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = node.value;
        for (Node node2 = this._getters.next; node2 != null; node2 = node2.next) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node2.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + annotatedMethod.getFullName() + " vs " + annotatedMethod2.getFullName());
        }
        return annotatedMethod;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedMethod getSetter() {
        Node<AnnotatedMethod> node = this._setters;
        if (node == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod = node.value;
        for (Node node2 = this._setters.next; node2 != null; node2 = node2.next) {
            AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) node2.value;
            Class<?> declaringClass = annotatedMethod.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedMethod = annotatedMethod2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Conflicting setter definitions for property \"" + getName() + "\": " + annotatedMethod.getFullName() + " vs " + annotatedMethod2.getFullName());
        }
        return annotatedMethod;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedField getField() {
        Node<AnnotatedField> node = this._fields;
        if (node == null) {
            return null;
        }
        AnnotatedField annotatedField = node.value;
        for (Node node2 = this._fields.next; node2 != null; node2 = node2.next) {
            AnnotatedField annotatedField2 = (AnnotatedField) node2.value;
            Class<?> declaringClass = annotatedField.getDeclaringClass();
            Class<?> declaringClass2 = annotatedField2.getDeclaringClass();
            if (declaringClass != declaringClass2) {
                if (declaringClass.isAssignableFrom(declaringClass2)) {
                    annotatedField = annotatedField2;
                } else if (declaringClass2.isAssignableFrom(declaringClass)) {
                }
            }
            throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + annotatedField.getFullName() + " vs " + annotatedField2.getFullName());
        }
        return annotatedField;
    }

    @Override // org.codehaus.jackson.map.BeanPropertyDefinition
    public AnnotatedParameter getConstructorParameter() {
        Node node = this._ctorParameters;
        if (node == null) {
            return null;
        }
        while (!(((AnnotatedParameter) node.value).getOwner() instanceof AnnotatedConstructor)) {
            node = node.next;
            if (node == null) {
                return this._ctorParameters.value;
            }
        }
        return (AnnotatedParameter) node.value;
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        this._fields = new Node<>(annotatedField, this._fields, str, z, z2);
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        this._ctorParameters = new Node<>(annotatedParameter, this._ctorParameters, str, z, z2);
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._getters = new Node<>(annotatedMethod, this._getters, str, z, z2);
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        this._setters = new Node<>(annotatedMethod, this._setters, str, z, z2);
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        this._fields = merge(this._fields, pOJOPropertyBuilder._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder._setters);
    }

    private static <T> Node<T> merge(Node<T> node, Node<T> node2) {
        return node == null ? node2 : node2 == null ? node : node.append(node2);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    public void removeNonVisible() {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            Node<AnnotatedMethod> node = this._getters;
            if (node != null) {
                AnnotationMap _mergeAnnotations = _mergeAnnotations(0, node, this._fields, this._ctorParameters, this._setters);
                Node<AnnotatedMethod> node2 = this._getters;
                this._getters = node2.withValue(node2.value.withAnnotations(_mergeAnnotations));
                return;
            }
            Node<AnnotatedField> node3 = this._fields;
            if (node3 != null) {
                AnnotationMap _mergeAnnotations2 = _mergeAnnotations(0, node3, this._ctorParameters, this._setters);
                Node<AnnotatedField> node4 = this._fields;
                this._fields = node4.withValue(node4.value.withAnnotations(_mergeAnnotations2));
                return;
            }
            return;
        }
        Node<AnnotatedParameter> node5 = this._ctorParameters;
        if (node5 != null) {
            AnnotationMap _mergeAnnotations3 = _mergeAnnotations(0, node5, this._setters, this._fields, this._getters);
            Node<AnnotatedParameter> node6 = this._ctorParameters;
            this._ctorParameters = node6.withValue(node6.value.withAnnotations(_mergeAnnotations3));
            return;
        }
        Node<AnnotatedMethod> node7 = this._setters;
        if (node7 != null) {
            AnnotationMap _mergeAnnotations4 = _mergeAnnotations(0, node7, this._fields, this._getters);
            Node<AnnotatedMethod> node8 = this._setters;
            this._setters = node8.withValue(node8.value.withAnnotations(_mergeAnnotations4));
            return;
        }
        Node<AnnotatedField> node9 = this._fields;
        if (node9 != null) {
            AnnotationMap _mergeAnnotations5 = _mergeAnnotations(0, node9, this._getters);
            Node<AnnotatedField> node10 = this._fields;
            this._fields = node10.withValue(node10.value.withAnnotations(_mergeAnnotations5));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, Node<? extends AnnotatedMember>... nodeArr) {
        AnnotationMap allAnnotations = ((AnnotatedMember) nodeArr[i].value).getAllAnnotations();
        while (true) {
            i++;
            if (i >= nodeArr.length) {
                return allAnnotations;
            }
            if (nodeArr[i] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i, nodeArr));
            }
        }
    }

    private <T> Node<T> _removeIgnored(Node<T> node) {
        return node == null ? node : node.withoutIgnored();
    }

    private <T> Node<T> _removeNonVisible(Node<T> node) {
        return node == null ? node : node.withoutNonVisible();
    }

    private <T> Node<T> _trimByVisibility(Node<T> node) {
        return node == null ? node : node.trimByVisibility();
    }

    public boolean anyExplicitNames() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    private <T> boolean _anyExplicitNames(Node<T> node) {
        while (node != null) {
            if (node.explicitName != null && node.explicitName.length() > 0) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(Node<T> node) {
        while (node != null) {
            if (node.isVisible) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anyDeserializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    public boolean anySerializeIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters);
    }

    private <T> boolean _anyIgnorals(Node<T> node) {
        while (node != null) {
            if (node.isMarkedIgnored) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public String findNewName() {
        Node<? extends AnnotatedMember> findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, null))));
        if (findRenamed == null) {
            return null;
        }
        return findRenamed.explicitName;
    }

    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r4 = r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends org.codehaus.jackson.map.introspect.AnnotatedMember> findRenamed(org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends org.codehaus.jackson.map.introspect.AnnotatedMember> r4, org.codehaus.jackson.map.introspect.POJOPropertyBuilder.Node<? extends org.codehaus.jackson.map.introspect.AnnotatedMember> r5) {
        /*
            r3 = this;
        L0:
            if (r4 == 0) goto L5b
            java.lang.String r0 = r4.explicitName
            if (r0 != 0) goto L7
            goto L1c
        L7:
            java.lang.String r1 = r3._name
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L10
            goto L1c
        L10:
            if (r5 != 0) goto L14
            r5 = r4
            goto L1c
        L14:
            java.lang.String r1 = r5.explicitName
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L1f
        L1c:
            org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node<T> r4 = r4.next
            goto L0
        L1f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Conflicting property name definitions: '"
            r1.append(r2)
            java.lang.String r2 = r5.explicitName
            r1.append(r2)
            java.lang.String r2 = "' (for "
            r1.append(r2)
            T r5 = r5.value
            r1.append(r5)
            java.lang.String r5 = ") vs '"
            r1.append(r5)
            java.lang.String r5 = r4.explicitName
            r1.append(r5)
            java.lang.String r5 = "' (for "
            r1.append(r5)
            T r4 = r4.value
            r1.append(r4)
            java.lang.String r4 = ")"
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            r0.<init>(r4)
            throw r0
        L5b:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.introspect.POJOPropertyBuilder.findRenamed(org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node, org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node):org.codehaus.jackson.map.introspect.POJOPropertyBuilder$Node");
    }

    public String toString() {
        return "[Property '" + this._name + "'; ctors: " + this._ctorParameters + ", field(s): " + this._fields + ", getter(s): " + this._getters + ", setter(s): " + this._setters + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Node<T> {
        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Node<T> next;
        public final T value;

        public Node(T t, Node<T> node, String str, boolean z, boolean z2) {
            this.value = t;
            this.next = node;
            if (str == null) {
                this.explicitName = null;
            } else {
                this.explicitName = str.length() != 0 ? str : null;
            }
            this.isVisible = z;
            this.isMarkedIgnored = z2;
        }

        public Node<T> withValue(T t) {
            return t == this.value ? this : new Node<>(t, this.next, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node<T> withNext(Node<T> node) {
            return node == this.next ? this : new Node<>(this.value, node, this.explicitName, this.isVisible, this.isMarkedIgnored);
        }

        public Node<T> withoutIgnored() {
            Node<T> withoutIgnored;
            if (this.isMarkedIgnored) {
                Node<T> node = this.next;
                if (node == null) {
                    return null;
                }
                return node.withoutIgnored();
            }
            Node<T> node2 = this.next;
            return (node2 == null || (withoutIgnored = node2.withoutIgnored()) == this.next) ? this : withNext(withoutIgnored);
        }

        public Node<T> withoutNonVisible() {
            Node<T> node = this.next;
            Node<T> withoutNonVisible = node == null ? null : node.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Node<T> append(Node<T> node) {
            Node<T> node2 = this.next;
            if (node2 == null) {
                return withNext(node);
            }
            return withNext(node2.append(node));
        }

        public Node<T> trimByVisibility() {
            Node<T> node = this.next;
            if (node == null) {
                return this;
            }
            Node<T> trimByVisibility = node.trimByVisibility();
            if (this.explicitName != null) {
                if (trimByVisibility.explicitName == null) {
                    return withNext(null);
                }
                return withNext(trimByVisibility);
            } else if (trimByVisibility.explicitName != null) {
                return trimByVisibility;
            } else {
                boolean z = this.isVisible;
                if (z == trimByVisibility.isVisible) {
                    return withNext(trimByVisibility);
                }
                return z ? withNext(null) : trimByVisibility;
            }
        }

        public String toString() {
            String str = this.value.toString() + "[visible=" + this.isVisible + "]";
            if (this.next != null) {
                return str + ", " + this.next.toString();
            }
            return str;
        }
    }
}
