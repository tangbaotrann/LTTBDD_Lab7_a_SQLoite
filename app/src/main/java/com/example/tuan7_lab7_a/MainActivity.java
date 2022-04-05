package com.example.tuan7_lab7_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Button btnAdd, btnDelete, btnCancel;
    private ListView listView;
    private List<People> listPeople;
    private PeopleAdapter adapter;
    private int index;

    private PeopleDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find id
        editTextName = findViewById(R.id.edtName);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnRemove);
        btnCancel = findViewById(R.id.btnCancel);
        listView = findViewById(R.id.idListview);

        // init obj
        db = new PeopleDatabaseHandler(this);

        // load --> UI
        loadDataToListView();

        // click button Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                if(name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bạn cần phải nhập tên!", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.addPeople(new People(name));
                loadDataToListView();

                editTextName.setText("");
                Toast.makeText(MainActivity.this, "Bạn vừa thêm: " + name, Toast.LENGTH_SHORT).show();
            }
        });

        // click listview (data to input)
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                People people = db.getPeople(listPeople.get(index).get_id());

                editTextName.setText(people.get_name());
            }
        });

        // click button delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bạn cần phải chọn tên để xóa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                db.deletePeople(listPeople.get(index).get_id());
                loadDataToListView();

                editTextName.setText("");
                Toast.makeText(MainActivity.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
            }
        });

        // click button cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName.setText(" ");
                loadDataToListView();
            }
        });

    }

    // Load --> UI
    public void loadDataToListView() {
        listPeople = db.getAllContacts();
        adapter = new PeopleAdapter(MainActivity.this, R.layout.item_custom_list_view, listPeople);
        listView.setAdapter(adapter);
    }
}