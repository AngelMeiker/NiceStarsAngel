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
                    .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/09b52687-0d7e-4eed-b23e-6906e094a792/ddf70na-8591651c-7ce9-4a34-89cc-662f1c9c7ecf.png/v1/fill/w_1920,h_3414,q_80,strp/reshiram_phone_wallpaper_by_santithur_ddf70na-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MzQxNCIsInBhdGgiOiJcL2ZcLzA5YjUyNjg3LTBkN2UtNGVlZC1iMjNlLTY5MDZlMDk0YTc5MlwvZGRmNzBuYS04NTkxNjUxYy03Y2U5LTRhMzQtODljYy02NjJmMWM5YzdlY2YucG5nIiwid2lkdGgiOiI8PTE5MjAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.cq25nM2GonC0M9XzVxIFF5q8XBErLonQaz-P76tpiqk")
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