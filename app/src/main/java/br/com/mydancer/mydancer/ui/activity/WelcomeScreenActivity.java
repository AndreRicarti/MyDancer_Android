package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button btUser = findViewById(R.id.welcome_bt_personal_user);
        Button btPersonalDancer =  findViewById(R.id.welcome_bt_personal_dancer);

        final Intent intentEvent = new Intent(WelcomeScreenActivity.this, EventTabActivity.class);
        final Intent intentPersonalDancer = new Intent(WelcomeScreenActivity.this, LoginActivity.class);

        btUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentEvent);
            }
        });

        btPersonalDancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentPersonalDancer);
            }
        });
    }
}
