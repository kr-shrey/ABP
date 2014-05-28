package com.example.abp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Intent i;
	ImageView iv;
	private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 mPlanetTitles = getResources().getStringArray(R.array.objective_list);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);

	        // Set the adapter for the list view
	        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
	                R.layout.drawer_list_item, mPlanetTitles));
	        // Set the list's click listener
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	        
	        mTitle = mDrawerTitle = getTitle();
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description */
	                R.string.drawer_close  /* "close drawer" description */
	                ) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
	                getActionBar().setTitle(mTitle);
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getActionBar().setTitle(mDrawerTitle);
	                //getFragmentManager().popBackStackImmediate();
	            }
	        };

	        // Set the drawer toggle as the DrawerListener
	        mDrawerLayout.setDrawerListener(mDrawerToggle);

	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	    
	   

		iv = (ImageView)findViewById(R.id.imageButton1);
		
		 AnimationDrawable animation = new AnimationDrawable();
		    animation.addFrame(getResources().getDrawable(R.drawable.aa), 3000);
		    animation.addFrame(getResources().getDrawable(R.drawable.bb), 3000);
		    animation.addFrame(getResources().getDrawable(R.drawable.cc), 3000);
		    animation.setOneShot(false);
		    
		    iv.setBackgroundDrawable(animation);

		    // start the animation!
		    animation.start();
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(MainActivity.this));
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	switch(position){
	        	case 0:
	        		Toast.makeText(MainActivity.this,"Profile", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), ProfileActivity.class);
	        		startActivity(i);
	        		break;
	        	case 1:
	        		Toast.makeText(MainActivity.this,"Stories of Women", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), WomenStoriesActivity.class);
	        		startActivity(i);
	        		break;
	        	case 2:
	        		Toast.makeText(MainActivity.this,"Request an Appointment", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), AppointmentActivity.class);
	        		startActivity(i);
	        		break;
	        	case 3:
	        		Toast.makeText(MainActivity.this,"Schedule", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), ScheduleActivity.class);
	        		startActivity(i);
	        		break;
	        	case 4:
	        		Toast.makeText(MainActivity.this,"Write to Ma'm", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), WriteToMamActivity.class);
	        		startActivity(i);
	        		break;
	        	case 5:
	        		Toast.makeText(MainActivity.this,"Campiagn", Toast.LENGTH_SHORT).show();
	        		i = new Intent(getApplicationContext(), CampaignActivity.class);
	        		startActivity(i);
	        		break;
	        	default:
	        		Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        	}
	        	
	        }
	    });
		
		
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    // Sync the toggle state after onRestoreInstanceState has occurred.
	    mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}
	
	private void selectItem(int position) {
		// TODO Auto-generated method stub
		 Fragment fragment = null;
		 Bundle args;
		 switch(position){
		 case 0:
			 fragment = new MenuFragment();
			 args = new Bundle();
			 args.putInt("position", position);
			 fragment.setArguments(args);
			 break;
			 
		 case 1:
			 fragment = new MenuFragment();
			 args = new Bundle();
			 args.putInt("position", position);
			 fragment.setArguments(args);
			 break;
		 case 2:
			 fragment = new MenuFragment();
			 args = new Bundle();
			 args.putInt(MenuFragment.ARG_MENU_NUMBER, position);
			 fragment.setArguments(args);
			 break;
		 case 3:
			 fragment = new MenuFragment();
			 args = new Bundle();
			 args.putInt(MenuFragment.ARG_MENU_NUMBER, position);
			 fragment.setArguments(args);
			 break;
		 case 4:
			 fragment = new MenuFragment();
			 args = new Bundle();
			 args.putInt(MenuFragment.ARG_MENU_NUMBER, position);
			 fragment.setArguments(args);
			 break;
			 
		 }
		    // Insert the fragment by replacing any existing fragment
		 FragmentTransaction transaction = getFragmentManager().beginTransaction();

         // Replace whatever is in the fragment_container view with this fragment,
         // and add the transaction to the back stack so the user can navigate back
         transaction.replace(R.id.content_frame, fragment);
         transaction.addToBackStack(null);

         // Commit the transaction
         transaction.commit();
		    // Highlight the selected item, update the title, and close the drawer
		    mDrawerList.setItemChecked(position, true);
		    setTitle(mPlanetTitles[position]);
		    mDrawerLayout.closeDrawer(mDrawerList);
		
	}
	
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getActionBar().setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	    }
		
		return super.onOptionsItemSelected(item);
	}
	
	public static class MenuFragment extends Fragment {
		
		Intent i;
		public static final String ARG_MENU_NUMBER = "position";
		int mCurrentPosition = -1;
		public MenuFragment(){
			
		}
		 @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
		        Bundle savedInstanceState) {
			 	Bundle args = getArguments();
			 	if(args!=null)
			 		mCurrentPosition = args.getInt(ARG_MENU_NUMBER);
			 	
		        // If activity recreated (such as from screen rotate), restore
		        // the previous article selection set by onSaveInstanceState().
		        // This is primarily necessary when in the two-pane layout.
		        if (savedInstanceState != null) {
		            mCurrentPosition = savedInstanceState.getInt(ARG_MENU_NUMBER);
		        }
		        
		        switch(mCurrentPosition){
		        
		        case 0:
		        	return inflater.inflate(R.layout.activity_main, container, false);
	        		
		        case 1:
		        	i = new Intent(getActivity().getApplicationContext(), NewsActivity.class);
	        		startActivity(i);
	        		break;
		        case 2:
		        	i = new Intent(getActivity().getApplicationContext(), TrendingActivity.class);
	        		startActivity(i);
	        		break;
		        case 3:
		        	i = new Intent(getActivity().getApplicationContext(), TwitterActivity.class);
	        		startActivity(i);
	        		break;
		        case 4:
		        	i = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
	        		startActivity(i);
	        		break;
		        }
		        // Inflate the layout for this fragment
		        return null;
		    }
		    
