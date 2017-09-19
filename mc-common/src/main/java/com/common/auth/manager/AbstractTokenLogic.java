package com.common.auth.manager;

import com.common.auth.exceptions.AuthException;
import com.common.auth.model.token.TokenItem;
import com.common.auth.utils.AuthUtil;
import com.common.enums.auth.AuthCodeEnum;
import com.common.model.vo.auth.UserAuth;
import com.common.model.vo.auth.UserCache;
import com.common.model.vo.auth.login.UserInfo;
import com.common.utils.redis.RedisUtils;
import com.xuanyan.hmc.midware.assist.utils.string.StringUtil;

/**
 * <b>Description：</b>  <br/>
 * <b>ClassName：</b> com.xuanyan.hmc.common.auth.manager <br/>
 * <b>@author：</b> hui.yan <br/>
 * <b>@date：</b> 2017/4/7 0007 <br/>
 * <b>@version: </b>  <br/>
 */
public abstract class AbstractTokenLogic implements TokenLogic{
    @Override
    public UserCache getUserCacheByUserId(String userId, String source) {
        if (StringUtil.isEmpty(userId) || StringUtil.isEmpty(source)) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        String userCacheKey= AuthUtil.getUserCachedKey(source, userId);
        UserCache userCache = RedisUtils.get(userCacheKey);
        if(userCache!=null){
            userCache.setUserCacheKey(userCacheKey);
        }
        return userCache==null?null:userCache;
    }


    @Override
    public boolean validateRefreshToken(String refreshToken) {
        return false;
    }

    @Override
    public UserAuth getUserAuthByRefreToken(String refreshToken) {
        return null;
    }

    @Override
    public UserCache getUserCacheByUserId(String userId) {
        return null;
    }

    @Override
    public UserCache getUserCacheByToken(String token) {
        if (StringUtil.isEmpty(token) ) {
            throw new AuthException(AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getValue(),
                    AuthCodeEnum.AUTH_REQUEST_PARAM_ERROR.getDesc());
        }
        TokenItem item = AuthUtil.decryptToken(token);

        String userCacheKey =AuthUtil.getUserCachedKey(item.getSource(), item.getUserId());
        UserCache userCache = RedisUtils.get(userCacheKey);
        if(userCache!=null){
            userCache.setUserCacheKey(userCacheKey);
        }
        return userCache;
    }

    @Override
    public UserInfo getUserInfoById(String userId, String source) {
        return null;
    }

    @Override
    public UserInfo getUserInfoByToken(String accessToken) {
        return null;
    }

    @Override
    public void deleteUserCache(String userId) {

    }

    @Override
    public void deleteUserToken(String accessToken, String refreshToken) {

    }
}
