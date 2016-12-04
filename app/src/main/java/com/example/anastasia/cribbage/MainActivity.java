package com.example.anastasia.cribbage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText setipAddress;
    private EditText setPortAddress;
    private Button  connect;
    private  InetAddress serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setipAddress = (EditText) findViewById(R.id.ipAddressHolder);
        setPortAddress = (EditText) findViewById(R.id.PortAddress);
        connect = (Button)findViewById(R.id.Connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAddress = setipAddress.getText().toString();
                String portAddress = setPortAddress.getText().toString();
                SingletonServer.initialize(ipAddress, portAddress);
                setContentView(R.layout.activity_ui);
            }
        });
    }

}
