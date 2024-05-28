package com.baidu.uaq.agent.android;

import android.support.annotation.Keep;
import p001a.p002a.p003a.p004a.outline;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@Keep
/* loaded from: E:\567196_dexfile_execute.dex */
public class AgentConfig {
    public static final String DEFAULT_COLLECTOR_HOST = "dq.jomodns.com";
    public static final String DEFAULT_COLLECTOR_PATH = "/sdk_push_stat";
    public static final int DEFAULT_COLLECTOR_PORT = 443;
    public static final String DEFAULT_CRASH_COLLECTOR_PATH = "/sdk_push_crash";
    public static final long DEFAULT_DATA_REPORT_LIMIT = 204800;
    public static final long DEFAULT_DATA_REPORT_PERIOD = 60000;
    public static final int DEFAULT_HARVESTABLE_CACHE_LIMIT = 1024;
    public static final int DEFAULT_LOG_LEVEL = 5;
    public static final int DEFAULT_RESPONSE_BODY_LIMIT = 2048;
    public static final long DEFAULT_SAMPLER_FREQ = 100;
    public static final double DEFAULT_SAMPLE_RATE = 100.0d;
    public static final int DEFAULT_STACK_TRACE_LIMIT = 100;
    public String APIKey;
    public String channel;
    public boolean collectAgentHealth;
    public String collectorHost;
    public String collectorPath;
    public int collectorPort;
    public String crashCollectorPath;
    public String cuid;
    public long dataReportLimit;
    public long dataReportPeriod;
    public boolean enableMobileNetworkReport;
    public boolean enableStatsEngine;
    public boolean enableTrace;
    public boolean enableTransmission;
    public int harvestableCacheLimit;
    public boolean logEnabled;
    public int logLevel;
    public boolean nativeControlDRP;
    public boolean reportCrashes;
    public long responseBodyLimit;
    public double sampleRate;
    public long samplerFreq;
    public int sourceType;
    public boolean thingsMonitor;
    public boolean useLogPersist;
    public boolean usePersistentUUID;
    public boolean useSsl;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    @Keep
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static final class Builder {
        public String APIKey;
        public String channel;
        public boolean collectAgentHealth;
        public String collectorHost;
        public String collectorPath;
        public int collectorPort;
        public String crashCollectorPath;
        public String cuid;
        public long dataReportLimit;
        public long dataReportPeriod;
        public boolean enableMobileNetworkReport;
        public boolean enableStatsEngine;
        public boolean enableTrace;
        public boolean enableTransmission;
        public int harvestableCacheLimit;
        public boolean logEnabled;
        public int logLevel;
        public boolean nativeControlDRP;
        public boolean reportCrashes;
        public long responseBodyLimit;
        public double sampleRate;
        public long samplerFreq;
        public int sourceType;
        public boolean thingsMonitor;
        public boolean useLogPersist;
        public boolean usePersistentUUID;
        public boolean useSsl;

        public Builder() {
            this.APIKey = "";
            this.logEnabled = false;
            this.logLevel = 5;
            this.useSsl = true;
            this.collectorHost = "dq.jomodns.com";
            this.collectorPort = 443;
            this.collectorPath = "/sdk_push_stat";
            this.crashCollectorPath = "/sdk_push_crash";
            this.dataReportPeriod = 60000L;
            this.nativeControlDRP = false;
            this.dataReportLimit = 204800L;
            this.enableMobileNetworkReport = true;
            this.useLogPersist = true;
            this.reportCrashes = true;
            this.usePersistentUUID = false;
            this.responseBodyLimit = 2048L;
            this.sampleRate = 100.0d;
            this.cuid = "null";
            this.channel = "null";
            this.sourceType = 0;
            this.harvestableCacheLimit = 1024;
            this.samplerFreq = 100L;
            this.enableTransmission = true;
            this.enableTrace = true;
            this.thingsMonitor = false;
            this.collectAgentHealth = true;
            this.enableStatsEngine = true;
        }

        public Builder(AgentConfig agentConfig) {
            this.APIKey = agentConfig.APIKey;
            this.logEnabled = agentConfig.logEnabled;
            this.logLevel = agentConfig.logLevel;
            this.useSsl = agentConfig.useSsl;
            this.collectorHost = agentConfig.collectorHost;
            this.collectorPath = agentConfig.collectorPath;
            this.crashCollectorPath = agentConfig.crashCollectorPath;
            this.collectorPort = agentConfig.collectorPort;
            this.dataReportPeriod = agentConfig.dataReportPeriod;
            this.nativeControlDRP = agentConfig.nativeControlDRP;
            this.dataReportLimit = agentConfig.dataReportLimit;
            this.enableMobileNetworkReport = agentConfig.enableMobileNetworkReport;
            this.useLogPersist = agentConfig.useLogPersist;
            this.reportCrashes = agentConfig.reportCrashes;
            this.usePersistentUUID = agentConfig.usePersistentUUID;
            this.responseBodyLimit = agentConfig.responseBodyLimit;
            this.sampleRate = agentConfig.sampleRate;
            this.cuid = agentConfig.cuid;
            this.channel = agentConfig.channel;
            this.sourceType = agentConfig.sourceType;
            this.harvestableCacheLimit = agentConfig.harvestableCacheLimit;
            this.samplerFreq = agentConfig.samplerFreq;
            this.enableTransmission = agentConfig.enableTransmission;
            this.enableTrace = agentConfig.enableTrace;
            this.thingsMonitor = agentConfig.thingsMonitor;
            this.enableStatsEngine = agentConfig.enableStatsEngine;
        }

        public Builder APIKey(String str) {
            this.APIKey = str;
            return this;
        }

        public AgentConfig build() {
            return new AgentConfig(this);
        }

        public Builder channel(String str) {
            this.channel = str;
            return this;
        }

        public Builder collectAgentHealth(boolean z) {
            this.collectAgentHealth = z;
            return this;
        }

        public Builder collectorHost(String str) {
            this.collectorHost = str;
            return this;
        }

        public Builder collectorPath(String str) {
            this.collectorPath = str;
            return this;
        }

        public Builder collectorPort(int i) {
            this.collectorPort = i;
            return this;
        }

        public Builder crashCollectorPath(String str) {
            this.crashCollectorPath = str;
            return this;
        }

        public Builder cuid(String str) {
            this.cuid = str;
            return this;
        }

        public Builder dataReportLimit(long j) {
            this.dataReportLimit = j;
            return this;
        }

        public Builder dataReportPeriod(long j) {
            this.dataReportPeriod = j;
            this.nativeControlDRP = true;
            return this;
        }

        public Builder enableMobileNetworkReport(boolean z) {
            this.enableMobileNetworkReport = z;
            return this;
        }

        public Builder harvestableCacheLimit(int i) {
            this.harvestableCacheLimit = i;
            return this;
        }

        public Builder logEnabled(boolean z) {
            this.logEnabled = z;
            return this;
        }

        public Builder logLevel(int i) {
            this.logLevel = i;
            return this;
        }

        public Builder reportCrashes(boolean z) {
            this.reportCrashes = z;
            return this;
        }

        public Builder responseBodyLimit(long j) {
            this.responseBodyLimit = j;
            return this;
        }

        public Builder sampleRate(double d) {
            this.sampleRate = d;
            return this;
        }

        public Builder samplerFreq(long j) {
            this.samplerFreq = j;
            return this;
        }

        public Builder sourceType(int i) {
            this.sourceType = i;
            return this;
        }

        public Builder thingsMonitor(boolean z) {
            this.thingsMonitor = z;
            return this;
        }

        public Builder useLogPersist(boolean z) {
            this.useLogPersist = z;
            return this;
        }

        public Builder usePersistentUUID(boolean z) {
            this.usePersistentUUID = z;
            return this;
        }

        public Builder useSsl(boolean z) {
            this.useSsl = z;
            return this;
        }
    }

