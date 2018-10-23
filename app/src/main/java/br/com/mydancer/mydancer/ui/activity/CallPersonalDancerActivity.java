package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;

public class CallPersonalDancerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_personal_dancer);

        Intent getIntent = getIntent();

        TextView eventTitle = findViewById(R.id.call_personal_title);
        eventTitle.setText((String) getIntent.getSerializableExtra("tituloEvento"));
    }
}
