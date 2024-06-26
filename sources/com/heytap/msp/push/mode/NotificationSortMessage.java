package com.heytap.msp.push.mode;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NotificationSortMessage implements Comparable<NotificationSortMessage> {
    private int autoDelete;

    /* renamed from: group  reason: collision with root package name */
    private String f27857group;
    private int importantLevel;
    private boolean isMcs;
    private String messageId;
    private int notifyId;
    private long postTime;
    private String statisticData;

    public NotificationSortMessage(String str, int i, int i2, boolean z, long j, int i3, String str2) {
        this(str, i, i2, z, j, str2);
        this.notifyId = i3;
    }

    public NotificationSortMessage(String str, int i, int i2, boolean z, long j, String str2) {
        this.messageId = str;
        this.importantLevel = i;
        this.autoDelete = i2;
        this.isMcs = z;
        this.postTime = j;
        this.statisticData = str2;
    }

    @Override // java.lang.Comparable
    public int compareTo(NotificationSortMessage notificationSortMessage) {
        if (notificationSortMessage.getPostTime() < this.postTime) {
            return 1;
        }
        return notificationSortMessage.getPostTime() == this.postTime ? 0 : -1;
    }

    public int getAutoDelete() {
        return this.autoDelete;
    }

    public String getGroup() {
        return this.f27857group;
    }

    public int getImportantLevel() {
        return this.importantLevel;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public long getPostTime() {
        return this.postTime;
    }

    public String getStatisticData() {
        return this.statisticData;
    }

    public boolean isMcs() {
        return this.isMcs;
    }

    public void setGroup(String str) {
        this.f27857group = str;
    }
}
