package fun.brooke.pureclass;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity {
    //这里是启动界面，包含启动图（0/PureClass/res/Welcome.png）、引导界面、登录注册界面
    //首次使用先加载启动图，然后跳转至引导界面，引导界面最后为登录和注册按钮，分别跳转到不同界面。
    //登录为用户名、密码，提交至服务器(http://pureclass.brooke.fun/login/)验证用户名密码，并将登录状态保存，使用SQLite数据库存储至本地(0/PureClass/sys/config.db)。
    //注册将注册信息转为json发送至服务器(http://pureclass.brooke.fun/signup/)
    //登录成功后跳转至下一Activity()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                setContentView(R.layout.avtivity_login);
            }
        }, 2000);//3秒后执行Runnable中的run方法


    }


}
