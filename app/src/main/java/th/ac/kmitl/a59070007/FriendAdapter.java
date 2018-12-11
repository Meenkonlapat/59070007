package th.ac.kmitl.a59070007;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FriendAdapter extends ArrayAdapter {
    ArrayList<JSONObject> postList;
    Context context;

    public FriendAdapter(Context context, int resource, ArrayList<JSONObject> objects)
    {
        super(context, resource, objects);
        this.postList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View postItem = LayoutInflater.from(context).inflate(R.layout.fragment_friend_item_list, parent, false);
        JSONObject postObj = postList.get(position);
        TextView postHeader = (TextView) postItem.findViewById(R.id.post_list_item_name);
        TextView postEmail  = (TextView) postItem.findViewById(R.id.post_list_item_email);
        TextView postPhone = (TextView) postItem.findViewById(R.id.post_list_item_phone);
        TextView postWebsite  = (TextView) postItem.findViewById(R.id.post_list_item_website);
        try
        {
            postHeader.setText(postObj.getString("id") + " : " + postObj.getString("name"));
            postEmail.setText(postObj.getString("email"));
            postPhone.setText(postObj.getString("Phone"));
            postWebsite.setText(postObj.getString("Website"));
        }
        catch (JSONException e)
        {
            Log.d("test", "catch JSONException : " + e.getMessage());
        }
        return postItem;
    }
}

