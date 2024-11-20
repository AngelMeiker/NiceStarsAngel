//Primera pantalla - Pantalla de carga con animacion + nombre app

package com.agomez.nicestart;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            //Glide for loading girls
            ImageView mFondo = findViewById(R.id.fondo); //id XML
            //ImageView mLogo = findViewById(R.id.logo);

            Glide.with(this)
                    .load("https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTEyL3Jhd3BpeGVsb2ZmaWNlM19hYnN0cmFjdF9nZW9tZXRyaWNfZmx1aWRfYmxhY2tfY29sb3JfZ3JhZGllbnRfYl9mNWYxMTRjMC01MTVlLTRkZTctYjAxMi05ZDFkODljYzEyODBfMS5qcGc.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000))//png
                    .centerCrop()
                    .placeholder(new ColorDrawable(this.getResources().getColor(R.color.verdecillo)))
                    .into(mFondo);

            return insets;
        });
        openApp();

        //Animacion del logo
        ImageView thunder = findViewById(R.id.imgSplash);

        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.blink);
        thunder.startAnimation(myAnim);

    }


    //Abrir la app y al ejecutarse intent para ir al login
    private void openApp(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },4000);
    }
}