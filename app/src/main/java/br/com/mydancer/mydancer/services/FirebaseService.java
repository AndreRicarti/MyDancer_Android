package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.FirebaseUserToken;
import br.com.mydancer.mydancer.model.LoginBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FirebaseService {
    @POST("FirebaseUserTokens")
    Call<Void> postFirebaseUserToken(@Body FirebaseUserToken firebaseUserToken);
}
