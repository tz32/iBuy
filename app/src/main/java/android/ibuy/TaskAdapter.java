package android.ibuy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.ibuy.R;
import android.ibuy.Task;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context mContext;
    private List<Task> mTasks;
    RoundImage roundedImage;
    Bitmap bm;

    public TaskAdapter(Context context, ArrayList<Task> objects) {
        super(context, R.layout.task_row_item, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.task_row_item, null);
        }

        Task task = mTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.task_description);

        ImageView userView = (ImageView) convertView.findViewById(R.id.img);

        descriptionView.setText(task.getDescription());


        switch (task.getUser())
        {
            case 1:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.rick);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.rick);
                break;
            case 2:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.morty);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.morty);
                break;
            case 3:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.meeseeks);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.meeseeks);
                break;
            case 4:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.summer);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.summer);
                break;
            case 5:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.dude);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.summer);
                break;
            case 6:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.beth);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.summer);
                break;
            default:
                bm = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.empty);
                roundedImage = new RoundImage(bm);
                userView.setImageDrawable(roundedImage);
                //userView.setImageResource(R.drawable.empty);
                break;
        }



        if(task.isCompleted()){
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            descriptionView.setPaintFlags(descriptionView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        return convertView;
    }
}