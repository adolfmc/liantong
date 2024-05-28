package com.tydic.softphone.encoder;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BASE64Decoder extends CharacterDecoder {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    byte[] decode_buffer = new byte[4];

    @Override // com.tydic.softphone.encoder.CharacterDecoder
    protected int bytesPerAtom() {
        return 4;
    }

    @Override // com.tydic.softphone.encoder.CharacterDecoder
    protected int bytesPerLine() {
        return 72;
    }

    static {
        int i = 0;
        for (int i2 = 0; i2 < 255; i2++) {
            pem_convert_array[i2] = -1;
        }
        while (true) {
            char[] cArr = pem_array;
            if (i >= cArr.length) {
                return;
            }
            pem_convert_array[cArr[i]] = (byte) i;
            i++;
        }
    }

    @Override // com.tydic.softphone.encoder.CharacterDecoder
    protected void decodeAtom(PushbackInputStream pushbackInputStream, OutputStream outputStream, int i) throws IOException {
        byte b;
        byte b2;
        byte b3;
        byte b4;
        if (i < 2) {
            throw new CEFormatException("BASE64Decoder: Not enough bytes for an atom.");
        }
        while (true) {
            int read = pushbackInputStream.read();
            byte b5 = -1;
            if (read == -1) {
                throw new CEStreamExhausted();
            }
            if (read != 10 && read != 13) {
                byte[] bArr = this.decode_buffer;
                bArr[0] = (byte) read;
                if (readFully(pushbackInputStream, bArr, 1, i - 1) == -1) {
                    throw new CEStreamExhausted();
                }
                if (i > 3 && this.decode_buffer[3] == 61) {
                    i = 3;
                }
                if (i > 2 && this.decode_buffer[2] == 61) {
                    i = 2;
                }
                switch (i) {
                    case 2:
                        b = -1;
                        byte[] bArr2 = pem_convert_array;
                        byte[] bArr3 = this.decode_buffer;
                        b2 = bArr2[bArr3[1] & 255];
                        byte b6 = bArr2[bArr3[0] & 255];
                        b3 = b;
                        b4 = b5;
                        b5 = b6;
                        break;
                    case 4:
                        b5 = pem_convert_array[this.decode_buffer[3] & 255];
                    case 3:
                        byte b7 = b5;
                        b5 = pem_convert_array[this.decode_buffer[2] & 255];
                        b = b7;
                        byte[] bArr22 = pem_convert_array;
                        byte[] bArr32 = this.decode_buffer;
                        b2 = bArr22[bArr32[1] & 255];
                        byte b62 = bArr22[bArr32[0] & 255];
                        b3 = b;
                        b4 = b5;
                        b5 = b62;
                        break;
                    default:
                        b4 = -1;
                        b3 = -1;
                        b2 = -1;
                        break;
                }
                switch (i) {
                    case 2:
                        outputStream.write((byte) (((b5 << 2) & 252) | ((b2 >>> 4) & 3)));
                        return;
                    case 3:
                        outputStream.write((byte) (((b5 << 2) & 252) | (3 & (b2 >>> 4))));
                        outputStream.write((byte) (((b4 >>> 2) & 15) | ((b2 << 4) & 240)));
                        return;
                    case 4:
                        outputStream.write((byte) (((b5 << 2) & 252) | ((b2 >>> 4) & 3)));
                        outputStream.write((byte) (((b2 << 4) & 240) | ((b4 >>> 2) & 15)));
                        outputStream.write((byte) (((b4 << 6) & 192) | (b3 & 63)));
                        return;
                    default:
                        return;
                }
            }
        }
    }
}
