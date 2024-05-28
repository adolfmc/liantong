package io.socket.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Binary {
    private static final String KEY_NUM = "num";
    private static final String KEY_PLACEHOLDER = "_placeholder";
    private static final Logger logger = Logger.getLogger(Binary.class.getName());

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DeconstructedPacket {
        public byte[][] buffers;
        public Packet packet;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Object] */
    public static DeconstructedPacket deconstructPacket(Packet packet) {
        ArrayList arrayList = new ArrayList();
        packet.data = _deconstructPacket(packet.data, arrayList);
        packet.attachments = arrayList.size();
        DeconstructedPacket deconstructedPacket = new DeconstructedPacket();
        deconstructedPacket.packet = packet;
        deconstructedPacket.buffers = (byte[][]) arrayList.toArray(new byte[arrayList.size()]);
        return deconstructedPacket;
    }

    private static Object _deconstructPacket(Object obj, List<byte[]> list) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("_placeholder", true);
                jSONObject.put("num", list.size());
                list.add((byte[]) obj);
                return jSONObject;
            } catch (JSONException e) {
                logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e);
                return null;
            }
        } else if (obj instanceof JSONArray) {
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = (JSONArray) obj;
            int length = jSONArray2.length();
            for (int i = 0; i < length; i++) {
                try {
                    jSONArray.put(i, _deconstructPacket(jSONArray2.get(i), list));
                } catch (JSONException e2) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e2);
                    return null;
                }
            }
            return jSONArray;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = (JSONObject) obj;
            Iterator<String> keys = jSONObject3.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject2.put(next, _deconstructPacket(jSONObject3.get(next), list));
                } catch (JSONException e3) {
                    logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e3);
                    return null;
                }
            }
            return jSONObject2;
        } else {
            return obj;
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.Object] */
    public static Packet reconstructPacket(Packet packet, byte[][] bArr) {
        packet.data = _reconstructPacket(packet.data, bArr);
        packet.attachments = -1;
        return packet;
    }

    private static Object _reconstructPacket(Object obj, byte[][] bArr) {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    jSONArray.put(i, _reconstructPacket(jSONArray.get(i), bArr));
                } catch (JSONException e) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e);
                    return null;
                }
            }
            return jSONArray;
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject.optBoolean("_placeholder")) {
                int optInt = jSONObject.optInt("num", -1);
                if (optInt < 0 || optInt >= bArr.length) {
                    return null;
                }
                return bArr[optInt];
            }
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, _reconstructPacket(jSONObject.get(next), bArr));
                } catch (JSONException e2) {
                    logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e2);
                    return null;
                }
            }
            return jSONObject;
        } else {
            return obj;
        }
    }
}
