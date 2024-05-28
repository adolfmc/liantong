package org.simalliance.openmobileapi;

import java.io.IOException;
import org.simalliance.openmobileapi.internal.ErrorStrings;
import org.simalliance.openmobileapi.util.CommandApdu;
import org.simalliance.openmobileapi.util.ResponseApdu;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AuthenticationProvider extends Provider {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public class PinID {
        public static final int MAX_ID_VALUE = 31;
        public static final int MIN_ID_VALUE = 0;
        public int mId;
        public boolean mLocal;

        public PinID(int i, boolean z) {
            if (i >= 0 && i <= 31) {
                this.mId = i;
                this.mLocal = z;
                return;
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("id"));
        }

        public final int getID() {
            return this.mId;
        }

        public final boolean isLocal() {
            return this.mLocal;
        }
    }

    public AuthenticationProvider(Channel channel) {
        super(channel);
    }

    public void activatePin(PinID pinID, byte[] bArr) {
        CommandApdu commandApdu;
        if (pinID != null) {
            if (bArr != null && (bArr.length == 0 || bArr.length > 65535)) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("pin"));
            }
            byte id = (byte) pinID.getID();
            if (pinID.isLocal()) {
                id = (byte) (id | 128);
            }
            byte b = id;
            if (bArr != null) {
                if (bArr.length <= 255) {
                    commandApdu = new CommandApdu((byte) 0, (byte) 40, (byte) 0, b, bArr);
                } else {
                    throw new IllegalArgumentException("PIN too long");
                }
            } else {
                commandApdu = new CommandApdu((byte) 0, (byte) 40, (byte) 1, b);
            }
            int swValue = new ResponseApdu(getChannel().transmit(commandApdu.toByteArray())).getSwValue();
            if (swValue == 25344) {
                throw new SecurityException("Wrong PIN.");
            }
            if (swValue == 27265) {
                throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
            }
            if (swValue == 27270) {
                if (bArr != null) {
                    throw new IllegalArgumentException("Referenced PIN could not be found.");
                }
                throw new IllegalArgumentException("Either pinID is wrong, or this pin does not support activation with pin = null.");
            } else if (swValue == 27272) {
                throw new IllegalArgumentException("Referenced PIN could not be found.");
            } else {
                if (swValue == 27392) {
                    if (bArr != null) {
                        throw new IllegalArgumentException("Referenced PIN could not be found.");
                    }
                    throw new IllegalArgumentException("Either pinID is wrong, or this pin does not support activation with pin = null.");
                } else if (swValue == 27904) {
                    throw new UnsupportedOperationException("This operation is not supported by the selected Applet.");
                } else {
                    if (swValue != 36864) {
                        switch (swValue) {
                            case 27010:
                                throw new SecurityException("Security status not satisfied.");
                            case 27011:
                                throw new SecurityException("PIN is blocked.");
                            default:
                                if (25536 <= swValue && swValue <= 25551) {
                                    throw new SecurityException("Wrong PIN.");
                                }
                                throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                        }
                    }
                    return;
                }
            }
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
    }

    public void changePin(PinID pinID, byte[] bArr, byte[] bArr2) {
        if (pinID == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
        }
        if (bArr == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("oldPin"));
        }
        if (bArr2 != null) {
            if (bArr.length != 0) {
                if (bArr2.length != 0) {
                    byte id = (byte) pinID.getID();
                    if (pinID.isLocal()) {
                        id = (byte) (id | 128);
                    }
                    byte b = id;
                    int length = bArr.length + bArr2.length;
                    byte[] bArr3 = new byte[length];
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
                    if (length <= 255) {
                        int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) 36, (byte) 0, b, bArr3).toByteArray())).getSwValue();
                        if (swValue == 25344) {
                            throw new SecurityException("Wrong PIN.");
                        }
                        if (swValue == 27265) {
                            throw new UnsupportedOperationException();
                        }
                        if (swValue == 27270) {
                            throw new IllegalArgumentException("Referenced PIN could not be found.");
                        }
                        if (swValue == 27392) {
                            throw new IllegalArgumentException("Referenced PIN could not be found.");
                        }
                        if (swValue == 27904) {
                            throw new UnsupportedOperationException();
                        }
                        if (swValue != 36864) {
                            switch (swValue) {
                                case 27010:
                                    throw new SecurityException("Security status not satisfied.");
                                case 27011:
                                    throw new SecurityException("Referenced PIN is blocked.");
                                case 27012:
                                    throw new SecurityException("Referenced PIN is blocked.");
                                default:
                                    if (25536 <= swValue && swValue <= 25551) {
                                        throw new SecurityException("Wrong PIN.");
                                    }
                                    throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                            }
                        }
                        return;
                    }
                    throw new IllegalArgumentException("PIN too long");
                }
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("newPin"));
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("oldPin"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("newPin"));
    }

    public void deactivatePin(PinID pinID, byte[] bArr) {
        CommandApdu commandApdu;
        if (pinID != null) {
            if (bArr != null && (bArr.length == 0 || bArr.length > 65535)) {
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("pin"));
            }
            byte id = (byte) pinID.getID();
            if (pinID.isLocal()) {
                id = (byte) (id | 128);
            }
            byte b = id;
            if (bArr != null) {
                if (bArr.length <= 255) {
                    commandApdu = new CommandApdu((byte) 0, (byte) 38, (byte) 0, b, bArr);
                } else {
                    throw new IllegalArgumentException("PIN too long");
                }
            } else {
                commandApdu = new CommandApdu((byte) 0, (byte) 38, (byte) 1, b);
            }
            int swValue = new ResponseApdu(getChannel().transmit(commandApdu.toByteArray())).getSwValue();
            if (swValue == 25344) {
                throw new SecurityException("Wrong PIN.");
            }
            if (swValue == 27265) {
                throw new UnsupportedOperationException();
            }
            if (swValue == 27270) {
                throw new IllegalArgumentException("Referenced PIN could not be found.");
            }
            if (swValue == 27392) {
                throw new IllegalArgumentException("Referenced PIN could not be found.");
            }
            if (swValue == 27904) {
                throw new UnsupportedOperationException();
            }
            if (swValue != 36864) {
                switch (swValue) {
                    case 27010:
                        throw new SecurityException("Security status not satisfied.");
                    case 27011:
                        throw new SecurityException("Referenced PIN is blocked.");
                    case 27012:
                        throw new SecurityException("Referenced PIN is blocked.");
                    default:
                        if (25536 <= swValue && swValue <= 25551) {
                            throw new SecurityException("Wrong PIN.");
                        }
                        throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                }
            }
            return;
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
    }

    public int getRetryCounter(PinID pinID) {
        if (pinID != null) {
            byte id = (byte) pinID.getID();
            if (pinID.isLocal()) {
                id = (byte) (id | 128);
            }
            int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) 32, (byte) 0, id).toByteArray())).getSwValue();
            if (swValue != 25344) {
                if (swValue != 27265) {
                    if (swValue != 27270) {
                        if (swValue != 27392) {
                            if (swValue != 27904) {
                                if (swValue == 36864) {
                                    throw new UnsupportedOperationException();
                                }
                                switch (swValue) {
                                    case 27011:
                                        throw new SecurityException("Referenced PIN is blocked.");
                                    case 27012:
                                        throw new SecurityException("Referenced PIN is blocked.");
                                    default:
                                        if (25536 > swValue || swValue > 25551) {
                                            throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                                        }
                                        return swValue & 15;
                                }
                            }
                            throw new UnsupportedOperationException();
                        }
                        throw new IllegalArgumentException("Referenced PIN could not be found.");
                    }
                    throw new IllegalArgumentException("Referenced PIN could not be found.");
                }
                throw new UnsupportedOperationException();
            }
            throw new SecurityException("Wrong PIN.");
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
    }

    public void resetPin(PinID pinID, byte[] bArr, byte[] bArr2) {
        byte[] bArr3;
        if (pinID == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
        }
        if (bArr != null) {
            if (bArr.length != 0) {
                if (bArr2 != null && bArr2.length == 0) {
                    throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("newPin"));
                }
                byte b = bArr2 != null ? (byte) 0 : (byte) 1;
                byte id = (byte) pinID.getID();
                byte b2 = pinID.isLocal() ? (byte) (id | 128) : id;
                if (bArr2 != null) {
                    bArr3 = new byte[bArr.length + bArr2.length];
                } else {
                    bArr3 = new byte[bArr.length];
                }
                if (bArr3.length <= 255) {
                    System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
                    if (bArr2 != null) {
                        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
                    }
                    int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) 44, b, b2, bArr3).toByteArray())).getSwValue();
                    if (swValue == 25344) {
                        throw new SecurityException("Wrong PIN.");
                    }
                    if (swValue == 27265) {
                        throw new UnsupportedOperationException();
                    }
                    if (swValue == 27270) {
                        throw new IllegalArgumentException("Referenced PIN could not be found.");
                    }
                    if (swValue == 27392) {
                        throw new IllegalArgumentException("Referenced PIN could not be found.");
                    }
                    if (swValue == 27904) {
                        throw new UnsupportedOperationException();
                    }
                    if (swValue != 36864) {
                        switch (swValue) {
                            case 27010:
                                throw new SecurityException("Security status not satisfied.");
                            case 27011:
                                throw new SecurityException("Referenced PIN is blocked.");
                            case 27012:
                                throw new SecurityException("Referenced PIN is blocked.");
                            default:
                                if (25536 <= swValue && swValue <= 25551) {
                                    throw new SecurityException("Wrong PIN.");
                                }
                                throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                        }
                    }
                    return;
                }
                throw new IllegalArgumentException("PIN too long");
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("resetPin"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("resetPin"));
    }

    public boolean verifyPin(PinID pinID, byte[] bArr) {
        if (pinID != null) {
            if (bArr != null) {
                if (bArr.length != 0 && bArr.length <= 65535) {
                    byte id = (byte) pinID.getID();
                    if (pinID.isLocal()) {
                        id = (byte) (id | 128);
                    }
                    byte b = id;
                    if (bArr.length <= 255) {
                        int swValue = new ResponseApdu(getChannel().transmit(new CommandApdu((byte) 0, (byte) 32, (byte) 0, b, bArr).toByteArray())).getSwValue();
                        if (swValue == 25344 || swValue == 26368) {
                            return false;
                        }
                        if (swValue != 27265) {
                            if (swValue != 27270) {
                                if (swValue != 27272) {
                                    if (swValue != 27392) {
                                        if (swValue != 27904) {
                                            if (swValue != 36864) {
                                                switch (swValue) {
                                                    case 27011:
                                                    case 27012:
                                                        return false;
                                                    default:
                                                        if (25536 > swValue || swValue > 25551) {
                                                            throw new IOException(ErrorStrings.unexpectedStatusWord(swValue));
                                                        }
                                                        return false;
                                                }
                                            }
                                            return true;
                                        }
                                        throw new UnsupportedOperationException();
                                    }
                                    throw new IllegalArgumentException("Referenced PIN could not be found.");
                                }
                                throw new IllegalArgumentException("Referenced PIN could not be found.");
                            }
                            throw new IllegalArgumentException("Referenced PIN could not be found.");
                        }
                        throw new UnsupportedOperationException();
                    }
                    throw new IllegalArgumentException("PIN too long");
                }
                throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("pin"));
            }
            throw new IllegalArgumentException(ErrorStrings.paramNull("pin"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("pinID"));
    }
}
