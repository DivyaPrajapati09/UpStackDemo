package astics.com.upstackdemo;

import android.app.Application;

import io.objectbox.BoxStore;


public class MyApplication extends Application {
    private BoxStore boxStore;
//   private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(this).build();
    }
}
