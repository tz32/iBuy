package android.ibuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class Settings extends ActionBarActivity implements View.OnClickListener {

    ImageButton chatTab;
    ImageButton listTab;
    ImageButton historyTab;

    ArrayList<String> completedtasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listTab = (ImageButton) findViewById(R.id.listTab);
        listTab.setOnClickListener(this);

        chatTab = (ImageButton) findViewById(R.id.chatTab);
        chatTab.setOnClickListener(this);

        historyTab = (ImageButton) findViewById(R.id.historyTab);
        historyTab.setOnClickListener(this);

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

            case R.id.chatTab:
                Intent chatIntent = new Intent(this, ChatActivity.class);
                chatIntent.putStringArrayListExtra("completedlist", completedtasks);
                startActivity(chatIntent);
                break;

            case R.id.historyTab:
                Intent historyIntent = new Intent(this, History.class);
                historyIntent.putStringArrayListExtra("completedlist", completedtasks);
                startActivity(historyIntent);
                break;

            default:
                break;
        }
    }
}
