package com.example.anastasia.cribbage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText setipAddress;
    private EditText setPortNumber;
    private Button  connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setipAddress = (EditText) findViewById(R.id.ip_address_holder);
        setPortNumber = (EditText) findViewById(R.id.port_number_holder);
        connect = (Button)findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAddress = setipAddress.getText().toString();
                int portNumber = Integer.parseInt(setPortNumber.getText().toString());
                SingletonSocket.initialize(ipAddress, portNumber);
                setContentView(R.layout.activity_ui);
            }
        });
    }

}
