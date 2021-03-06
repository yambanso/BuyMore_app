package com.example.buymore_app.screen_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.buymore_app.Adapter.ItemsAdapter;
import com.example.buymore_app.Adapter.Utility;
import com.example.buymore_app.Items;
import com.example.buymore_app.R;
import com.example.buymore_app.backend.favouriteItem;
import com.example.buymore_app.backend.favouritesDatabase;
import com.example.buymore_app.backend.itemDao;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static com.example.buymore_app.R.drawable.huawei_mate_20;
import static com.example.buymore_app.R.drawable.lg_v60;
import static com.example.buymore_app.R.drawable.nike_air_270;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favouritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favouritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;

    public favouritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favouritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favouritesFragment newInstance(String param1, String param2) {
        favouritesFragment fragment = new favouritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    List<favouriteItem> favlist = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView =view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Items> list = new ArrayList<>();

        favouritesDatabase db = favouritesDatabase.getDb(getContext());
        itemDao dao = db.Item();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uID = user.getUid();
        new Thread(new Runnable() {
            @Override
            public void run() {
                favlist = dao.getFavourites(uID);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"Rtreiving item please wait patiently", Toast.LENGTH_LONG).show();
                    }
                });

                for(int i = 0; i < favlist.size();i++){
                    favouriteItem itemm = favlist.get(i);
                    String GsonObject = itemm.getJonObject();
                    Items it = Utility.getGsonParser().fromJson(GsonObject,Items.class);
                    list.add(it);

                }
            }

        }).start();

        ItemsAdapter adapter = new ItemsAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
