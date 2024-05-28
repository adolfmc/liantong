package com.baidu.rtc;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum WebSocketStatusCode {
    DEFAULT_ERROR(400),
    UN_AUTHORIZED(401),
    JANUS_VIDEOROOM_ERROR_UNKNOWN_ERROR(499),
    JANUS_VIDEOROOM_ERROR_NO_MESSAGE(421),
    JANUS_VIDEOROOM_ERROR_INVALID_JSON(422),
    JANUS_VIDEOROOM_ERROR_INVALID_REQUEST(423),
    JANUS_VIDEOROOM_ERROR_JOIN_FIRST(424),
    ERROR_ALREADY_JOINED(425),
    JANUS_VIDEOROOM_ERROR_NO_SUCH_ROOM(426),
    JANUS_VIDEOROOM_ERROR_ROOM_EXISTS(427),
    JANUS_VIDEOROOM_ERROR_NO_SUCH_FEED(428),
    ERROR_MISSING_OR_INVALID_ELEMENT(429),
    JANUS_VIDEOROOM_ERROR_INVALID_ELEMENT(430),
    JANUS_VIDEOROOM_ERROR_INVALID_SDP_TYPE(431),
    JANUS_VIDEOROOM_ERROR_PUBLISHERS_FULL(432),
    JANUS_VIDEOROOM_ERROR_UNAUTHORIZED(433),
    JANUS_VIDEOROOM_ERROR_ALREADY_PUBLISHED(434, "Can't publish, already published/publishing"),
    JANUS_VIDEOROOM_ERROR_NOT_PUBLISHED(435, "Can't unpublish, not published"),
    JANUS_VIDEOROOM_ERROR_ID_EXISTS(436),
    JANUS_VIDEOROOM_ERROR_INVALID_SDP(437),
    JANUS_VIDEOROOM_ERROR_INCORRECT_USER_OR_ROOM(438, "Incorrect user or room"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_PUBLISHER_TRANSFER_CONFIG(439, "Incorrect publisher transfer configure, client cannot disable audio/video/data all"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_SEND_DATA_PARAMS(440, "Incorrect send data params, incorrect room/user/data"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_SET_USER_ATTRIBUTE_PARAMS(441, "Incorrect set user attribute params, incorrect room/user/attribute"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_GET_USER_ATTRIBUTE_PARAMS(442, "Incorrect get user attribute params, incorrect room/user"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_KICKOUT_USER_PARAMS(443, "Incorrect kick out user params, incorrect room/target"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_DISBAND_ROOM_PARAMS(444, "Incorrect disband room params, incorrect room"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_FORWARD_CONFIGURE_PARAMS(445, "Incorrect forward configure params, incorrect room/target/audio/video/data"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_FORWARD_CONFIGURE_PARAMS2(446, "Incorrect forward configure params, audio/video/data must have same configure"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_CONTROL_BYPASS_PARAMS(447, "Incorrect control bypass params, incorrect body/room/id/rtmp"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_SETUP_PARAMS(450, "Incorrect setup params, incorrect body/appId/roomId/roomName/userId"),
    JANUS_VIDEOROOM_ERROR_PARTICIPANT_ALREADY_JOINED(451, "Participant already joined"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_START_MEDIA_RELAY_PARAMS(452, "Incorrect start media relay params, incorrect body/dest_room_name"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_STOP_MEDIA_RELAY_PARAMS(453, "Incorrect stop media relay params, incorrect body/dest_room_name"),
    JANUS_VIDEOROOM_ERROR_INCORRECT_PARTICIPANT(454, "Participant info/type is error"),
    JANUS_VIDEOROOM_ERROR_START_MEDIA_RELAY_CONFIG_EXISTS(455, "Start media relay config in dest room already exists"),
    JANUS_VIDEOROOM_ERROR_USER_EXISTS_IN_DEST_ROOM(456, "User already exists in dest room"),
    JANUS_VIDEOROOM_ERROR_USER_NOT_EXIST(457, "Participant may not be setuped"),
    JANUS_VIDEOROOM_ERROR_USER_STATE(458, "Subscriber may not be webrtcup"),
    GATEWAY_ERROR_NOT_JOINED(470, "Participant is not joined"),
    GATEWAY_ERROR_INCORRECT_STATUS(471, "Participant is incorrect status"),
    GATEWAY_INVALID_APPID(403, "Invalid app_id"),
    GATEWAY_ERROR_TOKEN(401, "Error token"),
    ERROR_NULL_ROOM_DETAIL(500, "Platform response null for room detail"),
    ERROR_ROOM_JOINBANNED(501, "Room is join-banned"),
    ERROR_PUBLISHERS_REACH_THRESHOLD(502, "Publishers number reached the threshold"),
    ERROR_LISTENERS_REACH_THRESHOLD(503, "Listeners number reached the threshold"),
    ERROR_USER_KICKOUTED(504, "User is kick out"),
    ERROR_ROOM_DISBANDED(505, "Room is disbanded");
    
    String msg;
    public int statusCode;

    WebSocketStatusCode(int i) {
        this.statusCode = i;
        this.msg = "";
    }

    WebSocketStatusCode(int i, String str) {
        this.statusCode = i;
        this.msg = str;
    }
}
