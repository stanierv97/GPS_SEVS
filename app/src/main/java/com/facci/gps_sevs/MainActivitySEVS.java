package com.facci.gps_sevs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivitySEVS extends AppCompatActivity {

    LocationManager locManager;
    private double latitud;
    private double longitud;
    private double altitud;
    private float velocidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_sevs);
        // inicializar loc manager
        locManager=(LocationManager)getSystemService(LOCATION_SERVICE);
        // OBTENER LA LISTA DE PROVEDEROS
        List<String>listaProviders=locManager.getAllProviders();
        // OBTENER EL PRIMER PROVEDOR
        LocationProvider provider=locManager.getProvider(listaProviders.get(0));
    }
    public void ActualizarLatLongClick(View v){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED){
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }
    private final LocationListener locationListenerGPS =new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitud = location.getLongitude();
            latitud = location.getLatitude();
            altitud = location.getAltitude();
            velocidad =location.getSpeed();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText)findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText)findViewById(R.id.txtLatitud);
                    EditText txtAltitud = (EditText)findViewById(R.id.txtAltitud);
                    EditText txtVelocidad = (EditText)findViewById(R.id.txtVelocidad);
                    txtLatitud.setText(latitud+"");
                    txtLongitud.setText(String.valueOf(longitud));
                    txtAltitud.setText(String.valueOf(altitud));
                    txtVelocidad.setText(String.valueOf(velocidad));
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}

