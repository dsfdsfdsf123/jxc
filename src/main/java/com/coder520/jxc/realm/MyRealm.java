package com.coder520.jxc.realm;

import com.coder520.jxc.entity.User;
import com.coder520.jxc.repository.UserRepository;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author liugang
 * @create 2018/12/5 0:43
 **/
public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserRepository userRepository;

    /**授权**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**认证**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        User user = userRepository.findByUserName(userName);
        if (user!=null){
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"SOME_THING");
            return authcInfo;
        }
        return null;
    }
}
