package com.jorgelopezendrina.listaamigos.view;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.mode.entity.Contacto;
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
    private List<Contacto> listaContactos;
    private  ViewModelListaAmigos laViewModel;

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

    /**
     * Método que mediante una proyección y un cursor, va recuperando los contactos de la lista de
     * contactos interna del teléfono, para crear objetos de la clase propia, contacto y enviarlos
     * al viewmodel de la clase, donde se almacenarán en un array de objetos de la misma clase. Al
     * acabar de recorrer los contactos internos, recoge la lista del viewmodel y se la da al método
     * abrirRecicler, que mostrará la lista de contactos.
     * */
    public void mostrarAgenda(View view, NavController navC) {
        String nombre, tlfn;
        Contacto contacto;
        String[] proyeccion = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String seleccion = ContactsContract.Data.MIMETYPE + "= '" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String orden = ContactsContract.Data.DISPLAY_NAME + " ASC";
        Cursor micursor = getContext().getContentResolver().query(ContactsContract.Data.CONTENT_URI, proyeccion, seleccion, null, orden);

        while (micursor.moveToNext()) {
            nombre = micursor.getString(0);
            tlfn = micursor.getString(1);
            contacto = new Contacto(nombre, tlfn, null);
            laViewModel.setListaContactos(contacto);
        }
        micursor.close();
        listaContactos = laViewModel.getListaContactos();
        abrirRecicler(view, navC,listaContactos);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_lista_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        laViewModel = new ViewModelProvider(this).get(ViewModelListaAmigos.class);
        mostrarAgenda(view, navC);
    }
}