package cn.edu.scujcc.duoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class Personal extends AppCompatActivity{

    private final static String TAG = "DuoDuo";
    private TextInputLayout birthdayInput;
    private Date birthday = new Date();
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"加载个人信息ing");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        working();
    }

    public void working() {
        //同步按钮
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener( v -> {
            updateInformation();
            Toast.makeText(Personal.this, "同步更新ing", Toast.LENGTH_SHORT).show();
        });

        //生日获取
        birthdayInput =findViewById(R.id.yhbirthday);
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        //告诉builder想要的
        builder.setTitleText(R.string.birthday_title);
        MaterialDatePicker<Long> picker = builder.build();
        picker.addOnPositiveButtonClickListener(s -> {
            Log.d(TAG, "生日是：" + picker.getHeaderText());
            birthday.setTime(s);
            birthdayInput.getEditText().setText(picker.getHeaderText());

            //获取生日
            TextInputLayout birthday2 = findViewById(R.id.yhbirthday);
            TextView birthday = findViewById(R.id.birthday);
            birthday.setText(picker.getHeaderText());
        });
        birthdayInput.setEndIconOnClickListener(v -> {
            //弹出日历框
            Log.d(TAG, "单击生日图标");
            picker.show(getSupportFragmentManager(), picker.toString());
        });
    }

    public void updateInformation() {
//        User user = new User();
        //获取输入内容
        //获取真实姓名
        EditText truename2 = findViewById(R.id.truename2);
        Log.d(TAG,"用户姓名是：" + truename2.getText().toString());
        //获取输入框昵称
        EditText nickname2 = findViewById(R.id.nickname2);
        Log.d(TAG, "该用户昵称是：" + nickname2.getText().toString());
        //获取输入框邮箱
        EditText email2 = findViewById(R.id.email2);
        Log.d(TAG, "用户电子邮箱是：" + email2.getText().toString());

        //传递输入值给上面
        //获取姓名
        TextView truename = findViewById(R.id.truename);
        truename.setText(truename2.getText().toString());
        //获取上面昵称
        TextView nickname = findViewById(R.id.nickname);
        nickname.setText(nickname2.getText().toString());
        //获取上面电子邮箱
        TextView email = findViewById(R.id.email);
        email.setText(email2.getText().toString());
    }

}
