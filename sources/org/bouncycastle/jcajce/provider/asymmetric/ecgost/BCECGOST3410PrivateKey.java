package org.bouncycastle.jcajce.provider.asymmetric.ecgost;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.ECPointEncoder;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BCECGOST3410PrivateKey implements ECPrivateKey, ECPointEncoder, org.bouncycastle.jce.interfaces.ECPrivateKey, PKCS12BagAttributeCarrier {
    static final long serialVersionUID = 7245981689601667138L;
    private String algorithm;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier;

    /* renamed from: d */
    private transient BigInteger f26873d;
    private transient ECParameterSpec ecSpec;
    private transient ASN1Encodable gostParams;
    private transient ASN1BitString publicKey;
    private boolean withCompression;

    protected BCECGOST3410PrivateKey() {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.algorithm = str;
        this.f26873d = eCPrivateKeyParameters.getD();
        this.ecSpec = null;
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.algorithm = str;
        this.f26873d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
            eCParameterSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), EC5Util.convertPoint(parameters.getG()), parameters.getN(), parameters.getH().intValue());
        }
        this.ecSpec = eCParameterSpec;
        this.gostParams = bCECGOST3410PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(String str, ECPrivateKeyParameters eCPrivateKeyParameters, BCECGOST3410PublicKey bCECGOST3410PublicKey, org.bouncycastle.jce.spec.ECParameterSpec eCParameterSpec) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.algorithm = str;
        this.f26873d = eCPrivateKeyParameters.getD();
        if (eCParameterSpec == null) {
            ECDomainParameters parameters = eCPrivateKeyParameters.getParameters();
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(parameters.getCurve(), parameters.getSeed()), EC5Util.convertPoint(parameters.getG()), parameters.getN(), parameters.getH().intValue());
        } else {
            this.ecSpec = new ECParameterSpec(EC5Util.convertCurve(eCParameterSpec.getCurve(), eCParameterSpec.getSeed()), EC5Util.convertPoint(eCParameterSpec.getG()), eCParameterSpec.getN(), eCParameterSpec.getH().intValue());
        }
        this.gostParams = bCECGOST3410PublicKey.getGostParams();
        this.publicKey = getPublicKeyDetails(bCECGOST3410PublicKey);
    }

    public BCECGOST3410PrivateKey(ECPrivateKey eCPrivateKey) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.f26873d = eCPrivateKey.getS();
        this.algorithm = eCPrivateKey.getAlgorithm();
        this.ecSpec = eCPrivateKey.getParams();
    }

    public BCECGOST3410PrivateKey(ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.f26873d = eCPrivateKeySpec.getS();
        this.ecSpec = eCPrivateKeySpec.getParams();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCECGOST3410PrivateKey(PrivateKeyInfo privateKeyInfo) throws IOException {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        populateFromPrivKeyInfo(privateKeyInfo);
    }

    public BCECGOST3410PrivateKey(BCECGOST3410PrivateKey bCECGOST3410PrivateKey) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.f26873d = bCECGOST3410PrivateKey.f26873d;
        this.ecSpec = bCECGOST3410PrivateKey.ecSpec;
        this.withCompression = bCECGOST3410PrivateKey.withCompression;
        this.attrCarrier = bCECGOST3410PrivateKey.attrCarrier;
        this.publicKey = bCECGOST3410PrivateKey.publicKey;
        this.gostParams = bCECGOST3410PrivateKey.gostParams;
    }

    public BCECGOST3410PrivateKey(org.bouncycastle.jce.spec.ECPrivateKeySpec eCPrivateKeySpec) {
        this.algorithm = "ECGOST3410";
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
        this.f26873d = eCPrivateKeySpec.getD();
        this.ecSpec = eCPrivateKeySpec.getParams() != null ? EC5Util.convertSpec(EC5Util.convertCurve(eCPrivateKeySpec.getParams().getCurve(), eCPrivateKeySpec.getParams().getSeed()), eCPrivateKeySpec.getParams()) : null;
    }

    private void extractBytes(byte[] bArr, int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length < 32) {
            byte[] bArr2 = new byte[32];
            System.arraycopy(byteArray, 0, bArr2, bArr2.length - byteArray.length, byteArray.length);
            byteArray = bArr2;
        }
        for (int i2 = 0; i2 != 32; i2++) {
            bArr[i + i2] = byteArray[(byteArray.length - 1) - i2];
        }
    }

    private ASN1BitString getPublicKeyDetails(BCECGOST3410PublicKey bCECGOST3410PublicKey) {
        try {
            return SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(bCECGOST3410PublicKey.getEncoded())).getPublicKeyData();
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void populateFromPrivKeyInfo(org.bouncycastle.asn1.pkcs.PrivateKeyInfo r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 313
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ecgost.BCECGOST3410PrivateKey.populateFromPrivKeyInfo(org.bouncycastle.asn1.pkcs.PrivateKeyInfo):void");
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        populateFromPrivKeyInfo(PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray((byte[]) objectInputStream.readObject())));
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(getEncoded());
    }

    org.bouncycastle.jce.spec.ECParameterSpec engineGetSpec() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        return eCParameterSpec != null ? EC5Util.convertSpec(eCParameterSpec) : BouncyCastleProvider.CONFIGURATION.getEcImplicitlyCa();
    }

    public boolean equals(Object obj) {
        if (obj instanceof BCECGOST3410PrivateKey) {
            BCECGOST3410PrivateKey bCECGOST3410PrivateKey = (BCECGOST3410PrivateKey) obj;
            return getD().equals(bCECGOST3410PrivateKey.getD()) && engineGetSpec().equals(bCECGOST3410PrivateKey.engineGetSpec());
        }
        return false;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return this.algorithm;
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return this.attrCarrier.getBagAttribute(aSN1ObjectIdentifier);
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // org.bouncycastle.jce.interfaces.ECPrivateKey
    public BigInteger getD() {
        return this.f26873d;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c4  */
    @Override // java.security.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getEncoded() {
        /*
            r8 = this;
            org.bouncycastle.asn1.ASN1Encodable r0 = r8.gostParams
            r1 = 0
            if (r0 == 0) goto L2c
            r0 = 32
            byte[] r0 = new byte[r0]
            r2 = 0
            java.math.BigInteger r3 = r8.getS()
            r8.extractBytes(r0, r2, r3)
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r2 = new org.bouncycastle.asn1.pkcs.PrivateKeyInfo     // Catch: java.io.IOException -> L2b
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r3 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier     // Catch: java.io.IOException -> L2b
            org.bouncycastle.asn1.ASN1ObjectIdentifier r4 = org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers.gostR3410_2001     // Catch: java.io.IOException -> L2b
            org.bouncycastle.asn1.ASN1Encodable r5 = r8.gostParams     // Catch: java.io.IOException -> L2b
            r3.<init>(r4, r5)     // Catch: java.io.IOException -> L2b
            org.bouncycastle.asn1.DEROctetString r4 = new org.bouncycastle.asn1.DEROctetString     // Catch: java.io.IOException -> L2b
            r4.<init>(r0)     // Catch: java.io.IOException -> L2b
            r2.<init>(r3, r4)     // Catch: java.io.IOException -> L2b
            java.lang.String r0 = "DER"
            byte[] r0 = r2.getEncoded(r0)     // Catch: java.io.IOException -> L2b
            return r0
        L2b:
            return r1
        L2c:
            java.security.spec.ECParameterSpec r0 = r8.ecSpec
            boolean r2 = r0 instanceof org.bouncycastle.jce.spec.ECNamedCurveSpec
            if (r2 == 0) goto L51
            org.bouncycastle.jce.spec.ECNamedCurveSpec r0 = (org.bouncycastle.jce.spec.ECNamedCurveSpec) r0
            java.lang.String r0 = r0.getName()
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil.getNamedCurveOid(r0)
            if (r0 != 0) goto L4b
            org.bouncycastle.asn1.ASN1ObjectIdentifier r0 = new org.bouncycastle.asn1.ASN1ObjectIdentifier
            java.security.spec.ECParameterSpec r2 = r8.ecSpec
            org.bouncycastle.jce.spec.ECNamedCurveSpec r2 = (org.bouncycastle.jce.spec.ECNamedCurveSpec) r2
            java.lang.String r2 = r2.getName()
            r0.<init>(r2)
        L4b:
            org.bouncycastle.asn1.x9.X962Parameters r2 = new org.bouncycastle.asn1.x9.X962Parameters
            r2.<init>(r0)
            goto La4
        L51:
            if (r0 != 0) goto L65
            org.bouncycastle.asn1.x9.X962Parameters r2 = new org.bouncycastle.asn1.x9.X962Parameters
            org.bouncycastle.asn1.DERNull r0 = org.bouncycastle.asn1.DERNull.INSTANCE
            r2.<init>(r0)
            org.bouncycastle.jcajce.provider.config.ProviderConfiguration r0 = org.bouncycastle.jce.provider.BouncyCastleProvider.CONFIGURATION
            java.math.BigInteger r3 = r8.getS()
            int r0 = org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil.getOrderBitLength(r0, r1, r3)
            goto Lb4
        L65:
            java.security.spec.EllipticCurve r0 = r0.getCurve()
            org.bouncycastle.math.ec.ECCurve r3 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertCurve(r0)
            org.bouncycastle.asn1.x9.X9ECParameters r0 = new org.bouncycastle.asn1.x9.X9ECParameters
            org.bouncycastle.asn1.x9.X9ECPoint r4 = new org.bouncycastle.asn1.x9.X9ECPoint
            java.security.spec.ECParameterSpec r2 = r8.ecSpec
            java.security.spec.ECPoint r2 = r2.getGenerator()
            org.bouncycastle.math.ec.ECPoint r2 = org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util.convertPoint(r3, r2)
            boolean r5 = r8.withCompression
            r4.<init>(r2, r5)
            java.security.spec.ECParameterSpec r2 = r8.ecSpec
            java.math.BigInteger r5 = r2.getOrder()
            java.security.spec.ECParameterSpec r2 = r8.ecSpec
            int r2 = r2.getCofactor()
            long r6 = (long) r2
            java.math.BigInteger r6 = java.math.BigInteger.valueOf(r6)
            java.security.spec.ECParameterSpec r2 = r8.ecSpec
            java.security.spec.EllipticCurve r2 = r2.getCurve()
            byte[] r7 = r2.getSeed()
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)
            org.bouncycastle.asn1.x9.X962Parameters r2 = new org.bouncycastle.asn1.x9.X962Parameters
            r2.<init>(r0)
        La4:
            org.bouncycastle.jcajce.provider.config.ProviderConfiguration r0 = org.bouncycastle.jce.provider.BouncyCastleProvider.CONFIGURATION
            java.security.spec.ECParameterSpec r3 = r8.ecSpec
            java.math.BigInteger r3 = r3.getOrder()
            java.math.BigInteger r4 = r8.getS()
            int r0 = org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil.getOrderBitLength(r0, r3, r4)
        Lb4:
            org.bouncycastle.asn1.ASN1BitString r3 = r8.publicKey
            if (r3 == 0) goto Lc4
            org.bouncycastle.asn1.sec.ECPrivateKey r3 = new org.bouncycastle.asn1.sec.ECPrivateKey
            java.math.BigInteger r4 = r8.getS()
            org.bouncycastle.asn1.ASN1BitString r5 = r8.publicKey
            r3.<init>(r0, r4, r5, r2)
            goto Lcd
        Lc4:
            org.bouncycastle.asn1.sec.ECPrivateKey r3 = new org.bouncycastle.asn1.sec.ECPrivateKey
            java.math.BigInteger r4 = r8.getS()
            r3.<init>(r0, r4, r2)
        Lcd:
            org.bouncycastle.asn1.pkcs.PrivateKeyInfo r0 = new org.bouncycastle.asn1.pkcs.PrivateKeyInfo     // Catch: java.io.IOException -> Le8
            org.bouncycastle.asn1.x509.AlgorithmIdentifier r4 = new org.bouncycastle.asn1.x509.AlgorithmIdentifier     // Catch: java.io.IOException -> Le8
            org.bouncycastle.asn1.ASN1ObjectIdentifier r5 = org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers.gostR3410_2001     // Catch: java.io.IOException -> Le8
            org.bouncycastle.asn1.ASN1Primitive r2 = r2.toASN1Primitive()     // Catch: java.io.IOException -> Le8
            r4.<init>(r5, r2)     // Catch: java.io.IOException -> Le8
            org.bouncycastle.asn1.ASN1Primitive r2 = r3.toASN1Primitive()     // Catch: java.io.IOException -> Le8
            r0.<init>(r4, r2)     // Catch: java.io.IOException -> Le8
            java.lang.String r2 = "DER"
            byte[] r0 = r0.getEncoded(r2)     // Catch: java.io.IOException -> Le8
            return r0
        Le8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.ecgost.BCECGOST3410PrivateKey.getEncoded():byte[]");
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.ECKey
    public org.bouncycastle.jce.spec.ECParameterSpec getParameters() {
        ECParameterSpec eCParameterSpec = this.ecSpec;
        if (eCParameterSpec == null) {
            return null;
        }
        return EC5Util.convertSpec(eCParameterSpec);
    }

    @Override // java.security.interfaces.ECKey
    public ECParameterSpec getParams() {
        return this.ecSpec;
    }

    @Override // java.security.interfaces.ECPrivateKey
    public BigInteger getS() {
        return this.f26873d;
    }

    public int hashCode() {
        return getD().hashCode() ^ engineGetSpec().hashCode();
    }

    @Override // org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier
    public void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attrCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Encodable);
    }

    @Override // org.bouncycastle.jce.interfaces.ECPointEncoder
    public void setPointFormat(String str) {
        this.withCompression = !"UNCOMPRESSED".equalsIgnoreCase(str);
    }

    public String toString() {
        return ECUtil.privateKeyToString(this.algorithm, this.f26873d, engineGetSpec());
    }
}
