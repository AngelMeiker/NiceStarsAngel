package com.agomez.nicestart;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            //Glide for loading girls
            ImageView mProfile = findViewById(R.id.profile); //id XML
            //ImageView mLogo = findViewById(R.id.logo);

            Glide.with(this)
                    .load("https://static1.thegamerimages.com/wordpress/wp-content/uploads/2021/10/N-from-pokemon-black-and-white.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))//png
                    .circleCrop()
                    .into(mProfile);


            return insets;
        });



    }
}