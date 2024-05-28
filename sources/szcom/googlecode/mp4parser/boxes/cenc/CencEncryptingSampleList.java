package szcom.googlecode.mp4parser.boxes.cenc;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
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
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.RangeStartMap;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencEncryptingSampleList extends AbstractList<Sample> {
    List<CencSampleAuxiliaryDataFormat> auxiliaryDataFormats;
    RangeStartMap<Integer, SecretKey> ceks;
    Cipher cipher;
    private final String encryptionAlgo;
    List<Sample> parent;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class EncryptedSampleImpl implements Sample {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final SecretKey cek;
        private final CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat;
        private final Cipher cipher;
        private final Sample clearSample;

        private EncryptedSampleImpl(Sample sample, CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, Cipher cipher, SecretKey secretKey) {
            this.clearSample = sample;
            this.cencSampleAuxiliaryDataFormat = cencSampleAuxiliaryDataFormat;
            this.cipher = cipher;
            this.cek = secretKey;
        }

        /* synthetic */ EncryptedSampleImpl(CencEncryptingSampleList cencEncryptingSampleList, Sample sample, CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, Cipher cipher, SecretKey secretKey, EncryptedSampleImpl encryptedSampleImpl) {
            this(sample, cencSampleAuxiliaryDataFormat, cipher, secretKey);
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public ByteBuffer asByteBuffer() {
            CencSampleAuxiliaryDataFormat.Pair[] pairArr;
            ByteBuffer byteBuffer = (ByteBuffer) this.clearSample.asByteBuffer().rewind();
            ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.limit());
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = this.cencSampleAuxiliaryDataFormat;
            CencEncryptingSampleList.this.initCipher(cencSampleAuxiliaryDataFormat.f27682iv, this.cek);
            try {
                if (cencSampleAuxiliaryDataFormat.pairs != null) {
                    for (CencSampleAuxiliaryDataFormat.Pair pair : cencSampleAuxiliaryDataFormat.pairs) {
                        byte[] bArr = new byte[pair.clear()];
                        byteBuffer.get(bArr);
                        allocate.put(bArr);
                        if (pair.encrypted() > 0) {
                            byte[] bArr2 = new byte[CastUtils.l2i(pair.encrypted())];
                            byteBuffer.get(bArr2);
                            allocate.put(this.cipher.update(bArr2));
                        }
                    }
                } else {
                    byte[] bArr3 = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr3);
                    if ("cbc1".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        int length = (bArr3.length / 16) * 16;
                        allocate.put(this.cipher.doFinal(bArr3, 0, length));
                        allocate.put(bArr3, length, bArr3.length - length);
                    } else if ("cenc".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        allocate.put(this.cipher.doFinal(bArr3));
                    }
                }
                byteBuffer.rewind();
                allocate.rewind();
                return allocate;
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public long getSize() {
            return this.clearSample.getSize();
        }

        @Override // szcom.googlecode.mp4parser.authoring.Sample
        public void writeTo(WritableByteChannel writableByteChannel) {
            ByteBuffer wrap;
            CencSampleAuxiliaryDataFormat.Pair[] pairArr;
            ByteBuffer byteBuffer = (ByteBuffer) this.clearSample.asByteBuffer().rewind();
            CencEncryptingSampleList.this.initCipher(this.cencSampleAuxiliaryDataFormat.f27682iv, this.cek);
            try {
                if (this.cencSampleAuxiliaryDataFormat.pairs == null || this.cencSampleAuxiliaryDataFormat.pairs.length <= 0) {
                    byte[] bArr = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr);
                    if (!"cbc1".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                        if ("cenc".equals(CencEncryptingSampleList.this.encryptionAlgo)) {
                            wrap = ByteBuffer.wrap(this.cipher.doFinal(bArr));
                        }
                        byteBuffer.rewind();
                    }
                    int length = (bArr.length / 16) * 16;
                    writableByteChannel.write(ByteBuffer.wrap(this.cipher.doFinal(bArr, 0, length)));
                    wrap = ByteBuffer.wrap(bArr, length, bArr.length - length);
                } else {
                    byte[] bArr2 = new byte[byteBuffer.limit()];
                    byteBuffer.get(bArr2);
                    int i = 0;
                    for (CencSampleAuxiliaryDataFormat.Pair pair : this.cencSampleAuxiliaryDataFormat.pairs) {
                        int clear = i + pair.clear();
                        if (pair.encrypted() > 0) {
                            this.cipher.update(bArr2, clear, CastUtils.l2i(pair.encrypted()), bArr2, clear);
                            i = (int) (clear + pair.encrypted());
                        } else {
                            i = clear;
                        }
                    }
                    wrap = ByteBuffer.wrap(bArr2);
                }
                writableByteChannel.write(wrap);
                byteBuffer.rewind();
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e2) {
                throw new RuntimeException(e2);
            } catch (ShortBufferException e3) {
                throw new RuntimeException(e3);
            }
        }
    }

    public CencEncryptingSampleList(SecretKey secretKey, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2) {
        this(new RangeStartMap(0, secretKey), list, list2, "cenc");
    }

    public CencEncryptingSampleList(RangeStartMap<Integer, SecretKey> rangeStartMap, List<Sample> list, List<CencSampleAuxiliaryDataFormat> list2, String str) {
        Cipher cipher;
        this.ceks = new RangeStartMap<>();
        this.auxiliaryDataFormats = list2;
        this.ceks = rangeStartMap;
        this.encryptionAlgo = str;
        this.parent = list;
        try {
            if ("cenc".equals(str)) {
                cipher = Cipher.getInstance("AES/CTR/NoPadding");
            } else if (!"cbc1".equals(str)) {
                throw new RuntimeException("Only cenc & cbc1 is supported as encryptionAlgo");
            } else {
                cipher = Cipher.getInstance("AES/CBC/NoPadding");
            }
            this.cipher = cipher;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i) {
        Sample sample = this.parent.get(i);
        return this.ceks.get(Integer.valueOf(i)) != null ? new EncryptedSampleImpl(this, sample, this.auxiliaryDataFormats.get(i), this.cipher, this.ceks.get(Integer.valueOf(i)), null) : sample;
    }

    protected void initCipher(byte[] bArr, SecretKey secretKey) {
        try {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.cipher.init(1, secretKey, new IvParameterSpec(bArr2));
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.parent.size();
    }
}
