package com.common.auth.manager;

/**
 * <b>Description：</b> 手机验证码逻辑 <br/>
 * <b>ClassName：</b> UserAuthLogic <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2016年7月27日 下午6:45:13 <br/>
 * <b>@version: </b>  <br/>
 */
public interface SMSLogic {
    /**
     * 获取手机验证码
     * getSMSCode <br/> 
     * @param source
     * @param userPhone
     * @return  String <br/>
     */
   public String getSMSCode(String source,String userPhone);
   /**
    * 保存手机验证码
    * @param source
    * @param userPhone
    * @param smscode
    * @return  String <br/>
    */
   public String saveSMSCode(String source,String userPhone,String codeType); 
   /**
    * 验证手机验证码
    * validateSMSCode <br/> 
    * @param source
    * @param userPhone
    * @param smscode
    * @return  String <br/>
    */
   public boolean validateSMSCode(String source,String userPhone,String smscode);
   /**
    * 删除手机验证码
    * delSMSCode <br/>
    * @param source
    * @param userPhone
    * @param smscode
    * @return  boolean <br/>
    */
   boolean delSMSCode(String source, String userPhone, String smscode);
}
