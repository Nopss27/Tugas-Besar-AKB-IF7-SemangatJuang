package tugas.pergipergi.wisata.fragment;
/*
NIM : 10119259
Nama : Naufal Daffa Ilyasa
Kelas : IF-7
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import tugas.pergipergi.wisata.R;
import tugas.pergipergi.wisata.adapter.RecyclerViewConfig;
import tugas.pergipergi.wisata.model.Place;


public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;
    RecyclerViewConfig adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_places);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //parsing data dari firebase ke dalam recyclerview
        FirebaseRecyclerOptions<Place> options =
                new FirebaseRecyclerOptions.Builder<Place>()
                        .setQuery(
                                FirebaseDatabase.getInstance().getReference().child("location"),
                                Place.class
                        )
                        .build();
        adapter = new RecyclerViewConfig(options);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}