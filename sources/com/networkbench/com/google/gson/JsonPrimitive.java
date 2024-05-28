package com.networkbench.com.google.gson;

import com.networkbench.com.google.gson.internal.C$Gson$Preconditions;
import com.networkbench.com.google.gson.internal.LazilyParsedNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] PRIMITIVE_TYPES = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.networkbench.com.google.gson.JsonElement
    /* renamed from: a */
    public JsonPrimitive mo8661b() {
        return this;
    }

    public JsonPrimitive(Boolean bool) {
        m8662a(bool);
    }

    public JsonPrimitive(Number number) {
        m8662a(number);
    }

    public JsonPrimitive(String str) {
        m8662a(str);
    }

    public JsonPrimitive(Character ch) {
        m8662a(ch);
    }

    public JsonPrimitive(Object obj) {
        m8662a(obj);
    }

    /* renamed from: a */
    void m8662a(Object obj) {
        if (obj instanceof Character) {
            this.value = String.valueOf(((Character) obj).charValue());
            return;
        }
        C$Gson$Preconditions.checkArgument((obj instanceof Number) || isPrimitiveOrString(obj));
        this.value = obj;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    /* renamed from: c */
    Boolean mo8660c() {
        return (Boolean) this.value;
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public boolean getAsBoolean() {
        if (isBoolean()) {
            return mo8660c().booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public Number getAsNumber() {
        Object obj = this.value;
        return obj instanceof String ? new LazilyParsedNumber((String) obj) : (Number) obj;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return mo8660c().toString();
        }
        return (String) this.value;
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public BigDecimal getAsBigDecimal() {
        Object obj = this.value;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(obj.toString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public BigInteger getAsBigInteger() {
        Object obj = this.value;
        return obj instanceof BigInteger ? (BigInteger) obj : new BigInteger(obj.toString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public float getAsFloat() {
        return isNumber() ? getAsNumber().floatValue() : Float.parseFloat(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public short getAsShort() {
        return isNumber() ? getAsNumber().shortValue() : Short.parseShort(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public byte getAsByte() {
        return isNumber() ? getAsNumber().byteValue() : Byte.parseByte(getAsString());
    }

    @Override // com.networkbench.com.google.gson.JsonElement
    public char getAsCharacter() {
        return getAsString().charAt(0);
    }

    private static boolean isPrimitiveOrString(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> cls2 : PRIMITIVE_TYPES) {
            if (cls2.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            long longValue = getAsNumber().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        }
        Object obj = this.value;
        if (obj instanceof Number) {
            long doubleToLongBits = Double.doubleToLongBits(getAsNumber().doubleValue());
            return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
        }
        return obj.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
        if (this.value == null) {
            return jsonPrimitive.value == null;
        } else if (isIntegral(this) && isIntegral(jsonPrimitive)) {
            return getAsNumber().longValue() == jsonPrimitive.getAsNumber().longValue();
        } else if ((this.value instanceof Number) && (jsonPrimitive.value instanceof Number)) {
            double doubleValue = getAsNumber().doubleValue();
            double doubleValue2 = jsonPrimitive.getAsNumber().doubleValue();
            if (doubleValue != doubleValue2) {
                return Double.isNaN(doubleValue) && Double.isNaN(doubleValue2);
            }
            return true;
        } else {
            return this.value.equals(jsonPrimitive.value);
        }
    }

    private static boolean isIntegral(JsonPrimitive jsonPrimitive) {
        Object obj = jsonPrimitive.value;
        if (obj instanceof Number) {
            Number number = (Number) obj;
            return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
        }
        return false;
    }
}
