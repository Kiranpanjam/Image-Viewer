package com.example.project2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class ControlsFragment extends Fragment {

     OnbuttonClicks onbuttonClicks;
     int currentIndex;
    ImageButton BackButton;
    ImageButton FwdButton;

    public interface OnbuttonClicks{
       // int getPreviousImageIndex();
        void setPreviousImage(int index);
        void setNextImage(int index);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
        //BackButton.setEnabled(false);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        ViewGroup FragmentView= (ViewGroup) inflater.inflate(R.layout.fragment_controls, container, false);

        BackButton=FragmentView.findViewById(R.id.btnBack);
        FwdButton=FragmentView.findViewById(R.id.btnFwd);
  //     PreviousImageIndex=onbuttonClicks.getPreviousImageIndex();

/*
       if(PreviousImageIndex<0)
       {
           BackButton.setEnabled(false);
       }
       else
       {
           BackButton.setEnabled(true);
       }
*/
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onbuttonClicks.setPreviousImage(currentIndex);

               // view.findViewWithTag("photo").findViewById(R.id.ImgVPhoto)

            }
        });

        //BackButton.setEnabled(false);

       FwdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onbuttonClicks.setNextImage(currentIndex);
            }
        });


        return  FragmentView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity=(Activity) context;
        try{
            onbuttonClicks=(OnbuttonClicks) activity;
        }
        catch (ClassCastException e)
        {
        throw new ClassCastException(activity.toString()+" must implement method/methods,getPreviousImage() ,getNextImage ");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public  void SaveCurrentIndex( int index)
    {
        currentIndex=index;
    }
}