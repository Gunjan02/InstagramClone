package com.example.admin.instagramclone;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class UniversalImageLoader {
    private static final int profile_image=R.drawable.ic_profile;
    private Context mContext;
    public UniversalImageLoader(Context context){
        mContext=context;
    }
    public ImageLoaderConfiguration getConfig(){
        DisplayImageOptions imageOptions=new DisplayImageOptions.Builder()
                .showImageOnLoading(profile_image)
                .showImageForEmptyUri(profile_image)
                .showImageOnFail(profile_image)
                .cacheOnDisk(true).cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration imageLoaderConfiguration=new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(imageOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100*1024*1024).build();
        return imageLoaderConfiguration;
    }

    public static void setImage(String img, ImageView imageView, final ProgressBar progressBar,String append){
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(append = img, imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(progressBar!=null){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
