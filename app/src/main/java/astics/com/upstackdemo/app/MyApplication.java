package astics.com.upstackdemo.app;

import android.app.Application;

import astics.com.upstackdemo.dbModel.MyObjectBox;
import io.objectbox.BoxStore;

public class MyApplication extends Application {
    private BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;
    }


}
