package com.sinovatech.unicom.separatemodule.audience.function;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.AngleMoreEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.PlayBackPayInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MultiViewPlayBackVideoInfoFunction implements Function<String, SharpnessEntity> {
    @Override // io.reactivex.functions.Function
    public SharpnessEntity apply(String str) throws Exception {
        JSONObject optJSONObject;
        SharpnessEntity sharpnessEntity = new SharpnessEntity();
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        sharpnessEntity.setStatusCode(optString);
        sharpnessEntity.setMessage(jSONObject.optString("message"));
        sharpnessEntity.setLogo(jSONObject.optString("logo"));
        sharpnessEntity.setDataChannel(jSONObject.optString("dataChannel"));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        ArrayList arrayList = new ArrayList();
        if ("0000".equals(optString) && optJSONObject2 != null) {
            sharpnessEntity.setVideoId(optJSONObject2.optString("videoId"));
            sharpnessEntity.setVideoPraiseNum(optJSONObject2.optString("videoPraiseNum"));
            sharpnessEntity.setIsNeedCheck(optJSONObject2.optString("isNeedCheck"));
            sharpnessEntity.setPasswordCheckErrorNum(optJSONObject2.optString("passwordCheckErrorNum"));
            SharpnessEntity.LiveUrlBean liveUrlBean = new SharpnessEntity.LiveUrlBean();
            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("userInfo");
            sharpnessEntity.setUserInfo(parseUserinfo(optJSONObject3));
            sharpnessEntity.getUserInfo().getAnchorInfoBean().setLiveCoverImg(optJSONObject2.optString("videoImg"));
            String optString2 = optJSONObject2.optString("goodsInfo");
            if ("Y".equals(optJSONObject2.optString("isShowGoods")) && optString2.startsWith("[")) {
                parseGoodsList(sharpnessEntity, optJSONObject2.optJSONArray("goodsInfo"));
            }
            liveUrlBean.setJobNumber(optJSONObject3.optString("userId"));
            liveUrlBean.setSharpnessName("原画");
            liveUrlBean.setSharpnessLevel("L");
            liveUrlBean.setLiveFreePullUrl(optJSONObject2.optString("videoLink"));
            liveUrlBean.setLiveFreePullUrlByFlv(optJSONObject2.optString("videoLink"));
            liveUrlBean.setLivePullUrl(optJSONObject2.optString("videoLinkNotFree"));
            liveUrlBean.setLivePullUrlByFlv(optJSONObject2.optString("videoLinkNotFree"));
            arrayList.add(liveUrlBean);
        }
        if (optJSONObject2 != null && "Y".equals(optJSONObject2.optString("paidLiveBackPlay"))) {
            PlayBackPayInfoEntity playBackPayInfoEntity = new PlayBackPayInfoEntity();
            playBackPayInfoEntity.setPaidId(optJSONObject2.optString("paidId"));
            playBackPayInfoEntity.setPaidLiveBackPlay(optJSONObject2.optString("paidLiveBackPlay"));
            playBackPayInfoEntity.setPayUrl(optJSONObject2.optString("payUrl"));
            playBackPayInfoEntity.setTryLook(optJSONObject2.optString("tryLook"));
            playBackPayInfoEntity.setUserIsPaid(optJSONObject2.optString("userIsPaid"));
            playBackPayInfoEntity.setFreeTime(optJSONObject2.optString("freeTime"));
            playBackPayInfoEntity.setPromptText(optJSONObject2.optString("palyBackPromptText"));
            playBackPayInfoEntity.setPaidAd(optJSONObject2.optString("paidAd"));
            sharpnessEntity.setPbPayInfo(playBackPayInfoEntity);
        }
        sharpnessEntity.setLiveViewAngleMore(optJSONObject2.optString("moreViewAngle"));
        if (sharpnessEntity.getLiveViewAngleMore().equals("Y") && (optJSONObject = optJSONObject2.optJSONObject("playbackAngleMoreVO")) != null) {
            sharpnessEntity.setPaidLive(optJSONObject.optString("paid"));
            sharpnessEntity.setPayingUser(optJSONObject.optString("userIsPaid"));
            sharpnessEntity.setPromptText(optJSONObject.optString("promptText"));
            sharpnessEntity.setFreeTime(optJSONObject.optString("freeTime"));
            String optString3 = optJSONObject.optString("paidAd");
            if (TextUtils.isEmpty(optString3)) {
                optString3 = "应版权方要求，本内容需购买观看";
            }
            sharpnessEntity.setPaidAd(optString3);
            sharpnessEntity.setPaidLinks(optJSONObject.optString("payUrl"));
            ArrayList arrayList2 = new ArrayList();
            if (arrayList.size() > 0) {
                String optString4 = optJSONObject.optString("viewAngleUrl1");
                if (!TextUtils.isEmpty(optString4)) {
                    AngleMoreEntity angleMoreEntity = new AngleMoreEntity();
                    angleMoreEntity.setName(optJSONObject.optString("viewAngleName1"));
                    angleMoreEntity.setCover(optJSONObject.optString("viewAngleCover1"));
                    ArrayList arrayList3 = new ArrayList();
                    SharpnessEntity.LiveUrlBean liveUrlBean2 = new SharpnessEntity.LiveUrlBean();
                    liveUrlBean2.setJobNumber(optJSONObject.optString("jobNumber"));
                    liveUrlBean2.setSharpnessName("原画");
                    liveUrlBean2.setSharpnessLevel("L");
                    liveUrlBean2.setLivePullUrl(optString4);
                    liveUrlBean2.setLivePullUrlByFlv(optString4);
                    liveUrlBean2.setLiveFreePullUrl(optString4);
                    liveUrlBean2.setLiveFreePullUrlByFlv(optString4);
                    arrayList3.add(liveUrlBean2);
                    angleMoreEntity.setList(arrayList3);
                    arrayList2.add(angleMoreEntity);
                }
            }
            String optString5 = optJSONObject.optString("viewAngleUrl2");
            if (!TextUtils.isEmpty(optString5)) {
                AngleMoreEntity angleMoreEntity2 = new AngleMoreEntity();
                angleMoreEntity2.setName(optJSONObject.optString("viewAngleName2"));
                angleMoreEntity2.setCover(optJSONObject.optString("viewAngleCover2"));
                ArrayList arrayList4 = new ArrayList();
                SharpnessEntity.LiveUrlBean liveUrlBean3 = new SharpnessEntity.LiveUrlBean();
                liveUrlBean3.setJobNumber(optJSONObject.optString("jobNumber"));
                liveUrlBean3.setSharpnessName("原画");
                liveUrlBean3.setSharpnessLevel("L");
                liveUrlBean3.setLivePullUrl(optString5);
                liveUrlBean3.setLivePullUrlByFlv(optString5);
                liveUrlBean3.setLiveFreePullUrl(optString5);
                liveUrlBean3.setLiveFreePullUrlByFlv(optString5);
                arrayList4.add(liveUrlBean3);
                angleMoreEntity2.setList(arrayList4);
                arrayList2.add(angleMoreEntity2);
            }
            String optString6 = optJSONObject.optString("viewAngleUrl3");
            if (!TextUtils.isEmpty(optString6)) {
                AngleMoreEntity angleMoreEntity3 = new AngleMoreEntity();
                angleMoreEntity3.setName(optJSONObject.optString("viewAngleName3"));
                angleMoreEntity3.setCover(optJSONObject.optString("viewAngleCover3"));
                ArrayList arrayList5 = new ArrayList();
                SharpnessEntity.LiveUrlBean liveUrlBean4 = new SharpnessEntity.LiveUrlBean();
                liveUrlBean4.setJobNumber(optJSONObject.optString("jobNumber"));
                liveUrlBean4.setSharpnessName("原画");
                liveUrlBean4.setSharpnessLevel("L");
                liveUrlBean4.setLivePullUrl(optString6);
                liveUrlBean4.setLivePullUrlByFlv(optString6);
                liveUrlBean4.setLiveFreePullUrl(optString6);
                liveUrlBean4.setLiveFreePullUrlByFlv(optString6);
                arrayList5.add(liveUrlBean4);
                angleMoreEntity3.setList(arrayList5);
                arrayList2.add(angleMoreEntity3);
            }
            String optString7 = optJSONObject.optString("viewAngleUrl4");
            if (!TextUtils.isEmpty(optString7)) {
                AngleMoreEntity angleMoreEntity4 = new AngleMoreEntity();
                angleMoreEntity4.setName(optJSONObject.optString("viewAngleName4"));
                angleMoreEntity4.setCover(optJSONObject.optString("viewAngleCover4"));
                ArrayList arrayList6 = new ArrayList();
                SharpnessEntity.LiveUrlBean liveUrlBean5 = new SharpnessEntity.LiveUrlBean();
                liveUrlBean5.setJobNumber(optJSONObject.optString("jobNumber"));
                liveUrlBean5.setSharpnessName("原画");
                liveUrlBean5.setSharpnessLevel("L");
                liveUrlBean5.setLivePullUrl(optString7);
                liveUrlBean5.setLivePullUrlByFlv(optString7);
                liveUrlBean5.setLiveFreePullUrl(optString7);
                liveUrlBean5.setLiveFreePullUrlByFlv(optString7);
                arrayList6.add(liveUrlBean5);
                angleMoreEntity4.setList(arrayList6);
                arrayList2.add(angleMoreEntity4);
            }
            sharpnessEntity.setLiveViewAngleMoreList(arrayList2);
        }
        sharpnessEntity.setData(arrayList);
        return sharpnessEntity;
    }

    private void parseGoodsList(SharpnessEntity sharpnessEntity, JSONArray jSONArray) {
        if (jSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                GoodListEntity goodListEntity = new GoodListEntity();
                goodListEntity.setId(optJSONObject.optString("goodsId"));
                goodListEntity.setGoodsUrl(optJSONObject.optString("goodsLink"));
                goodListEntity.setName(optJSONObject.optString("goodsName"));
                goodListEntity.setDesc(optJSONObject.optString("goodsDesc"));
                goodListEntity.setCoverImgUrl(optJSONObject.optString("goodsImg"));
                goodListEntity.setOriginalPrice(optJSONObject.optString("originalPrice"));
                goodListEntity.setPrice(optJSONObject.optString("goodsPrice"));
                goodListEntity.setOutSideUrl(optJSONObject.optString("outSideUrl"));
                arrayList.add(goodListEntity);
                if (i == 0) {
                    ShopEntity shopEntity = new ShopEntity();
                    ShopEntity.DataBean dataBean = new ShopEntity.DataBean();
                    dataBean.setHaveGoods("Y");
                    dataBean.setGoods(a2B(goodListEntity));
                    shopEntity.setData(dataBean);
                    shopEntity.setMessage("添加成功");
                    shopEntity.setStatusCode("0000");
                    sharpnessEntity.setShopEntity(shopEntity);
                }
            }
            sharpnessEntity.setGoodlist(arrayList);
        }
    }

    private ShopEntity.DataBean.GoodsBean a2B(GoodListEntity goodListEntity) {
        ShopEntity.DataBean.GoodsBean goodsBean = new ShopEntity.DataBean.GoodsBean();
        goodsBean.setId(goodListEntity.getId());
        goodsBean.setGoodsUrl(goodListEntity.getGoodsUrl());
        goodsBean.setName(goodListEntity.getName());
        goodsBean.setDesc(goodListEntity.getDesc());
        goodsBean.setCoverImgUrl(goodListEntity.getCoverImgUrl());
        goodsBean.setOriginalPrice(goodListEntity.getOriginalPrice());
        goodsBean.setPrice(goodListEntity.getPrice());
        return goodsBean;
    }

    private ZhuboDataEntity parseUserinfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            ZhuboDataEntity zhuboDataEntity = new ZhuboDataEntity();
            zhuboDataEntity.setStatusCode("0000");
            zhuboDataEntity.setMessage("成功");
            ZhuboDataEntity.AnchorInfoBean anchorInfoBean = new ZhuboDataEntity.AnchorInfoBean();
            anchorInfoBean.setUserId(jSONObject.optString("userId"));
            anchorInfoBean.setUserName(jSONObject.optString("userName"));
            anchorInfoBean.setBornDate(jSONObject.optString("bornDate"));
            anchorInfoBean.setBusinessId(jSONObject.optString("businessId"));
            anchorInfoBean.setBusinessName(jSONObject.optString("businessName"));
            anchorInfoBean.setCurrentUserIsLeader(jSONObject.optString("currentUserIsLeader"));
            anchorInfoBean.setFansNum(jSONObject.optString("fansNum"));
            anchorInfoBean.setFollowType(jSONObject.optString("followType"));
            anchorInfoBean.setForcePassword(jSONObject.optString("forcePassword"));
            anchorInfoBean.setGender(jSONObject.optString("gender"));
            anchorInfoBean.setIsNeedCheck(jSONObject.optString("isNeedCheck"));
            anchorInfoBean.setLiveCoverImg(jSONObject.optString("liveCoverImg"));
            anchorInfoBean.setLiveFreePullUrl(jSONObject.optString("liveFreePullUrl"));
            anchorInfoBean.setLiveFreePullUrlByFlv(jSONObject.optString("liveFreePullUrlByFlv"));
            anchorInfoBean.setLiveIndexUrl(jSONObject.optString("liveIndexUrl"));
            anchorInfoBean.setLiveNotice(jSONObject.optString("liveNotice"));
            anchorInfoBean.setLivePullUrl(jSONObject.optString("livePullUrl"));
            anchorInfoBean.setLivePullUrlByFlv(jSONObject.optString("livePullUrlByFlv"));
            anchorInfoBean.setLiveRoom(jSONObject.optString("liveRoom"));
            anchorInfoBean.setLiveRound(jSONObject.optString("liveRound"));
            anchorInfoBean.setLiveStatus(jSONObject.optString("liveStatus"));
            anchorInfoBean.setLiving(jSONObject.optString("living"));
            anchorInfoBean.setLiveTitle(jSONObject.optString("liveTitle"));
            anchorInfoBean.setPasswordCheckNum(jSONObject.optString("passwordCheckNum"));
            anchorInfoBean.setReceiveGiftNum(jSONObject.optString("receiveGiftNum"));
            anchorInfoBean.setShowType(jSONObject.optString("showType"));
            anchorInfoBean.setSignature(jSONObject.optString("signature"));
            anchorInfoBean.setUserCity(jSONObject.optString("userCity"));
            anchorInfoBean.setUserCityName(jSONObject.optString("userCityName"));
            anchorInfoBean.setUserImg(jSONObject.optString("userImg"));
            anchorInfoBean.setUserIndexUrl(jSONObject.optString("userIndexUrl"));
            anchorInfoBean.setUserJumpHallUrl(jSONObject.optString("userJumpHallUrl"));
            anchorInfoBean.setUserPraiseNum(jSONObject.optString("userPraiseNum"));
            anchorInfoBean.setUserProvince(jSONObject.optString("userProvince"));
            anchorInfoBean.setUserType(jSONObject.optString("userType"));
            anchorInfoBean.setUserTypeParse(jSONObject.optString("userTypeParse"));
            anchorInfoBean.setDataType("3");
            zhuboDataEntity.setAnchorInfoBean(anchorInfoBean);
            return zhuboDataEntity;
        }
        return null;
    }
}
