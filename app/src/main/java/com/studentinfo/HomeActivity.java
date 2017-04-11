package com.studentinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.studentinfo.adapter.StudentListAdapter;
import com.studentinfo.model.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HomeActivity extends AppCompatActivity {

    public static ArrayList<Student> studentArrayList=new ArrayList<>();

    private ListView studentList;
    private GridView studentGrid;
    private TextView addStudentBt;
    private StudentListAdapter adapter;

    private ImageView listIcon;
    private Toolbar toolbar;

    private String currentView="list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addStudentBt=(TextView)findViewById(R.id.addStudent);
        studentList=(ListView)findViewById(R.id.studentList);
        studentGrid=(GridView) findViewById(R.id.studentGrid);
        listIcon=(ImageView)findViewById(R.id.arrangeIcon);
        toolbar=(Toolbar)findViewById(R.id.homeToolbar);
        setSupportActionBar(toolbar);



        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Page");
        }
        toolbar.inflateMenu(R.menu.menu_options);
       // toolbar.showOverflowMenu();

        adapter=new StudentListAdapter(this,0);


        addStudentBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddStudentActivity.class));
            }
        });

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Please Select")
                        .setMessage("What would you like to do ?")
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                // do nothing
//                                dialog.dismiss();
//                                Intent i=new Intent(HomeActivity.this,AddStudentActivity.class);
//                                i.putExtra("student",studentArrayList.get(position));
//                                startActivity(i);
                            }
                        })
                        .setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                studentArrayList.remove(studentArrayList.get(position));
                                adapter.setStudentArrayList(studentArrayList);
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        listIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentView.equalsIgnoreCase("list")){
                    currentView="grid";
                    onResume();
                }else{
                    currentView="list";
                    onResume();
                }
            }
        });
    }

    @Override
     protected void onResume() {
        super.onResume();
        if(studentArrayList.size()!=0){
            adapter.setStudentArrayList(studentArrayList);

            if(currentView.equalsIgnoreCase("list")){
                studentList.setVisibility(View.VISIBLE);
                studentList.setAdapter(adapter);
                studentGrid.setVisibility(View.GONE);
            }else{
                studentList.setVisibility(View.GONE);
                studentGrid.setVisibility(View.VISIBLE);
                studentGrid.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sort_by_name: {
                // do your sign-out stuff
                if (studentArrayList.size() > 0) {
                    Collections.sort(studentArrayList, new Comparator<Student>() {
                        @Override
                        public int compare(final Student object1, final Student object2) {
                            return object1.getName().compareTo(object2.getName());
                        }
                    });

                    onResume();
                }
                break;
            }
            case R.id.menu_sort_by_rollNo: {
                // do your sign-out stuff
                Collections.sort(studentArrayList, new Comparator<Student>() {
                    @Override
                    public int compare(final Student object1, final Student object2) {
                        return (object1.getRollNo()+"").compareTo(object2.getRollNo()+"");
                    }
                });

                onResume();
                break;
            }

            // case blocks for other MenuItems (if any)
        }
        return false;
    }
}
