package com.example.hoangbao.Model;

import java.util.List;

public class BookRoomModel {
    boolean success;
    String message;
    List<BookRoom> result;

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

    public List<BookRoom> getResult() {
        return result;
    }

    public void setResult(List<BookRoom> result) {
        this.result = result;
    }
}
