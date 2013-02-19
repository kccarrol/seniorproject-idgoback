package calpoly.csc.idgoback;

import java.util.ArrayList;

import calpoly.csc.idgoback.ParseSelectedItem.CustomerReview;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ReviewFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.review_fragment, container,false);
		
		String rating = ((InfoDetail) getActivity()).rating;
		RatingBar rateBar = (RatingBar) v.findViewById(R.id.ratingBar);
		rateBar.setRating(Float.parseFloat(rating));
		
		TextView tv = (TextView) v.findViewById(R.id.review_count);
		ArrayList<CustomerReview> customer = ((InfoDetail) getActivity()).reviews;
		int reviewCount = customer.size();
		if (reviewCount > 1)
			tv.setText(reviewCount + " reviews");
		else
			tv.setText(reviewCount + " review");
		
		Button addReview = (Button) v.findViewById(R.id.add_review);
		addReview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
			
		});
		for (CustomerReview c: customer) {
			System.out.println("Name " + c.author);
			System.out.println("Date " + c.date_review);
			System.out.println("Review " + c.review);
		}
		
		ListView lv =  (ListView) v.findViewById(R.id.reviewList);
		lv.setAdapter(new CustomerReviewAdapter(getActivity().getApplicationContext(),customer));
		
		return v;
	}
}
