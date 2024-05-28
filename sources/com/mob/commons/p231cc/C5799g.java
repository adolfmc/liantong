package com.mob.commons.p231cc;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.cc.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5799g implements InterfaceC5812q<C5799g> {
    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5799g c5799g, Class<C5799g> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("andOperation".equals(str) && objArr.length == 2) {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() & ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() & ((Long) objArr[1]).longValue());
            }
        } else if ("orOperation".equals(str) && objArr.length == 2) {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() | ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() | ((Long) objArr[1]).longValue());
            }
        } else if ("rMoveOperation".equals(str) && objArr.length == 2) {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() >> ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() >> ((int) ((Long) objArr[1]).longValue()));
            }
        } else if ("rrrMoveOperation".equals(str) && objArr.length == 2) {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() >>> ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() >>> ((int) ((Long) objArr[1]).longValue()));
            }
        } else if ("lMoveOperation".equals(str) && objArr.length == 2) {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() << ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() << ((int) ((Long) objArr[1]).longValue()));
            }
        } else if ("xOperation".equals(str) && objArr.length == 1) {
            if (objArr[0] != null && (objArr[0] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(~((Integer) objArr[0]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long)) {
                objArr2[0] = Long.valueOf(~((Long) objArr[0]).longValue());
            }
        } else if (!"xorOperation".equals(str) || objArr.length != 2) {
            return false;
        } else {
            if (objArr[0] != null && (objArr[0] instanceof Integer) && objArr[1] != null && (objArr[1] instanceof Integer)) {
                objArr2[0] = Integer.valueOf(((Integer) objArr[0]).intValue() ^ ((Integer) objArr[1]).intValue());
            } else if (objArr[0] != null && (objArr[0] instanceof Long) && objArr[1] != null && (objArr[1] instanceof Long)) {
                objArr2[0] = Long.valueOf(((Long) objArr[0]).longValue() ^ ((Long) objArr[1]).longValue());
            }
        }
        return true;
    }
}
