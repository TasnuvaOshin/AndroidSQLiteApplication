package khudrosoft.com.renataapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import khudrosoft.com.renataapplication.API.DvManager;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class TestActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        DvManager dvManager = new DvManager(this);

        String res = dvManager.addRecord("oshin", "01645sdfad", "dsa", "asdad", "adasasd");
        Log.d("name", String.valueOf(dvManager.getReadableDatabase()));
        Toast.makeText(this, "" + dvManager.getReadableDatabase(), Toast.LENGTH_SHORT).show();

        File dbFile = getDatabasePath("InfoDb.db");
        Log.v("file", "Db path is: " + dbFile);

        Cursor cursor = dvManager.getAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                stringBuffer.append("ID : " + cursor.getString(0) + "\n");
                stringBuffer.append("Name : " + cursor.getString(1) + "\n");
                stringBuffer.append("Phoneno : " + cursor.getString(2) + "\n");
                stringBuffer.append("District : " + cursor.getString(3) + "\n");
                stringBuffer.append("Thana : " + cursor.getString(4) + "\n");
                stringBuffer.append("Gift : " + cursor.getString(5) + "\n");
            }

            Log.d("data", String.valueOf(stringBuffer));
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(TestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(TestActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new android.app.AlertDialog.Builder(this)
                        .setTitle("Permission")
                        .setMessage("Please Share/on Your Location")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(TestActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                                startActivity(new Intent(TestActivity.this, TestActivity.class));
                                TestActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            }

            return false;
        } else {

            return true;

        }
    }
}
