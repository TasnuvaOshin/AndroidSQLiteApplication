package khudrosoft.com.renataapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import khudrosoft.com.renataapplication.API.DvManager;
import khudrosoft.com.renataapplication.Download.download_data;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

//this activity is for downloading the Data

public class TestActivity extends AppCompatActivity {
    private static final String TAG = "Data" ;
    File directory, sd, file;
    WritableWorkbook workbook;
    List<download_data> listdata;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        listdata = new ArrayList<>();
        DvManager dvManager = new DvManager(this);

//        String res = dvManager.addRecord("oshin", "01645sdfad", "dsa", "asdad", "adasasd");
//        Log.d("name", String.valueOf(dvManager.getReadableDatabase()));
//        Toast.makeText(this, "" + dvManager.getReadableDatabase(), Toast.LENGTH_SHORT).show();

        File dbFile = getDatabasePath("InfoDb.db");
        Log.v("file", "Db path is: " + dbFile);

        Cursor cursor = dvManager.getAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nothing to display", Toast.LENGTH_SHORT).show();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            while (cursor.moveToNext()) {
                listdata.add(new download_data(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
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


    public void createExcelSheet() {
        if(isStoragePermissionGranted()) {
            String csvFile = "Results.xls";
            sd = Environment.getExternalStorageDirectory();
            directory = new File(sd.getAbsolutePath());
            file = new File(directory, csvFile);
            WorkbookSettings wbSettings = new WorkbookSettings();
            wbSettings.setLocale(new Locale("en", "EN"));
            try {
                workbook = Workbook.createWorkbook(file, wbSettings);
                createFirstSheet();

                //closing cursor
                workbook.write();
                workbook.close();

                Toast.makeText(this, "File Saved !", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

            Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show();
        }
    }

    public void createFirstSheet() {
        try {

            //Excel sheet name. 0 (number)represents first sheet
            WritableSheet sheet = workbook.createSheet("sheet1", 0);
            // column and row title
            sheet.addCell(new Label(0, 0, "Id"));
            sheet.addCell(new Label(1, 0, "Name"));
            sheet.addCell(new Label(2, 0, "PhoneNo"));
            sheet.addCell(new Label(3, 0, "District"));
            sheet.addCell(new Label(4, 0, "Thana"));
            sheet.addCell(new Label(5, 0, "Gift"));


            for (int i = 0; i < listdata.size(); i++) {
                sheet.addCell(new Label(0, i + 1, listdata.get(i).getId()));
                sheet.addCell(new Label(1, i + 1, listdata.get(i).getName()));
                sheet.addCell(new Label(2, i + 1, listdata.get(i).getPhoneno()));
                sheet.addCell(new Label(3, i + 1, listdata.get(i).getDistrict()));
                sheet.addCell(new Label(4, i + 1, listdata.get(i).getThana()));
                sheet.addCell(new Label(5, i + 1, listdata.get(i).getGift()));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void SaveTheFile(View view) {
        createExcelSheet();
        Toast.makeText(this, "Please Check the File After 10 Min", Toast.LENGTH_SHORT).show();
    }


    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
}