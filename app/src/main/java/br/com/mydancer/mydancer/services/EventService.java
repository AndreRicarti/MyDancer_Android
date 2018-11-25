package br.com.mydancer.mydancer.services;

import java.util.List;

import br.com.mydancer.mydancer.model.Event;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventService {
    @GET("Events")
    Call<List<Event>> lista();

    @GET("Events/GetDate/{date}")
    Call<List<Event>> getDate(@Path("date") String date);
}
