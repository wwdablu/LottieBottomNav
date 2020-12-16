package com.wwdablu.soumya.lottiebottomnavsample;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MailFragment extends Fragment {

    public interface ClickHandler {
        void onChangeMenuIcon();
    }

    private ClickHandler mClickHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mail, container, false);

        view.findViewById(R.id.btn_animate).setOnClickListener(clickListener);

        return view;
    }

    public void setClickHandler(ClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(mClickHandler != null) {
                mClickHandler.onChangeMenuIcon();
            }
        }
    };
}