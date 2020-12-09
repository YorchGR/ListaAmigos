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
import com.jorgelopezendrina.listaamigos.model.entity.LlamadasDeAmigo;
import com.jorgelopezendrina.listaamigos.view.adapter.RvAdaptarAmigos;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ListaAmigos, extiende de fragment.
 *
 * @laViewModel objeto de la clase ViewModel. Es el viewmodel del programa
 * @listaLlamadasAmigo objeto list que contiene objetos de la clase LlamadasDeAmigo
 *
 *  @author Jorge López Endrina.
 * */

public class ListaAmigos extends Fragment {

    private ViewModelListaAmigos laViewModel;
    private List<LlamadasDeAmigo> listaLlamadasAmigo = new ArrayList<>();

    /**
     * Método que crea un adaptador e inicializa el recycler.
     * */
    private RvAdaptarAmigos abrirRecycler(@NonNull View view, NavController navC) {
        RecyclerView mi_recycler = view.findViewById(R.id.recyclerViewAmigos);
        RvAdaptarAmigos adaptador = new RvAdaptarAmigos(listaLlamadasAmigo, navC, getActivity());
        mi_recycler.setAdapter(adaptador);
        mi_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return adaptador;
    }

    /**
     * Métoodo que, mediante el uso de un LiveData al que se le llama desde el viewModel, mantiene
     * el array de llamadasDeAmigo (es decir, de amigos), actualizado
     * */
    private void escuchaListaAmigos(RvAdaptarAmigos adaptador) {
        laViewModel.getListaContadorLlamadas().observe(getActivity(), new Observer<List<LlamadasDeAmigo>>() {
            @Override
            public void onChanged(List<LlamadasDeAmigo> llamadasDeAmigos) {
                listaLlamadasAmigo.clear();
                listaLlamadasAmigo.addAll(llamadasDeAmigos);
                adaptador.notifyDataSetChanged();
            }
        });
    }

    /**
     * Método que navega a la lista de llamadas de amigos.
     * */
    private void iraVerLlamadas(@NonNull View view, NavController navC) {
        Button btVerLlamadas = view.findViewById(R.id.btVerLlamadas);
        btVerLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navC.navigate(R.id.ac_verLlamadas);
            }
        });
    }

    /**
     * Método que navega a la lista de contactos del teléfono.
     * */
    private void importarAmigos(@NonNull View view, NavController navC) {
        Button btimportAmigos = view.findViewById(R.id.btimportAmigos);
        btimportAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navC.navigate(R.id.ac_importarAmigos);
            }
        });
    }

    public ListaAmigos() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_lista_amigos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        laViewModel = new ViewModelProvider(getActivity()).get(ViewModelListaAmigos.class);
        NavController navC = Navigation.findNavController(view);
        RvAdaptarAmigos adaptador = abrirRecycler(view, navC);
        escuchaListaAmigos(adaptador);
        importarAmigos(view, navC);
        iraVerLlamadas(view, navC);
    }
}