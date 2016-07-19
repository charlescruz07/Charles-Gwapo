package com.example.acer.stopwatch;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private Chronometer chrono;
    private ToggleButton tglBtn;
    private Button btn;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tglBtn = (ToggleButton) findViewById(R.id.toggleButton);
        chrono = (Chronometer) findViewById(R.id.chronometer) ;
        btn = (Button) findViewById(R.id.button);
        chrono.setText("00:00");

        tglBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tglBtn.isChecked())
                {
                    if(counter == 0) {
                        chrono.setBase(SystemClock.elapsedRealtime());
                        chrono.start();
                        counter++;
                    }
                    else {
                        int stoppedMilliseconds = 0;

                        String chronoText = chrono.getText().toString();
                        String array[] = chronoText.split(":");
                        if (array.length == 2) {
                            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000
                                    + Integer.parseInt(array[1]) * 1000;
                        } else if (array.length == 3) {
                            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                                    + Integer.parseInt(array[1]) * 60 * 1000
                                    + Integer.parseInt(array[2]) * 1000;
                        }

                        chrono.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                        chrono.start();
                    }

                }
                else
                {
                    chrono.stop();
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 0;
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.stop();
                tglBtn.setChecked(false);
            }
        });

    }
}
