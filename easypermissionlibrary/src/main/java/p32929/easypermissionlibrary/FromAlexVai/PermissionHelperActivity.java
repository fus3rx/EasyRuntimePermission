package p32929.easypermissionlibrary.FromAlexVai;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by p32929 on 8/16/2018.
 */

/*
MIT License

Copyright (c) 2018 Fayaz Bin Salam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

public class PermissionHelperActivity extends AppCompatActivity {

    private static PermissionsListener permissionsListener;

    private ArrayList<String> list;

    private final int PERMISSIONS_REQUEST_CODE = 1245;

    public static void startActivity(Context context, Intent intent, PermissionsListener listener) {
        PermissionHelperActivity.permissionsListener = listener;
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_permission);
        overridePendingTransition(0, 0);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        if (getIntent() != null) {
            if (getIntent().hasExtra("LIST")) {
                list = getIntent().getStringArrayListExtra("LIST");
                //Log.e("TAG", "list: "+ list.size());
            }
        }

        checkPermission();
    }


    private void checkPermission() {
        ArrayList<String> permissionList = new ArrayList<>();

        for (String per : list) {
            if (ContextCompat.checkSelfPermission(this, per) != PackageManager.PERMISSION_GRANTED) {
                //Log.e("TAG", "permission need: "+ per);
                permissionList.add(per);
            }
        }

        //Log.e("TAG", "permissionList.size(): "+ permissionList.size());
        if (permissionList.size() > 0) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), PERMISSIONS_REQUEST_CODE);
            /*ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_CODE);*/
        } else {
            Log.e("TAG", "No permission is need");
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE: {
                ArrayList<String> deniedPermissions = getDeniedPermissions(this, permissions);
                permissionResult(deniedPermissions);

            }

        }
    }

    private void permissionResult(ArrayList<String> deniedPermissions) {

        if (deniedPermissions == null || deniedPermissions.isEmpty()) {
            if (permissionsListener != null) permissionsListener.onPermissionGranted();
        } else {
            if (permissionsListener != null)
                permissionsListener.onPermissionDenied(deniedPermissions);
        }

        PermissionHelperActivity.permissionsListener = null;

        finish();
    }

    public static boolean isDenied(Context context, @NonNull String permission) {
        //return !isGranted(context, permission);
        return ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED;
    }

    private static boolean isGranted(Context context, @NonNull String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static ArrayList<String> getDeniedPermissions(Context context, @NonNull String... permissions) {
        ArrayList<String> deniedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (isDenied(context, permission)) {
                deniedPermissions.add(permission);
            }
        }
        return deniedPermissions;
    }

    /*@Override
    protected void onStop() {
        super.onStop();
        Log.e("TAG", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy");
    }*/

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}