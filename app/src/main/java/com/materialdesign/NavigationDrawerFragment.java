package com.materialdesign;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends android.support.v4.app.Fragment {

    private static  final String TAG="NavigationDrawer";

    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    /*
        make instance of Drawer Toggle that drawable show a Hamburgar icon is closed
        and arrow when drawer is open when it is  clicked
     */
    ActionBarDrawerToggle mdrawerToggle;
    DrawerLayout mdrawerLayout;
    private boolean mUserLearnedDrawer;  // flag use to checkeck user open drawer
    private boolean mFromSavedInstanceState;
    private View mFragmentContainerView;  // put view for fragment that responsible for drawer
    public NavigationDrawerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer=Boolean.valueOf(readFromPreference(getActivity(),PREF_USER_LEARNED_DRAWER,"false"));
        Log.e(TAG,mUserLearnedDrawer+"");
        if (savedInstanceState !=null){
            mFromSavedInstanceState=true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }


    public void setUp(int fragment_drawer, DrawerLayout drawerLayout, final Toolbar toolbar) {

        Log.e(TAG,"setup");

        mdrawerLayout=drawerLayout;
        mFragmentContainerView=getActivity().findViewById(fragment_drawer);
        /*
        constructor of ActionBarToggle
        @param Activity =>linked to specefied Drawelayout and Toolbal navigation icon

         */
        mdrawerToggle=new ActionBarDrawerToggle(getActivity(),drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUserLearnedDrawer){
                    mUserLearnedDrawer=true;
                    saveToPreference(getActivity(),PREF_USER_LEARNED_DRAWER,mUserLearnedDrawer+"");
                    Log.e(TAG,mUserLearnedDrawer+"          open");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getActivity().invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
                Log.e(TAG, mUserLearnedDrawer + "           close");
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        if(!mUserLearnedDrawer&&!mFromSavedInstanceState){
            mdrawerLayout.openDrawer(mFragmentContainerView);

        }

        mdrawerLayout.setDrawerListener(mdrawerToggle);   // to listen drawer open or close
        // responsible for  toggle icon animation when drawer close icon is Hamburgar icon is closed and arrow when drawer is open when it is  clicked
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
            mdrawerToggle.syncState();
            }
        });
    }


    public static void saveToPreference(Context context,String prefernceName,String preferenceValue){
        SharedPreferences preferences=context.getSharedPreferences(PREF_USER_LEARNED_DRAWER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(prefernceName,preferenceValue);
        editor.apply();
    }

    public static String readFromPreference(Context context,String preferenceName,String defaultValue){
        SharedPreferences preferences=context.getSharedPreferences(PREF_USER_LEARNED_DRAWER,Context.MODE_PRIVATE);
        return preferences.getString(preferenceName,defaultValue);
    }

}
