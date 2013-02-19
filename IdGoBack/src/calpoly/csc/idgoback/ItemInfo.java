package calpoly.csc.idgoback;

import android.widget.RatingBar;

public class ItemInfo {
	public String name = "";
	public String distance = "";
	public RatingBar rate = null;
	
	public ItemInfo(String name, String dst) {
		this.name = name;
		this.distance = dst;
	}

}
