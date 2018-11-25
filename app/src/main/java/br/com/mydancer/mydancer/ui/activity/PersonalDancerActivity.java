package br.com.mydancer.mydancer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.mydancer.mydancer.R;
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

        Toast.makeText(this, SharedPrefManager.getmInstance(this).getToken(), Toast.LENGTH_LONG).show();

        Intent getIntent = getIntent();
        User user = (User) getIntent.getSerializableExtra("putUserLogin");

        FirebaseUserToken firebaseUserToken = new FirebaseUserToken();
        firebaseUserToken.setUserId(user.getId());
        firebaseUserToken.setToken(SharedPrefManager.getmInstance(this).getToken());
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

        TextView textView = findViewById(R.id.personal_token);
        textView.setText(user.getName());
    }
}
