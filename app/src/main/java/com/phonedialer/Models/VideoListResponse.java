package com.phonedialer.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Android on 17/12/18.
 */

public class VideoListResponse implements Serializable {


    /**
     * data : [{"ID":"2","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_2.mp4","DateTime":"2018-12-14 09:30:00","IsPost":"0","PostDateTime":"2018-12-14 09:30:06","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"3","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_3.mp4","DateTime":"2018-12-14 10:00:00","IsPost":"0","PostDateTime":"2018-12-14 10:00:09","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"4","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_4.mp4","DateTime":"2018-12-14 10:30:00","IsPost":"0","PostDateTime":"2018-12-14 10:30:05","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"5","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_5.mp4","DateTime":"2018-12-14 11:00:00","IsPost":"0","PostDateTime":"2018-12-14 11:00:08","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"6","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_6.mp4","DateTime":"2018-12-14 12:00:00","IsPost":"0","PostDateTime":"2018-12-14 12:00:30","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"7","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_7.mp4","DateTime":"2018-12-14 12:30:00","IsPost":"0","PostDateTime":"2018-12-14 12:30:05","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"8","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_8.mp4","DateTime":"2018-12-14 15:00:00","IsPost":"0","PostDateTime":"2018-12-14 15:00:08","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"9","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_9.mp4","DateTime":"2018-12-14 16:00:00","IsPost":"0","PostDateTime":"0000-00-00 00:00:00","Reason":"","CreatedDate":"2018-12-11 00:12:13"},{"ID":"10","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_10.mp4","DateTime":"2018-12-14 17:00:00","IsPost":"0","PostDateTime":"0000-00-00 00:00:00","Reason":"","CreatedDate":"2018-12-11 00:12:13"},{"ID":"11","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_11.mp4","DateTime":"2018-12-14 17:30:00","IsPost":"0","PostDateTime":"2018-12-14 17:30:13","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"},{"ID":"12","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_12.mp4","DateTime":"2018-12-14 18:00:00","IsPost":"0","PostDateTime":"0000-00-00 00:00:00","Reason":"","CreatedDate":"2018-12-11 00:12:13"},{"ID":"13","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_13.mp4","DateTime":"2018-12-14 18:20:00","IsPost":"0","PostDateTime":"0000-00-00 00:00:00","Reason":"","CreatedDate":"2018-12-11 00:12:13"},{"ID":"14","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_14.mp4","DateTime":"2018-12-14 18:40:00","IsPost":"0","PostDateTime":"0000-00-00 00:00:00","Reason":"","CreatedDate":"2018-12-11 00:12:13"},{"ID":"1","Story":"http://ewisesolution.com/Insta/assets/uploads/story/story_1.mp4","DateTime":"2018-12-15 14:20:00","IsPost":"0","PostDateTime":"2018-12-14 09:00:09","Reason":"Video Not Uploded","CreatedDate":"2018-12-11 00:12:13"}]
     * Error : 200
     */

    private int Error;
    private List<DataBean> data;

    public int getError() {
        return Error;
    }

    public void setError(int Error) {
        this.Error = Error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * ID : 2
         * Story : http://ewisesolution.com/Insta/assets/uploads/story/story_2.mp4
         * DateTime : 2018-12-14 09:30:00
         * IsPost : 0
         * PostDateTime : 2018-12-14 09:30:06
         * Reason : Video Not Uploded
         * CreatedDate : 2018-12-11 00:12:13
         */

        private String ID;
        private String Story;
        private String DateTime;
        private String IsPost;
        private String PostDateTime;
        private String Reason;
        private String CreatedDate;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getStory() {
            return Story;
        }

        public void setStory(String Story) {
            this.Story = Story;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String DateTime) {
            this.DateTime = DateTime;
        }

        public String getIsPost() {
            return IsPost;
        }

        public void setIsPost(String IsPost) {
            this.IsPost = IsPost;
        }

        public String getPostDateTime() {
            return PostDateTime;
        }

        public void setPostDateTime(String PostDateTime) {
            this.PostDateTime = PostDateTime;
        }

        public String getReason() {
            return Reason;
        }

        public void setReason(String Reason) {
            this.Reason = Reason;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }
    }
}
