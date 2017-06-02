package com.android.highway;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/19.
 */
public class Equipment implements Serializable {
    private static final long serialVersionUID = -4222729720833382885L;
    private double latitude;//精度
    private double longtitude;//维度
    private Integer id;
    private String address;
    private String commState;
    private String batteryState;
    private String state;
    private String type;
    private String area;
    private String TSurvnodename;
    private String TSurvnoderoad;
    private Integer survnodeindex;
    private String baudrate;
    private String port;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommState() {
        return commState;
    }

    public void setCommState(String commState) {
        this.commState = commState;
    }

    public String getBatteryState() {
        return batteryState;
    }

    public void setBatteryState(String batteryState) {
        this.batteryState = batteryState;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTSurvnodename() {
        return TSurvnodename;
    }

    public void setTSurvnodename(String TSurvnodename) {
        this.TSurvnodename = TSurvnodename;
    }

    public String getTSurvnoderoad() {
        return TSurvnoderoad;
    }

    public void setTSurvnoderoad(String TSurvnoderoad) {
        this.TSurvnoderoad = TSurvnoderoad;
    }

    public Integer getSurvnodeindex() {
        return survnodeindex;
    }

    public void setSurvnodeindex(Integer survnodeindex) {
        this.survnodeindex = survnodeindex;
    }

    public String getBaudrate() {
        return baudrate;
    }

    public void setBaudrate(String baudrate) {
        this.baudrate = baudrate;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Equipment(Integer id, double latitude, double longtitude, String address, String commState, String batteryState, String state, String type, String area, String TSurvnodename, String TSurvnoderoad, Integer survnodeindex, String baudrate, String port) {
        this.id = id;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.address = address;
        this.commState = commState;
        this.batteryState = batteryState;
        this.state = state;
        this.type = type;
        this.area = area;
        this.TSurvnodename = TSurvnodename;
        this.TSurvnoderoad = TSurvnoderoad;
        this.survnodeindex = survnodeindex;
        this.baudrate = baudrate;
        this.port = port;
    }
    public Equipment() {}
    public static List<Equipment> Equipments = new ArrayList<Equipment>();
//    //测试用样例
//    static {
//        Equipments.add(new Equipment(2,30.230349,120.044257,"留泗路100号","通信异常","电池正常","开","栏杆机","区域1","栏杆机1-1","杭州绕城高速",0,"115200","COM4"));
//        Equipments.add(new Equipment(2,30.230052,120.047761,"留泗路100号","通信正常","电池正常","关","栏杆机","区域1","栏杆机1-1","杭州绕城高速",0,"115200","COM4"));
//        Equipments.add(new Equipment(2,30.23545,120.049432,"留泗路210号","通信正常","电池正常","开","栏杆机","区域1","栏杆机1-1","杭州绕城高速",0,"115200","COM4"));
//    }
}
