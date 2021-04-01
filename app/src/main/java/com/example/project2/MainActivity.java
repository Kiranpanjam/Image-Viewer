package com.example.project2;

import android.Manifest;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ControlsFragment.OnbuttonClicks,PhotoFragment.ImageDetails,SlideshowFragment.SlideShowCheckbox {
    public static FragmentManager fragManager;
    ControlsFragment ControlsFragmentObj;
    PhotoFragment PhotoFragmentObj;
    SlideshowFragment slideshow_fragment;

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
                    fragmentTransaction.commit();

                }

        }
    }

    /*This method executes when Nextbutton is clicked
     * It will replace the photo fragment with photo fragment but with next image */

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

    }
    /*This method executes when backbutton is clicked
     * It will replace the photo fragment with photo fragment but with previous image */

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
    }


    /*This method executes when we check the gallery checkbox
     * It will replace the photo fragment with gallery fragment*/
    @Override
    public void openGallery(CheckBox id) {
        Toast.makeText(getApplicationContext(), "Gallery view loading!", Toast.LENGTH_SHORT).show();
        Fragment gallery_fragment = new GalleryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.photoContainer, gallery_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*This method executes when we uncheck the gallery checkbox
     * It will display the previous fragment in the stack*/
    @Override
    public void closeGallery(CheckBox id) {
        Toast.makeText(getApplicationContext(), "Gallery view closed!", Toast.LENGTH_SHORT).show();
        fragManager.popBackStack();

    }

    /*This method executes when we check the slide show checkbox
     * It will replace the main layout with slideshow_fragment*/
    @Override
    public void openSlideshow(int index) {
        Toast.makeText(getApplicationContext(), "Slideshow loading!", Toast.LENGTH_SHORT).show();
         slideshow_fragment = new SlideshowFragment();
        Bundle args=new Bundle();
        args.putInt("CurrentIndex",index);
        slideshow_fragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.OuterConstraintLayout, slideshow_fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        //index.setChecked(false);
    }






    @Override
    public void currentIndexNCount(int CurrentIndex,int PhotosCount) {

        ControlsFragmentObj=new ControlsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("CurrentIndex",CurrentIndex);
        bundle.putInt("PhotosCount",PhotosCount);
        ControlsFragmentObj.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().replace(R.id.controlsContainer,ControlsFragmentObj,null);

        fragmentTransaction.commit();

    }

    //This method is called on backnavigation button click (to exit slide show).This is used to send imageIndex and PhotoCount from SlideShow_fragment to Control_frahment and Photo_fragment
    // ..so that on exiting the slideshow, the screen would correctly display the normal mode of photoAlbum screen
    @Override
    public void ImageIndex(int index, int PhotosCount) {
        ControlsFragmentObj=new ControlsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("CurrentIndex",index);
        bundle.putInt("PhotosCount",PhotosCount);
        ControlsFragmentObj.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction().replace(R.id.controlsContainer,ControlsFragmentObj,null);

        fragmentTransaction.commit();
        PhotoFragmentObj.setImage(index);
    }
}