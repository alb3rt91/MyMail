package com.example.mymail;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Spinner emailSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); // Asegúrate de tener un Toolbar en tu activity_main.xml
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        emailSpinner = navigationView.getHeaderView(0).findViewById(R.id.email_spinner);

        // Configura el Spinner con las direcciones de correo
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.email_addresses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emailSpinner.setAdapter(adapter);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            selectDrawerItem(item);
            return true;
        });

        // Carga el fragment inicial (por ejemplo, Bandeja de entrada)
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new InboxFragment()).commit();
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment;
        Class fragmentClass;
        int itemId = menuItem.getItemId();

        if (itemId == R.id.nav_inbox) {
            fragmentClass = InboxFragment.class;
        } else if (itemId == R.id.nav_sent) {
            fragmentClass = SentFragment.class;
        } else if (itemId == R.id.nav_drafts) {
            fragmentClass = DraftsFragment.class;
        } else if (itemId == R.id.nav_spam) {
            fragmentClass = DraftsFragment.class;
        } else {
            fragmentClass = InboxFragment.class; // Fragment por defecto
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}