package com.example.scrollshield.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.scrollshield.R;
import com.example.scrollshield.ShortsAdapter;
import com.example.scrollshield.ShortsData;
import com.example.scrollshield.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 shortsContainer;
    private List<ShortsData> shortsDataList;
    private ShortsAdapter shortsAdapter;

    private HomeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        shortsDataList = new ArrayList<>();
        shortsDataList.add(new ShortsData("5YoGwRkpa9A", "vedal9867", "Neuro talks back to her father", R.drawable.person_24dp));

        shortsContainer = root.findViewById(R.id.videoPager);

        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        viewModel.getShortsDataList().observe(getViewLifecycleOwner(), dataList -> {
            shortsDataList = dataList;
        });

        shortsAdapter = new ShortsAdapter(shortsDataList);
        shortsContainer.setAdapter(shortsAdapter);

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}