package com.jorgelopezendrina.listaamigos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.jorgelopezendrina.listaamigos.R;

/**
 * Clase MainActivity. Esta clase es la encargada de comprobar los permisos.
 *
 * @author Jorge López Endrina.
 */

public class MainActivity extends AppCompatActivity {

    private static final int CODIGO_PERMISOS = 888;
    private static final String[] PERMISOS_REQUERIDOS = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS};

    /**
     * Método encargado de comprobar si se tienen los permisos requeridos
     */
    @SuppressLint("NewApi")
    private boolean chequearPermisos() {
        int perUno = checkSelfPermission(PERMISOS_REQUERIDOS[0]);
        int perDos = checkSelfPermission(PERMISOS_REQUERIDOS[1]);
        int perTres = checkSelfPermission(PERMISOS_REQUERIDOS[2]);
        return perUno == PackageManager.PERMISSION_GRANTED
                && perDos == PackageManager.PERMISSION_GRANTED
                && perTres == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * Método encargado de mostrar de una u otra manera, el mensaje de petición de permisos.
     */
    private void explicacionDetallada() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.titulo_permiso);
        builder.setMessage(R.string.mensaje_permiso_requerido);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestPermission();
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (chequearPermisos()) {
            } else {
                if (shouldShowRequestPermissionRationale(String.valueOf(PERMISOS_REQUERIDOS))) {
                    explicacionDetallada();
                } else {
                    requestPermission();
                }
            }
        }
    }

    /**
     * Método de comprobación de los permisos requeridos. De no tener todos los permisos, reitera en
     * su petición, saliendo de la aplicación de no tenerlos todos
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISOS:
                if (grantResults.length > 0) {
                    boolean perUno = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean perDos = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean perTres = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    if (perUno && perDos && perTres) {
                        Toast to = (Toast.makeText(getApplicationContext(), "Dispones de todos los permisos necesatios", Toast.LENGTH_SHORT));
                        to.show();
                    } else {
                        explicacionDetallada();
                    }
                }
                break;
        }
    }

    /**
     * Método encargado de pedir los permisos
     */
    @SuppressLint("NewApi")
    private void requestPermission() {
        requestPermissions(PERMISOS_REQUERIDOS, CODIGO_PERMISOS);
    }
}