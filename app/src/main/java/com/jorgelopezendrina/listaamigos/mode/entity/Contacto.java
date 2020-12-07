package com.jorgelopezendrina.listaamigos.mode.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Clase Pojo de tipo Contacto. Esta clase corresponderá a la tabla contacto en la base de datos.
 * Contiene los campos:
 *
 * @id id autogenerada para cada contacto.
 * @nombre nombre del contacto.
 * @tel telefono del contacto
 * @fecNac fecha de nacimiento del contacto
 *
 * @author Jorge López Endrina.
 * */

/*
* Con  "indices = {@Index(value = {"tel"}, unique = true" hacémos único el campo teléfono y así no
* repite la entrada de amigos, al importarlos de contactos.
* */
@Entity(tableName = "Contacto", indices = {@Index(value = {"tel"}, unique = true)})
public class Contacto {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "tel")
    private String tel;

    @Nullable
    @ColumnInfo(name = "fecNac")
    private String fecNac;

    public Contacto(@NonNull String nombre, @NonNull String tel, @Nullable String fecNac) {
        this.nombre = nombre;
        this.tel = tel;
        this.fecNac = fecNac;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getTel() {
        return tel;
    }

    public void setTel(@NonNull String tel) {
        this.tel = tel;
    }

    @NonNull
    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(@NonNull String fecNac) {
        this.fecNac = fecNac;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tel='" + tel + '\'' +
                ", fecNac='" + fecNac + '\'' +
                '}';
    }
}
