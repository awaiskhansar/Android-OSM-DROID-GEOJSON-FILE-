package com.ariffat.geojson.map;

import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.ariffat.geojson.R;
import com.ariffat.geojson.map.MapBaseActivity;
import com.nextome.geojsonify.GeoJsonify;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;

import java.io.IOException;


public class MapActivity extends MapBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

         setUpMap();

    }

    private void setUpMap() {
        MapView map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.getController().setZoom(10);
        map.setMaxZoomLevel(null);

        try {
            GeoJsonify.geoJsonifyMap(map, this.getJsonUris(), this.getJsonColors(), this.getContext());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this.getContext(), R.string.geojson_opener_unable_to_read, Toast.LENGTH_SHORT).show();
        }


        //        map.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
//
//            public boolean singleTapConfirmedHelper(GeoPoint p) {
//                Log.e("MapView", "normal click");
//                return true;
//            }
//
//            public boolean longPressHelper(GeoPoint p) {
//                Log.e("MapView", "long click");
//                return false;
//            }
//        }));

    }


    @Override
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
    }

}
