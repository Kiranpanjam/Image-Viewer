package com.example.project2;

import android.Manifest;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ControlsFragment.OnbuttonClicks {
    public static FragmentManager fragManager;
    ControlsFragment ControlsFragmentObj;
    PhotoFragment PhotoFragmentObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragManager=getSupportFragmentManager();
        if(findViewById(R.id.OuterConstraintLayout)!=null)
        {
            if(savedInstanceState!=null) {
                return;
            }

                if (findViewById(R.id.photoContainer) != null) {
                    if (savedInstanceState != null) {
                        return;
                    }
                    FragmentTransaction fragTransaction = fragManager.beginTransaction();
                     PhotoFragmentObj = new PhotoFragment();
                    fragTransaction.add(R.id.photoContainer, PhotoFragmentObj, "photo");
                    fragTransaction.commit();


                }

                if (findViewById(R.id.controlsContainer) != null) {
                    if (savedInstanceState != null) {
                        return;
                    }
                    FragmentTransaction fragmentTransaction = fragManager.beginTransaction();
                    ControlsFragmentObj = new ControlsFragment();
                    fragmentTransaction.add(R.id.controlsContainer, ControlsFragmentObj, "controls");
                    ControlsFragmentObj.SaveCurrentIndex(PhotoFragmentObj.getCurrentIndex());
                    fragmentTransaction.commit();

                }

        }
    }



    @Override
    public void setNextImage(int index) {

        PhotoFragmentObj=new PhotoFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("GoToNextImage",true);
        bundle.putBoolean("GoToPreviousImage",false);
        bundle.putInt("index",index);
        PhotoFragmentObj.setArguments(bundle);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().replace(R.id.photoContainer,PhotoFragmentObj,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        ControlsFragmentObj.SaveCurrentIndex(PhotoFragmentObj.getCurrentIndex());
    }

    @Override
    public void setPreviousImage(int index) {
        PhotoFragmentObj=new PhotoFragment();
        Bundle bundle=new Bundle();

        bundle.putBoolean("GoToNextImage",false);
        bundle.putBoolean("GoToPreviousImage",true);
        bundle.putInt("index",index);
        PhotoFragmentObj.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().replace(R.id.photoContainer,PhotoFragmentObj,null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        ControlsFragmentObj.SaveCurrentIndex(PhotoFragmentObj.getCurrentIndex());



    }
}