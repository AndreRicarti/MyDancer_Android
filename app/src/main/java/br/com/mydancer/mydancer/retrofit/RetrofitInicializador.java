package br.com.mydancer.mydancer.retrofit;

import br.com.mydancer.mydancer.model.EventConfirmations;
import br.com.mydancer.mydancer.services.EventConfirmationsService;
import br.com.mydancer.mydancer.services.EventService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInicializador {
    private final Retrofit retrofit;

    public RetrofitInicializador() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://serviceeventrest.azurewebsites.net/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public EventService getEventService() {
        return retrofit.create(EventService.class);
    }

    public EventConfirmationsService getEventConfirmationsService() {
        return retrofit.create(EventConfirmationsService.class);
    }
}