    public AgentConfig(Builder builder) {
        this.APIKey = builder.APIKey;
        this.logEnabled = builder.logEnabled;
        this.logLevel = builder.logLevel;
        this.useSsl = builder.useSsl;
        this.collectorHost = builder.collectorHost;
        this.collectorPath = builder.collectorPath;
        this.crashCollectorPath = builder.crashCollectorPath;
        this.collectorPort = builder.collectorPort;
        this.dataReportPeriod = builder.dataReportPeriod;
        this.nativeControlDRP = builder.nativeControlDRP;
        this.dataReportLimit = builder.dataReportLimit;
        this.enableMobileNetworkReport = builder.enableMobileNetworkReport;
        this.useLogPersist = builder.useLogPersist;
        this.reportCrashes = builder.reportCrashes;
        this.usePersistentUUID = builder.usePersistentUUID;
        this.responseBodyLimit = builder.responseBodyLimit;
        this.sampleRate = builder.sampleRate;
        this.cuid = builder.cuid;
        this.channel = builder.channel;
        this.sourceType = builder.sourceType;
        this.harvestableCacheLimit = builder.harvestableCacheLimit;
        this.samplerFreq = builder.samplerFreq;
        this.enableTransmission = builder.enableTransmission;
        this.enableTrace = builder.enableTrace;
        this.thingsMonitor = builder.thingsMonitor;
        this.collectAgentHealth = builder.collectAgentHealth;
        this.enableStatsEngine = builder.enableStatsEngine;
    }

