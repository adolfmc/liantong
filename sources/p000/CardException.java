package p000;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: o */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CardException extends IOException {
    public CardException(int i) {
        super(m1799a(i));
    }

    /* renamed from: a */
    public static String m1799a(int i) {
        String str;
        String str2 = "错误码:0x" + Integer.toHexString(i) + ",";
        String str3 = "未知错误.";
        if (i == 37634) {
            str = " MAC 错误";
        } else if (i != 37635) {
            switch (i) {
                case 25223:
                    str = "VAS数据未激活";
                    break;
                case 25600:
                    str = "状态标志位没有变";
                    break;
                case 25985:
                    str = "写 FLASH/EEPROM 失败";
                    break;
                case 26368:
                    str = "长度错误";
                    break;
                case 26754:
                    str = "不支持安全报文";
                    break;
                case 27270:
                    str = "P1，P2 参数不正确";
                    break;
                case 27272:
                    str = "引用数据未找到";
                    break;
                case 27392:
                    str = "参数错误（偏移地址超出了 EF 文件长度）";
                    break;
                case 27904:
                    str = "不正确的 INS";
                    break;
                case 28160:
                    str = "不正确的 CLA";
                    break;
                case 28416:
                    str = "未定义的错误";
                    break;
                case 37889:
                    str = "金额不足";
                    break;
                case 37891:
                    str = "密钥未找到";
                    break;
                case 37894:
                    str = "所需的 MAC 不可用";
                    break;
                case 37920:
                    str = "生成密钥对失败";
                    break;
                default:
                    switch (i) {
                        case 26112:
                            str = "接收通讯超时";
                            break;
                        case 26113:
                            str = "奇偶校验出错";
                            break;
                        case 26114:
                            str = "校验和不对";
                            break;
                        default:
                            switch (i) {
                                case 27009:
                                    str = "文件类型不匹配";
                                    break;
                                case 27010:
                                    str = "密钥使用权限不满足";
                                    break;
                                case 27011:
                                    str = "外部认证密钥锁死";
                                    break;
                                case 27012:
                                    str = "随机数无效，引用的数据无效";
                                    break;
                                case 27013:
                                    str = "使用条件不满足";
                                    break;
                                case 27014:
                                    str = "不满足命令执行条件（不允许的命令，INS有错）";
                                    break;
                                case 27015:
                                    str = "MAC丢失";
                                    break;
                                case 27016:
                                    str = "安全报文数据项（MAC）不正确";
                                    break;
                                default:
                                    switch (i) {
                                        case 27264:
                                            str = "数据域参数不正确";
                                            break;
                                        case 27265:
                                            str = "功能不支持/无 MF/目录已锁定";
                                            break;
                                        case 27266:
                                            str = "没有找到文件";
                                            break;
                                        case 27267:
                                            str = "没有找到记录/密钥已存在";
                                            break;
                                        case 27268:
                                            str = "没有足够的空间";
                                            break;
                                        default:
                                            if ((i >> 4) == 1596) {
                                                str = "认证失败，剩余尝试次数:" + (i & 15);
                                                break;
                                            }
                                            break;
                                    }
                            }
                    }
            }
            return str2 + str3;
        } else {
            str = "线路保护密钥锁死";
        }
        str3 = str;
        return str2 + str3;
    }
}
