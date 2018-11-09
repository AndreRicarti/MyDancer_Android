package br.com.mydancer.mydancer.firebase;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyDancerInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token Firebase", "##############################################################################################################################: " + refreshedToken);

        storeToken(refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        //enviaTokenServidor(refreshedToken);
    }

    private void storeToken(String refreshedToken) {
       SharedPrefManager.getmInstance(getApplicationContext()).storeToken(refreshedToken);
    }
}
