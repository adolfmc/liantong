package com.sinovatech.unicom.separatemodule.language.entity;

import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class LanguageEntityCursor extends Cursor<LanguageEntity> {
    private static final LanguageEntity_.LanguageEntityIdGetter ID_GETTER = LanguageEntity_.__ID_GETTER;
    private static final int __ID_languageName = LanguageEntity_.languageName.f24389id;
    private static final int __ID_languageCode = LanguageEntity_.languageCode.f24389id;
    private static final int __ID_rightToleft = LanguageEntity_.rightToleft.f24389id;
    private static final int __ID_isSelect = LanguageEntity_.isSelect.f24389id;
    private static final int __ID_url = LanguageEntity_.url.f24389id;
    private static final int __ID_ywshow = LanguageEntity_.ywshow.f24389id;
    private static final int __ID_languageDesc = LanguageEntity_.languageDesc.f24389id;
    private static final int __ID_reminderPop = LanguageEntity_.reminderPop.f24389id;
    private static final int __ID_switchLanguagePop = LanguageEntity_.switchLanguagePop.f24389id;
    private static final int __ID_cancelBtnPop = LanguageEntity_.cancelBtnPop.f24389id;
    private static final int __ID_confirmBtnPop = LanguageEntity_.confirmBtnPop.f24389id;
    private static final int __ID_showDirectionFlag = LanguageEntity_.showDirectionFlag.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<LanguageEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LanguageEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LanguageEntityCursor(transaction, j, boxStore);
        }
    }

    public LanguageEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LanguageEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LanguageEntity languageEntity) {
        return ID_GETTER.getId(languageEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(LanguageEntity languageEntity) {
        String str = languageEntity.languageName;
        int i = str != null ? __ID_languageName : 0;
        String str2 = languageEntity.languageCode;
        int i2 = str2 != null ? __ID_languageCode : 0;
        String str3 = languageEntity.rightToleft;
        int i3 = str3 != null ? __ID_rightToleft : 0;
        String url = languageEntity.getUrl();
        collect400000(this.cursor, 0L, 1, i, str, i2, str2, i3, str3, url != null ? __ID_url : 0, url);
        String ywshow = languageEntity.getYwshow();
        int i4 = ywshow != null ? __ID_ywshow : 0;
        String languageDesc = languageEntity.getLanguageDesc();
        int i5 = languageDesc != null ? __ID_languageDesc : 0;
        String reminderPop = languageEntity.getReminderPop();
        int i6 = reminderPop != null ? __ID_reminderPop : 0;
        String switchLanguagePop = languageEntity.getSwitchLanguagePop();
        collect400000(this.cursor, 0L, 0, i4, ywshow, i5, languageDesc, i6, reminderPop, switchLanguagePop != null ? __ID_switchLanguagePop : 0, switchLanguagePop);
        String cancelBtnPop = languageEntity.getCancelBtnPop();
        int i7 = cancelBtnPop != null ? __ID_cancelBtnPop : 0;
        String confirmBtnPop = languageEntity.getConfirmBtnPop();
        int i8 = confirmBtnPop != null ? __ID_confirmBtnPop : 0;
        String showDirectionFlag = languageEntity.getShowDirectionFlag();
        long collect313311 = collect313311(this.cursor, languageEntity.getId(), 2, i7, cancelBtnPop, i8, confirmBtnPop, showDirectionFlag != null ? __ID_showDirectionFlag : 0, showDirectionFlag, 0, null, __ID_isSelect, languageEntity.isSelect ? 1L : 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        languageEntity.setId(collect313311);
        return collect313311;
    }
}
