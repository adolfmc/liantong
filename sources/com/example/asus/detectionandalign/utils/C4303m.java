package com.example.asus.detectionandalign.utils;

import android.util.Log;
import java.io.IOException;
import org.p415a.p445f.C12934f;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.m */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4303m {

    /* renamed from: a */
    static String f10103a = "mQENBFvQMXwBCAC/jSNCAj7OF/uEBa4FZVJTSYQ+q9MnCfcze2LUaq0YvHklzubt\ndnNb8TcifHB5+4vmEzPshdigsodt5U6WSon49lSbYGSr0C1aAF4m8Izux3E9Yn/v\niTrSWOkbHb1XpggjYZOVIFCcl6m5dq7CJiAFumkMPW6O02L26RgZlL5kpFsqtZPU\nZsYZe8o2voZkHxo8Ll/NXoh1JMsrRiACy+i6gBixDobJfkNd6MzGbJmRSXvY3vOw\nyaH1MpteHZJmkqFranjJVI/XN2fvbWaKYj+NyHdIKVqFYhIZs4Ij0CCO/lPZLWCK\nEui1fza5OCj4CNlcgTLVUDHbFu3NwAiQVig9ABEBAAG0OHdhbmdsdXNoZW5nIChz\nZGsuMS4wLjE4LjEwMjQpIDx3YW5nbHVzaGVuZ0Bpc2hpemhlbi5jb20+iQE5BBMB\nCAAjBQJb0DF8AhsDBwsJCAcDAgEGFQgCCQoLBBYCAwECHgECF4AACgkQY1Rwc8Lf\nXIhUagf/Wo/SjJXYgp3laY8/4Ph8VobUKfdja37zsgovfT8AYZdUpwDFFrhwheSJ\nVKP0gBz2LD8beBUGK2TijxTJnF4LzfrFAP6MkZyHkn9wybtp+Iym34Esd5ECBqgz\nGEzRax0zgNdKHjb/pV2WVE/n2XlCI8psHdVVZTg0BlPl635AeznTyTtUrviT2COn\nTwmyJsEj9T5kfKWJV4p5QXuwBy5oLkUGJRRS/a1GptFZyiaPcv+HzCFGiZ4hKy1e\njmXfjTr39u/IPdpwynXK28NxTLqVJb2pism6pw1VA83LSdZrDuBN9rKhpF0zVoRp\nFyy7RwNoWJdH5+5TWSYgwlC2c3FCRrkBDQRb0DF8AQgA5igMxgCEXjnZZpgaCNs5\nhv0CbnckuJTTNDoEyRCY2KjFycH4BIfmHFftEfTOGVYiJwst1S28K3tyUNxAszU8\nNo2JaCBoIcXRmZ1YH+AE6g4cMYB/uh9TcC8HqAtWpWoeXGqHp5cl1FHhIPpx4GTy\nlEpCX91NlOiYgSsZXBSvoXakSchJrBaH3CuVlacF+5iP/G/3VWDr/9RnLIwdRRXO\n4FPd809PHWUUoCEy4PRZkVU1JplvjOm4WwYtiZSFqX383SO3mkhvwLy9Mf+Z/6I0\nl5py1LZG6BQXHhzjTGKFMtKxy1wWaEAdaTrqZy3umPOTTQmD1ZOVJ+B30lG+37f+\nZwARAQABiQEfBBgBCAAJBQJb0DF8AhsMAAoJEGNUcHPC31yI5FsH/icBzL4Si36a\nsdHguRUNIS79pkPLa/YjgT7D8e14zMLGy3XCt6Ed+y2VGRMtYS1Bzn0o6GNQkhH3\nvHTFwTGbUi/GRyVkhNUHlEe0YSFuZQuu4Yj49CSw+D8LSqFRzNh+XkwWJrrBJITB\naBGHarcnBYJA3ZLd4STdIa0rDhrYzub/oRkYEHq0dRkl19Grs03fbYt0HPyLaEWP\n9cIZt1MXh4PxROwIPrTGKzAC/58vspM+ghYzA7Ixo+gKKY6K/4adbbXy4Wtvbm67\nTgN75QCmoV1ilF/KTOqbEnBL0sGAtwaOiHQph5E8z8Shu/8ZCSo8WIuaFxQp3EHo\nqcx41Z8r208==";

    /* renamed from: a */
    public static String m15930a(String str) {
        String str2;
        String message;
        try {
            String m15947a = C4296h.m15947a(str, f10103a);
            Log.e("encrypt：", "success");
            return m15947a;
        } catch (IOException e) {
            str2 = "encrypt";
            message = e.getMessage();
            Log.e(str2, message);
            return null;
        } catch (C12934f e2) {
            str2 = "encrypt";
            message = e2.getMessage();
            Log.e(str2, message);
            return null;
        }
    }
}
