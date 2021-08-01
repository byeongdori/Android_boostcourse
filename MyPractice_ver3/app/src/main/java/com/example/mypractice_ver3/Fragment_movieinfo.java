package com.example.mypractice_ver3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mypractice_ver3.Commentitem;
import com.example.mypractice_ver3.CommentitemView;
import com.example.mypractice_ver3.MainActivity;
import com.example.mypractice_ver3.R;
import com.example.mypractice_ver3.comment_show;
import com.example.mypractice_ver3.comment_write;

import java.util.ArrayList;

public class Fragment_movieinfo extends Fragment {


    MainActivity mainactivity;

    int FROM_COMMENT_WRITE = 101;

    boolean like_pressed = false;
    boolean dislike_pressed = false;
    int like_countnum = 14;
    int dislike_countnum = 8;
    int Commentitem_index = 0;

    ListView list_commentView;

    CommentitemAdapter CommentAdapter = new CommentitemAdapter();
    ArrayList<Commentitem> Commentitem_data = new ArrayList<>();

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

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movieinfo, container, false);

        Button like_button = (Button) rootView.findViewById(R.id.like_button);
        TextView like_count = (TextView) rootView.findViewById(R.id.like_count);
        like_count.setText(String.valueOf(like_countnum));

        Button dislike_button = (Button) rootView.findViewById(R.id.dislike_button);
        TextView dislike_count = (TextView) rootView.findViewById(R.id.dislike_count);
        dislike_count.setText(String.valueOf(dislike_countnum));

        Commentitem_data.add(new Commentitem("superbottlegod", "정말 재미있을까요", R.drawable.user1, 7));
        Commentitem_data.add(new Commentitem("abcedfe", "정말 재미있을것같아요", R.drawable.user1, 14));
        Commentitem_data.add(new Commentitem("adddddddd", "재미없어요", R.drawable.user1, 1000));
        Commentitem_data.add(new Commentitem("adewfeewfwefw", "재미없을것 같아요", R.drawable.user1, 1020));
        Commentitem_data.add(new Commentitem("kimbyeongju", "자~ 드가자~", R.drawable.user1, 20));
        CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));
        CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));
        CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));
        CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));
        CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));

        list_commentView = (ListView) rootView.findViewById(R.id.listcomment);
        list_commentView.setAdapter(CommentAdapter);

        list_commentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.ScrollView);
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        TextView ToCommentWrite = (TextView) rootView.findViewById(R.id.Tocommentwrite);
        ToCommentWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CommentWrite = new Intent(mainactivity.getApplicationContext(), comment_write.class);
                startActivityForResult(CommentWrite, FROM_COMMENT_WRITE);
            }
        });

        Button ToCommentShow = (Button) rootView.findViewById(R.id.ToCommentShow);
        ToCommentShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CommentShow = new Intent(mainactivity.getApplicationContext(), comment_show.class);
                CommentShow.putParcelableArrayListExtra("AllCommentData", Commentitem_data);
                startActivity(CommentShow);
            }
        });

        like_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(like_pressed && !dislike_pressed) {
                    like_countnum--;
                    like_count.setText(String.valueOf(like_countnum));
                    like_button.setBackgroundResource(R.drawable.ic_thumb_up);
                }
                else if(!like_pressed) {
                    if(dislike_pressed) {
                        dislike_countnum--; like_countnum++;
                        dislike_button.setBackgroundResource(R.drawable.ic_thumb_down);
                        dislike_count.setText(String.valueOf(dislike_countnum));
                        like_button.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                        like_count.setText(String.valueOf(like_countnum));

                        dislike_pressed = !dislike_pressed;
                    }
                    else
                    {
                        like_countnum++;
                        like_button.setBackgroundResource(R.drawable.ic_thumb_up_selected);
                        like_count.setText(String.valueOf(like_countnum));
                    }
                }
                like_pressed = !like_pressed;
            }
        });

        dislike_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dislike_pressed && !like_pressed) {
                    dislike_countnum--;
                    dislike_count.setText(String.valueOf(dislike_countnum));
                    dislike_button.setBackgroundResource(R.drawable.ic_thumb_down);
                } else if (!dislike_pressed) {
                    if (like_pressed) {
                        like_countnum--;
                        dislike_countnum++;
                        like_button.setBackgroundResource(R.drawable.ic_thumb_up);
                        like_count.setText(String.valueOf(like_countnum));
                        dislike_button.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                        dislike_count.setText(String.valueOf(dislike_countnum));

                        like_pressed = !like_pressed;
                    } else {
                        dislike_countnum++;
                        dislike_button.setBackgroundResource(R.drawable.ic_thumb_down_selected);
                        dislike_count.setText(String.valueOf(dislike_countnum));
                    }
                }
                dislike_pressed = !dislike_pressed;
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && data != null) {
            Commentitem item = (Commentitem) data.getParcelableExtra("writedata");
            Commentitem_data.add(new Commentitem(item.getUser_id(), item.getComment(), item.getUser_resid(), item.getComment_like()));
            CommentAdapter.addItem(Commentitem_data.get(Commentitem_index++));
            list_commentView.setAdapter(CommentAdapter);
        }
    }

    public class CommentitemAdapter extends BaseAdapter {
        ArrayList<Commentitem> commentitems = new ArrayList();

        @Override
        public int getCount() { return commentitems.size(); }

        public void addItem(Commentitem item) { commentitems.add(item); }

        @Override
        public Object getItem(int position) { return commentitems.get(position); }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CommentitemView view = null;
            if (convertView == null) {
                view = new CommentitemView(parent.getContext());
            }
            else {
                view = (CommentitemView) convertView;
            }

            Commentitem item = commentitems.get(position);
            view.setUser_comment(item.getComment());
            view.setUser_id(item.getUser_id());
            view.setUser_image(item.getUser_resid());
            view.setUser_likecount(item.getComment_like());
            view.setUser_comment_time(item.getComment_time());

            return view;
        }
    }
}