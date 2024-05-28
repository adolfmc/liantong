package com.p201hb.omapi.union.sim.bean;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import p001a.p002a.p003a.p004a.p005a.p006b.C0098b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m1890d2 = {"Lcom/hb/omapi/union/sim/bean/ErrCode;", "", "()V", "Companion", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.hb.omapi.union.sim.bean.ErrCode */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ErrCode {
    public static final int Error_Success = 0;
    public static final Companion Companion = new Companion(null);
    public static final int Error_Param_Length = 1;
    public static final int Error_Class_Cmd = 2;
    public static final int Error_Instruction_Cmd = 3;
    public static final int Error_Security_Condition = 4;
    public static final int Error_Used_Condition = 5;
    public static final int Error_Data_Structure = 5;
    public static final int Error_Not_Support = 6;
    public static final int Error_Memory = 8;
    public static final int Error_Verify_PIN = 9;
    public static final int Error_Lock_PIN = 10;
    public static final int Error_No_Space = 11;
    public static final int Error_Cmd_Order = 12;
    public static final int Error_Security_Environment = 13;
    public static final int Error_P1P2 = 14;
    public static final int Error_Initialize = 15;
    public static final int Error_Overrun = 16;
    public static final int Error_SIMMAX = 16;
    public static final int Error_SDK_BASIC = 30;
    public static final int Error_BIND_FAIL = 31;
    public static final int Error_BIND_NULL = 32;
    public static final int Error_SIM_FAIL = 33;
    public static final int Error_SIM_VERSIGN = 34;
    public static final int Error_DATA_FAIL = 35;
    public static final int Error_PSW_FAIL = 36;
    public static final int Error_Exception = 99;
    @NotNull
    public static final String Exception_HEX = "6999";
    @NotNull
    public static HashMap<String, Integer> codeMap = new HashMap<>();
    @NotNull
    public static HashMap<Integer, String> errMsgMap = new HashMap<>();

    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b5\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020:J\u000e\u0010I\u001a\u00020:2\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u0004J\u000e\u0010M\u001a\u00020K2\u0006\u0010N\u001a\u00020:R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0014\u0010-\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0014\u0010/\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0014\u00101\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0014\u00103\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0014\u00105\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0014\u00109\u001a\u00020:X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R6\u0010=\u001a\u001e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u00040>j\u000e\u0012\u0004\u0012\u00020:\u0012\u0004\u0012\u00020\u0004`?X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR6\u0010D\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020:0>j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020:`?X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010A\"\u0004\bF\u0010C¨\u0006O"}, m1890d2 = {"Lcom/hb/omapi/union/sim/bean/ErrCode$Companion;", "", "()V", "Error_BIND_FAIL", "", "getError_BIND_FAIL", "()I", "Error_BIND_NULL", "getError_BIND_NULL", "Error_Class_Cmd", "getError_Class_Cmd", "Error_Cmd_Order", "getError_Cmd_Order", "Error_DATA_FAIL", "getError_DATA_FAIL", "Error_Data_Structure", "getError_Data_Structure", "Error_Exception", "getError_Exception", "Error_Initialize", "getError_Initialize", "Error_Instruction_Cmd", "getError_Instruction_Cmd", "Error_Lock_PIN", "getError_Lock_PIN", "Error_Memory", "getError_Memory", "Error_No_Space", "getError_No_Space", "Error_Not_Support", "getError_Not_Support", "Error_Overrun", "getError_Overrun", "Error_P1P2", "getError_P1P2", "Error_PSW_FAIL", "getError_PSW_FAIL", "Error_Param_Length", "getError_Param_Length", "Error_SDK_BASIC", "getError_SDK_BASIC", "Error_SIMMAX", "getError_SIMMAX", "Error_SIM_FAIL", "getError_SIM_FAIL", "Error_SIM_VERSIGN", "getError_SIM_VERSIGN", "Error_Security_Condition", "getError_Security_Condition", "Error_Security_Environment", "getError_Security_Environment", "Error_Success", "getError_Success", "Error_Used_Condition", "getError_Used_Condition", "Error_Verify_PIN", "getError_Verify_PIN", "Exception_HEX", "", "getException_HEX", "()Ljava/lang/String;", "codeMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getCodeMap", "()Ljava/util/HashMap;", "setCodeMap", "(Ljava/util/HashMap;)V", "errMsgMap", "getErrMsgMap", "setErrMsgMap", "getErrCode", "code", "getErrMsg", "set63cX", "", "num", "setErrException", "e", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: com.hb.omapi.union.sim.bean.ErrCode$Companion */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class Companion {
        public Companion() {
        }

        @NotNull
        public final HashMap<String, Integer> getCodeMap() {
            return ErrCode.codeMap;
        }

        public final int getErrCode(@NotNull String str) {
            String lowerCase = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            Integer num = getCodeMap().get(lowerCase);
            if (num == null) {
                if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) "63c", false, 2, (Object) null)) {
                    return 16;
                }
                num = Integer.valueOf(getError_Verify_PIN());
                int length = lowerCase.length() - 1;
                int length2 = lowerCase.length();
                if (lowerCase != null) {
                    String substring = lowerCase.substring(length, length2);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    set63cX(Integer.parseInt(substring, CharsKt.checkRadix(16)));
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            return num.intValue();
        }

        @NotNull
        public final String getErrMsg(int i) {
            String str = getErrMsgMap().get(Integer.valueOf(i));
            return str == null ? "未知错误" : str;
        }

        @NotNull
        public final HashMap<Integer, String> getErrMsgMap() {
            return ErrCode.errMsgMap;
        }

        public final int getError_BIND_FAIL() {
            return ErrCode.Error_BIND_FAIL;
        }

        public final int getError_BIND_NULL() {
            return ErrCode.Error_BIND_NULL;
        }

        public final int getError_Class_Cmd() {
            return ErrCode.Error_Class_Cmd;
        }

        public final int getError_Cmd_Order() {
            return ErrCode.Error_Cmd_Order;
        }

        public final int getError_DATA_FAIL() {
            return ErrCode.Error_DATA_FAIL;
        }

        public final int getError_Data_Structure() {
            return ErrCode.Error_Data_Structure;
        }

        public final int getError_Exception() {
            return ErrCode.Error_Exception;
        }

        public final int getError_Initialize() {
            return ErrCode.Error_Initialize;
        }

        public final int getError_Instruction_Cmd() {
            return ErrCode.Error_Instruction_Cmd;
        }

        public final int getError_Lock_PIN() {
            return ErrCode.Error_Lock_PIN;
        }

        public final int getError_Memory() {
            return ErrCode.Error_Memory;
        }

        public final int getError_No_Space() {
            return ErrCode.Error_No_Space;
        }

        public final int getError_Not_Support() {
            return ErrCode.Error_Not_Support;
        }

        public final int getError_Overrun() {
            return ErrCode.Error_Overrun;
        }

        public final int getError_P1P2() {
            return ErrCode.Error_P1P2;
        }

        public final int getError_PSW_FAIL() {
            return ErrCode.Error_PSW_FAIL;
        }

        public final int getError_Param_Length() {
            return ErrCode.Error_Param_Length;
        }

        public final int getError_SDK_BASIC() {
            return ErrCode.Error_SDK_BASIC;
        }

        public final int getError_SIMMAX() {
            return ErrCode.Error_SIMMAX;
        }

        public final int getError_SIM_FAIL() {
            return ErrCode.Error_SIM_FAIL;
        }

        public final int getError_SIM_VERSIGN() {
            return ErrCode.Error_SIM_VERSIGN;
        }

        public final int getError_Security_Condition() {
            return ErrCode.Error_Security_Condition;
        }

        public final int getError_Security_Environment() {
            return ErrCode.Error_Security_Environment;
        }

        public final int getError_Success() {
            return ErrCode.Error_Success;
        }

        public final int getError_Used_Condition() {
            return ErrCode.Error_Used_Condition;
        }

        public final int getError_Verify_PIN() {
            return ErrCode.Error_Verify_PIN;
        }

        @NotNull
        public final String getException_HEX() {
            return ErrCode.Exception_HEX;
        }

        public final void set63cX(int i) {
            HashMap<Integer, String> errMsgMap = getErrMsgMap();
            Integer valueOf = Integer.valueOf(getError_Verify_PIN());
            errMsgMap.put(valueOf, "PIN验证出错，还剩余尝试次数:" + i);
        }

        public final void setCodeMap(@NotNull HashMap<String, Integer> hashMap) {
            ErrCode.codeMap = hashMap;
        }

        public final void setErrException(@NotNull String str) {
            getCodeMap().put(getException_HEX(), Integer.valueOf(getError_Exception()));
            getErrMsgMap().put(Integer.valueOf(getError_Exception()), str);
        }

        public final void setErrMsgMap(@NotNull HashMap<Integer, String> hashMap) {
            ErrCode.errMsgMap = hashMap;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        codeMap.put("9000", Integer.valueOf(Error_Success));
        codeMap.put("6700", Integer.valueOf(Error_Param_Length));
        codeMap.put("6e00", Integer.valueOf(Error_Class_Cmd));
        codeMap.put("6d00", Integer.valueOf(Error_Instruction_Cmd));
        codeMap.put("6982", Integer.valueOf(Error_Security_Condition));
        codeMap.put("6985", Integer.valueOf(Error_Used_Condition));
        codeMap.put("6a80", Integer.valueOf(Error_Data_Structure));
        codeMap.put("6a81", Integer.valueOf(Error_Not_Support));
        codeMap.put("6581", Integer.valueOf(Error_Memory));
        codeMap.put("6502", Integer.valueOf(Error_Overrun));
        codeMap.put("63cx", Integer.valueOf(Error_Verify_PIN));
        codeMap.put("6988", Integer.valueOf(Error_Lock_PIN));
        codeMap.put("6283", Integer.valueOf(Error_Lock_PIN));
        codeMap.put("6a84", Integer.valueOf(Error_No_Space));
        codeMap.put("6e01", Integer.valueOf(Error_Cmd_Order));
        codeMap.put("6e02", Integer.valueOf(Error_Security_Environment));
        codeMap.put("6a86", Integer.valueOf(Error_P1P2));
        codeMap.put("6a83", Integer.valueOf(Error_Initialize));
        codeMap.put("6ffe", Integer.valueOf(Error_BIND_NULL));
        codeMap.put("6fff", Integer.valueOf(Error_SIM_FAIL));
        errMsgMap.put(Integer.valueOf(Error_Success), "成功");
        errMsgMap.put(Integer.valueOf(Error_Param_Length), C0098b.f33o.m24433d());
        errMsgMap.put(Integer.valueOf(Error_Class_Cmd), C0098b.f33o.m24425l());
        errMsgMap.put(Integer.valueOf(Error_Instruction_Cmd), C0098b.f33o.m24426k());
        errMsgMap.put(Integer.valueOf(Error_Security_Condition), C0098b.f33o.m24432e());
        errMsgMap.put(Integer.valueOf(Error_Used_Condition), C0098b.f33o.m24431f());
        errMsgMap.put(Integer.valueOf(Error_Data_Structure), C0098b.f33o.m24430g());
        errMsgMap.put(Integer.valueOf(Error_Not_Support), C0098b.f33o.m24429h());
        errMsgMap.put(Integer.valueOf(Error_Memory), C0098b.f33o.m24434c());
        errMsgMap.put(Integer.valueOf(Error_Overrun), C0098b.f33o.m24435b());
        errMsgMap.put(Integer.valueOf(Error_Verify_PIN), "PIN验证出错，还剩余尝试次数X次");
        errMsgMap.put(Integer.valueOf(Error_Lock_PIN), "PIN验证出错次数超限，PIN锁定");
        errMsgMap.put(Integer.valueOf(Error_No_Space), "空间不足");
        errMsgMap.put(Integer.valueOf(Error_Cmd_Order), C0098b.f33o.m24424m());
        errMsgMap.put(Integer.valueOf(Error_Security_Environment), C0098b.f33o.m24423n());
        errMsgMap.put(Integer.valueOf(Error_P1P2), C0098b.f33o.m24427j());
        errMsgMap.put(Integer.valueOf(Error_Initialize), C0098b.f33o.m24428i());
        for (int i = Error_SIMMAX + 1; i <= 29; i++) {
            errMsgMap.put(Integer.valueOf(i), "SIM卡其他错误");
        }
        errMsgMap.put(Integer.valueOf(Error_BIND_FAIL), "手机不支持OMA接口");
        errMsgMap.put(Integer.valueOf(Error_BIND_NULL), "服务未绑定");
        errMsgMap.put(Integer.valueOf(Error_SIM_FAIL), "卡片未安装该应用");
        errMsgMap.put(Integer.valueOf(Error_SIM_VERSIGN), "验签失败");
        errMsgMap.put(Integer.valueOf(Error_DATA_FAIL), "数据为空");
        errMsgMap.put(Integer.valueOf(Error_PSW_FAIL), "PIN码长度不足6位");
    }
}
