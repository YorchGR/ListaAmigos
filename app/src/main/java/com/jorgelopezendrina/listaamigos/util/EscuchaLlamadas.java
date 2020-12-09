package com.jorgelopezendrina.listaamigos.util;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;

import com.jorgelopezendrina.listaamigos.model.RepositorioAmigos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Clase que extiende de BroadcastReciver y que se encarga de escuchar las llamadas entrantes, y
 * mediante el uso de diferentes métodos, recoge la información necesaria para que se pueda crear un
 * objeto de la clase llamada. Contiene un hilo, que se encarga de recabar la información y de
 * mandarla para que se guarde en diferentes ficheros.
 *
 * @repositorioAmigos objeto de la clase repositorioAmigos.
 *
 * @author Jorge López Endrina.
 */

public class EscuchaLlamadas extends BroadcastReceiver {

    private RepositorioAmigos repositorioAmigos;

    /**
     * Método encargado de conseguir la fecha del sistema.
     */
    @SuppressLint("SimpleDateFormat")
    private String consigueFecha() {
        String fecha;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy; MM; dd; HH; mm; ss; ");
        formato.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        fecha = formato.format(cal.getTime());
        return fecha;
    }

    /**
     * Método encargado de extraer de una llamada entrante, el número de teléfono.
     */
    private String consigueNum(Intent intent) {
        String numTlf = null;
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String estadoTlf = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (estadoTlf.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numTlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            }
        }
        return numTlf;
    }

    /**
     * Clase privada que extiende de Thread, encargada de conseguir los distintos datos que conforman
     * un objeto de la clase llamada y de mandar los datos al repositorio para poder crear un objeto
     * de dicha clase. Se usa el repositorio directamente, por la imposibilidad de hacerlo mediante
     * el viewmodel de la clase.
     */
    private class Hilo extends Thread {
        private Context context;
        private Intent intent;
        String numero, fecha;

        public Hilo(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void run() {
            numero = consigueNum(intent);
            fecha = consigueFecha();
            repositorioAmigos.insertaLlamadaEntrate(numero, fecha);
        }
    }

    /**
     * Método onRecibe de la clase BroadcastReciver
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        repositorioAmigos = new RepositorioAmigos(context);
        Hilo h1 = new Hilo(context, intent);
        h1.start();
    }
}
