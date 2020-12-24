package com.example.todo_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.todo_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NavHostFragment navContainer = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_container);

        NavController controller = navContainer.getNavController();




    }
}