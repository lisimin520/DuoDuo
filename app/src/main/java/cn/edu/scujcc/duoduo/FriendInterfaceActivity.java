package cn.edu.scujcc.duoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FriendInterfaceActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendinterface);
        TextView fistView =findViewById(R.id.friend1);
        TextView secondView =findViewById(R.id.friend2);
        TextView thirdView =findViewById(R.id.friend3);
        TextView fourthView =findViewById(R.id.friend4);
        TextView fifthView =findViewById(R.id.friend5);

        fistView.setOnClickListener(this);
        secondView.setOnClickListener(this);
        thirdView.setOnClickListener(this);
        fourthView.setOnClickListener(this);
        fifthView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.friend1:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            break;
            case R.id.friend2:

                break;
            case R.id.friend3:
                break;
            case R.id.friend4:
                break;
            case R.id.friend5:
                break;
        }

    }
}
