package com.example.scrollshield;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.example.scrollshield.ui.home.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.scrollshield.databinding.ActivityMainBinding;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_ACCOUNT_NAME = "accountName";
    private static final String[] SCOPES = { YouTubeScopes.YOUTUBE_READONLY };

    private ActivityMainBinding binding;
    private GoogleAccountCredential credential;
    private List<ShortsData> shortsList;
    private HomeViewModel viewModel;

    private ActivityResultLauncher<Intent> recoverIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        Objects.requireNonNull(getSupportActionBar()).hide();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_filters, R.id.navigation_shop, R.id.navigation_home, R.id.navigation_badges, R.id.navigation_profile)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        recoverIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                getVideoListing();
            }
        });

        shortsList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        credential = GoogleAccountCredential.usingOAuth2(
                        getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());
        String username = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(PREF_ACCOUNT_NAME, null);

        credential.setSelectedAccountName(username);
        getVideoListing();
    }

    private void getVideoListing() {

        Exception ex = null;
        HttpTransport transport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        YouTube ytService = new com.google.api.services.youtube.YouTube.Builder(transport, jsonFactory, credential).setApplicationName("ScrollShield").build();

        ExecutorService requester = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        requester.execute(() -> {

            try {
                List<String> videoList = new ArrayList<String>();
//                YouTube.Videos.List req = ytService.videos().list("snippet. contentDetails, statistics");
//                VideoListResponse response = req
//                        .setChart("mostPopular")
//                        .setRegionCode("MY")
//                        .execute();

                YouTube.Search.List search = ytService.search().list("snippet");
                SearchListResponse resp2 = search
                        .setMaxResults((Integer.toUnsignedLong(25)))
                        .setOrder("viewCount")
                        .setType("video")
                        .setVideoDuration("short")
                        .setQ("Nature")
                        .execute();

            handler.post(() -> {
                for (SearchResult vid : resp2.getItems()) {
                    ShortsData newShorts = new ShortsData(vid.getId().getVideoId(), vid.getSnippet().getChannelTitle(), vid.getSnippet().getTitle(), R.drawable.person_24dp);
                    shortsList.add(newShorts);
                    viewModel.setShortsDataList(shortsList);
                }
            });

            } catch (Exception e) {
                if (e instanceof UserRecoverableAuthIOException) {
                   recoverIntent.launch(((UserRecoverableAuthIOException) e).getIntent());
                }
                throw new RuntimeException(e);
            }

        });
    }

}