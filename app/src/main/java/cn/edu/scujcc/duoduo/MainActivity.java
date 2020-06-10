package cn.edu.scujcc.duoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {

    private static Personal personal;
    private final static String TAG = "DuoDuo";
    private  RelativeLayout btn_message;
    private  RelativeLayout btn_friends;
    private  RelativeLayout btn_myinfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, Personal.class);
//        startActivity(intent);
//        personal.working();
        message();
        friends();
        myinfor();

    }

    public void message(){
        //TODO
//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        btn_myinfor = findViewById(R.id.bottom_myinfor);
        btn_myinfor.setOnClickListener( v ->{
//            startActivity(intent);
        });
    }

    public void friends(){
        //TODO
//        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        btn_friends = findViewById(R.id.bottom_friedns);
        btn_friends.setOnClickListener( v ->{
//            startActivity(intent);
        });
    }

    public void myinfor(){
        Intent intent = new Intent(MainActivity.this, Personal.class);
        btn_myinfor = findViewById(R.id.bottom_myinfor);
        btn_myinfor.setOnClickListener( v -> {
            startActivity(intent);
        });

    }

}