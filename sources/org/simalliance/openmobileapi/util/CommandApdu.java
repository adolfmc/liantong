package org.simalliance.openmobileapi.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CommandApdu {
    public byte mCla;
    public byte[] mData;
    public byte mIns;
    public Integer mLe;
    public byte mP1;
    public byte mP2;

    public CommandApdu() {
    }

    private void setCla(byte b) {
        if (b != -1) {
            this.mCla = b;
            return;
        }
        throw new IllegalArgumentException("Invalid value of CLA (" + Integer.toHexString(b) + ")");
    }

    private void setData(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= 65535) {
                if (bArr.length != 0) {
                    byte[] bArr2 = new byte[bArr.length];
                    this.mData = bArr2;
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    return;
                }
                throw new IllegalArgumentException("Data must not be empty.");
            }
            throw new IllegalArgumentException("Data too long.");
        }
        throw new IllegalArgumentException("Data must not be null.");
    }

    private void setIns(byte b) {
        int i = b & 240;
        if (i != 96 && i != 144) {
            this.mIns = b;
            return;
        }
        throw new IllegalArgumentException("Invalid value of INS (" + Integer.toHexString(b) + "). 0x6X and 0x9X are not valid values");
    }

    private void setLe(int i) {
        if (i >= 0 && i <= 65536) {
            this.mLe = Integer.valueOf(i);
            return;
        }
        throw new IllegalArgumentException("Invalid value for le parameter (" + i + ").");
    }

    private void setP1(byte b) {
        this.mP1 = b;
    }

    private void setP2(byte b) {
        this.mP2 = b;
    }

    public CommandApdu cloneWithLe(int i) {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return new CommandApdu(this.mCla, this.mIns, this.mP1, this.mP2, (byte) i);
        }
        return new CommandApdu(this.mCla, this.mIns, this.mP1, this.mP2, bArr, (byte) i);
    }

    public boolean isExtendedLength() {
        Integer num = this.mLe;
        if (num == null || num.intValue() <= 256) {
            byte[] bArr = this.mData;
            return bArr != null && bArr.length > 255;
        }
        return true;
    }

    public byte[] toByteArray() {
        Integer num;
        Integer num2;
        if (!isExtendedLength()) {
            if (this.mData == null && this.mLe == null) {
                return new byte[]{this.mCla, this.mIns, this.mP1, this.mP2};
            }
            if (this.mData != null || (num2 = this.mLe) == null) {
                byte[] bArr = this.mData;
                if (bArr != null && this.mLe == null) {
                    byte[] bArr2 = new byte[bArr.length + 5];
                    bArr2[0] = this.mCla;
                    bArr2[1] = this.mIns;
                    bArr2[2] = this.mP1;
                    bArr2[3] = this.mP2;
                    bArr2[4] = (byte) (bArr.length & 255);
                    System.arraycopy(bArr, 0, bArr2, 5, bArr.length);
                    return bArr2;
                }
                byte[] bArr3 = this.mData;
                int length = bArr3.length + 6;
                byte[] bArr4 = new byte[length];
                bArr4[0] = this.mCla;
                bArr4[1] = this.mIns;
                bArr4[2] = this.mP1;
                bArr4[3] = this.mP2;
                bArr4[4] = (byte) (bArr3.length & 255);
                System.arraycopy(bArr3, 0, bArr4, 5, bArr3.length);
                bArr4[length - 1] = (byte) (this.mLe.intValue() & 255);
                return bArr4;
            }
            return new byte[]{this.mCla, this.mIns, this.mP1, this.mP2, (byte) (num2.intValue() & 255)};
        } else if (this.mData != null || (num = this.mLe) == null) {
            byte[] bArr5 = this.mData;
            if (bArr5 != null && this.mLe == null) {
                byte[] bArr6 = new byte[bArr5.length + 7];
                bArr6[0] = this.mCla;
                bArr6[1] = this.mIns;
                bArr6[2] = this.mP1;
                bArr6[3] = this.mP2;
                bArr6[4] = 0;
                bArr6[5] = (byte) ((bArr5.length >> 8) & 255);
                bArr6[6] = (byte) (bArr5.length & 255);
                System.arraycopy(bArr5, 0, bArr6, 7, bArr5.length);
                return bArr6;
            }
            byte[] bArr7 = this.mData;
            int length2 = bArr7.length + 9;
            byte[] bArr8 = new byte[length2];
            bArr8[0] = this.mCla;
            bArr8[1] = this.mIns;
            bArr8[2] = this.mP1;
            bArr8[3] = this.mP2;
            bArr8[4] = 0;
            bArr8[5] = (byte) ((bArr7.length >> 8) & 255);
            bArr8[6] = (byte) (bArr7.length & 255);
            System.arraycopy(bArr7, 0, bArr8, 7, bArr7.length);
            bArr8[length2 - 2] = (byte) ((this.mLe.intValue() >> 8) & 255);
            bArr8[length2 - 1] = (byte) (this.mLe.intValue() & 255);
            return bArr8;
        } else {
            return new byte[]{this.mCla, this.mIns, this.mP1, this.mP2, 0, (byte) ((num.intValue() >> 8) & 255), (byte) (this.mLe.intValue() & 255)};
        }
    }

    public CommandApdu(byte b, byte b2, byte b3, byte b4) {
        setCla(b);
        setIns(b2);
        setP1(b3);
        setP2(b4);
    }

    public CommandApdu(byte b, byte b2, byte b3, byte b4, int i) {
        setCla(b);
        setIns(b2);
        setP1(b3);
        setP2(b4);
        setLe(i);
    }

    public CommandApdu(byte b, byte b2, byte b3, byte b4, byte[] bArr) {
        setCla(b);
        setIns(b2);
        setP1(b3);
        setP2(b4);
        setData(bArr);
    }

    public CommandApdu(byte b, byte b2, byte b3, byte b4, byte[] bArr, int i) {
        setCla(b);
        setIns(b2);
        setP1(b3);
        setP2(b4);
        setData(bArr);
        setLe(i);
    }

    public CommandApdu(byte[] bArr) {
        if (bArr.length >= 4) {
            setCla(bArr[0]);
            setIns(bArr[1]);
            setP1(bArr[2]);
            setP2(bArr[3]);
            if (bArr.length == 4) {
                return;
            }
            if (bArr.length == 5) {
                setLe(bArr[4] & 255);
                return;
            } else if (bArr[4] != 0) {
                int i = bArr[4] & 255;
                if (i != 0) {
                    if (bArr.length != i + 5) {
                        if (bArr.length == i + 6) {
                            setLe(bArr[bArr.length - 1] & 255);
                        } else {
                            throw new IllegalArgumentException("Unexpected value of Lc (" + i + ")");
                        }
                    }
                    byte[] bArr2 = new byte[i];
                    this.mData = bArr2;
                    System.arraycopy(bArr, 5, bArr2, 0, i);
                    return;
                }
                throw new IllegalArgumentException("Lc can't be 0");
            } else if (bArr.length == 7) {
                setLe(((bArr[5] & 255) << 8) + (bArr[6] & 255));
                return;
            } else if (bArr.length > 7) {
                int i2 = ((bArr[5] & 255) << 8) + (bArr[6] & 255);
                if (i2 != 0) {
                    if (bArr.length != i2 + 7) {
                        if (bArr.length == i2 + 9) {
                            setLe(((bArr[bArr.length - 2] & 255) << 8) + (bArr[bArr.length - 1] & 255));
                        } else {
                            throw new IllegalArgumentException("Unexpected value of Lc (" + i2 + ")--- 9 -" + bArr.length);
                        }
                    }
                    byte[] bArr3 = new byte[i2];
                    this.mData = bArr3;
                    System.arraycopy(bArr, 7, bArr3, 0, i2);
                    return;
                }
                throw new IllegalArgumentException("Lc can't be 0");
            } else {
                throw new IllegalArgumentException("Unexpected value of Lc or Le" + bArr.length);
            }
        }
        throw new IllegalArgumentException("Invalid length for command (" + bArr.length + ").");
    }
}
