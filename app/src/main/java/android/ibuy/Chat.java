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
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class Chat extends ActionBarActivity implements View.OnClickListener, OnItemClickListener {

    int chatcalled;

    ImageButton listTab;
    ImageButton historyTab;
    ImageButton settingsTab;

    ArrayList<String> completedtasks;

    private EditText cTaskInput;
    private ListView cListView;
    private ChatListAdapter cAdapter;

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



        //if (chatcalled == 0) {
        //    Parse.initialize(this, "Bdpx4McPbNgNqUr5SErqCNHTbZIX0PWMjY7Qzybl", "H8MaKiFdi9ka6eAqtSsbR86503MHhjN9rOAxS8hp");
        //    ParseObject.registerSubclass(Task.class);
        //}

        cTaskInput = (EditText) findViewById(R.id.chat_input);

        cListView = (ListView) findViewById(R.id.chat_list);
        //cListView.setOnItemClickListener(this);

        cAdapter = new ChatListAdapter(this, new ArrayList<ChatList>());
        //cListView.setAdapter(cAdapter);

        Intent intent = getIntent();
        completedtasks = intent.getStringArrayListExtra("completedlist");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            // If back button is pressed, go to the home screen
            case R.id.listTab:
                Intent listIntent = new Intent(this, MainActivity.class);
                listIntent.putExtra("listcalled", 1);
                startActivity(listIntent);
                break;

            case R.id.historyTab:
                Intent historyIntent = new Intent(this, History.class);
                historyIntent.putStringArrayListExtra("completedlist", completedtasks);
                startActivity(historyIntent);
                break;

            case R.id.settingsTab:
                Intent settingsIntent = new Intent(this, Settings.class);
                settingsIntent.putStringArrayListExtra("completedlist", completedtasks);
                startActivity(settingsIntent);
                break;

            default:
                break;
        }
    }



    public void createTask(View v) {
        if (cTaskInput.getText().length() > 0){
            ChatList t = new ChatList();
            t.setDescription(cTaskInput.getText().toString());
            //t.setCompleted(false);
            t.saveEventually();
            cTaskInput.setText("");

            cAdapter.insert(t, 0);
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChatList task = cAdapter.getItem(position);
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
