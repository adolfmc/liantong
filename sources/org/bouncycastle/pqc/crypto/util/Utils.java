package org.bouncycastle.pqc.crypto.util;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.p454bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.SPHINCS256KeyParams;
import org.bouncycastle.pqc.crypto.bike.BIKEParameters;
import org.bouncycastle.pqc.crypto.cmce.CMCEParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.falcon.FalconParameters;
import org.bouncycastle.pqc.crypto.frodo.FrodoParameters;
import org.bouncycastle.pqc.crypto.hqc.HQCParameters;
import org.bouncycastle.pqc.crypto.ntru.NTRUParameters;
import org.bouncycastle.pqc.crypto.ntruprime.NTRULPRimeParameters;
import org.bouncycastle.pqc.crypto.ntruprime.SNTRUPrimeParameters;
import org.bouncycastle.pqc.crypto.picnic.PicnicParameters;
import org.bouncycastle.pqc.crypto.saber.SABERParameters;
import org.bouncycastle.pqc.crypto.sike.SIKEParameters;
import org.bouncycastle.pqc.crypto.sphincsplus.SPHINCSPlusParameters;
import org.bouncycastle.util.Integers;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Utils {
    static final AlgorithmIdentifier AlgID_qTESLA_p_I = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_I);
    static final AlgorithmIdentifier AlgID_qTESLA_p_III = new AlgorithmIdentifier(PQCObjectIdentifiers.qTESLA_p_III);
    static final AlgorithmIdentifier SPHINCS_SHA3_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha3_256);
    static final AlgorithmIdentifier SPHINCS_SHA512_256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512_256);
    static final AlgorithmIdentifier XMSS_SHA256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
    static final AlgorithmIdentifier XMSS_SHA512 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
    static final AlgorithmIdentifier XMSS_SHAKE128 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake128);
    static final AlgorithmIdentifier XMSS_SHAKE256 = new AlgorithmIdentifier(NISTObjectIdentifiers.id_shake256);
    static final Map categories = new HashMap();
    static final Map picnicOids = new HashMap();
    static final Map picnicParams = new HashMap();
    static final Map frodoOids = new HashMap();
    static final Map frodoParams = new HashMap();
    static final Map saberOids = new HashMap();
    static final Map saberParams = new HashMap();
    static final Map mcElieceOids = new HashMap();
    static final Map mcElieceParams = new HashMap();
    static final Map sphincsPlusOids = new HashMap();
    static final Map sphincsPlusParams = new HashMap();
    static final Map sikeOids = new HashMap();
    static final Map sikeParams = new HashMap();
    static final Map ntruOids = new HashMap();
    static final Map ntruParams = new HashMap();
    static final Map falconOids = new HashMap();
    static final Map falconParams = new HashMap();
    static final Map kyberOids = new HashMap();
    static final Map kyberParams = new HashMap();
    static final Map ntruprimeOids = new HashMap();
    static final Map ntruprimeParams = new HashMap();
    static final Map sntruprimeOids = new HashMap();
    static final Map sntruprimeParams = new HashMap();
    static final Map dilithiumOids = new HashMap();
    static final Map dilithiumParams = new HashMap();
    static final Map bikeOids = new HashMap();
    static final Map bikeParams = new HashMap();
    static final Map hqcOids = new HashMap();
    static final Map hqcParams = new HashMap();

    static {
        categories.put(PQCObjectIdentifiers.qTESLA_p_I, Integers.valueOf(5));
        categories.put(PQCObjectIdentifiers.qTESLA_p_III, Integers.valueOf(6));
        mcElieceOids.put(CMCEParameters.mceliece348864r3, BCObjectIdentifiers.mceliece348864_r3);
        mcElieceOids.put(CMCEParameters.mceliece348864fr3, BCObjectIdentifiers.mceliece348864f_r3);
        mcElieceOids.put(CMCEParameters.mceliece460896r3, BCObjectIdentifiers.mceliece460896_r3);
        mcElieceOids.put(CMCEParameters.mceliece460896fr3, BCObjectIdentifiers.mceliece460896f_r3);
        mcElieceOids.put(CMCEParameters.mceliece6688128r3, BCObjectIdentifiers.mceliece6688128_r3);
        mcElieceOids.put(CMCEParameters.mceliece6688128fr3, BCObjectIdentifiers.mceliece6688128f_r3);
        mcElieceOids.put(CMCEParameters.mceliece6960119r3, BCObjectIdentifiers.mceliece6960119_r3);
        mcElieceOids.put(CMCEParameters.mceliece6960119fr3, BCObjectIdentifiers.mceliece6960119f_r3);
        mcElieceOids.put(CMCEParameters.mceliece8192128r3, BCObjectIdentifiers.mceliece8192128_r3);
        mcElieceOids.put(CMCEParameters.mceliece8192128fr3, BCObjectIdentifiers.mceliece8192128f_r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece348864_r3, CMCEParameters.mceliece348864r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece348864f_r3, CMCEParameters.mceliece348864fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece460896_r3, CMCEParameters.mceliece460896r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece460896f_r3, CMCEParameters.mceliece460896fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6688128_r3, CMCEParameters.mceliece6688128r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6688128f_r3, CMCEParameters.mceliece6688128fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6960119_r3, CMCEParameters.mceliece6960119r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece6960119f_r3, CMCEParameters.mceliece6960119fr3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece8192128_r3, CMCEParameters.mceliece8192128r3);
        mcElieceParams.put(BCObjectIdentifiers.mceliece8192128f_r3, CMCEParameters.mceliece8192128fr3);
        frodoOids.put(FrodoParameters.frodokem640aes, BCObjectIdentifiers.frodokem640aes);
        frodoOids.put(FrodoParameters.frodokem640shake, BCObjectIdentifiers.frodokem640shake);
        frodoOids.put(FrodoParameters.frodokem976aes, BCObjectIdentifiers.frodokem976aes);
        frodoOids.put(FrodoParameters.frodokem976shake, BCObjectIdentifiers.frodokem976shake);
        frodoOids.put(FrodoParameters.frodokem1344aes, BCObjectIdentifiers.frodokem1344aes);
        frodoOids.put(FrodoParameters.frodokem1344shake, BCObjectIdentifiers.frodokem1344shake);
        frodoParams.put(BCObjectIdentifiers.frodokem640aes, FrodoParameters.frodokem640aes);
        frodoParams.put(BCObjectIdentifiers.frodokem640shake, FrodoParameters.frodokem640shake);
        frodoParams.put(BCObjectIdentifiers.frodokem976aes, FrodoParameters.frodokem976aes);
        frodoParams.put(BCObjectIdentifiers.frodokem976shake, FrodoParameters.frodokem976shake);
        frodoParams.put(BCObjectIdentifiers.frodokem1344aes, FrodoParameters.frodokem1344aes);
        frodoParams.put(BCObjectIdentifiers.frodokem1344shake, FrodoParameters.frodokem1344shake);
        saberOids.put(SABERParameters.lightsaberkem128r3, BCObjectIdentifiers.lightsaberkem128r3);
        saberOids.put(SABERParameters.saberkem128r3, BCObjectIdentifiers.saberkem128r3);
        saberOids.put(SABERParameters.firesaberkem128r3, BCObjectIdentifiers.firesaberkem128r3);
        saberOids.put(SABERParameters.lightsaberkem192r3, BCObjectIdentifiers.lightsaberkem192r3);
        saberOids.put(SABERParameters.saberkem192r3, BCObjectIdentifiers.saberkem192r3);
        saberOids.put(SABERParameters.firesaberkem192r3, BCObjectIdentifiers.firesaberkem192r3);
        saberOids.put(SABERParameters.lightsaberkem256r3, BCObjectIdentifiers.lightsaberkem256r3);
        saberOids.put(SABERParameters.saberkem256r3, BCObjectIdentifiers.saberkem256r3);
        saberOids.put(SABERParameters.firesaberkem256r3, BCObjectIdentifiers.firesaberkem256r3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem128r3, SABERParameters.lightsaberkem128r3);
        saberParams.put(BCObjectIdentifiers.saberkem128r3, SABERParameters.saberkem128r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem128r3, SABERParameters.firesaberkem128r3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem192r3, SABERParameters.lightsaberkem192r3);
        saberParams.put(BCObjectIdentifiers.saberkem192r3, SABERParameters.saberkem192r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem192r3, SABERParameters.firesaberkem192r3);
        saberParams.put(BCObjectIdentifiers.lightsaberkem256r3, SABERParameters.lightsaberkem256r3);
        saberParams.put(BCObjectIdentifiers.saberkem256r3, SABERParameters.saberkem256r3);
        saberParams.put(BCObjectIdentifiers.firesaberkem256r3, SABERParameters.firesaberkem256r3);
        picnicOids.put(PicnicParameters.picnicl1fs, BCObjectIdentifiers.picnicl1fs);
        picnicOids.put(PicnicParameters.picnicl1ur, BCObjectIdentifiers.picnicl1ur);
        picnicOids.put(PicnicParameters.picnicl3fs, BCObjectIdentifiers.picnicl3fs);
        picnicOids.put(PicnicParameters.picnicl3ur, BCObjectIdentifiers.picnicl3ur);
        picnicOids.put(PicnicParameters.picnicl5fs, BCObjectIdentifiers.picnicl5fs);
        picnicOids.put(PicnicParameters.picnicl5ur, BCObjectIdentifiers.picnicl5ur);
        picnicOids.put(PicnicParameters.picnic3l1, BCObjectIdentifiers.picnic3l1);
        picnicOids.put(PicnicParameters.picnic3l3, BCObjectIdentifiers.picnic3l3);
        picnicOids.put(PicnicParameters.picnic3l5, BCObjectIdentifiers.picnic3l5);
        picnicOids.put(PicnicParameters.picnicl1full, BCObjectIdentifiers.picnicl1full);
        picnicOids.put(PicnicParameters.picnicl3full, BCObjectIdentifiers.picnicl3full);
        picnicOids.put(PicnicParameters.picnicl5full, BCObjectIdentifiers.picnicl5full);
        picnicParams.put(BCObjectIdentifiers.picnicl1fs, PicnicParameters.picnicl1fs);
        picnicParams.put(BCObjectIdentifiers.picnicl1ur, PicnicParameters.picnicl1ur);
        picnicParams.put(BCObjectIdentifiers.picnicl3fs, PicnicParameters.picnicl3fs);
        picnicParams.put(BCObjectIdentifiers.picnicl3ur, PicnicParameters.picnicl3ur);
        picnicParams.put(BCObjectIdentifiers.picnicl5fs, PicnicParameters.picnicl5fs);
        picnicParams.put(BCObjectIdentifiers.picnicl5ur, PicnicParameters.picnicl5ur);
        picnicParams.put(BCObjectIdentifiers.picnic3l1, PicnicParameters.picnic3l1);
        picnicParams.put(BCObjectIdentifiers.picnic3l3, PicnicParameters.picnic3l3);
        picnicParams.put(BCObjectIdentifiers.picnic3l5, PicnicParameters.picnic3l5);
        picnicParams.put(BCObjectIdentifiers.picnicl1full, PicnicParameters.picnicl1full);
        picnicParams.put(BCObjectIdentifiers.picnicl3full, PicnicParameters.picnicl3full);
        picnicParams.put(BCObjectIdentifiers.picnicl5full, PicnicParameters.picnicl5full);
        sikeOids.put(SIKEParameters.sikep434, BCObjectIdentifiers.sikep434);
        sikeOids.put(SIKEParameters.sikep503, BCObjectIdentifiers.sikep503);
        sikeOids.put(SIKEParameters.sikep610, BCObjectIdentifiers.sikep610);
        sikeOids.put(SIKEParameters.sikep751, BCObjectIdentifiers.sikep751);
        sikeOids.put(SIKEParameters.sikep434_compressed, BCObjectIdentifiers.sikep434_compressed);
        sikeOids.put(SIKEParameters.sikep503_compressed, BCObjectIdentifiers.sikep503_compressed);
        sikeOids.put(SIKEParameters.sikep610_compressed, BCObjectIdentifiers.sikep610_compressed);
        sikeOids.put(SIKEParameters.sikep751_compressed, BCObjectIdentifiers.sikep751_compressed);
        sikeParams.put(BCObjectIdentifiers.sikep434, SIKEParameters.sikep434);
        sikeParams.put(BCObjectIdentifiers.sikep503, SIKEParameters.sikep503);
        sikeParams.put(BCObjectIdentifiers.sikep610, SIKEParameters.sikep610);
        sikeParams.put(BCObjectIdentifiers.sikep751, SIKEParameters.sikep751);
        sikeParams.put(BCObjectIdentifiers.sikep434_compressed, SIKEParameters.sikep434_compressed);
        sikeParams.put(BCObjectIdentifiers.sikep503_compressed, SIKEParameters.sikep503_compressed);
        sikeParams.put(BCObjectIdentifiers.sikep610_compressed, SIKEParameters.sikep610_compressed);
        sikeParams.put(BCObjectIdentifiers.sikep751_compressed, SIKEParameters.sikep751_compressed);
        ntruOids.put(NTRUParameters.ntruhps2048509, BCObjectIdentifiers.ntruhps2048509);
        ntruOids.put(NTRUParameters.ntruhps2048677, BCObjectIdentifiers.ntruhps2048677);
        ntruOids.put(NTRUParameters.ntruhps4096821, BCObjectIdentifiers.ntruhps4096821);
        ntruOids.put(NTRUParameters.ntruhrss701, BCObjectIdentifiers.ntruhrss701);
        ntruParams.put(BCObjectIdentifiers.ntruhps2048509, NTRUParameters.ntruhps2048509);
        ntruParams.put(BCObjectIdentifiers.ntruhps2048677, NTRUParameters.ntruhps2048677);
        ntruParams.put(BCObjectIdentifiers.ntruhps4096821, NTRUParameters.ntruhps4096821);
        ntruParams.put(BCObjectIdentifiers.ntruhrss701, NTRUParameters.ntruhrss701);
        falconOids.put(FalconParameters.falcon_512, BCObjectIdentifiers.falcon_512);
        falconOids.put(FalconParameters.falcon_1024, BCObjectIdentifiers.falcon_1024);
        falconParams.put(BCObjectIdentifiers.falcon_512, FalconParameters.falcon_512);
        falconParams.put(BCObjectIdentifiers.falcon_1024, FalconParameters.falcon_1024);
        kyberOids.put(KyberParameters.kyber512, BCObjectIdentifiers.kyber512);
        kyberOids.put(KyberParameters.kyber768, BCObjectIdentifiers.kyber768);
        kyberOids.put(KyberParameters.kyber1024, BCObjectIdentifiers.kyber1024);
        kyberOids.put(KyberParameters.kyber512_aes, BCObjectIdentifiers.kyber512_aes);
        kyberOids.put(KyberParameters.kyber768_aes, BCObjectIdentifiers.kyber768_aes);
        kyberOids.put(KyberParameters.kyber1024_aes, BCObjectIdentifiers.kyber1024_aes);
        kyberParams.put(BCObjectIdentifiers.kyber512, KyberParameters.kyber512);
        kyberParams.put(BCObjectIdentifiers.kyber768, KyberParameters.kyber768);
        kyberParams.put(BCObjectIdentifiers.kyber1024, KyberParameters.kyber1024);
        kyberParams.put(BCObjectIdentifiers.kyber512_aes, KyberParameters.kyber512_aes);
        kyberParams.put(BCObjectIdentifiers.kyber768_aes, KyberParameters.kyber768_aes);
        kyberParams.put(BCObjectIdentifiers.kyber1024_aes, KyberParameters.kyber1024_aes);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr653, BCObjectIdentifiers.ntrulpr653);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr761, BCObjectIdentifiers.ntrulpr761);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr857, BCObjectIdentifiers.ntrulpr857);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr953, BCObjectIdentifiers.ntrulpr953);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr1013, BCObjectIdentifiers.ntrulpr1013);
        ntruprimeOids.put(NTRULPRimeParameters.ntrulpr1277, BCObjectIdentifiers.ntrulpr1277);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr653, NTRULPRimeParameters.ntrulpr653);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr761, NTRULPRimeParameters.ntrulpr761);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr857, NTRULPRimeParameters.ntrulpr857);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr953, NTRULPRimeParameters.ntrulpr953);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr1013, NTRULPRimeParameters.ntrulpr1013);
        ntruprimeParams.put(BCObjectIdentifiers.ntrulpr1277, NTRULPRimeParameters.ntrulpr1277);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup653, BCObjectIdentifiers.sntrup653);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup761, BCObjectIdentifiers.sntrup761);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup857, BCObjectIdentifiers.sntrup857);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup953, BCObjectIdentifiers.sntrup953);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup1013, BCObjectIdentifiers.sntrup1013);
        sntruprimeOids.put(SNTRUPrimeParameters.sntrup1277, BCObjectIdentifiers.sntrup1277);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup653, SNTRUPrimeParameters.sntrup653);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup761, SNTRUPrimeParameters.sntrup761);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup857, SNTRUPrimeParameters.sntrup857);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup953, SNTRUPrimeParameters.sntrup953);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup1013, SNTRUPrimeParameters.sntrup1013);
        sntruprimeParams.put(BCObjectIdentifiers.sntrup1277, SNTRUPrimeParameters.sntrup1277);
        dilithiumOids.put(DilithiumParameters.dilithium2, BCObjectIdentifiers.dilithium2);
        dilithiumOids.put(DilithiumParameters.dilithium3, BCObjectIdentifiers.dilithium3);
        dilithiumOids.put(DilithiumParameters.dilithium5, BCObjectIdentifiers.dilithium5);
        dilithiumOids.put(DilithiumParameters.dilithium2_aes, BCObjectIdentifiers.dilithium2_aes);
        dilithiumOids.put(DilithiumParameters.dilithium3_aes, BCObjectIdentifiers.dilithium3_aes);
        dilithiumOids.put(DilithiumParameters.dilithium5_aes, BCObjectIdentifiers.dilithium5_aes);
        dilithiumParams.put(BCObjectIdentifiers.dilithium2, DilithiumParameters.dilithium2);
        dilithiumParams.put(BCObjectIdentifiers.dilithium3, DilithiumParameters.dilithium3);
        dilithiumParams.put(BCObjectIdentifiers.dilithium5, DilithiumParameters.dilithium5);
        dilithiumParams.put(BCObjectIdentifiers.dilithium2_aes, DilithiumParameters.dilithium2_aes);
        dilithiumParams.put(BCObjectIdentifiers.dilithium3_aes, DilithiumParameters.dilithium3_aes);
        dilithiumParams.put(BCObjectIdentifiers.dilithium5_aes, DilithiumParameters.dilithium5_aes);
        bikeParams.put(BCObjectIdentifiers.bike128, BIKEParameters.bike128);
        bikeParams.put(BCObjectIdentifiers.bike192, BIKEParameters.bike192);
        bikeParams.put(BCObjectIdentifiers.bike256, BIKEParameters.bike256);
        bikeOids.put(BIKEParameters.bike128, BCObjectIdentifiers.bike128);
        bikeOids.put(BIKEParameters.bike192, BCObjectIdentifiers.bike192);
        bikeOids.put(BIKEParameters.bike256, BCObjectIdentifiers.bike256);
        hqcParams.put(BCObjectIdentifiers.hqc128, HQCParameters.hqc128);
        hqcParams.put(BCObjectIdentifiers.hqc192, HQCParameters.hqc192);
        hqcParams.put(BCObjectIdentifiers.hqc256, HQCParameters.hqc256);
        hqcOids.put(HQCParameters.hqc128, BCObjectIdentifiers.hqc128);
        hqcOids.put(HQCParameters.hqc192, BCObjectIdentifiers.hqc192);
        hqcOids.put(HQCParameters.hqc256, BCObjectIdentifiers.hqc256);
    }

    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier bikeOidLookup(BIKEParameters bIKEParameters) {
        return (ASN1ObjectIdentifier) bikeOids.get(bIKEParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BIKEParameters bikeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (BIKEParameters) bikeParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier dilithiumOidLookup(DilithiumParameters dilithiumParameters) {
        return (ASN1ObjectIdentifier) dilithiumOids.get(dilithiumParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DilithiumParameters dilithiumParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (DilithiumParameters) dilithiumParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier falconOidLookup(FalconParameters falconParameters) {
        return (ASN1ObjectIdentifier) falconOids.get(falconParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FalconParameters falconParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FalconParameters) falconParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier frodoOidLookup(FrodoParameters frodoParameters) {
        return (ASN1ObjectIdentifier) frodoOids.get(frodoParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FrodoParameters frodoParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (FrodoParameters) frodoParams.get(aSN1ObjectIdentifier);
    }

    public static AlgorithmIdentifier getAlgorithmIdentifier(String str) {
        if (str.equals("SHA-1")) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
        }
        if (str.equals("SHA-224")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha224);
        }
        if (str.equals("SHA-256")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256);
        }
        if (str.equals("SHA-384")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha384);
        }
        if (str.equals("SHA-512")) {
            return new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha512);
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Digest getDigest(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return new SHA256Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return new SHA512Digest();
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake128)) {
            return new SHAKEDigest(128);
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_shake256)) {
            return new SHAKEDigest(256);
        }
        throw new IllegalArgumentException("unrecognized digest OID: " + aSN1ObjectIdentifier);
    }

    public static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) OIWObjectIdentifiers.idSHA1)) {
            return "SHA-1";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha224)) {
            return "SHA-224";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha256)) {
            return "SHA-256";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha384)) {
            return "SHA-384";
        }
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_sha512)) {
            return "SHA-512";
        }
        throw new IllegalArgumentException("unrecognised digest algorithm: " + aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier hqcOidLookup(HQCParameters hQCParameters) {
        return (ASN1ObjectIdentifier) hqcOids.get(hQCParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HQCParameters hqcParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (HQCParameters) hqcParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier kyberOidLookup(KyberParameters kyberParameters) {
        return (ASN1ObjectIdentifier) kyberOids.get(kyberParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KyberParameters kyberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (KyberParameters) kyberParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier mcElieceOidLookup(CMCEParameters cMCEParameters) {
        return (ASN1ObjectIdentifier) mcElieceOids.get(cMCEParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CMCEParameters mcElieceParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (CMCEParameters) mcElieceParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier ntruOidLookup(NTRUParameters nTRUParameters) {
        return (ASN1ObjectIdentifier) ntruOids.get(nTRUParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NTRUParameters ntruParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRUParameters) ntruParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier ntrulprimeOidLookup(NTRULPRimeParameters nTRULPRimeParameters) {
        return (ASN1ObjectIdentifier) ntruprimeOids.get(nTRULPRimeParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NTRULPRimeParameters ntrulprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (NTRULPRimeParameters) ntruprimeParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier picnicOidLookup(PicnicParameters picnicParameters) {
        return (ASN1ObjectIdentifier) picnicOids.get(picnicParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PicnicParameters picnicParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (PicnicParameters) picnicParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier qTeslaLookupAlgID(int i) {
        switch (i) {
            case 5:
                return AlgID_qTESLA_p_I;
            case 6:
                return AlgID_qTESLA_p_III;
            default:
                throw new IllegalArgumentException("unknown security category: " + i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int qTeslaLookupSecurityCategory(AlgorithmIdentifier algorithmIdentifier) {
        return ((Integer) categories.get(algorithmIdentifier.getAlgorithm())).intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier saberOidLookup(SABERParameters sABERParameters) {
        return (ASN1ObjectIdentifier) saberOids.get(sABERParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SABERParameters saberParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SABERParameters) saberParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier sikeOidLookup(SIKEParameters sIKEParameters) {
        return (ASN1ObjectIdentifier) sikeOids.get(sIKEParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SIKEParameters sikeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SIKEParameters) sikeParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier sntruprimeOidLookup(SNTRUPrimeParameters sNTRUPrimeParameters) {
        return (ASN1ObjectIdentifier) sntruprimeOids.get(sNTRUPrimeParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SNTRUPrimeParameters sntruprimeParamsLookup(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (SNTRUPrimeParameters) sntruprimeParams.get(aSN1ObjectIdentifier);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier sphincs256LookupTreeAlgID(String str) {
        if (str.equals("SHA3-256")) {
            return SPHINCS_SHA3_256;
        }
        if (str.equals("SHA-512/256")) {
            return SPHINCS_SHA512_256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String sphincs256LookupTreeAlgName(SPHINCS256KeyParams sPHINCS256KeyParams) {
        AlgorithmIdentifier treeDigest = sPHINCS256KeyParams.getTreeDigest();
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA3_256.getAlgorithm())) {
            return "SHA3-256";
        }
        if (treeDigest.getAlgorithm().equals((ASN1Primitive) SPHINCS_SHA512_256.getAlgorithm())) {
            return "SHA-512/256";
        }
        throw new IllegalArgumentException("unknown tree digest: " + treeDigest.getAlgorithm());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier sphincsPlusOidLookup(SPHINCSPlusParameters sPHINCSPlusParameters) {
        int intValue = SPHINCSPlusParameters.getID(sPHINCSPlusParameters).intValue();
        int i = intValue & 196608;
        return i == 196608 ? BCObjectIdentifiers.sphincsPlus_haraka : i == 131072 ? BCObjectIdentifiers.sphincsPlus_shake_256 : ((intValue & 5) == 5 || (intValue & 6) == 6) ? BCObjectIdentifiers.sphincsPlus_sha_512 : BCObjectIdentifiers.sphincsPlus_sha_256;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier xmssLookupTreeAlgID(String str) {
        if (str.equals("SHA-256")) {
            return XMSS_SHA256;
        }
        if (str.equals("SHA-512")) {
            return XMSS_SHA512;
        }
        if (str.equals("SHAKE128")) {
            return XMSS_SHAKE128;
        }
        if (str.equals("SHAKE256")) {
            return XMSS_SHAKE256;
        }
        throw new IllegalArgumentException("unknown tree digest: " + str);
    }
}
