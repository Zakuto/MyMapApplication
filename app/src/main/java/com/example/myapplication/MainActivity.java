package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.widget.Toast;

import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.model.ListOfActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    RecyclerView recyclerView;
    ActivityAdapter activityAdapter;
    List<ListOfActivity> listOfActivities = new ArrayList();

    private static final String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[]{ACCESS_COARSE_LOCATION,ACCESS_FINE_LOCATION};

    private static final int REQUEST_PERMISSION_LOCATION = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String [] activityArray = res.getStringArray(R.array.activity_array);


        for(int i = 0; i < activityArray.length; i++){
            listOfActivities.add(new ListOfActivity(activityArray[i]));
        }

        if(ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)){

            requestPermissions(this,REQUIRED_SDK_PERMISSIONS,REQUEST_PERMISSION_LOCATION);

        }

        generateActivityDataList(listOfActivities);
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

    //calling the MainActivity using Intent.ACTION_MAIN
    public void goToMainActivity(){
        intent = new Intent(Intent.ACTION_MAIN);
        startActivity(intent);
    }

    //permission validation after PERMISSION_GRANTED
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

    private void generateActivityDataList(List<ListOfActivity> activityList){
        recyclerView = findViewById(R.id.mainRecyclerView);
        activityAdapter = new ActivityAdapter(this,activityList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(activityAdapter);
    }

}
