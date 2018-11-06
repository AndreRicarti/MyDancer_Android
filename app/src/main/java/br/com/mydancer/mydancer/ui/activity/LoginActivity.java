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
import br.com.mydancer.mydancer.model.LoginBody;
import br.com.mydancer.mydancer.model.User;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView etEmail;
    TextView etSenha;
    Button btAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeFields();

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBody loginBody = new LoginBody();
                loginBody.setEmail(etEmail.getText().toString());
                loginBody.setSenha(etSenha.getText().toString());

                Call<User> call = new RetrofitInicializador().getLoginService().login(loginBody);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Intent intent = new Intent(LoginActivity.this, CallPersonalDancerActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void initializeFields() {
        etEmail = findViewById(R.id.login_et_email);
        etSenha = findViewById(R.id.login_et_senha);
        btAcessar = findViewById(R.id.login_bt_acessar);
    }
}
