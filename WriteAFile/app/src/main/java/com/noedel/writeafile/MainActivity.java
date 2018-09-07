package com.noedel.writeafile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textFileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFileInfo = findViewById(R.id.idText);
        String filename = "myfile.txt";
        String fileContents = "Hello Dylano!";
        FileOutputStream outputStream;
        FileInputStream inputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
            Toast.makeText(this, "Opgeslagen in: " + getFilesDir() + "/" + filename, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        }


        try {
            inputStream = openFileInput("myfile.txt");
            inputStream.read(fileContents.getBytes());

            textFileInfo.setText(fileContents);

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
