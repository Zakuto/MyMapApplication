package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;


    private static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION};

    private static final int REQUEST_PERMISSION_LOCATION = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)){

            requestPermissions(this,REQUIRED_SDK_PERMISSIONS,REQUEST_PERMISSION_LOCATION);


        }

        findViewById(R.id.mapsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMapActivity();
            }
        });

        findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFloatActionMenuActivity();
            }
        });
    }

    private void goToMapActivity(){
        Intent mapActivityToast = new Intent(this,MapsActivity.class);
        startActivity(mapActivityToast);
    }

    private void requestPermissions(Activity activity, String[] permissionList, int requestCode){

        ActivityCompat.requestPermissions(activity, permissionList,requestCode);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMainActivity();
            }
        },3000);
    }

    public void goToMainActivity(){
        intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }

    public void goToFloatActionMenuActivity(){
        intent = new Intent(MainActivity.this,FloatingActionMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("TAG","@@@ PERMISSIONS grant");
                    goToMainActivity();
                } else {
                    Log.d("TAG","@@@ PERMISSIONS Denied");
                    Toast.makeText(this, "PERMISSIONS Denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



}
