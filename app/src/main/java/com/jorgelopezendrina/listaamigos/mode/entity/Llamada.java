package com.jorgelopezendrina.listaamigos.mode.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Clase Pojo de tipo Llamada. Esta clase corresponderá a la tabla Llamada en la base de datos.
 * Contiene los campos:
 *
 * @id id autogenerada para cada llamada.
 * @idContacto id del contacto que realiza la llamada.
 * @fechaLlamada fecha de la llamada
 *
 * @author Jorge López Endrina.
 * */

@Entity(tableName = "Llamada")
public class Llamada {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "idContacto")
    private long idContacto;

    @NonNull
    @ColumnInfo(name = "fechaLlamada")
    private String fechaLlamada;

    public Llamada(long idContacto, @NonNull String fechaLlamada) {
        this.idContacto = idContacto;
        this.fechaLlamada = fechaLlamada;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
    }

    @NonNull
    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(@NonNull String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "id=" + id +
                ", idContacto=" + idContacto +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                '}';
    }
}
