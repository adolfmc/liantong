package com.sinovatech.unicom.separatemodule.simbox.bean;

import com.sinovatech.unicom.separatemodule.simbox.encryp.ResultExt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: B4.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\b\"\u0004\b\u0011\u0010\n¨\u0006\u0016"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/simbox/bean/B4;", "", "title", "", "remark", "(Ljava/lang/String;Ljava/lang/String;)V", "recId", "getRecId", "()Ljava/lang/String;", "setRecId", "(Ljava/lang/String;)V", "getRemark", "setRemark", "time", "getTime", "setTime", "getTitle", "setTitle", "getB4Bytes", "", "getB4Update", "Companion", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.sinovatech.unicom.separatemodule.simbox.bean.B4 */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class C9466B4 {
    public static final Companion Companion = new Companion(null);
    private static int end = 1;
    private static int start;
    @NotNull
    private String recId;
    @NotNull
    private String remark;
    @NotNull
    private String time;
    @NotNull
    private String title;

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.title = str;
    }

    @NotNull
    public final String getRemark() {
        return this.remark;
    }

    public final void setRemark(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.remark = str;
    }

    @NotNull
    public final String getTime() {
        return this.time;
    }

    public final void setTime(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.time = str;
    }

    @NotNull
    public final String getRecId() {
        return this.recId;
    }

    public final void setRecId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.recId = str;
    }

    public C9466B4(@NotNull String title, @NotNull String remark) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(remark, "remark");
        this.time = "";
        this.recId = "";
        this.title = title;
        this.remark = remark;
    }

    @NotNull
    public final byte[] getB4Bytes() {
        byte[] bArr = new byte[1];
        String str = this.title;
        Charset charset = StandardCharsets.UTF_16LE;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.UTF_16LE");
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            bArr[0] = (byte) (bytes.length & 255);
            String str2 = this.title;
            Charset charset2 = StandardCharsets.UTF_16LE;
            Intrinsics.checkExpressionValueIsNotNull(charset2, "StandardCharsets.UTF_16LE");
            if (str2 != null) {
                byte[] bytes2 = str2.getBytes(charset2);
                Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
                byte[] plus = ArraysKt.plus(bArr, bytes2);
                byte[] bArr2 = new byte[1];
                String str3 = this.remark;
                Charset charset3 = StandardCharsets.UTF_16LE;
                Intrinsics.checkExpressionValueIsNotNull(charset3, "StandardCharsets.UTF_16LE");
                if (str3 != null) {
                    byte[] bytes3 = str3.getBytes(charset3);
                    Intrinsics.checkExpressionValueIsNotNull(bytes3, "(this as java.lang.String).getBytes(charset)");
                    bArr2[0] = (byte) (bytes3.length & 255);
                    String str4 = this.remark;
                    Charset charset4 = StandardCharsets.UTF_16LE;
                    Intrinsics.checkExpressionValueIsNotNull(charset4, "StandardCharsets.UTF_16LE");
                    if (str4 != null) {
                        byte[] bytes4 = str4.getBytes(charset4);
                        Intrinsics.checkExpressionValueIsNotNull(bytes4, "(this as java.lang.String).getBytes(charset)");
                        return ArraysKt.plus(plus, ArraysKt.plus(bArr2, bytes4));
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    public final byte[] getB4Update() {
        return ArraysKt.plus(ResultExt.toBytes(this.time), getB4Bytes());
    }

    /* compiled from: B4.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\rJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0015"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/simbox/bean/B4$Companion;", "", "()V", "end", "", "getEnd", "()I", "setEnd", "(I)V", "start", "getStart", "setStart", "changeContentSize", "", "midSize", "changeNumSize", "getListB4", "", "Lcom/sinovatech/unicom/separatemodule/simbox/bean/B4;", "hexString", "", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: com.sinovatech.unicom.separatemodule.simbox.bean.B4$Companion */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getStart() {
            return C9466B4.start;
        }

        public final void setStart(int i) {
            C9466B4.start = i;
        }

        public final int getEnd() {
            return C9466B4.end;
        }

        public final void setEnd(int i) {
            C9466B4.end = i;
        }

        @NotNull
        public final List<C9466B4> getListB4(@NotNull String hexString) {
            Intrinsics.checkParameterIsNotNull(hexString, "hexString");
            ArrayList arrayList = new ArrayList();
            byte[] bytes = ResultExt.toBytes(hexString);
            while (bytes.length != 0) {
                Companion companion = this;
                String hex = ResultExt.toHex(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd()));
                companion.setStart(companion.getStart() + 1);
                companion.setEnd(companion.getEnd() + 1);
                int i = ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd()));
                companion.setStart(companion.getEnd());
                companion.setEnd(companion.getEnd() + 4);
                String hex2 = ResultExt.toHex(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd()));
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset = StandardCharsets.UTF_16LE;
                Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.UTF_16LE");
                String str = new String(copyOfRange, charset);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange2 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset2 = StandardCharsets.UTF_16LE;
                Intrinsics.checkExpressionValueIsNotNull(charset2, "StandardCharsets.UTF_16LE");
                C9466B4 c9466b4 = new C9466B4(str, new String(copyOfRange2, charset2));
                c9466b4.setTime(hex2);
                c9466b4.setRecId(hex);
                arrayList.add(c9466b4);
                bytes = ArraysKt.copyOfRange(bytes, i + 2, bytes.length);
                companion.setStart(0);
                companion.setEnd(1);
            }
            return arrayList;
        }

        public final void changeNumSize() {
            Companion companion = this;
            companion.setStart(companion.getEnd());
            companion.setEnd(companion.getEnd() + 1);
        }

        public final void changeContentSize(int i) {
            Companion companion = this;
            companion.setStart(companion.getEnd());
            companion.setEnd(companion.getEnd() + i);
        }
    }
}
