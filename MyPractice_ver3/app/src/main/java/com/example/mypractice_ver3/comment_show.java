package com.example.mypractice_ver3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

public class comment_show extends AppCompatActivity {

    int Comment_index = 0;

    ListView list_allCommentView;
    RatingBar Total_Rating;
    TextView Total_Rating_Text;

    ArrayList<Commentitem> AllCommentitem = new ArrayList<>();
    CommentitemAdapter CommentAdapter = new CommentitemAdapter();

    API_moviecomment api_moviecomment;
    int movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_show);

        sendRequest();

        list_allCommentView = (ListView) findViewById(R.id.list_allCommentView);
        Total_Rating = (RatingBar) findViewById(R.id.Total_Rating);
        Total_Rating_Text = (TextView) findViewById(R.id.Total_Rating_text);

        Total_Rating.setRating(Float.valueOf(Total_Rating_Text.getText().toString()));

        /*Intent getdata = getIntent();
        AllCommentitem = getdata.getParcelableArrayListExtra("AllCommentData");

        while (Comment_index < AllCommentitem.size()) {
            CommentAdapter.addItem(AllCommentitem.get(Comment_index++));
        }*/

    }


    public class CommentitemAdapter extends BaseAdapter {
        ArrayList<com.example.mypractice_ver3.Commentitem> commentitems = new ArrayList();

        @Override
        public int getCount() { return commentitems.size(); }

        public void addItem(com.example.mypractice_ver3.Commentitem item) { commentitems.add(item); }

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

            com.example.mypractice_ver3.Commentitem item = commentitems.get(position);
            view.setUser_comment(item.getComment());
            view.setUser_id(item.getUser_id());
            view.setUser_image(item.getUser_resid());
            view.setUser_likecount(item.getComment_like());
            view.setUser_comment_time(item.getComment_time());

            return view;
        }
    }

    public void sendRequest() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readCommentList";
        url += "?" + "id=1" + "&limit=all";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponce(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    public void processResponce(String responce) {
        Gson gson = new Gson();
        api_moviecomment = gson.fromJson(responce, API_moviecomment.class);

        for (int i = 0; i < 10; i++) {
            AllCommentitem.add(new Commentitem(api_moviecomment.result.get(i).writer, api_moviecomment.result.get(i).contents, R.drawable.ic_facebook, api_moviecomment.result.get(i).recommend));
            CommentAdapter.addItem(AllCommentitem.get(i));
        }

        list_allCommentView.setAdapter(CommentAdapter);
    }
}