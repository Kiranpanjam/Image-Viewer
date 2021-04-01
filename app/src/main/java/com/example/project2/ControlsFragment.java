package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;


public class ControlsFragment extends Fragment {

     OnbuttonClicks onbuttonClicks;
     int currentIndex;
     int PhotosCount;
    ImageButton BackButton;
    ImageButton FwdButton;
    CheckBox gallery_checkbox;
    CheckBox slideshow_checkbox;

    //Interface to send data from this fragment to photo_fragment
    public interface OnbuttonClicks{
        void setPreviousImage(int index);
        void setNextImage(int index);
        void openGallery(CheckBox checkBoxId);
        void closeGallery(CheckBox checkBoxId);
        void openSlideshow(int index);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        ViewGroup FragmentView= (ViewGroup) inflater.inflate(R.layout.fragment_controls, container, false);

        BackButton=FragmentView.findViewById(R.id.btnBack);
        FwdButton=FragmentView.findViewById(R.id.btnFwd);
        gallery_checkbox=FragmentView.findViewById(R.id.ckGalleryView);
        slideshow_checkbox=FragmentView.findViewById(R.id.ckSlideShow);

        //get bundle Arguments
        Bundle args=getArguments();
        if(args!=null)
        {
            currentIndex=args.getInt("CurrentIndex");
            PhotosCount=args.getInt("PhotosCount");

        }

        //Conditions to enable and disable buttons
        if(currentIndex==0 && PhotosCount>1)
        {
            BackButton.setEnabled(false);
            BackButton.setAlpha((float) 0.5);
            FwdButton.setEnabled(true);
            FwdButton.setAlpha((float) 1);

        }
        else if(currentIndex==0 && PhotosCount==1)
        {
            BackButton.setEnabled(false);
            BackButton.setAlpha((float) 0.5);
            FwdButton.setEnabled(false);
            FwdButton.setAlpha((float) 0.5);
        }
        else if(currentIndex == PhotosCount-1)
        {
            BackButton.setEnabled(true);
            BackButton.setAlpha((float) 1);
            FwdButton.setEnabled(false);
            FwdButton.setAlpha((float) 0.5);
        }
        else
        {
            BackButton.setEnabled(true);
            BackButton.setAlpha((float) 1);
            FwdButton.setEnabled(true);
            FwdButton.setAlpha((float) 1);
        }




        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling interface method to send 'currentIndex' from Control_Fragment to Photo_fragment So that it can replace the image with previus Image
                onbuttonClicks.setPreviousImage(currentIndex);

            }
        });

       FwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling interface method to send 'currentIndex' from Control_Fragment to Photo_fragment So that it can replace the image with Next Image
                onbuttonClicks.setNextImage(currentIndex);
            }
        });

        //Functionality for onclick gallery checkbox
        gallery_checkbox.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(gallery_checkbox.isChecked()){
                onbuttonClicks.openGallery(gallery_checkbox);
                BackButton.setEnabled(false);
                BackButton.setAlpha((float) 0.5);
                FwdButton.setEnabled(false);
                FwdButton.setAlpha((float) 0.5);
            }
            else{
                onbuttonClicks.closeGallery(gallery_checkbox);
                BackButton.setEnabled(true);
                BackButton.setAlpha((float) 1);
                FwdButton.setEnabled(true);
                FwdButton.setAlpha((float) 1);
            }
           }
        }
        );

        slideshow_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(slideshow_checkbox.isChecked()){
                    onbuttonClicks.openSlideshow(currentIndex);
                    BackButton.setEnabled(false);
                    BackButton.setAlpha((float) 0.5);
                    FwdButton.setEnabled(false);
                    FwdButton.setAlpha((float) 0.5);
                }
                else{

                    BackButton.setEnabled(true);
                    BackButton.setAlpha((float) 1);
                    FwdButton.setEnabled(true);
                    FwdButton.setAlpha((float) 1);
                }
            }
        });

        return  FragmentView;
    }


    //Attaching 'OnbuttonClicks' interface to control fragment Activity by overriding 'onAttach' lifecycle method
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        try{
            onbuttonClicks=(OnbuttonClicks) activity;
        }
        catch (ClassCastException e)
        {
        throw new ClassCastException(activity.toString()+" must implement method/methods,getPreviousImage(), getNextImage(), closeGallery(), openGallery() ");
        }
    }



}