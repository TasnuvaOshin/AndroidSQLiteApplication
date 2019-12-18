package khudrosoft.com.renataapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.Home.SplashFragment;
import khudrosoft.com.renataapplication.Quiz.AddQuizData;
import khudrosoft.com.renataapplication.Quiz.QuizData;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

ArrayList<QuizData> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<QuizData>();

        AddQuizData.setArrayList(arrayList);
        SetFrame(new SplashFragment());
    }

    private void SetFrame(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
