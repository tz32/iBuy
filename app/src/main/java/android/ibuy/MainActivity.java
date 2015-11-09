package android.ibuy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQuery.CachePolicy;

public class MainActivity extends Activity implements OnItemClickListener, View.OnClickListener {

    int listcalled;

    ImageButton chatTab;
    ImageButton historyTab;
    ImageButton settingsTab;

    private EditText mTaskInput;
    private ListView mListView;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatTab = (ImageButton) findViewById(R.id.chatTab);
        chatTab.setOnClickListener(this);

        historyTab = (ImageButton) findViewById(R.id.historyTab);
        historyTab.setOnClickListener(this);

        settingsTab = (ImageButton) findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);

        listcalled = 0;

        Intent intent = getIntent();
        listcalled = intent.getIntExtra("listcalled", 0);

        if (listcalled == 0) {
            Parse.initialize(this, "Bdpx4McPbNgNqUr5SErqCNHTbZIX0PWMjY7Qzybl", "H8MaKiFdi9ka6eAqtSsbR86503MHhjN9rOAxS8hp");
            ParseObject.registerSubclass(Task.class);
        }


        mTaskInput = (EditText) findViewById(R.id.task_input);

        mListView = (ListView) findViewById(R.id.task_list);
        mListView.setOnItemClickListener(this);

        mAdapter = new TaskAdapter(this, new ArrayList<Task>());
        mListView.setAdapter(mAdapter);

        updateData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.chatTab:
                Intent chatIntent = new Intent(this, Chat.class);
                chatIntent.putExtra("chatcalled", 1);
                startActivity(chatIntent);
                break;

            case R.id.historyTab:
                Intent historyIntent = new Intent(this, History.class);
                startActivity(historyIntent);
                break;

            case R.id.settingsTab:
                Intent settingsIntent = new Intent(this, Settings.class);
                startActivity(settingsIntent);
                break;

            default:
              break;
        }
    }

    public void createTask(View v) {
        if (mTaskInput.getText().length() > 0){
            Task t = new Task();
            t.setDescription(mTaskInput.getText().toString());
            t.setCompleted(false);
            t.saveEventually();
            mTaskInput.setText("");

            mAdapter.insert(t, 0);
        }
    }

    public void updateData(){
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.setCachePolicy(CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<Task>() {

            @Override
            public void done(List<Task> tasks, ParseException error) {
                if (tasks != null) {
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Task task = mAdapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.task_description);

        task.setCompleted(!task.isCompleted());

        if(task.isCompleted()){
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        task.saveEventually();
    }
}
