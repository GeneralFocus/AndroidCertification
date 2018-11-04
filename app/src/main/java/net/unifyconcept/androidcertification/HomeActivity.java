package net.unifyconcept.androidcertification;

/*import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;*/

import net.unifyconcept.androidcertification.model.GitUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import net.unifyconcept.androidcertification.model.PlayersModel;

import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Olabode Qudus on 11/3/2018.
 */

public class HomeActivity extends AppCompatActivity
{private ParseContent parseContent;
    private final int jsoncode = 1;
    private ListView listView;
    private ArrayList<PlayersModel> playersModelArrayList;
    private CustomeAdapter customeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        parseContent = new ParseContent(this);
        listView = (ListView) findViewById(R.id.lv);

        try {
            parseJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJson() throws IOException, JSONException {

        if (!AndyUtils.isNetworkAvailable(HomeActivity.this)) {
            Toast.makeText(HomeActivity.this, "Internet is required!", Toast.LENGTH_SHORT).show();
            return;
        }
        AndyUtils.showSimpleProgressDialog(HomeActivity.this);
        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(AndyConstants.ServiceType.URL);
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:

                if (parseContent.isSuccess(response)) {
                    AndyUtils.removeSimpleProgressDialog();  //will remove progress dialog
                    playersModelArrayList = parseContent.getInfo(response);
                    customeAdapter = new CustomeAdapter(this,playersModelArrayList);
                    listView.setAdapter(customeAdapter);

                }else {
                    Toast.makeText(HomeActivity.this, parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }



public void comment(){
/* extends AppCompatActivity implements LoadJSONTask.Listener, AdapterView.OnItemClickListener {
    private ListView mListView;

    public static final String URL = "https://api.github.com/search/users?q=language:java%20location:lagos";

    private List<HashMap<String, String>> mAndroidMapList = new ArrayList<>();

    private static final String KEY_LOGIN = "login";
    private static final String KEY_HTML_URL = "html_url";
    private static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        new LoadJSONTask(this).execute(URL);
    }
    @Override
    public void onLoaded(List<GitUser> androidList) {

        for (GitUser gituser : androidList) {

            HashMap<String, String> map = new HashMap<>();

            map.put(KEY_LOGIN, gituser.getVer());
            map.put(KEY_HTML_URL, gituser.getName());
            map.put(KEY_ID, gituser.getApi());

            mAndroidMapList.add(map);
        }

        loadListView();
    }

    @Override
    public void onError() {

        Toast.makeText(this, "Error !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(this, mAndroidMapList.get(i).get(KEY_HTML_URL),Toast.LENGTH_LONG).show();
    }

    private void loadListView() {

        ListAdapter adapter = new SimpleAdapter(HomeActivity.this, mAndroidMapList, R.layout.list_item,
                new String[] { KEY_LOGIN, KEY_HTML_URL, KEY_ID },
                new int[] { R.id.version,R.id.name, R.id.api });

        mListView.setAdapter(adapter);

    }
}*/
}
}
