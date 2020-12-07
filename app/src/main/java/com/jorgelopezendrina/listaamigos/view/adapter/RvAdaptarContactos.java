package com.jorgelopezendrina.listaamigos.view.adapter;

import android.annotation.SuppressLint;
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
import com.jorgelopezendrina.listaamigos.mode.entity.Contacto;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

import java.util.List;

/**
 * Clase RvAdaptarContactos que extiende de recyclerViewAdapter. Contiene el adaptador del recyclerView
 * de la lista de contactos
 *
 * @listaContactos objeto list del tipo Contacto
 * @navCAux objeto NavController, para poder navegar entre fragmentos
 * @activity Activity de la aplicación
 * @laViewModel objeto ViewModel.
 *
 * @author Jorge López Endrina.
 * */
public class RvAdaptarContactos extends RecyclerView.Adapter<RvAdaptarContactos.ViewHolder> {

    private List<Contacto> listaContactos;
    private NavController navCAux;
    private ViewModelListaAmigos laViewModel;
    private Activity activity;

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        ViewHolder holder = new ViewHolder(vista);
        laViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ViewModelListaAmigos.class);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNombre.setText(listaContactos.get(position).getNombre());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contacto;
                contacto = listaContactos.get(position);
                laViewModel.setContacto(contacto);
                navCAux.navigate(R.id.ac_verContacto);
            }
        });
    }

    public RvAdaptarContactos(NavController navC, Activity activity, List<Contacto> listaContactos) {
        this.navCAux = navC;
        this.activity =activity;
        this.listaContactos = listaContactos;
    }

    /**
     * Clase interna ViewHolder que extiende de RecyclerView.
     * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreContactoLista);
            parentLayout = itemView.findViewById(R.id.constraintContactos);
        }
    }
}
