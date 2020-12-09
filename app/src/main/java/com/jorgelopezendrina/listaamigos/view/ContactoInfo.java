package com.jorgelopezendrina.listaamigos.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

/**
 * Clase ContactoInfo, extiende de fragment.
 *
 * @laViewModel objeto de la clase ViewModel. Es el viewmodel del programa
 * @tvNombreContacto TextView que va a mostrar nombre del contacto.
 * @tvTlfContacto TextView que va a mostrar el teléfono del contacto.
 * @contaco Objeto de la clase contacto.
 *
 *  @author Jorge López Endrina.
 * */
public class ContactoInfo extends Fragment {

    private ViewModelListaAmigos laViewModel;
    private TextView tvNombreContacto;
    private TextView tvTlfContacto;
    private Contacto contacto;

    public ContactoInfo() {
    }

    /**
     * Método que mapea las variables que se visualizan y muestra el contenido de los campos del
     * objeto contacto (contacto de la lista de contactos del teléfono).
     * */
    private void init(View view, NavController navC) {
        tvNombreContacto = view.findViewById(R.id.tvNombreContacto);
        tvTlfContacto = view.findViewById(R.id.tvTlfContacto);
        tvNombreContacto.setText(contacto.getNombre());
        tvTlfContacto.setText(contacto.getTel());
    }

    /**
     * Método que importa un contacto de la lista de contactos del teléfono, en tu lista de amigos
     * */
    private void importar(View view, NavController navC) {
        Button btImportar = view.findViewById(R.id.btImporta);
        btImportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laViewModel.insert(contacto);
                navC.navigate(R.id.ac_importarVolverContacto);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_contacto_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        laViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ViewModelListaAmigos.class);
        contacto = laViewModel.getContacto();
        init(view, navC);
        importar(view, navC);
        volver(view, navC);
    }

    /**
     * Método que te devuelve a la lista de contactos del teléfono.
     * */
    private void volver(View view, NavController navC) {
        Button btVoler = view.findViewById(R.id.btVolver);
        btVoler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navC.navigate(R.id.ac_importarVolverContacto);
            }
        });
    }
}