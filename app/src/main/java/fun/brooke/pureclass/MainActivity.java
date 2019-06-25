package fun.brooke.pureclass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends Activity {
    //这里是启动界面，包含启动图（0/PureClass/res/Welcome.png）、引导界面、登录注册界面
    //首次使用先加载启动图，然后跳转至引导界面，引导界面最后为登录和注册按钮，分别跳转到不同界面。
    //登录为用户名、密码，提交至服务器(http://pureclass.brooke.fun/login/)验证用户名密码，并将登录状态保存，使用SQLite数据库存储至本地(0/PureClass/sys/config.db)。
    //注册将注册信息转为json发送至服务器(http://pureclass.brooke.fun/signup/)
    //登录成功后跳转至下一Activity()
    private TextView tv_siup;
    private TextView tv_siin;
    private Button bt_regist;
    private Button bt_login;
    private TextView et_regist_username;
    private TextView et_regist_password1;
    private TextView et_regist_password2;
    private TextView et_login_username;
    private TextView et_login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.welcome);
        Handler handler = new Handler();
        String DATA_BASE_PATH = "/data/data/fun.brooke.pureclass/databases/PC.db";
        File f_ = new File(DATA_BASE_PATH);
        if (!f_.getParentFile().exists()) {
            Log.e("DBService", "文件夹不存在，新建一个");
            f_.getParentFile().mkdirs();
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/fun.brooke.pureclass/databases/PC.db", null);
            String user_table = "create table user(id integer primary key autoincrement,username text,password text)";
            String class_table = "create table class(id integer primary key,userid text,time text,issign int)";
            //执行SQL语句
            db.execSQL(user_table);
            db.execSQL(class_table);
        }
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

        bt_login = (Button) findViewById(R.id.bt_login);
        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_login_username.getText().toString();
                String password = et_login_password.getText().toString();
                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/fun.brooke.pureclass/databases/PC.db", null);
                Cursor cursor = db.query("user", null, "username=" + username + " and password=" + password, null, null, null, null);
                if (cursor.getCount() != 0) {
                    Toast.makeText(MainActivity.this, "登录成功," + username, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Desktop.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "登录失败," + username + "," + password, Toast.LENGTH_LONG).show();
                }
            }
        });


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

        bt_regist = (Button) findViewById(R.id.bt_regist);
        et_regist_username = findViewById(R.id.et_regist_username);
        et_regist_password1 = findViewById(R.id.et_regist_password1);
        et_regist_password2 = findViewById(R.id.et_regist_password2);

        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_regist_username.getText().toString() != null && et_regist_password1.getText().toString().equals(et_regist_password2.getText().toString())) {
                    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("/data/data/fun.brooke.pureclass/databases/PC.db", null);
                    String add_user = "insert into user(username,password) values(" + et_regist_username.getText().toString() + "," + et_regist_password1.getText().toString() + ")";
                    //执行SQL语句
                    db.execSQL(add_user);
                    Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "两次输入密码不一致，请修改!", Toast.LENGTH_LONG).show();
                }

            }
        });


        tv_siin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_login);
                login();
            }
        });
    }

}
