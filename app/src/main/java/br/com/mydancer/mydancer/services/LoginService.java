package br.com.mydancer.mydancer.services;

import br.com.mydancer.mydancer.model.LoginBody;
import br.com.mydancer.mydancer.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("Users/Login")
    Call<User> login(@Body LoginBody loginBody);
}
