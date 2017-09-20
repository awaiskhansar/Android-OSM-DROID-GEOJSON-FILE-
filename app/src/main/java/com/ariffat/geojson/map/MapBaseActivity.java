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

package com.ariffat.geojson.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ariffat.geojson.R;
import com.ariffat.geojson.map.GeoJsonViewerConstants;

import java.io.File;
import java.util.ArrayList;

public abstract class MapBaseActivity extends AppCompatActivity {
     private ArrayList<Uri> jsonUris = new ArrayList<>();
    private ArrayList<Integer> jsonColors = new ArrayList<>();
    private Context context;
    private String FILE_NAME = "test.geo";
    private String FILE_EXTENSION = ".json";
    private String FILE_FOLDER_NAME = "mapfiles";
    private static final int DEFAULT_LAYER_COLOR = Color.argb(255, 0, 0, 0);


////////////////////////////getJsonUris-START////////////////////////////////////////

    public ArrayList<Uri> getJsonUris() {

         jsonUris.add( Uri.fromFile(new File(getFilePath())));

        return jsonUris;
    }

    private String getFilePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + FILE_FOLDER_NAME + File.separator + FILE_NAME + FILE_EXTENSION;
    }
////////////////////////////getJsonUris-END////////////////////////////////////////

////////////////////////////getJsonColors-START////////////////////////////////////////

    public ArrayList<Integer> getJsonColors() {

        jsonColors.add(0, DEFAULT_LAYER_COLOR);

        return jsonColors;
    }
////////////////////////////getJsonColors-END////////////////////////////////////////

    public Context getContext() {
        context = getApplicationContext();
        return context;
    }
}
