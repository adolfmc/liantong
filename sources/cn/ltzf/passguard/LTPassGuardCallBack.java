package cn.ltzf.passguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface LTPassGuardCallBack {
    void addText(String str);

    void backsapce();

    void clear();

    String getEditText();

    int getEndSelection();

    int getStartSelection();

    void hideAction();

    void moveSelectionLeft();

    void moveSelectionRight();

    void replaceText();

    void setCursorSelection(int i);

    void setCursorSelection(int i, int i2);

    void showAction();
}
