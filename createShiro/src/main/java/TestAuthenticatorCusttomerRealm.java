
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * 测试自定义的Realm
 */
public class TestAuthenticatorCusttomerRealm {

    public static void main(String[] args) {
        //1.创建安全管理对象 securityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        //2.给安全管理器设置realm（设置为自定义realm获取认证数据）
        defaultSecurityManager.setRealm(new CustomerRealm());
        //IniRealm realm = new IniRealm("classpath:shiro.ini");

        //3.给安装工具类中设置默认安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //4.获取主体对象subject
        Subject subject = SecurityUtils.getSubject();

        //5.创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("wangye", "wang");
        try {
            subject.login(token);//用户登录
            System.out.println(subject.isAuthenticated());
            System.out.println("登录成功~~");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!!");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误!!!");
        }

    }
}
