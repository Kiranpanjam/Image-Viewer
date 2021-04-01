package com.example.project2;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class GalleryFragment extends Fragment {
    int images[] = {R.drawable.animal13,R.drawable.animal14,R.drawable.animal15,R.drawable.animal16,R.drawable.animal17,R.drawable.animal18};

    String names[] = {"Tiger", "Lion", "Eagle","Dog", "Pigeon", "Snake"};

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        ListView listView = (ListView) view.findViewById(R.id.cfrag);

        CustomAdaptor customAdapter = new CustomAdaptor();
        listView.setAdapter(customAdapter);
        return view;

    }

    class CustomAdaptor extends BaseAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = getLayoutInflater().inflate(R.layout.rowlayout, null);
            ImageView imageView = view.findViewById(R.id.image);
            TextView textView = view.findViewById(R.id.name);

            imageView.setImageResource(images[position]);
            textView.setText(names[position]);
            return view;
        }
    }

}