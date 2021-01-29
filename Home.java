package com.example.tap_tapsearch;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Button ttsb,sttb, ftsb, ittb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttsb = (Button)findViewById(R.id.tts);
        sttb = (Button)findViewById(R.id.stt);
        ftsb = (Button)findViewById(R.id.fts);
        ittb = (Button)findViewById(R.id.itt);

        ttsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), TextToSpeechActivity.class);
                startActivity(intent);
            }
        });
        sttb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), SpeechToTextActivity.class);
                startActivity(intent);
            }
        });
        ftsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), FileToSpeechActivity.class);
                startActivity(intent);
            }
        });
        ittb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(getBaseContext(), ImageToSpeechActivity.class);
                startActivity(intent);
            }
        });

    }

}
