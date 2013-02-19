package calpoly.csc.idgoback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.util.Log;

public class ParseSelectedItem implements ContentHandler {
	private DetailItemInfo item = new DetailItemInfo();
	private ArrayList<CustomerReview> reviews = new ArrayList<CustomerReview>();
	private CustomerReview customer;
	String currText="";
	boolean isReview = false;

	public void parseSelectedItem(String url) {
		DefaultHttpClient client = new DefaultHttpClient();

		HttpGet httpRequest = new HttpGet(url);

		try {

			HttpResponse httpResponse = client.execute(httpRequest);
			final int statusCode = httpResponse.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(),
						"Error => " + statusCode + " => for URL " + url);
				return;
			}

			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				//create a parser
				SAXParserFactory parseFactory = SAXParserFactory.newInstance();
				SAXParser xmlParser = parseFactory.newSAXParser();   

				//get an XML reader
				XMLReader xmlIn = xmlParser.getXMLReader();

				//instruct the app to use this object as the handler
				xmlIn.setContentHandler(this);

				InputStreamReader xmlStream = new InputStreamReader(httpEntity.getContent());

				//build a buffered reader
				BufferedReader xmlBuff = new BufferedReader(xmlStream);

				//parse the data
				xmlIn.parse(new InputSource(xmlBuff));
			}
			catch(SAXException se) { 
				Log.e("SAX Error", se.getMessage()); 
			}
			catch(IOException ie) { 
				Log.e("Input Error",ie.getMessage());
			}
			catch(Exception oe) { 
				Log.e("Unspecified Error",oe.getMessage()); 
			}

		}
		catch (IOException e) {
			httpRequest.abort();
			Log.w(getClass().getSimpleName(), "Error for URL =>" + url, e);
		}


		return;

	}



	@Override
	public void startDocument() throws SAXException {
		Log.i("ParseSelectedItem","Start of XML document");

	}

	@Override
	public void endDocument() throws SAXException {
		Log.i("ParseSelectedItem","End of XML document");

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (qName.equals("review")) {
			customer =  new CustomerReview();
			isReview = true;
		}

	}



	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("name")) {
			Log.i("Name",currText);
			item.name = currText;
		}
		if(qName.equals("street")) {
			Log.i("Street", currText);
			item.street = currText;
		}

		if(qName.equals("city")) {
			Log.i("City", currText);
			item.city = currText;
		}

		if(qName.equals("state")) {
			Log.i("State", currText);
			item.state = currText;
		}

		if(qName.equals("postal_code")) {
			Log.i("ZipCode", currText);
			item.zipCode = currText;
		}

		if(qName.equals("latitude")) {
			Log.i("Lat", currText);
			item.lat = currText;
		}

		if(qName.equals("longitude")) {
			Log.i("Lon", currText);
			item.lon = currText;
		}

		if(qName.equals("display_phone")) {
			Log.i("Phone", currText);
			item.phoneNumber = currText;
		}

		if(qName.equals("display_url")) {
			Log.i("URL", currText);
			item.url = currText;
		}
		
		if (qName.equals("business_hours"))
		{
			item.hours = currText;
		}
		if (isReview) {
			if (qName.equals("review_author")) {
				Log.i("Author", currText);
				customer.author = currText;
			}
			
			if(qName.equals("review_text")) {
				customer.review = currText;
				Log.i("Text", currText);
			}
			
			if (qName.equals("review_date")) {
				Log.i("Date", currText);
				customer.date_review = currText;
			}
			
			if(qName.equals("review_rating")) {
				customer.customer_rate = Float.parseFloat(currText)/2;
				reviews.add(customer);
				isReview = false;
			}
		}
		currText = "";

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		for (int i = 0; i<start+length; i++) {
			currText += ch[i];
		}		
		
		Log.i("CurrText", currText);
	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {

	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {

	}

	@Override
	public void setDocumentLocator(Locator locator) {

	}

	@Override
	public void skippedEntity(String name) throws SAXException {

	}



	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {

	}
	
	
	public DetailItemInfo getItem() {
		return item;
	}
	
	
	public ArrayList<CustomerReview> getCustomerReviews() {
		return reviews;
	}
	
	public class CustomerReview {
		public String author ="";
		public String review ="";
		public String date_review= "";
		public Float customer_rate = 0.f;
	}

	public class DetailItemInfo {
		public String name = "";
		public String rating = "";
		public String street ="";
		public String city ="";
		public String state ="";
		public String zipCode ="";
		public String lat="";
		public String lon="";
		public String phoneNumber ="";
		public String url;
		public String hours = "";
	}
}
