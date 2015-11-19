package android.ibuy;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 11/18/15.
 */
public class HistoryAdapter extends ArrayAdapter<String> {
    private Context hContext;
    private List<String> hTasks;

    public HistoryAdapter(Context context, ArrayList<String> objects) {
        super(context, R.layout.task_row_item, objects);
        this.hContext = context;
        this.hTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(hContext);
            convertView = mLayoutInflater.inflate(R.layout.task_row_item, null);
        }

        String taskname = hTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.task_description);

        descriptionView.setText(taskname);

        return convertView;
    }

}