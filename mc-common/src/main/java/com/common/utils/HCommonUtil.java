package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.common.config.HConfig;
import com.common.enums.auth.AuthLevelEnum;
import com.common.model.vo.auth.DecryptToken;
import com.common.utils.redis.RedisUtils;
import com.common.utils.rsa.Encrypter;
import com.common.utils.string.StringUtil;

/**
 * 
 * <b>Description：</b> 通用工具类(未归类工具类方法)  <br/>
 * <b>ClassName：</b> CommonUtil <br/>
 * <b>@author：</b> cheney CHU <br/>
 * <b>@date：</b> 2016年7月25日 下午2:04:06 <br/>
 * <b>@version: </b>  <br/>
 */
public class HCommonUtil {
    
	//private Logger logger =LoggerFactory.getLogger(HCommonUtil.class);
	
	private final static String EncrypterSplit="#";
	
	/**
	 * companyIp:公司ip地址
	 */
	private static final String authExcludeIp=HConfig.SYSTEM.getProperty("hmc.auth.exclude.ip","127.0.0.1");
	
	/**
	 * 用户最大请求网站的次数
	 */
	private static final int accessTimesMax=HConfig.SYSTEM.getProperty("hmc.auth.accessTimes_max",2000);
	
	private static final String url=HConfig.CONTEXT.getProperty("url_prefix","10.0.0.100:20010");
	
    /**
     * 
     * 生成一个全球唯一的标志，用来作为数据库唯一主键 <br/> 
     * getUUIDString <br/> 
     * @return  String <br/>
     */
    public static String getUUIDString(){
        String str = UUID.randomUUID().toString(); 
        str = str.replaceAll("-", "");
        return str;
    }
	
	/**
	 * 生成手机串码，用于验证用户是否到店
	 * @return
	 */
	public static String getValidateCode(){
		SimpleDateFormat sdf=new SimpleDateFormat("MMdd");
		String str="V"+sdf.format(new Date());
		Random ran=new Random();
		int m=0;
		while(m<5){
			m++;
			str+= ran.nextInt(10);
		}
		return str;
	}
	
	/**     
	 * 生成access token <br/> 
	 * createAccessToken <br/> 
	 * @param userId
	 * @param source
	 * @param ip
	 * @param appKey
	 * @return  String <br/>   
	 */
	public static String createAccessToken(String userId,String userType,String source,String ip,String appKey){
		String token=Encrypter.encrypt(userId+EncrypterSplit+userType+EncrypterSplit+source+EncrypterSplit+ip
				+EncrypterSplit+appKey+EncrypterSplit+HCommonUtil.getUUIDString());
		return token;
	}
	
	/**     
	 * 生成refresh token <br/> 
	 * createRefreshToken <br/> 
	 * @param userId
	 * @param userType
	 * @param source
	 * @param ip
	 * @param appKey
	 * @return  String <br/>   
	 */
	public static String createRefreshToken(String userId,String userType,String source,String ip,String appKey,long timeStamp){
		String token=Encrypter.encrypt(userId+EncrypterSplit+userType+EncrypterSplit+source+EncrypterSplit+ip
				+EncrypterSplit+appKey+EncrypterSplit+timeStamp+EncrypterSplit+HCommonUtil.getUUIDString());
		return token;
	}
	
    /**     
     * 解密token <br/> 
     * decryptToken <br/> 
     * @param token
     * @return  Map<String,Object> <br/>   
     */
    public static DecryptToken decryptToken(String token){
    	String[] decrypt=Encrypter.decrypt(token).split(EncrypterSplit);
    	DecryptToken decryptToken=new DecryptToken();
    	decryptToken.setUserId(decrypt[0]);
    	decryptToken.setUserType(decrypt[1]);
    	decryptToken.setSource(decrypt[2]);
    	decryptToken.setIp(decrypt[3]);
    	decryptToken.setApplicationPrefix(decrypt[4]);
    	return decryptToken;
    }
    
    /**
	 * 短信验证码
	 * @return
	 */
	public static String getSmsCode(){
		StringBuffer sb=new StringBuffer();
		Random ran=new Random();
		int m=0;
		while(m<4){
			m++;
			sb.append(ran.nextInt(10));
		}
		return sb.toString();
	}
	
	/**     
	 * ip请求次数 <br/> 
	 * validateRequestTimes <br/> 
	 * @param ip
	 * @return  boolean <br/>   
	 */
	public static boolean validateRequestTimes(String ip){
		 if(authExcludeIp.contains(ip)){
			 return true;
		 }
		 if(RedisUtils.get(ip)!=null){
			 String tempTimes=RedisUtils.get(ip);
			 if(Integer.valueOf(tempTimes)>accessTimesMax){
				 return false;
			 }else{
				// RedisUtils.incr(ip);
				 return true;
			 }
		 }else{
			 RedisUtils.save(ip, "1", AuthLevelEnum.TOKEN_MIDDLE.getValue());
		 }
		 return true;
	}
	
	
	/**     
	 * 添加url前缀 <br/> 
	 * addUrlPrefix <br/> 
	 * @param path
	 * @return  String <br/>   
	 */
	public static String addUrlPrefix(String path) {
		if(StringUtil.isBlank(path)){
			return path;
		}
		
		if (!path.startsWith("http://")&&!path.startsWith("https://")) {
			if (!StringUtil.isBlank(url)&& url.startsWith("http")) {
				return url + "/" + path;
			} else {
				return "http://" + url + "/" + path;
			}
		}else{
			return path;
		}
	}
	/**     
	 * 加密手机号码 <br/> 
	 * hideUserPhone <br/> 
	 * @param userPhone
	 * @return  String <br/>   
	 */
	public static String hideUserPhone(Object userPhone){
		String tempUserPhone=userPhone.toString();
		tempUserPhone=tempUserPhone.substring(0, 3)+"****"+tempUserPhone.substring(7);
		return tempUserPhone;
	}
	
}
