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

        Button btnProfissional = findViewById(R.id.btnProfissional);
        Button btnUsuario =  findViewById(R.id.btnUsuario);

        final Intent intentVaiProFormulario = new Intent(WelcomeScreenActivity.this, EventActivity.class);

        btnProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeScreenActivity.this, "Você foi direcionado para tela de dançarin@", Toast.LENGTH_LONG).show();
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentVaiProFormulario);
            }
        });
    }
}
