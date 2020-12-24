package com.example.todo_app.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.todo_app.R;

public class FragmentHomeScreen extends Fragment {

    Button changeThemeButton;

    @Override
    public void onStart() {
        super.onStart();
        changeThemeButton.setOnClickListener(v ->
        {
            int defaultNightMode = AppCompatDelegate.getDefaultNightMode();
            if (defaultNightMode != AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        changeThemeButton = view.findViewById(R.id.button_change_theme);
    }
}




