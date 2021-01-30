package com.example.tap_tap_search;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class FileToSpeechActivity extends AppCompatActivity {
    private int request_code =1, FILE_SELECT_CODE =101;
    private static final int PICK_IMAGE_REQUEST = 1 ;
    private TextView textView;
    private TextToSpeech textToSpeech;
    private Object MyService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_to_speech);

        textView = findViewById(R.id.F2STv);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });

        Button upload = (Button)findViewById(R.id.F2SUpload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                }
                showFileChooser();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(filePath);
                Scanner input = new Scanner(inputStream);
                StringBuffer br = new StringBuffer();

                while(input.hasNext()){
                    String line = input.nextLine();
                    br.append(line);

                }
                textView.setText(br.toString());
                textToSpeech.speak(br.toString(), TextToSpeech.QUEUE_FLUSH, null);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}