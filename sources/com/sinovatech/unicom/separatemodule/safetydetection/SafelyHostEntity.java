package com.sinovatech.unicom.separatemodule.safetydetection;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SafelyHostEntity {
    private String blackCancelName;
    private String blackConfirmName;
    private String blackHint;
    private String blackHintLink;
    private String blackHosts;
    private boolean blackOpen;
    private String blackVersion;
    private String ecsTokenHosts;
    private boolean ecsTokenOpen;
    private String ecsTokenVersion;
    private String grayCancelName;
    private String grayConfimName;
    private String grayDialogHint;
    private String grayHint;
    private String grayHintLink;
    private boolean grayOpen;
    private String homeWebBaoGuangStatus;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18604id;
    private String whiteHosts;
    private boolean whiteOpen;
    private String whiteVersion;

    public long getId() {
        return this.f18604id;
    }

    public void setId(long j) {
        this.f18604id = j;
    }

    public String getWhiteHosts() {
        return this.whiteHosts;
    }

    public void setWhiteHosts(String str) {
        this.whiteHosts = str;
    }

    public boolean isWhiteOpen() {
        return this.whiteOpen;
    }

    public void setWhiteOpen(boolean z) {
        this.whiteOpen = z;
    }

    public String getWhiteVersion() {
        return this.whiteVersion;
    }

    public void setWhiteVersion(String str) {
        this.whiteVersion = str;
    }

    public String getBlackHosts() {
        return this.blackHosts;
    }

    public void setBlackHosts(String str) {
        this.blackHosts = str;
    }

    public boolean isBlackOpen() {
        return this.blackOpen;
    }

    public void setBlackOpen(boolean z) {
        this.blackOpen = z;
    }

    public String getBlackVersion() {
        return this.blackVersion;
    }

    public void setBlackVersion(String str) {
        this.blackVersion = str;
    }

    public String getBlackHint() {
        return this.blackHint;
    }

    public void setBlackHint(String str) {
        this.blackHint = str;
    }

    public String getBlackHintLink() {
        return this.blackHintLink;
    }

    public void setBlackHintLink(String str) {
        this.blackHintLink = str;
    }

    public String getGrayHint() {
        return this.grayHint;
    }

    public void setGrayHint(String str) {
        this.grayHint = str;
    }

    public String getGrayHintLink() {
        return this.grayHintLink;
    }

    public void setGrayHintLink(String str) {
        this.grayHintLink = str;
    }

    public boolean isGrayOpen() {
        return this.grayOpen;
    }

    public void setGrayOpen(boolean z) {
        this.grayOpen = z;
    }

    public String getEcsTokenHosts() {
        return this.ecsTokenHosts;
    }

    public void setEcsTokenHosts(String str) {
        this.ecsTokenHosts = str;
    }

    public boolean isEcsTokenOpen() {
        return this.ecsTokenOpen;
    }

    public void setEcsTokenOpen(boolean z) {
        this.ecsTokenOpen = z;
    }

    public String getEcsTokenVersion() {
        return this.ecsTokenVersion;
    }

    public void setEcsTokenVersion(String str) {
        this.ecsTokenVersion = str;
    }

    public String getBlackCancelName() {
        return this.blackCancelName;
    }

    public void setBlackCancelName(String str) {
        this.blackCancelName = str;
    }

    public String getBlackConfirmName() {
        return this.blackConfirmName;
    }

    public void setBlackConfirmName(String str) {
        this.blackConfirmName = str;
    }

    public String getGrayCancelName() {
        return this.grayCancelName;
    }

    public void setGrayCancelName(String str) {
        this.grayCancelName = str;
    }

    public String getGrayConfimName() {
        return this.grayConfimName;
    }

    public void setGrayConfimName(String str) {
        this.grayConfimName = str;
    }

    public String getGrayDialogHint() {
        return this.grayDialogHint;
    }

    public void setGrayDialogHint(String str) {
        this.grayDialogHint = str;
    }

    public String getHomeWebBaoGuangStatus() {
        return this.homeWebBaoGuangStatus;
    }

    public void setHomeWebBaoGuangStatus(String str) {
        this.homeWebBaoGuangStatus = str;
    }
}
