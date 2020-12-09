package com.jorgelopezendrina.listaamigos.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Llamada;

import java.util.List;

/**
 * Clase RvAdaptarLlamadas que extiende de recyclerViewAdapter. Contiene el adaptador del recyclerView
 * de la lista de llamadas
 *
 * @listaLlamadasAmigos objeto list del tipo Llamada
 *
 * @author Jorge López Endrina.
 * */

public class RvAdaptarLlamadas extends RecyclerView.Adapter<RvAdaptarLlamadas.ViewHolder> {

    private List<Llamada> listaLlamadasAmigos;


    @Override
    public int getItemCount() {
        return listaLlamadasAmigos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_llamada, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvIdAmigo.setText(String.valueOf(listaLlamadasAmigos.get(position).getIdContacto()));
        holder.tvFechLlamada.setText(listaLlamadasAmigos.get(position).getFechaLlamada());
    }

    public RvAdaptarLlamadas(List<Llamada> listaLlamadasAmigos) {
        this.listaLlamadasAmigos = listaLlamadasAmigos;
    }

    /**
     * Clase interna ViewHolder que extiende de RecyclerView.
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdAmigo;
        TextView  tvFechLlamada;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdAmigo = itemView.findViewById(R.id.tvIdAmigo);
            tvFechLlamada = itemView.findViewById(R.id.tvFechLlamada);
            parentLayout = itemView.findViewById(R.id.costraintListaLlamadas);
        }
    }
}
