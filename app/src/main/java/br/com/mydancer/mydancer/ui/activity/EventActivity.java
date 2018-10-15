package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import java.util.List;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import br.com.mydancer.mydancer.ui.recyclerview.adapter.EventAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends AppCompatActivity {

    private EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Call<List<Event>> call = new RetrofitInicializador().getEventService().lista();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                List<Event> events = response.body();
                configuraRecyclerView(events);
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });
    }

    private void configuraRecyclerView(List<Event> events) {
        RecyclerView listEvents = findViewById(R.id.list_event_recyclerview);
        configAdapter(events, listEvents);

    }

    private void configAdapter(List<Event> events, RecyclerView listEvents) {
        adapter = new EventAdapter(events, this);
        listEvents.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
