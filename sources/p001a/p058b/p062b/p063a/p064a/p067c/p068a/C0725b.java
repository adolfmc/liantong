package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import org.json.JSONArray;
import org.json.JSONException;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.c.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0725b extends AbstractC0746c {

    /* renamed from: b */
    public static final InterfaceC3321a f2205b = C0749a.f2299a;

    /* renamed from: c */
    public long f2206c;

    /* renamed from: d */
    public long f2207d;

    public C0725b() {
    }

    public C0725b(long j, long j2) {
        this.f2206c = j;
        this.f2207d = j2;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, this.f2206c);
            jSONArray.put(1, this.f2207d);
        } catch (JSONException e) {
            f2205b.mo17426a("Caught error while DataToken asJSONArray: ", e);
            C0735a.m22302a(e);
        }
        return jSONArray;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0725b.class != obj.getClass()) {
            return false;
        }
        C0725b c0725b = (C0725b) obj;
        return this.f2206c == c0725b.f2206c && this.f2207d == c0725b.f2207d;
    }

    public int hashCode() {
        long j = this.f2206c;
        long j2 = this.f2207d;
        return (((int) (j ^ (j >>> 32))) * 31) + ((int) ((j2 >>> 32) ^ j2));
    }

    public String toString() {
        StringBuilder m24437a = outline.m24437a("DataToken{accountId=");
        m24437a.append(this.f2206c);
        m24437a.append(", agentId=");
        m24437a.append(this.f2207d);
        m24437a.append('}');
        return m24437a.toString();
    }
}
