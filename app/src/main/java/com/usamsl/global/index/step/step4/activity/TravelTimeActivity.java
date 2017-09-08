package com.usamsl.global.index.step.step4.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.timessquare.CalendarCellDecorator;
import com.squareup.timessquare.CalendarPickerView;
import com.squareup.timessquare.DefaultDayViewAdapter;
import com.usamsl.global.R;
import com.usamsl.global.constants.Constants;
import com.usamsl.global.util.manager.ActivityManager;
import com.usamsl.global.view.MyCalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * 时间：2017/1/9
 * 描述：出行时间的选择
 */
public class TravelTimeActivity extends AppCompatActivity {

    //返回按钮
    private ImageView img_back;
    //确定按钮
    private RelativeLayout tv_ok;
    private CalendarPickerView calendar;
    //选择的日期
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_time);
        ActivityManager.getInstance().addActivity(TravelTimeActivity.this);
        initView();
        initData();
        toListener();
    }

    /**
     * 监听
     */
    private void toListener() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra("time", str);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Constants.travelDate = date;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                str = format.format(date);
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });
    }

    /**
     * 数据加载
     */
    private void initData() {
        calendar.setCustomDayView(new DefaultDayViewAdapter());
        Calendar c = Calendar.getInstance();
        c.add(c.DAY_OF_MONTH, Constants.plan_weekday + 1);
        Date date = c.getTime();
        c.add(c.MONTH, 6);
        Date travel = c.getTime();
        calendar.setDecorators(Collections.<CalendarCellDecorator>emptyList());
        calendar.init(date, travel)
                .inMode(CalendarPickerView.SelectionMode.SINGLE);
    }

    /**
     * 界面初始化
     */
    private void initView() {
        tv_ok = (RelativeLayout) findViewById(R.id.tv_ok);
        img_back = (ImageView) findViewById(R.id.img_back);
        calendar = (MyCalendarView) findViewById(R.id.calendar_view);
    }
}
