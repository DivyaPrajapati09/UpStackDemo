package astics.com.upstackdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by BHARAT on 4/12/2018.
 */

public class ResponseModel {

    @SerializedName("data")
    public List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    class Data {

        @SerializedName("images")
        public Images images;

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }
    }

    class Images {

        @SerializedName("fixed_height_small")
        public ImageData imageData;

        public ImageData getImageData() {
            return imageData;
        }

        public void setImageData(ImageData imageData) {
            this.imageData = imageData;
        }
    }

    class ImageData {
        String url;   //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.gif",
        String width; // ": "91",
        String height;   //": "100",
        String size;     //": "193115",
        String mp4;  //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.mp4",
        String mp4_size;     //": "18591",
        String webp;    //": "https://media2.giphy.com/media/ypqHf6pQ5kQEg/100.webp",
        String webp_size;   //": "115556"

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
}
