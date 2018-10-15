package br.com.mydancer.mydancer.services;

import java.util.List;

import br.com.mydancer.mydancer.model.Event;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventService {
    @GET("Events")
    Call<List<Event>> lista();
}
