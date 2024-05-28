package cn.sharesdk.framework;

import android.app.Activity;
import android.os.Handler;
import cn.sharesdk.framework.authorize.AuthorizeParams;
import cn.sharesdk.framework.authorize.SdkPlusTags;
import cn.sharesdk.framework.p094a.StatisticsLogger;
import cn.sharesdk.framework.p094a.p096b.ApiEvent;
import cn.sharesdk.framework.p094a.p096b.DemoEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareSDKCore {
    /* renamed from: a */
    public static ArrayList<Platform> m21799a() {
        ArrayList<Platform> m21787e = m21787e();
        m21794a(m21787e);
        return m21787e;
    }

    /* renamed from: e */
    private static ArrayList<Platform> m21787e() {
        String[] strArr;
        ArrayList<Platform> arrayList = new ArrayList<>();
        for (String str : new String[]{"cn.sharesdk.douban.Douban", "cn.sharesdk.evernote.Evernote", "cn.sharesdk.facebook.Facebook", "cn.sharesdk.renren.Renren", "cn.sharesdk.sina.weibo.SinaWeibo", "cn.sharesdk.kaixin.KaiXin", "cn.sharesdk.linkedin.LinkedIn", "cn.sharesdk.system.email.Email", "cn.sharesdk.system.text.ShortMessage", "cn.sharesdk.tencent.qq.QQ", "cn.sharesdk.tencent.qzone.QZone", "cn.sharesdk.tencent.weibo.TencentWeibo", "cn.sharesdk.twitter.Twitter", "cn.sharesdk.wechat.friends.Wechat", "cn.sharesdk.wechat.moments.WechatMoments", "cn.sharesdk.wechat.favorite.WechatFavorite", "cn.sharesdk.youdao.YouDao", "cn.sharesdk.google.GooglePlus", "cn.sharesdk.foursquare.FourSquare", "cn.sharesdk.pinterest.Pinterest", "cn.sharesdk.flickr.Flickr", "cn.sharesdk.tumblr.Tumblr", "cn.sharesdk.dropbox.Dropbox", "cn.sharesdk.vkontakte.VKontakte", "cn.sharesdk.instagram.Instagram", "cn.sharesdk.yixin.friends.Yixin", "cn.sharesdk.yixin.moments.YixinMoments", "cn.sharesdk.mingdao.Mingdao", "cn.sharesdk.line.Line", "cn.sharesdk.kakao.story.KakaoStory", "cn.sharesdk.kakao.talk.KakaoTalk", "cn.sharesdk.whatsapp.WhatsApp", "cn.sharesdk.pocket.Pocket", "cn.sharesdk.instapaper.Instapaper", "cn.sharesdk.facebookmessenger.FacebookMessenger", "cn.sharesdk.alipay.friends.Alipay", "cn.sharesdk.alipay.moments.AlipayMoments", "cn.sharesdk.dingding.friends.Dingding", "cn.sharesdk.youtube.Youtube", "cn.sharesdk.meipai.Meipai", "cn.sharesdk.telegram.Telegram", "cn.sharesdk.cmcc.Cmcc", "cn.sharesdk.reddit.Reddit", "cn.sharesdk.telecom.Telecom", "cn.sharesdk.accountkit.Accountkit", "cn.sharesdk.douyin.Douyin", "cn.sharesdk.wework.Wework", "cn.sharesdk.oasis.Oasis", "cn.sharesdk.hwaccount.HWAccount", "cn.sharesdk.xmaccount.XMAccount", "cn.sharesdk.snapchat.Snapchat", "cn.sharesdk.littleredbook.Littleredbook", "cn.sharesdk.kuaishou.Kuaishou", "cn.sharesdk.watermelonvideo.Watermelonvideo", "cn.sharesdk.tiktok.Tiktok", "cn.sharesdk.taptap.Taptap"}) {
            try {
                arrayList.add((Platform) Class.forName(str).newInstance());
            } catch (Throwable unused) {
                SSDKLog.m21740b().m21744a("not found:" + str, new Object[0]);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m21794a(ArrayList<Platform> arrayList) {
        if (arrayList == null) {
            return;
        }
        Collections.sort(arrayList, new Comparator<Platform>() { // from class: cn.sharesdk.framework.h.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Platform platform, Platform platform2) {
                if (platform.getSortId() != platform2.getSortId()) {
                    return platform.getSortId() - platform2.getSortId();
                }
                return platform.getPlatformId() - platform2.getPlatformId();
            }
        });
    }

    /* renamed from: a */
    public static void m21797a(Activity activity) {
        AuthorizeParams m21872c = AuthorizeParams.m21872c();
        if (m21872c != null) {
            m21872c.m21875a(activity);
        }
    }

    /* renamed from: b */
    public static Activity m21791b() {
        return AuthorizeParams.m21872c().m21873b();
    }

    /* renamed from: a */
    public static void m21792a(boolean z) {
        AuthorizeParams m21872c = AuthorizeParams.m21872c();
        if (m21872c != null) {
            m21872c.m21874a(z);
        }
    }

    /* renamed from: c */
    public static boolean m21789c() {
        return AuthorizeParams.m21872c().m21876a();
    }

    /* renamed from: b */
    public static void m21790b(boolean z) {
        SdkPlusTags m21865c = SdkPlusTags.m21865c();
        if (m21865c != null) {
            m21865c.m21867a(z);
        }
    }

    /* renamed from: d */
    public static boolean m21788d() {
        return SdkPlusTags.m21865c().m21869a();
    }

    /* renamed from: a */
    public static void m21796a(Handler handler) {
        StatisticsLogger m21893a = StatisticsLogger.m21893a();
        if (m21893a != null) {
            m21893a.m21892a(handler);
            m21893a.mo21687d();
        }
    }

    /* renamed from: a */
    public static void m21798a(int i, Platform platform) {
        DemoEvent demoEvent = new DemoEvent();
        switch (i) {
            case 1:
                demoEvent.f2801a = "SHARESDK_ENTER_SHAREMENU";
                break;
            case 2:
                demoEvent.f2801a = "SHARESDK_CANCEL_SHAREMENU";
                break;
            case 3:
                demoEvent.f2801a = "SHARESDK_EDIT_SHARE";
                break;
            case 4:
                demoEvent.f2801a = "SHARESDK_FAILED_SHARE";
                break;
            case 5:
                demoEvent.f2801a = "SHARESDK_CANCEL_SHARE";
                break;
        }
        if (platform != null) {
            demoEvent.f2802b = platform.getPlatformId();
        }
        StatisticsLogger m21893a = StatisticsLogger.m21893a();
        if (m21893a != null) {
            m21893a.m21891a(demoEvent);
        }
    }

    /* renamed from: a */
    public static void m21795a(String str, int i) {
        StatisticsLogger m21893a = StatisticsLogger.m21893a();
        if (m21893a == null) {
            return;
        }
        ApiEvent apiEvent = new ApiEvent();
        apiEvent.f2784b = str;
        apiEvent.f2783a = i;
        m21893a.m21891a(apiEvent);
    }

    /* renamed from: a */
    public static HashMap<Integer, HashMap<String, Object>> m21793a(HashMap<String, Object> hashMap) {
        ArrayList arrayList;
        int i;
        if (hashMap == null || hashMap.size() <= 0 || (arrayList = (ArrayList) hashMap.get("fakelist")) == null) {
            return null;
        }
        HashMap<Integer, HashMap<String, Object>> hashMap2 = new HashMap<>();
        EventRecorder.addBegin("ShareSDK", "parseDevInfo");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap<String, Object> hashMap3 = (HashMap) it.next();
            if (hashMap3 != null) {
                try {
                    i = ResHelper.parseInt(String.valueOf(hashMap3.get("snsplat")));
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21737b(th);
                    i = -1;
                }
                if (i != -1) {
                    hashMap2.put(Integer.valueOf(i), hashMap3);
                }
            }
        }
        EventRecorder.addEnd("ShareSDK", "parseDevInfo");
        return hashMap2;
    }
}
