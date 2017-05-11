package com.example.areyen.mosqueapps1;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.areyen.mosqueapps1.Adapter.LruBitmapCache;

/**
 * Created by Android Dev on 4/10/2017.
 */

public class AppController extends Application {
    private static AppController instance;
    private RequestQueue requestQueue;
    private ImageLoader mImageLoader;

    public static AppController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(this);
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request request) {
        getRequestQueue().add(request);

    }


}
