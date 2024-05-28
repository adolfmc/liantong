package com.fidoalliance.uaf.app.api;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Authenticator {
    public String aaid;
    public String assertionScheme;
    public long attachmentHint;
    public List<Short> attestationTypes;
    public short authenticationAlgorithm;
    public String description;
    public String icon;
    public boolean isSecondFactorOnly;
    public short keyProtection;
    public short matcherProtection;
    public short tcDisplay;
    public String tcDisplayContentType;
    public String title;
    public long userVerification;
    public List<Version> supportedUAFVersions = new ArrayList();
    public List<DisplayPNGCharacteristicsDescriptor> tcDisplayPNGCharacteristics = new ArrayList();
    public List<String> supportedExtensionIDs = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class DisplayPNGCharacteristicsDescriptor {
        public byte bitDepth;
        public byte colorType;
        public byte compression;

        /* renamed from: filter  reason: collision with root package name */
        public byte f27853filter;
        public long height;
        public byte interlace;
        public List<rgbPaletteEntry> plte = new ArrayList();
        public long width;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class rgbPaletteEntry {

        /* renamed from: b */
        public short f10126b;

        /* renamed from: g */
        public short f10127g;

        /* renamed from: r */
        public short f10128r;
    }
}
