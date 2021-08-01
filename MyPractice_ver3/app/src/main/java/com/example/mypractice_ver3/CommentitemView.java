package com.example.mypractice_ver3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CommentitemView extends LinearLayout {

    ImageView user_image;
    TextView user_id;
    TextView user_comment;
    TextView user_likecount;
    TextView user_comment_time;

    public CommentitemView(Context context) {
        super(context);
        init(context);
    }

    public CommentitemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.comment_item, this, true);

        user_image = (ImageView) findViewById(R.id.user_image);
        user_id = (TextView) findViewById(R.id.user_id);
        user_comment = (TextView) findViewById(R.id.user_comment);
        user_likecount = (TextView) findViewById(R.id.user_likecount);
        user_comment_time = (TextView) findViewById(R.id.Comment_time);
    }

    public void setUser_id(String username) { user_id.setText(username); }

    public void setUser_comment(String comment) {user_comment.setText(comment); }

    public void setUser_image(int resid) { user_image.setBackgroundResource(resid); }

    public void setUser_likecount(int count) { user_likecount.setText(String.valueOf(count)); }

    public void setUser_comment_time(int time) {user_comment_time.setText(String.valueOf(time) + "분 전"); }
}
