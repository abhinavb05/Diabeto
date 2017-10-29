package com.example.yolo.diabeto;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by abhinav on 29/10/17.
 */

public class Graph_back extends AsyncTask<String, Void, String>{

    Context C;
    String fresult;
    ArrayList<HashMap<String, String>> arrayList;

    public Graph_back(final Context C){this.C = C; }

    @Override
    protected String doInBackground(String... strings) {
        arrayList = new ArrayList<>();
        try {
            URL url = new URL("http://minorprojectf5.esy.es/User_Readings.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            //Exception thrown if no data available for reading in the set time.
            conn.setConnectTimeout(15000);
            //Exception thrown if no connection is made by the web server.
            conn.setRequestMethod("POST");
            //sets the request method for the url request.
            conn.setDoInput(true);
            conn.setDoOutput(true);
            StringBuilder hashString = new StringBuilder();
            hashString.append(URLEncoder.encode("username","UTF-8"));
            hashString.append("=");
            hashString.append(URLEncoder.encode(strings[0],"UTF-8"));
            OutputStream outstream = conn.getOutputStream();
            //Opening a stream with an intention of writing data to server.
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outstream, "UTF-8"));
            //BufferedWriter provides a memory buffer and improves the speed.
            //OutputStreamWriter encodes the character written to it to bytes. Encoded data takes less space.
            writer.write(hashString.toString());
            writer.flush();
            writer.close();
            outstream.close();


            InputStream instream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream,"iso-8859-1"));
            StringBuilder result = new StringBuilder();
            String line = null;

            while((line = reader.readLine())!=null){
                result.append(line+"\n");
            }
            fresult = result.toString();

            JSONArray array = new JSONArray(fresult);
            for(int i=0; i<array.length(); i++)
            {
                JSONObject arr = array.getJSONObject(i);
                String value = arr.getString("value");
                HashMap<String, String> a = new HashMap<>();
                a.put("value", value);
                arrayList.add(a);
            }
            reader.close();
            instream.close();
            conn.disconnect();
        }catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Intent intent = new Intent(C, Graph.class);
        intent.putExtra("mylist", arrayList);
        C.startActivity(intent);
    }
}
