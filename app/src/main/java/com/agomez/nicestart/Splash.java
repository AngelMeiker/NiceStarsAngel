// Clase Splash - Primera pantalla de la aplicación (pantalla de carga con animación y transición al Login)

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
        // Habilita el diseño Edge-to-Edge para usar toda la pantalla, incluyendo áreas como la barra de estado
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Ajusta el padding para respetar las áreas del sistema (como la barra de estado y de navegación)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Glide: carga una imagen remota para el fondo con animaciones y placeholder
            ImageView mFondo = findViewById(R.id.fondo);
            Glide.with(this)
                    .load("https://img.freepik.com/free-vector/hexagonal-black-background-modern-design_1017-37442.jpg")
                    .transition(DrawableTransitionOptions.withCrossFade(2000)) // Transición con desvanecimiento de 2s
                    .centerCrop() // Ajusta la imagen al centro sin deformarla
                    .placeholder(new ColorDrawable(this.getResources().getColor(R.color.verdecillo))) // Imagen temporal mientras se carga
                    .into(mFondo);

            return insets;
        });

        // Llama a la función que inicia la transición a la próxima pantalla después del delay
        openApp();

        // Configura y empieza la animación para el logo
        ImageView thunder = findViewById(R.id.imgSplash);
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.blink); // Animación "parpadeo"
        thunder.startAnimation(myAnim); // Aplica la animación al logo
    }

    // Método que configura el retraso antes de abrir la pantalla de Login
    private void openApp() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() { // Ejecuta la acción después de 4s
            @Override
            public void run() {
                //Intent para abrir la activity "Login"
                Intent intent = new Intent(Splash.this, Login.class);
                // Asegura que no se pueda volver a la pantalla Splash presionando "atrás"
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent); // Inicia la actividad de Login
            }
        }, 4000); //4s de retraso
    }
}
