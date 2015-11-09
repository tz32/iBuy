package android.ibuy;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Chat extends ActionBarActivity implements View.OnClickListener {

    ImageButton listTab;
    ImageButton historyTab;
    ImageButton settingsTab;
    EditText mTaskInput;
    ListView mListView;
    ChatListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listTab = (ImageButton) findViewById(R.id.listTab);
        listTab.setOnClickListener(this);

        historyTab = (ImageButton) findViewById(R.id.historyTab);
        historyTab.setOnClickListener(this);

        settingsTab = (ImageButton) findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);

        Parse.initialize(this, "Bdpx4McPbNgNqUr5SErqCNHTbZIX0PWMjY7Qzybl", "H8MaKiFdi9ka6eAqtSsbR86503MHhjN9rOAxS8hp");
        ParseObject.registerSubclass(ChatList.class);

        mTaskInput = (EditText) findViewById(R.id.chat_input);

        mListView = (ListView) findViewById(R.id.chat_list);
        mListView.setOnItemClickListener((AdapterView.OnItemClickListener) this);

        mAdapter = new ChatListAdapter(this, new ArrayList<ChatList>());
        mListView.setAdapter(mAdapter);

        updateData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            // If back button is pressed, go to the home screen
            case R.id.listTab:
                Intent listIntent = new Intent(this, MainActivity.class);
                startActivity(listIntent);
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
            ChatList t = new ChatList();
            t.setDescription(mTaskInput.getText().toString());
            //t.setCompleted(false);
            t.saveEventually();
            mTaskInput.setText("");

            mAdapter.insert(t, 0);
        }
    }

    public void updateData(){
        ParseQuery<ChatList> query = ParseQuery.getQuery(ChatList.class);
        query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
        query.findInBackground(new FindCallback<ChatList>() {

            @Override
            public void done(List<ChatList> tasks, ParseException error) {
                if (tasks != null) {
                    mAdapter.clear();
                    mAdapter.addAll(tasks);
                }
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChatList task = mAdapter.getItem(position);
        TextView taskDescription = (TextView) view.findViewById(R.id.chatlist_description);

        //task.setCompleted(!task.isCompleted());

        //if(task.isCompleted()){
        //    taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //}else{
        //    taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        //}

        task.saveEventually();
    }
}
