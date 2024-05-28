package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.com.google.gson.JsonArray;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ActionDatas extends HarvestableArray {
    private static final InterfaceC6393e log = C6394f.m10150a();
    public boolean isSendState = false;
    private final Collection<ActionData> actionDatas = new CopyOnWriteArrayList();
    private final Collection<ActionData> reserveActionDatas = new CopyOnWriteArrayList();

    public synchronized void add(ActionData actionData) {
        if (this.isSendState) {
            this.reserveActionDatas.add(actionData);
        } else {
            this.actionDatas.add(actionData);
        }
    }

    public synchronized void remove(ActionData actionData) {
        this.actionDatas.remove(actionData);
    }

    public synchronized void clear() {
        this.actionDatas.clear();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public synchronized JsonArray asJsonArray() {
        JsonArray jsonArray;
        jsonArray = new JsonArray();
        for (ActionData actionData : this.actionDatas) {
            jsonArray.add(actionData.asJson());
        }
        return jsonArray;
    }

    public Collection<ActionData> getActionDatas() {
        return this.actionDatas;
    }

    public int count() {
        return this.actionDatas.size();
    }

    public String toString() {
        return "ActionDatas{actionDatas=" + this.actionDatas + '}';
    }

    public synchronized void recoverData() {
        for (ActionData actionData : this.reserveActionDatas) {
            this.actionDatas.add(actionData);
        }
        this.reserveActionDatas.clear();
    }
}
