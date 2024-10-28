package com.agomez.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);



            //Glide for loading girls
            ImageView mGirl = findViewById(R.id.girl); //id XML
            //ImageView mLogo = findViewById(R.id.logo);

            Glide.with(this)
                    .load("https://i.pinimg.com/736x/13/28/95/132895e631d37158600c91d8d70207a3.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))//png
                    .centerCrop()
                    .placeholder(new ColorDrawable(this.getResources().getColor(R.color.verdecillo)))
                    .into(mGirl);


            return insets;
        });
    }

    //Metodo para abrir sign up al pulsar el boton
    public void openSignup(View v){
        Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
    }

    public void openMain(View v){
        Intent intentMain = new Intent(Login.this,Main.class);
            startActivity(intentMain);
    }

}