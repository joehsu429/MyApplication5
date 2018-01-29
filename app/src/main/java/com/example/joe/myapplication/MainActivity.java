package com.example.joe.myapplication;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class

MainActivity extends AppCompatActivity {

    private TextView sleep_showtime,awake_showtime;
    private Button btn,btn2;
    private Calendar mCalendar;
    private String str;
    private SimpleDateFormat df;
    private long startTime;
    private Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setViews();
        operation();
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd HH:mm");
        try
        {
            Date d1 = df.parse("yyyy/MM/dd HH:mm");
            Date d2 = df.parse("yyyy/MM/dd HH:mm");
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            long days = diff / (1000 * 60 * 60 * 24);

            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            System.out.println(""+days+"天"+hours+"小时"+minutes+"分");
        }catch (Exception e)
        {
        }
    }

    public void setViews() {
        sleep_showtime = (TextView) findViewById(R.id.sleep_showtime);
        awake_showtime=(TextView) findViewById(R.id.awake_showtime);
        btn =(Button)findViewById(R.id.startsleepbt);
        btn2=(Button)findViewById(R.id.awakeupbt);

    }

    public void operation(){
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sleep_showtime.setText(""+ str);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                awake_showtime.setText(""+ str);
            }
        });

        startTime = System.currentTimeMillis(); //取得目前時間
        handler.removeCallbacks(updateTimer);//設定定時要執行的方法
        handler.postDelayed(updateTimer, 500);//設定Delay的時間

    }

    //固定要執行的方法
    Runnable updateTimer = new Runnable() {
        public void run() {
            handler.postDelayed(this, 500);
            mCalendar = Calendar.getInstance();
            df = new SimpleDateFormat("yyyy/MM/dd  HH:mm");
            str = df.format(mCalendar.getTime());
        }
    };

}
