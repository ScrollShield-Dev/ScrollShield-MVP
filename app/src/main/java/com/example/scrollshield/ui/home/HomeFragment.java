package com.example.scrollshield.ui.home;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import java.util.Objects;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPager2 shortsContainer;
    private List<ShortsData> shortsDataList;
    private ShortsAdapter shortsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        shortsDataList = new ArrayList<>();
        shortsDataList.add(new ShortsData(Uri.parse("android+resource://" + requireActivity().getPackageName() + "/" + R.raw.neuro_talks_back_to_her_father), "vedal9867", "Neuro talks back to her father", R.drawable.person_24dp));

        shortsContainer = root.findViewById(R.id.videoPager);

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