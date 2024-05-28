package com.baidu.location;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.location.Address;
import com.baidu.location.p140e.C2735k;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BDLocation implements Parcelable {
    public static final String BDLOCATION_BD09LL_TO_GCJ02 = "bd09ll2gcj";
    public static final String BDLOCATION_BD09_TO_GCJ02 = "bd092gcj";
    public static final String BDLOCATION_COOR_TYPE_BD09LL = "bd09";
    public static final String BDLOCATION_COOR_TYPE_BD09MC = "bd09mc";
    public static final String BDLOCATION_COOR_TYPE_GCJ02 = "gcj02";
    public static final String BDLOCATION_COOR_TYPE_GCJ03 = "gcj03";
    public static final String BDLOCATION_COOR_TYPE_WGS84 = "wgs84";
    public static final String BDLOCATION_GCJ02_TO_BD09 = "bd09";
    public static final String BDLOCATION_GCJ02_TO_BD09LL = "bd09ll";
    public static final String BDLOCATION_GNSS_PROVIDER_FROM_BAIDU_BEIDOU = "bd_beidou";
    public static final String BDLOCATION_GNSS_PROVIDER_FROM_SYSTEM = "system";
    public static final String BDLOCATION_WGS84_TO_GCJ02 = "gps2gcj";
    public static final Parcelable.Creator<BDLocation> CREATOR = new C2620a();
    public static final int GPS_ACCURACY_BAD = 3;
    public static final int GPS_ACCURACY_GOOD = 1;
    public static final int GPS_ACCURACY_MID = 2;
    public static final int GPS_ACCURACY_UNKNOWN = 0;
    public static final int GPS_RECTIFY_INDOOR = 1;
    public static final int GPS_RECTIFY_NONE = 0;
    public static final int GPS_RECTIFY_OUTDOOR = 2;
    public static final int INDOOR_LOCATION_NEARBY_SURPPORT_TRUE = 2;
    public static final int INDOOR_LOCATION_SOURCE_BLUETOOTH = 4;
    public static final int INDOOR_LOCATION_SOURCE_MAGNETIC = 2;
    public static final int INDOOR_LOCATION_SOURCE_SMALLCELLSTATION = 8;
    public static final int INDOOR_LOCATION_SOURCE_UNKNOWN = 0;
    public static final int INDOOR_LOCATION_SOURCE_WIFI = 1;
    public static final int INDOOR_LOCATION_SURPPORT_FALSE = 0;
    public static final int INDOOR_LOCATION_SURPPORT_TRUE = 1;
    public static final int INDOOR_NETWORK_STATE_HIGH = 2;
    public static final int INDOOR_NETWORK_STATE_LOW = 0;
    public static final int INDOOR_NETWORK_STATE_MIDDLE = 1;
    public static final int LOCATION_WHERE_IN_CN = 1;
    public static final int LOCATION_WHERE_OUT_CN = 0;
    public static final int LOCATION_WHERE_UNKNOW = 2;
    public static final int MOCK_GPS_PROBABILITY_HIGH = 3;
    public static final int MOCK_GPS_PROBABILITY_LOW = 1;
    public static final int MOCK_GPS_PROBABILITY_MIDDLE = 2;
    public static final int MOCK_GPS_PROBABILITY_UNKNOW = -1;
    public static final int MOCK_GPS_PROBABILITY_ZERO = 0;
    public static final int OPERATORS_TYPE_MOBILE = 1;
    public static final int OPERATORS_TYPE_TELECOMU = 3;
    public static final int OPERATORS_TYPE_UNICOM = 2;
    public static final int OPERATORS_TYPE_UNKONW = 0;
    public static final int TYPE_BMS_HD_LOCATION = 602;
    public static final int TYPE_CLOSE_LOCATION_SERVICE_SWITCH_FAIL = 69;
    public static final int TYPE_HD_LOCATION = 601;
    public static final int TYPE_NO_PERMISSION_AND_CLOSE_SWITCH_FAIL = 71;
    public static final int TYPE_NO_PERMISSION_LOCATION_FAIL = 70;
    public static final int TypeCacheLocation = 65;
    public static final int TypeCriteriaException = 62;
    public static final int TypeGpsLocation = 61;
    public static final int TypeNetWorkException = 63;
    public static final int TypeNetWorkLocation = 161;
    public static final int TypeNone = 0;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeServerCheckKeyError = 505;
    public static final int TypeServerDecryptError = 162;
    public static final int TypeServerError = 167;
    public static final int USER_INDDOR_TRUE = 1;
    public static final int USER_INDOOR_FALSE = 0;
    public static final int USER_INDOOR_UNKNOW = -1;

    /* renamed from: A */
    private String f5002A;

    /* renamed from: B */
    private String f5003B;

    /* renamed from: C */
    private double f5004C;

    /* renamed from: D */
    private boolean f5005D;

    /* renamed from: E */
    private int f5006E;

    /* renamed from: F */
    private int f5007F;

    /* renamed from: G */
    private String f5008G;

    /* renamed from: H */
    private int f5009H;

    /* renamed from: I */
    private String f5010I;

    /* renamed from: J */
    private int f5011J;

    /* renamed from: K */
    private int f5012K;

    /* renamed from: L */
    private int f5013L;

    /* renamed from: M */
    private int f5014M;

    /* renamed from: N */
    private String f5015N;

    /* renamed from: O */
    private String f5016O;

    /* renamed from: P */
    private String f5017P;

    /* renamed from: Q */
    private int f5018Q;

    /* renamed from: R */
    private List<Poi> f5019R;

    /* renamed from: S */
    private String f5020S;

    /* renamed from: T */
    private String f5021T;

    /* renamed from: U */
    private String f5022U;

    /* renamed from: V */
    private Bundle f5023V;

    /* renamed from: W */
    private int f5024W;

    /* renamed from: X */
    private int f5025X;

    /* renamed from: Y */
    private long f5026Y;

    /* renamed from: Z */
    private String f5027Z;

    /* renamed from: a */
    private int f5028a;

    /* renamed from: aa */
    private String f5029aa;

    /* renamed from: ab */
    private double f5030ab;

    /* renamed from: ac */
    private double f5031ac;

    /* renamed from: ad */
    private boolean f5032ad;

    /* renamed from: ae */
    private PoiRegion f5033ae;

    /* renamed from: af */
    private float f5034af;

    /* renamed from: ag */
    private double f5035ag;

    /* renamed from: ah */
    private int f5036ah;

    /* renamed from: ai */
    private int f5037ai;

    /* renamed from: aj */
    private BDLocation f5038aj;

    /* renamed from: ak */
    private Bundle f5039ak;

    /* renamed from: al */
    private String f5040al;

    /* renamed from: b */
    private String f5041b;

    /* renamed from: c */
    private double f5042c;

    /* renamed from: d */
    private double f5043d;

    /* renamed from: e */
    private boolean f5044e;

    /* renamed from: f */
    private double f5045f;

    /* renamed from: g */
    private boolean f5046g;

    /* renamed from: h */
    private float f5047h;

    /* renamed from: i */
    private boolean f5048i;

    /* renamed from: j */
    private float f5049j;

    /* renamed from: k */
    private String f5050k;

    /* renamed from: l */
    private float f5051l;

    /* renamed from: m */
    private int f5052m;

    /* renamed from: n */
    private float f5053n;

    /* renamed from: o */
    private boolean f5054o;

    /* renamed from: p */
    private int f5055p;

    /* renamed from: q */
    private float f5056q;

    /* renamed from: r */
    private String f5057r;

    /* renamed from: s */
    private boolean f5058s;

    /* renamed from: t */
    private String f5059t;

    /* renamed from: u */
    private String f5060u;

    /* renamed from: v */
    private String f5061v;

    /* renamed from: w */
    private String f5062w;

    /* renamed from: x */
    private boolean f5063x;

    /* renamed from: y */
    private Address f5064y;

    /* renamed from: z */
    private String f5065z;

    public BDLocation() {
        this.f5028a = 0;
        this.f5041b = null;
        this.f5042c = Double.MIN_VALUE;
        this.f5043d = Double.MIN_VALUE;
        this.f5044e = false;
        this.f5045f = Double.MIN_VALUE;
        this.f5046g = false;
        this.f5047h = 0.0f;
        this.f5048i = false;
        this.f5049j = 0.0f;
        this.f5051l = 0.0f;
        this.f5052m = -1;
        this.f5053n = 0.0f;
        this.f5054o = false;
        this.f5055p = -1;
        this.f5056q = -1.0f;
        this.f5057r = null;
        this.f5058s = false;
        this.f5059t = null;
        this.f5060u = null;
        this.f5061v = null;
        this.f5062w = null;
        this.f5063x = false;
        this.f5064y = new Address.Builder().build();
        this.f5065z = null;
        this.f5002A = null;
        this.f5003B = null;
        this.f5005D = false;
        this.f5006E = 0;
        this.f5007F = 1;
        this.f5008G = null;
        this.f5010I = "";
        this.f5011J = -1;
        this.f5012K = 0;
        this.f5013L = 2;
        this.f5014M = 0;
        this.f5015N = null;
        this.f5016O = null;
        this.f5017P = null;
        this.f5018Q = -1;
        this.f5019R = null;
        this.f5020S = null;
        this.f5021T = null;
        this.f5022U = null;
        this.f5023V = new Bundle();
        this.f5024W = 0;
        this.f5025X = 0;
        this.f5026Y = 0L;
        this.f5027Z = null;
        this.f5029aa = null;
        this.f5030ab = Double.MIN_VALUE;
        this.f5031ac = Double.MIN_VALUE;
        this.f5032ad = false;
        this.f5033ae = null;
        this.f5034af = -1.0f;
        this.f5035ag = -1.0d;
        this.f5036ah = 0;
        this.f5037ai = -1;
        this.f5039ak = null;
        this.f5040al = null;
    }

    private BDLocation(Parcel parcel) {
        this.f5028a = 0;
        this.f5041b = null;
        this.f5042c = Double.MIN_VALUE;
        this.f5043d = Double.MIN_VALUE;
        this.f5044e = false;
        this.f5045f = Double.MIN_VALUE;
        this.f5046g = false;
        this.f5047h = 0.0f;
        this.f5048i = false;
        this.f5049j = 0.0f;
        this.f5051l = 0.0f;
        this.f5052m = -1;
        this.f5053n = 0.0f;
        this.f5054o = false;
        this.f5055p = -1;
        this.f5056q = -1.0f;
        this.f5057r = null;
        this.f5058s = false;
        this.f5059t = null;
        this.f5060u = null;
        this.f5061v = null;
        this.f5062w = null;
        this.f5063x = false;
        this.f5064y = new Address.Builder().build();
        this.f5065z = null;
        this.f5002A = null;
        this.f5003B = null;
        this.f5005D = false;
        this.f5006E = 0;
        this.f5007F = 1;
        this.f5008G = null;
        this.f5010I = "";
        this.f5011J = -1;
        this.f5012K = 0;
        this.f5013L = 2;
        this.f5014M = 0;
        this.f5015N = null;
        this.f5016O = null;
        this.f5017P = null;
        this.f5018Q = -1;
        this.f5019R = null;
        this.f5020S = null;
        this.f5021T = null;
        this.f5022U = null;
        this.f5023V = new Bundle();
        this.f5024W = 0;
        this.f5025X = 0;
        this.f5026Y = 0L;
        this.f5027Z = null;
        this.f5029aa = null;
        this.f5030ab = Double.MIN_VALUE;
        this.f5031ac = Double.MIN_VALUE;
        this.f5032ad = false;
        this.f5033ae = null;
        this.f5034af = -1.0f;
        this.f5035ag = -1.0d;
        this.f5036ah = 0;
        this.f5037ai = -1;
        this.f5039ak = null;
        this.f5040al = null;
        this.f5028a = parcel.readInt();
        this.f5041b = parcel.readString();
        this.f5042c = parcel.readDouble();
        this.f5043d = parcel.readDouble();
        this.f5045f = parcel.readDouble();
        this.f5047h = parcel.readFloat();
        this.f5049j = parcel.readFloat();
        this.f5050k = parcel.readString();
        this.f5051l = parcel.readFloat();
        this.f5052m = parcel.readInt();
        this.f5053n = parcel.readFloat();
        this.f5055p = parcel.readInt();
        this.f5056q = parcel.readFloat();
        this.f5065z = parcel.readString();
        this.f5006E = parcel.readInt();
        this.f5002A = parcel.readString();
        this.f5003B = parcel.readString();
        this.f5004C = parcel.readDouble();
        this.f5008G = parcel.readString();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        String readString4 = parcel.readString();
        String readString5 = parcel.readString();
        String readString6 = parcel.readString();
        parcel.readString();
        String readString7 = parcel.readString();
        String readString8 = parcel.readString();
        String readString9 = parcel.readString();
        this.f5064y = new Address.Builder().country(readString7).countryCode(readString8).province(readString).city(readString2).cityCode(readString6).district(readString3).street(readString4).streetNumber(readString5).adcode(readString9).town(parcel.readString()).build();
        boolean[] zArr = new boolean[8];
        this.f5009H = parcel.readInt();
        this.f5010I = parcel.readString();
        this.f5060u = parcel.readString();
        this.f5061v = parcel.readString();
        this.f5062w = parcel.readString();
        this.f5007F = parcel.readInt();
        this.f5020S = parcel.readString();
        this.f5011J = parcel.readInt();
        this.f5012K = parcel.readInt();
        this.f5013L = parcel.readInt();
        this.f5014M = parcel.readInt();
        this.f5015N = parcel.readString();
        this.f5016O = parcel.readString();
        this.f5017P = parcel.readString();
        this.f5018Q = parcel.readInt();
        this.f5024W = parcel.readInt();
        this.f5021T = parcel.readString();
        this.f5025X = parcel.readInt();
        this.f5022U = parcel.readString();
        this.f5027Z = parcel.readString();
        this.f5029aa = parcel.readString();
        this.f5026Y = parcel.readLong();
        this.f5030ab = parcel.readDouble();
        this.f5031ac = parcel.readDouble();
        this.f5034af = parcel.readFloat();
        this.f5035ag = parcel.readDouble();
        this.f5036ah = parcel.readInt();
        this.f5037ai = parcel.readInt();
        this.f5057r = parcel.readString();
        this.f5040al = parcel.readString();
        try {
            this.f5038aj = (BDLocation) parcel.readParcelable(BDLocation.class.getClassLoader());
        } catch (Exception e) {
            this.f5038aj = null;
            e.printStackTrace();
        }
        try {
            parcel.readBooleanArray(zArr);
            this.f5044e = zArr[0];
            this.f5046g = zArr[1];
            this.f5048i = zArr[2];
            this.f5054o = zArr[3];
            this.f5058s = zArr[4];
            this.f5063x = zArr[5];
            this.f5005D = zArr[6];
            this.f5032ad = zArr[7];
        } catch (Exception unused) {
        }
        ArrayList arrayList = new ArrayList();
        try {
            parcel.readList(arrayList, Poi.class.getClassLoader());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (arrayList.size() == 0) {
            this.f5019R = null;
        } else {
            this.f5019R = arrayList;
        }
        try {
            this.f5023V = parcel.readBundle();
        } catch (Exception e3) {
            e3.printStackTrace();
            this.f5023V = new Bundle();
        }
        try {
            this.f5039ak = parcel.readBundle();
        } catch (Exception e4) {
            e4.printStackTrace();
            this.f5039ak = new Bundle();
        }
        try {
            this.f5033ae = (PoiRegion) parcel.readParcelable(PoiRegion.class.getClassLoader());
        } catch (Exception e5) {
            this.f5033ae = null;
            e5.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ BDLocation(Parcel parcel, C2620a c2620a) {
        this(parcel);
    }

    public BDLocation(BDLocation bDLocation) {
        this.f5028a = 0;
        ArrayList arrayList = null;
        this.f5041b = null;
        this.f5042c = Double.MIN_VALUE;
        this.f5043d = Double.MIN_VALUE;
        this.f5044e = false;
        this.f5045f = Double.MIN_VALUE;
        this.f5046g = false;
        this.f5047h = 0.0f;
        this.f5048i = false;
        this.f5049j = 0.0f;
        this.f5051l = 0.0f;
        this.f5052m = -1;
        this.f5053n = 0.0f;
        this.f5054o = false;
        this.f5055p = -1;
        this.f5056q = -1.0f;
        this.f5057r = null;
        this.f5058s = false;
        this.f5059t = null;
        this.f5060u = null;
        this.f5061v = null;
        this.f5062w = null;
        this.f5063x = false;
        this.f5064y = new Address.Builder().build();
        this.f5065z = null;
        this.f5002A = null;
        this.f5003B = null;
        this.f5005D = false;
        this.f5006E = 0;
        this.f5007F = 1;
        this.f5008G = null;
        this.f5010I = "";
        this.f5011J = -1;
        this.f5012K = 0;
        this.f5013L = 2;
        this.f5014M = 0;
        this.f5015N = null;
        this.f5016O = null;
        this.f5017P = null;
        this.f5018Q = -1;
        this.f5019R = null;
        this.f5020S = null;
        this.f5021T = null;
        this.f5022U = null;
        this.f5023V = new Bundle();
        this.f5024W = 0;
        this.f5025X = 0;
        this.f5026Y = 0L;
        this.f5027Z = null;
        this.f5029aa = null;
        this.f5030ab = Double.MIN_VALUE;
        this.f5031ac = Double.MIN_VALUE;
        this.f5032ad = false;
        this.f5033ae = null;
        this.f5034af = -1.0f;
        this.f5035ag = -1.0d;
        this.f5036ah = 0;
        this.f5037ai = -1;
        this.f5039ak = null;
        this.f5040al = null;
        this.f5028a = bDLocation.f5028a;
        this.f5041b = bDLocation.f5041b;
        this.f5042c = bDLocation.f5042c;
        this.f5043d = bDLocation.f5043d;
        this.f5044e = bDLocation.f5044e;
        this.f5045f = bDLocation.f5045f;
        this.f5046g = bDLocation.f5046g;
        this.f5047h = bDLocation.f5047h;
        this.f5048i = bDLocation.f5048i;
        this.f5049j = bDLocation.f5049j;
        this.f5050k = bDLocation.f5050k;
        this.f5051l = bDLocation.f5051l;
        this.f5052m = bDLocation.f5052m;
        this.f5053n = bDLocation.f5053n;
        this.f5054o = bDLocation.f5054o;
        this.f5055p = bDLocation.f5055p;
        this.f5056q = bDLocation.f5056q;
        this.f5057r = bDLocation.f5057r;
        this.f5058s = bDLocation.f5058s;
        this.f5059t = bDLocation.f5059t;
        this.f5063x = bDLocation.f5063x;
        this.f5064y = new Address.Builder().country(bDLocation.f5064y.country).countryCode(bDLocation.f5064y.countryCode).province(bDLocation.f5064y.province).city(bDLocation.f5064y.city).cityCode(bDLocation.f5064y.cityCode).district(bDLocation.f5064y.district).street(bDLocation.f5064y.street).streetNumber(bDLocation.f5064y.streetNumber).adcode(bDLocation.f5064y.adcode).town(bDLocation.f5064y.town).build();
        this.f5065z = bDLocation.f5065z;
        this.f5002A = bDLocation.f5002A;
        this.f5003B = bDLocation.f5003B;
        this.f5004C = bDLocation.f5004C;
        this.f5007F = bDLocation.f5007F;
        this.f5006E = bDLocation.f5006E;
        this.f5005D = bDLocation.f5005D;
        this.f5008G = bDLocation.f5008G;
        this.f5009H = bDLocation.f5009H;
        this.f5010I = bDLocation.f5010I;
        this.f5060u = bDLocation.f5060u;
        this.f5061v = bDLocation.f5061v;
        this.f5062w = bDLocation.f5062w;
        this.f5011J = bDLocation.f5011J;
        this.f5012K = bDLocation.f5012K;
        this.f5013L = bDLocation.f5012K;
        this.f5014M = bDLocation.f5014M;
        this.f5015N = bDLocation.f5015N;
        this.f5016O = bDLocation.f5016O;
        this.f5017P = bDLocation.f5017P;
        this.f5018Q = bDLocation.f5018Q;
        this.f5024W = bDLocation.f5024W;
        this.f5022U = bDLocation.f5022U;
        this.f5027Z = bDLocation.f5027Z;
        this.f5029aa = bDLocation.f5029aa;
        this.f5030ab = bDLocation.f5030ab;
        this.f5031ac = bDLocation.f5031ac;
        this.f5026Y = bDLocation.f5026Y;
        this.f5035ag = bDLocation.f5035ag;
        this.f5036ah = bDLocation.f5036ah;
        this.f5037ai = bDLocation.f5037ai;
        this.f5038aj = bDLocation.f5038aj;
        this.f5021T = bDLocation.f5021T;
        if (bDLocation.f5019R != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < bDLocation.f5019R.size(); i++) {
                Poi poi = bDLocation.f5019R.get(i);
                arrayList.add(new Poi(poi.getId(), poi.getName(), poi.getRank(), poi.getTags(), poi.getAddr()));
            }
        }
        this.f5019R = arrayList;
        this.f5020S = bDLocation.f5020S;
        this.f5023V = bDLocation.f5023V;
        this.f5025X = bDLocation.f5025X;
        this.f5032ad = bDLocation.f5032ad;
        this.f5033ae = bDLocation.f5033ae;
        this.f5034af = bDLocation.f5034af;
        this.f5039ak = bDLocation.f5039ak;
        this.f5040al = bDLocation.f5040al;
    }

    /* JADX WARN: Removed duplicated region for block: B:191:0x049d A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x04e1 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x04fc A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:205:0x051b A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0536 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0551 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:220:0x056c A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x057c A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0679 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0683 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TRY_LEAVE, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:278:0x068e A[Catch: Exception -> 0x069f, Error -> 0x079d, TryCatch #12 {Exception -> 0x069f, blocks: (B:276:0x0686, B:278:0x068e, B:279:0x069c), top: B:358:0x0686 }] */
    /* JADX WARN: Removed duplicated region for block: B:279:0x069c A[Catch: Exception -> 0x069f, Error -> 0x079d, TRY_LEAVE, TryCatch #12 {Exception -> 0x069f, blocks: (B:276:0x0686, B:278:0x068e, B:279:0x069c), top: B:358:0x0686 }] */
    /* JADX WARN: Removed duplicated region for block: B:282:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x06b5 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x06c5 A[Catch: Error -> 0x079d, Exception -> 0x07a3, TRY_LEAVE, TryCatch #14 {Error -> 0x079d, blocks: (B:7:0x00a9, B:9:0x00cf, B:11:0x0133, B:12:0x013c, B:14:0x0144, B:15:0x014d, B:17:0x0155, B:18:0x0163, B:19:0x0166, B:22:0x016d, B:26:0x0179, B:28:0x01b7, B:29:0x01c1, B:31:0x01ca, B:32:0x01d8, B:34:0x01e0, B:35:0x01e9, B:37:0x01f2, B:38:0x0200, B:40:0x0208, B:42:0x0216, B:44:0x0222, B:46:0x0228, B:48:0x0230, B:49:0x0242, B:51:0x0248, B:53:0x026b, B:55:0x0277, B:57:0x027f, B:59:0x028a, B:60:0x0299, B:61:0x029b, B:63:0x02a3, B:65:0x02af, B:66:0x02b1, B:68:0x02b9, B:70:0x02cd, B:71:0x02d3, B:73:0x02db, B:74:0x02e1, B:76:0x02ea, B:77:0x02f1, B:78:0x02f8, B:80:0x0300, B:82:0x030c, B:83:0x030e, B:85:0x0316, B:89:0x0322, B:91:0x033c, B:92:0x0342, B:94:0x034a, B:95:0x0350, B:97:0x0358, B:98:0x035e, B:100:0x0366, B:101:0x036c, B:103:0x0374, B:104:0x037a, B:106:0x0382, B:107:0x0388, B:109:0x0391, B:110:0x0398, B:112:0x03a1, B:113:0x03a8, B:115:0x03b0, B:116:0x03b8, B:118:0x03c1, B:191:0x049d, B:193:0x04d9, B:195:0x04e1, B:197:0x04f1, B:198:0x04f4, B:200:0x04fc, B:202:0x0508, B:203:0x0513, B:205:0x051b, B:207:0x052b, B:208:0x052e, B:210:0x0536, B:212:0x0546, B:213:0x0549, B:215:0x0551, B:217:0x0561, B:218:0x0564, B:220:0x056c, B:221:0x0574, B:223:0x057c, B:225:0x0588, B:226:0x058b, B:229:0x0594, B:230:0x059e, B:232:0x05a6, B:234:0x05b4, B:236:0x05c4, B:238:0x05cb, B:239:0x05ce, B:241:0x05d7, B:242:0x05e9, B:244:0x05f1, B:245:0x05f9, B:247:0x0601, B:248:0x0609, B:250:0x0611, B:251:0x061a, B:253:0x0622, B:255:0x0632, B:257:0x063c, B:259:0x0640, B:261:0x064c, B:263:0x0654, B:265:0x065f, B:266:0x0663, B:267:0x0667, B:272:0x0671, B:274:0x0679, B:276:0x0686, B:278:0x068e, B:279:0x069c, B:280:0x069f, B:283:0x06a6, B:285:0x06ad, B:287:0x06b5, B:288:0x06bd, B:290:0x06c5, B:304:0x06fb, B:305:0x06fe, B:314:0x073c, B:275:0x0683, B:271:0x066e, B:122:0x03d3, B:124:0x03e2, B:131:0x03f4, B:138:0x0406, B:145:0x0416, B:152:0x0429, B:160:0x043c, B:167:0x044d, B:174:0x045d, B:181:0x046e, B:189:0x0494, B:192:0x04d3, B:322:0x074e, B:323:0x0753), top: B:361:0x00a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x06f6 A[Catch: Throwable -> 0x06f9, TRY_LEAVE, TryCatch #19 {Throwable -> 0x06f9, blocks: (B:292:0x06cd, B:294:0x06d5, B:296:0x06dd, B:298:0x06e1, B:300:0x06f6), top: B:367:0x06cd }] */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0706 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:345:0x05a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:378:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public BDLocation(java.lang.String r25) {
        /*
            Method dump skipped, instructions count: 1965
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.BDLocation.<init>(java.lang.String):void");
    }

    /* renamed from: a */
    private void m19636a(Boolean bool) {
        this.f5063x = bool.booleanValue();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getAcc() {
        return this.f5004C;
    }

    public String getAdCode() {
        return this.f5064y.adcode;
    }

    public String getAddrStr() {
        return this.f5064y.address;
    }

    public Address getAddress() {
        return this.f5064y;
    }

    public double getAltitude() {
        return this.f5045f;
    }

    public String getBuildingID() {
        return this.f5002A;
    }

    public String getBuildingName() {
        return this.f5003B;
    }

    public String getCity() {
        return this.f5064y.city;
    }

    public String getCityCode() {
        return this.f5064y.cityCode;
    }

    public String getCoorType() {
        return this.f5057r;
    }

    public String getCountry() {
        return this.f5064y.country;
    }

    public String getCountryCode() {
        return this.f5064y.countryCode;
    }

    public long getDelayTime() {
        return this.f5026Y;
    }

    @Deprecated
    public float getDerect() {
        return this.f5056q;
    }

    public float getDirection() {
        return this.f5056q;
    }

    public double getDisToRealLocation() {
        return this.f5035ag;
    }

    public String getDistrict() {
        return this.f5064y.district;
    }

    public Bundle getExtraInfo() {
        return this.f5039ak;
    }

    public Location getExtraLocation(String str) {
        Bundle bundle = this.f5023V;
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable(str);
            if (parcelable instanceof Location) {
                return (Location) parcelable;
            }
            return null;
        }
        return null;
    }

    public String getFloor() {
        return this.f5065z;
    }

    public double[] getFusionLocInfo(String str) {
        return this.f5023V.getDoubleArray(str);
    }

    public String getGnssProvider() {
        return this.f5040al;
    }

    public int getGpsAccuracyStatus() {
        return this.f5024W;
    }

    public float getGpsBiasProb() {
        return this.f5034af;
    }

    public int getGpsCheckStatus() {
        return this.f5025X;
    }

    public int getInOutStatus() {
        return this.f5018Q;
    }

    public int getIndoorLocationSource() {
        return this.f5014M;
    }

    public int getIndoorLocationSurpport() {
        return this.f5012K;
    }

    public String getIndoorLocationSurpportBuidlingID() {
        return this.f5016O;
    }

    public String getIndoorLocationSurpportBuidlingName() {
        return this.f5015N;
    }

    public int getIndoorNetworkState() {
        return this.f5013L;
    }

    public String getIndoorSurpportPolygon() {
        return this.f5017P;
    }

    public double getLatitude() {
        return this.f5042c;
    }

    public int getLocType() {
        return this.f5028a;
    }

    public String getLocTypeDescription() {
        return this.f5020S;
    }

    public String getLocationDescribe() {
        return this.f5060u;
    }

    public String getLocationID() {
        return this.f5021T;
    }

    public int getLocationWhere() {
        return this.f5007F;
    }

    public double getLongitude() {
        return this.f5043d;
    }

    public int getMockGpsProbability() {
        return this.f5037ai;
    }

    public int getMockGpsStrategy() {
        return this.f5036ah;
    }

    public String getNetworkLocationType() {
        return this.f5008G;
    }

    public double getNrlLat() {
        return this.f5030ab;
    }

    public double getNrlLon() {
        return this.f5031ac;
    }

    public String getNrlResult() {
        return this.f5027Z;
    }

    public int getOperators() {
        return this.f5009H;
    }

    public List<Poi> getPoiList() {
        return this.f5019R;
    }

    public PoiRegion getPoiRegion() {
        return this.f5033ae;
    }

    public String getProvince() {
        return this.f5064y.province;
    }

    public float getRadius() {
        return this.f5049j;
    }

    public BDLocation getReallLocation() {
        if (getMockGpsStrategy() > 0) {
            return this.f5038aj;
        }
        return null;
    }

    public String getRetFields(String str) {
        return this.f5023V.getString(str);
    }

    public String getRoadLocString() {
        return this.f5022U;
    }

    public int getSatelliteNumber() {
        this.f5054o = true;
        return this.f5055p;
    }

    @Deprecated
    public String getSemaAptag() {
        return this.f5060u;
    }

    public float getSpeed() {
        return this.f5047h;
    }

    public String getStreet() {
        return this.f5064y.street;
    }

    public String getStreetNumber() {
        return this.f5064y.streetNumber;
    }

    public String getTime() {
        return this.f5041b;
    }

    public String getTown() {
        return this.f5064y.town;
    }

    public String getTraffic() {
        return this.f5050k;
    }

    public float getTrafficConfidence() {
        return this.f5051l;
    }

    public float getTrafficSkipProb() {
        return this.f5053n;
    }

    public int getUserIndoorState() {
        return this.f5011J;
    }

    public String getVdrJsonString() {
        Bundle bundle = this.f5023V;
        if (bundle == null || !bundle.containsKey("vdr")) {
            return null;
        }
        return this.f5023V.getString("vdr");
    }

    public String getViaductResult() {
        return this.f5029aa;
    }

    public boolean hasAddr() {
        return this.f5058s;
    }

    public boolean hasAltitude() {
        return this.f5044e;
    }

    public boolean hasRadius() {
        return this.f5048i;
    }

    public boolean hasSateNumber() {
        return this.f5054o;
    }

    public boolean hasSpeed() {
        return this.f5046g;
    }

    public boolean isCellChangeFlag() {
        return this.f5063x;
    }

    public boolean isInIndoorPark() {
        return this.f5032ad;
    }

    public boolean isIndoorLocMode() {
        return this.f5005D;
    }

    public boolean isNrlAvailable() {
        return (this.f5031ac == Double.MIN_VALUE || this.f5030ab == Double.MIN_VALUE) ? false : true;
    }

    public int isParkAvailable() {
        return this.f5006E;
    }

    public int isTrafficStation() {
        return this.f5052m;
    }

    public void setAcc(double d) {
        this.f5004C = d;
    }

    public void setAddr(Address address) {
        if (address != null) {
            this.f5064y = address;
            this.f5058s = true;
        }
    }

    public void setAddrStr(String str) {
        this.f5059t = str;
        this.f5058s = str != null;
    }

    public void setAltitude(double d) {
        if (d < 9999.0d) {
            this.f5045f = d;
            this.f5044e = true;
        }
    }

    public void setBuildingID(String str) {
        this.f5002A = str;
    }

    public void setBuildingName(String str) {
        this.f5003B = str;
    }

    public void setCoorType(String str) {
        this.f5057r = str;
    }

    public void setDelayTime(long j) {
        this.f5026Y = j;
    }

    public void setDirection(float f) {
        this.f5056q = f;
    }

    public void setDisToRealLocation(double d) {
        this.f5035ag = d;
    }

    public void setExtraLocation(String str, Location location) {
        if (this.f5023V == null) {
            this.f5023V = new Bundle();
        }
        this.f5023V.putParcelable(str, location);
    }

    public void setExtrainfo(Bundle bundle) {
        this.f5039ak = bundle == null ? null : new Bundle(bundle);
    }

    public void setFloor(String str) {
        this.f5065z = str;
    }

    public void setFusionLocInfo(String str, double[] dArr) {
        if (this.f5023V == null) {
            this.f5023V = new Bundle();
        }
        this.f5023V.putDoubleArray(str, dArr);
    }

    public void setGnssProvider(String str) {
        this.f5040al = str;
    }

    public void setGpsAccuracyStatus(int i) {
        this.f5024W = i;
    }

    public void setGpsBiasProb(float f) {
        this.f5034af = f;
    }

    public void setGpsCheckStatus(int i) {
        this.f5025X = i;
    }

    public void setInOutStatus(int i) {
        this.f5018Q = i;
    }

    public void setIndoorLocMode(boolean z) {
        this.f5005D = z;
    }

    public void setIndoorLocationSource(int i) {
        this.f5014M = i;
    }

    public void setIndoorLocationSurpport(int i) {
        this.f5012K = i;
    }

    public void setIndoorNetworkState(int i) {
        this.f5013L = i;
    }

    public void setIndoorSurpportPolygon(String str) {
        this.f5017P = str;
    }

    public void setIsInIndoorPark(boolean z) {
        this.f5032ad = z;
    }

    public void setIsTrafficStation(int i) {
        this.f5052m = i;
    }

    public void setLatitude(double d) {
        this.f5042c = d;
    }

    public void setLocType(int i) {
        String str;
        this.f5028a = i;
        switch (i) {
            case 61:
                setLocTypeDescription("GPS location successful!");
                setUserIndoorState(0);
                setGnssProvider("system");
                return;
            case 62:
                str = "Location failed beacuse we can not get any loc information!";
                break;
            case 63:
            case 67:
                str = "Offline location failed, please check the net (wifi/cell)!";
                break;
            case 66:
                str = "Offline location successful!";
                break;
            case 69:
                str = "Location failed because the location service switch is not on";
                break;
            case 70:
                str = "Location failed because the location permission is not enabled";
                break;
            case 71:
                str = "Location failed because the location service switch is not on and the location permission is not enabled";
                break;
            case 161:
                str = "NetWork location successful!";
                break;
            case 162:
                str = "NetWork location failed because baidu location service can not decrypt the request query, please check the so file !";
                break;
            case 167:
                str = "NetWork location failed because baidu location service can not caculate the location!";
                break;
            case 505:
                str = "NetWork location failed because baidu location service check the key is unlegal, please check the key in AndroidManifest.xml !";
                break;
            default:
                str = "UnKnown!";
                break;
        }
        setLocTypeDescription(str);
    }

    public void setLocTypeDescription(String str) {
        this.f5020S = str;
    }

    public void setLocationDescribe(String str) {
        this.f5060u = str;
    }

    public void setLocationID(String str) {
        this.f5021T = str;
    }

    public void setLocationWhere(int i) {
        this.f5007F = i;
    }

    public void setLongitude(double d) {
        this.f5043d = d;
    }

    public void setMockGpsProbability(int i) {
        this.f5037ai = i;
    }

    public void setMockGpsStrategy(int i) {
        this.f5036ah = i;
    }

    public void setNetworkLocationType(String str) {
        this.f5008G = str;
    }

    public void setNrlData(String str) {
        this.f5027Z = str;
    }

    public void setOperators(int i) {
        this.f5009H = i;
    }

    public void setParkAvailable(int i) {
        this.f5006E = i;
    }

    public void setPoiList(List<Poi> list) {
        this.f5019R = list;
    }

    public void setPoiRegion(PoiRegion poiRegion) {
        this.f5033ae = poiRegion;
    }

    public void setRadius(float f) {
        this.f5049j = f;
        this.f5048i = true;
    }

    public void setReallLocation(BDLocation bDLocation) {
        if (getMockGpsStrategy() > 0) {
            this.f5038aj = bDLocation;
        }
    }

    public void setRetFields(String str, String str2) {
        if (this.f5023V == null) {
            this.f5023V = new Bundle();
        }
        this.f5023V.putString(str, str2);
    }

    public void setRoadLocString(float f, float f2, String str) {
        String format = ((double) f) > 0.001d ? String.format("%.2f", Float.valueOf(f)) : "";
        String format2 = ((double) f2) > 0.001d ? String.format("%.2f", Float.valueOf(f2)) : "";
        if (this.f5027Z != null) {
            this.f5022U = String.format(Locale.US, "%s|%s,%s", this.f5027Z, format, format2);
            if (this.f5029aa != null) {
                this.f5022U = String.format(Locale.US, "%s|%s", this.f5022U, this.f5029aa);
            }
        }
        if (str != null) {
            this.f5022U = String.format(Locale.US, "%s|%s", this.f5022U, str);
        }
    }

    public void setSatelliteNumber(int i) {
        this.f5055p = i;
    }

    public void setSpeed(float f) {
        this.f5047h = f;
        this.f5046g = true;
    }

    public void setTime(String str) {
        this.f5041b = str;
        setLocationID(C2735k.m19061a(str));
    }

    public void setTraffic(String str) {
        this.f5050k = str;
    }

    public void setTrafficConfidence(float f) {
        this.f5051l = f;
    }

    public void setTrafficSkipProb(float f) {
        this.f5053n = f;
    }

    public void setUserIndoorState(int i) {
        this.f5011J = i;
    }

    public void setVdrJsonValue(String str) {
        if (this.f5023V == null) {
            this.f5023V = new Bundle();
        }
        this.f5023V.putString("vdr", str);
    }

    public void setViaductData(String str) {
        this.f5029aa = str;
    }

    public String toString() {
        return "&loctype=" + getLocType() + "&lat=" + getLatitude() + "&lon=" + getLongitude() + "&radius=" + getRadius() + "&biasprob=" + getGpsBiasProb() + "&extrainfo=" + getExtraInfo();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f5028a);
        parcel.writeString(this.f5041b);
        parcel.writeDouble(this.f5042c);
        parcel.writeDouble(this.f5043d);
        parcel.writeDouble(this.f5045f);
        parcel.writeFloat(this.f5047h);
        parcel.writeFloat(this.f5049j);
        parcel.writeString(this.f5050k);
        parcel.writeFloat(this.f5051l);
        parcel.writeInt(this.f5052m);
        parcel.writeFloat(this.f5053n);
        parcel.writeInt(this.f5055p);
        parcel.writeFloat(this.f5056q);
        parcel.writeString(this.f5065z);
        parcel.writeInt(this.f5006E);
        parcel.writeString(this.f5002A);
        parcel.writeString(this.f5003B);
        parcel.writeDouble(this.f5004C);
        parcel.writeString(this.f5008G);
        parcel.writeString(this.f5064y.province);
        parcel.writeString(this.f5064y.city);
        parcel.writeString(this.f5064y.district);
        parcel.writeString(this.f5064y.street);
        parcel.writeString(this.f5064y.streetNumber);
        parcel.writeString(this.f5064y.cityCode);
        parcel.writeString(this.f5064y.address);
        parcel.writeString(this.f5064y.country);
        parcel.writeString(this.f5064y.countryCode);
        parcel.writeString(this.f5064y.adcode);
        parcel.writeString(this.f5064y.town);
        parcel.writeInt(this.f5009H);
        parcel.writeString(this.f5010I);
        parcel.writeString(this.f5060u);
        parcel.writeString(this.f5061v);
        parcel.writeString(this.f5062w);
        parcel.writeInt(this.f5007F);
        parcel.writeString(this.f5020S);
        parcel.writeInt(this.f5011J);
        parcel.writeInt(this.f5012K);
        parcel.writeInt(this.f5013L);
        parcel.writeInt(this.f5014M);
        parcel.writeString(this.f5015N);
        parcel.writeString(this.f5016O);
        parcel.writeString(this.f5017P);
        parcel.writeInt(this.f5018Q);
        parcel.writeInt(this.f5024W);
        parcel.writeString(this.f5021T);
        parcel.writeInt(this.f5025X);
        parcel.writeString(this.f5022U);
        parcel.writeString(this.f5027Z);
        parcel.writeString(this.f5029aa);
        parcel.writeLong(this.f5026Y);
        parcel.writeDouble(this.f5030ab);
        parcel.writeDouble(this.f5031ac);
        parcel.writeFloat(this.f5034af);
        parcel.writeDouble(this.f5035ag);
        parcel.writeInt(this.f5036ah);
        parcel.writeInt(this.f5037ai);
        parcel.writeString(this.f5057r);
        parcel.writeString(this.f5040al);
        parcel.writeParcelable(this.f5038aj, i);
        parcel.writeBooleanArray(new boolean[]{this.f5044e, this.f5046g, this.f5048i, this.f5054o, this.f5058s, this.f5063x, this.f5005D, this.f5032ad});
        parcel.writeList(this.f5019R);
        parcel.writeBundle(this.f5023V);
        parcel.writeBundle(this.f5039ak);
        parcel.writeParcelable(this.f5033ae, i);
    }
}
