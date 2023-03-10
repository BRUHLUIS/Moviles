package com.example.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_app);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                Intent homeIntent = new Intent(this, HomeApp.class);
                startActivity(homeIntent);
                return true;
            case R.id.nav_mostrarperros:
                Intent mostrarIntent = new Intent(this, MostrarPerros.class);
                startActivity(mostrarIntent);
                return true;
            case R.id.nav_registroperros:
                Intent registroIntent = new Intent(this, RegistroPerros.class);
                startActivity(registroIntent);
                return true;
            case R.id.nav_nosotros:
                Intent nosotrosIntent = new Intent(this, Nosotros.class);
                startActivity(nosotrosIntent);
                return true;
            case R.id.nav_contacto:
                Intent contactoIntent = new Intent(this, Contacto.class);
                startActivity(contactoIntent);
                return true;
            case R.id.itemSalir:
                Toast.makeText(this, "HA SALIDO CON EXITO", Toast.LENGTH_SHORT).show();
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
