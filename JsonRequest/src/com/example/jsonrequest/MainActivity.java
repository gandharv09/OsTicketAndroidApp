package com.example.jsonrequest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
private static final String TAG = "MainActivity";
private static final String URL = "http://192.168.48.129/osTicket-v1.7.1/upload/api/http.php/tickets.json";
public static String EXTRA_MESSAGE = "MESSAGE";
String jsonObjRecv;
String data;
JSONObject jsonObjSend = new JSONObject();
String message="not received";

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
Button fbt = (Button) findViewById(R.id.button1);

try {
	jsonObjSend.put("alert", "true");
	jsonObjSend.put("source", "API");
	jsonObjSend.put( "name", "Gandharv");
	jsonObjSend.put("email", "api@osticket.com");
	jsonObjSend.put("phone", "3185558634X123");
	jsonObjSend.put("subject", "Testing API");
	jsonObjSend.put("ip", "192.168.49.166");
	//jsonObjSend.put("attachments", "[]");
	jsonObjSend.put("message", "MESSAGE HERE");
} catch (JSONException e2) {
	// TODO Auto-generated catch block
	e2.printStackTrace();
}
Thread thread = new Thread()
{
	@Override
	public void run()
	{
		
			URL urlObject = null;
			try {
			urlObject = new URL("http://192.168.48.129/osTicket-v1.7.1/upload/api/http.php/tickets.json");
			} catch (MalformedURLException e1) {
			Log.d("URL","Bad url");
			return;
			}
			HttpURLConnection urlConn = null;
			try {
			urlConn = (HttpURLConnection)urlObject.openConnection();
			} catch (IOException e) {
			Log.d("connection", "main ni karna connect");
			return;
			}
			urlConn.addRequestProperty("X-API-Key"," 61521579BC155EE98BE6F1B196903D5F");
			urlConn.addRequestProperty("Content-Type","application/json");
			urlConn.setDoOutput(true);
			    urlConn.setDoInput(true);
			    try{
			    OutputStreamWriter wr = new OutputStreamWriter(urlConn.getOutputStream());
			    Log.d("wtf",jsonObjSend.toString());
			    wr.write(jsonObjSend.toString());
			    wr.flush();
			    }
			    catch (IOException e){
			    Log.d("outputstream","IOException in outputstream");
			    return;
			    }
			try {
			 InputStream in = new BufferedInputStream(urlConn.getInputStream());
			 BufferedReader buffin = new BufferedReader(new InputStreamReader(in));
			 jsonObjRecv=buffin.readLine();
			 
			}
			catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
			} 
			finally {
			urlConn.disconnect();
			}

		
			
		
	
}

// JSON object to hold the information, which is sent to the server

/*

try {
// Add key/value pairs
jsonObjSend.put("alert", "true");
jsonObjSend.put("source", "API");
jsonObjSend.put( "name", "Gandharv");
jsonObjSend.put("email", "api@osticket.com");
jsonObjSend.put("phone", "3185558634X123");
jsonObjSend.put("subject", "Testing API");
jsonObjSend.put("ip", "123.211.233.122");
jsonObjSend.put("attachments", "[]");
jsonObjSend.put("message", "MESSAGE HERE");
// Add a nested JSONObject (e.g. for header information)
JSONObject header = new JSONObject();
header.put("deviceType","Android"); // Device type
header.put("deviceVersion","2.0"); // Device OS version
header.put("language", "es-es");	// Language of the Android client
jsonObjSend.put("header", header);

// Output the JSON object we're sending to Logcat:
Log.i(TAG, jsonObjSend.toString(2));

} catch (JSONException e) {
e.printStackTrace();
}

Log.i("HELLO","TEST1");
// Send the HttpPostRequest and receive a JSONObject in return
 jsonObjRecv = HttpClient.SendHttpPost(URL, jsonObjSend);
 //Log.i("HELLO",jsonObjRecv);
	fbt.setOnClickListener(new View.OnClickListener() {
		
*/		
};
thread.start();
}
public void onClick(View v) {
			sendmessage(v);
		};



public void sendmessage(View view)
{
	
	Intent intent = new Intent(this, Display.class); 
	//EditText edittext = (EditText) findViewById(R.id.edit_message);
	message = jsonObjRecv;
	intent.putExtra(EXTRA_MESSAGE , message);
	startActivity(intent);
}
}