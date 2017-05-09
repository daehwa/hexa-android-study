package com.example.eoghk.sqlite_practice;

import android.provider.Settings;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DBManager dbManager = new DBManager(getApplicationContext(),"Comment.db",null,1);

        final TextView result = (TextView) findViewById(R.id.result);

        final EditText etitle = (EditText) findViewById(R.id.title);
        final EditText edate = (EditText) findViewById(R.id.date);

        long now = System.currentTimeMillis(); //현재시간
        final Time date = new Time(now); //현재 시간의 날짜

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/d h:m:s"); //날짜의 출력 형식
        edate.setText(simpleDateFormat.format(date)); //date를 저 포멧으로 받아야함(?)

        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String date = edate.getText().toString();
                System.out.println(date);
                String title = etitle.getText().toString();
                System.out.println(title);

                dbManager.insert(title,date,date);
                result.setText(dbManager.getResult());
                etitle.setText("");
            }
        });

        Button update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = etitle.getText().toString();
                String date = edate.getText().toString();

                dbManager.update(title,date);
                result.setText(dbManager.getResult());
                etitle.setText("");
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = etitle.getText().toString();

                dbManager.delete(title);
                result.setText(dbManager.getResult());
                etitle.setText("");
            }
        });

        Button select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(dbManager.getResult());
            }
        });
    }
}
