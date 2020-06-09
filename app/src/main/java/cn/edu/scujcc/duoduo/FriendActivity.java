package cn.edu.scujcc.duoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FriendActivity extends AppCompatActivity {
    private TextView message;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        this.message= findViewById(R.id.message);
        list = new List(FriendActivity.this, p -> {
            //跳转到新界面，使用意图Intent
            Intent intent = new Intent(FriendActivity.this, MainActivity.class);
            List c = lab.getChannel(p);
            intent.putExtra("List",c);
            startActivity(intent);
        });


    }
    }
