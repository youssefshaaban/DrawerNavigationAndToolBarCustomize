package com.materialdesign;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_appbar);
        toolbar=(Toolbar)findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);
        // show arrow homw button
        getSupportActionBar().setHomeButtonEnabled(true);
        //instantiate object fron Navigation Drawer
        NavigationDrawerFragment drawerFragment=(NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);
        drawerFragment.setUp(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(menuItem);
        MenuItemCompat.setOnActionExpandListener(menuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                Toast.makeText(MainActivity.this,"when collapsed",Toast.LENGTH_LONG).show();
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                Toast.makeText(MainActivity.this,"when expanded",Toast.LENGTH_LONG).show();
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
            case R.id.action_search:

                Toast.makeText(this,"Search",Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
