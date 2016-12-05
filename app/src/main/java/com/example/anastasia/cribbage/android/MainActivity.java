package com.example.anastasia.cribbage.android;

import com.example.anastasia.cribbage.GameState;
import com.example.anastasia.cribbage.PlayerClient;
import com.example.anastasia.cribbage.PlayerController;
import com.example.anastasia.cribbage.SingletonSocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText setipAddress;
    private EditText setPortNumber;
    private Button  connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build());
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
                String meInt = SingletonSocket.readLine();
                int me = Integer.parseInt(meInt);
                String state = SingletonSocket.readLine();
                GameState initial = new GameState(state);
                android.util.Log.e("SUSAN", findViewById(android.R.id.content) + "");
                PlayerClient client = (PlayerClient) findViewById(R.id.activity_ui).getParent();
                android.util.Log.e("SUSAN", client + "");
                PlayerController controller = new PlayerController(initial.getPlayers()[me], initial, client);
            }
        });
    }

}
