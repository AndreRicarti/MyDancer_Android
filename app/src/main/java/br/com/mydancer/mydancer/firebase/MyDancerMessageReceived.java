package br.com.mydancer.mydancer.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyDancerMessageReceived extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> messageData = remoteMessage.getData();
        Log.i("Mensagem Recebida", String.valueOf(messageData));
    }
}
