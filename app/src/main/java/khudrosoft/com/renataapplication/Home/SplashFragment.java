package khudrosoft.com.renataapplication.Home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.FragmentTransaction;
import khudrosoft.com.renataapplication.Login.LoginFragment;
import khudrosoft.com.renataapplication.R;


public class SplashFragment extends Fragment {
    VideoView webView;
    MediaController mediaController;
    boolean play = true;

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        webView = view.findViewById(R.id.webview);
        mediaController = new MediaController(getActivity());
        webView.setVideoPath("android.resource://" + getActivity().getPackageName() + "/" + R.raw.promo);
        //   mediaController.show();

        mediaController.setAnchorView(webView);
        mediaController.setMediaPlayer(webView);
        mediaController.requestFocus();

        webView.setMediaController(mediaController);
        webView.start();

        webView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });



        webView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                play = false;

            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // Toast.makeText(getActivity(), "I Clicked", Toast.LENGTH_SHORT).show();
                SetFrame(new LoginFragment());

                return true;
            }


        });
        Log.d("play", String.valueOf(play));
        return view;
    }

    private void SetFrame(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}
