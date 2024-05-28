package org.java_websocket.drafts;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.IncompleteHandshakeException;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.CloseFrameBuilder;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Draft_76 extends Draft_75 {
    private static final byte[] closehandshake = {-1, 0};
    private boolean failed = false;
    private final Random reuseableRandom = new Random();

    public static byte[] createChallenge(String str, String str2, byte[] bArr) throws InvalidHandshakeException {
        byte[] part = getPart(str);
        byte[] part2 = getPart(str2);
        try {
            return MessageDigest.getInstance("MD5").digest(new byte[]{part[0], part[1], part[2], part[3], part2[0], part2[1], part2[2], part2[3], bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generateKey() {
        Random random = new Random();
        long nextInt = random.nextInt(12) + 1;
        String l = Long.toString((random.nextInt(Math.abs(new Long(4294967295L / nextInt).intValue())) + 1) * nextInt);
        int nextInt2 = random.nextInt(12) + 1;
        String str = l;
        for (int i = 0; i < nextInt2; i++) {
            int abs = Math.abs(random.nextInt(str.length()));
            char nextInt3 = (char) (random.nextInt(95) + 33);
            if (nextInt3 >= '0' && nextInt3 <= '9') {
                nextInt3 = (char) (nextInt3 - 15);
            }
            str = new StringBuilder(str).insert(abs, nextInt3).toString();
        }
        for (int i2 = 0; i2 < nextInt; i2++) {
            str = new StringBuilder(str).insert(Math.abs(random.nextInt(str.length() - 1) + 1), " ").toString();
        }
        return str;
    }

    private static byte[] getPart(String str) throws InvalidHandshakeException {
        try {
            long parseLong = Long.parseLong(str.replaceAll("[^0-9]", ""));
            long length = str.split(" ").length - 1;
            if (length == 0) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
            long longValue = new Long(parseLong / length).longValue();
            return new byte[]{(byte) (longValue >> 24), (byte) ((longValue << 8) >> 24), (byte) ((longValue << 16) >> 24), (byte) ((longValue << 24) >> 24)};
        } catch (NumberFormatException unused) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public Draft.HandshakeState acceptHandshakeAsClient(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        if (this.failed) {
            return Draft.HandshakeState.NOT_MATCHED;
        }
        try {
            if (serverHandshake.getFieldValue("Sec-WebSocket-Origin").equals(clientHandshake.getFieldValue("Origin")) && basicAccept(serverHandshake)) {
                byte[] content = serverHandshake.getContent();
                if (content == null || content.length == 0) {
                    throw new IncompleteHandshakeException();
                }
                if (Arrays.equals(content, createChallenge(clientHandshake.getFieldValue("Sec-WebSocket-Key1"), clientHandshake.getFieldValue("Sec-WebSocket-Key2"), clientHandshake.getContent()))) {
                    return Draft.HandshakeState.MATCHED;
                }
                return Draft.HandshakeState.NOT_MATCHED;
            }
            return Draft.HandshakeState.NOT_MATCHED;
        } catch (InvalidHandshakeException e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public Draft.HandshakeState acceptHandshakeAsServer(ClientHandshake clientHandshake) {
        if (clientHandshake.getFieldValue("Upgrade").equals("WebSocket") && clientHandshake.getFieldValue("Connection").contains("Upgrade") && clientHandshake.getFieldValue("Sec-WebSocket-Key1").length() > 0 && !clientHandshake.getFieldValue("Sec-WebSocket-Key2").isEmpty() && clientHandshake.hasFieldValue("Origin")) {
            return Draft.HandshakeState.MATCHED;
        }
        return Draft.HandshakeState.NOT_MATCHED;
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.put("Upgrade", "WebSocket");
        clientHandshakeBuilder.put("Connection", "Upgrade");
        clientHandshakeBuilder.put("Sec-WebSocket-Key1", generateKey());
        clientHandshakeBuilder.put("Sec-WebSocket-Key2", generateKey());
        if (!clientHandshakeBuilder.hasFieldValue("Origin")) {
            clientHandshakeBuilder.put("Origin", "random" + this.reuseableRandom.nextInt());
        }
        byte[] bArr = new byte[8];
        this.reuseableRandom.nextBytes(bArr);
        clientHandshakeBuilder.setContent(bArr);
        return clientHandshakeBuilder;
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.setHttpStatusMessage("WebSocket Protocol Handshake");
        serverHandshakeBuilder.put("Upgrade", "WebSocket");
        serverHandshakeBuilder.put("Connection", clientHandshake.getFieldValue("Connection"));
        serverHandshakeBuilder.put("Sec-WebSocket-Origin", clientHandshake.getFieldValue("Origin"));
        serverHandshakeBuilder.put("Sec-WebSocket-Location", "ws://" + clientHandshake.getFieldValue("Host") + clientHandshake.getResourceDescriptor());
        String fieldValue = clientHandshake.getFieldValue("Sec-WebSocket-Key1");
        String fieldValue2 = clientHandshake.getFieldValue("Sec-WebSocket-Key2");
        byte[] content = clientHandshake.getContent();
        if (fieldValue == null || fieldValue2 == null || content == null || content.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        serverHandshakeBuilder.setContent(createChallenge(fieldValue, fieldValue2, content));
        return serverHandshakeBuilder;
    }

    @Override // org.java_websocket.drafts.Draft
    public Handshakedata translateHandshake(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        HandshakeBuilder translateHandshakeHttp = translateHandshakeHttp(byteBuffer, this.role);
        if ((translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Key1") || this.role == WebSocket.Role.CLIENT) && !translateHandshakeHttp.hasFieldValue("Sec-WebSocket-Version")) {
            byte[] bArr = new byte[this.role == WebSocket.Role.SERVER ? 8 : 16];
            try {
                byteBuffer.get(bArr);
                translateHandshakeHttp.setContent(bArr);
            } catch (BufferUnderflowException unused) {
                throw new IncompleteHandshakeException(byteBuffer.capacity() + 16);
            }
        }
        return translateHandshakeHttp;
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public List<Framedata> translateFrame(ByteBuffer byteBuffer) throws InvalidDataException {
        byteBuffer.mark();
        List<Framedata> translateRegularFrame = super.translateRegularFrame(byteBuffer);
        if (translateRegularFrame == null) {
            byteBuffer.reset();
            List<Framedata> list = this.readyframes;
            this.readingState = true;
            if (this.currentFrame == null) {
                this.currentFrame = ByteBuffer.allocate(2);
                if (byteBuffer.remaining() > this.currentFrame.remaining()) {
                    throw new InvalidFrameException();
                }
                this.currentFrame.put(byteBuffer);
                if (!this.currentFrame.hasRemaining()) {
                    if (Arrays.equals(this.currentFrame.array(), closehandshake)) {
                        list.add(new CloseFrameBuilder(1000));
                        return list;
                    }
                    throw new InvalidFrameException();
                }
                this.readyframes = new LinkedList();
                return list;
            }
            throw new InvalidFrameException();
        }
        return translateRegularFrame;
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() == Framedata.Opcode.CLOSING) {
            return ByteBuffer.wrap(closehandshake);
        }
        return super.createBinaryFrame(framedata);
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public Draft.CloseHandshakeType getCloseHandshakeType() {
        return Draft.CloseHandshakeType.ONEWAY;
    }

    @Override // org.java_websocket.drafts.Draft_75, org.java_websocket.drafts.Draft
    public Draft copyInstance() {
        return new Draft_76();
    }
}
