package p32929.easypermission_demoapp;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import p32929.easypermissionlibrary.FromAlexVai.PermissionsListener;
import p32929.easypermissionlibrary.FromP32929.Easy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getOne(View view) {
        // Getting single permission
        Easy.getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, this, new PermissionsListener() {
            @Override
            public void onPermissionGranted() {
                // Your code here
                // When Permissions are granted
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // Your code here
                // When Permissions are denied
            }
        });
    }

    public void getMul(View view) {
        // Getting multiple permissions
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.READ_CONTACTS);

        Easy.getPermissions(permissions, this, new PermissionsListener() {
            @Override
            public void onPermissionGranted() {
                // Your code here
                // When Permissions are granted
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // Your code here
                // When Permissions are denied
            }
        });
    }
}
