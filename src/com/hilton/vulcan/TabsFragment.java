package com.hilton.vulcan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabsFragment extends Fragment {
    protected static final String TAG = TabsFragment.class.getName();
    private Tab[] mTabs = new Tab[] {
	    new Tab("Tab 1"),
	    new Tab("Tab 2"),
	    new Tab("Tab 3"),
	    new Tab("Tab 4"),
	    new Tab("Tab 5")
    };
    
    private TabHost mTabHost;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	View root = inflater.inflate(R.layout.tabs, container, false);
	mTabHost = (TabHost) root.findViewById(android.R.id.tabhost);
	setupTabs(mTabHost);
	return root;
    }
    
    private void setupTabs(final TabHost tabHost) {
	tabHost.setup();
	for (final Tab tab : mTabs) {
	    TabSpec spec = tabHost.newTabSpec(tab.mTag);
	    spec.setIndicator(tab.mTag);
	    spec.setContent(new TabContentFactory() {
		@Override
		public View createTabContent(String tag) {
		    TextView tv =  new TextView(getActivity());
		    tv.setText(tab.mTag);
		    return tv;
		}
		
	    });
	    tabHost.addTab(spec);
	}
	tabHost.setOnTabChangedListener(new OnTabChangeListener() {
	    @Override
	    public void onTabChanged(String tabId) {
		Log.e(TAG, "which is showing ? tab id " + tabId);
	    }
	});
    }
    
    private class Tab {
	public String mTag;
	
	public Tab(final String tag) {
	    mTag = tag;
	}
	
	@Override
	public String toString() {
	    return "[tag " + mTag + "]";
	}
    }
}