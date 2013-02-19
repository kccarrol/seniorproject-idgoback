package calpoly.csc.idgoback;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.view.Menu;

public class DetailInfo extends Activity{
	private static final int MENU_INFO = 0;
	private static final int MENU_REVIEWS = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayShowTitleEnabled(true);
		
		Tab tab = actionBar
				.newTab()
				.setText("Explore")
				.setTabListener(
						new CustomTabListener<ExploreUI>(this, "explore",
								ExploreUI.class))
				.setIcon(R.drawable.ic_action_search);
		actionBar.addTab(tab);
		
		/** Creating HOME Tab */
		tab = actionBar
				.newTab()
				.setText("Home")
				.setTabListener(
						new CustomTabListener<HomeUI>(this, "home",
								HomeUI.class)).setIcon(R.drawable.home);
		actionBar.addTab(tab);

        
    }
}
