package astics.com.upstackdemo;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ImageData implements Serializable {
    @Id
    public long id;
    public String url;   //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.gif",
    public String width; // ": "91",
    public String height;   //": "100",
    public String size;     //": "193115",
    public String mp4;  //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.mp4",
    public String mp4_size;     //": "18591",
    public String webp;    //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.webp",
    public String webp_size;   //": "115556"

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getMp4_size() {
        return mp4_size;
    }

    public void setMp4_size(String mp4_size) {
        this.mp4_size = mp4_size;
    }

    public String getWebp() {
        return webp;
    }

    public void setWebp(String webp) {
        this.webp = webp;
    }

    public String getWebp_size() {
        return webp_size;
    }

    public void setWebp_size(String webp_size) {
        this.webp_size = webp_size;
    }
}
