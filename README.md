# EasyRuntimePermission
An Easier &amp; Lazier approach to getting runtime permission in Android 6.0+

## Installation:

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Add the dependency
```
dependencies {
        implementation 'com.github.p32929:EasyRuntimePermission:1.0'
}
```

## Usage
Steps to follow:
* Add required permissions in your ```manifest``` file

Now
* For getting single permission call ```Easy.getPermission()```
* For getting multiple permissions call ```Easy.getPermissions()```
With appropriate parameters

# Code example
### Adding permissions into the ```AndroidManifest.xml``` file
Add your permissions above the ```application``` TAG like this:

```
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

## Getting Permissions:
### To get one permission, call ```Easy.getPermission()``` like this:
```
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
```

### To get multiple permissions:
* First create an ```ArrayList<String>```
* Add your permissions into the ArrayList variable
* Call ```Easy.getPermissions()```

Like this:
```
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
```

### Contribution:
I have used the ```BitsPermission``` library classes from Alex Beshine (https://github.com/alex31n) . So, thank you Alex vai :)

### & Thanks to:
Everyone. Hope you'll enjoy using the library :)

## License:
```
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
```
