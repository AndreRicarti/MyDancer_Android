package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.model.FirebaseUserToken;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallPersonalDancerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_personal_dancer);

        Intent getIntent = getIntent();

        TextView eventTitle = findViewById(R.id.call_personal_title);
        eventTitle.setText((String) getIntent.getSerializableExtra("tituloEvento"));

        Button btCall = findViewById(R.id.call_personal_call);
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<FirebaseUserToken> call = new RetrofitInicializador().getFirebaseService().GetFirebaseUserTokens();
                call.enqueue(new Callback<FirebaseUserToken>() {
                    @Override
                    public void onResponse(Call<FirebaseUserToken> call, Response<FirebaseUserToken> response) {
                        FirebaseUserToken firebaseUserToken = new FirebaseUserToken();
                        firebaseUserToken = response.body();

                        sendMessagePersonalDancer(firebaseUserToken);

                    }

                    @Override
                    public void onFailure(Call<FirebaseUserToken> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void sendMessagePersonalDancer(FirebaseUserToken firebaseUserToken) {
        Call<Void> call = new RetrofitInicializador().getFirebaseService().cloudMessaging(firebaseUserToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
