package com.jorgelopezendrina.listaamigos.mode;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import com.jorgelopezendrina.listaamigos.mode.dao.ContactosDao;
import com.jorgelopezendrina.listaamigos.mode.dao.LlamadasDao;
import com.jorgelopezendrina.listaamigos.mode.entity.Contacto;
import com.jorgelopezendrina.listaamigos.mode.entity.Llamada;
import com.jorgelopezendrina.listaamigos.mode.entity.LlamadasDeAmigo;
import com.jorgelopezendrina.listaamigos.mode.room.AmigosDataBase;
import com.jorgelopezendrina.listaamigos.util.ListaAmigosApplication;
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

    public LiveData<List<Contacto>> getListaContactosLive() {
        return listaContactosLive;
    }

    public LiveData<List<LlamadasDeAmigo>> getListaContadorLlamadas() {
        return listaContadorLlamadas;
    }

    public LiveData<List<Llamada>> getListaLlamadasLive() {
        return listaLlamadasLive;
    }

    public void insertaLlamadaEntrate(String tlf, String fecha){
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Llamada llamadaAux = new Llamada(contactosDao.getidLlamada(tlf),fecha);
                if (llamadaAux.getIdContacto() != 0 ){
                    LlamadasDao.insert(llamadaAux);
                }
            }
        });
    }

    public void insert(Contacto contactoAux) {
        ListaAmigosApplication.threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    contactosDao.insert(contactoAux);
                }catch(Exception e){
                    e.getMessage();
                }
            }
        });
    }

    public RepositorioAmigos(Application context) {
        AmigosDb = AmigosDataBase.getDatabase(context);
        contactosDao = AmigosDb.getContactosDao();
        LlamadasDao = AmigosDb.getLlamadasDao();
        listaContactosLive = contactosDao.getAllContactos();
        listaLlamadasLive = LlamadasDao.getAllLlamadas();
        listaContadorLlamadas = contactosDao.getAllLlamadasAmigo();
    }

    /*Utilizo un segundo constructora porque no me lo coge en cuando lo creo en el broadcast reciver*/
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
