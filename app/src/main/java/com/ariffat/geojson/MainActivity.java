package com.ariffat.geojson;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.ariffat.geojson.map.MapActivity;

/**
 * Created by ARiffat on 8/22/2017.
 */

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_STORAGE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();
    }
    public void openOsm(View v) {
        openMap(MapActivity.class);
    }
    public void openMap(Class<?> activity) {
        if (activity != null) {
            Intent mapsIntent = new Intent(this, activity);
             startActivity(mapsIntent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.geojson_opener_unable_to_read, Toast.LENGTH_LONG).show();
            finish();
        }
    }


    ////////////////////////////PERMISSION-START////////////////////////////////////////

    public void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                Toast.makeText(getApplicationContext(), R.string.activity_main_ask_permission, Toast.LENGTH_LONG).show();
                askPermissions();
            } else {
                askPermissions();
            }
        }
    }
    private void askPermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_STORAGE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, R.string.permission_granted, Toast.LENGTH_SHORT).show();
                 } else {
                    finish();
                }
                return;
            }
        }
    }
////////////////////////////PERMISSION-END////////////////////////////////////////


}
