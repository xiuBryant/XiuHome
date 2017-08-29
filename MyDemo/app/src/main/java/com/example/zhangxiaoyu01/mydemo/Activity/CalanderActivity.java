package com.example.zhangxiaoyu01.mydemo.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zhangxiaoyu01.mydemo.R;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CalanderActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.readUserButton)
    Button readUserButton;

    @Bind(R.id.readEventButton)
    Button readEventButton;

    @Bind(R.id.writeEventButton)
    Button writeEventButton;


    private static String calanderURL = "";
    private static String calanderEventURL = "";
    private static String calanderRemiderURL = "";
    //为了兼容不同版本的日历,2.2以后url发生改变
    static{
        if(Build.VERSION.SDK_INT >= 8){
            calanderURL = "content://com.android.calendar/calendars";
            calanderEventURL = "content://com.android.calendar/events";
            calanderRemiderURL = "content://com.android.calendar/reminders";

        }else{
            calanderURL = "content://calendar/calendars";
            calanderEventURL = "content://calendar/events";
            calanderRemiderURL = "content://calendar/reminders";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calander);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        readUserButton.setOnClickListener(this);
        readEventButton.setOnClickListener(this);
        writeEventButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == readUserButton){

            Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null,
                    null, null, null);
            if(userCursor.getCount() > 0){
                userCursor.moveToFirst();
                String userName = userCursor.getString(userCursor.getColumnIndex("name"));
                Toast.makeText(this, userName, Toast.LENGTH_LONG).show();
            }
        }else if(v == readEventButton){
            Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null,
                    null, null, null);
            if(eventCursor.getCount() > 0){
                eventCursor.moveToLast();
                String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                Toast.makeText(this, eventTitle, Toast.LENGTH_LONG).show();
            }
        }else if(v == writeEventButton){
            //获取要出入的gmail账户的id
            String calId = "";
            Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null,
                    null, null, null);
            if(userCursor.getCount() > 0){
                userCursor.moveToFirst();
                calId = userCursor.getString(userCursor.getColumnIndex("_id"));

            }
            ContentValues event = new ContentValues();
            event.put("title", "与苍井空小姐动作交流");
            event.put("description", "Frankie受空姐邀请,今天晚上10点以后将在Sheraton动作交流.lol~");
            //插入hoohbood@gmail.com这个账户
            event.put("calendar_id",calId);

            Calendar mCalendar = Calendar.getInstance();
            mCalendar.set(Calendar.HOUR_OF_DAY,10);
            long start = mCalendar.getTime().getTime();
            mCalendar.set(Calendar.HOUR_OF_DAY,11);
            long end = mCalendar.getTime().getTime();

            event.put("dtstart", start);
            event.put("dtend", end);
            event.put("hasAlarm",1);

            event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());

            Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
            long id = Long.parseLong( newEvent.getLastPathSegment() );
            ContentValues values = new ContentValues();
            values.put( "event_id", id );
            //提前10分钟有提醒
            values.put( "minutes", 10 );
            getContentResolver().insert(Uri.parse(calanderRemiderURL), values);
            Toast.makeText(this, "插入事件成功!!!", Toast.LENGTH_LONG).show();
        }
}
}
