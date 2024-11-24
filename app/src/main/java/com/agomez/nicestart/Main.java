package com.agomez.nicestart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {
    private WebView miVisorWeb;
    private SwipeRefreshLayout swipeLayout; // Componente para refrescar contenido deslizando hacia abajo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Activa el diseño Edge-to-Edge
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeWebView(); // Inicializa y configura la WebView
        setupSwipeRefresh(); // Configura el SwipeRefreshLayout
    }


    // Inicializa y configura las propiedades de la WebView
    private void initializeWebView() {
        miVisorWeb = findViewById(R.id.vistaweb);
        registerForContextMenu(miVisorWeb); // Registra la WebView para mostrar un menú contextual

        WebSettings webSettings = miVisorWeb.getSettings();
        webSettings.setJavaScriptEnabled(true); // Habilita JavaScript
        webSettings.setBuiltInZoomControls(true); // Controles de zoom
        webSettings.setLoadWithOverviewMode(true); // Ajusta las páginas al tamaño de la pantalla
        webSettings.setUseWideViewPort(true); // Permite un viewport amplio

        miVisorWeb.loadUrl("https://definicion.com/wp-content/uploads/2022/09/imagen.jpg.webp"); // Carga una URL
    }

    // Configura el SwipeRefreshLayout para recargar la WebView al refrescar
    private void setupSwipeRefresh() {
        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(() -> {
            showSnackBar("Pagina MAIN recargada"); // Muestra un mensaje
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false); // Detiene la animación de refresco
        });
    }

    // Crea el menú contextual al realizar una pulsación larga
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu); // Infla el menú contextual desde el XML
    }

    // Crea el menú de opciones de la aplicación
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu); // Infla el menú desde el XML
        return true;
    }

    // Maneja las acciones seleccionadas en el menú contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.item1) {
            showToast("Item copied");
            return true;
        } else if (itemId == R.id.item2) {
            showToast("Downloading item...");
            return true;
        } else if (itemId == R.id.itemS) {
            showAlertDialogButtonClicked(); // Muestra un diálogo de alerta
            return true;
        }

        return false;
    }

    // Maneja las opciones seleccionadas en el menú principal
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        ConstraintLayout layout = findViewById(R.id.main);

        if (itemId == R.id.item2) {
            showSnackBarWithAction(layout, "Perfil", "UNDO"); // Muestra un mensaje con acción
            Intent intent = new Intent(this, Profile.class); // Navega a la pantalla de perfil
            startActivity(intent);
            return true;
        } else if (itemId == R.id.item3) {
            showSnackBarWithAction(layout, "Copiado", "UNDO");
            return true;
        } else if (itemId == R.id.item4) {
            showSnackBarWithAction(layout, "Ajustes", "UNDO");
            return true;
        } else if (itemId == R.id.itemS) {
            showAlertDialogButtonClicked(); // Muestra un diálogo de alerta
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Muestra un diálogo de alerta con botones
    private void showAlertDialogButtonClicked() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Titulo!!!")
                .setMessage("¿A dónde vamos?")
                .setIcon(R.drawable.user_logo)
                .setCancelable(false)
                .setPositiveButton("Scrolling", (dialog, which) -> {
                    startActivity(new Intent(Main.this, Login.class)); // Navega a la pantalla de Login
                    dialog.dismiss();
                })
                .setNegativeButton("Do nothing", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Muestra un mensaje usando Snackbar
    private void showSnackBar(String message) {
        ConstraintLayout layout = findViewById(R.id.main);
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    // Muestra un Snackbar con una acción asociada
    private void showSnackBarWithAction(ConstraintLayout layout, String message, String action) {
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction(action, view -> {
                    Snackbar.make(layout, "Action restored!", Snackbar.LENGTH_SHORT).show();
                });
        snackbar.show();
    }

    //Mensaje corto usando Toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
