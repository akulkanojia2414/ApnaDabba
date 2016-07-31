package example.akul.apnadabba;

/**
 * Created by akul on 28-07-2016.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ProgrammingKnowledge on 1/5/2016.
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    BackgroundWorker (Context ctx) {
        context = ctx;
    }
    String login_url = "http://apnadabba.co/today_fooddata.php";
    String result = "";
    private ProgressDialog loading;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // loading = ProgressDialog.show(context, "Please Wait...",null,true,true);
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];

        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(login_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();


            int status = con.getResponseCode();
            Log.d("Akul", String.valueOf(status));
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String json;
            while((json = bufferedReader.readLine())!= null){
                sb.append(json+"\n");
                Log.d("Akul","asd");
            }
            Log.d("Akul",sb.toString().trim());
            return sb.toString().trim();

        }catch(Exception e){
            return null;
        }

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loading.dismiss();
    }
}


   /* protected String doInBackground(String... params) {
        String type = params[0];

        DishesMenu data = new DishesMenu();
        final MyDbHelper helper = MyDbHelper.getInstance(context);

        if (type.equals("fetch")) {

            JSONObject json = jParser.makeHttpRequest(login_url, "GET", params);
            try {

                JSONObject jsonRootObject = new JSONObject(result);
                JSONArray jsonArray = jsonRootObject.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    data.product_id = Integer.parseInt(jsonObject.optString("food_id").toString());
                    data.likes = Integer.parseInt((jsonObject.optString("food_likes").toString()));
                    data.Description = jsonObject.optString("food_description").toString();
                    data.name = jsonObject.optString("food_name").toString();
                    data.image = jsonObject.optString("food_image").toString();
                    data.price = Integer.parseInt(jsonObject.optString("food_price").toString());
                    data.today = Integer.parseInt(jsonObject.optString("today").toString());
                    Log.d("Akul", data.name);
                    helper.InsertPlease(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Loading products. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
        return;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}*/