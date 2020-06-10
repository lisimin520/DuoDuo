package cn.edu.scujcc.duoduo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    Button to_login; //返回按钮
//    private TextView tv_main_title;//标题
//    private TextView tv_back;//返回按钮
//    private Button register_button;//注册按钮
//    private EditText r_password1, r_password2;
//    private String userName, psw, pswAgain;
    private final static String TAG = "DuoDuo";
    private TextInputLayout birthdayInput;
    private Button registerButton;
    private Date birthday = new Date();
    private UserLab lab = UserLab.getInstance();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg != null) {
                switch (msg.what) {
                    case UserLab.USER_REGISTER_SUCCESS:
                        Toast.makeText(RegisterActivity.this, "注册成功！欢迎你的加入！", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case UserLab.USER_REGISTER_FAIL:
                        Toast.makeText(RegisterActivity.this, "注册失败！请稍候再试。", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        birthdayInput = findViewById(R.id.r_birthday);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            register();
        });

        // new Builder()
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        //告诉builder我们想要的效果
        builder.setTitleText(R.string.birthday_title);
        MaterialDatePicker<Long> picker = builder.build();
        //操作日历

        //日历点击“确定”后的处理
        picker.addOnPositiveButtonClickListener(s -> {
            Log.d(TAG, "日历的结果是：" + s);
            Log.d(TAG, "标题是：" + picker.getHeaderText());
            birthday.setTime(s);
            birthdayInput.getEditText().setText(picker.getHeaderText());

        });

        birthdayInput.setEndIconOnClickListener(v -> {
            //弹出日历选择框
            Log.d(TAG, "生日图标被点击了！");
            picker.show(getSupportFragmentManager(), picker.toString());
        });
    }

    private void register() {
        User u = new User();
        boolean error = false;
        String errorMessage;
        //获得用户名
        TextInputLayout usernameInput = findViewById(R.id.r_username);
        Editable username = usernameInput.getEditText().getText();
        u.setUsername(username != null ? username.toString() : "");

        //检查密码是否一致
        TextInputLayout passwordInput1 = findViewById(R.id.r_password1);
        TextInputLayout passwordInput2 = findViewById(R.id.r_password2);
        Editable password1 = passwordInput1.getEditText().getText();
        Editable password2 = passwordInput2.getEditText().getText();
        if (password1 != null && password2 != null) {
            if (!password2.toString().equals(password1.toString())) { //两次密码不相同
                error = true;
                errorMessage = "两次密码不相同";
            } else {
                u.setPassword(password1.toString());
            }
        }
        //获得手机号
        TextInputLayout phoneInput = findViewById(R.id.r_phone);
        Editable phone = phoneInput.getEditText().getText();
        u.setPhone(phone != null ? phone.toString() : "");

        //获得生日
        u.setBirthday(birthday);

        //把u发送到服务器
        lab.register(u, handler);

        //返回按钮
        to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到登录界面
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


//        private void init () {
//            //从main_title_bar.xml 页面布局中获取对应的UI控件
//            tv_main_title.setText("注册");
//            //从activity_register.xml 页面中获取对应的UI控件
//            register_button = findViewById(R.id.register_button);
//            r_password1 = findViewById(R.id.r_password1);
//            r_password2 = findViewById(R.id.r_password2);
//            tv_back.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //返回键
//                    RegisterActivity.this.finish();
//                }
//            });
//            //注册按钮
//            register_button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //获取输入在相应控件中的字符串
//                    getEditString();
//                    //判断输入框内容
//                    if (TextUtils.isEmpty(userName)) {
//                        Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else if (TextUtils.isEmpty(psw)) {
//                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else if (TextUtils.isEmpty(pswAgain)) {
//                        Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else if (!psw.equals(pswAgain)) {
//                        Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
//                        return;
//                        /**
//                         *从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
//                         */
//                    } else if (isExistUserName(userName)) {
//                        Toast.makeText(RegisterActivity.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
//                        return;
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                        //把账号、密码和账号标识保存到sp里面
//                        /**
//                         * 保存账号和密码到SharedPreferences中
//                         */
//                        saveRegisterInfo(userName, psw);
//                        //注册成功后把账号传递到LoginActivity.java中
//                        // 返回值到loginActivity显示
//                        Intent data = new Intent();
//                        data.putExtra("userName", userName);
//                        setResult(RESULT_OK, data);
//                        //RESULT_OK为Activity系统常量，状态码为-1，
//                        // 表示此页面下的内容操作成功将data返回到上一页面，如果是用back返回过去的则不存在用setResult传递data值
//                        RegisterActivity.this.finish();
//                    }
//                }
//            });
//        }
//    }
//
//    /**
//     * 获取控件中的字符串
//     */
//    private void getEditString(){
//        psw=r_password1.getText().toString().trim();
//        pswAgain=r_password2.getText().toString().trim();
//    }
//
//    /**
//     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
//     */
//    private boolean isExistUserName(String userName) {
//        boolean has_userName=false;
//        //mode_private SharedPreferences sp = getSharedPreferences( );
//        // "loginInfo", MODE_PRIVATE
//        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
//        //获取密码
//        String spPsw=sp.getString(userName, "");//传入用户名获取密码
//        //如果密码不为空则确实保存过这个用户名
//        if(!TextUtils.isEmpty(spPsw)) {
//            has_userName=true;
//        }
//        return has_userName;
//    }
//    /**
//     * 保存账号和密码到SharedPreferences中SharedPreferences
//     */
//    private void saveRegisterInfo(String userName,String psw){
//        String md5Psw = MD5Utils.md5(psw);//把密码用MD5加密
//        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
//        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
//        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
//        SharedPreferences.Editor editor=sp.edit();
//        //以用户名为key，密码为value保存在SharedPreferences中
//        //key,value,如键值对，editor.putString(用户名，密码）;
//        editor.putString(userName, md5Psw);
//        //提交修改 editor.commit();
//        editor.commit();
    }
    }
