package android.support.p083v4.content;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: android.support.v4.content.SharedPreferencesCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class SharedPreferencesCompat {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* renamed from: android.support.v4.content.SharedPreferencesCompat$EditorCompat */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class EditorCompat {
        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: android.support.v4.content.SharedPreferencesCompat$EditorCompat$Helper */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        static class Helper {
            Helper() {
            }

            public void apply(@NonNull SharedPreferences.Editor editor) {
                try {
                    editor.apply();
                } catch (AbstractMethodError unused) {
                    editor.commit();
                }
            }
        }

        private EditorCompat() {
        }

        @Deprecated
        public static EditorCompat getInstance() {
            if (sInstance == null) {
                sInstance = new EditorCompat();
            }
            return sInstance;
        }

        @Deprecated
        public void apply(@NonNull SharedPreferences.Editor editor) {
            this.mHelper.apply(editor);
        }
    }

    private SharedPreferencesCompat() {
    }
}
