package khudrosoft.com.renataapplication;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Util {

   public static  void showAlert(final Activity activity,String message){
        new android.app.AlertDialog.Builder(activity)
                .setTitle("Alert !!!!")
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent(activity, MainActivity.class));

                    }
                })
                .create()
                .show();


    }


}
