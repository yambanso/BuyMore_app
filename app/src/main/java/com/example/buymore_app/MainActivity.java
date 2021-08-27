package com.example.buymore_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.buymore_app.home_fragments.nortifications;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {


    private ImageView imageButton;
    MenuItem signout;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        DrawerLayout drawerlayout = findViewById(R.id.drawerlayout);
        signout = findViewById(R.id.menuSignOut);

        //opens navigation
        findViewById(R.id.menuImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);
        // the id supplied here is from the fragment
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView textTitle = findViewById(R.id.textTitle);

        //method sets title of the page we are in
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {
                textTitle.setText(destination.getLabel());

            }
        });
        imageButton = findViewById(R.id.nortification);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fr = getSupportFragmentManager();
                fr.beginTransaction()
                        .replace(R.id.navHostFragment, new nortifications())
                        .addToBackStack(null)
                        .commit();
            }
        });

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.menuSignOut);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                log_out();
                return true;
            }
        });



        }
    public void log_out() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, login.class));
    }
    }

