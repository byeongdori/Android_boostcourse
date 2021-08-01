package com.example.mypractice_ver3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Fragment_movielist extends Fragment {

    MainActivity mainactivity;

    ViewPager movieViewPager;
    Fragment_movielistpager moviePager1;
    Fragment_movielistpager moviePager2;
    Fragment_movielistpager moviePager3;
    Fragment_movielistpager moviePager4;


    API_movieinfo api_movieinfo;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movielist, container, false);

        movieViewPager = (ViewPager) rootView.findViewById(R.id.movieViewPager);
        movieViewPager.setOffscreenPageLimit(3);

        mainactivity.sendRequest();

        MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager());
        moviePager1 = new Fragment_movielistpager();
        //moviePager1.setMovieInfo(R.drawable.image1, api_movieinfo.result.get(0).title, api_movieinfo.result.get(0).grade, api_movieinfo.result.get(0).date, api_movieinfo.result.get(0).reservation_rate);
        moviePagerAdapter.addItem(moviePager1);
        moviePager2 = new Fragment_movielistpager();
        moviePager2.setMovieInfo(R.drawable.image2, "공조", 15, "D - 4", 2.4);
        moviePagerAdapter.addItem(moviePager2);
        moviePager3 = new Fragment_movielistpager();
        moviePager3.setMovieInfo(R.drawable.image3, "더 킹", 15, "상영중", 99.4);
        moviePagerAdapter.addItem(moviePager3);
        moviePager4 = new Fragment_movielistpager();
        moviePager4.setMovieInfo(R.drawable.image4, "레지던트 이블", 19, "개봉 미정", 0.0);
        moviePagerAdapter.addItem(moviePager4);
        movieViewPager.setAdapter(moviePagerAdapter);

        Button toMovieInfo = (Button) rootView.findViewById(R.id.toMovieInfo);
        toMovieInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainactivity.FragmentChange(movieViewPager.getCurrentItem());
            }
        });
        return rootView;
    }

    class MoviePagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<Fragment> items = new ArrayList<>();

        public MoviePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

    }

}
