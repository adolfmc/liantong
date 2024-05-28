package com.baidu.cloud.framework.frame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TextureBuffer {
    public int textureId;
    public Type type;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Type {
        OES(36197),
        RGB(3553);
        
        private final int glTarget;

        Type(int i) {
            this.glTarget = i;
        }

        public int getGlTarget() {
            return this.glTarget;
        }

        public static Type fromGlTarget(int i) {
            if (i == 3553) {
                return RGB;
            }
            return OES;
        }
    }

    public TextureBuffer(int i, Type type) {
        this.textureId = i;
        this.type = type;
    }
}
