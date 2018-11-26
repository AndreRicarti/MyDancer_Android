package br.com.mydancer.mydancer.ui.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.model.FirebaseUserToken;
import br.com.mydancer.mydancer.model.User;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallPersonalDancerActivity extends AppCompatActivity {

    User user;
    Event event;
    AlertDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_personal_dancer);
        setTitle("Vamos Dançar");

        getIntentActivity();

        progress = new SpotsDialog(this, R.style.Custom);

        Button btCall = findViewById(R.id.call_personal_bt_call);
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestGetTokenPersonal();

                progress.dismiss();
            }
        });

        TextView nomeEvento = findViewById(R.id.call_personal_tv_nome_evento);
        nomeEvento.setText(event.getTitle());
    }

    private void getIntentActivity() {
        Intent getIntent = getIntent();
        user = (User) getIntent.getSerializableExtra("putLoginUser");
        event = (Event) getIntent.getSerializableExtra("putEvent");
    }

    private void requestGetTokenPersonal() {
        progress.setTitle("Chamando");
        progress.setCancelable(false);
        progress.show();

        Call<FirebaseUserToken> call = new RetrofitInicializador().getFirebaseService().CallPersonaDancer();
        call.enqueue(new Callback<FirebaseUserToken>() {
            @Override
            public void onResponse(Call<FirebaseUserToken> call, Response<FirebaseUserToken> response) {
                String personalToken = String.valueOf(response.body().getToken());
                sendMessagePersonalDancer(personalToken);
            }

            @Override
            public void onFailure(Call<FirebaseUserToken> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
                progress.dismiss();
            }
        });
    }

    private void sendMessagePersonalDancer(String personalToken) {
        Call<Void> call = new RetrofitInicializador().getFirebaseService().cloudMessaging(user.getId(), personalToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("onResponse", "Requisição com sucesso");
                Toast.makeText(CallPersonalDancerActivity.this, "Solicitação enviada com sucesso, aguarde para dançar!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
                progress.dismiss();
            }
        });
    }
}
