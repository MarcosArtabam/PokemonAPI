package com.app.PokemonAPI;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.PokeViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokeAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public PokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_item, parent, false);
        return new PokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokeViewHolder holder, int position) {

        Pokemon p = dataset.get(position);
        holder.tvNamePoke.setText(p.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNum()+".png")
                .centerCrop()
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagePoke);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void ListarPokemons(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class PokeViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImagePoke;
        private TextView tvNamePoke;

        public PokeViewHolder(View itemView) {
            super(itemView);

            ivImagePoke = (ImageView) itemView.findViewById(R.id.ivImagePoke);
            tvNamePoke = (TextView) itemView.findViewById(R.id.tvNamePoke);
        }
    }
}
