package br.com.mydancer.mydancer.ui.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.model.ErrorResponse;
import br.com.mydancer.mydancer.model.Event;
import br.com.mydancer.mydancer.model.EventConfirmations;
import br.com.mydancer.mydancer.model.LoginBody;
import br.com.mydancer.mydancer.model.User;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.mydancer.mydancer.util.DateUtil.currentDateFormat;

public class LoginActivity extends AppCompatActivity {
    TextView etEmail;
    TextView etSenha;
    Button btAcessar;
    Event event = new Event();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Login");

        final AlertDialog progress;
        progress = new SpotsDialog(this, R.style.Custom);

        initializeFields();

        btAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginBody loginBody = new LoginBody();
                loginBody.setEmail(etEmail.getText().toString());
                loginBody.setSenha(etSenha.getText().toString());

                progress.setTitle("Carregando");
                progress.setCancelable(false);
                progress.show();

                Call<User> call = new RetrofitInicializador().getLoginService().login(loginBody);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.code() == 200) {
                            User user = response.body();
                            Intent intent;

                            if (user.getUserTypeId() == 2) {
                                intent = new Intent(LoginActivity.this, PersonalDancerActivity.class);
                                progress.dismiss();
                                intent.putExtra("putLoginUser", user);
                                startActivity(intent);
                            } else {
                                intent = new Intent(LoginActivity.this, CallPersonalDancerActivity.class);
                                Intent getIntent = getIntent();

                                if (getIntent.getSerializableExtra("putEventId") != null) {
                                    EventConfirmations eventConfirmations = new EventConfirmations();
                                    eventConfirmations.setEventId((Integer) getIntent.getSerializableExtra("putEventId"));
                                    eventConfirmations.setUserId(user.getId());
                                    eventConfirmations.setDateCreation(currentDateFormat());

                                    final Call<EventConfirmations> callEventConfirmations = new RetrofitInicializador().getEventConfirmationsService().insere(eventConfirmations);
                                    callEventConfirmations.enqueue(new Callback<EventConfirmations>() {
                                        @Override
                                        public void onResponse(Call<EventConfirmations> call, Response<EventConfirmations> response) {
                                            Log.i("onResponse", "Requisição com sucesso " + response.body());
                                        }

                                        @Override
                                        public void onFailure(Call<EventConfirmations> call, Throwable t) {
                                            Log.e("onFailure chamado", t.getMessage());
                                        }
                                    });

                                    progress.dismiss();
                                    intent.putExtra("putLoginUser", user);
                                    startActivity(intent);
                                }else {
                                    progress.dismiss();
                                    Toast.makeText(LoginActivity.this, "Usuário precisa ser um Personal Dancer", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else if (response.code() == 422) {
                            progress.dismiss();
                            Gson gson = new Gson();
                            ErrorResponse message = gson.fromJson(response.errorBody().charStream(), ErrorResponse.class);
                            Toast.makeText(LoginActivity.this, message.getError().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "O e-mail ou senha esta errado.", Toast.LENGTH_SHORT).show();
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
