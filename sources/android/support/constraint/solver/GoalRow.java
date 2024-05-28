package android.support.constraint.solver;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GoalRow extends ArrayRow {
    public GoalRow(Cache cache) {
        super(cache);
    }

    @Override // android.support.constraint.solver.ArrayRow, android.support.constraint.solver.LinearSystem.Row
    public void addError(SolverVariable solverVariable) {
        super.addError(solverVariable);
        solverVariable.usageInRowCount--;
    }
}
