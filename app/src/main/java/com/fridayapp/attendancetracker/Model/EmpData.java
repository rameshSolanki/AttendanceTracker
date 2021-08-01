package com.fridayapp.attendancetracker.Model;

import com.google.gson.annotations.SerializedName;

public class EmpData {
    @SerializedName("emp_data_id")
    int emp_data_id;

    @SerializedName("emp__no")
    int emp__no;

    @SerializedName("created_date")
    String created_date;

    @SerializedName("update_date")
    String update_date;

    @SerializedName("longitude")
    String longitude;

    @SerializedName("latitude")
    String latitude;

    @SerializedName("response")
    private String response;

    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getResponse() {
        return response;
    }
    public int getEmp_data_id() {
        return emp_data_id;
    }

    public void setEmp_data_id(int emp_data_id) {
        this.emp_data_id = emp_data_id;
    }

    public int getEmp__no() {
        return emp__no;
    }

    public void setEmp__no(int emp__no) {
        this.emp__no = emp__no;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "EmpData{" +
                "response='" + response + '\'' +
                "emp_data_id=" + emp_data_id +
                ", emp__no=" + emp__no +
                ", created_date='" + created_date + '\'' +
                ", update_date='" + update_date + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
