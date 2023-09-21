package com.kdapps.videoplayer.hdmaxplayer.video.volleyexample.pickimagecustom.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class pictureFacer implements Parcelable {
    public static final Creator<pictureFacer> CREATOR = new Creator<pictureFacer>() {
        /* class com.pinal.photoeditorphotocollage.simpleimagegallery.Model.pictureFacer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public pictureFacer createFromParcel(Parcel in) {
            return new pictureFacer(in);
        }

        @Override // android.os.Parcelable.Creator
        public pictureFacer[] newArray(int size) {
            return new pictureFacer[size];
        }
    };
    private String imageUri;
    private String picturName;
    private String picturePath;
    private String pictureSize;
    private Boolean selected = false;

    public pictureFacer() {
    }

    public pictureFacer(String picturName2, String picturePath2, String pictureSize2, String imageUri2) {
        this.picturName = picturName2;
        this.picturePath = picturePath2;
        this.pictureSize = pictureSize2;
        this.imageUri = imageUri2;
    }

    protected pictureFacer(Parcel in) {
        Boolean bool;
        boolean z = false;
        this.picturName = in.readString();
        this.picturePath = in.readString();
        this.pictureSize = in.readString();
        this.imageUri = in.readString();
        byte tmpSelected = in.readByte();
        if (tmpSelected == 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(tmpSelected == 1 ? true : z);
        }
        this.selected = bool;
    }

    public String getPicturName() {
        return this.picturName;
    }

    public void setPicturName(String picturName2) {
        this.picturName = picturName2;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath2) {
        this.picturePath = picturePath2;
    }

    public String getPictureSize() {
        return this.pictureSize;
    }

    public void setPictureSize(String pictureSize2) {
        this.pictureSize = pictureSize2;
    }

    public String getImageUri() {
        return this.imageUri;
    }

    public void setImageUri(String imageUri2) {
        this.imageUri = imageUri2;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setSelected(Boolean selected2) {
        this.selected = selected2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.picturName);
        dest.writeString(this.picturePath);
        dest.writeString(this.pictureSize);
        dest.writeString(this.imageUri);
        Boolean bool = this.selected;
        dest.writeByte((byte) (bool == null ? 0 : bool.booleanValue() ? 1 : 2));
    }
}
