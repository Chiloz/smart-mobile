package com.noedel.sms_history;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView textFileInfo;

    FileOutputStream outputStream;
    FileInputStream inputStream;

    String filename = "smshistory.txt";
    String fileContents = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFileInfo = findViewById(R.id.stringHistory);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        reloadHistory();
    }


    public void sendMessage(View view) {


        try {
            EditText numberText = (EditText) findViewById(R.id.idNumber);
            String history = numberText.getText().toString();

            fileContents += history + "\n";

//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(fileContents.getBytes());
//            outputStream.close();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("smshistory.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(fileContents);
            outputStreamWriter.close();

            reloadHistory();
            Toast.makeText(this, "Opgeslagen in: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadHistory() {
        try {
//            inputStream = openFileInput("smshistory.txt");
//            inputStream.read(fileContents.getBytes());
//
//            textFileInfo.setText(fileContents);
//
//            inputStream.close();
            inputStream = openFileInput("smshistory.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString + "\n");
                }

                inputStream.close();
                fileContents = stringBuilder.toString();
                textFileInfo.setText(fileContents);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
