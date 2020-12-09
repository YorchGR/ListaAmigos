package com.jorgelopezendrina.listaamigos.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.jorgelopezendrina.listaamigos.model.entity.Contacto;
import com.jorgelopezendrina.listaamigos.model.entity.LlamadasDeAmigo;

import java.util.List;

/**
 * Interfaz Dao de la clase contacto.
 *
 * @author Jorge López Endrina.
 */

@Dao
public interface ContactosDao {

    @Delete
    void delete(Contacto contacto);

    @Insert
    void insert(Contacto contacto);

    @Update
    void upodate(Contacto contacto);


    @Query("select * from contacto order by nombre")
    LiveData<List<Contacto>> getAllContactos();

    @Query("select c.id, c.nombre, c.tel, c.fecNac, count(l.id) count from contacto c left join llamada l on c.id = l.idContacto group by c.id, c.nombre, c.tel, c.fecNac order by nombre")
    LiveData<List<LlamadasDeAmigo>> getAllLlamadasAmigo();

    @Query("select * from contacto where id = :id")
    Contacto getId(long id);

    @Query("select id from contacto where tel = :tel")
    long getidLlamada(String tel);

}
