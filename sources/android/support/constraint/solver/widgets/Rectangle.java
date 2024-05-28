package android.support.constraint.solver.widgets;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Rectangle {
    public int height;
    public int width;

    /* renamed from: x */
    public int f2457x;

    /* renamed from: y */
    public int f2458y;

    public void setBounds(int i, int i2, int i3, int i4) {
        this.f2457x = i;
        this.f2458y = i2;
        this.width = i3;
        this.height = i4;
    }

    void grow(int i, int i2) {
        this.f2457x -= i;
        this.f2458y -= i2;
        this.width += i * 2;
        this.height += i2 * 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean intersects(Rectangle rectangle) {
        int i;
        int i2;
        int i3 = this.f2457x;
        int i4 = rectangle.f2457x;
        return i3 >= i4 && i3 < i4 + rectangle.width && (i = this.f2458y) >= (i2 = rectangle.f2458y) && i < i2 + rectangle.height;
    }

    public boolean contains(int i, int i2) {
        int i3;
        int i4 = this.f2457x;
        return i >= i4 && i < i4 + this.width && i2 >= (i3 = this.f2458y) && i2 < i3 + this.height;
    }

    public int getCenterX() {
        return (this.f2457x + this.width) / 2;
    }

    public int getCenterY() {
        return (this.f2458y + this.height) / 2;
    }
}
