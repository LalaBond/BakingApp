package com.example.someone.bakingapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by someone on 7/18/18.
 */

public class StepModel implements Serializable {

    @SerializedName("id")
    public Integer id;

    @SerializedName("shortDescription")
    public String shortDescription;

    @SerializedName("description")
    public String description;

    @SerializedName("videoURL")
    public String videoURL;

    @SerializedName("thumbnailURL")
    public String thumbnailURL;
}
