### Nested Recyclerview in android app. 

<br/>

<img src="https://github.com/suhelarmankhan/nested-recyclerview/assets/112642509/28e5f9f5-64b1-4271-9b97-fe4b5ce0f032" alt="Additional Screenshot"  height="600"> 


### Create an xml layout for the child RecyclerView item called 'child_rv_layout' and Put the xml codes there.
## child_rv_layout.xml
```xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:paddingStart="8dp"
    android:paddingEnd="5dp"
    android:layout_height="wrap_content">


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        >


        <androidx.cardview.widget.CardView
            android:layout_width="120dp"
            android:layout_height="170dp"
            android:id="@+id/cv_child_item"
            app:cardCornerRadius="10dp"

            >


            <ImageView
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:id="@+id/iv_child_item"
                android:scaleType="fitXY"
                android:src="@drawable/latest_1"


                />


        </androidx.cardview.widget.CardView>

    </LinearLayout>


</RelativeLayout>


```


#### Create an xml layout for the Parent RecyclerView item called 'parent_rv_layout.xml' and Put the xml codes there.
## parent_rv_layout.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:background="@color/black"
    android:layout_height="wrap_content">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"

        >
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="10dp"
            android:textSize="20sp"
            android:textStyle="bold"
            android:text="Title"
            android:id="@+id/tv_parent_title"
            android:textColor="@color/white"
            />


        <androidx.recyclerview.widget.RecyclerView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"

            android:id="@+id/rv_child"

            >


        </androidx.recyclerview.widget.RecyclerView>

    </LinearLayout>

</RelativeLayout>
```

<br/>


#### These codes are for activity_main.xml. It comes built with your projector by default. Find the activity_main.xml inside the layout folder and put the codes inside it
## activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/black"
    tools:context=".MainActivity">

<androidx.recyclerview.widget.RecyclerView
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="10dp"
    android:id="@+id/rv_parent"

    />



</RelativeLayout>

```

<br/>



#### Now we will create two adapter called 'ChildAdapter' and 'ParentAdapter'. Give the codes inside it.

#### ChildAdapter.java

```java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tecoditlimited.nestedrecyclerview.Model.ChildModelClass;
import com.tecoditlimited.nestedrecyclerview.R;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {


    List<ChildModelClass> childModelClassList;
    Context context;

    public ChildAdapter(List<ChildModelClass> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.child_rv_layout, null,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {

        // get Adapter Position
        int clickedPosition = holder.getAdapterPosition();

        holder.iv_child_image.setImageResource(childModelClassList.get(position).image);

        //item on click
        holder.iv_child_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast
                Toast.makeText(context.getApplicationContext(), "Item clicked " + clickedPosition , Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_child_image;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_child_image = itemView.findViewById(R.id.iv_child_item);

        }
    }
}

```
<br/>

## ParentAdapter
```java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tecoditlimited.nestedrecyclerview.Model.ParentModelClass;
import com.tecoditlimited.nestedrecyclerview.R;

import java.util.List;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {


    List<ParentModelClass> parentModelClassList;
    Context context;

    public ParentAdapter(List<ParentModelClass> parentModelClassList, Context context) {
        this.parentModelClassList = parentModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parent_rv_layout, null,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.ViewHolder holder, int position) {

        holder.tv_parent_title.setText(parentModelClassList.get(position).title);

        ChildAdapter childAdapter;
        childAdapter = new ChildAdapter(parentModelClassList.get(position).childModelClassList,context);
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.rv_child.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();





    }

    @Override
    public int getItemCount() {
        return parentModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


    RecyclerView rv_child;
    TextView tv_parent_title;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        rv_child = itemView.findViewById(R.id.rv_child);
        tv_parent_title = itemView.findViewById(R.id.tv_parent_title);

        }
    }
}

```
 
<br/>

#### Now I will make two models class. One name is 'ChildModelClass' and the other is 'ParentModelClass'

## ChildModelClass
```java

public class ChildModelClass {

    public int image;


    public ChildModelClass(int image) {
        this.image = image;
    }
}


```
<br/>

## ChildModelClass
```java
import java.util.List;


public class ParentModelClass {

    public String title;
    public List<ChildModelClass> childModelClassList;

    public ParentModelClass(String title, List<ChildModelClass> childModelClassList) {
        this.title = title;
        this.childModelClassList = childModelClassList;
    }
}



```
<br/>


#### Now we will code it for MainActivity.
## MainActivity

```java

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


```

