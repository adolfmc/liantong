package com.fido.uaf.ver0100.types;

import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MatchCriteria {
    @Expose
    public List<String> aaid;
    @Expose
    public List<String> assertionSchemes;
    @Expose
    public long attachmentHint;
    @Expose
    public List<Short> attestationTypes;
    @Expose
    public List<Short> authenticationAlgorithms;
    @Expose
    public short authenticatorVersion;
    public List<Extension> exts;
    @Expose
    public List<String> keyIDs;
    @Expose
    public short keyProtection;
    @Expose
    public short matcherProtection;
    @Expose
    public short tcDisplay;
    @Expose
    public long userVerification;
    @Expose
    public List<String> vendorID;
}
