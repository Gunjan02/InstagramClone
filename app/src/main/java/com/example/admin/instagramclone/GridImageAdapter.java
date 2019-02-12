package com.example.admin.instagramclone;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class GridImageAdapter extends ArrayAdapter<String> {
    private Context mcontext;
    private LayoutInflater mInflater;
    private int layoutresource;
    private String append;
    private ArrayList<String> imgUrls;
    public GridImageAdapter(@NonNull Context context, int resource, String append, ArrayList<String> imgUrls) {
        super(context, resource,imgUrls);
        mcontext=context;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutresource=resource;
        append=append;
        this.imgUrls=imgUrls;
    }

    private static class ViewHolder{
        ImageView profileImage;
        ProgressBar mprogressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(layoutresource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.mprogressBar=(ProgressBar)convertView.findViewById(R.id.progressBar2);
            viewHolder.profileImage=(ImageView)convertView.findViewById(R.id.profile_image);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        String imgUrl=getItem(position);
        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(append = imgUrl, viewHolder.profileImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(viewHolder.mprogressBar!=null){
                    viewHolder.mprogressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(viewHolder.mprogressBar!=null){
                    viewHolder.mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(viewHolder.mprogressBar!=null){
                    viewHolder.mprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(viewHolder.mprogressBar!=null){
                    viewHolder.mprogressBar.setVisibility(View.GONE);
                }
            }
        });
        return convertView;
    }
}
