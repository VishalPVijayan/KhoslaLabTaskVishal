package com.vishalpvijayan.khoslalabtaskvishal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    public static final int MULTIPLE_PERMISSIONS = 5; // code you want.
    List<String> listPermissionsNeeded = new ArrayList<>();


    String[] permissions= new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if(listPermissionsNeeded != null){

            Intent i = new Intent (SplashScreen.this,MainActivity.class);
            startActivity(i);
            finish();

        }
        else {

            CheckPermission();





        }



        }

    // FOR CHECKING PERMISSIONS

    public boolean CheckPermission() {
        int result;

        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(SplashScreen.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );

            return false;
        }
        return true;

    }


    // ELSE STATEMENT BY SATHISH ADDED HERE

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissionsList[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS:{
                if (grantResults.length > 0) {
                    String permissionsDenied = "";
                    for (String per : permissionsList) {
                        if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                            permissionsDenied += "\n" + per;

                        }

                    }
                    // Show permissionsDenied

                }
                return;
            }
        }
    }



}
