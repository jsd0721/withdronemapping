package com.example.dpviewer;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.esri.arcgisruntime.data.ShapefileFeatureTable;

public class MainActivity extends AppCompatActivity {
    ImageButton fileExplorer_button ;
    EditText JibunText;
    Button searchbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileExplorer_button = findViewById(R.id.fileExplorerbutton_mainActivity);
        searchbutton = findViewById(R.id.explorerbutton_mainActivity);
        JibunText = (EditText)findViewById(R.id.Injibun_mainAcvity);


        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMapView = new Intent(getApplicationContext(), mapView.class);
                startActivity(intentMapView);
            }
        });
    }
}