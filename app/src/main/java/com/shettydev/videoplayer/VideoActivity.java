package com.shettydev.videoplayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.*;

public class VideoActivity extends AppCompatActivity {
        VideoView videoView;
        ImageView screenRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video);
        videoView = (VideoView)findViewById(R.id.VideoView);
        screenRotate=findViewById(R.id.screenRotate);
        screenRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checks the orientation of the screen
                if (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE == getRequestedOrientation()){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT == getRequestedOrientation()){
                    setRequestedOrientation(SCREEN_ORIENTATION_LANDSCAPE);

                }
            }
        });
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");


        videoView.setVideoPath(sessionId);
        videoView.start();
        final MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoView);


        videoView.setMediaController(mediacontroller);
        videoView.setVideoPath(sessionId);
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Video over", Toast.LENGTH_SHORT).show();



            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API123", "What " + what + " extra " + extra);
                return false;
            }
        });
    }
}