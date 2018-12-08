package com.example.umer.evsandroid;

public class Contact {
    private String Name;
    private  String PhoneNo;
    private  String ImageUrl;

    public Contact()
    {


    }

    public Contact(String _name,String _phoneno,String _imageurl)
    {

        setName(_name);
        setPhoneNo(_phoneno);
        setImageUrl(_imageurl);


    }

    public void setName(String Name) {
        this.Name=Name;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
