package com.common.auth.manager;

import com.common.auth.exceptions.AuthException;
import com.common.auth.utils.AuthUtil;
import com.common.config.HConfig;
import com.common.enums.SmsTemplateEnum;
import com.common.enums.auth.AuthCodeEnum;
import com.common.enums.auth.AuthLevelEnum;
import com.common.utils.redis.RedisUtils;
import com.common.utils.validate.ValidateUtils;
import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;

/**
 * <b>Description：</b>手机验证码逻辑实现 <br/>
 * <b>ClassName：</b> TokenLogicImpl <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午5:37:06 <br/>
 * <b>@version: </b> <br/>
 */
public class SMSLogicImpl implements SMSLogic {

    public static SMSLogic smsLogic = new SMSLogicImpl();
    //同一手机30分钟内点击获取验证码，验证码的内容相同，同时获取到的验证码有效期为30分钟
   // private static final int SMSCODE_EXPIRE_TIME_SECOND =1*24*3600;
    private static final int SMSCODE_EXPIRE_TIME_SECOND =30*60;
    private static final String authExcludePhone=HConfig.SYSTEM.getProperty("hmc.auth.exclude.phone","13512341234");
	
	private static final int smsCodeTimesMax=HConfig.SYSTEM.getProperty("hmc.auth.smsCodeTimes_max",1);
	
    private SMSLogicImpl() {
    }

    public static SMSLogic getInstance() {
        return smsLogic;
    }

    @Override
    public String getSMSCode(String source,String userPhone) {
        if (StringUtil.isEmpty(userPhone) || StringUtil.isEmpty(source)  ) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        return  RedisUtils.get(AuthUtil.getSMSCodeCachedKey(userPhone, source));
    }

    @Override
    public String saveSMSCode(String source,String userPhone,String codeType) {
        if (StringUtil.isEmpty(userPhone) || StringUtil.isEmpty(source)  ) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        if(!ValidateUtils.isMobile(userPhone)){
        	  throw new AuthException(AuthCodeEnum.PHONE_REGEX_ERROR.getValue(),
                      AuthCodeEnum.PHONE_REGEX_ERROR.getDesc());
        }
        if(!validateSmsCodeTimes(userPhone,codeType)){
        	 throw new AuthException(AuthCodeEnum.USER_FREQUENT_SMSCODE.getValue(),
                     AuthCodeEnum.USER_FREQUENT_SMSCODE.getDesc());
        }

        String smsCodeCacheKey = AuthUtil.getSMSCodeCachedKey(userPhone, source);
        String smsCodeCached = RedisUtils.get(smsCodeCacheKey);
        if(StringUtil.isEmpty(smsCodeCached)){
            smsCodeCached=AuthUtil.getSmsCode();
        }
        if(authExcludePhone.contains(userPhone)){
            smsCodeCached="123456";
        }
        RedisUtils.save(smsCodeCacheKey, smsCodeCached,SMSCODE_EXPIRE_TIME_SECOND );
        return smsCodeCached;
    }

    @Override
    public boolean validateSMSCode(String source, String userPhone, String smscode) {
        if (StringUtil.isEmpty(userPhone) || StringUtil.isEmpty(source) || StringUtil.isEmpty(smscode)) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        String smsCodeCacheKey = AuthUtil.getSMSCodeCachedKey(userPhone, source);
        String smsCodeCached = RedisUtils.get(smsCodeCacheKey);

        if (!smscode.equals(smsCodeCached)) {
            return false;
        }

        return true;
    }
    @Override
    public boolean delSMSCode(String source, String userPhone, String smscode){
        if (StringUtil.isEmpty(userPhone) || StringUtil.isEmpty(source) || StringUtil.isEmpty(smscode)) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        String smsCodeCacheKey = AuthUtil.getSMSCodeCachedKey(userPhone, source);
        return RedisUtils.del(smsCodeCacheKey);
    }

    /**     
	 * 验证手机获取短信次数是否超限 <br/> 
	 * validateSmsCodeTimes <br/> 
	 * @param phone
	 * @return  boolean <br/>   
	 */
	public static boolean validateSmsCodeTimes(String phone,String codeType){
		 if(authExcludePhone.contains(phone)){
			 return true;
		 }
		 if (SmsTemplateEnum.voice_code_type.getName().equals(codeType)) {
			 if(RedisUtils.exists(AuthUtil.getSMSCodeTimesCachedKey(phone + codeType))){
				 return false;
			 } else {
				 RedisUtils.save(AuthUtil.getSMSCodeTimesCachedKey(phone + codeType), "1", AuthLevelEnum.SMSCODE_HIGH.getValue());
				 
			 }
		 } else {
			 //非语音
			 if(RedisUtils.exists(AuthUtil.getSMSCodeTimesCachedKey(phone))){
				 /*String tempTimes=RedisUtils.get(AuthUtil.getSMSCodeTimesCachedKey(phone));
				 long sumTimes=Long.valueOf(tempTimes);
	             sumTimes++;
				 if(sumTimes>smsCodeTimesMax){
					 return false;
				 }else{
	                 RedisUtils.save(AuthUtil.getSMSCodeTimesCachedKey(phone), String.valueOf(sumTimes), AuthLevelEnum.SMSCODE_HIGH.getValue());
					 return true;
				 }*/
	             return false;
			 } else {
				 RedisUtils.save(AuthUtil.getSMSCodeTimesCachedKey(phone), "1", AuthLevelEnum.SMSCODE_HIGH.getValue());
			 }
		 }
		 
		 return true;
	}
  
}
