package com.example.a33consumowebrecycler.Helper;

import com.example.a33consumowebrecycler.Remote.IMenuRequest;
import com.example.a33consumowebrecycler.Remote.RetrofitClient;

public class Common {

    public static IMenuRequest getMenuRequest(){
        return RetrofitClient.getClient("https://api.androidhive.info/").create(IMenuRequest.class);
    }
}
