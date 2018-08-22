package com.powellapps.dreamsire.ws;

import com.powellapps.dreamsire.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by raphaelramos on 20/11/2017.
 */

public interface APIService {

    @POST("salva")
    Call<Usuario> createUser(@Body Usuario user);
}
