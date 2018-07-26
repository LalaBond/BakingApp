package com.example.someone.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.someone.bakingapp.models.IngredientModel;
import com.example.someone.bakingapp.models.RecipeModel;
import com.example.someone.bakingapp.models.StepModel;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailsActivity extends AppCompatActivity implements ExoPlayer.EventListener{

    private StepModel stepsModel;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private TextView descriptionTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        stepsModel = (StepModel) intent.getSerializableExtra("recipe");

        playerView = findViewById(R.id.playerView);
        descriptionTv = findViewById(R.id.description);

        descriptionTv.setText(stepsModel.description);

        Uri uri;
        if(!stepsModel.videoURL.isEmpty()) {

            uri = Uri.parse(stepsModel.videoURL);
            initializePlayer(uri);
        }
        else if(!stepsModel.thumbnailURL.isEmpty()){
            uri = Uri.parse(stepsModel.thumbnailURL);
            initializePlayer(uri);
        }
        else{
            playerView.setVisibility(playerView.GONE);
        }

    }

    private void initializePlayer(Uri uri){

        if(player== null){

            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(this, trackSelector, loadControl);
            playerView.setPlayer(player);

            // Set the ExoPlayer.EventListener to this activity.
            player.addListener(this);

            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(this, "Recipe Video");
            MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(
                    this, userAgent), new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
            player.setPlayWhenReady(true);

        }
    }

    private void releasePlayer(){

        if(player != null) {
            player.stop();
            player.release();
            player = null;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        releasePlayer();
        finish();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
