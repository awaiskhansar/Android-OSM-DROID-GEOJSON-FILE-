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
import android.net.Uri;


import org.json.JSONException;
import org.osmdroid.views.MapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeoJsonify {

    /**
     * Add GeoJson features to a Google Maps map.
     * Accepts a list of uris, each one with a different .geojson file to parse
     * Each layer will be added on top of the other based on the order in the list
     *
     *
     * @param map GoogleMap object where to add GeoJson layers
     * @param jsonUris list of uris of .geojson files
     * @param jsonColors list of colors for each geojson layer
     * @param context application context
     * @throws IOException if unable to open file
     * @throws JSONException if unable to parse JSON content
     */





    /**
     * Add GeoJson features to a Open Street Maps map.
     * Accepts a list of uris, each one with a different .geojson file to parse
     * Each layer will be added on top of the other based on the order in the list
     *
     *
     * @param map MapView object where to add GeoJson layers
     * @param jsonUris list of uris of .geojson files
     * @param jsonColors list of colors for each geojson layer
     * @param context application context
     * @throws IOException if unable to open file
     */
    public static void geoJsonifyMap(MapView map, List<Uri> jsonUris, List<Integer> jsonColors, Context context) throws IOException {
        JsonifyOSM.geoJsonifyMap(map, jsonUris, jsonColors, context);
    }

    /**
     * Works just like {@link GeoJsonify#geoJsonifyMap(MapView, List, List, Context)} except
     * all the added GeoJson layers will be the same color
     *
     * @param color color for all geojson layers
     * @see GeoJsonify#geoJsonifyMap(MapView, List, List, Context)
     */
    public static void geoJsonifyMap(MapView map, List<Uri> jsonUris, int color, Context context) throws IOException {
        JsonifyOSM.geoJsonifyMap(map, jsonUris, generateColorsList(color, jsonUris.size()), context);
    }

    private static List<Integer> generateColorsList(int color, int size) {
        List<Integer> jsonColors = new ArrayList<>();
        for (int i=0; i<size; i++){
            jsonColors.add(color);
        }

        return jsonColors;
    }
}
