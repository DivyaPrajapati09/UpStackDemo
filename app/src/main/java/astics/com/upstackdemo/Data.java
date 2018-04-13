package astics.com.upstackdemo;

import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Data {

    @Id
    public long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

}