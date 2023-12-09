package com.example.tweeter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tweet_RecyclerViewAdapter extends RecyclerView.Adapter<Tweet_RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Tweet> listaTweets;
    private añadirTweets listener;
    public boolean formProfile_page;

    public Tweet_RecyclerViewAdapter(Context context, ArrayList<Tweet> listaTweets) {
        this.context = context;
        this.listaTweets = listaTweets;
    }

    public Tweet_RecyclerViewAdapter(Context context, ArrayList<Tweet> listaTweets, boolean formProfile_page) {
        this.context = context;
        this.listaTweets = listaTweets;
        this.formProfile_page = formProfile_page;
    }

    @NonNull
    @Override
    public Tweet_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Assignar ("inflate") les vistes dels registres al RecyclerView
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recicler_view_row, parent, false);
        return new Tweet_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Tweet tweet = listaTweets.get(position);
        holder.idView.setText(tweet.getIdUsuario() + "");
        holder.imagenPerfil.setImageResource(tweet.getImagenPerfil());
        holder.nombreUsuario.setText(tweet.getNombre());
        holder.cuadroTexto.setText(tweet.getContenido());
        holder.fechaTweetView.setText(tweet.getFecha().toString());
        holder.botonLike.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                buttonView.setText("1");
            } else {
                buttonView.setText("0");
            }
        });

        if (formProfile_page) {
            holder.itemView.setOnLongClickListener(view -> {
                listaTweets.remove(position);

                // Sin "notifyDataSetChanged()" la lista no se actualizara.
                notifyDataSetChanged();
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaTweets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Declarar vistes del nostre "Tweet"
        private TextView idView;
        private ImageView imagenPerfil;
        private TextView nombreUsuario;
        private TextView cuadroTexto;
        private TextView fechaTweetView;
        private CheckBox botonLike;
        private Button botonAñadirTweet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            idView = itemView.findViewById(R.id.id);
            imagenPerfil = itemView.findViewById(R.id.homeImagenCabezeraView);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            cuadroTexto = itemView.findViewById(R.id.cuadroTexto);
            fechaTweetView = itemView.findViewById(R.id.fechaTweetView);
            botonLike = itemView.findViewById(R.id.like);
        }
    }

    public ArrayList<Tweet> getListaTweetsRecyclerView() {
        return listaTweets;
    }
}
