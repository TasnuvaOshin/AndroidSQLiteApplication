package khudrosoft.com.renataapplication.Quiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.Draw.DrawFragment;
import khudrosoft.com.renataapplication.Login.LoginFragment;
import khudrosoft.com.renataapplication.R;

public class QuizHomeFragment extends Fragment {
    Button button;
    ArrayList<QuizData> arrayList;
    int max = 10;
    int min = 0;
    Random rand;
    int randomNum;
    private TextView question;
    private RadioButton op1, op2, op3, op4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_home, container, false);
        arrayList = AddQuizData.getArrayList();
        question = view.findViewById(R.id.qus);
        op1 = view.findViewById(R.id.op1);
        op2 = view.findViewById(R.id.op2);
        op3 = view.findViewById(R.id.op3);
        op4 = view.findViewById(R.id.op4);

        rand = new Random();
        randomNum = rand.nextInt((max - min) + 1) + min;
        if (arrayList.size() > 0) {
            Log.d("data", arrayList.get(0).question);
            question.setText(arrayList.get(randomNum).question);
            op1.setText(arrayList.get(randomNum).opt_one);
            op2.setText(arrayList.get(randomNum).opt_two);
            op3.setText(arrayList.get(randomNum).opt_three);
            op4.setText(arrayList.get(randomNum).opt_four);
        } else {
            Log.d("data", "dont get any data");
        }
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFrame(new LoginFragment());
            }
        });

        return view;
    }

    private void SetFrame(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
