package com.example.moshuying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PredefinedOperation extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predefined_operation);

        findViewById(R.id.openBrowser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用隐式Intent打开浏览器
                Intent intent = new Intent(Intent.ACTION_VIEW);
                EditText text = (EditText) findViewById(R.id.text);
                EditText time = (EditText) findViewById(R.id.time);
                intent.setData(Uri.parse("https://threelib.moshuying.top/assets/AndroidActive.html?time="+time.getText()+"&title=跳转到墨抒颖家&text="+text.getText()));
                startActivity(intent);
            }
        });

        findViewById(R.id.editContacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailAddress = (EditText) findViewById(R.id.email);
                EditText phoneNumber = (EditText) findViewById(R.id.phone);
                EditText name = (EditText) findViewById(R.id.name);
                // Creates a new Intent to insert a contact
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                // Sets the MIME type to match the Contacts Provider
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                /*
                 * Inserts new data into the Intent. This data is passed to the
                 * contacts app's Insert screen
                 */
                // Inserts an email address
                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress.getText())
                        /*
                         * In this example, sets the email type to be a work email.
                         * You can set other email types as necessary.
                         */
                        .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,
                                ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                        // Inserts a phone number
                        .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText())
                        /*
                         * In this example, sets the phone type to be a work phone.
                         * You can set other phone types as necessary.
                         */
                        .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                        .putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME,name.getText());

//                intent.setData(Uri.parse("content://contacts/people/1"));
                startActivity(intent);
            }
        });

        findViewById(R.id.callContacts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 使用隐式Intent打开拨打电话节面
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:110"));
                startActivity(intent);
            }
        });
    }
}
