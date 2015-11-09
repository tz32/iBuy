package android.ibuy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.ibuy.R;
import android.ibuy.Task;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatListAdapter extends ArrayAdapter<ChatList> {
    private Context mContext;
    private List<ChatList> mTasks;

    public ChatListAdapter(Context context, ArrayList<ChatList> objects) {
        super(context, R.layout.chat_row_item, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.chat_row_item, null);
        }

        ChatList task = mTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.chatlist_description);

        descriptionView.setText(task.getDescription());

        //if(task.isCompleted()){
        //    descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        //}else{
        //    descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        //}

        return convertView;
    }

}