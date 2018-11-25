package br.com.mydancer.mydancer.firebase;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import br.com.mydancer.mydancer.retrofit.RetrofitInicializador;

public class MyDancerInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token Firebase", "##############################################################################################################################: " + refreshedToken);

        storeToken(refreshedToken);
    }

    private void storeToken(String refreshedToken) {
       SharedPrefManager.getmInstance(getApplicationContext()).storeToken(refreshedToken);
    }
}
