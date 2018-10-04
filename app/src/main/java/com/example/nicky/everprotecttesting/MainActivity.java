package com.example.nicky.everprotecttesting;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Adapters.PagerAdapterForBanner;
import Adapters.RecycleAdapter_Cusine;
import Adapters.RecycleAdapter_Dish;
import BeanClasses.BeanClassForCusine;
import BeanClasses.BeanClassForDish;
import customfonts.MyTextView_Lato_Light;

public class MainActivity extends AppCompatActivity {



    private PagerAdapterForBanner pagerAdapterForBanner;

    private ViewPager viewPager;
    MyTextView_Lato_Light textView;
    CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;

    private ArrayList<BeanClassForDish> beanClassForDashboards;

    private RecyclerView recyclerView_dish;
    private RecycleAdapter_Dish mAdapter_dish;

    Integer image[] = {R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img};
    String dish_name[] = {"Paratha","Cheese Butter","Paneer Handi","Paneer Kopta","Chiken"};
    String dish_type[] = {"Punjabi","Maxican","Punjabi","Punjabi","Non Veg"};
    String price[] = {"Rs 500 / person (app.)","Rs 800 / person (app.)","Rs 400 / person (app.)","Rs 200 / person (app.)","Rs 500 / person (app.)"};



    private ArrayList<BeanClassForCusine> beanClassForCusines;

    private RecyclerView recyclerView_cusine;
    private RecycleAdapter_Cusine mAdapter_cusine;


    Integer image1[] = {R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img,R.drawable.white_img};
    String price1[] ={"Rs 350","Rs 200","Rs 550","Rs 400","Rs 250"};
    String cusine_name[] = {"Thai Cusine","Maxican","Desert","South Indian","Italian"};
    String city[] = {"Vadodara","Vadodara","Vadodara","Vadodara","Vadodara"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //NO PROBLEM !!!!
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        textView = (MyTextView_Lato_Light) findViewById(R.id.location);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_main);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M&&checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
        }
        else{
            LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
            Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                String place = hereLocation(location.getLatitude(), location.getLongitude());
                Pattern p=Pattern.compile("India");
                Matcher m=p.matcher(place);
                if(m.find())
                {textView.setText(place);}
                else
                {
                    Snackbar snackbar = Snackbar.make(coordinatorLayout, "This App is only available in India", Snackbar.LENGTH_INDEFINITE);
                    snackbar.show();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,"Not Found!",Toast.LENGTH_SHORT).show();
            }
        }

//        Dish Recyclerview Code

        recyclerView_dish = (RecyclerView) findViewById(R.id.recyclerview_dish);
        beanClassForDashboards = new ArrayList<>();

        recyclerView_dish.setNestedScrollingEnabled(false);


        for (int i = 0; i < image.length; i++) {
            BeanClassForDish beanClassForRecyclerView_contacts = new BeanClassForDish(image[i],dish_name[i],dish_type[i],price[i]);


            beanClassForDashboards.add(beanClassForRecyclerView_contacts);
        }


        mAdapter_dish = new RecycleAdapter_Dish(MainActivity.this,beanClassForDashboards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_dish.setLayoutManager(mLayoutManager);
        recyclerView_dish.setItemAnimator(new DefaultItemAnimator());
        recyclerView_dish.setAdapter(mAdapter_dish);




        //        Cusine Recyclerview Code

        recyclerView_cusine = (RecyclerView) findViewById(R.id.recyclerview_cusine);
        beanClassForCusines = new ArrayList<>();
        recyclerView_cusine.setNestedScrollingEnabled(false);x


        for (int i = 0; i < image1.length; i++) {
            BeanClassForCusine beanClassForCusine = new BeanClassForCusine(image1[i],price1[i],cusine_name[i],city[i]);


            beanClassForCusines.add(beanClassForCusine);
        }


        mAdapter_cusine = new RecycleAdapter_Cusine(MainActivity.this,beanClassForCusines);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView_cusine.setLayoutManager(mLayoutManager1);
        recyclerView_cusine.setItemAnimator(new DefaultItemAnimator());
        recyclerView_cusine.setAdapter(mAdapter_cusine);





        /*Banner ViewPager Code*/



        viewPager = (ViewPager) findViewById(R.id.viewpager);



        pagerAdapterForBanner = new PagerAdapterForBanner(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapterForBanner);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch(requestCode){
            case 1000:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    LocationManager locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    Location location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    try {
                        String place = hereLocation(location.getLatitude(), location.getLongitude());
                        Pattern p=Pattern.compile("India");
                        Matcher m=p.matcher(place);
                        if(m.find())
                        {textView.setText(place);}
                        else
                        {
                            Snackbar snackbar = Snackbar.make(coordinatorLayout, "This App is only available in India ", Snackbar.LENGTH_INDEFINITE);
                            snackbar.show();
                        }
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"Not Found!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Permission Not Granted!",Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private String hereLocation(double lat, double lon) {
        //String cityName= "";
        //String stateName="";
        //String postalCode="";
        //String country="";
        String address="";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        //Geocoding is the process of transforming a street address or other description of a location into a (latitude, longitude) coordinate.
        // Reverse geocoding is the process of transforming a (latitude, longitude) coordinate into a (partial) Address
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(lat,lon,10);
            if (addresses.size()>0){
                for(Address adr:addresses){
                    if(adr.getLocality()!=null&&adr.getLocality().length()>0){
                        //cityName=adr.getLocality();
                        //stateName=adr.getAdminArea();
                        //postalCode=adr.getPostalCode();
                        //country=adr.getCountryName();
                        address=adr.getAddressLine(0);
                        break;
                    }

                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return address;
    }
}
