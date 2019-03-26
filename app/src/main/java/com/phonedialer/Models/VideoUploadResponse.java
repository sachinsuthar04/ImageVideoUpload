package com.phonedialer.Models;

import java.io.Serializable;

/**
 * Created by Android on 17/12/18.
 */

public class VideoUploadResponse implements Serializable{

    /**
     * Error : 200
     * Message : Story uploaded successfully.
     */

    private int Error;
    private String Message;

    public int getError() {
        return Error;
    }

    public void setError(int Error) {
        this.Error = Error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
