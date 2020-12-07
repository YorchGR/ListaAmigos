package com.jorgelopezendrina.listaamigos.util;

import com.jorgelopezendrina.listaamigos.view.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase que contiene el pool de hebras
 *
 * @author Jorge López Endrina.
 * */
public class ListaAmigosApplication extends MainActivity {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    public static final ExecutorService threadExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS + 2);
}
