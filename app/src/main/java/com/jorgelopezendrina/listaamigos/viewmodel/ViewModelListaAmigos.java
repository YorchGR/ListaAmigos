package com.jorgelopezendrina.listaamigos.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jorgelopezendrina.listaamigos.model.RepositorioAmigos;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.model.entity.Llamada;
import com.jorgelopezendrina.listaamigos.model.entity.LlamadasDeAmigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ViewModelListaAmigos. Esta clase extiende de viewmodel. Actua como el "repositorio" de la
 * aplicación y es el intermediario con el repositorio. La clase repositorio es el repositorio de
 * la base de datos.
 *
 * @author Jorge López Endrina.
 * @contaco Objeto de la clase contacto.
 * @repositorioAmigos objeto de la clase repositorioAmigos.
 * @listaContactos objeto list que contiene objetos de la clase contacto
 * @tlf variable String que va a contener el teléfono
 * @contador variable long que va a contener el número de veces que un amigo en concreto nos ha
 * llamado
 */

public class ViewModelListaAmigos extends AndroidViewModel {

    private Contacto contacto;
    private RepositorioAmigos repositorioAmigos;
    private List<Contacto> listaContactos = new ArrayList<>();
    private static String tlf;
    private long contador;

    public void delete(Contacto contacto) {
        repositorioAmigos.delete(contacto);
    }

    public Contacto getContacto() {
        return contacto;
    }

    public long getContador() {
        return contador;
    }

    public void getId(long id) {
        repositorioAmigos.getId(id);
    }

    public LiveData<List<Contacto>> getListaContactosLive() {
        return repositorioAmigos.getListaContactosLive();
    }

    public List<Contacto> getListaContactos(Context context) {
        return repositorioAmigos.getListaContactosAux(context);
    }

    public LiveData<List<LlamadasDeAmigo>> getListaContadorLlamadas() {
        return repositorioAmigos.getListaContadorLlamadas();
    }

    public LiveData<List<Llamada>> getListaLlamadasLive() {
        return repositorioAmigos.getListaLlamadasLive();
    }

    public void getNum(String numtlf) {
        tlf = numtlf;
    }

    public void insert(Contacto contacto) {
        repositorioAmigos.insert(contacto);
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void setContador(long contador) {
        this.contador = contador;
    }

    public void setListaContactos(Contacto contacto) {
        listaContactos.add(contacto);
    }

    public void update(Contacto contacto) {
        repositorioAmigos.update(contacto);
    }

    public ViewModelListaAmigos(@NonNull Application application) {
        super(application);
        repositorioAmigos = new RepositorioAmigos(application);
    }


}
