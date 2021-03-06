package com.example.thirtyseven.lastlessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference databaseReferenceMon;
    DatabaseReference databaseReferenceTues;
    DatabaseReference databaseReferenceWedn;
    DatabaseReference databaseReferenceThurs;
    DatabaseReference databaseReferenceFrid;
    DatabaseReference databaseReferenceSatur;
    Button button;
    ListView listViewMonday;
    ListView listViewTuesday;
    ListView listViewWednesday;
    ListView listViewThursday;
    ListView listViewFriday;
    ListView listViewSaturday;
    Lesson lesson;
    Switch oddOrEven;
    Spinner dayOfWeek;
    String dofw, oddOrEvenStr = "even";
    int group = 510;
    ArrayList<Lesson> lessListMonday;
    ArrayList<Lesson> lessListTuesday;
    ArrayList<Lesson> lessListWednesday;
    ArrayList<Lesson> lessListThursday;
    ArrayList<Lesson> lessListFriday;
    ArrayList<Lesson> lessListSaturday;

    ArrayAdapter adapter;
    ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMethods();
        init();
        dofw = "monday";
        refreshRef(dofw, group, oddOrEvenStr);
        sixRef(oddOrEvenStr);
        setListener();
        setAdapters();
        dayOfWeek.setSelection(0);

    }

    void sixRef(String odorevstr) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceMon = firebaseDatabase.getReference("lessons").child("510").child("monday").child(odorevstr);
        databaseReferenceTues = firebaseDatabase.getReference("lessons").child("510").child("tuesday").child(odorevstr);
        databaseReferenceWedn = firebaseDatabase.getReference("lessons").child("510").child("wednesday").child(odorevstr);
        databaseReferenceThurs = firebaseDatabase.getReference("lessons").child("510").child("thursday").child(odorevstr);
        databaseReferenceFrid = firebaseDatabase.getReference("lessons").child("510").child("friday").child(odorevstr);
        databaseReferenceSatur = firebaseDatabase.getReference("lessons").child("510").child("saturday").child(odorevstr);

    }

    private void setAdapters() {
        lessListMonday = new ArrayList<>();
        lessListTuesday = new ArrayList<>();
        lessListWednesday = new ArrayList<>();
        lessListThursday = new ArrayList<>();
        lessListFriday = new ArrayList<>();
        lessListSaturday = new ArrayList<>();
        ArrayAdapter adapter1 = new CustomAdapter(this, R.layout.custom_adapter, lessListMonday);
        ArrayAdapter adapter2 = new CustomAdapter(this, R.layout.custom_adapter, lessListTuesday);
        ArrayAdapter adapter3 = new CustomAdapter(this, R.layout.custom_adapter, lessListWednesday);
        ArrayAdapter adapter4= new CustomAdapter(this, R.layout.custom_adapter, lessListThursday);
        ArrayAdapter adapter5 = new CustomAdapter(this, R.layout.custom_adapter, lessListFriday);
        ArrayAdapter adapter6 = new CustomAdapter(this, R.layout.custom_adapter, lessListSaturday);
        listViewMonday.setAdapter(adapter1);
        listViewTuesday.setAdapter(adapter2);
        listViewWednesday.setAdapter(adapter3);
        listViewThursday.setAdapter(adapter4);
        listViewFriday.setAdapter(adapter5);
        listViewSaturday.setAdapter(adapter6);
        ArrayAdapter<CharSequence> DofWAdapter = ArrayAdapter.createFromResource(this,
                R.array.dayOfWeek, android.R.layout.simple_spinner_item);
        DofWAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeek.setAdapter(DofWAdapter);
    }

    private void setListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddLessonActivity.class);
                startActivity(intent);
            }
        });

        oddOrEven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    oddOrEven.setText("Числитель");
                    oddOrEvenStr = "even";
                } else {
                    oddOrEven.setText("Знаменатель");
                    oddOrEvenStr = "odd";
                }
            }
        });

        dayOfWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayOfWeek.setSelection(position);
                switch (position) {
                    case 0:
                        dayOfWeek.setSelection(0);
                        dofw = "monday";
                        break;
                    case 1:
                        dayOfWeek.setSelection(1);
                        dofw = "tuesday";
                        break;
                    case 2:
                        dayOfWeek.setSelection(2);
                        dofw = "wednesday";
                        break;
                    case 3:
                        dayOfWeek.setSelection(3);
                        dofw = "thursday";
                        break;
                    case 4:
                        dayOfWeek.setSelection(4);
                        dofw = "friday";
                        break;
                    case 5:
                        dayOfWeek.setSelection(5);
                        dofw = "saturday";
                        break;
                    case 6:
                        dayOfWeek.setSelection(6);
                        dofw = "sunday";
                        break;
                }
                refreshRef(dofw, group, oddOrEvenStr);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                dayOfWeek.setSelection(0);
                dofw = "monday";
                refreshRef(dofw, group, oddOrEvenStr);
            }
        });


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                lesson = dataSnapshot.getValue(Lesson.class);
                int order = lesson.getTime();
                lessListMonday.set(order, lesson);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                lesson = dataSnapshot.getValue(Lesson.class);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addChildEventListener(childEventListener);
        databaseReferenceMon.addChildEventListener(childEventListener);
        databaseReferenceTues.addChildEventListener(childEventListener);
        databaseReferenceWedn.addChildEventListener(childEventListener);
        databaseReferenceThurs.addChildEventListener(childEventListener);
        databaseReferenceFrid.addChildEventListener(childEventListener);
        databaseReferenceSatur.addChildEventListener(childEventListener);

    }

    private void refreshRef(String dofw, int group, String oddOrEvenStr) {
        databaseReference = firebaseDatabase.getReference("lessons")
                .child(String.valueOf(group))
                .child(dofw)
                .child(oddOrEvenStr);
    }

    private void init() {
        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("lessons").child("510").child("monday").child("odd");
        button = (Button) findViewById(R.id.button2);
        dayOfWeek = (Spinner) findViewById(R.id.groupSpinner);
        listViewMonday = (ListView) findViewById(R.id.lessonListMond);
        listViewTuesday = (ListView) findViewById(R.id.lessonListTuesday);
        listViewWednesday = (ListView) findViewById(R.id.lessonListWednesday);
        listViewThursday = (ListView) findViewById(R.id.lessonListThursday);
        listViewFriday = (ListView) findViewById(R.id.lessonListFriday);
        listViewSaturday = (ListView) findViewById(R.id.lessonListSaturday);
        oddOrEven = (Switch) findViewById(R.id.switch1);
        dayOfWeek = (Spinner) findViewById(R.id.groupSpinner);
    }

    private void mainMethods() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
