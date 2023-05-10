package com.example.hoangbao.Model;

import com.example.hoangbao.utils.Utils;

import java.io.Serializable;

public class Room implements Serializable {
    int sophong, giaphong;
    String tenkhachsan, mota, khuvuc, diachi, sodienthoai, image;

//    public Room(int sophong,  String tenkhachsan,int giaphong, String mota, String khuvuc, String diachi, String sodienthoai, String image) {
//        this.sophong = sophong;
//        this.giaphong = giaphong;
//        this.tenkhachsan = tenkhachsan;
//        this.mota = mota;
//        this.khuvuc = khuvuc;
//        this.diachi = diachi;
//        this.sodienthoai = sodienthoai;
//        this.image = image;
//    }

    public int getSophong() {
        return sophong;
    }

    public void setSophong(int sophong) {
        this.sophong = sophong;
    }

    public int getGiaphong() {
        return giaphong;
    }

    public void setGiaphong(int giaphong) {
        this.giaphong = giaphong;
    }

    public String getTenkhachsan() {
        return tenkhachsan;
    }

    public void setTenkhachsan(String tenkhachsan) {
        this.tenkhachsan = tenkhachsan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getImage() {
        return Utils.URL_BASE+"doan/public/uploads/"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
