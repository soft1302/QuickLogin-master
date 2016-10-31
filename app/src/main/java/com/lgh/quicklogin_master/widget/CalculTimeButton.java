package com.lgh.quicklogin_master.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Button;

import com.lgh.quicklogin_master.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by guohu on 2016/10/25.
 * 时间计时器button，直接可以使用，还可以设置文字和时间
 *
 */

public class CalculTimeButton extends Button {
    private String txtAfter = "(s)后重试";
    private String txtBefore = "获取验证码";
    private int maxTime = 60;

    // 时间计时器
    private Timer timer = null;
    private TimerTask task = null;
    private int i = maxTime;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.arg1 < 0) {
                // 设计原来样式
                CalculTimeButton.this.setText(txtBefore);
                CalculTimeButton.this.setBackgroundResource(R.drawable.btn_normal);
                i = maxTime;
                stop();
                CalculTimeButton.this.setClickable(true);
            } else {
                CalculTimeButton.this.setText(msg.arg1 + txtAfter);
                startTime();
            }
        }
    };

    public CalculTimeButton(Context context) {
        super(context);
    }

    public CalculTimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CalculTimeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void startTime() {
        timer = new Timer();
        task = new TimerTask() {

            @Override
            public void run() {
                Message message = mHandler.obtainMessage();
                message.arg1 = i;
                mHandler.sendMessage(message);
                i--;
            }
        };
        timer.schedule(task, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public void begin() {
        CalculTimeButton.this.setClickable(false);
        // 验证码计时
        CalculTimeButton.this.setText(i + txtAfter);
        CalculTimeButton.this.setBackgroundResource(R.drawable.btn_calcul_time);
        i--;
        startTime();
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
        this.i = maxTime;
    }

    public void setTxtAfter(String txtAfter) {
        this.txtAfter = txtAfter;
    }

    public void setTxtBefore(String txtBefore) {
        this.txtBefore = txtBefore;
    }
}
