package com.example.someone.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

public class StepDetailsActivity extends AppCompatActivity implements ExoPlayer.EventListener{

    private StepModel stepsModel;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;
    private TextView descriptionTv;
    private long vPosition = 0;
    private ImageView recipeThumbnailIV;
    private boolean playWhenReady = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            vPosition = savedInstanceState.getLong("video_position");
            playWhenReady = savedInstanceState.getBoolean("player_state");
        }

        setContentView(R.layout.activity_step_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        stepsModel = (StepModel) intent.getSerializableExtra("recipe");

        playerView = findViewById(R.id.playerView);
        descriptionTv = findViewById(R.id.description);
        recipeThumbnailIV =  findViewById(R.id.recipeThumbnail);

        descriptionTv.setText(stepsModel.description);

        videoInit();
    }

    private void videoInit() {
        Uri uri;
        //if video url is not empty display video
        if(!stepsModel.videoURL.isEmpty()) {
            playerView.setVisibility(View.VISIBLE);
            recipeThumbnailIV.setVisibility(View.GONE);
            uri = Uri.parse(stepsModel.videoURL);
            initializePlayer(uri);
        }
        else { //Check to display thumbnail

            //If thumbnail is not empty....
            if(!stepsModel.thumbnailURL.isEmpty()) {
                String extension = stepsModel.thumbnailURL.substring(stepsModel.thumbnailURL.lastIndexOf("."));

                //check if its with video format and handle it as error
                switch (extension) {
                    case ".mp4":
                        playerView.setVisibility(View.GONE);
                        Log.e("DEBUG", "Cant load image to video player");
                        break;
                        //Else display image with picasso
                    default:
                        playerView.setVisibility(View.GONE);
                        recipeThumbnailIV.setVisibility(View.VISIBLE);
                        Picasso.with(this)
                                .load(stepsModel.thumbnailURL)
                                .into(recipeThumbnailIV);
                        break;
                }
                //if thumbnail was also empty, display nothing
            }  else{
                playerView.setVisibility(playerView.GONE);
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("video_position", vPosition);
        outState.putBoolean("player_state", playWhenReady);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player == null) {
            videoInit();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player != null) {
            playWhenReady = player.getPlayWhenReady();
            vPosition = player.getCurrentPosition();
            releasePlayer();
        }
    }

    private void initializePlayer(Uri uri){

        if(player == null){

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
            player.seekTo(vPosition);
            player.setPlayWhenReady(playWhenReady);

        }
        else{
            releasePlayer();
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
