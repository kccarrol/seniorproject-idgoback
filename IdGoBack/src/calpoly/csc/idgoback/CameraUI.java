package calpoly.csc.idgoback;


import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CameraUI extends Fragment {


	final int PICTURE_ACTIVITY = 1; // This is only really needed if you are catching the results of more than one activity. It'll make sense later.
	private View view = null;
	
    /* Override the onCreate method */
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        
//        //ParseObject testObject = new ParseObject("TestObject");
//        ParseQuery query = new ParseQuery("TestObject");
//        query.whereEqualTo("playerName", "Kane Carroll");
//        query.getFirstInBackground(new GetCallback() {
//			@Override
//			public void done(ParseObject object, ParseException e) {
//				if (object == null){
//					
//				}
//				else {
//					int age = object.getInt("age");
//					System.out.println("Age: " + age);
//				}
//				
//			}
//        });
               
        //setContentView(R.layout.camera);
		view = inflater.inflate(R.layout.camera, container, false);
		dispatchTakePictureIntent();
				//TextView txt = (TextView) findViewById(R.id.main_text_view);
//
//        final Button cameraButton = (Button)view.findViewById(R.id.camera_button); // Get a handle to the button so we can add a handler for the click event
//        cameraButton.setOnClickListener(new OnClickListener() {
//        	@Override
//        	public void onClick(View v){
//        		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Normally you would populate this with your custom intent.
//        		startActivityForResult(cameraIntent, PICTURE_ACTIVITY); // This will cause the onActivityResult event to fire once it's done
//        	}
//        });
        return view;
    	//return inflater.inflate(R.layout.home, container, false);
    }
    
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, PICTURE_ACTIVITY);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	System.out.println("HERE1?");
    	
    	//super.onActivityResult(requestCode, resultCode, data);
    	System.out.println("HERE2?");
    	System.out.println("requestCode: " + requestCode);
    	System.out.println("resultCode: " + resultCode);

        if( resultCode == 1888 ) {
        	System.out.println("HERE3?");

            Bitmap photo = (Bitmap) data.getExtras().get("data");
          
            final Button cameraButton = (Button)view.findViewById(R.id.camera_button); // Get a handle to the button so we can add a handler for the click event
            cameraButton.setOnClickListener(new OnClickListener() {
          	@Override
          	public void onClick(View v){
        		dispatchTakePictureIntent();
          	}
          });
            
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//    	super.onActivityResult(requestCode, resultCode, intent);
//
//    	AlertDialog msgDialog;
//    	if (resultCode == RESULT_CANCELED) { // The user didn't like the photo. ;_;
//    		msgDialog = createAlertDialog("Q_Q", "Kitty wouldn't sit still, eh? It's ok - you can try again!", "OK! I'LL TRY AGAIN!");
//
//    	} else {
//	/*
//	This is where you would trap the requestCode (in this case PICTURE_ACTIVITY). Seeing as how this is the ONLY
//	Activity that we are calling from THIS activity, it's kind of a moot point. If you had more than one activity that
//	you were calling for results, you would need to throw a switch statement in here or a bunch of if-then-else
//	constructs. Whatever floats your boat.
//	*/
//    		msgDialog = createAlertDialog("ZOMG!", "YOU TOOK A PICTURE! WITH YOUR PHONE! HOLY CRAP!", "I KNOW RITE??!?");
//
//	/*
//	Yes, I know that throwing a simple alert dialog doesn't really do anything impressive.
//	If you wanna do something with the picture (save it, display it, shoot it to a web server, etc) then you can get the
//	image data like this:
//	Bitmap = getIntent().getExtras().get("data");
//	Then do whatever you want with it.
//	*/
//
//    	}
//
//    	msgDialog.show();
//    }

    
//    private AlertDialog createAlertDialog(String title, String msg, String buttonText){
//    	AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//    	AlertDialog msgDialog = dialogBuilder.create();
//    	msgDialog.setTitle(title);
//    	msgDialog.setMessage(msg);
//    	msgDialog.setButton(buttonText, new DialogInterface.OnClickListener(){
//    		@Override
//    		public void onClick(DialogInterface dialog, int idx){
//    			return; // Nothing to see here...
//    		}
//    	});
//
//    	return msgDialog;
//    }
    
}


