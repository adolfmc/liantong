package org.bouncycastle.asn1.edec;

import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface EdECObjectIdentifiers {
    public static final ASN1ObjectIdentifier id_edwards_curve_algs = new ASN1ObjectIdentifier("1.3.101");
    public static final ASN1ObjectIdentifier id_X25519 = id_edwards_curve_algs.branch(CityChangeManager.DEFAULT_SELECT_CITY_CODE).intern();
    public static final ASN1ObjectIdentifier id_X448 = id_edwards_curve_algs.branch("111").intern();
    public static final ASN1ObjectIdentifier id_Ed25519 = id_edwards_curve_algs.branch("112").intern();
    public static final ASN1ObjectIdentifier id_Ed448 = id_edwards_curve_algs.branch("113").intern();
}
