package tv.danmaku.ijk.media.player;

import android.graphics.Rect;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class IjkTimedText {
    private Rect mTextBounds;
    private String mTextChars;

    public IjkTimedText(Rect rect, String str) {
        this.mTextBounds = null;
        this.mTextChars = null;
        this.mTextBounds = rect;
        this.mTextChars = str;
    }

    public Rect getBounds() {
        return this.mTextBounds;
    }

    public String getText() {
        return this.mTextChars;
    }
}
