package szcom.mp4parser.streaming.extensions;

import szcom.mp4parser.streaming.TrackExtension;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NameTrackExtension implements TrackExtension {
    private String name;

    public static NameTrackExtension create(String str) {
        NameTrackExtension nameTrackExtension = new NameTrackExtension();
        nameTrackExtension.name = str;
        return nameTrackExtension;
    }

    public String getName() {
        return this.name;
    }
}
