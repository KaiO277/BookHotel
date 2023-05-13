package com.example.hoangbao.Model;

import com.example.hoangbao.utils.Utils;

import java.util.List;

public class ShowRoom {

    int id, giaphong, sophong, totalPaymen, night;
    String tenkhachsan, image,  status, startDate;





    public String getTenkhachsan() {
        return tenkhachsan;
    }

    public void setTenkhachsan(String tenkhachsan) {
        this.tenkhachsan = tenkhachsan;
    }

    public String getImage() {
        return Utils.URL_BASE+"doan/public/uploads/"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSophong() {
        return sophong;
    }

    public void setSophong(int sophong) {
        this.sophong = sophong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setNight(int night) {
        this.night = night;
    }

    public int getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(int giaphong) {
        this.giaphong = giaphong;
    }

    public int getNight() {
        return night;
    }

    public int getTotalPaymen() {
        return getGiaphong()*getNight();
    }

    public void setTotalPaymen(int totalPaymen) {
        this.totalPaymen = totalPaymen;
    }
}
