package com.sinovatech.unicom.separatemodule.search;

import com.sinovatech.unicom.separatemodule.search.SearchEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SearchEntityCursor extends Cursor<SearchEntity> {
    private static final SearchEntity_.SearchEntityIdGetter ID_GETTER = SearchEntity_.__ID_GETTER;
    private static final int __ID_mobile = SearchEntity_.mobile.f24389id;
    private static final int __ID_commandCode = SearchEntity_.commandCode.f24389id;
    private static final int __ID_proviceCode = SearchEntity_.proviceCode.f24389id;
    private static final int __ID_cityCode = SearchEntity_.cityCode.f24389id;
    private static final int __ID_searchId = SearchEntity_.searchId.f24389id;
    private static final int __ID_title = SearchEntity_.title.f24389id;
    private static final int __ID_url = SearchEntity_.url.f24389id;
    private static final int __ID_imageURL = SearchEntity_.imageURL.f24389id;
    private static final int __ID_desc = SearchEntity_.desc.f24389id;
    private static final int __ID_price = SearchEntity_.price.f24389id;
    private static final int __ID_needLogin = SearchEntity_.needLogin.f24389id;
    private static final int __ID_backMode = SearchEntity_.backMode.f24389id;
    private static final int __ID_isGroup = SearchEntity_.isGroup.f24389id;
    private static final int __ID_sectionsTitle = SearchEntity_.sectionsTitle.f24389id;
    private static final int __ID_sectionsIndex = SearchEntity_.sectionsIndex.f24389id;
    private static final int __ID_type = SearchEntity_.type.f24389id;
    private static final int __ID_isFooter = SearchEntity_.isFooter.f24389id;
    private static final int __ID_hisBg = SearchEntity_.hisBg.f24389id;
    private static final int __ID_goodsImage = SearchEntity_.goodsImage.f24389id;
    private static final int __ID_goodsName = SearchEntity_.goodsName.f24389id;
    private static final int __ID_goodsPrice = SearchEntity_.goodsPrice.f24389id;
    private static final int __ID_goodsUrl = SearchEntity_.goodsUrl.f24389id;
    private static final int __ID_activityName = SearchEntity_.activityName.f24389id;
    private static final int __ID_NAME = SearchEntity_.NAME.f24389id;
    private static final int __ID_IS_BEST = SearchEntity_.IS_BEST.f24389id;
    private static final int __ID_IS_HOT = SearchEntity_.IS_HOT.f24389id;
    private static final int __ID_WOZHIDAOURL = SearchEntity_.WOZHIDAOURL.f24389id;
    private static final int __ID_floorPrice = SearchEntity_.floorPrice.f24389id;
    private static final int __ID_marketPrice = SearchEntity_.marketPrice.f24389id;
    private static final int __ID_subtitle = SearchEntity_.subtitle.f24389id;
    private static final int __ID_best_img = SearchEntity_.best_img.f24389id;
    private static final int __ID_hot_img = SearchEntity_.hot_img.f24389id;
    private static final int __ID_templateTitle = SearchEntity_.templateTitle.f24389id;
    private static final int __ID_searchKey = SearchEntity_.searchKey.f24389id;
    private static final int __ID_describeInfo = SearchEntity_.describeInfo.f24389id;
    private static final int __ID_templateName = SearchEntity_.templateName.f24389id;
    private static final int __ID_dataId = SearchEntity_.dataId.f24389id;
    private static final int __ID_gameType = SearchEntity_.gameType.f24389id;
    private static final int __ID_clientVersion = SearchEntity_.clientVersion.f24389id;
    private static final int __ID_huodong_time = SearchEntity_.huodong_time.f24389id;
    private static final int __ID_huodong_id = SearchEntity_.huodong_id.f24389id;
    private static final int __ID_huodong_hallId = SearchEntity_.huodong_hallId.f24389id;
    private static final int __ID_actType = SearchEntity_.actType.f24389id;
    private static final int __ID_browseNum = SearchEntity_.browseNum.f24389id;
    private static final int __ID_author = SearchEntity_.author.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<SearchEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<SearchEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new SearchEntityCursor(transaction, j, boxStore);
        }
    }

    public SearchEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, SearchEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(SearchEntity searchEntity) {
        return ID_GETTER.getId(searchEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(SearchEntity searchEntity) {
        String mobile = searchEntity.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String commandCode = searchEntity.getCommandCode();
        int i2 = commandCode != null ? __ID_commandCode : 0;
        String proviceCode = searchEntity.getProviceCode();
        int i3 = proviceCode != null ? __ID_proviceCode : 0;
        String cityCode = searchEntity.getCityCode();
        collect400000(this.cursor, 0L, 1, i, mobile, i2, commandCode, i3, proviceCode, cityCode != null ? __ID_cityCode : 0, cityCode);
        String searchId = searchEntity.getSearchId();
        int i4 = searchId != null ? __ID_searchId : 0;
        String title = searchEntity.getTitle();
        int i5 = title != null ? __ID_title : 0;
        String url = searchEntity.getUrl();
        int i6 = url != null ? __ID_url : 0;
        String imageURL = searchEntity.getImageURL();
        collect400000(this.cursor, 0L, 0, i4, searchId, i5, title, i6, url, imageURL != null ? __ID_imageURL : 0, imageURL);
        String desc = searchEntity.getDesc();
        int i7 = desc != null ? __ID_desc : 0;
        String price = searchEntity.getPrice();
        int i8 = price != null ? __ID_price : 0;
        String needLogin = searchEntity.getNeedLogin();
        int i9 = needLogin != null ? __ID_needLogin : 0;
        String str = searchEntity.backMode;
        collect400000(this.cursor, 0L, 0, i7, desc, i8, price, i9, needLogin, str != null ? __ID_backMode : 0, str);
        String str2 = searchEntity.sectionsTitle;
        int i10 = str2 != null ? __ID_sectionsTitle : 0;
        String type = searchEntity.getType();
        int i11 = type != null ? __ID_type : 0;
        String goodsImage = searchEntity.getGoodsImage();
        int i12 = goodsImage != null ? __ID_goodsImage : 0;
        String goodsName = searchEntity.getGoodsName();
        collect400000(this.cursor, 0L, 0, i10, str2, i11, type, i12, goodsImage, goodsName != null ? __ID_goodsName : 0, goodsName);
        String goodsPrice = searchEntity.getGoodsPrice();
        int i13 = goodsPrice != null ? __ID_goodsPrice : 0;
        String goodsUrl = searchEntity.getGoodsUrl();
        int i14 = goodsUrl != null ? __ID_goodsUrl : 0;
        String activityName = searchEntity.getActivityName();
        int i15 = activityName != null ? __ID_activityName : 0;
        String name = searchEntity.getNAME();
        collect400000(this.cursor, 0L, 0, i13, goodsPrice, i14, goodsUrl, i15, activityName, name != null ? __ID_NAME : 0, name);
        String is_best = searchEntity.getIS_BEST();
        int i16 = is_best != null ? __ID_IS_BEST : 0;
        String is_hot = searchEntity.getIS_HOT();
        int i17 = is_hot != null ? __ID_IS_HOT : 0;
        String wozhidaourl = searchEntity.getWOZHIDAOURL();
        int i18 = wozhidaourl != null ? __ID_WOZHIDAOURL : 0;
        String floorPrice = searchEntity.getFloorPrice();
        collect400000(this.cursor, 0L, 0, i16, is_best, i17, is_hot, i18, wozhidaourl, floorPrice != null ? __ID_floorPrice : 0, floorPrice);
        String marketPrice = searchEntity.getMarketPrice();
        int i19 = marketPrice != null ? __ID_marketPrice : 0;
        String subtitle = searchEntity.getSubtitle();
        int i20 = subtitle != null ? __ID_subtitle : 0;
        String best_img = searchEntity.getBest_img();
        int i21 = best_img != null ? __ID_best_img : 0;
        String hot_img = searchEntity.getHot_img();
        collect400000(this.cursor, 0L, 0, i19, marketPrice, i20, subtitle, i21, best_img, hot_img != null ? __ID_hot_img : 0, hot_img);
        String templateTitle = searchEntity.getTemplateTitle();
        int i22 = templateTitle != null ? __ID_templateTitle : 0;
        String searchKey = searchEntity.getSearchKey();
        int i23 = searchKey != null ? __ID_searchKey : 0;
        String describeInfo = searchEntity.getDescribeInfo();
        int i24 = describeInfo != null ? __ID_describeInfo : 0;
        String templateName = searchEntity.getTemplateName();
        collect400000(this.cursor, 0L, 0, i22, templateTitle, i23, searchKey, i24, describeInfo, templateName != null ? __ID_templateName : 0, templateName);
        String dataId = searchEntity.getDataId();
        int i25 = dataId != null ? __ID_dataId : 0;
        String gameType = searchEntity.getGameType();
        int i26 = gameType != null ? __ID_gameType : 0;
        String clientVersion = searchEntity.getClientVersion();
        int i27 = clientVersion != null ? __ID_clientVersion : 0;
        String huodong_time = searchEntity.getHuodong_time();
        collect400000(this.cursor, 0L, 0, i25, dataId, i26, gameType, i27, clientVersion, huodong_time != null ? __ID_huodong_time : 0, huodong_time);
        String huodong_id = searchEntity.getHuodong_id();
        int i28 = huodong_id != null ? __ID_huodong_id : 0;
        String huodong_hallId = searchEntity.getHuodong_hallId();
        int i29 = huodong_hallId != null ? __ID_huodong_hallId : 0;
        String actType = searchEntity.getActType();
        int i30 = actType != null ? __ID_actType : 0;
        String str3 = searchEntity.browseNum;
        collect400000(this.cursor, 0L, 0, i28, huodong_id, i29, huodong_hallId, i30, actType, str3 != null ? __ID_browseNum : 0, str3);
        String str4 = searchEntity.author;
        long collect313311 = collect313311(this.cursor, searchEntity.getId(), 2, str4 != null ? __ID_author : 0, str4, 0, null, 0, null, 0, null, __ID_sectionsIndex, searchEntity.sectionsIndex, __ID_hisBg, searchEntity.getHisBg(), __ID_isGroup, searchEntity.isGroup ? 1L : 0L, __ID_isFooter, searchEntity.isFooter() ? 1 : 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        searchEntity.setId(collect313311);
        return collect313311;
    }
}
