package khudrosoft.com.renataapplication.Draw;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.API.DvManager;
import khudrosoft.com.renataapplication.Home.SplashFragment;
import khudrosoft.com.renataapplication.R;

public class DrawFragment extends Fragment {
    private int rotation;
    private Button cardView;
    private ObjectAnimator animation;
    private ImageView imageView, imageView1;
    private RotateAnimation rotate;
    private String title, msg;
    private AlertDialog.Builder alert;
    String status;
    private ArrayList<String> rightArray;
    int max = 50;
    int min = 0;
    Random rand;
    int randomNum;
    String gift;
    SharedPreferences sharedPrefs;
    String name, phone, district;
    private EditText editText;
    private ImageView banner_top, banner_bottom;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw, container, false);
        rand = new Random();
        randomNum = rand.nextInt((max - min) + 1) + min;
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        name = sharedPrefs.getString("name", "defaultValue");
        phone = sharedPrefs.getString("phone", "defaultValue");
        district = sharedPrefs.getString("district", "defaultValue");
        editText = view.findViewById(R.id.et);
        banner_top = view.findViewById(R.id.banner_top);
        banner_bottom = view.findViewById(R.id.banner_bottom);
        floatingActionButton = view.findViewById(R.id.submit);
        imageView1 = view.findViewById(R.id.imageView1);
        rightArray = new ArrayList<String>();
        rightArray.add("kiamfrypan");
        rightArray.add("wokpan");
        rightArray.add("belt");
        rightArray.add("nakshikhata");
        rightArray.add("towel");
        rightArray.add("electrickettle");
        rightArray.add("bedsheet");
        rightArray.add("treeplate");
        rightArray.add("cuttleryset");
        rightArray.add("belfmoneybadcombo");
        rightArray.add("pressurecokker");
        rightArray.add("pendrive");
        rightArray.add("frypan");

        status = getArguments().getString("status");
        //Toast.makeText(getActivity(), "" + status, Toast.LENGTH_LONG).show();
        cardView = view.findViewById(R.id.cardview);

        imageView = view.findViewById(R.id.imageView);
        alert = new AlertDialog.Builder(getActivity());
        animation = ObjectAnimator.ofFloat(imageView1, "rotation", 0, 360);
        animation.setDuration(500);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new LinearInterpolator());

//cardview
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asize", String.valueOf(rightArray.size()));
                cardView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.GONE);
                imageView1.setVisibility(View.VISIBLE);
                animation.start();
                //   CountMe();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        animation.cancel();
                        ShowStatus("Result", "You Have Win " + statusWork());
                        DvManager dvManager = new DvManager(getActivity());
                        String res = dvManager.addRecord(name, phone, district, "asdad", statusWork());
                        Log.d("res", res);
                    }
                }, 5000);
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    Log.d("asize", String.valueOf(rightArray.size()));
                    cardView.setVisibility(View.INVISIBLE);
                    animation.start();
                    //   CountMe();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            animation.cancel();
                            ShowStatus("Result", "You Have Win " + statusWork());
                            DvManager dvManager = new DvManager(getActivity());
                            String res = dvManager.addRecord(name, phone, district, "asdad", statusWork());
                            Log.d("res", res);
                        }
                    }, 5000);

                    return true;
                }
                return false;
            }
        });
        ShowtypeOne();
        return view;
    }

    private void ShowStatus(String title, String msg) {
        new android.app.AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        SetFrame(new SplashFragment());
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void SetFrame(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


    public String statusWork() {

        if (status.equals("right")) {


            if (randomNum >= 0 && randomNum <= 5) {
                gift = "Belt";
                return gift;
            } else if (randomNum >= 6 && randomNum <= 8) {
                gift = "pendrive";
                return gift;
            } else if (randomNum >= 9 && randomNum <= 12) {
                gift = "Bed Sheet";
                return gift;
            } else if (randomNum >= 13 && randomNum <= 15) {
                gift = "pendrive";
                return gift;
            } else if (randomNum >= 16 && randomNum <= 18) {
                gift = "Towel";
                return gift;
            } else if (randomNum >= 19 && randomNum <= 22) {
                gift = "Pendrive";
                return gift;
            } else if (randomNum >= 23 && randomNum <= 25) {
                gift = "Belt";
                return gift;
            } else if (randomNum >= 25 && randomNum <= 27) {
                gift = "Belt Money Bag combo";
                return gift;
            } else if (randomNum >= 28 && randomNum <= 30) {
                gift = "Cuttlery set";
                return gift;
            } else if (randomNum >= 31 && randomNum <= 32) {
                gift = "Kiam Fry pan";
                return gift;
            } else if (randomNum >= 33 && randomNum <= 35) {
                gift = "Wok pan";
                return gift;
            } else if (randomNum >= 36 && randomNum <= 38) {
                gift = "Nakshi Katha";
                return gift;
            } else if (randomNum >= 39 && randomNum <= 41) {
                gift = "Fry Pan";
                return gift;
            } else if (randomNum >= 42 && randomNum <= 45) {
                gift = "Pressure Cooker";
                return gift;
            } else if (randomNum >= 46 && randomNum <= 48) {
                gift = "Tree Plate";
                return gift;
            } else if (randomNum >= 49 && randomNum <= 50) {
                gift = "Electric Kettle";
                return gift;
            }
        } else {
            //this is the wrong part


            if (randomNum >= 0 && randomNum <= 10) {
                gift = "Belt";
                return gift;
            } else if (randomNum >= 11 && randomNum <= 15) {
                gift = "Pendrive";
                return gift;
            } else if (randomNum >= 16 && randomNum <= 25) {
                gift = "Towel";
                return gift;
            } else if (randomNum >= 26 && randomNum <= 30) {
                gift = "pendrive";
                return gift;
            } else if (randomNum >= 31 && randomNum <= 35) {
                gift = "Pendrive";
                return gift;
            } else if (randomNum >= 36 && randomNum <= 40) {
                gift = "Towel";
                return gift;
            } else if (randomNum >= 41 && randomNum <= 45) {
                gift = "Nivea Men Shower Gel & Pantene Shampoo(Combo)";
                return gift;
            } else if (randomNum >= 46 && randomNum <= 50) {
                gift = "Belt";
                return gift;
            }
        }

        return "no gift";
    }

    private void ShowtypeOne() {


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            private boolean useDiceOne;

            @Override
            public void run() {

                if (!useDiceOne) {

                    banner_top.setImageResource(R.drawable.azisan);
                    banner_bottom.setImageResource(R.drawable.alpa);
                } else {

                    banner_top.setImageResource(R.drawable.tanven);
                    banner_bottom.setImageResource(R.drawable.ant);

                }
                useDiceOne = !useDiceOne;
                handler.postDelayed(this, 3000);
            }
        }, 8000);


    }

}
