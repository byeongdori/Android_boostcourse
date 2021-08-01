package com.example.mypractice_ver3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class comment_write extends AppCompatActivity {

    String Comment;
    int CommentRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        RatingBar CommentRating = (RatingBar) findViewById(R.id.CommentRating);
        EditText CommentWrite = (EditText) findViewById(R.id.CommentWrite);
        Button CommentSave = (Button) findViewById(R.id.Comment_save);
        Button CommentCancel = (Button) findViewById(R.id.Comment_cancel);


        CommentSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentRate = CommentRating.getNumStars();
                Comment = CommentWrite.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                com.example.mypractice_ver3.Commentitem writecomment = new com.example.mypractice_ver3.Commentitem("sample data", Comment, R.drawable.user1, 100);
                intent.putExtra("writedata" , writecomment);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        CommentCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}