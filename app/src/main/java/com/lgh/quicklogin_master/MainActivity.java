package com.lgh.quicklogin_master;
/**
 * create by lgh,
 */

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lgh.quicklogin_master.utile.PhoneValid;
import com.lgh.quicklogin_master.widget.CalculTimeButton;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(value = R.id.tv_title)
    private TextView tv_title;
    @ViewInject(value = R.id.iv_title_back)
    private ImageView iv_title_back;

    @ViewInject(value = R.id.edt_phone)
    private EditText edt_phone;
    @ViewInject(value = R.id.edt_code)
    private EditText edt_code;
    @ViewInject(value = R.id.btn_getCode)
    private CalculTimeButton btn_getCode;
    @ViewInject(value = R.id.btn_login)
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initView();
    }
    private void initView() {
        tv_title.setText("手机号快速登录");
        tv_title.setTextColor(ContextCompat.getColor(getApplication(), R.color.c_txt_main));
    }

    @Event(value = {R.id.rl_title_back, R.id.btn_getCode, R.id.btn_login})
    private void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.rl_title_back:
                finish();
                break;
            case R.id.btn_getCode:
                if (TextUtils.isEmpty(edt_phone.getText())) {
                    Toast.makeText(getApplication(), "请输入电话号码！", Toast.LENGTH_SHORT).show();
                } else if (!PhoneValid.valid(edt_phone.getText().toString())) {
                    Toast.makeText(getApplication(), "请输入正确电话号码！", Toast.LENGTH_SHORT).show();
                } else {
                    getCode();
                }
                break;
            case R.id.btn_login:
                break;
            default:
                break;
        }
    }

    private void getCode() {
        btn_getCode.begin();
    }

    @Override
    protected void onDestroy() {
        btn_getCode.stop();
        super.onDestroy();
    }
}
