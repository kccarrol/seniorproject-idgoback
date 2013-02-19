package calpoly.csc.idgoback;

import java.util.List;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogIn extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        

	}
	
	public void signIn(View v) {
		EditText input_username = null;
		EditText input_password = null;
		
		input_username = (EditText)findViewById(R.id.login_username);
		input_password = (EditText)findViewById(R.id.login_password);
		
		final String string_name = input_username.getText().toString();
		final String string_pw = input_password.getText().toString();
		
//		
//		System.out.println("username:  " + input_username.getText().toString());
//		System.out.println("password:  " + input_password.getText().toString());
		
//		ParseQuery query = ParseUser.getQuery();
//		query.whereEqualTo("username", input_username.getText().toString());
//		query.findInBackground(new FindCallback() {
//			  public void done(List<ParseObject> objects, ParseException e) {
//			    if (e == null) {
//			        // The query was successful.
//			    	String pw = objects.get(0).getParseUser(key)
//			    	String pw = objects.get(0).getString("password");
//			    	System.out.println("PASSWORD:  " + pw);
//			        //setContentView(R.layout.home);
//			    } else {
//			        // Something went wrong.
//			    }
//			  }
//			});
//		
		ParseUser.logInInBackground(input_username.getText().toString(),
				input_password.getText().toString(), new LogInCallback() {
			public void done (ParseUser user, ParseException e) {
				if (user != null) {
					//User is logged in
					startMain(string_name, string_pw);
				}
				else {
					//LogIn failed
			        setContentView(R.layout.search);
				}
			}
			
		});
		
	}
	
	public void startMain(String un, String pw) {
		Intent intent = new Intent(this,MainUI.class);
		UserInfo.username = un;
		UserInfo.password = pw;
		startActivity(intent);
	}
}
