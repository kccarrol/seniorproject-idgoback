package calpoly.csc.idgoback;

import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.Request;
import com.facebook.Session;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.model.GraphUser;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.model.*;
import android.widget.TextView;

public class SignUp extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        
	}
	
	public void signUpFacebook(View v) {
		// start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {
			// callback when session changes state
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				if (session.isOpened()) {
	
					// make request to the /me API
					Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
	
						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) {
							if (user != null) {
//								TextView welcome = (TextView) findViewById(R.id.welcome);
//								UserInfo.username = user.getUsername();
//								UserInfo.password = "def4u1t";
//								UserInfo.facebookGraphUser = user;
//								UserInfo.linkedWithFacebook = true;
								
								startMain(user.getUsername(), "def4u1t", true, user);
								
						        JSONObject json_data = null;
						        Bundle mBundle;
//						        try
//						        {
//						            JSONObject rsp = Util.parseJson(session, mBundle, "GET")); // Get a friend information from facebook
//						            JSONArray jArray = rsp.getJSONArray("data");
//
//						            json_data = jArray.getJSONObject(0);
//						            String name = json_data.getString("name");
//						        }
//						        catch (MalformedURLException e)
//						        {
//						            e.printStackTrace();
//						        }
//						        catch (JSONException e)
//						        {
//						            e.printStackTrace();
//						        }
//						        catch (IOException e)
//						        {
//						            e.printStackTrace();
//						        }
//						        catch (FacebookError e)
//						        {
//						            e.printStackTrace();
//						        }
								//UserInfo.emial = user.
							}
						}
					});
				}
			}
		});  
	}
	
	public void signUp(View v) {
		EditText input_username = null;
		EditText input_password = null;
		EditText input_email = null;
		EditText input_phone = null;

		
		input_username = (EditText)findViewById(R.id.signUp_username);
		input_password = (EditText)findViewById(R.id.signUp_password);
		input_email = (EditText)findViewById(R.id.signUp_email);
		input_phone = (EditText)findViewById(R.id.signUp_phone);
		
		if (input_username == null || input_password == null)
			return;
		
		ParseUser newUser = new ParseUser();
		newUser.setUsername(input_username.getText().toString());
		newUser.setPassword(input_password.getText().toString());
		newUser.setEmail(input_email.getText().toString());
		
		final String string_name = input_username.getText().toString();
		final String string_pw = input_password.getText().toString();
		//newUser.put("phoneNumber", input_phone.getText().toString());
		
		System.out.println("password:  " + input_password.getText().toString());
		
		newUser.signUpInBackground(new SignUpCallback() {
			  public void done(ParseException e) {
			    if (e == null) {
			      // Hooray! Let them use the app now.
			    	startMain(string_name, string_pw, false, null);
			    } else {
			      // Sign up didn't succeed. Look at the ParseException
			      // to figure out what went wrong
			        setContentView(R.layout.camera);
			    }
			  }
			});	
	}
	
	public void signUpParse(final String un, final String pw, final String email) {
		ParseUser newUser = new ParseUser();
		newUser.setUsername(un);
		newUser.setPassword(pw);
		newUser.setEmail(email);
		
		newUser.signUpInBackground(new SignUpCallback() {
			  public void done(ParseException e) {
			    if (e == null) {
			      // Hooray! Let them use the app now.
			    	startMain(un, pw, false, null);
			    } else {
			      // Sign up didn't succeed. Look at the ParseException
			      // to figure out what went wrong
			        setContentView(R.layout.camera);
			    }
			  }
			});	
	}
	
	public void startMain(String un, String pw, boolean lf, GraphUser fusr) {
		Intent intent = new Intent(this,MainUI.class);
		UserInfo.username = un;
		UserInfo.password = pw;
		UserInfo.linkedWithFacebook = lf;
		UserInfo.facebookGraphUser = fusr;
		startActivity(intent);
	}
	
}
