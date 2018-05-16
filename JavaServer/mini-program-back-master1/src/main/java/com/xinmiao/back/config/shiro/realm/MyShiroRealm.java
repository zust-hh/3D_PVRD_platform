package com.xinmiao.back.config.shiro.realm;

import com.xinmiao.back.domain.Permission;
import com.xinmiao.back.domain.User;
import com.xinmiao.back.mapper.PermissionMapper;
import com.xinmiao.back.mapper.UserMapper;
import com.xinmiao.back.util.PasswordHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;


@Slf4j
public abstract class MyShiroRealm extends AuthorizingRealm {
    @Resource
    UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    private Integer supportedLoginType;

    public Integer getSupportedLoginType() {
        return supportedLoginType;
    }

    public void setSupportedLoginType(Integer supportedLoginType) {
        this.supportedLoginType = supportedLoginType;
    }
    //    @Autowired
//    private CacheManager cacheManager;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("登录成功之后开始授权");
        User user = (User) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        log.debug("" + user.getUserId());
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("userid",user.getUserId());
        List<Permission> permissionList = permissionMapper.loadUserPermissions(user.getUserId());
//      权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Permission permission : permissionList) {
            info.addStringPermission(permission.getPermissionUrl());
        }

        return info;
    }

    //认证
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        //获取用户的输入的账号.
//        //log.debug("登录时候的认证～～～"+"username = " + String.valueOf(token.getPrincipal()) + ", pass = " + new String((char[])token.getCredentials()));
//        String username = (String)token.getPrincipal();
//        String password = new String((char[])token.getCredentials());
//
//
//        User user = userMapper.selectByTelephone(username);
//
//        if(user==null) throw new UnknownAccountException();
//        if (true==user.getIsLocked()) {
//            throw new LockedAccountException(); // 帐号锁定
//        }
//
//        SimpleAuthenticationInfo authenticationInfo;
//        String encryptPassword = PasswordHelper.encryptPassword(password,username);
//
//        //如果密码等于token
//        if(encryptPassword.equals(user.getToken())){
//            authenticationInfo = new SimpleAuthenticationInfo(
//                    user, //用户
//                    user.getToken(), //使用数据库中的token作为登录凭据
//                    ByteSource.Util.bytes(username),
//                    getName()  //realm name
//            );
//        }else{
//            authenticationInfo = new SimpleAuthenticationInfo(
//                    user, //用户
//                    user.getPasswd(), //使用密码作为登录凭据
//                    ByteSource.Util.bytes(username),
//                    getName()  //realm name
//            );
//        }
//
//        //如果登录成功,清楚上一次的权限缓存，将上次登录的session设置属性kickout，防止上次会话再次使用，实现踢出登录
//        if(encryptPassword.equals(user.getToken()) || encryptPassword.equals(user.getPasswd())){
//            // 当验证都通过后，把用户信息放在session里,更新数据库的token
//            Session session = SecurityUtils.getSubject().getSession();
//
//            RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
//            CacheManager cacheManager = this.getCacheManager();
//
//            List<Integer> tmp = new ArrayList<Integer>();
//            tmp.add(user.getUserId());
//            clearUserAuthByUserId(tmp);//清除之前的授权信息
//            //==========================
//            Cache<String,String> cache = cacheManager.getCache("shiro-kickout-session");
//            String kickSessionId = cache.get(user.getTelephoneNumber());//获得要踢出的sessionId;
//            Session kickoutSession = securityManager.getSession(new DefaultSessionKey(kickSessionId));
//            if(kickoutSession != null) {
//                //设置会话的kickout属性表示踢出了
//                kickoutSession.setAttribute("kickout", true);
//            }
//            cache.put(user.getTelephoneNumber(),session.getId().toString());//设置新的sessionId
//            //==========================
//
//            user.setToken(PasswordHelper.encryptPassword(session.getId().toString(),user.getTelephoneNumber()));
//            System.out.println("update table user"+user.getUserId()+" token" + user.getToken());
//
//            userMapper.updateByPrimaryKeySelective(user);//更新数据库token
//            session.setAttribute("userSession", user);
//            session.setAttribute("userSessionId", user.getUserId());
//        }
//
//        return authenticationInfo;
//    }


    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     *
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Integer> userIds) {
        if (null == userIds || userIds.size() == 0) return;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof User) {
                    User user = (User) obj;
                    System.out.println("user:" + user);
                    //比较用户ID，符合即加入集合
                    if (null != user && userIds.contains(user.getUserId())) {
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }


    //登录成功后清除前一个登录的信息，从而实现一个帐号只能在一个设备上登录
    protected void updateUserAndClearOldUserCache(User user) {
        // 当验证都通过后，把用户信息放在session里,更新数据库的token

        Session session = SecurityUtils.getSubject().getSession();

        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        CacheManager cacheManager = this.getCacheManager();

        //清空之前的授权信息
        List<Integer> tmp = new ArrayList<Integer>();
        tmp.add(user.getUserId());
        clearUserAuthByUserId(tmp);//清除之前的授权信息

        /*
        //清除之前的session信息（在之前的session中设置kickout信息）
        Cache<String, String> cache = cacheManager.getCache("shiro-kickout-session");
        String kickSessionId = cache.get(user.getUserWx());//获得要踢出的sessionId;
        Session kickoutSession = securityManager.getSession(new DefaultSessionKey(kickSessionId));
        if (kickoutSession != null) {
            //设置会话的kickout属性表示踢出了
            kickoutSession.setAttribute("kickout", true);
        }
        cache.put(user.getUserWx(), session.getId().toString());//设置新的sessionId
        */

        //更新数据库中user的token
            user.setToken(session.getId().toString());
            userMapper.updateTokenByWx(user.getUserWx(),session.getId().toString());//更新数据库token

        String newToken = PasswordHelper.encryptPassword(session.getId().toString(), user.getUserWx());
        System.out.println("newToken = " + newToken);
        userMapper.updateTokenByWx(newToken, user.getUserWx());

        System.out.println("update table user" + user.getUserId() + " token" + user.getToken());
        //将用户信息存入session缓存
        session.setAttribute("userSession", user);
//        session.setAttribute("userSessionId", user.getUserId());

    }

    public boolean supports(AuthenticationToken token) {
        if (token instanceof UsernamePasswordLoginTypeToken) {
            UsernamePasswordLoginTypeToken usernamePasswordLoginTypeToken = (UsernamePasswordLoginTypeToken) token;
            return getSupportedLoginType().equals(usernamePasswordLoginTypeToken.getLoginType());
        }
        return false;
    }

}
