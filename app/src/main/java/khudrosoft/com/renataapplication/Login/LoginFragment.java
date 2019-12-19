package khudrosoft.com.renataapplication.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.API.DvManager;
import khudrosoft.com.renataapplication.API.user_data;
import khudrosoft.com.renataapplication.Home.SplashFragment;
import khudrosoft.com.renataapplication.MainActivity;
import khudrosoft.com.renataapplication.Quiz.AddQuizData;
import khudrosoft.com.renataapplication.Quiz.QuizData;
import khudrosoft.com.renataapplication.Quiz.QuizHomeFragment;
import khudrosoft.com.renataapplication.R;
import khudrosoft.com.renataapplication.Util;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class LoginFragment extends Fragment {
    private FloatingActionButton keyboard;
    private EditText editText, name_et;
    private Button button2;
    private Spinner spinner;
    private TextView textView;
    private String profile;
    ArrayList<CharSequence> arrayListCheck;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    private EditText thana;
    private CardView floatingActionButton;

    String name;
    String phone;
    String district;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final DvManager dvManager = new DvManager(getActivity());
        keyboard = view.findViewById(R.id.keyboard);
        editText = view.findViewById(R.id.phoneno);
        name_et = view.findViewById(R.id.name);
        button2 = view.findViewById(R.id.signup);
        spinner = view.findViewById(R.id.profile_spinner);
        textView = view.findViewById(R.id.district);

        thana = view.findViewById(R.id.thana);


        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getActivity(), "touch", Toast.LENGTH_SHORT).show();
                //  hideSoftKeyboard(getActivity());
                editText.clearFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPrefs.edit();

//        button2.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                String name = name_et.getText().toString();
//                String phone = editText.getText().toString();
//                String district = textView.getText().toString();
//                if (TextUtils.isEmpty(name)) {
//                    Util.showAlert(getActivity(), "Enter your Name Please");
//
//                } else {
//
//                    if (TextUtils.isEmpty(phone)) {
//                        Util.showAlert(getActivity(), "Enter your Phone Number Please");
//
//                    } else {
//
////                        Cursor cursor = dvManager.getAllDataPhone(phone);
////                        Log.d("count", String.valueOf(cursor.getCount()));
//
//                        if (dvManager.getAllDataPhone(phone)) {
//                            Util.showAlert(getActivity(),"Already Registered");
//
//
//                        }
//
//                        else  {
//
//                            String res = dvManager.addPhone(name, phone);
//                            editor.putString("name", name);
//                            editor.putString("phone", phone);
//                            editor.putString("district", district);
//                            editor.apply();
//                            SetFrame(new QuizHomeFragment());
//                            Log.d("log", String.valueOf(dvManager.getAllDataPhone(phone)));
//
//                        }
//
//
//                    }
//                }
//return true;
//            }
//        });

        //button2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_et.getText().toString();
                phone = editText.getText().toString();
                district = textView.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Util.showAlert(getActivity(), "Enter your Name Please");
                } else {

                    if (TextUtils.isEmpty(phone)) {
                        Util.showAlert(getActivity(), "Enter your Phone Number Please");
                    } else {

//                        Cursor cursor = dvManager.getAllDataPhone(phone);
//                        Log.d("count", String.valueOf(cursor.getCount()));

                        if (dvManager.getAllDataPhone(phone)) {
                            Util.showAlert(getActivity(), "Already Registered");

                        } else {

                            String res = dvManager.addPhone(name, phone);
                            editor.putString("name", name);
                            editor.putString("phone", phone);
                            editor.putString("district", district);
                            editor.apply();
                            SetFrame(new QuizHomeFragment());
                            Log.d("log", String.valueOf(dvManager.getAllDataPhone(phone)));
                        }


                    }
                }
            }
        });


        thana.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Log.d("thana", String.valueOf(thana.getText()));
                    Toast.makeText(getActivity(), thana.getText(), Toast.LENGTH_SHORT).show();

                    name = name_et.getText().toString();
                    phone = editText.getText().toString();
                    district = textView.getText().toString();

                    if (dvManager.getAllDataPhone(phone)) {
                        Util.showAlert(getActivity(), "Already Registered");

                    } else {

                        String res = dvManager.addPhone(name, phone);
                        editor.putString("name", name);
                        editor.putString("phone", phone);
                        editor.putString("district", district);
                        editor.apply();
                        SetFrame(new QuizHomeFragment());
                        Log.d("log", String.valueOf(dvManager.getAllDataPhone(phone)));

                    }


                    return true;
                }
                return false;
            }
        });


        ShowSpinner();
        return view;


    }

    private void SetFrame(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void ShowSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.profile, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                //    questionInfo.put("question_four", adapterView.getItemAtPosition(i).toString());
                textView.setText((CharSequence) adapterView.getItemAtPosition(i));
                profile = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}
