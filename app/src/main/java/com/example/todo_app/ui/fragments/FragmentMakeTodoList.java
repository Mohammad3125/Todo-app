package com.example.todo_app.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.model.TodoModel;
import com.example.todo_app.viewmodel.TodoViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.List;
import java.util.Map;


public class FragmentMakeTodoList extends Fragment {

    MaterialToolbar toolbar;
    MaterialButton pickTimeButton;
    TextView showTimeTextView;
    TodoViewModel todoViewModel;

    TextInputEditText todoNameEditText;
    TextInputEditText todoDetailEditText;

    String time;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_todo_list, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);



    }

    private void initViews(View view) {
        toolbar = view.findViewById(R.id.fragment_create_todo_tool_bar);
        pickTimeButton = view.findViewById(R.id.button_pick_time);
        showTimeTextView = view.findViewById(R.id.text_view_chosen_time);
        todoNameEditText = view.findViewById(R.id.note_name_edit_text);
        todoDetailEditText = view.findViewById(R.id.note_detail_edit_text);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(v -> Navigation.findNavController(view).popBackStack());


    }

    @Override
    public void onStart() {
        super.onStart();


        pickTimeButton.setOnClickListener(v ->
        {
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                    .build();

            timePicker.show(requireParentFragment().getParentFragmentManager(), "time-picker");

            timePicker.addOnPositiveButtonClickListener(v2 -> {
                int minute = timePicker.getMinute();
                time = String.valueOf(timePicker.getHour() + " : " + (minute < 10 ? "0" + minute : minute));
                showTimeTextView.setText(time);
            });

        });

        toolbar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.tool_bar_icon_check) {
                String todoName = todoNameEditText.getText().toString().trim();
                String todoDetail = todoDetailEditText.getText().toString().trim();
                todoViewModel.insert(new TodoModel(0, todoName, todoDetail, time));
                Navigation.findNavController(requireView()).popBackStack();
            }
            return true;
        });





    }
}
