package br.com.mydancer.mydancer.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import br.com.mydancer.mydancer.ui.recyclerview.adapter.EventAdapter;
import br.com.mydancer.mydancer.ui.recyclerview.adapter.listener.OnItemClickListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        final ProgressDialog progress = new ProgressDialog(EventActivity.this);

        progress.setTitle("Carregando");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();

        Call<List<Event>> call = new RetrofitInicializador().getEventService().lista();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> events = response.body();
                configuraRecyclerView(events);
                progress.dismiss();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
                progress.dismiss();
            }
        });
    }

    private void configuraRecyclerView(List<Event> events) {
        RecyclerView listEvents = findViewById(R.id.list_event_recyclerview);
        configuraAdapter(events, listEvents);

    }

    private void configuraAdapter(final List<Event> events, RecyclerView listEvents) {
        adapter = new EventAdapter(events, this);
        listEvents.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(Event event, int posicao) {
                Intent intent = new Intent(EventActivity.this, ConfirmEventActivity.class);
                intent.putExtra("nomeEvento", events.get(posicao));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
