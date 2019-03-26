package com.phonedialer.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android on 17/12/18.
 */

public class ImageListResponse implements Serializable {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("Error")
    @Expose
    private Integer error;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public class Datum implements Serializable{

        @SerializedName("ID")
        @Expose
        private String iD;
        @SerializedName("Image")
        @Expose
        private String image;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("DateTime")
        @Expose
        private String dateTime;
        @SerializedName("IsPost")
        @Expose
        private String isPost;
        @SerializedName("PostDateTime")
        @Expose
        private String postDateTime;
        @SerializedName("Reason")
        @Expose
        private String reason;
        @SerializedName("CreatedDate")
        @Expose
        private String createdDate;

        public String getID() {
            return iD;
        }

        public void setID(String iD) {
            this.iD = iD;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getIsPost() {
            return isPost;
        }

        public void setIsPost(String isPost) {
            this.isPost = isPost;
        }

        public String getPostDateTime() {
            return postDateTime;
        }

        public void setPostDateTime(String postDateTime) {
            this.postDateTime = postDateTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }
    }
}