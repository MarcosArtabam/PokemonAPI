package com.app.PokemonAPI;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
        holder.tvNamePoke.setText(p.getName().toUpperCase());
        holder.tvNumPoke.setText("Nº" + p.getNum());

        holder.setItemClickListenner(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(new Intent(PokemonType.KEY_ENABLE_HOME).putExtra("position",position));
            }
        });


                Glide.with(context)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNum() + ".png")
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

    public class PokeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivImagePoke;
        private TextView tvNumPoke;
        private TextView tvNamePoke;

        public void setItemClickListenner(ItemClickListener itemClickListenner) {
            ItemClickListenner = itemClickListenner;
        }

        public ItemClickListener ItemClickListenner;


        public PokeViewHolder(View itemView) {
            super(itemView);

            ivImagePoke = (ImageView) itemView.findViewById(R.id.ivImagePoke);
            tvNumPoke = (TextView) itemView.findViewById(R.id.tvNumPoke);
            tvNamePoke = (TextView) itemView.findViewById(R.id.tvNamePoke);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            ItemClickListenner.onClick(view,getBindingAdapterPosition());
        }
    }
}
