package fun.brooke.pureclass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Desktop extends Activity {

    private TextView huanyingtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        huanyingtv = findViewById(R.id.huanying);
        Intent it = getIntent();
        String username = it.getStringExtra("username");
        huanyingtv.setText("你好，" + username);
    }
}
