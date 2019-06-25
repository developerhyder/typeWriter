package com.example.typewriter;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int progressStatus = 0;
    private int count = 0;
    private Handler handler = new Handler();
    private int i=0;
    MediaPlayer mp;
    TextView tv;
    String test = "Turn on the sound !!! Ssup people this is one of the ways to emulate a type writer via a text view you can download the source code from my git repository ping me if you want the link to my repository!!!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.textView0);
        mp = MediaPlayer.create(this, R.raw.k4);
        somee(mp);
    }
    private void somee(final MediaPlayer mp) {
        final int ct = test.length();
        final char[] ch = test.toCharArray();

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < ct) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            mp.start();
                            String s = Character.toString(ch[i]);

                            tv.append(s);
                            s="";
                            i++;
                            if (i == ct-1){
                                mp.stop();
                            }
                        }
                    });
                    try {
                        // Sleep for 600 milliseconds.
                        Thread.sleep(75);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus){
            mp.stop();
        }
        super.onWindowFocusChanged(hasFocus);
    }
}
