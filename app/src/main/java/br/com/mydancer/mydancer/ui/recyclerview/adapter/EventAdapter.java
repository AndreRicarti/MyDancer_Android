package br.com.mydancer.mydancer.ui.recyclerview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.ui.activity.EventActivity;
import br.com.mydancer.mydancer.ui.activity.MainActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private final List<Event> events;
    private final Context context;

    public EventAdapter(List<Event> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @NonNull
    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewCriada = LayoutInflater.from(context)
                .inflate(R.layout.item_event, viewGroup, false);
        return new EventViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.EventViewHolder eventViewHolder, int i) {
        Event event = events.get(i);
        eventViewHolder.vincula(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        private final TextView data;
        private final TextView nomeEvento;
        private final ImageView imagemEvento;
        public String nomeEventoFinal;
        public View view;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            data = itemView.findViewById(R.id.item_event_data);
            nomeEvento = itemView.findViewById(R.id.item_event_descricao_evento);
            imagemEvento = itemView.findViewById(R.id.item_event_imagem);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.putExtra("nomeEvento", nomeEventoFinal);
                    context.startActivity(intent);
                }
            });
        }

        public void vincula(Event event) {
            preencheCampo(event);
        }

        public void preencheCampo(Event event) {
            data.setText(formataData(event.getDateStart()));
            nomeEvento.setText(event.getTitle());
            nomeEventoFinal = event.getTitle();

            int id = event.getId();

            if (id == 1)
            {
                imagemEvento.setBackgroundResource(R.drawable.balada);
            } else if (id == 2) {
                imagemEvento.setBackgroundResource(R.drawable.balada2);
            } else if (id == 3) {
                imagemEvento.setBackgroundResource(R.drawable.balada3);
            } else {
                imagemEvento.setBackgroundResource(R.drawable.balada);
            }
        }

        private String formataData(Date date) {
            Locale local = new Locale("pt","BR");
            DateFormat formato = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy ',' HH:mm", local);
            return formato.format(date);
        }
    }

}



