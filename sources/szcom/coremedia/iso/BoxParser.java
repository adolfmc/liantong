package szcom.coremedia.iso;

import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.DataSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface BoxParser {
    Box parseBox(DataSource dataSource, Container container);
}
