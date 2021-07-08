package com.example.mylearncrawler.staticDate;



/**
 * @author musan
 */


public enum HttpStatus {
    Continue("继续",100), SwitchingProtocols("交换协议",101), OK("成功",200),Created("创建",201),
    Accepted("已接受",202), NonAuthoritativeInformation("非权威信息",203),
    NoContent("没有内容",204),ResetContent("重置内容",205),PartialContent("部分内容",206),
    MultipleChoices("多种选择",300),MovedPermanently("永久移动",301),Found("找到",302),
    SeeOther("参见其他",303),NotModified("未修改",304),UseProxy("使用代理",305),
    Unused("未使用",306),
    TemporaryRedirect("暂时重定向",307),BadRequest("错误的请求",400),Unauthorized("未经授权",401),
    PaymentRequired("付费请求",402),Forbidden("禁止",403),NotFound("没有找到",404),
    MethodNotAllowed("方法不允许",405),NotAcceptable("禁止",406),ProxyAuthenticationRequired("需要代理身份验证",407),
    RequestTimeout("请求超时",408),Conflict("指令冲突",409),Gone("文档永久地离开了指定的位置",410),
    LengthRequired("需要Content-Length头请求",411),PreconditionFailed("前提条件失败",412),
    RequestEntityTooLarge("请求实体太大",413),RequestURITooLong("请求URI太长",414),
    UnsupportedMediaType("不支持的媒体类型",415),RequestedRangeNotSatisfiable("请求的范围不可满足",416),
    ExpectationFailed("期望失败",417),InternalServerError("内部服务器错误",500),
    NotImplemented("未实现",501),BadGateway("错误的网关",502),
    ServiceUnavailable("服务不可用",503), GatewayTimeout("网关超时",504),
    HTTPVersionNotSupported("HTTP版本不支持",505);

    public String getStatusMessage() {
        return statusMessage;
    }

    public Integer getCode() {
        return code;
    }

    private String statusMessage;
        private Integer code;


        private HttpStatus(String statusMessage,Integer code){
            this.statusMessage = statusMessage;
            this.code = code;

        }

}
