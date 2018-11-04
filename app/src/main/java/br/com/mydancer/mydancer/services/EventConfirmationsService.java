package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.EventConfirmations;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface EventConfirmationsService {
    @POST("EventConfirmations")
    Call<Void> insere(@Body EventConfirmations eventConfirmations);
}
