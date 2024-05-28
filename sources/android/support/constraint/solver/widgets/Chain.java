package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        int i3;
        ChainHead[] chainHeadArr;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = i4;
            i2 = 0;
        } else {
            i2 = 2;
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (constraintWidgetContainer.optimizeFor(4)) {
                if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i2, chainHead)) {
                    applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
                }
            } else {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0167  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r35, android.support.constraint.solver.LinearSystem r36, int r37, int r38, android.support.constraint.solver.widgets.ChainHead r39) {
        /*
            Method dump skipped, instructions count: 1355
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
