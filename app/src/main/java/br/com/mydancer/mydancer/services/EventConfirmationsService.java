package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.EventConfirmations;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventConfirmationsService {
    @POST("EventConfirmations")
    Call<EventConfirmations> insere(@Body EventConfirmations eventConfirmations);
}
