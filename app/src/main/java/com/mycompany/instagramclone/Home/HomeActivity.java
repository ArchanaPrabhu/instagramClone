package com.mycompany.instagramclone.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mycompany.instagramclone.Login.LoginActivity;
import com.mycompany.instagramclone.R;
import com.mycompany.instagramclone.Utils.BottomNavigationViewHelper;
import com.mycompany.instagramclone.Utils.SectionsPagerAdapter;
import com.mycompany.instagramclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;


public class HomeActivity extends AppCompatActivity {
public static final String TAG="MainActivity";
    public static final int Activity_num=0;
    private Context mContext = HomeActivity.this;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();
        setupFirebaseAuth();
    }
    private void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(HomeActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    /*
    Responsible for adding the three tabs Camera,Home,Messages
     */

    public void setupViewPager()
    {
        SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());
        adapter.addFragment(new HomeFragment());
        adapter.addFragment(new MessagesFragment());
        ViewPager viewPager=(ViewPager)findViewById(R.id.container);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_appicon);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }
    /*
    Bottom navigation view setup
     */
    public void setupBottomNavigationView(){
        Log.d(TAG,"Bottom nav view");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
      /*
    ------------------------------------ Firebase ---------------------------------------------
     */

            /**
      * checks to see if the @param 'user' is logged in
      * @param user
      */
             private void checkCurrentUser(FirebaseUser user){
                 Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

                         if(user == null){
                         Intent intent = new Intent(mContext, LoginActivity.class);
                         startActivity(intent);
                     }
             }
    /**
      * Setup the firebase auth object
      */
            private void setupFirebaseAuth(){
                Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

                        mAuth = FirebaseAuth.getInstance();

                        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                        //check if the user is logged in
                                                checkCurrentUser(user);

                                        if (user != null) {
                                        // User is signed in
                                                Log.d(TAG, "onAuthStateChanged:signed_in:" +user.getUid());
                                    } else {
                                        // User is signed out
                                                Log.d(TAG, "onAuthStateChanged:signed_out");
                                    }
                                // ...
                                    }
        };
            }

            @Override
    public void onStart() {
                super.onStart();
                mAuth.addAuthStateListener(mAuthListener);
                checkCurrentUser(mAuth.getCurrentUser());
            }

            @Override
    public void onStop() {
                super.onStop();
                if (mAuthListener != null) {
                        mAuth.removeAuthStateListener(mAuthListener);
                    }
            }
}
