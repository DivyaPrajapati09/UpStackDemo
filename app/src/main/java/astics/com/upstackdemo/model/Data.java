package astics.com.upstackdemo.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    public String imageId;

    @SerializedName("images")
    public Images images;

    public Data(Images images, String imageId) {
        this.images = images;
        this.imageId = imageId;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

}