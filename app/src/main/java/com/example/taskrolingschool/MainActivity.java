package com.example.taskrolingschool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    public ArrayList<ListItem> animalPerson;
    FloatingActionButton flActBtn, flActBtnFilter;
    MyRecyclerViewAdapter adapter;
    EmployeeDao employeeDao;
    AppDatabase db;
    //int currentFilter=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);
        animalPerson = new ArrayList<>();
        initializedList();// Test content for animalPerson list
        flActBtn = (FloatingActionButton) findViewById(R.id.flActBtn);
        flActBtnFilter = (FloatingActionButton) findViewById(R.id.flActBtnFilter);
        flActBtn.setOnClickListener(this);
        flActBtnFilter.setOnClickListener(this);

        db = App.getInstance().getDatabase();
        employeeDao = db.employeeDao();

    }

    public void initializedList() {
        animalPerson.add(new ListItem(0, "Алёша", 2, "Cat"));
        animalPerson.add(new ListItem(0, "Вася", 1, "Cat"));
        animalPerson.add(new ListItem(0, "Юлий", 3, "Cat"));
        animalPerson.add(new ListItem(0, "Гусь", 4, "Dog"));
        animalPerson.add(new ListItem(0, "Жук", 5, "Dog"));
        animalPerson.add(new ListItem(0, "Борис", 8, "Dog"));
        animalPerson.add(new ListItem(0, "Мурзик", 6, "Dog"));
        animalPerson.add(new ListItem(0, "Дымок", 9, "Aist"));
        animalPerson.add(new ListItem(0, "Яркий", 10, "Aist"));
        animalPerson.add(new ListItem(0, "Беляш", 11, "Aist"));// Test content for animalPerson list
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flActBtn:
                Intent intent = new Intent(this, Add_Animal_Activity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.flActBtnFilter:
                Intent intentSec = new Intent(this, activity_add_filter.class);
                startActivityForResult(intentSec, 2);
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 1) {
            String name = data.getStringExtra("name");
            int age = data.getIntExtra("age", 0);
            String breed = data.getStringExtra("breed");
            ListItem tmp = new ListItem(animalPerson.size(), name, age, breed);
            animalPerson.add(tmp);
            employeeDao.insert(tmp);
//        int index = animalPerson.size()-1;
//        Toast.makeText(this,animalPerson.get(index).breed,Toast.LENGTH_SHORT).show();


            int size = animalPerson.size();
            if (size == 0) adapter.notifyItemChanged(animalPerson.size());
            else adapter.notifyItemChanged(animalPerson.size() - 1);
        } else if (requestCode == 2) {
            int filter = data.getIntExtra("filter", 0);
            //if(filter != currentFilter){
            sortListby(filter);
            // }
        } else {
        }
    }

    class MyViewHolder extends ViewHolder {
        TextView name, age, breed;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            age = (TextView) itemView.findViewById(R.id.textViewAge);
            breed = (TextView) itemView.findViewById(R.id.textViewBreed);
        }
    }

    class MyRecyclerViewAdapter extends Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclre_view_item, parent, false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ListItem listItem = animalPerson.get(holder.getAdapterPosition());
            holder.name.setText("Name: " + listItem.name);
            holder.age.setText("Age: " + valueOf(listItem.age));
            holder.breed.setText("Breed: " + listItem.breed);
            //say helloggg

        }

        @Override
        public int getItemCount() {
            return animalPerson.size();
        }

    }

    public void sortListby(int filter) {  // сортирует лист с данными в зависимости от переданного атрибута


        switch (filter) {
            case 0:
                ArrayList<String> tmpList = new ArrayList<>();
                for (ListItem x : animalPerson) {
                    tmpList.add(x.name);
                }
                Collections.sort(tmpList);
                ArrayList<ListItem> tmpListItem = new ArrayList<>();
                for (int i = 0; i < tmpList.size(); i++) {
                    tmpListItem.add(new ListItem(0, tmpList.get(i), 0, ""));
                }
                for (int i = 0; i < tmpListItem.size(); i++) {
                    for (int y = 0; y < animalPerson.size(); y++) {
                        if (tmpListItem.get(i).name.equals(animalPerson.get(y).name)) {
                            tmpListItem.set(i, animalPerson.get(y));
                            animalPerson.remove(y);
                            break;
                        }
                    }
                }
                animalPerson.addAll(tmpListItem);
                adapter.notifyDataSetChanged();
                break;
            case 1:
                ArrayList<Integer> tmpListAge = new ArrayList<>();
                for (ListItem x : animalPerson) {
                    tmpListAge.add(x.age);
                }
                Collections.sort(tmpListAge);
                ArrayList<ListItem> tmpListItemAge = new ArrayList<>();
                for (int i = 0; i < tmpListAge.size(); i++) {
                    tmpListItemAge.add(new ListItem(0, "", tmpListAge.get(i), ""));
                }
                for (int i = 0; i < tmpListItemAge.size(); i++) {
                    for (int y = 0; y < animalPerson.size(); y++) {
                        if (tmpListItemAge.get(i).age == animalPerson.get(y).age) {
                            tmpListItemAge.set(i, animalPerson.get(y));
                            animalPerson.remove(y);
                            break;
                        }
                    }
                }
                animalPerson.addAll(tmpListItemAge);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                ArrayList<String> tmpListBreed = new ArrayList<>();
                for (ListItem x : animalPerson) {
                    tmpListBreed.add(x.breed);
                }
                Collections.sort(tmpListBreed);
                ArrayList<ListItem> tmpListItemBreed = new ArrayList<>();
                for (int i = 0; i < tmpListBreed.size(); i++) {
                    tmpListItemBreed.add(new ListItem(0, "", 0, tmpListBreed.get(i)));
                }
                for (int i = 0; i < tmpListItemBreed.size(); i++) {
                    for (int y = 0; y < animalPerson.size(); y++) {
                        if (tmpListItemBreed.get(i).breed.equals(animalPerson.get(y).breed) && !tmpListItemBreed.get(i).breed.equals("")) {
                            tmpListItemBreed.set(i, animalPerson.get(y));
                            animalPerson.remove(y);
                            break;
                        }
                    }
                }
                animalPerson.clear();
                animalPerson.addAll(tmpListItemBreed);
                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }
}