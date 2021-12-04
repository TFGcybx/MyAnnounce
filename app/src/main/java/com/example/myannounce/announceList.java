package com.example.myannounce;

public class announceList {
    private String _id;
    private String UName;
    private String Title;
    private String Details;
    private String Date;

    public String get_id(){return _id;}

    public void set_id(String _id){this._id=_id;}

    public String getUName() {
        return UName;
    }

    public void setUName(String uName) {
        UName = uName;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate(){return Date;}

    public void setDate(String date){Date=date;}

}

