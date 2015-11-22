package android.ibuy;

import android.content.Intent;
import android.graphics.Paint;
import android.net.MailTo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class History extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    ImageButton listTab;
    ImageButton chatTab;
    ImageButton settingsTab;

    ArrayList<String> completedtasks;

    private ListView mListView;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listTab = (ImageButton) findViewById(R.id.listTab);
        listTab.setOnClickListener(this);

        chatTab = (ImageButton) findViewById(R.id.chatTab);
        chatTab.setOnClickListener(this);

        settingsTab = (ImageButton) findViewById(R.id.settingsTab);
        settingsTab.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.historylist);
        mListView.setOnItemClickListener(this);

        mAdapter = new HistoryAdapter(this, new ArrayList<String>());
        mListView.setAdapter(mAdapter);

        Intent intent = getIntent();
        completedtasks = intent.getStringArrayListExtra("completedlist");

        if (completedtasks != null)
            mAdapter.addAll(completedtasks);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.listTab:
                Intent listIntent = new Intent(this, MainActivity.class);
                listIntent.putExtra("listcalled", 1);
                startActivity(listIntent);
                break;

            case R.id.chatTab:
                Intent chatIntent = new Intent(this, ChatActivity.class);
                chatIntent.putStringArrayListExtra("completedlist", completedtasks);
                startActivity(chatIntent);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String task = mAdapter.getItem(position);
//        TextView taskDescription = (TextView) view.findViewById(R.id.task_description);
//
//        task.setCompleted(!task.isCompleted());
//
//        if(task.isCompleted()){
//            taskDescription.setPaintFlags(taskDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//        }else{
//            taskDescription.setPaintFlags(taskDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
//        }
//
//        task.saveEventually();
    }
}
