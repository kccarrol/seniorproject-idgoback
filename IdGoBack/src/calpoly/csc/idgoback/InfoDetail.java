package calpoly.csc.idgoback;

import java.util.ArrayList;

import calpoly.csc.idgoback.ParseSelectedItem.*;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class InfoDetail extends Activity{
	String id = "";
	String rating="";
	public ParseSelectedItem i =  new ParseSelectedItem();
	public DetailItemInfo item = i.new DetailItemInfo();
	public ArrayList<CustomerReview> reviews = new ArrayList<CustomerReview>();
	static String name = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_info);
		
		/* Retrieve id and rating passed from ItemList class */
		Intent intent = getIntent();
		id = intent.getStringExtra(ItemList.ID);
		//System.out.println("ID " + id);
		rating = intent.getStringExtra(ItemList.Rating);

		new MyAsyncTask().execute();
		//name = item.name;
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab = actionBar
				.newTab()
				.setText("Info")
				.setTabListener(
						new CustomTabListener<InfoFragment>(this, "info",
								InfoFragment.class));
		actionBar.addTab(tab);

		/** Creating HOME Tab */
		tab = actionBar
				.newTab()
				.setText("Reviews")
				.setTabListener(
						new CustomTabListener<ReviewFragment>(this, "review",
								ReviewFragment.class));
		actionBar.addTab(tab);
	}
	

	private class MyAsyncTask extends AsyncTask<Void,Void,Void>{
		ProgressDialog progressDialog;
		ParseSelectedItem handler;

		@Override
		protected void onPreExecute()
		{               
			super.onPreExecute();
			progressDialog = new ProgressDialog(InfoDetail.this);
			progressDialog.setMessage("Loading Item Detail.Please Wait!!!");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progressDialog.show();
		
		}; 

		@Override
		protected Void doInBackground(Void... arg0) {
			handler = new ParseSelectedItem();
			String myURL = "http://api.citygridmedia.com/content/places/v2/detail?id=" + id
					+ "&id_type=cs&placement=search_page&review_count=20&client_ip=123.4.56.78&publisher=test";

			handler.parseSelectedItem(myURL);
			
			item = handler.getItem();
			item.rating = rating;
			reviews = handler.getCustomerReviews();
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			progressDialog.dismiss();
			TextView txtName = (TextView) findViewById(R.id.name);
			txtName.setText(item.name);
			
			RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
			ratingBar.setRating(Float.parseFloat(rating));
			
			TextView txtHours = (TextView) findViewById(R.id.hours);
			if (item.hours.equals("")) {
				TextView hourLabel = (TextView) findViewById(R.id.txtHours);
				txtHours.setVisibility(View.GONE);
				hourLabel.setText("Information is not available");
			}
			else
				txtHours.setText(item.hours);
			
			
			TextView txtAddress = (TextView) findViewById(R.id.address);
			String addr = item.street + "\n" + item.city + "," + item.state + " " + item.zipCode;
			txtAddress.setText(addr);
			
			TextView txtContact = (TextView) findViewById(R.id.contact);
			String contact = item.phoneNumber + "\n" + item.url.toLowerCase();
			txtContact.setText(contact);
			
			View view = (View) findViewById(R.id.info);
			view.setVisibility(View.VISIBLE);
		}
	}
}
