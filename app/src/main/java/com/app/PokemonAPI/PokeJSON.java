package com.app.PokemonAPI;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeJSON {
    @GET("pokemon")
    Call<PokeResults>ChamarListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
