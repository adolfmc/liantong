package io.objectbox.ideasonly;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ModelModifier {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class EntityModifier {
        final String name;
        final String schemaName;

        public void remove() {
        }

        public void renameTo(String str) {
        }

        EntityModifier(String str, String str2) {
            this.schemaName = str;
            this.name = str2;
        }

        public PropertyModifier property(String str) {
            return new PropertyModifier(this, str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class PropertyModifier {
        final EntityModifier entityModifier;
        final String name;

        public void remove() {
        }

        public void renameTo(String str) {
        }

        PropertyModifier(EntityModifier entityModifier, String str) {
            this.entityModifier = entityModifier;
            this.name = str;
        }
    }

    public EntityModifier entity(String str) {
        return new EntityModifier("default", str);
    }
}
