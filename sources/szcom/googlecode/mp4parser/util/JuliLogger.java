package szcom.googlecode.mp4parser.util;

import java.util.logging.Level;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JuliLogger extends Logger {
    java.util.logging.Logger logger;

    public JuliLogger(String str) {
        this.logger = java.util.logging.Logger.getLogger(str);
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logDebug(String str) {
        this.logger.log(Level.FINE, str);
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logError(String str) {
        this.logger.log(Level.SEVERE, str);
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logWarn(String str) {
        this.logger.log(Level.WARNING, str);
    }
}
