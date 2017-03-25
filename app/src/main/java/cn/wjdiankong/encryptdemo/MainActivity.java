package cn.wjdiankong.encryptdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

     static {
        System.loadLibrary("encrypt");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = isEquals("123456");
                Log.i("jw", "res:" + res);
            }
        });

    }

    private native boolean isEquals(String str);

}
