package com.example.instagramclone;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView username = findViewById(R.id.username);
        TextView description = findViewById(R.id.description);
        ImageView postImage = findViewById(R.id.postImage);
        Button buttonChangeColor = findViewById(R.id.buttonChangeColor);

        // Set default values for the profile
        username.setText("geewonii");
        description.setText("this nerd loves jiwon <3");

        // Set default images for profile and post
        profileImage.setImageResource(R.drawable.new_profile_image);
        postImage.setImageResource(R.drawable.new_post_image);

        // Handle background color change
        buttonChangeColor.setOnClickListener(v -> {
            int[] colors = {Color.parseColor("#F8F8F8"), Color.parseColor("#E3F2FD"), Color.parseColor("#FFC0CB"), Color.parseColor("#FFEB3B")};
            int randomIndex = (int) (Math.random() * colors.length);
            findViewById(R.id.mainLayout).setBackgroundColor(colors[randomIndex]);
        });
    }
}