package com.example.project2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;


public class PhotoFragment extends Fragment {
    public ImageView image;

    public static int currentIndex;
    public static int photolist[]={R.drawable.animal13,R.drawable.animal14,R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,R.drawable.animal18};
    public ImageView imageView[]=new ImageView[photolist.length];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
         //   CurrentPhotoIndex = getArguments().getInt(ARG_PARAM1);


        }


       /* getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                // We use a String here, but any type that can be put in a Bundle is supported
                String result = bundle.getString("bundleKey");
                // Do something with the result...*/


        //currentIndex=0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        ViewGroup PhotoView= (ViewGroup)inflater.inflate(R.layout.fragment_photo, container, false);
      image=PhotoView.findViewById(R.id.ImgVPhoto);
       Bundle bundle=getArguments();


    if(bundle==null)
    {
    currentIndex=0;
        image.setImageResource(photolist[currentIndex]);
        image.setTag(photolist[currentIndex]);
    }
    else
    {
        currentIndex=bundle.getInt("index");
        if(bundle.getBoolean("GoToNextImage")==true && bundle.getBoolean("GoToPreviousImage")==false )
        {


            currentIndex=currentIndex+1;
            image.setImageResource(photolist[currentIndex]);
            image.setTag(photolist[currentIndex]);
        }

        else if(bundle.getBoolean("GoToNextImage")==false && bundle.getBoolean("GoToPreviousImage")==true )
        {
            currentIndex=currentIndex-1;
            image.setImageResource(photolist[currentIndex]);
            image.setTag(photolist[currentIndex]);
        }
        else
        {
            image.setImageResource(photolist[currentIndex]);
            image.setTag(photolist[currentIndex]);
        }

    }


        return PhotoView ;
    }

    public void setCurrentIndex()
    {

    }
    public int getCurrentIndex()
    {
        return currentIndex;
    }



}