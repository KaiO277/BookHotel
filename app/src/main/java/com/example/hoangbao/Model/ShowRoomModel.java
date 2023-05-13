package com.example.hoangbao.Model;

import java.util.List;

public class ShowRoomModel {
    boolean success;
    String message;
    List<ShowRoom> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShowRoom> getResult() {
        return result;
    }

    public void setResult(List<ShowRoom> result) {
        this.result = result;
    }
}
