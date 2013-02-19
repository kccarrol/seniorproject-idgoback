package calpoly.csc.idgoback;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class HomeUI extends Fragment {
	public Button invite;
	public Button changepwd;
	public Button signout;
	public QuickContactBadge badge;
	final int PICTURE_ACTIVITY = 1;
			
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v =  inflater.inflate(R.layout.home, container, false);
		setUpUI(v);
		return v;
	}

	public void setUpUI(View v) {
		invite = (Button) v.findViewById(R.id.invite);
		invite.setBackgroundColor(Color.CYAN);
		invite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createInviteDialog();

			}

		});

		changepwd = (Button) v.findViewById(R.id.changepwd);
		changepwd.setBackgroundColor(Color.CYAN);
		changepwd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createPasswordDialog();

			}

		});


		signout = (Button) v.findViewById(R.id.signout);
		signout.setBackgroundColor(Color.CYAN);
		signout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),Welcome.class);
				startActivity(intent);

			}

		});

		badge = (QuickContactBadge) v.findViewById(R.id.badge);
		badge.setMode(ContactsContract.QuickContact.MODE_MEDIUM);
		badge.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // Normally you would populate this with your custom intent.
				//startActivityForResult(cameraIntent, PICTURE_ACTIVITY);
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType("image/");
				startActivityForResult(intent,PICTURE_ACTIVITY);

			}

		});

	}

	public void createInviteDialog() {
		/* Create layout for Invite dialog box */
		LinearLayout layout = new LinearLayout(getActivity());
		layout.setOrientation(LinearLayout.HORIZONTAL);

		/* Add an email label */
		final TextView tv = new TextView(getActivity());
		tv.setText("Email");
		layout.addView(tv);


		/* Add an edit text field */
		final EditText input = new EditText(getActivity());
		layout.addView(input);

		AlertDialog.Builder ibox = new AlertDialog.Builder(getActivity());
		ibox.setView(layout);

		ibox.setPositiveButton("Send", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String to = input.getText().toString();
				System.out.println(to);
				Intent email = new Intent(android.content.Intent.ACTION_SEND);		
				email.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{to});
				email.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hello");
				email.putExtra(android.content.Intent.EXTRA_TEXT,"This is a test");
				email.setType("plain/text");
				startActivity(Intent.createChooser(email, "Email:"));
				return;
			}
		});

		ibox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;

			}
		});

		ibox.show();
	}

	public void createPasswordDialog() {
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View dialogLayout = inflater.inflate(R.layout.password,(ViewGroup) getActivity().getCurrentFocus());
		final AlertDialog.Builder pwdDialog = new AlertDialog.Builder(getActivity());
		pwdDialog.setView(dialogLayout);
		pwdDialog.setPositiveButton("Comfirm", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				final EditText newpwd = (EditText) dialogLayout.findViewById(R.id.newpwd);
				final EditText comfirm = (EditText) dialogLayout.findViewById(R.id.comfirm);
				String s1 = newpwd.getText().toString();
				String s2 = comfirm.getText().toString();
				AlertDialog.Builder messageBox = new AlertDialog.Builder(getActivity());
				if (s1.equals(s2)) {
					messageBox.setMessage("Your password has been chaned");
					messageBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//pwdDialog.show();
							return;

						}
					});
					return;
				}
				else {

					messageBox.setMessage("Password does not match.\n\tPlease try again");
					messageBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							//pwdDialog.show();
							return;

						}
					});

					//Toast.makeText(getActivity().getApplication(),"Password does not match", Toast.LENGTH_SHORT).show();
				}
				messageBox.show();

			}

		});
		pwdDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;

			}
		});
		pwdDialog.show();



	}
}
