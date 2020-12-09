package com.jorgelopezendrina.listaamigos.model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import androidx.lifecycle.LiveData;

import com.jorgelopezendrina.listaamigos.model.dao.ContactosDao;
import com.jorgelopezendrina.listaamigos.model.dao.LlamadasDao;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.model.entity.Llamada;
import com.jorgelopezendrina.listaamigos.model.entity.LlamadasDeAmigo;
import com.jorgelopezendrina.listaamigos.model.room.AmigosDataBase;
import com.jorgelopezendrina.listaamigos.util.ListaAmigosApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase RepositorioAmigos. Clase repositorio de la base de datos AmigosDataBase. Contine:
 *
 * @amigosDb objeto de la base de datos
 * @ContactosDao objeto de la clase contactosDao
 * @LlamadasDao objeto de la clase LlamadasDao
 * @listaContactosLive objeto LiveData de tipo lista que contendrá objetos de la clase Contacto
 * @listaContadorLlamadas objeto LiveData de tipo lista que contendrá objetos de la clase LlamadasDeAmigo
 * @listaLlamadasLive objeto LiveData de tipo lista que contendrá objetos de la clase Llamada
 *
 * @author Jorge López Endrina.
 * */
public class RepositorioAmigos {

    private AmigosDataBase AmigosDb;
    private ContactosDao contactosDao;
    private LlamadasDao LlamadasDao;
    private LiveData<List<Contacto>> listaContactosLive;
    private LiveData<List<LlamadasDeAmigo>> listaContadorLlamadas;
    private LiveData<List<Llamada>> listaLlamadasLive;
    private List<Contacto> listaContactos = new ArrayList<>();

    public void delete(Contacto contacto) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactosDao.delete(contacto);
            }
        });
    }

    public void getId(long id) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactosDao.getId(id);
            }
        });
    }

    public List<Contacto> getListaContactosAux(Context context) {
        mostrarAgenda(context);
        return listaContactos;
    }

    public void setListaContactosAux(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public LiveData<List<Contacto>> getListaContactosLive() {
        return listaContactosLive;
    }

    public LiveData<List<LlamadasDeAmigo>> getListaContadorLlamadas() {
        return listaContadorLlamadas;
    }

    public LiveData<List<Llamada>> getListaLlamadasLive() {
        return listaLlamadasLive;
    }

    public void insertaLlamadaEntrate(String tlf, String fecha) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Llamada llamadaAux = new Llamada(contactosDao.getidLlamada(tlf), fecha);
                if (llamadaAux.getIdContacto() != 0) {
                    LlamadasDao.insert(llamadaAux);
                }
            }
        });
    }

    public void insert(Contacto contactoAux) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    contactosDao.insert(contactoAux);
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    /**
     * Método que mediante una proyección y un cursor, va recuperando los contactos de la lista de
     * contactos interna del teléfono, para crear objetos de la clase propia, contacto y enviarlos
     * al viewmodel de la clase, donde se almacenarán en un array de objetos de la misma clase. Al
     * acabar de recorrer los contactos internos, recoge la lista del viewmodel y se la da al método
     * abrirRecicler, que mostrará la lista de contactos.
     */
    private void mostrarAgenda(Context context) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String nombre, tlfn;
                Contacto contacto;
                try {
                    String[] proyeccion = new String[]{ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                    String seleccion = ContactsContract.Data.MIMETYPE + "= '" +
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " +
                            ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
                    String orden = ContactsContract.Data.DISPLAY_NAME + " ASC";
                    Cursor micursor = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, proyeccion, seleccion, null, orden);
                    while (micursor.moveToNext()) {
                        nombre = micursor.getString(0);
                        tlfn = micursor.getString(1);
                        contacto = new Contacto(nombre, tlfn, null);
                        listaContactos.add(contacto);
                    }
                    micursor.close();
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
    }

    public RepositorioAmigos(Context context) {
        AmigosDb = AmigosDataBase.getDatabase(context);
        contactosDao = AmigosDb.getContactosDao();
        LlamadasDao = AmigosDb.getLlamadasDao();
        listaContactosLive = contactosDao.getAllContactos();
        listaLlamadasLive = LlamadasDao.getAllLlamadas();
        listaContadorLlamadas = contactosDao.getAllLlamadasAmigo();
    }

    public void update(Contacto contacto) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                contactosDao.upodate(contacto);
            }
        });
    }
}
