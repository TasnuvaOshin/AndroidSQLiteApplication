package khudrosoft.com.renataapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.API.DvManager;
import khudrosoft.com.renataapplication.Quiz.QuizHomeFragment;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class RegFragment extends Fragment {
    private Button button;
    private EditText editText;
    String  num;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    DvManager dvManager ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_reg, container, false);
      button = view.findViewById(R.id.signup);
      editText = view.findViewById(R.id.number);
        dvManager= new DvManager(getActivity());
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPrefs.edit();
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              num = editText.getText().toString();

              if(TextUtils.isEmpty(num)){

                  Util.showAlert(getActivity(), "Please Enter The Registration Number");

              }else {
                  if (dvManager.getAllDataPhone(num)) {
                      Util.showAlert(getActivity(), "Already Registered");

                  } else {

                      String res = dvManager.addPhone("premium", num);
                      editor.putString("name", "premium");
                      editor.putString("phone", num);
                      editor.putString("district", "premium");
                      editor.apply();
                      SetFrame(new QuizHomeFragment());
                      Log.d("log", String.valueOf(dvManager.getAllDataPhone(num)));

                  }

              }

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
