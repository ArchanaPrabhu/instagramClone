package com.mycompany.instagramclone.Profile;

/**
 * Created by Vidya Prabhu on 29-07-2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mycompany.instagramclone.R;
import com.mycompany.instagramclone.Utils.BottomNavigationViewHelper;
import com.mycompany.instagramclone.Utils.SectionsStatePagerAdapter;

import java.util.ArrayList;


/**
 * Created by User on 6/4/2017.
 */

public class AccountSettingsActivity extends AppCompatActivity {

    private static final String TAG = "AccountSettingsActivity";
    private Context mContext;
    public static final int Activity_num=4;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountsettings);
        mContext = AccountSettingsActivity.this;
        Log.d(TAG, "onCreate: started.");
        setupBottomNavigationView();
        mViewPager = (ViewPager) findViewById(R.id.container);
                mRelativeLayout = (RelativeLayout) findViewById(R.id.relLayout1);
                        setupSettingsList();
        setupFragments();
                        //setup the backarrow for navigating back to "ProfileActivity"
                                ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
                backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                Log.d(TAG, "onClick: navigating back to 'ProfileActivity'");
                                finish();
                            }
        });
            }

    private void setupFragments(){
                pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
                pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment)); //fragment 0
                pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
            }

            private void setViewPager(int fragmentNumber){
                mRelativeLayout.setVisibility(View.GONE);
                Log.d(TAG, "setViewPager: navigating to fragment #: "+fragmentNumber);
                mViewPager.setAdapter(pagerAdapter);
                mViewPager.setCurrentItem(fragmentNumber);
            }
            private void setupSettingsList(){
                Log.d(TAG, "setupSettingsList: initializing 'Account Settings' list.");
                ListView listView = (ListView) findViewById(R.id.lvAccountSettings);

                        ArrayList<String> options = new ArrayList<>();
                options.add(getString(R.string.edit_profile_fragment)); //fragment 0
                        options.add(getString(R.string.sign_out_fragment)); //fragement 1


                        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Log.d(TAG, "onItemClick: navigating to fragment#: "+position);
                                        setViewPager(position);
                                    }
        });



    }
    /*
   Bottom navigation view setup
    */
    public void setupBottomNavigationView(){
        Log.d(TAG,"Bottom nav view");
        BottomNavigationViewEx bottomNavigationViewEx=(BottomNavigationViewEx)findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(AccountSettingsActivity.this,bottomNavigationViewEx);
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(Activity_num);
        menuItem.setChecked(true);
    }
}