package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SlideshowFragment extends Fragment {
    int currentIndex;
    SlideShowCheckbox SlideShowDetails;
    public  interface SlideShowCheckbox{
        //To send the Image-details to control_fragment
        public void ImageIndex(int index,int PhotosCount);

    }

    public static int images[] = {R.drawable.animal13,R.drawable.animal14,R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,R.drawable.animal18
    };

    ViewFlipper view_flipper;

    public SlideshowFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args=getArguments();
        currentIndex=args.getInt("CurrentIndex");

        requireActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                //To send the Imagedetails to control_fragment
                SlideShowDetails.ImageIndex(currentIndex,images.length);
                setEnabled(false);
                requireActivity().onBackPressed();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        view_flipper = view.findViewById(R.id.flipper);

        for(int i=0;i<images.length;i++)
        {
            slideshow(images[i]);
        }
        view_flipper.startFlipping();
        return view;
    }

    /*This method will create a image slide show using view flipper with animations*/
    public void slideshow(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        view_flipper.addView(imageView);
        view_flipper.setFlipInterval(4000);

        view_flipper.setInAnimation(getContext(), android.R.anim.slide_out_right);
        view_flipper.setInAnimation(getContext(), android.R.anim.slide_in_left);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        try{
            SlideShowDetails=(SlideshowFragment.SlideShowCheckbox) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+" must implement method,ImageIndex() ");
        }
    }
}
