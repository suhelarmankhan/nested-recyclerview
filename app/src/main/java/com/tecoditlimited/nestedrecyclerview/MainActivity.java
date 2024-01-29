package com.tecoditlimited.nestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tecoditlimited.nestedrecyclerview.Adapter.ParentAdapter;
import com.tecoditlimited.nestedrecyclerview.Model.ChildModelClass;
import com.tecoditlimited.nestedrecyclerview.Model.ParentModelClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <ParentModelClass> parentModelClassArrayList;
    ArrayList <ChildModelClass> childModelClassArrayList;
    ArrayList<ChildModelClass>HollywoodMoviesList;
    ArrayList<ChildModelClass> recentlyWatchedList;
    ArrayList <ChildModelClass> latestList;
    ArrayList <ChildModelClass> BollywoodMoviesList;
    ArrayList <ChildModelClass> TurkishMoviesList;
    ParentAdapter parentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_parent);
        childModelClassArrayList = new ArrayList<>();
        HollywoodMoviesList = new ArrayList<>();
        recentlyWatchedList = new ArrayList<>();
        parentModelClassArrayList = new ArrayList<>();
        BollywoodMoviesList = new ArrayList<>();
        TurkishMoviesList = new ArrayList<>();
        latestList = new ArrayList<>();


        latestList.add(new ChildModelClass(R.drawable.latest_1));
        latestList.add(new ChildModelClass(R.drawable.latest_2));
        latestList.add(new ChildModelClass(R.drawable.latest_3));
        latestList.add(new ChildModelClass(R.drawable.latest_4));
        latestList.add(new ChildModelClass(R.drawable.latest_5));
        latestList.add(new ChildModelClass(R.drawable.latest_6));
        latestList.add(new ChildModelClass(R.drawable.latest_7));
        latestList.add(new ChildModelClass(R.drawable.latest_8));
        latestList.add(new ChildModelClass(R.drawable.latest_9));

        parentModelClassArrayList.add(new ParentModelClass("Latest Movie",latestList));



        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_1));
        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_2));
        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_3));
        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_4));
        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_5));
        HollywoodMoviesList.add(new ChildModelClass(R.drawable.hollywood_6));


        parentModelClassArrayList.add(new ParentModelClass("Hollywood Movie",HollywoodMoviesList));



        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollwood_3));
        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollywood_6));
        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollwood_1));
        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollwood_2));
        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollwood_5));
        BollywoodMoviesList.add(new ChildModelClass(R.drawable.bollywood_6));


        parentModelClassArrayList.add(new ParentModelClass("Bollywood Movie",BollywoodMoviesList));

        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_5));
        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_4));
        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_1));
        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_2));
        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_3));
        TurkishMoviesList.add(new ChildModelClass(R.drawable.turkish_4));

        parentModelClassArrayList.add(new ParentModelClass("Turkish Movie",TurkishMoviesList));




        parentAdapter = new ParentAdapter(parentModelClassArrayList, MainActivity.this);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     recyclerView.setAdapter(parentAdapter);
     parentAdapter.notifyDataSetChanged();


    }
}