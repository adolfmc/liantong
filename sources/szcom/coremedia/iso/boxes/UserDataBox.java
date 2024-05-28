package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class UserDataBox extends AbstractContainerBox {
    public static final String TYPE = "udta";

    public UserDataBox() {
        super("udta");
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        super.getBox(writableByteChannel);
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        super.parse(dataSource, byteBuffer, j, boxParser);
    }
}
