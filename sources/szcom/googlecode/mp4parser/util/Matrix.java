package szcom.googlecode.mp4parser.util;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class Matrix {

    /* renamed from: a */
    double f27672a;

    /* renamed from: b */
    double f27673b;

    /* renamed from: c */
    double f27674c;

    /* renamed from: d */
    double f27675d;

    /* renamed from: tx */
    double f27676tx;

    /* renamed from: ty */
    double f27677ty;

    /* renamed from: u */
    double f27678u;

    /* renamed from: v */
    double f27679v;

    /* renamed from: w */
    double f27680w;
    public static final Matrix ROTATE_0 = new Matrix(1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix ROTATE_90 = new Matrix(0.0d, 1.0d, -1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix ROTATE_180 = new Matrix(-1.0d, 0.0d, 0.0d, -1.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);
    public static final Matrix ROTATE_270 = new Matrix(0.0d, -1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d);

    public Matrix(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.f27678u = d5;
        this.f27679v = d6;
        this.f27680w = d7;
        this.f27672a = d;
        this.f27673b = d2;
        this.f27674c = d3;
        this.f27675d = d4;
        this.f27676tx = d8;
        this.f27677ty = d9;
    }

    public static Matrix fromByteBuffer(ByteBuffer byteBuffer) {
        return fromFileOrder(IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint1616(byteBuffer), IsoTypeReader.readFixedPoint0230(byteBuffer));
    }

    public static Matrix fromFileOrder(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        return new Matrix(d, d2, d4, d5, d3, d6, d9, d7, d8);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Matrix matrix = (Matrix) obj;
        return Double.compare(matrix.f27672a, this.f27672a) == 0 && Double.compare(matrix.f27673b, this.f27673b) == 0 && Double.compare(matrix.f27674c, this.f27674c) == 0 && Double.compare(matrix.f27675d, this.f27675d) == 0 && Double.compare(matrix.f27676tx, this.f27676tx) == 0 && Double.compare(matrix.f27677ty, this.f27677ty) == 0 && Double.compare(matrix.f27678u, this.f27678u) == 0 && Double.compare(matrix.f27679v, this.f27679v) == 0 && Double.compare(matrix.f27680w, this.f27680w) == 0;
    }

    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27672a);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27673b);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f27678u);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27674c);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27675d);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f27679v);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27676tx);
        IsoTypeWriter.writeFixedPoint1616(byteBuffer, this.f27677ty);
        IsoTypeWriter.writeFixedPoint0230(byteBuffer, this.f27680w);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.f27678u);
        long doubleToLongBits2 = Double.doubleToLongBits(this.f27679v);
        long doubleToLongBits3 = Double.doubleToLongBits(this.f27680w);
        long doubleToLongBits4 = Double.doubleToLongBits(this.f27672a);
        long doubleToLongBits5 = Double.doubleToLongBits(this.f27673b);
        long doubleToLongBits6 = Double.doubleToLongBits(this.f27674c);
        long doubleToLongBits7 = Double.doubleToLongBits(this.f27675d);
        long doubleToLongBits8 = Double.doubleToLongBits(this.f27676tx);
        long doubleToLongBits9 = Double.doubleToLongBits(this.f27677ty);
        return (((((((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + ((int) (doubleToLongBits7 ^ (doubleToLongBits7 >>> 32)))) * 31) + ((int) (doubleToLongBits8 ^ (doubleToLongBits8 >>> 32)))) * 31) + ((int) ((doubleToLongBits9 >>> 32) ^ doubleToLongBits9));
    }

    public String toString() {
        if (equals(ROTATE_0)) {
            return "Rotate 0°";
        }
        if (equals(ROTATE_90)) {
            return "Rotate 90°";
        }
        if (equals(ROTATE_180)) {
            return "Rotate 180°";
        }
        if (equals(ROTATE_270)) {
            return "Rotate 270°";
        }
        return "Matrix{u=" + this.f27678u + ", v=" + this.f27679v + ", w=" + this.f27680w + ", a=" + this.f27672a + ", b=" + this.f27673b + ", c=" + this.f27674c + ", d=" + this.f27675d + ", tx=" + this.f27676tx + ", ty=" + this.f27677ty + '}';
    }
}
