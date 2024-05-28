package com.google.protobuf;

import com.google.protobuf.Value;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ValueOrBuilder extends MessageOrBuilder {
    boolean getBoolValue();

    Value.KindCase getKindCase();

    ListValue getListValue();

    ListValueOrBuilder getListValueOrBuilder();

    NullValue getNullValue();

    int getNullValueValue();

    double getNumberValue();

    String getStringValue();

    ByteString getStringValueBytes();

    Struct getStructValue();

    StructOrBuilder getStructValueOrBuilder();
}
