package com.jorgelopezendrina.listaamigos.mode.entity;

import androidx.room.Embedded;

/**
 * Clase Pojo de tipo LlamadasDeAmigo. Esta clase intermedia contiene la clase embebida contacto.
 *
 * @contacto contacto embebido de la clase contacto.
 * @count long que contiene el número de llamadas.
 *
 * @author Jorge López Endrina.
 * */

public class LlamadasDeAmigo {

    @Embedded
    private Contacto contacto;
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "LlamadasAmigo{" +
                "contacto=" + contacto +
                ", count=" + count +
                '}';
    }
}

