package com.jorgelopezendrina.listaamigos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.view.adapter.RvAdaptarContactos;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

import java.util.List;

/**
 * Clase ListaContactos, extiende de fragment.
 *
 * @laViewModel objeto de la clase ViewModel. Es el viewmodel del programa
 * @listaContactos objeto list que contiene objetos de la clase contacto
 *
 *  @author Jorge López Endrina.
 * */
public class ListaContactos extends Fragment {

    /**
     * Método que crea un adaptador e inicializa el recycler.
     * */
    private void abrirRecicler(View view, NavController navC,List<Contacto> listaContactos) {
        RecyclerView mi_recycler = view.findViewById(R.id.recyclerViewContactos);
        RvAdaptarContactos adaptador = new RvAdaptarContactos(navC,getActivity(),listaContactos);
        mi_recycler.setAdapter(adaptador);
        mi_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public ListaContactos() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_lista_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        ViewModelListaAmigos laViewModel = new ViewModelProvider(this).get(ViewModelListaAmigos.class);
        List<Contacto> listaContactos = laViewModel.getListaContactos(getContext());
        abrirRecicler(view, navC,listaContactos);
    }
}