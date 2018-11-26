package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.FirebaseUserToken;
import br.com.mydancer.mydancer.model.User;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallPersonalDancerActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_personal_dancer);

        getIntentActivity();

        Button btCall = findViewById(R.id.call_personal_call);
        btCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestGetTokenPersonal();
            }
        });
    }

    private void getIntentActivity() {
        Intent getIntent = getIntent();
        user = (User) getIntent.getSerializableExtra("putLoginUser");
    }

    private void requestGetTokenPersonal() {
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
            }
        });
    }

    private void sendMessagePersonalDancer(String personalToken) {
        Call<Void> call = new RetrofitInicializador().getFirebaseService().cloudMessaging(user.getId(), personalToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("onResponse", "Requisição com sucesso");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("onFailure chamado", t.getMessage());
            }
        });
    }
}
