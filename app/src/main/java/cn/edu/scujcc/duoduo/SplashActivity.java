package cn.edu.scujcc.duoduo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private TextView tv_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //设置此界面为
        // 竖屏
        init();
    }
    private void init() {
        tv_version = findViewById(R.id.tv_version);
        try {
            //获取程序包信息
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            tv_version.setText("version:" + packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            tv_version.setText("version");
        }
        //利用timer让此界面延迟3秒后跳转，timer有一个线程，该线程不断执行task
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //发送intent实现页面跳转，第一个参数为当前页面的context，第二个参数为要跳转的主页
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                //跳转后关闭当前欢迎页面
                SplashActivity.this.finish();
            }
        };
        //调度执行timerTask，第二个参数传入延迟时间（毫秒）
        timer.schedule(timerTask, 5000);
    }
}
