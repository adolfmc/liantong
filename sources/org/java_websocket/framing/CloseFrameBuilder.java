package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.util.Charsetfunctions;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CloseFrameBuilder extends FramedataImpl1 implements CloseFrame {
    static final ByteBuffer emptybytebuffer = ByteBuffer.allocate(0);
    private int code;
    private String reason;

    public CloseFrameBuilder() {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
    }

    public CloseFrameBuilder(int i) throws InvalidDataException {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, "");
    }

    public CloseFrameBuilder(int i, String str) throws InvalidDataException {
        super(Framedata.Opcode.CLOSING);
        setFin(true);
        setCodeAndMessage(i, str);
    }

    private void setCodeAndMessage(int i, String str) throws InvalidDataException {
        if (str == null) {
            str = "";
        }
        if (i == 1015) {
            str = "";
            i = 1005;
        }
        if (i == 1005) {
            if (str.length() > 0) {
                throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
            }
            return;
        }
        byte[] utf8Bytes = Charsetfunctions.utf8Bytes(str);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.position(2);
        ByteBuffer allocate2 = ByteBuffer.allocate(utf8Bytes.length + 2);
        allocate2.put(allocate);
        allocate2.put(utf8Bytes);
        allocate2.rewind();
        setPayload(allocate2);
    }

    private void initCloseCode() throws InvalidFrameException {
        this.code = 1005;
        ByteBuffer payloadData = super.getPayloadData();
        payloadData.mark();
        if (payloadData.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(payloadData.getShort());
            allocate.position(0);
            this.code = allocate.getInt();
            int i = this.code;
            if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
            }
        }
        payloadData.reset();
    }

    @Override // org.java_websocket.framing.CloseFrame
    public int getCloseCode() {
        return this.code;
    }

    private void initMessage() throws InvalidDataException {
        if (this.code == 1005) {
            this.reason = Charsetfunctions.stringUtf8(super.getPayloadData());
            return;
        }
        ByteBuffer payloadData = super.getPayloadData();
        int position = payloadData.position();
        try {
            try {
                payloadData.position(payloadData.position() + 2);
                this.reason = Charsetfunctions.stringUtf8(payloadData);
            } catch (IllegalArgumentException e) {
                throw new InvalidFrameException(e);
            }
        } finally {
            payloadData.position(position);
        }
    }

    @Override // org.java_websocket.framing.CloseFrame
    public String getMessage() {
        return this.reason;
    }

    @Override // org.java_websocket.framing.FramedataImpl1
    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    @Override // org.java_websocket.framing.FramedataImpl1, org.java_websocket.framing.FrameBuilder
    public void setPayload(ByteBuffer byteBuffer) throws InvalidDataException {
        super.setPayload(byteBuffer);
        initCloseCode();
        initMessage();
    }

    @Override // org.java_websocket.framing.FramedataImpl1, org.java_websocket.framing.Framedata
    public ByteBuffer getPayloadData() {
        if (this.code == 1005) {
            return emptybytebuffer;
        }
        return super.getPayloadData();
    }
}
