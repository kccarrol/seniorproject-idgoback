package calpoly.csc.idgoback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.*;
import com.facebook.model.*;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.PushService;

public class Welcome extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        Parse.initialize(this, "HHzy8PlSkP0I2VY6txBath2BZFmim6rhtSgbB1BG", "qkEw6JRVGUZMSs1oB7i1BLVjWt1b2mhj7iMWA0Ld");

//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("age", 1);
//        testObject.put("playerName", "Kane Carroll");
//        testObject.saveInBackground();
//        testObject.saveEventually();   
	}
	
	public void signUp(View v) {
		Intent intent = new Intent(this,SignUp.class);
		startActivity(intent);
	}
	
 
	public void logIn(View v) {
		
		try {
			Intent intent = new Intent(this, LogIn.class);
			startActivity(intent);
		}
		catch (Exception ex) {
			Log.e("logIn", ex.toString());
		}
        //setContentView(R.layout.login);
//

	}
	  
	
//	  @Override
//	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
//	      super.onActivityResult(requestCode, resultCode, data);
//	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
//	  }

//	  
//	  /*
//	 * Face Book Sign in
//	 * 
//	 * */
//	public void signIn(View v) {
//		Intent intent = new Intent(this,MainUI.class);
//		
//		// start Facebook Login
//		Session.openActiveSession(this, true, new Session.StatusCallback() {
//			// callback when session changes state
//			@Override
//			public void call(Session session, SessionState state, Exception exception) {
//				if (session.isOpened()) {
//	
//					// make request to the /me API
//					Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
//	
//						// callback after Graph API response with user object
//						@Override
//						public void onCompleted(GraphUser user, Response response) {
//							if (user != null) {
////								TextView welcome = (TextView) findViewById(R.id.welcome);
//								TextView txt = (TextView) findViewById(R.id.main_text_view);
//
//								txt.setText("Hello " + user.getName() + "!");
//	                 
//							}
//						}
//					});
//				}
//			}
//		});    
//		
//		startActivity(intent);
//	}
}























//// start Facebook Login
//Session.openActiveSession(this, true, new Session.StatusCallback() {
//
//// callback when session changes state
//@Override
//public void call(Session session, SessionState state, Exception exception) {
//  if (session.isOpened()) {
//
//    // make request to the /me API
//    Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
//
//      // callback after Graph API response with user object
//      @Override
//      public void onCompleted(GraphUser user, Response response) {
//        if (user != null) {
//          TextView welcome = (TextView) findViewById(R.id.welcome);
//          welcome.setText("Hello " + user.getName() + "!");
//         
//        }
//      }
//    });
//  }
//}
//});

