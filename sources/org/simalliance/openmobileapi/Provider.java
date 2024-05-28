package org.simalliance.openmobileapi;

import org.simalliance.openmobileapi.internal.ErrorStrings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Provider {
    public Channel mChannel;

    public Provider(Channel channel) {
        if (channel != null) {
            if (!channel.isClosed()) {
                this.mChannel = channel;
                return;
            }
            throw new IllegalStateException("Channel is closed.");
        }
        throw new IllegalStateException(ErrorStrings.paramNull("channel"));
    }

    public Channel getChannel() {
        return this.mChannel;
    }
}
