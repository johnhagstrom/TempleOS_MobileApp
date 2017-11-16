package org.templeos.templeos7;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restrict to portrait mode
        // TODO: allow orientation change while not interrupting animation and music
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Start theme song
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.templeos_theme3);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();

        // Start animation
        ImageView img = (ImageView)findViewById(R.id.temple_theme);
        img.setAnimation(AnimationUtils.loadAnimation(this,R.anim.zoom_in));
    }

    public void screenTapped(View view) {
        mediaPlayer.stop();
        Intent intent = new Intent(this, Nav.class);
        startActivity(intent);
    }
}
