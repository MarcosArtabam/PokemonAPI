package com.app.PokemonAPI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.app.PokemonAPI.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private PokeAdapter PokeAdapter;
    private int offset;
    private boolean carregar;
    private ActivityMainBinding binding;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle("Pokédex");
        //Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AnimationDrawable animationDrawable = (AnimationDrawable) binding.fundo.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        recyclerView = (RecyclerView) findViewById(R.id.rvPokemons);
        PokeAdapter = new PokeAdapter(this);
        recyclerView.setAdapter(PokeAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager LayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        MediaPlayer pokemusic = MediaPlayer.create(getApplication(), R.raw.poke8bit);
        playmusic(pokemusic);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        carregar = true;
        offset = 0;
        PokeDados(offset);
    }

        private void PokeDados ( int offset){
            PokeJSON service = retrofit.create(PokeJSON.class);
            Call<PokeResults> PokeResultsCall = service.ChamarListaPokemon(150, offset);

            PokeResultsCall.enqueue(new Callback<PokeResults>() {
                @Override
                public void onResponse(Call<PokeResults> call, Response<PokeResults> response) {
                    carregar = true;
                    if (response.isSuccessful()) {
                        PokeResults pokeResposta = response.body();
                        ArrayList<Pokemon> listaPokemon = pokeResposta.getResults();
                        PokeAdapter.ListarPokemons(listaPokemon);
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<PokeResults> call, Throwable t) {
                    carregar = true;
                    Log.e(TAG, " onFailure: " + t.getMessage());
                }
            });
        }
        public void playmusic(MediaPlayer music){
            music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    music.start();
                    music.setLooping(true);
                }
            });
            music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    music.release();
                }
            });
    }
}