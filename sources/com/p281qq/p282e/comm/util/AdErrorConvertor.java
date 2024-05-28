package com.p281qq.p282e.comm.util;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.util.AdErrorConvertor */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AdErrorConvertor {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static AdError formatErrorCode(int i) {
        AdError adError;
        switch (i) {
            case 2001:
                return new AdError(2001, "可能由于SDK版本较低，或系统应用不支持插件导致，可以将SDK升级至最新版本。如在进行通路测试或者仍未解决，请至反馈中心提交工单联系技术支持处理。");
            case 2002:
                adError = new AdError(2002, "内部错误。");
                break;
            case 2003:
                return new AdError(2003, "SDK未初始化。");
            default:
                switch (i) {
                    case 3001:
                    case 3004:
                    case 3005:
                        return new AdError(3001, "网络问题导致，请开发者检查下是否有连接代理或者网络环境不佳，建议使用稳定的4G、5G网络或者WiFi，确认网络状态后重试。");
                    case 3002:
                        return new AdError(3002, "网络超时。");
                    case 3003:
                        return new AdError(3003, "网络不可用。");
                    default:
                        switch (i) {
                            case 4001:
                                return new AdError(4001, "传入的参数有错误。");
                            case 4002:
                                return new AdError(4002, "Manifest文件中Activity/Service/Permission的声明有问题或者Permission权限未授予。");
                            case 4003:
                                return new AdError(4003, "广告位错误。");
                            case 4004:
                                return new AdError(4004, "开屏场景的广告容器不可见，请将广告容器设置为可见，并及时移除上次拉取广告的view避免容器被遮挡；如是开屏全屏广告，请确保调用了fetchFullScreenAndShowIn接口。如果仍未解决，请至反馈中心提交工单联系技术支持处理。");
                            case 4005:
                                return new AdError(4005, "广告容器尺寸错误。");
                            case 4006:
                                return new AdError(4006, "原生广告接口调用顺序错误，调用点击接口前未调用曝光接口。");
                            case 4007:
                                return new AdError(4007, "当前广告形态不支持模拟器，或开屏不支持Pad，建议使用真机、手机进行测试，如在进行通路测试或者仍未解决，请至反馈中心提交工单联系技术支持处理。");
                            case 4008:
                                return new AdError(4008, "设备方向不适合展示广告。");
                            case 4009:
                                return new AdError(4009, "开屏广告的自定义跳过按钮尺寸小于3x3dp。");
                            default:
                                switch (i) {
                                    case 4014:
                                        return new AdError(4014, "广告未成功返回或广告无效时，启动了广告展示，请在广告返回后再进行展示广告的操作。具体方法为，请在show之前，使用isValid判断广告是否有效，当结果为true时再去调用show方法。");
                                    case 4015:
                                        return new AdError(4015, "同一条广告不允许多次展示，请再次拉取后展示。");
                                    default:
                                        switch (i) {
                                            case 5001:
                                                return new AdError(5001, "后台数据异常，可能由于sdk版本过低导致。请升级优量汇SDK版本至最新，如仍有错误，请联系运营人员或至反馈中心提交工单联系技术支持处理。");
                                            case 5002:
                                                return new AdError(5002, "视频素材下载错误，如网络环境不佳导致视频类广告无法下载。建议检查网络环境后重试，如仍有错误，请联系优量汇运营人员或至反馈中心提单咨询。");
                                            case 5003:
                                                return new AdError(5003, "视频素材播放错误。");
                                            case 5004:
                                                return new AdError(5004, "没有匹配到合适的广告资源，属于调试中或者线上广告的正常现象。如果您在调试环境中遇到此问题，可以通过帮助中的广告助手解决此类问题。");
                                            case 5005:
                                                return new AdError(5005, "广告请求量或消耗等指标超过日限额，请明日00:30后再发送请求。");
                                            case 5006:
                                                return new AdError(5006, "包名校验错误，当前App的包名和优量汇移动联盟官网注册的媒体包名不一致，因此无广告返回");
                                            case 5007:
                                                return new AdError(5007, "资源加载错误。");
                                            case 5008:
                                                return new AdError(5008, "调试期间使用连接代理或者网络不佳，导致图片加载错误，建议关闭代理或者确保网络状况良好再去请求广告，如仍有错误，请至反馈中心提单咨询。");
                                            case 5009:
                                                return new AdError(5009, "广告请求量或者消耗等超过小时限额，请下一小时再发起请求。");
                                            case 5010:
                                                break;
                                            case 5011:
                                                return new AdError(5011, "原生模板渲染失败。");
                                            case 5012:
                                                return new AdError(5012, "广告已经过期，建议在调用show之前使用isValid方法判断下广告是否有效，广告有效再去拉取广告（如果开发者有使用预加载广告一定要先判断再展示）。");
                                            case 5013:
                                                return new AdError(5013, "当前广告请求过于频繁，建议适当降低请求频率。");
                                            case 5014:
                                                return new AdError(5014, "广告被定向过滤（多发生在下载类广告，如应用安装或未安装），属于一种正常现象，开发者可酌情忽略，也可以通过调整一次广告请求返回的广告数量进行缓解。");
                                            default:
                                                switch (i) {
                                                    case 5018:
                                                        return new AdError(5018, "当前请求所使用的广告位已被下线，请至开发者平台流量合作板块，检查广告位是否被关闭、冻结或者已被删除。");
                                                    case 5019:
                                                        return new AdError(5019, "appid被封，请至平台流量合作板块，检查appid的状态。");
                                                    default:
                                                        switch (i) {
                                                            case 5021:
                                                                return new AdError(5021, "该类型广告已废弃，请使用其他类型的广告。");
                                                            case 5022:
                                                                return new AdError(5022, "广告模板渲染失败。");
                                                            default:
                                                                switch (i) {
                                                                    case 100135:
                                                                        return new AdError(100135, "媒体未通过审核或广告位处于「封禁、暂停、冻结」等状态。建议检查媒体或广告位状态，如有疑问可联系运营人员或至反馈中心提单咨询。");
                                                                    case 100136:
                                                                        return new AdError(100136, "广告位未匹配到合适规格和样式的素材资源，建议调整或扩充广告的样式，如需了解详情，请至反馈中心提单咨询。");
                                                                    default:
                                                                        switch (i) {
                                                                            case 107034:
                                                                                break;
                                                                            case 107035:
                                                                                return new AdError(4013, "在旧版SDK上使用了模板视频的广告位。请升级优量汇SDK版本至最新。");
                                                                            default:
                                                                                switch (i) {
                                                                                    case 107049:
                                                                                        return new AdError(107049, "JS旧准入规则已废弃，目前不支持iOS设备以及微信环境，请使用符合准入规则的测试设备如安卓版手机QQ、QQ浏览器。");
                                                                                    case 107050:
                                                                                        return new AdError(107050, "SDK接口与广告位不匹配，广告位是模板2.0广告位，请调用SDK模板2.0接口请求广告。发生此问题时可至反馈中心提交工单(同时提供广告请求和回包数据)联系技术支持定位处理。");
                                                                                    default:
                                                                                        switch (i) {
                                                                                            case 109506:
                                                                                                return new AdError(5005, "广告请求量或者消耗等超过日限额，请明天再请求广告。");
                                                                                            case 109507:
                                                                                                return new AdError(5009, "广告请求量或者消耗等超过小时限额，请一小时后再请求广告。");
                                                                                            default:
                                                                                                switch (i) {
                                                                                                    case 132007:
                                                                                                        return new AdError(132007, "server bidding请求没有收到有效的广告素材，请检查实例化广告对象时使用的token是否过期（token有效期为90s）。");
                                                                                                    case 132008:
                                                                                                        return new AdError(132008, "server bidding请求广告时，token参数解析失败，请使用线上环境请求价格时返回的token参数而非联调工具中的token参数。");
                                                                                                    default:
                                                                                                        switch (i) {
                                                                                                            case 200101:
                                                                                                                return new AdError(2001, "初始化错误，详细码：200101");
                                                                                                            case 200102:
                                                                                                                return new AdError(2001, "初始化错误，详细码：200102");
                                                                                                            case 200103:
                                                                                                                return new AdError(2001, "初始化错误，详细码：200103");
                                                                                                            default:
                                                                                                                switch (i) {
                                                                                                                    case 200201:
                                                                                                                        adError = new AdError(2002, "内部错误，详细码：200201");
                                                                                                                        break;
                                                                                                                    case 200202:
                                                                                                                        adError = new AdError(2002, "内部错误，详细码：200202");
                                                                                                                        break;
                                                                                                                    default:
                                                                                                                        switch (i) {
                                                                                                                            case 400101:
                                                                                                                            case 400102:
                                                                                                                            case 400103:
                                                                                                                            case 400104:
                                                                                                                                return new AdError(4001, "传入的参数有错误。详细码：" + i);
                                                                                                                            default:
                                                                                                                                switch (i) {
                                                                                                                                    case 4011:
                                                                                                                                        return new AdError(4011, "由于网络环境不佳或连有连接代理，导致开屏广告拉取超过设置时间。请保障网络畅通，并根据错误信息提示修改嵌入代码；在开屏广告下，可以通过fetchDelay设置超时时间为5s或者其他时间。");
                                                                                                                                    case 4017:
                                                                                                                                        return new AdError(4017, "广告实例尚未准备好。请确保在注册实例成功后再调用实例的相关方法。");
                                                                                                                                    case 5024:
                                                                                                                                        return new AdError(5024, "广告位接口组合错误，请检查代码中是否存在开屏非全屏广告使用全屏接口的情况。");
                                                                                                                                    case 100133:
                                                                                                                                        return new AdError(100133, "请检查广告位ID的使用是否正确，并确保广告位处于开启状态（中途如有开关操作则需等待半小时后使用），如是新建广告位，请在新建30分钟后请求广告。");
                                                                                                                                    case 100159:
                                                                                                                                        return new AdError(4016, "应用横竖方向参数与广告位支持方向不匹配。");
                                                                                                                                    case 102006:
                                                                                                                                        return new AdError(102006, "没有找到符合价格要求或体验要求的广告，也可能由于请求过于频繁或返回未曝光，且产生的收入低导致无广告返回。建议适当调整底价，并提升广告曝光率，或至平台广告中心检查核对屏蔽规则。");
                                                                                                                                    case 107009:
                                                                                                                                        return new AdError(107009, "JS请求域名不匹配，请检查JS请求域名是否正确，确保与在平台注册的域名一致。可先进行域名校验，成功后再进行广告请求。");
                                                                                                                                    case 107011:
                                                                                                                                        return new AdError(107011, "请求中的操作系统类型与广告位在优量汇平台的设置不匹配，请检查广告请求中的os字段是否正确。");
                                                                                                                                    case 107030:
                                                                                                                                        return new AdError(107030, "App包名无效,请参考app_bundle_id参数填写逻辑，请检查代码中使用的App包名是否和注册时填写的一致。");
                                                                                                                                    case 107040:
                                                                                                                                        return new AdError(107040, "错误的sdk接口调用，比如申请的广告位是原生自渲染，广告使用场景是信息流，却错误的调用了信息流的相关接口NativeExpressAD，就造成了广告位是自渲染，接口是模板渲染的不匹配情况。");
                                                                                                                                    case 109502:
                                                                                                                                        return new AdError(109502, "请求过于频繁或返回未曝光，且产生的收入低，触发平台出于成本考虑的填充限制。建议减少无效的重复请求，提升广告曝光率。");
                                                                                                                                    case 109512:
                                                                                                                                        return new AdError(109512, "没有找到合适的素材类型或规格，例如非4G和WiFi网络，则无法返回视频广告。建议释放或调整广告位的素材要求。");
                                                                                                                                    case 132005:
                                                                                                                                        return new AdError(132005, "server bidding请求广告时，缺少token参数。");
                                                                                                                                    default:
                                                                                                                                        return new AdError(6000, "发生某些错误或问题，需要根据详细错误码来定位具体问题。详细码：" + i);
                                                                                                                                }
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                        return new AdError(5010, "广告位与调用接口不匹配，导致广告样式校验失败，请检查是否存在广告形态与调用接口不一致的情况。比如，是否用插屏全屏广告位的实例化对象调用了插屏半屏接口loadAD()。");
                                }
                        }
                }
        }
        return adError;
    }
}
