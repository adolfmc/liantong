package com.android.uaf.asmcore;

import android.content.Context;
import android.util.Base64;
import com.android.client.asm.sdk.AuthenticatorException;
import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AuthenticatorDatabase;
import com.fido.android.framework.p197tm.core.inf.ICryptoModule;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import io.socket.engineio.client.Socket;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BoundAuthenticatorDatabase extends AuthenticatorDatabase {
    private static final String TAG = "BoundAuthenticatorDatabase";
    private final Context mContext;
    protected ICryptoModule mCryptoModule;
    private Database mDb;
    private final String mDbPath;
    private final Gson mGson = new Gson();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Database {

        /* renamed from: v */
        public int f4064v = 1;
        public HashMap<String, String> kvs = new HashMap<>();
        public List<AuthenticatorDatabase.RegistrationRecord> regs = new ArrayList();
    }

    public BoundAuthenticatorDatabase(String str, ICryptoModule iCryptoModule, Context context) throws AsmException {
        this.mDbPath = str;
        this.mCryptoModule = iCryptoModule;
        this.mContext = context;
        load();
    }

    private void flush() throws AsmException {
        Logger.m15889i(TAG, Socket.EVENT_FLUSH);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                Gson gson = this.mGson;
                Database database = this.mDb;
                byte[] bytes = (!(gson instanceof Gson) ? gson.toJson(database) : NBSGsonInstrumentation.toJson(gson, database)).getBytes(Charsets.utf8Charset);
                String str = TAG;
                Logger.m15889i(str, "size of database: " + bytes.length);
                byte[] encryptData = this.mCryptoModule.encryptData(bytes, null);
                Context context = this.mContext;
                fileOutputStream = context.openFileOutput(this.mDbPath + ".tmp", 0);
                fileOutputStream.write(encryptData);
                fileOutputStream.getFD().sync();
                fileOutputStream.flush();
                this.mContext.deleteFile(this.mDbPath);
                Context context2 = this.mContext;
                File fileStreamPath = context2.getFileStreamPath(this.mDbPath + ".tmp");
                if (!fileStreamPath.renameTo(new File(fileStreamPath.getParent(), this.mDbPath))) {
                    throw new AsmException(AsmError.FAILURE, "Failed to rename database file.");
                } else if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        Logger.m15891e(TAG, "Failed to close database file.", e);
                    }
                }
            } catch (Exception e2) {
                throw new AsmException(AsmError.FAILURE, "Exception writing database files.", e2);
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    Logger.m15891e(TAG, "Failed to close database file.", e3);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0099 A[Catch: IOException -> 0x00b1, TRY_ENTER, TRY_LEAVE, TryCatch #4 {IOException -> 0x00b1, blocks: (B:12:0x006c, B:30:0x0099, B:36:0x00ad), top: B:51:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void load() throws com.gmrz.android.client.asm.api.AsmException {
        /*
            Method dump skipped, instructions count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.uaf.asmcore.BoundAuthenticatorDatabase.load():void");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected void addKeyValue(String str, String str2) throws AsmException {
        if (str == null || str2 == null) {
            throw new AsmException(AsmError.FAILURE);
        }
        this.mDb.kvs.put(str, str2);
        flush();
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected String getValue(String str) {
        return this.mDb.kvs.get(str);
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected List<AuthenticatorDatabase.RegistrationRecord> getRegistrations(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("The callerID is invalid");
        }
        ArrayList arrayList = new ArrayList();
        for (AuthenticatorDatabase.RegistrationRecord registrationRecord : this.mDb.regs) {
            if (registrationRecord.callerID.equals(str)) {
                arrayList.add(registrationRecord);
            }
        }
        return arrayList;
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void eraseDatabase() throws AsmException {
        this.mDb = new Database();
        flush();
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void removeAllRegistrations() throws AsmException {
        this.mDb.regs = new ArrayList();
        flush();
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void addRegistration(AuthenticatorDatabase.RegistrationRecord registrationRecord) throws AsmException {
        if (registrationRecord == null) {
            throw new IllegalArgumentException("registration record is empty");
        }
        if (registrationRecord.appID == null || registrationRecord.appID.equals("") || registrationRecord.callerID == null || registrationRecord.callerID.equals("") || registrationRecord.keyID == null || registrationRecord.keyID.equals("") || registrationRecord.keyHandle == null || registrationRecord.keyHandle.equals("") || registrationRecord.timeStamp == 0) {
            throw new IllegalArgumentException("Invalid registration record, missing key fields");
        }
        this.mDb.regs.add(registrationRecord);
        flush();
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void removeRegistration(String str, String str2, String str3) throws AsmException {
        boolean z;
        Iterator<AuthenticatorDatabase.RegistrationRecord> it = this.mDb.regs.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            AuthenticatorDatabase.RegistrationRecord next = it.next();
            if (next.callerID.equals(str) && next.appID.equals(str2) && next.keyID.equals(str3)) {
                it.remove();
                z = true;
                break;
            }
        }
        if (z) {
            flush();
        }
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void validateUserRegistrations(IMatcher iMatcher) throws AsmException {
        boolean z;
        boolean z2;
        Logger.m15895d(TAG, "validateUserRegistrations matcher:" + iMatcher);
        Iterator<AuthenticatorDatabase.RegistrationRecord> it = this.mDb.regs.iterator();
        boolean z3 = false;
        while (it.hasNext()) {
            AuthenticatorDatabase.RegistrationRecord next = it.next();
            if (next.userID != null && !next.userID.equals("")) {
                Logger.m15895d(TAG, "validateUserRegistrations , uvt");
                try {
                    z2 = iMatcher.isUserIDValid(Base64.decode(next.userID, 0));
                } catch (AuthenticatorException e) {
                    Logger.m15891e(TAG, "Failed to execute Matcher.isIDValid. Do not run cleanup.", e);
                    z2 = true;
                }
                if (!z2) {
                    Logger.m15895d(TAG, "UserID (" + next.userID + ") not valid. Removing the record.");
                    it.remove();
                    z3 = true;
                }
            } else {
                Logger.m15895d(TAG, "validateUserRegistrations , uvt");
                try {
                    z = iMatcher.isUserIDValid("".getBytes());
                } catch (AuthenticatorException e2) {
                    Logger.m15891e(TAG, "Failed to execute Matcher.isIDValid. Do not run cleanup.", e2);
                    z = true;
                }
                if (!z) {
                    Logger.m15895d(TAG, "UserID (" + next.userID + ") not valid. Removing the record.");
                    it.remove();
                    z3 = true;
                }
            }
        }
        if (z3) {
            flush();
        }
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public boolean hasRegistrations() {
        return this.mDb.regs.size() > 0;
    }
}
