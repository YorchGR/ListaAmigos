package com.jorgelopezendrina.listaamigos.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.model.entity.LlamadasDeAmigo;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

import java.util.List;

/**
 * Clase RvAdaptarAmigos que extiende de recyclerViewAdapter. Contiene el adaptador del recyclerView
 * de la lista de amigos
 *
 * @listaContactos objeto list del tipo LlamadasDeAmigo
 * @navCAux objeto NavController, para poder navegar entre fragmentos
 * @activity Activity de la aplicación
 * @laViewModel objeto ViewModel.
 *
 * @author Jorge López Endrina.
 * */

public class RvAdaptarAmigos extends RecyclerView.Adapter<RvAdaptarAmigos.ViewHolder> {

    private List<LlamadasDeAmigo>  listaContactos;
    private NavController navCAux;
    private Activity activity;
    private ViewModelListaAmigos laViewModel;

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amigo, parent, false);
        laViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModelListaAmigos.class);
        ViewHolder holder = new ViewHolder(vista);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvnombreAmigo.setText(listaContactos.get(position).getContacto().getNombre());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto = listaContactos.get(position).getContacto();
                laViewModel.setContador(listaContactos.get(position).getCount());
                laViewModel.setContacto(contacto);
                navCAux.navigate(R.id.ac_verAmigo);
            }
        });
    }

    public RvAdaptarAmigos(List<LlamadasDeAmigo> listaContactos, NavController navCAux, Activity activity) {
        this.listaContactos = listaContactos;
        this.navCAux = navCAux;
        this.activity = activity;
    }

    /**
     * Clase interna ViewHolder que extiende de RecyclerView.
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnombreAmigo;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnombreAmigo = itemView.findViewById(R.id.tvnombreAmigo);
            parentLayout = itemView.findViewById(R.id.constraintListaAmigos);
        }
    }
}
