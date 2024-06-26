package kotlin.reflect;

import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: KType.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\n8&X§\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010¨\u0006\u0011"}, m1890d2 = {"Lkotlin/reflect/KType;", "Lkotlin/reflect/KAnnotatedElement;", "arguments", "", "Lkotlin/reflect/KTypeProjection;", "arguments$annotations", "()V", "getArguments", "()Ljava/util/List;", "classifier", "Lkotlin/reflect/KClassifier;", "classifier$annotations", "getClassifier", "()Lkotlin/reflect/KClassifier;", "isMarkedNullable", "", "()Z", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface KType extends KAnnotatedElement {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: KType.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1889k = 3, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void arguments$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void classifier$annotations() {
        }
    }

    @NotNull
    List<KTypeProjection> getArguments();

    @Nullable
    KClassifier getClassifier();

    boolean isMarkedNullable();
}
