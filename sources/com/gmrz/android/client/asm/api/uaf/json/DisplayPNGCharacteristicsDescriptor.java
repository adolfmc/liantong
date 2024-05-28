package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DisplayPNGCharacteristicsDescriptor {
    public static final short MIN_SIZE_IN_BYTES = 13;
    public static final short RGBPALLETE_ENTRY_SIZE = 6;
    @Expose
    public byte bitDepth;
    @Expose
    public byte colorType;
    @Expose
    public byte compression;
    @Expose

    /* renamed from: filter  reason: collision with root package name */
    public byte f27854filter;
    @Expose
    public long height;
    @Expose
    public byte interlace;
    @Expose
    public List<rgbPalletteEntry> plte;
    @Expose
    public long width;

    public DisplayPNGCharacteristicsDescriptor() {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class rgbPalletteEntry {
        @Expose

        /* renamed from: b */
        public short f10149b;
        @Expose

        /* renamed from: g */
        public short f10150g;
        @Expose

        /* renamed from: r */
        public short f10151r;

        public rgbPalletteEntry() {
        }

        public rgbPalletteEntry(short s, short s2, short s3) {
            this.f10151r = s;
            this.f10150g = s2;
            this.f10149b = s3;
        }
    }

    public DisplayPNGCharacteristicsDescriptor(long j, long j2, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.width = j;
        this.height = j2;
        this.bitDepth = b;
        this.colorType = b2;
        this.compression = b3;
        this.f27854filter = b4;
        this.interlace = b5;
    }

    public static DisplayPNGCharacteristicsDescriptor getDefaultPNGDescriptor() {
        DisplayPNGCharacteristicsDescriptor displayPNGCharacteristicsDescriptor = new DisplayPNGCharacteristicsDescriptor(200L, 400L, (byte) 1, (byte) 3, (byte) 0, (byte) 0, (byte) 0);
        displayPNGCharacteristicsDescriptor.addRGBPalletteEntry(new rgbPalletteEntry((short) 200, (short) 0, (short) 0));
        displayPNGCharacteristicsDescriptor.addRGBPalletteEntry(new rgbPalletteEntry((short) 216, (short) 216, (short) 216));
        return displayPNGCharacteristicsDescriptor;
    }

    public void addRGBPalletteEntry(rgbPalletteEntry rgbpalletteentry) {
        if (this.plte == null) {
            this.plte = new ArrayList();
        }
        this.plte.add(rgbpalletteentry);
    }
}
