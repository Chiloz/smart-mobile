package com.noedel.managerwifi;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textWiFiInfo;
    Button buttonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textWiFiInfo = findViewById(R.id.idText);
        buttonInfo = findViewById(R.id.idButton);
    }

    public void getWiFiInformation(View view) {
        WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        String output = "SSID heeft level: " + wifiInfo.getSSID() + "\n";

        textWiFiInfo.setText(output);

    }
}
