package br.com.mydancer.mydancer.event;

import br.com.mydancer.mydancer.model.CallPersonalDancer;

public class CallAlertPersonalEvent {

    String nomeDoEvento = "";

    public CallAlertPersonalEvent(CallPersonalDancer callPersonalDancer) {
        nomeDoEvento = callPersonalDancer.getNameUser();
    }

    public String toString() {
        return nomeDoEvento;
    }
}
