package sg.edu.rp.c346.id21021397.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    EditText etDate;
    Button btnInsert;
    Button btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayList<String> alTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.editTextTasks);
        etDate = findViewById(R.id.editTextDate);
        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        lv = findViewById(R.id.lv);
        alTasks = new ArrayList<>();
        

        ArrayAdapter aaTask = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,alTasks);

        lv.setAdapter(aaTask);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(etTask.getText().toString(), etDate.getText().toString());

            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                ArrayList<Task> datalist = db.getTasks();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + " " + data.get(i) + "\n";
                    String pos = i + " ";
                    alTasks.add(datalist.get(i).getId() + " \n" + datalist.get(i).getDescription() + " \n" + datalist.get(i).getDate() + " ");
                    aaTask.notifyDataSetChanged();
                }
                tvResults.setText(txt);




            }
        });

    }

}