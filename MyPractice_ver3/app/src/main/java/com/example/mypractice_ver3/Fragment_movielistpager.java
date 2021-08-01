package com.example.mypractice_ver3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mypractice_ver3.MainActivity;
import com.example.mypractice_ver3.R;

public class Fragment_movielistpager extends Fragment {

    MainActivity mainactivity;

    int resid_moviePoster = R.drawable.ic_all;
    String string_movieName = "영화 이름";
    int string_movieAge = 999;
    String string_movieDday = "개봉 디데이";
    double double_movieTicketRate = 0.0;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        mainactivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mainactivity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielistpager, container, false);

        ImageView moviePoster = (ImageView) rootView.findViewById(R.id.moviePoster);
        TextView movieName = (TextView) rootView.findViewById(R.id.movieName);
        TextView movieAge = (TextView) rootView.findViewById(R.id.movieAge);
        TextView movieDday = (TextView) rootView.findViewById(R.id.movieDday);
        TextView movieTicketRate = (TextView) rootView.findViewById(R.id.movieTicketRate);

        moviePoster.setBackgroundResource(resid_moviePoster);
        movieName.setText(string_movieName);
        movieAge.setText("관람등급 : " + string_movieAge);
        movieDday.setText(string_movieDday);
        movieTicketRate.setText("예매율 : " + double_movieTicketRate + "%");

        return rootView;
    }

    public void setMovieInfo(int moviePoster, String Name, int Age, String Dday, double TicketRate) {

        resid_moviePoster = moviePoster;
        string_movieName = Name;
        string_movieAge = Age;
        string_movieDday = Dday;
        double_movieTicketRate = TicketRate;
    }
}
