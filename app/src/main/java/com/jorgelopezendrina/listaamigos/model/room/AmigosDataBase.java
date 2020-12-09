package com.jorgelopezendrina.listaamigos.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jorgelopezendrina.listaamigos.model.dao.ContactosDao;
import com.jorgelopezendrina.listaamigos.model.dao.LlamadasDao;
import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.model.entity.Llamada;

/**
 * Clase de la base de datos AmigosDataBase
 *
 * @author Jorge López Endrina.
 * */

@Database(entities = {Contacto.class, Llamada.class}, version = 1, exportSchema = false)
public abstract class AmigosDataBase extends RoomDatabase {

    public abstract ContactosDao getContactosDao();
    public abstract LlamadasDao getLlamadasDao();

    private static volatile AmigosDataBase INSTANCE;

    public static AmigosDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AmigosDataBase.class, "listaAmigos.sqlite").build();
        }
        return INSTANCE;
    }
}
