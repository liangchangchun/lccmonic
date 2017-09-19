package com.common.enums;

/**
 * 
 * <b>Description：</b> 短信模板枚举类 <br/>
 * <b>ClassName：</b> ErrorCodeEnum <br/>
 * <b>@author：</b> jackyshang <br/>
 * <b>@date：</b> 2016年7月14日 下午3:11:45 <br/>
 * <b>@version: </b>  <br/>
 */
public enum SmsTemplateEnum {
	/**用户预订成功之后，通知专属服务顾问**/
    notice_cs_order_sv_type( "SMS_8335035","用户${name}，保价预订成功，订单号为：${code}。请及时对用户进行跟进！"),
    /**直营订单提醒功能*/
    notice_dcc_dscar_type( "SMS_7145018","您有一条新的${orderName}，请尽快处理！"),
    /**提醒商户自动处理订单数量*/
    notice_web_auto_respondNum_type( "SMS_3715052","${name} 你好，好买车已为你自动处理询价单数量：${num}单，敬请知悉"),
    /**4S店收到报价通知短信v2**/
    respond_type( "SMS_3020088","${fsName}, 您有用户发起了对&quot;${typeName}&quot;的询价，报价链接为: http://t.cn/RAkFhbM 好买车客服：400-879-8779"),
    /**4S店收到报价通知短信v3**/
    respond_type2( "SMS_69875340","${fsName}, 您有新用户发起了对&quot;${typeName}&quot;的询价, 失效时间：${askpRespTime}, 请尽快抢单处理，客服：400-879-8779"),
    /**4S店收到报价通知短信v4**/
    respond_type3( "SMS_88310015","尊敬的商户：${fsName}, 您有新的询价单需要处理，45分钟后（\"${askpRespTime}\"）失效。"),
    /**通知用户查看报价*/
    notice_web_lookrespond_type( "SMS_2425059","${typeName}，点击 http://t.cn/RAkFvUx 手机查看详细报价单！最高现金优惠：${price}元(有效期至${time})。客服电话：400-879-8779"),
    /**通知用户查收报价-版本2*/
    notice_web_lookrespond2_type( "SMS_2440043","${typeName}，点击 http://t.cn/RAkFvUx 手机查看详细报价单！客服电话：400-879-8779"),
    /**通知用户查收报价-版本3*/
    notice_web_lookrespond3_type( "SMS_85450071","${typeName}车型，${count}家4S店已报价，请点击 http://t.cn/R9uaghL 查看详细总价（车源+车价+上牌+税费+保险+杂费…）！(有效期至${time})"),
    /**退款成功之后提醒用户*/
    notice_web_refund_type( "SMS_2220384","您的退款已成功，${money}元已打入您的支付宝账户，请注意查收。400-879-8779"),
    /**订阅通知*/
    subscribe_notice_type("SMS_69905249","降价通知，您订阅的${typeName}车型价格已降至${modelPrice}万元以下，请登录 （m.haomaiche.com/）查看！"),
    /**通知联系客户*/
    notice_contact_user( "SMS_37040066","用户：${userName}（手机号码：${phone}），对您的“${carTypeName}”报价很有兴趣，请尽快联系！"),
    
    
    /**登陆验证码*/
    login_code_type( "SMS_85295026","验证码：（${code}）, 有效时间30分钟，400-879-8779"),
    /**注册验证码*/
    reg_code_type( "SMS_85295026","验证码：（${code}）, 有效时间30分钟，400-879-8779"),
    
    /**语音验证码 */
    voice_code_type( "2",""),
    
    
    
    /** 杭州大师贴膜优惠券 */
    cs_film_hz_ds("SMS_77165077","您申请的大师贴膜券号为：${code}，请凭券号预约门店贴膜服务（材料免费，人工费380元）预约热线：0571-87229093"),
    /** 杭州康得新贴膜优惠券 */
    cs_film_hz_kdx("SMS_77175074","您申请的康得新贴膜券号为：${code}，请凭券号预约门店贴膜服务（材料免费，人工费380元）预约热线：0571-87153082"),
    /** 上海大师贴膜优惠券 */
    cs_film_sh_ds("SMS_71365136","您申请的大师贴膜券号为：${code}，请凭券号预约门店贴膜服务（材料免费人工费380元）预约热线：021-50195610"),
    /** 优惠券短信模板-龙膜 */
    cs_film_sh_lk("SMS_10665623","尊敬的用户您好！您申请的Limooking贴膜优惠券号为：${code}，请凭券号预约上门贴膜服务（材料免费，人工服务费900元）预约热线：4009210612；"),
    /** 优惠券短信模板-普通贴膜 */
    cs_film_sh_lk_normal("SMS_3100634","尊敬的用户您好！您申请的Limooking贴膜优惠券号为：${code}，请凭券号预约上门贴膜服务（材料免费，人工服务费350元）预约热线：4009210612；"),
    
    
    /** 客服回访（未接通） */
    cs_user_no_pick_up("SMS_76475180","您好，感谢通过好买车网（haomaiche.com）向4S店询价，我是您在网站的专属服务顾问${name}，手机${phone}，收到4S店报价后有任何疑问可来电/添加微信咨询！"),
    /** 客服回访 接通，上海杭州通用 */
    cs_user_feedback("SMS_77270008","您好，感谢使用好买车(haomaic.com)，我是您在网站的专属服务顾问${name}，手机${phone}，随时为您提供专业购车咨询。"),
	;
    
    
	String name;
	String content;
	
	
    SmsTemplateEnum(String name, String content) {
        this.name = name;
        this.content = content;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }
    
    
	
	

}
