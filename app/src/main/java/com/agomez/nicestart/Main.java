package com.agomez.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView texto = findViewById(R.id.textProfile);
        registerForContextMenu(texto);

    }
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        getMenuInflater().inflate(R.menu.menu_context, menu);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.item1) {
            Toast toast = Toast.makeText(this, "Item copied",
                    Toast.LENGTH_LONG);
            toast.show();

            return true;
        } else if (item.getItemId() == R.id.item2) {
            Toast toast2 = Toast.makeText(this, "Downloading item...",
                    Toast.LENGTH_LONG);
            toast2.show();
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item2) {

            final ConstraintLayout mLayout = findViewById(R.id.main);
            Snackbar snackbar = Snackbar
                                .make(mLayout, "Perfil", Snackbar.LENGTH_LONG)
                               .setAction("UNDO", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Snackbar snackbar1 = Snackbar.make(mLayout, "Perfil", Snackbar.LENGTH_SHORT);
                                        snackbar1.show();
                                   }
                               });

                        snackbar.show();

            Intent intentMain = new Intent(this,Profile.class);
            startActivity(intentMain);

        }
        if (id == R.id.item3) {
            final ConstraintLayout mLayout = findViewById(R.id.main);
            Snackbar snackbar = Snackbar
                    .make(mLayout, "Copiado", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mLayout, "Copiado", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();
        }
        if (id == R.id.item4) {
            final ConstraintLayout mLayout = findViewById(R.id.main);
            Snackbar snackbar = Snackbar
                    .make(mLayout, "Ajustes", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mLayout, "Ajustes", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();
        }


        return super.onOptionsItemSelected(item);
    }


}