    public String getAPIKey() {
        return this.APIKey;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getCollectorHost() {
        return this.collectorHost;
    }

    public String getCollectorPath() {
        return this.collectorPath;
    }

    public int getCollectorPort() {
        return this.collectorPort;
    }

    public String getCrashCollectorPath() {
        return this.crashCollectorPath;
    }

    public String getCuid() {
        return this.cuid;
    }

    public long getDataReportLimit() {
        return this.dataReportLimit;
    }

    public long getDataReportPeriod() {
        return this.dataReportPeriod;
    }

    public int getHarvestableCacheLimit() {
        return this.harvestableCacheLimit;
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public long getResponseBodyLimit() {
        return this.responseBodyLimit;
    }

    public double getSampleRate() {
        return this.sampleRate;
    }

    public long getSamplerFreq() {
        return this.samplerFreq;
    }

    public int getSourceType() {
        return this.sourceType;
    }

    public boolean isCollectAgentHealth() {
        return this.collectAgentHealth;
    }

    public boolean isEnableMobileNetworkReport() {
        return this.enableMobileNetworkReport;
    }

    public boolean isEnableStatsEngine() {
        return this.enableStatsEngine;
    }

    public boolean isEnableTrace() {
        return this.enableTrace;
    }

    public boolean isEnableTransmission() {
        return this.enableTransmission;
    }

    public boolean isLogEnabled() {
        return this.logEnabled;
    }

    public boolean isNativeControlDRP() {
        return this.nativeControlDRP;
    }

    public boolean isReportCrashes() {
        return this.reportCrashes;
    }

    public boolean isThingsMonitor() {
        return this.thingsMonitor;
    }

    public boolean isUseLogPersist() {
        return this.useLogPersist;
    }

    public boolean isUsePersistentUUID() {
        return this.usePersistentUUID;
    }

    public boolean isUseSsl() {
        return this.useSsl;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public String toString() {
        StringBuilder m24437a = outline.m24437a("AgentConfig{\nAPIKey='");
        m24437a.append(this.APIKey);
        m24437a.append('\'');
        m24437a.append("\n, logEnabled=");
        m24437a.append(this.logEnabled);
        m24437a.append("\n, logLevel=");
        m24437a.append(this.logLevel);
        m24437a.append("\n, useSsl=");
        m24437a.append(this.useSsl);
        m24437a.append("\n, collectorHost='");
        m24437a.append(this.collectorHost);
        m24437a.append('\'');
        m24437a.append("\n, collectorPath='");
        m24437a.append(this.collectorPath);
        m24437a.append('\'');
        m24437a.append("\n, crashCollectorPath='");
        m24437a.append(this.crashCollectorPath);
        m24437a.append('\'');
        m24437a.append("\n, dataReportPeriod=");
        m24437a.append(this.dataReportPeriod);
        m24437a.append("\n, nativeControlDRP=");
        m24437a.append(this.nativeControlDRP);
        m24437a.append("\n, dataReportLimit=");
        m24437a.append(this.dataReportLimit);
        m24437a.append("\n, enableMobileNetworkReport=");
        m24437a.append(this.enableMobileNetworkReport);
        m24437a.append("\n, useLogPersist=");
        m24437a.append(this.useLogPersist);
        m24437a.append("\n, reportCrashes=");
        m24437a.append(this.reportCrashes);
        m24437a.append("\n, usePersistentUUID=");
        m24437a.append(this.usePersistentUUID);
        m24437a.append("\n, responseBodyLimit=");
        m24437a.append(this.responseBodyLimit);
        m24437a.append("\n, sampleRate=");
        m24437a.append(this.sampleRate);
        m24437a.append("\n, cuid='");
        m24437a.append(this.cuid);
        m24437a.append('\'');
        m24437a.append("\n, channel='");
        m24437a.append(this.channel);
        m24437a.append('\'');
        m24437a.append("\n, harvestableCacheLimit=");
        m24437a.append(this.harvestableCacheLimit);
        m24437a.append("\n, samplerFreq=");
        m24437a.append(this.samplerFreq);
        m24437a.append("\n, enableTransmission=");
        m24437a.append(this.enableTransmission);
        m24437a.append("\n, enableTrace=");
        m24437a.append(this.enableTrace);
        m24437a.append("\n, thingsMonitor=");
        m24437a.append(this.thingsMonitor);
        m24437a.append("\n, enableStatsEngine=");
        m24437a.append(this.enableStatsEngine);
        m24437a.append("\n");
        m24437a.append('}');
        return m24437a.toString();
    }
}
