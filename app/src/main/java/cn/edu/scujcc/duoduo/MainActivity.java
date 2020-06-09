package cn.edu.scujcc.duoduo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends AppCompatActivity {
    private NewsRvAdapter rvAdapter;
    private RecyclerView newsRv;
    private NewsLab lab = NewsLab.getInstance();
    private  final  static String TAG = "DuoDuo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.newsRv = findViewById(R.id.news_rv);
        //lambda简化
        rvAdapter = new NewsRvAdapter(this, p -> {
            //跳转到新界面，使用意图Intent
            Intent intent = new Intent(MainActivity.this, ChatAdapter.class);
            //传递用户选中的频道到下一个界面
            //传递位置p得到当前频道channel
            News c = lab.getNews(p);
            intent.putExtra("News", c);
            startActivity(intent);
        });

        initData();

        this.newsRv.setAdapter(rvAdapter);
        this.newsRv.setLayoutManager(new LinearLayoutManager(this));
    }

    //初始化即将显示的数据
    private void initData() {
        //得到网络上的数据后，去更新界面
        Handler handler = new Handler() {
            //快捷键Ctrl O
            @Override
            public void handleMessage(@NonNull Message msg) {
                //若收到来自其他线程的数据，则运行以下代码
//                List<Channel> channels = (List<Channel>) msg.obj;
//                lab.setData(channels);
//                rvAdapter.notifyDataSetChanged();

            }
        };
//        lab.getData(handler);
    }
}
