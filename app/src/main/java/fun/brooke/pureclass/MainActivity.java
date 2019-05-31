package fun.brooke.pureclass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    //这里是启动界面，包含启动图（0/PureClass/res/Welcome.png）、引导界面、登录注册界面
    //首次使用先加载启动图，然后跳转至引导界面，引导界面最后为登录和注册按钮，分别跳转到不同界面。
    //登录为用户名、密码，提交至服务器(http://pureclass.brooke.fun/login/)验证用户名密码，并将登录状态保存，使用SQLite数据库存储至本地(0/PureClass/sys/config.db)。
    //注册将注册信息转为json发送至服务器(http://pureclass.brooke.fun/signup/)
    //登录成功后跳转至下一Activity()
    private TextView tv_siup;
    private TextView tv_siin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                main();
            }
        }, 2000);//3秒后执行Runnable中的run方法


    }

    public void main() {
        setContentView(R.layout.activity_main);

        View bt_siin = (Button) findViewById(R.id.bt_login);
        View bt_siup = (Button) findViewById(R.id.bt_signup);


        bt_siin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        bt_siup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    public void login() {
        setContentView(R.layout.activity_login);
        tv_siup = findViewById(R.id.tv_signup);

        tv_siup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    public void signup() {
        setContentView(R.layout.activity_signup);
        tv_siin = findViewById(R.id.tv_signin);

        tv_siin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_login);
                login();
            }
        });
    }

}
