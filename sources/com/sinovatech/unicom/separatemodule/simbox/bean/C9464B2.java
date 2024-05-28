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

/* compiled from: B2.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u0000 $2\u00020\u0001:\u0001$B?\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000eR\u001a\u0010\u001a\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e¨\u0006%"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/simbox/bean/B2;", "", "title", "", "account", "bank", "withdrawalPsw", "queryPsw", "phonePsw", "remark", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "getBank", "setBank", "getPhonePsw", "setPhonePsw", "getQueryPsw", "setQueryPsw", "recId", "getRecId", "setRecId", "getRemark", "setRemark", "time", "getTime", "setTime", "getTitle", "setTitle", "getWithdrawalPsw", "setWithdrawalPsw", "getB2Bytes", "", "getB2Update", "Companion", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.sinovatech.unicom.separatemodule.simbox.bean.B2 */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class C9464B2 {
    public static final Companion Companion = new Companion(null);
    private static int end = 1;
    private static int start;
    @NotNull
    private String account;
    @NotNull
    private String bank;
    @NotNull
    private String phonePsw;
    @NotNull
    private String queryPsw;
    @NotNull
    private String recId;
    @NotNull
    private String remark;
    @NotNull
    private String time;
    @NotNull
    private String title;
    @NotNull
    private String withdrawalPsw;

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.title = str;
    }

    @NotNull
    public final String getAccount() {
        return this.account;
    }

    public final void setAccount(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.account = str;
    }

    @NotNull
    public final String getBank() {
        return this.bank;
    }

    public final void setBank(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.bank = str;
    }

    @NotNull
    public final String getWithdrawalPsw() {
        return this.withdrawalPsw;
    }

    public final void setWithdrawalPsw(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.withdrawalPsw = str;
    }

    @NotNull
    public final String getQueryPsw() {
        return this.queryPsw;
    }

    public final void setQueryPsw(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.queryPsw = str;
    }

    @NotNull
    public final String getPhonePsw() {
        return this.phonePsw;
    }

    public final void setPhonePsw(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.phonePsw = str;
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

    public C9464B2(@NotNull String title, @NotNull String account, @NotNull String bank, @NotNull String withdrawalPsw, @NotNull String queryPsw, @NotNull String phonePsw, @NotNull String remark) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(account, "account");
        Intrinsics.checkParameterIsNotNull(bank, "bank");
        Intrinsics.checkParameterIsNotNull(withdrawalPsw, "withdrawalPsw");
        Intrinsics.checkParameterIsNotNull(queryPsw, "queryPsw");
        Intrinsics.checkParameterIsNotNull(phonePsw, "phonePsw");
        Intrinsics.checkParameterIsNotNull(remark, "remark");
        this.time = "";
        this.recId = "";
        this.title = title;
        this.account = account;
        this.bank = bank;
        this.withdrawalPsw = withdrawalPsw;
        this.queryPsw = queryPsw;
        this.phonePsw = phonePsw;
        this.remark = remark;
    }

    @NotNull
    public final byte[] getB2Bytes() {
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
                String str3 = this.account;
                Charset charset3 = StandardCharsets.US_ASCII;
                Intrinsics.checkExpressionValueIsNotNull(charset3, "StandardCharsets.US_ASCII");
                if (str3 != null) {
                    byte[] bytes3 = str3.getBytes(charset3);
                    Intrinsics.checkExpressionValueIsNotNull(bytes3, "(this as java.lang.String).getBytes(charset)");
                    bArr2[0] = (byte) (bytes3.length & 255);
                    String str4 = this.account;
                    Charset charset4 = StandardCharsets.US_ASCII;
                    Intrinsics.checkExpressionValueIsNotNull(charset4, "StandardCharsets.US_ASCII");
                    if (str4 != null) {
                        byte[] bytes4 = str4.getBytes(charset4);
                        Intrinsics.checkExpressionValueIsNotNull(bytes4, "(this as java.lang.String).getBytes(charset)");
                        byte[] plus2 = ArraysKt.plus(bArr2, bytes4);
                        byte[] bArr3 = new byte[1];
                        String str5 = this.bank;
                        Charset charset5 = StandardCharsets.UTF_16LE;
                        Intrinsics.checkExpressionValueIsNotNull(charset5, "StandardCharsets.UTF_16LE");
                        if (str5 != null) {
                            byte[] bytes5 = str5.getBytes(charset5);
                            Intrinsics.checkExpressionValueIsNotNull(bytes5, "(this as java.lang.String).getBytes(charset)");
                            bArr3[0] = (byte) (bytes5.length & 255);
                            String str6 = this.bank;
                            Charset charset6 = StandardCharsets.UTF_16LE;
                            Intrinsics.checkExpressionValueIsNotNull(charset6, "StandardCharsets.UTF_16LE");
                            if (str6 != null) {
                                byte[] bytes6 = str6.getBytes(charset6);
                                Intrinsics.checkExpressionValueIsNotNull(bytes6, "(this as java.lang.String).getBytes(charset)");
                                byte[] plus3 = ArraysKt.plus(bArr3, bytes6);
                                byte[] bArr4 = new byte[1];
                                String str7 = this.withdrawalPsw;
                                Charset charset7 = StandardCharsets.US_ASCII;
                                Intrinsics.checkExpressionValueIsNotNull(charset7, "StandardCharsets.US_ASCII");
                                if (str7 != null) {
                                    byte[] bytes7 = str7.getBytes(charset7);
                                    Intrinsics.checkExpressionValueIsNotNull(bytes7, "(this as java.lang.String).getBytes(charset)");
                                    bArr4[0] = (byte) (bytes7.length & 255);
                                    String str8 = this.withdrawalPsw;
                                    Charset charset8 = StandardCharsets.US_ASCII;
                                    Intrinsics.checkExpressionValueIsNotNull(charset8, "StandardCharsets.US_ASCII");
                                    if (str8 != null) {
                                        byte[] bytes8 = str8.getBytes(charset8);
                                        Intrinsics.checkExpressionValueIsNotNull(bytes8, "(this as java.lang.String).getBytes(charset)");
                                        byte[] plus4 = ArraysKt.plus(bArr4, bytes8);
                                        byte[] bArr5 = new byte[1];
                                        String str9 = this.queryPsw;
                                        Charset charset9 = StandardCharsets.US_ASCII;
                                        Intrinsics.checkExpressionValueIsNotNull(charset9, "StandardCharsets.US_ASCII");
                                        if (str9 != null) {
                                            byte[] bytes9 = str9.getBytes(charset9);
                                            Intrinsics.checkExpressionValueIsNotNull(bytes9, "(this as java.lang.String).getBytes(charset)");
                                            bArr5[0] = (byte) (bytes9.length & 255);
                                            String str10 = this.queryPsw;
                                            Charset charset10 = StandardCharsets.US_ASCII;
                                            Intrinsics.checkExpressionValueIsNotNull(charset10, "StandardCharsets.US_ASCII");
                                            if (str10 != null) {
                                                byte[] bytes10 = str10.getBytes(charset10);
                                                Intrinsics.checkExpressionValueIsNotNull(bytes10, "(this as java.lang.String).getBytes(charset)");
                                                byte[] plus5 = ArraysKt.plus(bArr5, bytes10);
                                                byte[] bArr6 = new byte[1];
                                                String str11 = this.phonePsw;
                                                Charset charset11 = StandardCharsets.US_ASCII;
                                                Intrinsics.checkExpressionValueIsNotNull(charset11, "StandardCharsets.US_ASCII");
                                                if (str11 != null) {
                                                    byte[] bytes11 = str11.getBytes(charset11);
                                                    Intrinsics.checkExpressionValueIsNotNull(bytes11, "(this as java.lang.String).getBytes(charset)");
                                                    bArr6[0] = (byte) (bytes11.length & 255);
                                                    String str12 = this.phonePsw;
                                                    Charset charset12 = StandardCharsets.US_ASCII;
                                                    Intrinsics.checkExpressionValueIsNotNull(charset12, "StandardCharsets.US_ASCII");
                                                    if (str12 != null) {
                                                        byte[] bytes12 = str12.getBytes(charset12);
                                                        Intrinsics.checkExpressionValueIsNotNull(bytes12, "(this as java.lang.String).getBytes(charset)");
                                                        byte[] plus6 = ArraysKt.plus(bArr6, bytes12);
                                                        byte[] bArr7 = new byte[1];
                                                        String str13 = this.remark;
                                                        Charset charset13 = StandardCharsets.UTF_16LE;
                                                        Intrinsics.checkExpressionValueIsNotNull(charset13, "StandardCharsets.UTF_16LE");
                                                        if (str13 != null) {
                                                            byte[] bytes13 = str13.getBytes(charset13);
                                                            Intrinsics.checkExpressionValueIsNotNull(bytes13, "(this as java.lang.String).getBytes(charset)");
                                                            bArr7[0] = (byte) (bytes13.length & 255);
                                                            String str14 = this.remark;
                                                            Charset charset14 = StandardCharsets.UTF_16LE;
                                                            Intrinsics.checkExpressionValueIsNotNull(charset14, "StandardCharsets.UTF_16LE");
                                                            if (str14 != null) {
                                                                byte[] bytes14 = str14.getBytes(charset14);
                                                                Intrinsics.checkExpressionValueIsNotNull(bytes14, "(this as java.lang.String).getBytes(charset)");
                                                                return ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(plus, plus2), plus3), plus4), plus5), plus6), ArraysKt.plus(bArr7, bytes14));
                                                            }
                                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                                        }
                                                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                                    }
                                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                                }
                                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                            }
                                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                        }
                                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                    }
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                                }
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
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
    public final byte[] getB2Update() {
        return ArraysKt.plus(ResultExt.toBytes(this.time), getB2Bytes());
    }

    /* compiled from: B2.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u0006\u0010\u000f\u001a\u00020\rJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\u0015"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/simbox/bean/B2$Companion;", "", "()V", "end", "", "getEnd", "()I", "setEnd", "(I)V", "start", "getStart", "setStart", "changeContentSize", "", "midSize", "changeNumSize", "getListB2", "", "Lcom/sinovatech/unicom/separatemodule/simbox/bean/B2;", "hexString", "", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: com.sinovatech.unicom.separatemodule.simbox.bean.B2$Companion */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getStart() {
            return C9464B2.start;
        }

        public final void setStart(int i) {
            C9464B2.start = i;
        }

        public final int getEnd() {
            return C9464B2.end;
        }

        public final void setEnd(int i) {
            C9464B2.end = i;
        }

        @NotNull
        public final List<C9464B2> getListB2(@NotNull String hexString) {
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
                Charset charset2 = StandardCharsets.US_ASCII;
                Intrinsics.checkExpressionValueIsNotNull(charset2, "StandardCharsets.US_ASCII");
                String str2 = new String(copyOfRange2, charset2);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange3 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset3 = StandardCharsets.UTF_16LE;
                Intrinsics.checkExpressionValueIsNotNull(charset3, "StandardCharsets.UTF_16LE");
                String str3 = new String(copyOfRange3, charset3);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange4 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset4 = StandardCharsets.US_ASCII;
                Intrinsics.checkExpressionValueIsNotNull(charset4, "StandardCharsets.US_ASCII");
                String str4 = new String(copyOfRange4, charset4);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange5 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset5 = StandardCharsets.US_ASCII;
                Intrinsics.checkExpressionValueIsNotNull(charset5, "StandardCharsets.US_ASCII");
                String str5 = new String(copyOfRange5, charset5);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange6 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset6 = StandardCharsets.US_ASCII;
                Intrinsics.checkExpressionValueIsNotNull(charset6, "StandardCharsets.US_ASCII");
                String str6 = new String(copyOfRange6, charset6);
                companion.changeNumSize();
                companion.changeContentSize(ResultExt.toInt(ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd())));
                byte[] copyOfRange7 = ArraysKt.copyOfRange(bytes, companion.getStart(), companion.getEnd());
                Charset charset7 = StandardCharsets.UTF_16LE;
                Intrinsics.checkExpressionValueIsNotNull(charset7, "StandardCharsets.UTF_16LE");
                C9464B2 c9464b2 = new C9464B2(str, str2, str3, str4, str5, str6, new String(copyOfRange7, charset7));
                c9464b2.setTime(hex2);
                c9464b2.setRecId(hex);
                arrayList.add(c9464b2);
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
