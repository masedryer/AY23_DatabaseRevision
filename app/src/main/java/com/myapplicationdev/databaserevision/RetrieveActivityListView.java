package com.myapplicationdev.databaserevision;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RetrieveActivityListView extends AppCompatActivity {

    Button btnGetNotes;

    ListView lv;
    ArrayAdapter<Note> aa;
    ArrayList<Note> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_lv);

        btnGetNotes = findViewById(R.id.btnGetTasks);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<Note>(RetrieveActivityListView.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        registerForContextMenu(lv);

        btnGetNotes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

              DBHelper db = new DBHelper(RetrieveActivityListView.this);
              al.clear();
              al.addAll(db.getNotesInObjects());
              aa.notifyDataSetChanged();

            }
        });


        //Option: Implement dialog to edit a record
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

                View viewDialog = inflater.inflate(R.layout.list_view_dialogue, null);

                final EditText newName = viewDialog.findViewById(R.id.editTextName);
                final EditText newPriority = viewDialog.findViewById(R.id.editTextPriority);

                Note data = al.get(position);
                newName.setText(data.getContent());
                newPriority.setText("" + data.getPriority());

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(RetrieveActivityListView.this);
                myBuilder.setView(viewDialog);

                myBuilder.setTitle("Edit Content");

                myBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(RetrieveActivityListView.this);

                        data.setContent(newName.getText().toString());
                        data.setPriority(newPriority.getText().toString());

                        db.updateNote(data);
                        Toast.makeText(RetrieveActivityListView.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                });

                myBuilder.setNegativeButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });



        //Option: Implement context to delete a record

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,0,0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        DBHelper db = new DBHelper(RetrieveActivityListView.this);

        if(item.getItemId() == 0){
            Note note = (Note) aa.getItem(pos);

            db.deleteNote(note.getId());
            Toast.makeText(RetrieveActivityListView.this, "Note deleted", Toast.LENGTH_SHORT).show();
            al.clear();
            al.addAll(db.getNotesInObjects());
            aa.notifyDataSetChanged();
        }

        return super.onContextItemSelected(item);
    }


}