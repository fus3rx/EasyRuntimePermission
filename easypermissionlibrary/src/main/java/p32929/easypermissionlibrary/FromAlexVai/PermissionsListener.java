package p32929.easypermissionlibrary.FromAlexVai;

import java.util.ArrayList;

/**
 * Created by p32929 on 8/16/2018.
 */

public interface PermissionsListener {

    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);
}