package com.tydic.softphone.entity;

import java.math.BigInteger;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Publisher {
    private String display;
    private BigInteger handleId;

    /* renamed from: id */
    private BigInteger f20021id;

    public Publisher() {
    }

    public Publisher(BigInteger bigInteger, String str) {
        this.f20021id = bigInteger;
        this.display = str;
    }

    public BigInteger getId() {
        return this.f20021id;
    }

    public void setId(BigInteger bigInteger) {
        this.f20021id = bigInteger;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String str) {
        this.display = str;
    }

    public BigInteger getHandleId() {
        return this.handleId;
    }

    public void setHandleId(BigInteger bigInteger) {
        this.handleId = bigInteger;
    }

    public String toString() {
        return "Publisher{id=" + this.f20021id + ", display='" + this.display + "', handleId=" + this.handleId + '}';
    }
}
