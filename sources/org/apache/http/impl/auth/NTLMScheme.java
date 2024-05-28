package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.NTCredentials;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class NTLMScheme extends AuthSchemeBase {
    private String challenge;
    private final NTLMEngine engine;
    private State state;

    /* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\452516_dexfile_execute.dex */
    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        MSG_TYPE1_GENERATED,
        MSG_TYPE2_RECEVIED,
        MSG_TYPE3_GENERATED,
        FAILED
    }

    public NTLMScheme(NTLMEngine nTLMEngine) {
        if (nTLMEngine == null) {
            throw new IllegalArgumentException("NTLM engine may not be null");
        }
        this.engine = nTLMEngine;
        this.state = State.UNINITIATED;
        this.challenge = null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "ntlm";
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getParameter(String str) {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return true;
    }

    @Override // org.apache.http.impl.auth.AuthSchemeBase
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) throws MalformedChallengeException {
        String substringTrimmed = charArrayBuffer.substringTrimmed(i, i2);
        if (substringTrimmed.length() == 0) {
            if (this.state == State.UNINITIATED) {
                this.state = State.CHALLENGE_RECEIVED;
            } else {
                this.state = State.FAILED;
            }
            this.challenge = null;
            return;
        }
        this.state = State.MSG_TYPE2_RECEVIED;
        this.challenge = substringTrimmed;
    }

    @Override // org.apache.http.auth.AuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) throws AuthenticationException {
        String generateType1Msg;
        try {
            NTCredentials nTCredentials = (NTCredentials) credentials;
            if (this.state == State.CHALLENGE_RECEIVED || this.state == State.FAILED) {
                generateType1Msg = this.engine.generateType1Msg(nTCredentials.getDomain(), nTCredentials.getWorkstation());
                this.state = State.MSG_TYPE1_GENERATED;
            } else if (this.state == State.MSG_TYPE2_RECEVIED) {
                generateType1Msg = this.engine.generateType3Msg(nTCredentials.getUserName(), nTCredentials.getPassword(), nTCredentials.getDomain(), nTCredentials.getWorkstation(), this.challenge);
                this.state = State.MSG_TYPE3_GENERATED;
            } else {
                throw new AuthenticationException("Unexpected state: " + this.state);
            }
            CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
            if (isProxy()) {
                charArrayBuffer.append("Proxy-Authorization");
            } else {
                charArrayBuffer.append("Authorization");
            }
            charArrayBuffer.append(": NTLM ");
            charArrayBuffer.append(generateType1Msg);
            return new BufferedHeader(charArrayBuffer);
        } catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for NTLM authentication: " + credentials.getClass().getName());
        }
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        return this.state == State.MSG_TYPE3_GENERATED || this.state == State.FAILED;
    }
}
