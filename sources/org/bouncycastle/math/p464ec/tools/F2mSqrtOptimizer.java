package org.bouncycastle.math.p464ec.tools;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeSet;
import org.bouncycastle.asn1.p457x9.ECNamedCurveTable;
import org.bouncycastle.asn1.p457x9.X9ECParametersHolder;
import org.bouncycastle.crypto.p458ec.CustomNamedCurves;
import org.bouncycastle.math.p464ec.ECAlgorithms;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.tools.F2mSqrtOptimizer */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class F2mSqrtOptimizer {
    private static ArrayList enumToList(Enumeration enumeration) {
        ArrayList arrayList = new ArrayList();
        while (enumeration.hasMoreElements()) {
            arrayList.add(enumeration.nextElement());
        }
        return arrayList;
    }

    private static void implPrintRootZ(ECCurve eCCurve) {
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(BigInteger.valueOf(2L));
        ECFieldElement sqrt = fromBigInteger.sqrt();
        System.out.println(sqrt.toBigInteger().toString(16).toUpperCase());
        if (!sqrt.square().equals(fromBigInteger)) {
            throw new IllegalStateException("Optimized-sqrt sanity check failed");
        }
    }

    public static void main(String[] strArr) {
        TreeSet<String> treeSet = new TreeSet(enumToList(ECNamedCurveTable.getNames()));
        treeSet.addAll(enumToList(CustomNamedCurves.getNames()));
        for (String str : treeSet) {
            X9ECParametersHolder byNameLazy = CustomNamedCurves.getByNameLazy(str);
            if (byNameLazy == null) {
                byNameLazy = ECNamedCurveTable.getByNameLazy(str);
            }
            if (byNameLazy != null) {
                ECCurve curve = byNameLazy.getCurve();
                if (ECAlgorithms.isF2mCurve(curve)) {
                    PrintStream printStream = System.out;
                    printStream.print(str + ":");
                    implPrintRootZ(curve);
                }
            }
        }
    }

    public static void printRootZ(ECCurve eCCurve) {
        if (!ECAlgorithms.isF2mCurve(eCCurve)) {
            throw new IllegalArgumentException("Sqrt optimization only defined over characteristic-2 fields");
        }
        implPrintRootZ(eCCurve);
    }
}
