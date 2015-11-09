package android.ibuy;

import com.parse.ParseObject;

/**
 * Created by Tomas on 11/8/15.
 */
public class ChatList extends ParseObject{
    public ChatList(){

    }

    public String getUsername(){
        return getString("username");
    }

    // public String setUsername(){
    //    put("username", username);
    // }

    public String getDescription(){
        return getString("description");
    }

    public void setDescription(String description){
        put("description", description);
    }
}

