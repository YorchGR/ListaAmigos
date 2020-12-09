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
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jorgelopezendrina.listaamigos.R;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.util.ValidaDatos;
import com.jorgelopezendrina.listaamigos.viewmodel.ViewModelListaAmigos;

/**
 * Clase AmigoInfo, extiende de fragment.
 *
 * @laViewModel objeto de la clase ViewModel. Es el viewmodel del programa
 * @etNombreAmigo EditText que va a mostrar el nombre del amigo. Campo editable
 * @etCumpleanios EditText que va a mostrar el cumpleaños del amigo. Campo editable
 * @numLlamadas TextView que va a mostrar el número de llamadas recibidas por ese amigo.
 * @tvTelefonoAmigo TextView que va a mostrar el teléfono del amigo.
 * @contaco Objeto de la clase contacto.
 *
 *  @author Jorge López Endrina.
 * */
public class AmigoInfo extends Fragment {

    private ViewModelListaAmigos laViewModel;
    private EditText etNombreAmigo;
    private EditText etCumpleanios;
    private TextView numLlamadas;
    private TextView tvTelefonoAmigo;
    private Contacto contacto;


    public AmigoInfo() {
    }

    /**
     * Método que llama a borrar un contacto y que vuelve al fragmento de la lista de maigos.
     * */
    private void borrar(@NonNull View view, NavController navC) {
        Button btBorrar = view.findViewById(R.id.btBorrar);
        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laViewModel.delete(contacto);
                navC.navigate(R.id.ac_volverEditarBorrarAmigo);
            }
        });
    }

    /**
     * Método que llama a edita un contacto, recogiendo y validando sus campos, para después volver
     * a la lista de amigos.
     * */
    private void editar(@NonNull View view, NavController navC) {
        Button btEditar = view.findViewById(R.id.btEditar);
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacto.setNombre(String.valueOf(etNombreAmigo.getText()));
                if (ValidaDatos.validaFecha(String.valueOf(etCumpleanios.getText()))){
                    contacto.setFecNac(String.valueOf(etCumpleanios.getText()));
                    laViewModel.update(contacto);
                    navC.navigate(R.id.ac_volverEditarBorrarAmigo);
                }else{
                    Snackbar.make(view, "Fecha mal introducida", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Método que mapea las variables que se visualizan y muestra el contenido de los campos del
     * objeto contacto (que es un amigo).
     * */
    private void init(@NonNull View view) {
        etNombreAmigo = view.findViewById(R.id.etNombreAmigo);
        etCumpleanios = view.findViewById(R.id.etCumplueanios);
        numLlamadas = view.findViewById(R.id.tvNumLlamadasAmigo);
        tvTelefonoAmigo = view.findViewById(R.id.tvtelefonoAmigo);
        numLlamadas.setText(String.valueOf(laViewModel.getContador()));
        tvTelefonoAmigo.setText(contacto.getTel());
        etNombreAmigo.setText(contacto.getNombre());
        etCumpleanios.setText(contacto.getFecNac());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_amigo_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navC = Navigation.findNavController(view);
        laViewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity()).get(ViewModelListaAmigos.class);
        contacto = laViewModel.getContacto();
        init(view);
        editar(view, navC);
        borrar(view, navC);
        volver(view, navC);
    }

    /**
     * Método que te devuelve a la lista de amigos.
     * */
    private void volver(@NonNull View view, NavController navC) {
        Button btVolver = view.findViewById(R.id.btvolverAmigos);
        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navC.navigate(R.id.ac_volverEditarBorrarAmigo);
            }
        });
    }
}