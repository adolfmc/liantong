package szorg.mp4parser.aspectj.runtime.reflect;

import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.lang.ProceedingJoinPoint;
import szorg.mp4parser.aspectj.lang.Signature;
import szorg.mp4parser.aspectj.lang.reflect.SourceLocation;
import szorg.mp4parser.aspectj.runtime.internal.AroundClosure;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class JoinPointImpl implements ProceedingJoinPoint {
    Object _this;
    private AroundClosure arc;
    Object[] args;
    JoinPoint.StaticPart staticPart;
    Object target;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class EnclosingStaticPartImpl extends StaticPartImpl implements JoinPoint.EnclosingStaticPart {
        public EnclosingStaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            super(i, str, signature, sourceLocation);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class StaticPartImpl implements JoinPoint.StaticPart {

        /* renamed from: id */
        private int f27687id;
        String kind;
        Signature signature;
        SourceLocation sourceLocation;

        public StaticPartImpl(int i, String str, Signature signature, SourceLocation sourceLocation) {
            this.kind = str;
            this.signature = signature;
            this.sourceLocation = sourceLocation;
            this.f27687id = i;
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public int getId() {
            return this.f27687id;
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public String getKind() {
            return this.kind;
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public Signature getSignature() {
            return this.signature;
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public SourceLocation getSourceLocation() {
            return this.sourceLocation;
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public final String toLongString() {
            return toString(StringMaker.longStringMaker);
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public final String toShortString() {
            return toString(StringMaker.shortStringMaker);
        }

        @Override // szorg.mp4parser.aspectj.lang.JoinPoint.StaticPart
        public final String toString() {
            return toString(StringMaker.middleStringMaker);
        }

        String toString(StringMaker stringMaker) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(stringMaker.makeKindName(getKind()));
            stringBuffer.append("(");
            stringBuffer.append(((SignatureImpl) getSignature()).toString(stringMaker));
            stringBuffer.append(")");
            return stringBuffer.toString();
        }
    }

    public JoinPointImpl(JoinPoint.StaticPart staticPart, Object obj, Object obj2, Object[] objArr) {
        this.staticPart = staticPart;
        this._this = obj;
        this.target = obj2;
        this.args = objArr;
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public Object[] getArgs() {
        if (this.args == null) {
            this.args = new Object[0];
        }
        Object[] objArr = this.args;
        Object[] objArr2 = new Object[objArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public String getKind() {
        return this.staticPart.getKind();
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public Signature getSignature() {
        return this.staticPart.getSignature();
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public SourceLocation getSourceLocation() {
        return this.staticPart.getSourceLocation();
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public JoinPoint.StaticPart getStaticPart() {
        return this.staticPart;
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public Object getTarget() {
        return this.target;
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public Object getThis() {
        return this._this;
    }

    @Override // szorg.mp4parser.aspectj.lang.ProceedingJoinPoint
    public Object proceed() {
        AroundClosure aroundClosure = this.arc;
        if (aroundClosure == null) {
            return null;
        }
        return aroundClosure.run(aroundClosure.getState());
    }

    @Override // szorg.mp4parser.aspectj.lang.ProceedingJoinPoint
    public Object proceed(Object[] objArr) {
        AroundClosure aroundClosure = this.arc;
        if (aroundClosure == null) {
            return null;
        }
        int flags = aroundClosure.getFlags();
        int i = 1048576 & flags;
        int i2 = 1;
        boolean z = (65536 & flags) != 0;
        int i3 = (flags & 4096) != 0 ? 1 : 0;
        int i4 = (flags & 256) != 0 ? 1 : 0;
        boolean z2 = (flags & 16) != 0;
        boolean z3 = (flags & 1) != 0;
        Object[] state = this.arc.getState();
        int i5 = i3 + 0 + ((!z2 || z) ? 0 : 1);
        if (i3 == 0 || i4 == 0) {
            i2 = 0;
        } else {
            state[0] = objArr[0];
        }
        if (z2 && z3) {
            if (z) {
                i2 = i4 + 1;
                state[0] = objArr[i4];
            } else {
                i2 = i3 + 1;
                state[i3] = objArr[i3];
            }
        }
        for (int i6 = i2; i6 < objArr.length; i6++) {
            state[(i6 - i2) + i5] = objArr[i6];
        }
        return this.arc.run(state);
    }

    @Override // szorg.mp4parser.aspectj.lang.ProceedingJoinPoint
    public void set$AroundClosure(AroundClosure aroundClosure) {
        this.arc = aroundClosure;
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public final String toLongString() {
        return this.staticPart.toLongString();
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public final String toShortString() {
        return this.staticPart.toShortString();
    }

    @Override // szorg.mp4parser.aspectj.lang.JoinPoint
    public final String toString() {
        return this.staticPart.toString();
    }
}
