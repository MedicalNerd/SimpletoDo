package sg.edu.rp.c346.id22021421.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner TaskManager;
    EditText Input;
    Button Add, Delete, Clear;
    ListView DisplayTv;
    ArrayAdapter<String> adapter;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskManager = findViewById(R.id.spinnerTask);
        Input = findViewById(R.id.editTextInput);
        Add = findViewById(R.id.buttonAdd);
        Delete = findViewById(R.id.buttonDelete);
        Clear = findViewById(R.id.buttonClear);
        DisplayTv = findViewById(R.id.ListView);
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        DisplayTv.setAdapter(adapter);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = Input.getText().toString().trim();
                if(!task.isEmpty()){
                    taskList.add(task);
                    adapter.notifyDataSetChanged();
                    Input.setText("");
                }
                else{
                    Toast.makeText(MainActivity.this, "You do not have any task to add!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = Input.getText().toString().trim();
                if(!taskList.isEmpty()&& Input.length()!=0){
                    int pos =Integer.parseInt(Input.getText().toString());
                    if (!task.isEmpty() && pos >= 1 && pos <= taskList.size()&& !taskList.isEmpty()) {
                        taskList.remove(pos - 1);
                        adapter.notifyDataSetChanged();
                    }
                    else if (pos > taskList.size()) {
                        Toast.makeText(MainActivity.this, "Invalid task position!", Toast.LENGTH_SHORT).show();
                    }

                }
                else if(taskList.isEmpty()&& Input.length()!=0){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove!",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }
                else if(Input.length()==0){
                    Toast.makeText(MainActivity.this,"You didn't indicate any input!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!taskList.isEmpty()){
                    taskList.clear();
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(MainActivity.this, "List is Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TaskManager.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = parent.getItemAtPosition(position).toString();

                if (selectedOption.equals("Remove A Task")) {
                    Add.setEnabled(false);
                    Delete.setEnabled(true);
                    Clear.setEnabled(true);
                } else {
                    Add.setEnabled(true);
                    Delete.setEnabled(false);
                    Clear.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where no item is selected (if needed)
            }
        });
    }
}

