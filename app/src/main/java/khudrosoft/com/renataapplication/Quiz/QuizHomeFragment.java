package khudrosoft.com.renataapplication.Quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.Draw.DrawFragment;
import khudrosoft.com.renataapplication.Login.LoginFragment;
import khudrosoft.com.renataapplication.MainActivity;
import khudrosoft.com.renataapplication.R;
import khudrosoft.com.renataapplication.Util;

public class QuizHomeFragment extends Fragment {
    Button button;
    ArrayList<QuizData> arrayList;
    int max = 29;
    int min = 0;
    Random rand;
    int randomNum;
    private TextView question;
    private RadioButton op1, op2, op3, op4;
    private RadioGroup radioGroup;
    private String corAns;
    private Bundle bundle;
    private ImageView banner_top, banner_bottom;
    private EditText editText;
    private FloatingActionButton floatingActionButton;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_home, container, false);
        bundle = new Bundle();
        radioGroup = view.findViewById(R.id.radioGroup1);
        arrayList = AddQuizData.getArrayList();
        question = view.findViewById(R.id.qus);
        op1 = view.findViewById(R.id.op1);
        op2 = view.findViewById(R.id.op2);
        op3 = view.findViewById(R.id.op3);
        op4 = view.findViewById(R.id.op4);
        editText = view.findViewById(R.id.et);
        floatingActionButton = view.findViewById(R.id.submit);
        banner_top = view.findViewById(R.id.banner_top);
        banner_bottom = view.findViewById(R.id.banner_bottom);

        rand = new Random();
        randomNum = rand.nextInt((max - min) + 1) + min;
        if (arrayList.size() > 0) {
            Log.d("data", arrayList.get(0).question);
            question.setText(arrayList.get(randomNum).question);
            op1.setText(arrayList.get(randomNum).opt_one);
            op2.setText(arrayList.get(randomNum).opt_two);
            op3.setText(arrayList.get(randomNum).opt_three);
            op4.setText(arrayList.get(randomNum).opt_four);
            corAns = arrayList.get(randomNum).answer;
        } else {
            Log.d("data", "dont get any data");
        }
        button = view.findViewById(R.id.button);

//        button.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                int radioButtonID = radioGroup.getCheckedRadioButtonId();
//                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
//                String selectedtext = (String) radioButton.getText();
//                Log.d("index",selectedtext);
//
//                if(selectedtext.equals(corAns)){
//                    ShowStatus("Congratulations","You Got It","right");
//                    // Toast.makeText(getActivity(), "Right Answer", Toast.LENGTH_SHORT).show();
//                }else {
//                    ShowStatus("Thanks For Participation","Claim Your Gift","wrong");
//                    // Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();
//
//                }
//                // SetFrame(new LoginFragment());
//                return true;
//            }
//        });


        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    int radioButtonID = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                    String selectedtext = (String) radioButton.getText();
                    Log.d("index", selectedtext);

                    if (selectedtext.equals(corAns)) {
                        ShowStatus("Congratulations", "You Got It", "right");
                        // Toast.makeText(getActivity(), "Right Answer", Toast.LENGTH_SHORT).show();
                    } else {
                        ShowStatus("Thanks For Participation", "Claim Your Gift", "wrong");
                        // Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();

                    }

                    return true;
                }
                return false;
            }
        });
//button


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonID);
                String selectedtext = (String) radioButton.getText();
                Log.d("index", selectedtext);

                if (selectedtext.equals(corAns)) {
                    ShowStatus("Congratulations", "You Got It", "right");
                    // Toast.makeText(getActivity(), "Right Answer", Toast.LENGTH_SHORT).show();
                } else {
                    ShowStatus("Thanks For Participation", "Claim Your Gift", "wrong");
                    // Toast.makeText(getActivity(), "Wrong Answer", Toast.LENGTH_SHORT).show();

                }
                // SetFrame(new LoginFragment());
            }
        });
        ShowtypeOne();
        return view;
    }

    private void ShowStatus(String title, String msg, final String answer) {
        new android.app.AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DrawFragment drawFragment = new DrawFragment();
                        bundle.putString("status", answer);
                        drawFragment.setArguments(bundle);
                        SetFrame(drawFragment);
                    }
                })
                .create()
                .show();
    }

    private void SetFrame(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
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
