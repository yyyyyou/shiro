import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import realm.CustomerMD5Realm;

import java.util.Arrays;

public class TestAuthenticatorMD5CusttomerRealm {
    public static void main(String[] args) {

        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        CustomerMD5Realm realm = new CustomerMD5Realm();
        //设置realm使用hash凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //使用的算法
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列/迭代的次数
        //   credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);

        securityManager.setRealm(realm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming", "123");

        try {
            subject.login(token);
            System.out.println(subject.isAuthenticated());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!!!");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //授权
        if (subject.isAuthenticated()) {

            //基于角色权限控制
            System.out.println(subject.hasRole("admin"));
            //基于多角色的权限控制
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "user")));//true
            System.out.println(subject.hasAllRoles(Arrays.asList("admin", "manager")));//false
            //是否具有其中一个角色
            boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "user", "manager"));
            for (boolean aBoolean : booleans) {
                System.out.println(aBoolean);
            }

            System.out.println("====这是一个分隔符====");

            //基于权限字符串的访问控制  资源标识符：操作：资源类型
            //用户具有的权限 user:*:01  prodect:*
            System.out.println("权限:" + subject.isPermitted("user:update:01"));
            System.out.println("权限:" + subject.isPermitted("product:update:02"));

            //分别具有哪些权限
            boolean[] permitted = subject.isPermitted("user:*:01", "user:update:02");
            for (boolean b : permitted) {
                System.out.println(b);
            }

            //同时具有哪些权限
            boolean permittedAll = subject.isPermittedAll("product:*:01", "product:update:03");
            System.out.println(permittedAll);

        }
    }
}