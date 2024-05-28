package szcom.googlecode.mp4parser.boxes.cenc;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractList;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.SampleImpl;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.RangeStartMap;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencDecryptingSampleList extends AbstractList<Sample> {
    String encryptionAlgo;
    RangeStartMap<Integer, SecretKey> keys;
    List<Sample> parent;
    List<CencSampleAuxiliaryDataFormat> sencInfo;

    public CencDecryptingSampleList(SecretKey secretKey, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2) {
        this(new RangeStartMap(0, secretKey), list, list2, "cenc");
    }

    public CencDecryptingSampleList(RangeStartMap<Integer, SecretKey> rangeStartMap, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2, String str) {
        this.keys = new RangeStartMap<>();
        this.sencInfo = list2;
        this.keys = rangeStartMap;
        this.parent = list;
        this.encryptionAlgo = str;
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i) {
        Sample sample;
        byte[] doFinal;
        CencSampleAuxiliaryDataFormat.Pair[] pairArr;
        if (this.keys.get(Integer.valueOf(i)) != null) {
            ByteBuffer asByteBuffer = this.parent.get(i).asByteBuffer();
            asByteBuffer.rewind();
            ByteBuffer allocate = ByteBuffer.allocate(asByteBuffer.limit());
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = this.sencInfo.get(i);
            Cipher cipher = getCipher(this.keys.get(Integer.valueOf(i)), cencSampleAuxiliaryDataFormat.f27682iv);
            try {
                if (cencSampleAuxiliaryDataFormat.pairs == null || cencSampleAuxiliaryDataFormat.pairs.length <= 0) {
                    byte[] bArr = new byte[asByteBuffer.limit()];
                    asByteBuffer.get(bArr);
                    if ("cbc1".equals(this.encryptionAlgo)) {
                        int length = (bArr.length / 16) * 16;
                        allocate.put(cipher.doFinal(bArr, 0, length));
                        allocate.put(bArr, length, bArr.length - length);
                    } else if ("cenc".equals(this.encryptionAlgo)) {
                        doFinal = cipher.doFinal(bArr);
                    }
                    asByteBuffer.rewind();
                    allocate.rewind();
                    return new SampleImpl(allocate);
                }
                for (CencSampleAuxiliaryDataFormat.Pair pair : cencSampleAuxiliaryDataFormat.pairs) {
                    int clear = pair.clear();
                    int l2i = CastUtils.l2i(pair.encrypted());
                    byte[] bArr2 = new byte[clear];
                    asByteBuffer.get(bArr2);
                    allocate.put(bArr2);
                    if (l2i > 0) {
                        byte[] bArr3 = new byte[l2i];
                        asByteBuffer.get(bArr3);
                        allocate.put(cipher.update(bArr3));
                    }
                }
                if (asByteBuffer.remaining() > 0) {
                    System.err.println("Decrypted sample but still data remaining: " + sample.getSize());
                }
                doFinal = cipher.doFinal();
                allocate.put(doFinal);
                asByteBuffer.rewind();
                allocate.rewind();
                return new SampleImpl(allocate);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e2) {
                throw new RuntimeException(e2);
            }
        }
        return this.parent.get(i);
    }

    Cipher getCipher(SecretKey secretKey, byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        try {
            if ("cenc".equals(this.encryptionAlgo)) {
                Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
                cipher.init(2, secretKey, new IvParameterSpec(bArr2));
                return cipher;
            } else if ("cbc1".equals(this.encryptionAlgo)) {
                Cipher cipher2 = Cipher.getInstance("AES/CBC/NoPadding");
                cipher2.init(2, secretKey, new IvParameterSpec(bArr2));
                return cipher2;
            } else {
                throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
            }
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchAlgorithmException e3) {
            throw new RuntimeException(e3);
        } catch (NoSuchPaddingException e4) {
            throw new RuntimeException(e4);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.parent.size();
    }
}
