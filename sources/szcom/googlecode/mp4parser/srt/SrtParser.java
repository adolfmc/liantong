package szcom.googlecode.mp4parser.srt;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import szcom.googlecode.mp4parser.authoring.tracks.TextTrackImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SrtParser {
    private static long parse(String str) {
        return (Long.parseLong(str.split(":")[0].trim()) * 60 * 60 * 1000) + (Long.parseLong(str.split(":")[1].trim()) * 60 * 1000) + (Long.parseLong(str.split(":")[2].split(",")[0].trim()) * 1000) + Long.parseLong(str.split(":")[2].split(",")[1].trim());
    }

    public static TextTrackImpl parse(InputStream inputStream) {
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream, "UTF-8"));
        TextTrackImpl textTrackImpl = new TextTrackImpl();
        while (lineNumberReader.readLine() != null) {
            String readLine = lineNumberReader.readLine();
            String str = "";
            while (true) {
                String readLine2 = lineNumberReader.readLine();
                if (readLine2 != null && !readLine2.trim().equals("")) {
                    str = String.valueOf(str) + readLine2 + "\n";
                }
            }
            textTrackImpl.getSubs().add(new TextTrackImpl.Line(parse(readLine.split("-->")[0]), parse(readLine.split("-->")[1]), str));
        }
        return textTrackImpl;
    }
}
