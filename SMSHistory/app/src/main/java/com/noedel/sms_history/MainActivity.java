package com.noedel.sms_history;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView textFileInfo;

    FileInputStream inputStream;

    String filename = "smshistory3.txt";
    String fileContents = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFileInfo = findViewById(R.id.stringHistory);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                                    Manifest.permission.SEND_SMS}, 1);



        reloadHistory();

        Button buttonSend = (Button) findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText numberText = (EditText) findViewById(R.id.idNumber);
                String number = numberText.getText().toString();

                EditText messageText = (EditText) findViewById(R.id.idMessage);
                String message = messageText.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(number, null, message, null,null);
                sendMessage();
                reloadHistory();
            }
        });
    }


    public void sendMessage() {


        try {
            EditText numberText =  findViewById(R.id.idNumber);
            EditText messageText =  findViewById(R.id.idMessage);
            String numberHistory = numberText.getText().toString();
            String textHistory = messageText.getText().toString();

            fileContents += "To " + numberHistory + ": - '" + textHistory + "'" + "\n";

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("smshistory3.txt", Context.MODE_PRIVATE));
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
            inputStream = openFileInput("smshistory3.txt");

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
