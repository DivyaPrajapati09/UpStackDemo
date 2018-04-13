package astics.com.upstackdemo.dbModel;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class DBModel {

    @Id
    public long id;

    public String imageId;

    public String likeCount;

    public String unlikeCount;

    public DBModel(String imageId, String likeCount, String unlikeCount) {
        this.imageId = imageId;
        this.likeCount = likeCount;
        this.unlikeCount = unlikeCount;
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

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getUnlikeCount() {
        return unlikeCount;
    }

    public void setUnlikeCount(String unlikeCount) {
        this.unlikeCount = unlikeCount;
    }
}
