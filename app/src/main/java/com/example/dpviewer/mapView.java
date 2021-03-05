package com.example.dpviewer;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static java.util.jar.Pack200.Packer.PASS;


public class mapView extends AppCompatActivity {
    MapView basemap;
    FloatingActionButton menufab;
    ShapefileFeatureTable shpfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        menufab = (FloatingActionButton)findViewById(R.id.menufab_mapView);

        ArcGISRuntimeEnvironment.setApiKey("AAPKed68ed96c57d4a53a26e167eaf3b7ec1Z6kJ-I7y7B_xhUx7Gh8sMGSPwqjZgxVVYpCt85Nf2KR-iXOZpNX2YROj4IHYfzUG");

        basemap = findViewById(R.id.basemap_mapView);
        ArcGISMap map = new ArcGISMap(BasemapStyle.ARCGIS_STREETS);
        basemap.setMap(map);
        basemap.setViewpoint(new Viewpoint(35.467538,128.517794,5000));

        //fab click시 shpfile 그리기
        menufab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("결과",shpfile.getLoadStatus().toString());

            }
        });
        //shp파일 불러오기
        shpfile = new ShapefileFeatureTable(getExternalFilesDir(null)+"/east_grs80.shp");

        //피쳐 레이어 생성
        FeatureLayer flayer = new FeatureLayer(shpfile);

        //심볼 생성
        SimpleLineSymbol Lsymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED,1.0f);
        SimpleFillSymbol Fsymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.WHITE,Lsymbol);

        //렌더러 생성
        SimpleRenderer renderer = new SimpleRenderer(Fsymbol);

        //피처 레이어에 렌더러 부착
        flayer.setRenderer(renderer);

        //맵에 표시
        map.getOperationalLayers().add(flayer);

    }

    @Override
    protected void onPause() {
        super.onPause();
        basemap.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        basemap.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        basemap.dispose();
    }
}