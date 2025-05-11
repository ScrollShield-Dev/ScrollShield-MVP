package com.example.scrollshield.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.scrollshield.ContentUsageAdapter;
import com.example.scrollshield.ContentUsageData;
import com.example.scrollshield.R;
import com.example.scrollshield.RecentActivityAdapter;
import com.example.scrollshield.RecentActivityData;
import com.example.scrollshield.databinding.FragmentProfileBinding;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private RecyclerView contentUsageView;
    private RecyclerView recentActivityView;
    private ContentUsageAdapter contentUsageAdapter;
    private RecentActivityAdapter recentActivityAdapter;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayList<ContentUsageData> contentUsageData = new ArrayList<>();
        contentUsageData.add(new ContentUsageData("Mathematics", "2h 15m"));
        contentUsageData.add(new ContentUsageData("Science", "1h 45m"));
        contentUsageData.add(new ContentUsageData("Animals", "45m"));
        contentUsageData.add(new ContentUsageData("Nature", "30m"));

        ArrayList<RecentActivityData> recentActivityData = new ArrayList<>();
        recentActivityData.add(new RecentActivityData("Earned \"Night Runner\" badge", "2 hours ago"));
        recentActivityData.add(new RecentActivityData("Connected with CyberPunk99", "Yesterday"));
        recentActivityData.add(new RecentActivityData("Completed Neural Link challenge", "3 days ago"));

        contentUsageView = root.findViewById(R.id.content_usage_recyclerview);
        contentUsageAdapter = new ContentUsageAdapter(contentUsageData);
        contentUsageView.setAdapter(contentUsageAdapter);

        recentActivityView = root.findViewById(R.id.recent_activity_recyclerview);
        recentActivityAdapter = new RecentActivityAdapter(recentActivityData);
        recentActivityView.setAdapter(recentActivityAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}