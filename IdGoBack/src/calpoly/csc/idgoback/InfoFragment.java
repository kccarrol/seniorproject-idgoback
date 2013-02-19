package calpoly.csc.idgoback;

import calpoly.csc.idgoback.ParseSelectedItem.DetailItemInfo;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {
	//String id="";
	static DetailItemInfo item;
	static final String STATE_ITEM = "selectedItem";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("onCreateView");
		View v = inflater.inflate(R.layout.info_fragment, container,false);
		v.setVisibility(View.INVISIBLE);
		return v;
	}
	

	public void onResume() {
		super.onResume();
		item =  ((InfoDetail) getActivity()).item;
		if (!item.name.equals("")) {
			TextView txtName = (TextView) getActivity().findViewById(R.id.name);
			txtName.setText(item.name);
			
			TextView txtHours = (TextView) getActivity().findViewById(R.id.hours);
			if (item.hours.equals("")) {
				TextView hourLabel = (TextView) getActivity().findViewById(R.id.txtHours);
				txtHours.setVisibility(View.GONE);
				hourLabel.setText("Information is not available");
			}
			else
				txtHours.setText("Hours  " + item.hours);
			
			
			TextView txtAddress = (TextView) getActivity().findViewById(R.id.address);
			String addr = item.street + "\n" + item.city + "," + item.state + " " + item.zipCode;
			txtAddress.setText(addr);
			
			TextView txtContact = (TextView) getActivity().findViewById(R.id.contact);
			String contact = item.phoneNumber + "\n" + item.url.toLowerCase();
			txtContact.setText(contact);
			
			View v = (View) getActivity().findViewById(R.id.info);
			v.setVisibility(View.VISIBLE);
		}
	}
}
