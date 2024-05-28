package org.simalliance.openmobileapi;

import java.io.IOException;
import org.simalliance.openmobileapi.internal.ByteArrayConverter;
import org.simalliance.openmobileapi.internal.ErrorStrings;
import org.simalliance.openmobileapi.util.CommandApdu;
import org.simalliance.openmobileapi.util.ResponseApdu;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecureStorageProvider extends Provider {
    public static final byte INS_CREATE_SS_ENTRY = -32;
    public static final byte INS_DELETE_ALL_SS_ENTRIES = -27;
    public static final byte INS_DELETE_SS_ENTRY = -28;
    public static final byte INS_GET_SS_ENTRY_DATA = -54;
    public static final byte INS_GET_SS_ENTRY_ID = -78;
    public static final byte INS_PING_SS_APPLET = -86;
    public static final byte INS_PUT_SS_ENTRY_DATA = -38;
    public static final byte INS_SELECT_SS_ENTRY = -91;
    public static final int MAX_TITLE_LENGTH = 60;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.simalliance.openmobileapi.SecureStorageProvider$1 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static /* synthetic */ class C135521 {

        /* renamed from: $SwitchMap$org$simalliance$openmobileapi$SecureStorageProvider$GetDataP1 */
        public static final /* synthetic */ int[] f27426x11d102c6;

        /* renamed from: $SwitchMap$org$simalliance$openmobileapi$SecureStorageProvider$PutDataP1 */
        public static final /* synthetic */ int[] f27427xc64bb7bf;

        /* renamed from: $SwitchMap$org$simalliance$openmobileapi$SecureStorageProvider$SelectP1 */
        public static final /* synthetic */ int[] f27428x2e231a78;

        static {
            int[] iArr = new int[GetDataP1.values().length];
            f27426x11d102c6 = iArr;
            try {
                iArr[GetDataP1.Size.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f27426x11d102c6[GetDataP1.First.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f27426x11d102c6[GetDataP1.Next.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[PutDataP1.values().length];
            f27427xc64bb7bf = iArr2;
            try {
                iArr2[PutDataP1.Size.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f27427xc64bb7bf[PutDataP1.First.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f27427xc64bb7bf[PutDataP1.Next.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[SelectP1.values().length];
            f27428x2e231a78 = iArr3;
            try {
                iArr3[SelectP1.Id.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f27428x2e231a78[SelectP1.First.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f27428x2e231a78[SelectP1.Next.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum GetDataP1 {
        Size,
        First,
        Next
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class ProcessingException extends Exception {
        public int mSwValue;

        public /* synthetic */ ProcessingException(SecureStorageProvider secureStorageProvider, int i, C135521 c135521) {
            this(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getSwValue() {
            return this.mSwValue;
        }

        public ProcessingException(int i) {
            this.mSwValue = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum PutDataP1 {
        Size,
        First,
        Next
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum SelectP1 {
        Id,
        First,
        Next
    }

    public SecureStorageProvider(Channel channel) {
        super(channel);
        if (!sendPingCommand()) {
            throw new IllegalStateException("Channel is not connected to a Secure Storage applet.");
        }
    }

    private int sendCreateEntryCommand(String str) {
        ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu(Byte.MIN_VALUE, (byte) -32, (byte) 0, (byte) 0, str.getBytes(), 2).toByteArray()));
        int swValue = responseApdu.getSwValue();
        if (swValue == 36864) {
            return ByteArrayConverter.byteArrayToInt(responseApdu.getData());
        }
        throw new ProcessingException(this, swValue, null);
    }

    private boolean sendDeleteEntryCommand(int i) {
        byte[] intToByteArray = ByteArrayConverter.intToByteArray(i);
        int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu(Byte.MIN_VALUE, (byte) -28, intToByteArray[2], intToByteArray[3]).toByteArray())).getSwValue();
        if (swValue == 36864) {
            return true;
        }
        if (swValue == 27272) {
            return false;
        }
        throw new ProcessingException(this, swValue, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private byte[] sendGetDataCommand(org.simalliance.openmobileapi.SecureStorageProvider.GetDataP1 r10) {
        /*
            r9 = this;
            int[] r0 = org.simalliance.openmobileapi.SecureStorageProvider.C135521.f27426x11d102c6
            int r10 = r10.ordinal()
            r10 = r0[r10]
            r0 = 2
            r1 = 1
            r2 = 0
            if (r10 == r1) goto L17
            if (r10 == r0) goto L15
            r1 = 3
            if (r10 == r1) goto L13
            goto L17
        L13:
            r6 = r0
            goto L18
        L15:
            r6 = r1
            goto L18
        L17:
            r6 = r2
        L18:
            org.simalliance.openmobileapi.util.CommandApdu r10 = new org.simalliance.openmobileapi.util.CommandApdu
            r4 = -128(0xffffffffffffff80, float:NaN)
            r5 = -54
            r7 = 0
            r8 = 0
            r3 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            org.simalliance.openmobileapi.util.ResponseApdu r0 = new org.simalliance.openmobileapi.util.ResponseApdu
            org.simalliance.openmobileapi.Channel r1 = r9.getChannel()
            byte[] r10 = r10.toByteArray()
            byte[] r10 = r1.transmit(r10)
            r0.<init>(r10)
            int r10 = r0.getSwValue()
            r1 = 36864(0x9000, float:5.1657E-41)
            if (r10 != r1) goto L43
            byte[] r10 = r0.getData()
            return r10
        L43:
            org.simalliance.openmobileapi.SecureStorageProvider$ProcessingException r0 = new org.simalliance.openmobileapi.SecureStorageProvider$ProcessingException
            r1 = 0
            r0.<init>(r9, r10, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simalliance.openmobileapi.SecureStorageProvider.sendGetDataCommand(org.simalliance.openmobileapi.SecureStorageProvider$GetDataP1):byte[]");
    }

    private int sendGetIdCommand(String str) {
        ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(new CommandApdu(Byte.MIN_VALUE, (byte) -78, (byte) 0, (byte) 0, str.getBytes(), 2).toByteArray()));
        int swValue = responseApdu.getSwValue();
        if (swValue == 36864) {
            return ByteArrayConverter.byteArrayToInt(responseApdu.getData());
        }
        throw new ProcessingException(this, swValue, null);
    }

    private boolean sendPingCommand() {
        try {
            return new ResponseApdu(getChannel().transmit(new CommandApdu(Byte.MIN_VALUE, (byte) -86, (byte) 0, (byte) 0).toByteArray())).getSwValue() == 36864;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sendPutDataCommand(org.simalliance.openmobileapi.SecureStorageProvider.PutDataP1 r10, byte[] r11) {
        /*
            r9 = this;
            int[] r0 = org.simalliance.openmobileapi.SecureStorageProvider.C135521.f27427xc64bb7bf
            int r10 = r10.ordinal()
            r10 = r0[r10]
            r0 = 2
            r1 = 1
            r2 = 0
            if (r10 == r1) goto L17
            if (r10 == r0) goto L15
            r1 = 3
            if (r10 == r1) goto L13
            goto L17
        L13:
            r6 = r0
            goto L18
        L15:
            r6 = r1
            goto L18
        L17:
            r6 = r2
        L18:
            org.simalliance.openmobileapi.util.CommandApdu r10 = new org.simalliance.openmobileapi.util.CommandApdu
            r4 = -128(0xffffffffffffff80, float:NaN)
            r5 = -38
            r7 = 0
            r3 = r10
            r8 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            org.simalliance.openmobileapi.util.ResponseApdu r11 = new org.simalliance.openmobileapi.util.ResponseApdu
            org.simalliance.openmobileapi.Channel r0 = r9.getChannel()
            byte[] r10 = r10.toByteArray()
            byte[] r10 = r0.transmit(r10)
            r11.<init>(r10)
            int r10 = r11.getSwValue()
            r11 = 36864(0x9000, float:5.1657E-41)
            if (r10 != r11) goto L3f
            return
        L3f:
            org.simalliance.openmobileapi.SecureStorageProvider$ProcessingException r11 = new org.simalliance.openmobileapi.SecureStorageProvider$ProcessingException
            r0 = 0
            r11.<init>(r9, r10, r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simalliance.openmobileapi.SecureStorageProvider.sendPutDataCommand(org.simalliance.openmobileapi.SecureStorageProvider$PutDataP1, byte[]):void");
    }

    private String sendSelectCommand(SelectP1 selectP1, int i) {
        CommandApdu commandApdu;
        int i2 = C135521.f27428x2e231a78[selectP1.ordinal()];
        if (i2 == 1) {
            byte[] bArr = new byte[2];
            System.arraycopy(ByteArrayConverter.intToByteArray(i), 2, bArr, 0, 2);
            commandApdu = new CommandApdu(Byte.MIN_VALUE, (byte) -91, (byte) 0, (byte) 0, bArr, 0);
        } else if (i2 != 2) {
            commandApdu = i2 != 3 ? null : new CommandApdu(Byte.MIN_VALUE, (byte) -91, (byte) 2, (byte) 0, 0);
        } else {
            commandApdu = new CommandApdu(Byte.MIN_VALUE, (byte) -91, (byte) 1, (byte) 0, 0);
        }
        ResponseApdu responseApdu = new ResponseApdu(getChannel().transmit(commandApdu.toByteArray()));
        int swValue = responseApdu.getSwValue();
        if (swValue == 36864) {
            return new String(responseApdu.getData(), "UTF-8");
        }
        throw new ProcessingException(this, swValue, null);
    }

    public void create(String str, byte[] bArr) {
        PutDataP1 putDataP1;
        if (str != null) {
            if (str.length() != 0) {
                if (str.length() <= 60) {
                    try {
                        int sendCreateEntryCommand = sendCreateEntryCommand(str);
                        if (bArr == null || bArr.length == 0) {
                            return;
                        }
                        try {
                            sendSelectCommand(sendCreateEntryCommand);
                            try {
                                sendPutDataCommand(bArr.length);
                                int i = 0;
                                while (i < bArr.length) {
                                    int length = bArr.length - i;
                                    if (length >= 255) {
                                        length = 255;
                                    }
                                    byte[] bArr2 = new byte[length];
                                    System.arraycopy(bArr, i, bArr2, 0, length);
                                    if (i == 0) {
                                        putDataP1 = PutDataP1.First;
                                    } else {
                                        putDataP1 = PutDataP1.Next;
                                    }
                                    try {
                                        sendPutDataCommand(putDataP1, bArr2);
                                        i += length;
                                    } catch (IOException e) {
                                        try {
                                            sendDeleteEntryCommand(sendCreateEntryCommand);
                                        } catch (ProcessingException unused) {
                                        }
                                        throw e;
                                    } catch (ProcessingException e2) {
                                        try {
                                            sendDeleteEntryCommand(sendCreateEntryCommand);
                                        } catch (ProcessingException unused2) {
                                        }
                                        if (e2.getSwValue() != 25985) {
                                            if (e2.getSwValue() != 27268) {
                                                throw new IOException("Create process failed. Put data operation failed: " + ErrorStrings.unexpectedStatusWord(e2.getSwValue()));
                                            }
                                            throw new IllegalArgumentException("Not enough memory space");
                                        }
                                        throw new IllegalArgumentException("File creation failed due to memory issues.");
                                    }
                                }
                                return;
                            } catch (IOException e3) {
                                try {
                                    sendDeleteEntryCommand(sendCreateEntryCommand);
                                } catch (ProcessingException unused3) {
                                }
                                throw e3;
                            } catch (ProcessingException e4) {
                                try {
                                    sendDeleteEntryCommand(sendCreateEntryCommand);
                                } catch (ProcessingException unused4) {
                                }
                                if (e4.getSwValue() != 25985) {
                                    throw new IOException("Create process failed. Put data operation failed: " + ErrorStrings.unexpectedStatusWord(e4.getSwValue()));
                                }
                                throw new IllegalArgumentException("File creation failed due to memory issues.");
                            }
                        } catch (IOException e5) {
                            try {
                                sendDeleteEntryCommand(sendCreateEntryCommand);
                            } catch (ProcessingException unused5) {
                            }
                            throw e5;
                        } catch (ProcessingException e6) {
                            try {
                                sendDeleteEntryCommand(sendCreateEntryCommand);
                            } catch (ProcessingException unused6) {
                            }
                            throw new IOException("Create process failed. Select operation failed: " + ErrorStrings.unexpectedStatusWord(e6.getSwValue()));
                        }
                    } catch (ProcessingException e7) {
                        int swValue = e7.getSwValue();
                        if (swValue != 25985) {
                            if (swValue != 27010) {
                                if (swValue == 27264) {
                                    throw new IllegalArgumentException("The specified title already exists.");
                                }
                                if (swValue != 27266) {
                                    if (swValue != 27268) {
                                        throw new IOException(ErrorStrings.unexpectedStatusWord(e7.getSwValue()));
                                    }
                                    throw new IOException("Not enough memory space");
                                }
                            }
                            throw new SecurityException("Security status not satisfied.");
                        }
                        throw new IOException("File creation failed due to memory issues.");
                    }
                }
                throw new IllegalArgumentException("The title is too long (max value 60 chars).");
            }
            throw new IllegalArgumentException("The title is empty");
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("title"));
    }

    public boolean delete(String str) {
        if (str != null) {
            if (str.length() != 0) {
                if (str.length() <= 60) {
                    try {
                        try {
                            return sendDeleteEntryCommand(sendGetIdCommand(str));
                        } catch (ProcessingException e) {
                            int swValue = e.getSwValue();
                            if (swValue != 25985) {
                                if (swValue != 27010) {
                                    throw new IOException(ErrorStrings.unexpectedStatusWord(e.getSwValue()));
                                }
                                throw new SecurityException("Security status not satisfied.");
                            }
                            throw new IOException("Memory failure.");
                        }
                    } catch (ProcessingException e2) {
                        int swValue2 = e2.getSwValue();
                        if (swValue2 == 27010 || swValue2 == 27266) {
                            throw new SecurityException("Security status not satisfied.");
                        }
                        if (swValue2 == 27272) {
                            return false;
                        }
                        throw new IOException(ErrorStrings.unexpectedStatusWord(e2.getSwValue()));
                    }
                }
                throw new IllegalArgumentException("The title is too long (max value 60 chars).");
            }
            throw new IllegalArgumentException("The title is empty");
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("title"));
    }

    public void deleteAll() {
        int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu(Byte.MIN_VALUE, (byte) -27, (byte) 0, (byte) 0).toByteArray())).getSwValue();
        if (swValue == 25985) {
            throw new IOException("Memory failure.");
        }
        if (swValue == 27010 || swValue == 27266) {
            throw new SecurityException("Security status not satisfied.");
        }
        if (swValue != 36864) {
            throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
        }
    }

    public boolean exist(String str) {
        if (str != null) {
            if (str.length() != 0) {
                if (str.length() <= 60) {
                    try {
                        sendGetIdCommand(str);
                        return true;
                    } catch (ProcessingException e) {
                        int swValue = e.getSwValue();
                        if (swValue == 27010 || swValue == 27266) {
                            throw new SecurityException("Security status not satisfied.");
                        }
                        if (swValue == 27272) {
                            return false;
                        }
                        throw new IOException(ErrorStrings.unexpectedStatusWord(e.getSwValue()));
                    }
                }
                throw new IllegalArgumentException("The title is too long (max value 60 chars).");
            }
            throw new IllegalArgumentException("The title is empty");
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("title"));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0029 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0023 -> B:13:0x0027). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String[] list() {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 27272(0x6a88, float:3.8216E-41)
            r2 = 27010(0x6982, float:3.7849E-41)
            org.simalliance.openmobileapi.SecureStorageProvider$SelectP1 r3 = org.simalliance.openmobileapi.SecureStorageProvider.SelectP1.First     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L15
            java.lang.String r3 = r7.sendSelectCommand(r3)     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L15
            r0.add(r3)     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L15
            r3 = 0
            r4 = r7
            goto L27
        L15:
            r3 = move-exception
            int r4 = org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException.access$000(r3)
            if (r4 == r2) goto L6f
            r5 = 27266(0x6a82, float:3.8208E-41)
            if (r4 == r5) goto L6f
            if (r4 != r1) goto L61
            r3 = r7
        L23:
            r4 = 1
            r6 = r4
            r4 = r3
            r3 = r6
        L27:
            if (r3 != 0) goto L54
            org.simalliance.openmobileapi.SecureStorageProvider$SelectP1 r5 = org.simalliance.openmobileapi.SecureStorageProvider.SelectP1.Next     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L33
            java.lang.String r5 = r4.sendSelectCommand(r5)     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L33
            r0.add(r5)     // Catch: org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException -> L33
            goto L27
        L33:
            r3 = move-exception
            int r5 = org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException.access$000(r3)
            if (r5 == r2) goto L4c
            if (r5 != r1) goto L3e
            r3 = r4
            goto L23
        L3e:
            java.io.IOException r0 = new java.io.IOException
            int r1 = org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException.access$000(r3)
            java.lang.String r1 = org.simalliance.openmobileapi.internal.ErrorStrings.unexpectedStatusWord(r1)
            r0.<init>(r1)
            throw r0
        L4c:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "Security status not satisfied."
            r0.<init>(r1)
            throw r0
        L54:
            int r1 = r0.size()
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            java.lang.String[] r0 = (java.lang.String[]) r0
            return r0
        L61:
            java.io.IOException r0 = new java.io.IOException
            int r1 = org.simalliance.openmobileapi.SecureStorageProvider.ProcessingException.access$000(r3)
            java.lang.String r1 = org.simalliance.openmobileapi.internal.ErrorStrings.unexpectedStatusWord(r1)
            r0.<init>(r1)
            throw r0
        L6f:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "Security status not satisfied."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simalliance.openmobileapi.SecureStorageProvider.list():java.lang.String[]");
    }

    public byte[] read(String str) {
        GetDataP1 getDataP1;
        if (str != null) {
            if (str.length() != 0 && str.length() <= 60) {
                try {
                    try {
                        sendSelectCommand(sendGetIdCommand(str));
                        try {
                            int byteArrayToInt = ByteArrayConverter.byteArrayToInt(sendGetDataCommand(GetDataP1.Size));
                            byte[] bArr = new byte[byteArrayToInt];
                            int i = 0;
                            while (i < byteArrayToInt) {
                                if (i == 0) {
                                    getDataP1 = GetDataP1.First;
                                } else {
                                    getDataP1 = GetDataP1.Next;
                                }
                                try {
                                    byte[] sendGetDataCommand = sendGetDataCommand(getDataP1);
                                    System.arraycopy(sendGetDataCommand, 0, bArr, i, sendGetDataCommand.length);
                                    i += sendGetDataCommand.length;
                                } catch (ProcessingException e) {
                                    if (e.getSwValue() != 27010) {
                                        throw new IOException(ErrorStrings.unexpectedStatusWord(e.getSwValue()));
                                    }
                                    throw new SecurityException("Security status not satisfied.");
                                }
                            }
                            return bArr;
                        } catch (ProcessingException e2) {
                            if (e2.getSwValue() != 27010) {
                                throw new IOException(ErrorStrings.unexpectedStatusWord(e2.getSwValue()));
                            }
                            throw new SecurityException("Security status not satisfied.");
                        }
                    } catch (ProcessingException e3) {
                        if (e3.getSwValue() != 27010) {
                            throw new IOException(ErrorStrings.unexpectedStatusWord(e3.getSwValue()));
                        }
                        throw new SecurityException("Security status not satisfied.");
                    }
                } catch (ProcessingException e4) {
                    int swValue = e4.getSwValue();
                    if (swValue == 27010 || swValue == 27266) {
                        throw new SecurityException("Security status not satisfied.");
                    }
                    if (swValue == 27272) {
                        return new byte[0];
                    }
                    throw new IOException(ErrorStrings.unexpectedStatusWord(e4.getSwValue()));
                }
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("title"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("title"));
    }

    public void update(String str, byte[] bArr) {
        byte[] bArr2;
        PutDataP1 putDataP1;
        if (str != null) {
            if (str.length() != 0) {
                if (str.length() <= 60) {
                    if (bArr == null) {
                        bArr = new byte[0];
                    }
                    try {
                        int sendGetIdCommand = sendGetIdCommand(str);
                        try {
                            sendSelectCommand(sendGetIdCommand);
                            byte[] read = read(str);
                            try {
                                sendPutDataCommand(bArr.length);
                                int i = 0;
                                while (i < bArr.length) {
                                    int length = bArr.length - i;
                                    if (length < 65535) {
                                        bArr2 = new byte[length];
                                    } else {
                                        bArr2 = new byte[length];
                                    }
                                    System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
                                    if (i == 0) {
                                        putDataP1 = PutDataP1.First;
                                    } else {
                                        putDataP1 = PutDataP1.Next;
                                    }
                                    try {
                                        sendPutDataCommand(putDataP1, bArr2);
                                        i += bArr2.length;
                                    } catch (IOException e) {
                                        try {
                                            sendDeleteEntryCommand(sendGetIdCommand);
                                            create(str, read);
                                        } catch (ProcessingException unused) {
                                        }
                                        throw e;
                                    } catch (ProcessingException e2) {
                                        try {
                                            sendDeleteEntryCommand(sendGetIdCommand);
                                            create(str, read);
                                        } catch (ProcessingException unused2) {
                                        }
                                        if (e2.getSwValue() != 25985) {
                                            if (e2.getSwValue() != 27268) {
                                                throw new IOException("Update process failed. Put data operation failed: " + ErrorStrings.unexpectedStatusWord(e2.getSwValue()));
                                            }
                                            throw new IllegalArgumentException("Not enough memory space");
                                        }
                                        throw new IllegalArgumentException("File creation failed due to memory issues.");
                                    }
                                }
                                return;
                            } catch (ProcessingException e3) {
                                int swValue = e3.getSwValue();
                                if (swValue == 25985) {
                                    throw new IllegalArgumentException("File creation failed due to memory issues.");
                                }
                                if (swValue != 27268) {
                                    throw new IOException(ErrorStrings.unexpectedStatusWord(e3.getSwValue()));
                                }
                                throw new IllegalArgumentException("Not enough memory space");
                            }
                        } catch (ProcessingException e4) {
                            if (e4.getSwValue() != 27010) {
                                throw new IOException(ErrorStrings.unexpectedStatusWord(e4.getSwValue()));
                            }
                            throw new SecurityException("Security status not satisfied.");
                        }
                    } catch (ProcessingException e5) {
                        int swValue2 = e5.getSwValue();
                        if (swValue2 == 27010 || swValue2 == 27266) {
                            throw new SecurityException("Security status not satisfied.");
                        }
                        if (swValue2 != 27272) {
                            throw new IOException(ErrorStrings.unexpectedStatusWord(e5.getSwValue()));
                        }
                        throw new IllegalArgumentException("Referenced data not found (if no SeS entry is currently selected");
                    }
                }
                throw new IllegalArgumentException("The title is too long (max value 60 chars).");
            }
            throw new IllegalArgumentException("The title is empty");
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("title"));
    }

    private void sendPutDataCommand(int i) {
        byte[] bArr = new byte[2];
        System.arraycopy(ByteArrayConverter.intToByteArray(i), 2, bArr, 0, 2);
        sendPutDataCommand(PutDataP1.Size, bArr);
    }

    private String sendSelectCommand(SelectP1 selectP1) {
        if (selectP1 != SelectP1.Id) {
            return sendSelectCommand(selectP1, 0);
        }
        throw new IllegalArgumentException();
    }

    private String sendSelectCommand(int i) {
        return sendSelectCommand(SelectP1.Id, i);
    }
}
