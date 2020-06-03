package cn.edu.scujcc.duoduo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class Personal extends AppCompatActivity {

    private final static String TAG = "DuoDuo";
    private TextInputLayout birthdayInput;
    private Date birthday = new Date();
    private Button updateButton;
    private EditText true_name;
    private EditText name;
    private EditText email;
    private TextView yh_name;
    private TextView yh_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        //同步按钮
        updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener( v -> {
            updateInformation();
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

            });
            birthdayInput.setEndIconOnClickListener(v -> {
                //弹出日历框
                Log.d(TAG, "单击生日图标");
                picker.show(getSupportFragmentManager(), picker.toString());
            });
    }

    private void updateInformation() {
        true_name = findViewById(R.id.truename);
        name = findViewById(R.id.yhname2);
        email = findViewById(R.id.yhEmail2);

    }

}
