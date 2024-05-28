package com.baidu.cloud.videocache;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class rwd {

    /* renamed from: a */
    private static final Logger f4943a = LoggerFactory.getLogger("HlsPlaylistUtil");

    /* renamed from: a */
    private static String m19732a(String str, String str2, String str3) {
        if (str3 == null || str3.isEmpty()) {
            return str3;
        }
        Uri m19771a = C2579o.m19771a(str, str2);
        return TextUtils.isEmpty(m19771a.toString()) ? str3 : str3.replace(str2, C2576l.m19781b(m19771a.toString()));
    }

    /* renamed from: a */
    private static boolean m19731a(Scanner scanner) {
        return scanner != null && scanner.hasNextLine() && "#EXTM3U".equals(scanner.nextLine());
    }

    /* renamed from: a */
    public static byte[] m19730a(byte[] bArr, String str) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        String trim = new String(bArr).trim();
        Scanner scanner = new Scanner(trim);
        if (!m19731a(scanner)) {
            f4943a.debug("Input does not start with the #EXTM3U.url :" + str);
            return null;
        }
        boolean z = true;
        while (scanner.hasNextLine()) {
            String trim2 = scanner.nextLine().trim();
            if (z) {
                if (trim2.startsWith("#EXT-X-STREAM-INF")) {
                    z = true;
                } else if (trim2.startsWith("#EXT-X-TARGETDURATION") || trim2.startsWith("#EXT-X-MEDIA-SEQUENCE") || trim2.startsWith("#EXTINF") || trim2.startsWith("#EXT-X-KEY") || trim2.startsWith("#EXT-X-BYTERANGE") || trim2.equals("#EXT-X-DISCONTINUITY") || trim2.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || trim2.equals("#EXT-X-ENDLIST")) {
                    z = true;
                }
            }
            if (z) {
                if (trim2.startsWith("#EXT-X-STREAM-INF")) {
                    trim2 = scanner.nextLine();
                    trim = m19732a(str, trim2, trim);
                }
            } else if (z && !trim2.startsWith("#")) {
                trim = m19732a(str, trim2, trim);
            }
        }
        scanner.close();
        f4943a.debug("Convert completed:\n" + trim);
        return trim.getBytes();
    }
}
