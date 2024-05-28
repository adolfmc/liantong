package com.networkbench.agent.impl.socket;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.p */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6619p extends HarvestableArray {

    /* renamed from: a */
    protected int f17037a;

    /* renamed from: c */
    protected int f17039c;

    /* renamed from: d */
    protected int f17040d;

    /* renamed from: b */
    protected String f17038b = "";

    /* renamed from: e */
    protected String f17041e = "";

    /* renamed from: f */
    protected boolean f17042f = false;

    /* renamed from: g */
    protected boolean f17043g = false;

    /* renamed from: h */
    protected boolean f17044h = false;

    /* renamed from: h */
    public abstract void mo9214h();

    /* renamed from: j */
    public boolean m9213j() {
        return this.f17043g;
    }

    /* renamed from: k */
    public boolean m9212k() {
        return this.f17042f;
    }

    /* renamed from: a */
    public void mo9222a(boolean z) {
        this.f17042f = z;
    }

    /* renamed from: b */
    public void mo9221b(boolean z) {
        this.f17043g = z;
    }

    /* renamed from: l */
    public boolean m9211l() {
        return this.f17044h;
    }

    /* renamed from: c */
    public void m9220c(boolean z) {
        this.f17044h = z;
    }

    /* renamed from: e */
    public void m9218e(int i) {
        this.f17037a = i;
    }

    /* renamed from: e */
    public void m9217e(String str) {
        this.f17038b = str;
    }

    /* renamed from: f */
    public void m9216f(int i) {
        this.f17039c = i;
    }

    /* renamed from: m */
    public int m9210m() {
        return this.f17039c;
    }

    /* renamed from: g */
    public void m9215g(int i) {
        this.f17040d = i;
    }

    /* renamed from: d */
    public void mo9219d(String str) {
        this.f17041e = str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        mo9214h();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f17037a)));
        jsonArray.add(new JsonPrimitive(this.f17038b));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f17039c)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f17040d)));
        jsonArray.add(new JsonPrimitive(this.f17041e));
        return jsonArray;
    }

    public String toString() {
        return "SocketData: eventtype:" + this.f17037a + ",target = " + this.f17038b + ", duration = " + this.f17039c + ", network_error_code = " + this.f17040d + ", desc = " + this.f17041e;
    }
}
