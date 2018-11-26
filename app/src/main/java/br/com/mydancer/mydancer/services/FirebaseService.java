package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.FirebaseUserToken;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FirebaseService {
    @POST("FirebaseUserTokens")
    Call<Void> postFirebaseUserToken(@Body FirebaseUserToken firebaseUserToken);

    @POST("FirebaseUserToken/CallPersonaDancer")
    Call<FirebaseUserToken> CallPersonaDancer();

    @POST("Firebase/CloudMessaging/{userId}/{personalToken}")
    Call<Void> cloudMessaging(@Path("userId") int userId, @Path("personalToken") String personalToken);
}
