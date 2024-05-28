package org.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Predicate<T> {
    Predicate<T> and(Predicate<? super T> predicate);

    Predicate<T> negate();

    /* renamed from: or */
    Predicate<T> mo199or(Predicate<? super T> predicate);

    boolean test(T t);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.webrtc.Predicate$-CC  reason: invalid class name */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static Predicate $default$or(final Predicate predicate, final Predicate predicate2) {
            return new Predicate<T>() { // from class: org.webrtc.Predicate.1
                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate3) {
                    return CC.$default$and(this, predicate3);
                }

                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> negate() {
                    return CC.$default$negate(this);
                }

                @Override // org.webrtc.Predicate
                /* renamed from: or */
                public /* synthetic */ Predicate<T> mo199or(Predicate<? super T> predicate3) {
                    return CC.$default$or(this, predicate3);
                }

                @Override // org.webrtc.Predicate
                public boolean test(T t) {
                    return Predicate.this.test(t) || predicate2.test(t);
                }
            };
        }

        public static Predicate $default$and(final Predicate predicate, final Predicate predicate2) {
            return new Predicate<T>() { // from class: org.webrtc.Predicate.2
                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate3) {
                    return CC.$default$and(this, predicate3);
                }

                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> negate() {
                    return CC.$default$negate(this);
                }

                @Override // org.webrtc.Predicate
                /* renamed from: or */
                public /* synthetic */ Predicate<T> mo199or(Predicate<? super T> predicate3) {
                    return CC.$default$or(this, predicate3);
                }

                @Override // org.webrtc.Predicate
                public boolean test(T t) {
                    return Predicate.this.test(t) && predicate2.test(t);
                }
            };
        }

        public static Predicate $default$negate(final Predicate predicate) {
            return new Predicate<T>() { // from class: org.webrtc.Predicate.3
                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> and(Predicate<? super T> predicate2) {
                    return CC.$default$and(this, predicate2);
                }

                @Override // org.webrtc.Predicate
                public /* synthetic */ Predicate<T> negate() {
                    return CC.$default$negate(this);
                }

                @Override // org.webrtc.Predicate
                /* renamed from: or */
                public /* synthetic */ Predicate<T> mo199or(Predicate<? super T> predicate2) {
                    return CC.$default$or(this, predicate2);
                }

                @Override // org.webrtc.Predicate
                public boolean test(T t) {
                    return !Predicate.this.test(t);
                }
            };
        }
    }
}
