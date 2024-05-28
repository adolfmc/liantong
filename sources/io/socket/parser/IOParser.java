package io.socket.parser;

import io.socket.hasbinary.HasBinary;
import io.socket.parser.Binary;
import io.socket.parser.Parser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONTokener;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class IOParser implements Parser {
    private static final Logger logger = Logger.getLogger(IOParser.class.getName());

    static /* synthetic */ Packet access$100() {
        return error();
    }

    private static Packet<String> error() {
        return new Packet<>(4, "parser error");
    }

    private IOParser() {
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class Encoder implements Parser.Encoder {
        @Override // io.socket.parser.Parser.Encoder
        public void encode(Packet packet, Parser.Encoder.Callback callback) {
            if ((packet.type == 2 || packet.type == 3) && HasBinary.hasBinary(packet.data)) {
                packet.type = packet.type == 2 ? 5 : 6;
            }
            if (IOParser.logger.isLoggable(Level.FINE)) {
                IOParser.logger.fine(String.format("encoding packet %s", packet));
            }
            if (5 == packet.type || 6 == packet.type) {
                encodeAsBinary(packet, callback);
            } else {
                callback.call(new String[]{encodeAsString(packet)});
            }
        }

        private String encodeAsString(Packet packet) {
            StringBuilder sb = new StringBuilder("" + packet.type);
            if (5 == packet.type || 6 == packet.type) {
                sb.append(packet.attachments);
                sb.append("-");
            }
            if (packet.nsp != null && packet.nsp.length() != 0 && !"/".equals(packet.nsp)) {
                sb.append(packet.nsp);
                sb.append(",");
            }
            if (packet.f24819id >= 0) {
                sb.append(packet.f24819id);
            }
            if (packet.data != 0) {
                sb.append(packet.data);
            }
            if (IOParser.logger.isLoggable(Level.FINE)) {
                IOParser.logger.fine(String.format("encoded %s as %s", packet, sb));
            }
            return sb.toString();
        }

        private void encodeAsBinary(Packet packet, Parser.Encoder.Callback callback) {
            Binary.DeconstructedPacket deconstructPacket = Binary.deconstructPacket(packet);
            String encodeAsString = encodeAsString(deconstructPacket.packet);
            ArrayList arrayList = new ArrayList(Arrays.asList(deconstructPacket.buffers));
            arrayList.add(0, encodeAsString);
            callback.call(arrayList.toArray());
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class Decoder implements Parser.Decoder {
        private Parser.Decoder.Callback onDecodedCallback;
        BinaryReconstructor reconstructor = null;

        @Override // io.socket.parser.Parser.Decoder
        public void add(String str) {
            Parser.Decoder.Callback callback;
            Packet decodeString = decodeString(str);
            if (5 == decodeString.type || 6 == decodeString.type) {
                this.reconstructor = new BinaryReconstructor(decodeString);
                if (this.reconstructor.reconPack.attachments != 0 || (callback = this.onDecodedCallback) == null) {
                    return;
                }
                callback.call(decodeString);
                return;
            }
            Parser.Decoder.Callback callback2 = this.onDecodedCallback;
            if (callback2 != null) {
                callback2.call(decodeString);
            }
        }

        @Override // io.socket.parser.Parser.Decoder
        public void add(byte[] bArr) {
            BinaryReconstructor binaryReconstructor = this.reconstructor;
            if (binaryReconstructor == null) {
                throw new RuntimeException("got binary data when not reconstructing a packet");
            }
            Packet takeBinaryData = binaryReconstructor.takeBinaryData(bArr);
            if (takeBinaryData != null) {
                this.reconstructor = null;
                Parser.Decoder.Callback callback = this.onDecodedCallback;
                if (callback != null) {
                    callback.call(takeBinaryData);
                }
            }
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
        private static Packet decodeString(String str) {
            int i;
            int length = str.length();
            Packet packet = new Packet(Character.getNumericValue(str.charAt(0)));
            if (packet.type < 0 || packet.type > Parser.types.length - 1) {
                return IOParser.access$100();
            }
            if (5 != packet.type && 6 != packet.type) {
                i = 0;
            } else if (!str.contains("-") || length <= 1) {
                return IOParser.access$100();
            } else {
                StringBuilder sb = new StringBuilder();
                i = 0;
                while (true) {
                    i++;
                    if (str.charAt(i) == '-') {
                        break;
                    }
                    sb.append(str.charAt(i));
                }
                packet.attachments = Integer.parseInt(sb.toString());
            }
            int i2 = i + 1;
            if (length > i2 && '/' == str.charAt(i2)) {
                StringBuilder sb2 = new StringBuilder();
                do {
                    i++;
                    char charAt = str.charAt(i);
                    if (',' == charAt) {
                        break;
                    }
                    sb2.append(charAt);
                } while (i + 1 != length);
                packet.nsp = sb2.toString();
            } else {
                packet.nsp = "/";
            }
            int i3 = i + 1;
            if (length > i3 && Character.getNumericValue(Character.valueOf(str.charAt(i3)).charValue()) > -1) {
                StringBuilder sb3 = new StringBuilder();
                do {
                    i++;
                    char charAt2 = str.charAt(i);
                    if (Character.getNumericValue(charAt2) < 0) {
                        i--;
                        break;
                    }
                    sb3.append(charAt2);
                } while (i + 1 != length);
                try {
                    packet.f24819id = Integer.parseInt(sb3.toString());
                } catch (NumberFormatException unused) {
                    return IOParser.access$100();
                }
            }
            int i4 = i + 1;
            if (length > i4) {
                try {
                    str.charAt(i4);
                    packet.data = new JSONTokener(str.substring(i4)).nextValue();
                } catch (JSONException e) {
                    IOParser.logger.log(Level.WARNING, "An error occured while retrieving data from JSONTokener", (Throwable) e);
                    return IOParser.access$100();
                }
            }
            if (IOParser.logger.isLoggable(Level.FINE)) {
                IOParser.logger.fine(String.format("decoded %s as %s", str, packet));
            }
            return packet;
        }

        @Override // io.socket.parser.Parser.Decoder
        public void destroy() {
            BinaryReconstructor binaryReconstructor = this.reconstructor;
            if (binaryReconstructor != null) {
                binaryReconstructor.finishReconstruction();
            }
            this.onDecodedCallback = null;
        }

        @Override // io.socket.parser.Parser.Decoder
        public void onDecoded(Parser.Decoder.Callback callback) {
            this.onDecodedCallback = callback;
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class BinaryReconstructor {
        List<byte[]> buffers = new ArrayList();
        public Packet reconPack;

        BinaryReconstructor(Packet packet) {
            this.reconPack = packet;
        }

        public Packet takeBinaryData(byte[] bArr) {
            this.buffers.add(bArr);
            if (this.buffers.size() == this.reconPack.attachments) {
                Packet packet = this.reconPack;
                List<byte[]> list = this.buffers;
                Packet reconstructPacket = Binary.reconstructPacket(packet, (byte[][]) list.toArray(new byte[list.size()]));
                finishReconstruction();
                return reconstructPacket;
            }
            return null;
        }

        public void finishReconstruction() {
            this.reconPack = null;
            this.buffers = new ArrayList();
        }
    }
}
