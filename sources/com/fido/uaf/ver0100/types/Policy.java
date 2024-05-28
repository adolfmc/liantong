package com.fido.uaf.ver0100.types;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Policy {
    @Expose
    public List<List<MatchCriteria>> accepted = null;
    @Expose
    public List<MatchCriteria> disallowed = new ArrayList();
}
