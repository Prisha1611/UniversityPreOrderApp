package com.example.midtermproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class MainActivity extends AppCompatActivity {
    Button getStartedButton;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getStartedButton = findViewById(R.id.getStartedButton);
        animationView = findViewById(R.id.lottieAnimationView);

        // Initialize the animation but do not play it immediately
        animationView.setAnimation(R.raw.order);
        animationView.setRepeatCount(0); // Ensure the animation plays only once
        animationView.setVisibility(View.INVISIBLE); // Start with the animation view invisible

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make the animation view visible and play the animation
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();

                // Add an animation listener to wait for the animation to end
                animationView.addAnimatorListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // Animation ended, navigate after a delay
                        navigateAfterDelay();
                    }
                });
            }
        });
    }

    private void navigateAfterDelay() {
        // Delay time in milliseconds (e.g., 2 seconds)
        int delayMillis = 300;

        // Use a Handler to add a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Navigate to the Login activity
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        }, delayMillis);
    }
}
