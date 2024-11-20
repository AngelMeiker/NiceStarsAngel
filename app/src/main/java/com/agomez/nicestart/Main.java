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
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupWindowInsets();
        initializeWebView();
        setupSwipeRefresh();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initializeWebView() {
        miVisorWeb = findViewById(R.id.vistaweb);
        registerForContextMenu(miVisorWeb);

        WebSettings webSettings = miVisorWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        miVisorWeb.loadUrl("https://definicion.com/wp-content/uploads/2022/09/imagen.jpg.webp");
    }

    private void setupSwipeRefresh() {
        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(() -> {
            showSnackBar("Fancy a Snack while you refresh?");
            miVisorWeb.reload();
            swipeLayout.setRefreshing(false);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

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
            showAlertDialogButtonClicked();
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        ConstraintLayout layout = findViewById(R.id.main);

        if (itemId == R.id.item2) {
            showSnackBarWithAction(layout, "Perfil", "UNDO");
            Intent intent = new Intent(this,Profile.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.item3) {
            showSnackBarWithAction(layout, "Copiado", "UNDO");
            return true;
        } else if (itemId == R.id.item4) {
            showSnackBarWithAction(layout, "Ajustes", "UNDO");
            return true;
        } else if (itemId == R.id.itemS) {
            showAlertDialogButtonClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialogButtonClicked() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                .setTitle("Titulo!!!")
                .setMessage("¿A dónde vamos?")
                .setIcon(R.drawable.user_logo)
                .setCancelable(false)
                .setPositiveButton("Scrolling", (dialog, which) -> {
                    startActivity(new Intent(Main.this, Login.class)); //Hay que hacer cambio aqui
                    dialog.dismiss();
                })
                .setNegativeButton("Do nothing", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showSnackBar(String message) {
        ConstraintLayout layout = findViewById(R.id.main);
        Snackbar.make(layout, message, Snackbar.LENGTH_SHORT).show();
    }

    private void showSnackBarWithAction(ConstraintLayout layout, String message, String action) {
        Snackbar snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction(action, view -> {
                    Snackbar.make(layout, "Action restored!", Snackbar.LENGTH_SHORT).show();
                });
        snackbar.show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
