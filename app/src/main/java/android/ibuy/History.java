package android.ibuy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class History extends ActionBarActivity implements View.OnClickListener {

    ImageButton listTab;
    ImageButton chatTab;
    ImageButton settingsTab;

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
                Intent chatIntent = new Intent(this, Chat.class);
                chatIntent.putExtra("chatcalled", 1);
                startActivity(chatIntent);
                break;

            case R.id.settingsTab:
                Intent settingsIntent = new Intent(this, Settings.class);
                startActivity(settingsIntent);
                break;

            default:
                break;
        }
    }
}
