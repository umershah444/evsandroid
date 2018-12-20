package com.example.umer.evsandroid;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

public class Contact {
    private String Name;
    private  String PhoneNo;
    private Uri ImageUrl;

    public Contact(Context context)
    {

        ImageUrl = (new Uri.Builder())
                .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
                .authority(context.getResources().getResourcePackageName(R.drawable.user))
                .appendPath(context.getResources().getResourceTypeName(R.drawable.user))
                .appendPath(context.getResources().getResourceEntryName(R.drawable.user))
                .build();

        //ImageUrl=Uri.parse("@mipmap/ic_launcher_round");
    }

    public Contact(String _name,String _phoneno,Uri _imageurl)
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

    public Uri getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(Uri imageUrl) {
        ImageUrl = imageUrl;
    }
}
