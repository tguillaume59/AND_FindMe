package com.tguillaume.findme.views;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tguillaume.findme.R;

import java.util.ArrayList;

/**
 * Created by Avast on 24/04/2017.
 */

public class FDMSearchingInfos extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rView = inflater.inflate(R.layout.fragment_searching_infos, container, false);

        // avec quelles valeurs initialiser le spinner ?
        ArrayList<String> rDatasRoot = new ArrayList<String>();
        rDatasRoot.add("");

        Spinner rSpinner = (Spinner) rView.findViewById(R.id.searching_infos_spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getContext(),R.layout.support_simple_spinner_dropdown_item, rDatasRoot);
        rSpinner.setAdapter(spinnerArrayAdapter);

        return rView;
    }

    public void setName(String name) {
        View rView = getView();
        TextView rText = (TextView) rView.findViewById(R.id.searching_infos_textview);
        String rFind = R.string.find_guy + " " + name;
        rText.setText(rFind);
    }

    public void setAvatarPicture(Drawable sAvatar) {
        View rView = getView();
        ImageView rImage = (ImageView) rView.findViewById(R.id.searching_infos_imageview);
        rImage.setImageDrawable(sAvatar);
    }

    public void notifyDataChange(ArrayList<String> sList) {
        View rView = getView();
        Spinner rSpinner = (Spinner) rView.findViewById(R.id.searching_infos_spinner);
        ArrayAdapter<String> rSpinnerAdapter = (ArrayAdapter<String>) rSpinner.getAdapter();

        rSpinnerAdapter.clear();
        rSpinnerAdapter.addAll(sList);
        rSpinnerAdapter.notifyDataSetChanged();
    }
}
