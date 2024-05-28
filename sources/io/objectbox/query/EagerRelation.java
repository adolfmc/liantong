package io.objectbox.query;

import io.objectbox.relation.RelationInfo;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class EagerRelation {
    public final int limit;
    public final RelationInfo relationInfo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EagerRelation(int i, RelationInfo relationInfo) {
        this.limit = i;
        this.relationInfo = relationInfo;
    }
}
