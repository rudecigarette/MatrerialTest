package com.example.materialtest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.materialtest.R;
import com.example.materialtest.Views.inputView;
import com.example.materialtest.utils.MysqlUtil;
import com.example.materialtest.utils.StatusBarUtils;
import com.example.materialtest.utils.UserUtils;

public class LoginActivity extends BaseActivity {

    private inputView mInputPhone,mInputPassward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        MysqlUtil.getAllPhoneAndPassword();
    }
    /*
    * 初始化View
    * */
    private void initView(){
        initNavbar(false,"登录");
        mInputPhone = findViewById(R.id.input_phone);
        mInputPassward = findViewById(R.id.input_passward);
        StatusBarUtils.setColor(this, getResources().getColor(R.color.colorPrimary));
    }
    /*
     *跳转注册页面点击事件
     * */
    public void onRegisterClick(View v){
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
    /*
     *登录按钮点击事件
     * */
    public void onCommitClick(View v){
        String phone = mInputPhone.getInputStr();
        String passward = mInputPassward.getInputStr();
        //验证用户输入是否合法
        if(!UserUtils.validateLogin(this,phone,passward)){
            return;
        }
        Toast.makeText(this,"登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,ChoseLabelActivity.class);
        startActivity(intent);
        MysqlUtil.getUserCollection(phone);
        finish();
    }
}
