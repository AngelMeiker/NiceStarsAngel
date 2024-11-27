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

            // Configuración de Glide para cargar una imagen en el ImageView (mGirl)
            ImageView mGirl = findViewById(R.id.girl);

            Glide.with(this)
                    .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/09b52687-0d7e-4eed-b23e-6906e094a792/ddf70w4-e276ce61-ebb3-48ee-90ee-d745d2421f27.png/v1/fill/w_670,h_1192/zekrom_phone_wallpaper_by_santithur_ddf70w4-pre.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MzQxNCIsInBhdGgiOiJcL2ZcLzA5YjUyNjg3LTBkN2UtNGVlZC1iMjNlLTY5MDZlMDk0YTc5MlwvZGRmNzB3NC1lMjc2Y2U2MS1lYmIzLTQ4ZWUtOTBlZS1kNzQ1ZDI0MjFmMjcucG5nIiwid2lkdGgiOiI8PTE5MjAifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.sXwsureIVKeL4PUmhf1NSGlsI8T2bZRKeNxLDgnjg-Y") // URL
                    .transition(DrawableTransitionOptions.withCrossFade(2000)) // Transición de 2000 ms
                    .centerCrop() // Ajusta la imagen para cubrir el ImageView
                    .placeholder(new ColorDrawable(this.getResources().getColor(R.color.verdecillo))) // Color de fondo temporal mientras se carga
                    .into(mGirl); // Carga la imagen en el ImageView

            return insets; // Devuelve los insets aplicados
        });
    }

    // Intent para viajar al activity "Sign Up"
    public void openSignup(View v) {
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }

    // Intent para viajar al activity "Main"
    public void openMain(View v) {
        Intent intentMain = new Intent(Login.this, Main.class);
        startActivity(intentMain);
    }

}
