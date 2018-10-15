package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        TextView textView = findViewById(R.id.textView);
        Event event = new Event();
        event = (Event) intent.getSerializableExtra("nomeEvento");

        textView.setText(event.getTitle());

    }
}
