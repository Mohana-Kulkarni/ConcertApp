package com.example.concertticketingapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class QRCardAdapter extends PagerAdapter {

    Context context;
    List<Bitmap> qrCodes;

    public QRCardAdapter(Context context, List<Bitmap> qrCodes) {
        this.context = context;
        this.qrCodes = qrCodes;
    }

    @Override
    public int getCount() {
        return qrCodes.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(qrCodes.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
