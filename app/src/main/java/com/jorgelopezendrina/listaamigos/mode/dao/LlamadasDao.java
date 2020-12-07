package com.jorgelopezendrina.listaamigos.mode.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jorgelopezendrina.listaamigos.mode.entity.Contacto;
import com.jorgelopezendrina.listaamigos.mode.entity.Llamada;

import java.util.List;

/**
 * Interfaz Dao de la clase Llamada.
 *
 * @author Jorge López Endrina.
 */

@Dao
public interface LlamadasDao {

    @Query("select * from Llamada order by fechaLlamada")
    LiveData<List<Llamada>> getAllLlamadas();

    @Insert
    void insert(Llamada llamada);
}
