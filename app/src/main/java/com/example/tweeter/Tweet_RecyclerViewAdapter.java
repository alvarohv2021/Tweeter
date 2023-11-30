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

public class Tweet_RecyclerViewAdapter extends RecyclerView.Adapter<Tweet_RecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private GeneraciónListaTweets generaciónListaTweets;
    private añadirTweets listener;

    public Tweet_RecyclerViewAdapter(Context context, GeneraciónListaTweets generaciónListaTweets) {
        this.context = context;
        this.generaciónListaTweets = generaciónListaTweets;
        this.listener = listener;
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
        Tweet tweet = generaciónListaTweets.getTwit(position);
        holder.idView.setText(tweet.getIdUsuario()+"");
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

    }

    @Override
    public int getItemCount() {
        return generaciónListaTweets.getSize();
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
            imagenPerfil = itemView.findViewById(R.id.imageView);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            cuadroTexto = itemView.findViewById(R.id.cuadroTexto);
            fechaTweetView = itemView.findViewById(R.id.fechaTweetView);
            botonLike = itemView.findViewById(R.id.like);

            itemView.setOnLongClickListener(view -> {
                generaciónListaTweets.remove(this.getAdapterPosition());

                // Sin "notifyDataSetChanged()" la lista no se actualizara.
                notifyDataSetChanged();
                return true;
            });
        }
    }
}
