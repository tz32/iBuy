package android.ibuy;

/**
 * Created by Tomas on 11/4/15.
 */
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

@ParseClassName("Task")
public class Task extends ParseObject{
    public Task(){

    }

    public boolean isCompleted(){
        return getBoolean("completed");
    }

    public void setCompleted(boolean complete){
        put("completed", complete);
    }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }

    public int getUser(){
        return getInt("userID");
    }

    public void setUser(int userID){
        put("userID", userID);
    }
}
