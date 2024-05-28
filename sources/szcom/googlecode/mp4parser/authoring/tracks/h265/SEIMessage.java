package szcom.googlecode.mp4parser.authoring.tracks.h265;

import java.io.PrintStream;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SEIMessage {
    public SEIMessage(BitReaderBuffer bitReaderBuffer) {
        int i = 0;
        while (bitReaderBuffer.readBits(8) == 255) {
            i += 255;
        }
        int readBits = bitReaderBuffer.readBits(8) + i;
        do {
        } while (bitReaderBuffer.readBits(8) == 255);
        bitReaderBuffer.readBits(8);
        PrintStream printStream = System.err;
        printStream.println("payloadType " + readBits);
    }
}
