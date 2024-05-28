package com.sinovatech.unicom.separatemodule.gamedistribution;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GameEntity {
    private String gameCentreDetailsUrl;
    private String gameDetailsUrl;
    private String gameDownloadStatus;
    private String gameIcon;
    private String gameId;
    private String gameName;
    private String gamePackageName;
    private String gameSize;
    private String gameSubtitle;
    private String gameUrl;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18535id;

    public long getId() {
        return this.f18535id;
    }

    public void setId(long j) {
        this.f18535id = j;
    }

    public String getGameId() {
        return this.gameId;
    }

    public void setGameId(String str) {
        this.gameId = str;
    }

    public String getGameName() {
        return this.gameName;
    }

    public void setGameName(String str) {
        this.gameName = str;
    }

    public String getGameUrl() {
        return this.gameUrl;
    }

    public void setGameUrl(String str) {
        this.gameUrl = str;
    }

    public String getGameIcon() {
        return this.gameIcon;
    }

    public void setGameIcon(String str) {
        this.gameIcon = str;
    }

    public String getGamePackageName() {
        return this.gamePackageName;
    }

    public void setGamePackageName(String str) {
        this.gamePackageName = str;
    }

    public String getGameDetailsUrl() {
        return this.gameDetailsUrl;
    }

    public void setGameDetailsUrl(String str) {
        this.gameDetailsUrl = str;
    }

    public String getGameCentreDetailsUrl() {
        return this.gameCentreDetailsUrl;
    }

    public void setGameCentreDetailsUrl(String str) {
        this.gameCentreDetailsUrl = str;
    }

    public String getGameSubtitle() {
        return this.gameSubtitle;
    }

    public void setGameSubtitle(String str) {
        this.gameSubtitle = str;
    }

    public String getGameSize() {
        return this.gameSize;
    }

    public void setGameSize(String str) {
        this.gameSize = str;
    }

    public String getGameDownloadStatus() {
        return this.gameDownloadStatus;
    }

    public void setGameDownloadStatus(String str) {
        this.gameDownloadStatus = str;
    }
}
