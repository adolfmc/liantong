package com.sinovatech.unicom.separatemodule.miniprogram.log;

import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CumpLogEntityCursor extends Cursor<CumpLogEntity> {
    private static final CumpLogEntity_.CumpLogEntityIdGetter ID_GETTER = CumpLogEntity_.__ID_GETTER;
    private static final int __ID_sceneNumber = CumpLogEntity_.sceneNumber.f24389id;
    private static final int __ID_subSceneNumber = CumpLogEntity_.subSceneNumber.f24389id;
    private static final int __ID_stepNumber = CumpLogEntity_.stepNumber.f24389id;
    private static final int __ID_serialNumber = CumpLogEntity_.serialNumber.f24389id;
    private static final int __ID_startTime = CumpLogEntity_.startTime.f24389id;
    private static final int __ID_endTime = CumpLogEntity_.endTime.f24389id;
    private static final int __ID_takeupTime = CumpLogEntity_.takeupTime.f24389id;
    private static final int __ID_edopAppId = CumpLogEntity_.edopAppId.f24389id;
    private static final int __ID_edopAppName = CumpLogEntity_.edopAppName.f24389id;
    private static final int __ID_url = CumpLogEntity_.url.f24389id;
    private static final int __ID_title = CumpLogEntity_.title.f24389id;
    private static final int __ID_response = CumpLogEntity_.response.f24389id;
    private static final int __ID_status = CumpLogEntity_.status.f24389id;
    private static final int __ID_oldEdopAppVersion = CumpLogEntity_.oldEdopAppVersion.f24389id;
    private static final int __ID_newEdopAppVersion = CumpLogEntity_.newEdopAppVersion.f24389id;
    private static final int __ID_authFunctionCode = CumpLogEntity_.authFunctionCode.f24389id;
    private static final int __ID_jsFunction = CumpLogEntity_.jsFunction.f24389id;
    private static final int __ID_interceptStatus = CumpLogEntity_.interceptStatus.f24389id;
    private static final int __ID_mobile = CumpLogEntity_.mobile.f24389id;
    private static final int __ID_provinceCode = CumpLogEntity_.provinceCode.f24389id;
    private static final int __ID_cityCode = CumpLogEntity_.cityCode.f24389id;
    private static final int __ID_deviceId = CumpLogEntity_.deviceId.f24389id;
    private static final int __ID_osType = CumpLogEntity_.osType.f24389id;
    private static final int __ID_osVersion = CumpLogEntity_.osVersion.f24389id;
    private static final int __ID_clientVersion = CumpLogEntity_.clientVersion.f24389id;
    private static final int __ID_deviceBrand = CumpLogEntity_.deviceBrand.f24389id;
    private static final int __ID_deviceModel = CumpLogEntity_.deviceModel.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CumpLogEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CumpLogEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CumpLogEntityCursor(transaction, j, boxStore);
        }
    }

    public CumpLogEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CumpLogEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CumpLogEntity cumpLogEntity) {
        return ID_GETTER.getId(cumpLogEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CumpLogEntity cumpLogEntity) {
        String sceneNumber = cumpLogEntity.getSceneNumber();
        int i = sceneNumber != null ? __ID_sceneNumber : 0;
        String subSceneNumber = cumpLogEntity.getSubSceneNumber();
        int i2 = subSceneNumber != null ? __ID_subSceneNumber : 0;
        String stepNumber = cumpLogEntity.getStepNumber();
        int i3 = stepNumber != null ? __ID_stepNumber : 0;
        String serialNumber = cumpLogEntity.getSerialNumber();
        collect400000(this.cursor, 0L, 1, i, sceneNumber, i2, subSceneNumber, i3, stepNumber, serialNumber != null ? __ID_serialNumber : 0, serialNumber);
        String startTime = cumpLogEntity.getStartTime();
        int i4 = startTime != null ? __ID_startTime : 0;
        String endTime = cumpLogEntity.getEndTime();
        int i5 = endTime != null ? __ID_endTime : 0;
        String takeupTime = cumpLogEntity.getTakeupTime();
        int i6 = takeupTime != null ? __ID_takeupTime : 0;
        String edopAppId = cumpLogEntity.getEdopAppId();
        collect400000(this.cursor, 0L, 0, i4, startTime, i5, endTime, i6, takeupTime, edopAppId != null ? __ID_edopAppId : 0, edopAppId);
        String edopAppName = cumpLogEntity.getEdopAppName();
        int i7 = edopAppName != null ? __ID_edopAppName : 0;
        String url = cumpLogEntity.getUrl();
        int i8 = url != null ? __ID_url : 0;
        String title = cumpLogEntity.getTitle();
        int i9 = title != null ? __ID_title : 0;
        String response = cumpLogEntity.getResponse();
        collect400000(this.cursor, 0L, 0, i7, edopAppName, i8, url, i9, title, response != null ? __ID_response : 0, response);
        String status = cumpLogEntity.getStatus();
        int i10 = status != null ? __ID_status : 0;
        String oldEdopAppVersion = cumpLogEntity.getOldEdopAppVersion();
        int i11 = oldEdopAppVersion != null ? __ID_oldEdopAppVersion : 0;
        String newEdopAppVersion = cumpLogEntity.getNewEdopAppVersion();
        int i12 = newEdopAppVersion != null ? __ID_newEdopAppVersion : 0;
        String authFunctionCode = cumpLogEntity.getAuthFunctionCode();
        collect400000(this.cursor, 0L, 0, i10, status, i11, oldEdopAppVersion, i12, newEdopAppVersion, authFunctionCode != null ? __ID_authFunctionCode : 0, authFunctionCode);
        String jsFunction = cumpLogEntity.getJsFunction();
        int i13 = jsFunction != null ? __ID_jsFunction : 0;
        String interceptStatus = cumpLogEntity.getInterceptStatus();
        int i14 = interceptStatus != null ? __ID_interceptStatus : 0;
        String mobile = cumpLogEntity.getMobile();
        int i15 = mobile != null ? __ID_mobile : 0;
        String provinceCode = cumpLogEntity.getProvinceCode();
        collect400000(this.cursor, 0L, 0, i13, jsFunction, i14, interceptStatus, i15, mobile, provinceCode != null ? __ID_provinceCode : 0, provinceCode);
        String cityCode = cumpLogEntity.getCityCode();
        int i16 = cityCode != null ? __ID_cityCode : 0;
        String deviceId = cumpLogEntity.getDeviceId();
        int i17 = deviceId != null ? __ID_deviceId : 0;
        String osType = cumpLogEntity.getOsType();
        int i18 = osType != null ? __ID_osType : 0;
        String osVersion = cumpLogEntity.getOsVersion();
        collect400000(this.cursor, 0L, 0, i16, cityCode, i17, deviceId, i18, osType, osVersion != null ? __ID_osVersion : 0, osVersion);
        String clientVersion = cumpLogEntity.getClientVersion();
        int i19 = clientVersion != null ? __ID_clientVersion : 0;
        String deviceBrand = cumpLogEntity.getDeviceBrand();
        int i20 = deviceBrand != null ? __ID_deviceBrand : 0;
        String deviceModel = cumpLogEntity.getDeviceModel();
        long collect313311 = collect313311(this.cursor, cumpLogEntity.getId(), 2, i19, clientVersion, i20, deviceBrand, deviceModel != null ? __ID_deviceModel : 0, deviceModel, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        cumpLogEntity.setId(collect313311);
        return collect313311;
    }
}
