package com.jorgelopezendrina.listaamigos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Llamada;
import com.jorgelopezendrina.listaamigos.view.adapter.RvAdaptarLlamadas;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ListaLlamadas, extiende de fragment.
 *
 * @laViewModel objeto de la clase ViewModel. Es el viewmodel del programa
 * @listaLlamadas objeto list que contiene objetos de la clase Llamada
 *
 *  @author Jorge López Endrina.
 * */
public class ListaLlamadas extends Fragment {

    private ViewModelListaAmigos laViewModel;
    private List<Llamada> listaLlamadas = new ArrayList<>();

    /**
     * Método que crea un adaptador e inicializa el recycler.
     * */
    private RvAdaptarLlamadas abrirRecycler(@NonNull View view) {
        RecyclerView mi_recycler = view.findViewById(R.id.recyclerViewlLlamadas);
        RvAdaptarLlamadas adaptador = new RvAdaptarLlamadas(listaLlamadas);
        mi_recycler.setAdapter(adaptador);
        mi_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return adaptador;
    }

    /**
     * Métoodo que, mediante el uso de un LiveData al que se le llama desde el viewModel, mantiene
     * el array de llamadasLlamadas (es decir, llamadas de amigos), actualizado
     * */
    private void escuchaListaLlamadas(RvAdaptarLlamadas adaptador) {
        laViewModel.getListaLlamadasLive().observe(getActivity(), new Observer<List<Llamada>>() {
            @Override
            public void onChanged(List<Llamada> llamadas) {
                listaLlamadas.clear();
                listaLlamadas.addAll(llamadas);
                adaptador.notifyDataSetChanged();
            }
        });
    }

    public ListaLlamadas() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_lista_llamadas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        laViewModel = new ViewModelProvider(getActivity()).get(ViewModelListaAmigos.class);
        NavController navC = Navigation.findNavController(view);
        RvAdaptarLlamadas adaptador = abrirRecycler(view);
        escuchaListaLlamadas(adaptador);
        volver(view, navC);
    }
    /**
     * Método que te devuelve a la lista de amigos.
     * */
    private void volver(@NonNull View view, NavController navC) {
        Button btVolver = view.findViewById(R.id.btVolverAmigos);
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navC.navigate(R.id.ac_volverListaLlamadas);
            }
        });
    }
}