package com.example.todo_app.ui.fragments;

import android.graphics.Color;
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

import com.example.todo_app.R;
import com.example.todo_app.model.TodoModel;
import com.example.todo_app.viewmodel.TodoViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.transition.MaterialContainerTransform;

import java.util.List;
import java.util.Map;

public class FragmentViewTodoList extends Fragment {

    MaterialCardView cardView;
    MaterialToolbar toolbar;
    TextView todoName;
    TextView todoDetail;
    TextView todoTime;


    TodoViewModel todoViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();
        View view = inflater.inflate(R.layout.fragment_view_todo_list, container, false);
        initViews(view);
        todoViewModel = new ViewModelProvider(requireActivity()).get(TodoViewModel.class);
        return view;
    }

    private void initViews(View view) {
        cardView = view.findViewById(R.id.shared_element_container);
        toolbar = view.findViewById(R.id.tool_bar_view_fragment);
        todoName = view.findViewById(R.id.item_view_text_view_todo_name);
        todoDetail = view.findViewById(R.id.item_view_text_view_todo_detail);
        todoTime = view.findViewById(R.id.item_view_text_view_todo_time);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialContainerTransform containerTransform = new MaterialContainerTransform();
        containerTransform.setDrawingViewId(R.id.nav_container);
        containerTransform.setScrimColor(Color.TRANSPARENT);
        containerTransform.setDuration(Integer.parseInt(getString(R.string.transition_time)));
        containerTransform.setAllContainerColors(MaterialColors.getColor(requireContext(), R.attr.colorSurface, R.attr.colorError));
        setSharedElementEnterTransition(containerTransform);

        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                sharedElements.put(names.get(0), cardView);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setNavigationOnClickListener(view1 -> Navigation.findNavController(view1).popBackStack());


        TodoModel todoModel = todoViewModel.getNote(FragmentViewTodoListArgs.fromBundle(getArguments()).getItemId());

        todoName.setText(todoModel.name);
        todoDetail.setText(todoModel.text);
        todoTime.setText(todoModel.time);

        startPostponedEnterTransition();

    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
