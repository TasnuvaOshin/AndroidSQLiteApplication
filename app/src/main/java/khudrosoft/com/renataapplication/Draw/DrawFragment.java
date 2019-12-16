package khudrosoft.com.renataapplication.Draw;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import khudrosoft.com.renataapplication.R;

public class DrawFragment extends Fragment {
    private int rotation;
    private Button cardView;
    private ObjectAnimator animation;
    private ImageView imageView;
    private RotateAnimation rotate;
    private String title, msg;
    private AlertDialog.Builder alert;

    int rot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw, container, false);

        cardView = view.findViewById(R.id.cardview);

        imageView = view.findViewById(R.id.imageView);
        alert = new AlertDialog.Builder(getActivity());
        animation = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        animation.setDuration(500);
        animation.setRepeatCount(ObjectAnimator.INFINITE);
        animation.setInterpolator(new LinearInterpolator());

        //start
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView.setVisibility(View.INVISIBLE);
                animation.start();
             //   CountMe();

            }
        });

        return view;
    }


}
