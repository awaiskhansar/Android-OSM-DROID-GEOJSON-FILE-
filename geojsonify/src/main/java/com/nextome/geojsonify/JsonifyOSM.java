/*
 * Copyright 2017 Nextome S.r.l
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nextome.geojsonify;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import org.json.JSONObject;
import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.bonuspack.kml.KmlFeature;
import org.osmdroid.bonuspack.kml.KmlLineString;
import org.osmdroid.bonuspack.kml.KmlPlacemark;
import org.osmdroid.bonuspack.kml.KmlPoint;
import org.osmdroid.bonuspack.kml.KmlPolygon;
import org.osmdroid.bonuspack.kml.KmlTrack;
import org.osmdroid.bonuspack.kml.Style;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonifyOSM {
    static void geoJsonifyMap(final MapView map, List<Uri> jsonUris, List<Integer> jsonColors, Context context) throws IOException {
        final KmlDocument kmlDocument = new KmlDocument();

        for (int i=0; i<jsonUris.size(); i++) {
            Uri uri = jsonUris.get(i);
            kmlDocument.parseGeoJSON(FileUtils.getStringFromFile(uri, context));
            kmlDocument.mKmlRoot.mName="sdsad";
            kmlDocument.mKmlRoot.mDescription="sdsasadasd";

            //Add styler
            KmlFeature.Styler styler = new MyKmlStyler();


//            Drawable defaultMarker = context.getResources().getDrawable(R.drawable.marker_default);
//            Bitmap defaultBitmap = ((BitmapDrawable) defaultMarker).getBitmap();
//            Style defaultStyle = new Style(defaultBitmap, jsonColors.get(i), 2f, 0x00000000);
//            FolderOverlay geoJsonOverlay = (FolderOverlay) kmlDocument.mKmlRoot.buildOverlay(map, defaultStyle, null, kmlDocument);
//            geoJsonOverlay.setName("ABC");
//            geoJsonOverlay.setDescription("ABC");

            FolderOverlay geoJsonOverlay = (FolderOverlay) kmlDocument.mKmlRoot.buildOverlay(map, null, styler, kmlDocument);

            geoJsonOverlay.setName("sadjkasdjkashdkjashdjk");
             map.getOverlays().add(geoJsonOverlay);


            map.invalidate();
         }


        // Workaround for osmdroid issue
        // See: https://github.com/osmdroid/osmdroid/issues/337
        map.addOnFirstLayoutListener(new MapView.OnFirstLayoutListener() {
            @Override
            public void onFirstLayout(View v, int left, int top, int right, int bottom) {
                BoundingBox boundingBox = kmlDocument.mKmlRoot.getBoundingBox();
                // Yep, it's called 2 times. Another workaround for zoomToBoundingBox.
                // See: https://github.com/osmdroid/osmdroid/issues/236#issuecomment-257061630
                map.zoomToBoundingBox(boundingBox, false);
                map.zoomToBoundingBox(boundingBox, false);
                map.invalidate();
            }
        });
    }




    static class MyKmlStyler implements KmlFeature.Styler {
        @Override
        public void onFeature(Overlay overlay, KmlFeature kmlFeature) {}

        @Override
        public void onPoint(Marker marker, KmlPlacemark kmlPlacemark, KmlPoint kmlPoint) {}

        @Override
        public void onLineString(Polyline polyline, KmlPlacemark kmlPlacemark, KmlLineString kmlLineString) {}

        @Override
        public void onPolygon(Polygon polygon, KmlPlacemark kmlPlacemark, KmlPolygon kmlPolygon) {
            try {
                polygon.setTitle("This is a polygon");
                polygon.setSubDescription(Polygon.class.getCanonicalName());
                polygon.setFillColor(Color.GREEN);
                polygon.setVisible(true);
                polygon.setStrokeColor(Color.BLACK);
                polygon.setStrokeWidth(2f);


 //                polygon.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, mMapView));

                String styleString = kmlPlacemark.getExtendedData("style");
                JSONObject o = new JSONObject(styleString);
                if(o.getBoolean("stroke")) {
                    String colorHash = "#"+Integer.toHexString((int)(o.getDouble("opacity")*255))+o.getString("color").replace("#","");
                    polygon.setStrokeColor(Color.parseColor(colorHash));
                    polygon.setStrokeWidth((float) o.getDouble("weight"));
                }
                if(o.getBoolean("fill")){
                    String colorHash = "#"+Integer.toHexString((int)(o.getDouble("fillOpacity")*255))+o.getString("color").replace("#","");
                    polygon.setFillColor(Color.parseColor(colorHash));
                }
                //Would be great if this method helped me add title
                polygon.setTitle("Tadadada!!!");
            }catch (Exception e){
                e.printStackTrace();}

        }

        @Override
        public void onTrack(Polyline polyline, KmlPlacemark kmlPlacemark, KmlTrack kmlTrack) {

        }
    }


}
