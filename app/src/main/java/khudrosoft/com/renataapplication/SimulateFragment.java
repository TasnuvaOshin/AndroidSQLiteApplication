package khudrosoft.com.renataapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.Login.LoginFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SimulateFragment extends Fragment {
    private Button regnum, signup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simulate, container, false);
        regnum = view.findViewById(R.id.regnum);
        signup = view.findViewById(R.id.signupnum);


        regnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetFrame(new RegFragment());

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
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
