package calpoly.csc.idgoback;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.view.Menu;

public class MainUI extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("username: " + UserInfo.username);
		System.out.println("password: " + UserInfo.password);
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayShowTitleEnabled(true);

		/** Creating EXPLORE Tab */
		Tab tab = actionBar
				.newTab()
				.setText("Explore")
				.setTabListener(
						new CustomTabListener<ExploreUI>(this, "explore",
								ExploreUI.class))
				.setIcon(R.drawable.ic_action_search);
		actionBar.addTab(tab);

		/** Creating CAMERA Tab */
		tab = actionBar
				.newTab()
				.setText("Camera")
				.setTabListener(
						new CustomTabListener<CameraUI>(this, "camera",
								CameraUI.class)).setIcon(R.drawable.camera);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_ui, menu);
		return true;
	}
}
