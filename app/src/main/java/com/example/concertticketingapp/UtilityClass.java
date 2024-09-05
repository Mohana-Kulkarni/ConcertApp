package com.example.concertticketingapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;

import com.example.concertticketingapp.model.Event;

import java.util.ArrayList;
import java.util.List;

public class UtilityClass {
    static ProgressDialog mProgressDialog;
    static PopupWindow popupWindow;

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int numColumns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = (int) Math.ceil((double) items / numColumns);

        for (int i = 0; i < rows; i++) {
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(gridView.getWidth(), View.MeasureSpec.AT_MOST),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            );
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight + (gridView.getVerticalSpacing() * (rows - 1));
        gridView.setLayoutParams(params);
        gridView.requestLayout();
    }

    public static void goToMainActivity(Context context, String selectedCity) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("cityName", selectedCity);
        context.startActivity(intent);
    }

    public static void goToEventsActivity(Context context, List<Event> eventList, String selectedCity) {
        Intent intent = new Intent(context, Activity_Events.class);
        intent.putExtra("cityName", selectedCity);
        System.out.println("In main : " + eventList);
        intent.putParcelableArrayListExtra("eventList", (ArrayList<? extends Parcelable>) eventList);
        context.startActivity(intent);
    }

    public static void goToPurchasedTicketsActivity(Context context, String selectedCity) {
        Intent intent = new Intent(context, ActivityPurchasedTickets.class);
        intent.putExtra("cityName", selectedCity);
        context.startActivity(intent);
    }

    public static void goToIssuedVCsActivity(Context context, String selectedCity) {
        Intent intent = new Intent(context, ActivityIssuedVCs.class);
        intent.putExtra("cityName", selectedCity);
        context.startActivity(intent);
    }

    public static void addProgressBar(Context context) {
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    public static void removeProgressBar() {
        if (mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

    public static void showPopup(View view) {
        removeProgressBar();

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focus = true;


        popupWindow = new PopupWindow(view, width, height, focus);

        View rootView = view.getRootView();

        if(rootView != null) {
            popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
        }

    }
}
