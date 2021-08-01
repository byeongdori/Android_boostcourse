package com.example.mypractice_ver3;

import android.os.Parcel;
import android.os.Parcelable;

public class Commentitem implements Parcelable {

    String user_id;
    String user_comment;
    int user_resid;
    int comment_like;
    int comment_time;

    public Commentitem(String user_id, String comment, int user_resid, int comment_like) {
        this.user_id = user_id;
        this.user_comment = comment;
        this.user_resid = user_resid;
        this.comment_like = comment_like;
        this.comment_time = 99;
    }

    public Commentitem(Parcel src) {
        this.user_id = src.readString();
        this.user_comment = src.readString();
        this.user_resid = src.readInt();
        this.comment_like = src.readInt();
        this.comment_time = 99;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment() {
        return user_comment;
    }

    public void setComment(String comment) {
        this.user_comment = comment;
    }

    public int getComment_like() {
        return comment_like;
    }

    public void setComment_like(int comment_like) {
        this.comment_like = comment_like;
    }

    public int getUser_resid() {
        return user_resid;
    }

    public void setUser_resid(int user_resid) {
        this.user_resid = user_resid;
    }

    public int getComment_time() {
        return comment_time;
    }

    public void setComment_time(int comment_time) {
        this.comment_time = comment_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator CREATOR = new Creator() {

        public com.example.mypractice_ver3.Commentitem createFromParcel(Parcel src) {
            return new com.example.mypractice_ver3.Commentitem(src);
        }

        public com.example.mypractice_ver3.Commentitem[] newArray(int size) {
            return new com.example.mypractice_ver3.Commentitem[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(user_id);
        dest.writeString(user_comment);
        dest.writeInt(user_resid);
        dest.writeInt(comment_like);
    }
}
