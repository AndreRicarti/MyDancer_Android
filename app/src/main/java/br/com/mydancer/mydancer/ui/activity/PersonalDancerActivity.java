package br.com.mydancer.mydancer.ui.activity;

import android.app.AlertDialog;
import android.app.Person;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.mydancer.mydancer.R;
import br.com.mydancer.mydancer.event.CallAlertPersonalEvent;
import br.com.mydancer.mydancer.firebase.SharedPrefManager;
import br.com.mydancer.mydancer.model.FirebaseUserToken;
import br.com.mydancer.mydancer.model.User;
import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDancerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_dancer);

        setTitle("Personal Dancer");

        EventBus eventBus = EventBus.getDefault();
        eventBus.register(this);

        Intent getIntent = getIntent();
        User user = (User) getIntent.getSerializableExtra("putLoginUser");

        FirebaseUserToken firebaseUserToken = new FirebaseUserToken();
        firebaseUserToken.setUserId(user.getId());
        firebaseUserToken.setToken(SharedPrefManager.getmInstance(this).getToken());
        firebaseUserToken.setStatus(false);
        firebaseUserToken.setDateCreation(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));

        Call call = new RetrofitInicializador().getFirebaseService().postFirebaseUserToken(firebaseUserToken);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("onResponse", "Requisição com sucesso");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(PersonalDancerActivity.this, "onFailure - Requisição falhou", Toast.LENGTH_SHORT).show();
            }
        });

        TextView textView = findViewById(R.id.personal_name);
        textView.setText("Olá " + user.getName());
    }

    @Subscribe
    public void callAlertPersonal(final CallAlertPersonalEvent event) {

        PersonalDancerActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(PersonalDancerActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(PersonalDancerActivity.this);
                }
                builder.setTitle("Solicitação de Dança")
                        .setMessage(event.toString() + " está chamando para dançar.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
}
