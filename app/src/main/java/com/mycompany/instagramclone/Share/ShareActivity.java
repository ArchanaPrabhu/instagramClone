package com.mycompany.instagramclone.Share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mycompany.instagramclone.R;
import com.mycompany.instagramclone.Utils.BottomNavigationViewHelper;


/**
 * Created by Vidya Prabhu on 26-07-2017.
 */

public class ShareActivity extends AppCompatActivity {
    private static final String TAG="ShareActivity";
    public static final int Activity_num=2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG,"onCreate started");
       setupBottomNavigationView();
    }

    /*
    Bottom navigation view setup
     */
    public void setupBottomNavigationView(){
        Log.d(TAG,"Bottom nav view");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ShareActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }

}
