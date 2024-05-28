package cn.ltzf.passguard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.Time;
import com.bytedance.applog.tracker.Tracker;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class LTPassGuardVerify {
    private static final int Android = 2;
    private static boolean bVerify;
    private static String mlicense;
    private Context context = null;
    private AlertDialog alert = null;
    private boolean isShowing = false;
    private final int TESTVERIFY_TIME = 300000;

    private LTPassGuardVerify() {
    }

    public static boolean getVerify() {
        return bVerify;
    }

    private static int parseMonth(String str) {
        return Integer.parseInt(str.substring(4, 6)) - 1;
    }

    private static int parseMonthDay(String str) {
        return Integer.parseInt(str.substring(6, 8));
    }

    private static Time parseTime(String str) {
        Time time = new Time();
        time.set(parseMonthDay(str), parseMonth(str), parseYear(str));
        return time;
    }

    private static int parseYear(String str) {
        return Integer.parseInt(str.substring(0, 4));
    }

    public static void setLicense(String str) {
        mlicense = str;
    }

    private void showAlertDialog() {
        if (this.alert == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setTitle("控件提示");
            builder.setMessage("此控件为临时开发版本，测试license已过期，请您及时升级为正式版本，避免影响您的使用！");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: cn.ltzf.passguard.LTPassGuardVerify.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                }
            });
            AlertDialog create = builder.create();
            this.alert = create;
            create.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.ltzf.passguard.LTPassGuardVerify.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    LTPassGuardVerify.this.isShowing = false;
                }
            });
        }
        if (this.isShowing || ((Activity) this.context).isFinishing()) {
            return;
        }
        this.alert.show();
        this.isShowing = true;
    }

    public static boolean verify(Context context) {
        boolean z;
        String string;
        String string2;
        String str = mlicense;
        boolean z2 = true;
        if (str == null) {
            return true;
        }
        String str2 = new String(LTBase64.decode(str, 2));
        if (str2.contains("{")) {
            byte[] decode = LTBase64.decode(str2.substring(0, str2.indexOf("{")), 2);
            String substring = str2.substring(str2.indexOf("{"));
            try {
                String key = LTPassGuardEncrypt.getKey();
                RSAPublicKeySpec rSAPublicKeySpec = new RSAPublicKeySpec(new BigInteger(key.substring(0, key.indexOf("&"))), new BigInteger(key.substring(key.indexOf("&") + 1)));
                Signature signature = Signature.getInstance("SHA1withRSA");
                signature.initVerify((RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(rSAPublicKeySpec));
                signature.update(substring.getBytes());
                if (signature.verify(decode)) {
                    JSONObject jSONObject = new JSONObject(substring);
                    if ((jSONObject.has("id") ? jSONObject.getInt("id") : -1) != 0) {
                        return true;
                    }
                    String string3 = jSONObject.has("type") ? jSONObject.getString("type") : null;
                    if (string3 != null && string3.equalsIgnoreCase("test")) {
                        if (((jSONObject.has("platform") ? jSONObject.getInt("platform") : 0) & 2) == 0) {
                            return true;
                        }
                        string = jSONObject.has("notafter") ? jSONObject.getString("notafter") : null;
                        string2 = jSONObject.has("notbefore") ? jSONObject.getString("notbefore") : null;
                        if (string == null || string2 == null) {
                            return true;
                        }
                    } else if (string3 != null && !string3.equalsIgnoreCase("product")) {
                        return true;
                    } else {
                        JSONArray jSONArray = jSONObject.has("package") ? jSONObject.getJSONArray("package") : null;
                        JSONArray jSONArray2 = jSONObject.has("applyname") ? jSONObject.getJSONArray("applyname") : null;
                        int i = jSONObject.has("platform") ? jSONObject.getInt("platform") : 0;
                        if (jSONArray == null || jSONArray2 == null || (2 & i) == 0) {
                            return true;
                        }
                        String charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
                        int i2 = 0;
                        while (true) {
                            if (i2 >= jSONArray2.length()) {
                                z = false;
                                break;
                            } else if (jSONArray2.getString(i2).equals(charSequence)) {
                                z = true;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        String packageName = context.getPackageName();
                        int i3 = 0;
                        while (true) {
                            if (i3 >= jSONArray.length()) {
                                break;
                            } else if (jSONArray.getString(i3).equals(packageName)) {
                                r4 = 1;
                                break;
                            } else {
                                i3++;
                            }
                        }
                        if (!z || r4 == 0) {
                            return true;
                        }
                        string = jSONObject.has("notafter") ? jSONObject.getString("notafter") : null;
                        string2 = jSONObject.has("notbefore") ? jSONObject.getString("notbefore") : null;
                        if (string == null || string2 == null) {
                            return true;
                        }
                    }
                    z2 = verifyTime(string2, string);
                    return z2;
                }
                return true;
            } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException | JSONException e) {
                e.printStackTrace();
                return z2;
            }
        }
        return true;
    }

    private static boolean verifyTime(String str, String str2) {
        Time time = new Time("GMT+8");
        time.setToNow();
        Time parseTime = parseTime(str);
        Time parseTime2 = parseTime(str2);
        if (time.after(parseTime)) {
            time.before(parseTime2);
            return true;
        }
        return true;
    }
}
