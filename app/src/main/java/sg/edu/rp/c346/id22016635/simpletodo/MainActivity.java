package sg.edu.rp.c346.id22016635.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add;
    Button clear;

    Button delete;
    ListView todoList;
    EditText todo;

    Spinner choice;


    ArrayList<String> todoArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = findViewById(R.id.btnAdd);
        clear = findViewById(R.id.btnClear);
        delete = findViewById(R.id.btnDelete);
        todoList = findViewById(R.id.toDoList);
        todo = findViewById(R.id.etToDo);
        choice = findViewById(R.id.choiceSPIN);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,todoArray);
        todoList.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!todo.getText().toString().equalsIgnoreCase("")) {
                    String action = todo.getText().toString();
                    todoArray.add(action);
                    todo.setText("");
                    adapter.notifyDataSetChanged();
                }else{
                    Toast errMsg = Toast.makeText(getApplicationContext(), "You don't have any task to add", Toast.LENGTH_SHORT);
                    errMsg.show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//TRY CATCH FUNCTION//
               {
                   if(todo.getText().toString() == ""){
                       Toast errMsg = Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT);
                       errMsg.show();

                   }

                if(!todo.getText().toString().isEmpty()){
                int toDelete = Integer.parseInt(todo.getText().toString());



                if (toDelete < todoArray.size()  ) {

                        todoArray.remove(toDelete);
                        todo.setText("");
                        adapter.notifyDataSetChanged();

                } else if (todoArray.isEmpty()) {
                    Toast errMsg = Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT);
                    errMsg.show();
                } else {
                    Toast errMsg = Toast.makeText(getApplicationContext(), "Wrong Index Number", Toast.LENGTH_SHORT);
                    errMsg.show();
                }
            }
        }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!todoArray.isEmpty()) {
                    todoArray.clear();
                    adapter.notifyDataSetChanged();
                    todo.setText("");
                    adapter.notifyDataSetChanged();
                }else{
                    Toast errMsg = Toast.makeText(getApplicationContext(),"You don't have any task to remove",Toast.LENGTH_SHORT);
                    errMsg.show();
                }
            }
        });

        choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        todo.setHint("Type in a new task here");
                        todo.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                        todo.setText("");
                        break;
                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        todo.setHint("Type in the index of the task to be removed");
                        todo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        todo.setText("");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}