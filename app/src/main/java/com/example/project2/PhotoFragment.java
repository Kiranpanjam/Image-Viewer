package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;




public class PhotoFragment extends Fragment {
    public ImageView image;

    public static int currentIndex;
    public static int photolist[]={R.drawable.animal13,R.drawable.animal14,R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,R.drawable.animal18};
    ImageDetails SendImageDetails;
    //Interface to send data from this fragment to control_fragment
    public interface ImageDetails
    {
         void currentIndexNCount(int currentIndex,int PhotosCount);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        ViewGroup PhotoView= (ViewGroup)inflater.inflate(R.layout.fragment_photo, container, false);
      image=PhotoView.findViewById(R.id.ImgVPhoto);

      //get bundle arguments
       Bundle bundle=getArguments();


    if(bundle==null)
    {
        currentIndex=0;
        image.setImageResource(photolist[currentIndex]);
        image.setTag(photolist[currentIndex]);
        SendImageDetails.currentIndexNCount(currentIndex,photolist.length);

    }
    else
    {
        currentIndex=bundle.getInt("index");
        //To display next image
        if(bundle.getBoolean("GoToNextImage")&& !bundle.getBoolean("GoToPreviousImage") )
        {
            currentIndex=currentIndex+1;
            image.setImageResource(photolist[currentIndex]);
            image.setTag(photolist[currentIndex]);
        }
        //To display previous image
        else if(!bundle.getBoolean("GoToNextImage") && bundle.getBoolean("GoToPreviousImage") )
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
        SendImageDetails.currentIndexNCount(currentIndex,photolist.length);

    }


        return PhotoView ;
    }



//Attaching 'ImageDetails' interface to photo fragment by overriding 'onAttach' lifecycle method
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        try{
            SendImageDetails=(PhotoFragment.ImageDetails) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+" must implement currentIndexNCount ");
        }
    }

    // This is called when user exits side show to set image index after slide show is closed
    public void setImage(int index){
        currentIndex = index;
        image.setImageResource(photolist[index]);
        image.setTag(photolist[index]);
    }


}