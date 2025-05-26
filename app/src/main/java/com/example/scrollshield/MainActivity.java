package com.example.scrollshield;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.scrollshield.databinding.ActivityMainBinding;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String PREF_ACCOUNT_NAME = "accountName";

    private ActivityMainBinding binding;
    private GoogleAccountCredential credential;

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

        String username = getPreferences(Context.MODE_PRIVATE).getString(PREF_ACCOUNT_NAME, null);
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
                YouTube.Videos.List req = ytService.videos().list("snippet. contentDetails, statistics");
                VideoListResponse response = req.setChart("mostPopular").setRegionCode("MY").execute();

            handler.post(() -> {
                for (Video vid : response.getItems()) {

                }
            });

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }

}