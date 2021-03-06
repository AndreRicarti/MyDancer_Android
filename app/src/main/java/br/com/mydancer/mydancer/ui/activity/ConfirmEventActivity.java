package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.model.EventConfirmations;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmEventActivity extends AppCompatActivity {

    TextView nameEvent;
    TextView descriptionEvent;
    Event event = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_event);

        Intent getIntent = getIntent();
        event = (Event) getIntent.getSerializableExtra("nomeEvento");

        callActivity();
        initializeFields();
        fillsFields(event);
    }


    private void callActivity() {
        Button callPersonalDancer = findViewById(R.id.confirm_event_button_confirm);
        callPersonalDancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmEventActivity.this, LoginActivity.class);
                intent.putExtra("putEvent", event);
                startActivity(intent);
            }
        });
    }

    private void fillsFields(Event event) {
        nameEvent.setText(event.getTitle());
        descriptionEvent.setText(event.getDescription());
    }

    private void initializeFields() {
        nameEvent = findViewById(R.id.confirm_event_name_event);
        descriptionEvent = findViewById(R.id.confirm_event_description_event);
    }
}