//		    @Override
//		    public void onStart() {
//		        super.onStart();
//
//		        // During startup, check if there are arguments passed to the fragment.
//		        // onStart is a good place to do this because the layout has already been
//		        // applied to the fragment at this point so we can safely call the method
//		        // below that sets the article text.
//		        Bundle args = getArguments();
//		        if (args != null) {
//		            // Set article based on argument passed in
//		            updatePageView(args.getInt(ARG_MENU_NUMBER));
//		        } else if (mCurrentPosition != -1) {
//		            // Set article based on saved instance state defined during onCreateView
//		            updatePageView(mCurrentPosition);
//		        }       
//		       
//		    }
		  
//		    public void updatePageView(int position) {
//		    	
//		    	GridView gridview = (GridView) findViewById(R.id.gridview);
//		        gridview.setAdapter(new ImageAdapter(getBaseContext()));
//		        
//		        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//				
//		            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//		                Toast.makeText(getBaseContext(), "" + position, Toast.LENGTH_SHORT).show();
//		            }
//		        });
//		    }
		    
		    @Override
		    public void onSaveInstanceState(Bundle outState) {
		        super.onSaveInstanceState(outState);

		        // Save the current article selection in case we need to recreate the fragment
		        outState.putInt(ARG_MENU_NUMBER, mCurrentPosition);
		    }
//		@Override
//		public void onCreate(Bundle savedInstanceState) {
//			// TODO Auto-generated method stub
//			super.onCreate(savedInstanceState);
//			if (savedInstanceState != null) {
//				System.out.println("It is not null");
//	            mCurrentPosition = savedInstanceState.getInt("position"); 
//	        }
//			System.out.println("current pos==>"+mCurrentPosition);
//		}
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//			
//			if (savedInstanceState != null) {
//				System.out.println("It is not null");
//	            mCurrentPosition = savedInstanceState.getInt("position");
//	            
//	        }
//			System.out.println("current pos==>"+mCurrentPosition);
//			//ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_news, null);
//			switch(mCurrentPosition){
//			 
//			 case 0:
//		        return (ViewGroup) inflater.inflate(R.layout.activity_main, null);
//			 case 1:
//				 return (ViewGroup) inflater.inflate(R.layout.activity_news, null);
//			 }
//			
//			return null;
			
////            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
//            int i = getArguments().getInt(ARG_MENU_NUMBER);
//            String planet = getResources().getStringArray(R.array.objective_list)[i];
////
////            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
////                            "drawable", getActivity().getPackageName());
////            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
////            getActivity().setTitle(planet);
//            return rootView;
			
			 
			 
//			 return null;
//        }
	}

}