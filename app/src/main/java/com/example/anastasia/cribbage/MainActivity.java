package com.example.anastasia.cribbage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private EditText setIPAddress;
    private EditText setPortAddress;
    private Button  connect;
    private  InetAddress serverAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIPAddress = (EditText) findViewById(R.id.IPAddressHolder);
        setPortAddress = (EditText) findViewById(R.id.PortAddress);
        connect = (Button)findViewById(R.id.Connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IPAddress = setIPAddress.getText().toString();
                String portAddress = setPortAddress.getText().toString();
                SingletonSocket instance  = SingletonSocket.getInstance();



                try {
                    serverAddress = InetAddress.getByName(IPAddress);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }


            }

        });



    }

}
