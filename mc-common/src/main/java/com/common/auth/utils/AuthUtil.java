package com.common.auth.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.common.auth.model.token.TokenItem;
import com.common.auth.utils.sec.TokenProductFactory;
import com.common.config.HConfig;
import com.common.constants.CacheConstant;
import com.common.enums.AppEnum;
import com.common.enums.LoginTypeEnum;
import com.common.enums.auth.AuthLevelEnum;
import com.common.utils.UUIDUitl;
import com.common.utils.redis.RedisUtils;
import com.xuanyan.hmc.midware.logger.Logger;
import com.xuanyan.hmc.midware.logger.LoggerFactory;

/**
 * 
 * <b>Description：</b> 通用工具类(未归类工具类方法) <br/>
 * <b>ClassName：</b> CommonUtil <br/>
 * <b>@author：</b> cheney CHU <br/>
 * <b>@date：</b> 2016年7月25日 下午2:04:06 <br/>
 * <b>@version: </b> <br/>
 */
public class AuthUtil {

    private Logger logger = LoggerFactory.getLogger(AuthUtil.class);
    //加密字段的分割符
    private static final String EncrypterSplit = "#";
    //key的分割符
    private static final String KEY_SPLIT = "_";
    //accessToken标记
    private static final String KEY_ACCESS_TOKEN = "AT";
    //refreshToken标记
    private static final String KEY_REFRESH_TOKEN = "RT";

    /**
     * companyIp:公司ip地址
     */
    private static final String authExcludeIp = HConfig.SYSTEM.getProperty("hmc.auth.exclude.ip","127.0.0.1");

    /**
     * 用户最大请求网站的次数
     */
    private static final int times = HConfig.SYSTEM.getProperty("hmc.auth.loginTimes_max", 300);

    /**
     * 获取手机验证码缓存Key
     * 
     * @param source
     * @param userId
     * @return String <br/>
     */
    public static String getSMSCodeCachedKey(String source,String userPhone) {
        StringBuffer sb =new StringBuffer();
        sb.append(CacheConstant.MEMBER_SMSCODE_KEY);
        sb.append(userPhone + KEY_SPLIT);
        sb.append(source);
        return sb.toString();
    }
    
    public static String getSMSCodeTimesCachedKey(String userPhone) {
        StringBuffer sb =new StringBuffer();
        sb.append(CacheConstant.MEMBER_SMSCODE_TIMES_KEY);
        sb.append(userPhone + KEY_SPLIT);
        return sb.toString();
    }
    
    /**
     * 生成手机串码，用于验证用户是否到店
     * 
     * @return
     */
    public static String getValidateCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String str = "V" + sdf.format(new Date());
        Random ran = new Random();
        int m = 0;
        while (m < 5) {
            m++;
            str += ran.nextInt(10);
        }
        return str;
    }

    /**
     * 获取缓存key getUserCachedKey <br/>
     * 
     * @param source
     * @param userId
     * @return String <br/>
     */
    public static String getUserCachedKey(String source, String userId) {
        return getUserCachedKeyPrefix(source) + userId;
    }
    /**
     * 获取微信缓存 key
     * @param source
     * @param openID   微信端唯一标识
     * @return
     */
    public static String getWeixinUserCachedKey(String openID) {
        return CacheConstant.P_AUTH_WEIXIN_USER + openID;
    }

    /**
     * 获取缓存key getUserCachedKey <br/>
     * 
     * @param source
     * @param userId
     * @return String <br/>
     */
    public static String getUserCachedKeyPrefix(String source) {
        StringBuffer sb = new StringBuffer();
        if (AppEnum.isPortal(source)) {// 官网
            sb.append(CacheConstant.P_AUTH_MEMBER_USER);
        } else if (AppEnum.isMPS(source)) {// 商户
            sb.append(CacheConstant.P_AUTH_MERCHANT_USER);
        } else if (AppEnum.isOMS(source)) {// 运营
            sb.append(CacheConstant.P_AUTH_OPERATORUSER);
        }
        return sb.toString();
    }
    

    /**
     * 获取用户类型
     * 
     * @return String <br/>
     */
    public static String getUserType(String source) {
        StringBuffer sb = new StringBuffer();
        if (AppEnum.isPortal(source)) {// 官网
            sb.append(LoginTypeEnum.MEMBER.getValue());
        } else if (AppEnum.isMPS(source)) {// 商户
            sb.append(LoginTypeEnum.MERCHANT.getValue());
        } else if (AppEnum.isOMS(source)) {// 运营
            sb.append(LoginTypeEnum.OPERATOR.getValue());
        }
        return sb.toString();
    }

    /**
     * 生成access token <br/>
     * createAccessToken <br/>
     * 
     * @param userId
     * @param source
     * @param ip
     * @param appKey
     * @return String <br/>
     */
    public static String genAccessToken(String userId, String source) {

        StringBuffer sb = new StringBuffer();
        sb.append(userId + EncrypterSplit);
        sb.append(source + EncrypterSplit);
        sb.append(KEY_ACCESS_TOKEN + EncrypterSplit);
        sb.append(UUIDUitl.generateAllString(8));

        return TokenProductFactory.getInstallBase64().encrypt(sb.toString());
    }
    /**
     * 生成refresh token <br/> 
     * genRefreshToken <br/> 
     * @param userId
     * @param source
     * @return  String <br/>
     */
    public static String genRefreshToken(String userId, String source) {
        StringBuffer sb = new StringBuffer();
        sb.append(userId + EncrypterSplit);
        sb.append(source + EncrypterSplit);
        sb.append(KEY_REFRESH_TOKEN + EncrypterSplit);
        sb.append(UUIDUitl.generateAllString(8));
        return TokenProductFactory.getInstallBase64().encrypt(sb.toString());
    }

    /**
     * 解密token <br/>
     * decryptToken <br/>
     * 
     * @param token
     * @return Map<String,Object> <br/>
     */
    public static TokenItem decryptToken(String token) {
        String[] tokenItemArray = TokenProductFactory.getInstallBase64().decrypt(token).split(EncrypterSplit);
        int tokenArrLength=tokenItemArray.length;
        TokenItem tokenItem = new TokenItem();
        String userId=tokenItemArray[0];
        tokenItem.setUserId(userId);
        String source=tokenItemArray[1];
        tokenItem.setSource(source);
        if(tokenArrLength>2){
            String regedit=tokenItemArray[2];
            tokenItem.setRegedit(regedit);
        }
        return tokenItem;
    }

    /**
     * 短信验证码
     * 
     * @return
     */
    public static String getSmsCode() {
        StringBuffer sb = new StringBuffer();
        Random ran = new Random();
        int m = 0;
        while (m < 4) {
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
             long sumTimes=Long.valueOf(tempTimes)+1;
             if(sumTimes>times){
                 return false;
             }else{
                 RedisUtils.save(ip, sumTimes+"");
                 return true;
             }
         }else{
             RedisUtils.save(ip, "1", AuthLevelEnum.TOKEN_MIDDLE.getValue());
         }
         return true;
    }

    public static String genMPSAccessToken(String userId,String source,String regedit) {
        StringBuffer sb = new StringBuffer();
        sb.append(userId + EncrypterSplit);
        sb.append(source + EncrypterSplit);
        sb.append(regedit + EncrypterSplit);
        sb.append(KEY_ACCESS_TOKEN + EncrypterSplit);
        sb.append(UUIDUitl.generateAllString(8));

        return TokenProductFactory.getInstallBase64().encrypt(sb.toString());
    }
}
