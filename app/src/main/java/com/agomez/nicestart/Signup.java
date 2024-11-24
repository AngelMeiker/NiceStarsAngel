package com.agomez.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            ImageView mHipman = findViewById(R.id.hipman); //id XML

            Glide.with(this)
                    .load("https://res.cloudinary.com/cook-becker/image/fetch/q_auto:good,f_auto,w_1920/https://candb.com/site/candb/images/artwork/Aloy-Horizon-Zero-Dawn_Guerrilla.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))//png
                    .centerCrop()
                    .placeholder(new ColorDrawable(this.getResources().getColor(R.color.valladolid)))
                    .into(mHipman);


            return insets;
        });
    }


    public void openMain(View v){
        Intent intentMain = new Intent(Signup.this,Main.class);
        startActivity(intentMain);
    }
}