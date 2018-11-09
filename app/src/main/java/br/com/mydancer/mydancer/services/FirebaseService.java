package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.FirebaseUserToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface FirebaseService {
    @POST("FirebaseUserTokens")
    Call<Void> postFirebaseUserToken(@Body FirebaseUserToken firebaseUserToken);

    @GET("FirebaseUserTokens")
    Call<FirebaseUserToken> GetFirebaseUserTokens();

    @POST("Firebase/CloudMessaging")
    Call<Void> cloudMessaging(@Body FirebaseUserToken firebaseUserToken);
}
