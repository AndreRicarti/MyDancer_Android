package br.com.mydancer.mydancer.firebase;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

import br.com.mydancer.mydancer.event.CallAlertPersonalEvent;
import br.com.mydancer.mydancer.model.CallPersonalDancer;

public class MyDancerMessageReceived extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> messageData = remoteMessage.getData();
        Log.i("Mensagem Recebida", String.valueOf(messageData));

        callDancer(messageData);
    }

    private void callDancer(Map<String, String> messageData) {
        String chaveDeAcesso = "callPersonalDancer";
        if (messageData.containsKey(chaveDeAcesso)) {
            String json = messageData.get(chaveDeAcesso);
            ObjectMapper mapper = new ObjectMapper();
            try {
                CallPersonalDancer callPersonalDancer = mapper.readValue(json, CallPersonalDancer.class);

                EventBus eventBus = EventBus.getDefault();
                eventBus.post(new CallAlertPersonalEvent(callPersonalDancer));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
