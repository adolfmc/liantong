package org.codehaus.jackson.impl;

import org.codehaus.jackson.JsonLocation;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.util.CharTypes;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child = null;
    protected int _columnNr;
    protected String _currentName;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, int i, int i2, int i3) {
        this._type = i;
        this._parent = jsonReadContext;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    protected final void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
    }

    public static JsonReadContext createRootContext(int i, int i2) {
        return new JsonReadContext(null, 0, i, i2);
    }

    public static JsonReadContext createRootContext() {
        return new JsonReadContext(null, 0, 1, 0);
    }

    public final JsonReadContext createChildArrayContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, 1, i, i2);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(1, i, i2);
        return jsonReadContext;
    }

    public final JsonReadContext createChildObjectContext(int i, int i2) {
        JsonReadContext jsonReadContext = this._child;
        if (jsonReadContext == null) {
            JsonReadContext jsonReadContext2 = new JsonReadContext(this, 2, i, i2);
            this._child = jsonReadContext2;
            return jsonReadContext2;
        }
        jsonReadContext.reset(2, i, i2);
        return jsonReadContext;
    }

    @Override // org.codehaus.jackson.JsonStreamContext
    public final String getCurrentName() {
        return this._currentName;
    }

    @Override // org.codehaus.jackson.JsonStreamContext
    public final JsonReadContext getParent() {
        return this._parent;
    }

    public final JsonLocation getStartLocation(Object obj) {
        return new JsonLocation(obj, -1L, this._lineNr, this._columnNr);
    }

    public final boolean expectComma() {
        int i = this._index + 1;
        this._index = i;
        return this._type != 0 && i > 0;
    }

    public void setCurrentName(String str) {
        this._currentName = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(64);
        switch (this._type) {
            case 0:
                sb.append("/");
                break;
            case 1:
                sb.append('[');
                sb.append(getCurrentIndex());
                sb.append(']');
                break;
            case 2:
                sb.append('{');
                if (this._currentName != null) {
                    sb.append('\"');
                    CharTypes.appendQuoted(sb, this._currentName);
                    sb.append('\"');
                } else {
                    sb.append('?');
                }
                sb.append('}');
                break;
        }
        return sb.toString();
    }
}
