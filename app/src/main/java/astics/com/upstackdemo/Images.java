package astics.com.upstackdemo;

import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Backlink;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Images {
    @Id
    public long id;

    @SerializedName("fixed_height_small")
    @Backlink
    public ImageData imageData;

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
