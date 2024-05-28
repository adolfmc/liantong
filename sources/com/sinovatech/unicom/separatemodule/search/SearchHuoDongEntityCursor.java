package com.sinovatech.unicom.separatemodule.search;

import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SearchHuoDongEntityCursor extends Cursor<SearchHuoDongEntity> {
    private static final SearchHuoDongEntity_.SearchHuoDongEntityIdGetter ID_GETTER = SearchHuoDongEntity_.__ID_GETTER;
    private static final int __ID_actType = SearchHuoDongEntity_.actType.f24389id;
    private static final int __ID_hallId = SearchHuoDongEntity_.hallId.f24389id;
    private static final int __ID_huodongId = SearchHuoDongEntity_.huodongId.f24389id;
    private static final int __ID_isNeedLogin = SearchHuoDongEntity_.isNeedLogin.f24389id;
    private static final int __ID_linkUrl = SearchHuoDongEntity_.linkUrl.f24389id;
    private static final int __ID_searchWordName = SearchHuoDongEntity_.searchWordName.f24389id;
    private static final int __ID_time = SearchHuoDongEntity_.time.f24389id;
    private static final int __ID_mobile = SearchHuoDongEntity_.mobile.f24389id;
    private static final int __ID_proviceCode = SearchHuoDongEntity_.proviceCode.f24389id;
    private static final int __ID_cityCode = SearchHuoDongEntity_.cityCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<SearchHuoDongEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<SearchHuoDongEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new SearchHuoDongEntityCursor(transaction, j, boxStore);
        }
    }

    public SearchHuoDongEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, SearchHuoDongEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(SearchHuoDongEntity searchHuoDongEntity) {
        return ID_GETTER.getId(searchHuoDongEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(SearchHuoDongEntity searchHuoDongEntity) {
        String actType = searchHuoDongEntity.getActType();
        int i = actType != null ? __ID_actType : 0;
        String hallId = searchHuoDongEntity.getHallId();
        int i2 = hallId != null ? __ID_hallId : 0;
        String huodongId = searchHuoDongEntity.getHuodongId();
        int i3 = huodongId != null ? __ID_huodongId : 0;
        String isNeedLogin = searchHuoDongEntity.getIsNeedLogin();
        collect400000(this.cursor, 0L, 1, i, actType, i2, hallId, i3, huodongId, isNeedLogin != null ? __ID_isNeedLogin : 0, isNeedLogin);
        String linkUrl = searchHuoDongEntity.getLinkUrl();
        int i4 = linkUrl != null ? __ID_linkUrl : 0;
        String searchWordName = searchHuoDongEntity.getSearchWordName();
        int i5 = searchWordName != null ? __ID_searchWordName : 0;
        String time = searchHuoDongEntity.getTime();
        int i6 = time != null ? __ID_time : 0;
        String mobile = searchHuoDongEntity.getMobile();
        collect400000(this.cursor, 0L, 0, i4, linkUrl, i5, searchWordName, i6, time, mobile != null ? __ID_mobile : 0, mobile);
        String proviceCode = searchHuoDongEntity.getProviceCode();
        int i7 = proviceCode != null ? __ID_proviceCode : 0;
        String cityCode = searchHuoDongEntity.getCityCode();
        long collect313311 = collect313311(this.cursor, searchHuoDongEntity.getId(), 2, i7, proviceCode, cityCode != null ? __ID_cityCode : 0, cityCode, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        searchHuoDongEntity.setId(collect313311);
        return collect313311;
    }
}
