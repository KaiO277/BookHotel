package com.example.hoangbao.Model;

import java.util.List;

public class RoomModel {
    boolean success;
    String message;
    List<Room> result;

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

    public List<Room> getResult() {
        return result;
    }

    public void setResult(List<Room> result) {
        this.result = result;
    }
}
