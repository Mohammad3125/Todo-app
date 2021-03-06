package com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.R;
import com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory.utils.TodoDiffCallBack;
import com.example.todo_app.ui.fragments.FragmentHomeScreenDirectory.utils.TodoRecyclerViewAdapter;
import com.example.todo_app.viewmodel.TodoViewModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.transition.Hold;
import com.google.android.material.transition.MaterialElevationScale;

import java.util.List;
import java.util.Map;

import kotlinx.coroutines.AwaitKt;

public class FragmentHomeScreen extends Fragment {

    Button changeThemeButton;
    Button newNoteButton;

    RecyclerView recyclerView;
    TodoRecyclerViewAdapter recyclerViewAdapter;
    TodoViewModel todoViewModel;

    private int selectedPosition;
    private int defaultNightMode;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoViewModel = new ViewModelProvider(requireActivity()).get(TodoViewModel.class);

        setExitTransition(null);

        setReenterTransition(new MaterialElevationScale(true));

        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                RecyclerView.ViewHolder selectedViewHolder =
                        recyclerView.findViewHolderForItemId(selectedPosition);

                if (selectedViewHolder == null || selectedViewHolder.itemView == null)
                    return;

                sharedElements.put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.item_shared_element));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        changeThemeButton.setOnClickListener(v ->
        {
            defaultNightMode = AppCompatDelegate.getDefaultNightMode();
            if (defaultNightMode != AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });


        newNoteButton.setOnClickListener(v1 ->
                Navigation.findNavController(v1).
                        navigate(FragmentHomeScreenDirections.actionFragmentHomeScreenToFragmentMakeTodoList()));


        recyclerViewAdapter.setOnTodoItemClickListener((itemId, itemView) -> {

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(itemView, itemView.getTransitionName())
                    .build();

            if (defaultNightMode != AppCompatDelegate.MODE_NIGHT_YES)
                setExitTransition(new MaterialElevationScale(false));
            else setExitTransition(new Hold());

            selectedPosition = itemId;

            Navigation.findNavController(requireView()).
                    navigate(FragmentHomeScreenDirections.actionFragmentHomeScreenToFragmentViewTodoList(itemId), extras);

        });

    }


    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setHasFixedSize(false);

        recyclerViewAdapter = new TodoRecyclerViewAdapter(new TodoDiffCallBack());

        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        todoViewModel.getNotes().observe(getViewLifecycleOwner(), notes ->
                recyclerViewAdapter.submitList(notes));

        recyclerViewAdapter.setOnTodoItemDeleteListener(item -> {
            AlertDialog dialog = new MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogStyle).setTitle("Deleting Item")
                    .setMessage("Are you sure to delete this item?").setPositiveButton("YES", (a, b) -> todoViewModel.delete(item)).setNegativeButton("NO", null).
                            create();
            dialog.show();

        });

        recyclerViewAdapter.setOnItemsAdded(this::startPostponedEnterTransition);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);


        initView(view);

        initRecyclerView();

        postponeEnterTransition();

        return view;
    }

    private void initView(View view) {
        changeThemeButton = view.findViewById(R.id.button_change_theme);
        newNoteButton = view.findViewById(R.id.button_new_note);
        recyclerView = view.findViewById(R.id.todo_recycler_view);
    }
}




