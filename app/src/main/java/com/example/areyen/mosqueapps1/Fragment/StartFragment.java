package com.example.areyen.mosqueapps1.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.areyen.mosqueapps1.R;

/**
 * Created by Android Dev on 3/30/2017.
 */
public class StartFragment extends Fragment {
    ImageButton about,contact,department,donate,event,facebook,news,twiter,wellcome;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.start_fragment,container,false);

        return view;

    }
}
