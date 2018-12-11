package th.ac.kmitl.a59070007;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<String> _menu = new ArrayList<>();
    SQLiteDatabase myDB;

    public HomeFragment() {
        _menu.add("PROFILE SETUP");
        _menu.add("MY FREINDS");
        _menu.add("Sign Out");
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("my DB",Context.MODE_PRIVATE, null);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        final ArrayAdapter<String> _menuAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                _menu
        );

        ListView _menuList = (ListView) getView().findViewById(R.id.menu_list);
        _menuList.setAdapter(_menuAdapter);
        _menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MENU", "Click on menu = " + _menu.get(i));
                switch (_menu.get(i)) {
                    case "PROFILE SETUP":
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new ProfileFragment())
                                .addToBackStack(null)
                                .commit();
                        Log.d("USER", "GO TO PROFILE");
                        break;
                    case "MY FRIENDS":
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new FriendFragment())
                                .addToBackStack(null)
                                .commit();
                        Log.d("USER", "GO TO WEIGHT");
                        break;
                    case "Sign Out":
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_view, new LoginFragment())
                                .commit();
                        Log.d("USER", "SIGN OUT");
                        break;
                    default:
                        break;
                }

            }
        });
    }
}


