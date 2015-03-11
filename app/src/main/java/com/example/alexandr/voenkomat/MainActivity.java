package com.example.alexandr.voenkomat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    Button calcbtn;
    String endDate;
    EditText endDateField;
    long now, end, diff;
    Locale myLocale = new Locale("ru","RU");
    Calendar calEnd;
    SimpleDateFormat sdf;
    TimeZone tz;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        calcbtn = (Button) findViewById(R.id.calcbtn);
        calcbtn.setOnClickListener(this);
        endDateField = (EditText) findViewById(R.id.endDateField);

        tz = TimeZone.getTimeZone("GMT+03:00");

        calEnd = Calendar.getInstance(tz);
        calEnd.set(2015, Calendar.JUNE, 1);

        sdf = new SimpleDateFormat("d.MMMM.yyyy",myLocale);
        endDate = sdf.format(calEnd.getTime());
        end = calEnd.getTimeInMillis();

        endDateField.setText(endDate);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Calendar calNow = Calendar.getInstance(tz);
        now = calNow.getTimeInMillis();
        //end = sdf.format(endDateField.getText());

        diff = end - now;

        long seconds = diff / 1000;
        long days = seconds / 86400;
        long hours = (seconds % 86400)/3600;
        long minutes = ((seconds % 86400)%3600) / 60;
        seconds = ((seconds % 86400)%3600) % 60 ;

        String strOutput = getResources().getString(R.string.output);
        String finalStr = String.format(strOutput, days, hours, minutes, seconds);

        //todayDate =  System.currentTimeMillis();
        Toast.makeText(this, finalStr, Toast.LENGTH_LONG).show();

    }
}
