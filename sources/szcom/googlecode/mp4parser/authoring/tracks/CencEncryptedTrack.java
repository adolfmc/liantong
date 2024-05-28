package szcom.googlecode.mp4parser.authoring.tracks;

import java.util.List;
import java.util.UUID;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface CencEncryptedTrack extends Track {
    UUID getDefaultKeyId();

    List<CencSampleAuxiliaryDataFormat> getSampleEncryptionEntries();

    boolean hasSubSampleEncryption();
}
