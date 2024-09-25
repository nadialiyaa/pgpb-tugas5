package com.example.penugasan5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TimePicker timePicker;
    private Spinner spinnerStatus;
    private EditText editTextKeterangan;
    private Button buttonSubmit;

    private String selectedDate;
    private String selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        timePicker = findViewById(R.id.time_picker);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        editTextKeterangan = findViewById(R.id.editTextKeterangan);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize selected date and time with current values
        Calendar calendar = Calendar.getInstance();
        selectedDate = getFormattedDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        selectedTime = getFormattedTime(timePicker.getHour(), timePicker.getMinute());

        // Handle date selection
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = getFormattedDate(year, month, dayOfMonth);
            }
        });

        // Handle time selection
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                selectedTime = getFormattedTime(hourOfDay, minute);
            }
        });

        // Handle submit button click
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = spinnerStatus.getSelectedItem().toString();
                String keterangan = editTextKeterangan.getText().toString();

                // Display data using Toast (or you can save the data as needed)
                Toast.makeText(MainActivity.this,
                        "Tanggal: " + selectedDate + "\n" +
                                "Jam: " + selectedTime + "\n" +
                                "Status: " + status + "\n" +
                                "Keterangan: " + keterangan,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    // Format date in DD/MM/YYYY format
    private String getFormattedDate(int year, int month, int day) {
        return day + "/" + (month + 1) + "/" + year;
    }

    // Format time in HH:MM AM/PM format
    private String getFormattedTime(int hour, int minute) {
        String amPm = (hour < 12) ? "AM" : "PM";
        int formattedHour = (hour == 0 || hour == 12) ? 12 : hour % 12;
        return String.format("%02d:%02d %s", formattedHour, minute, amPm);
    }

}