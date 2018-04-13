package astics.com.upstackdemo.model;

import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("fixed_height_small")
    public ImageData imageData;

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }
}
