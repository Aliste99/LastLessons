package com.example.thirtyseven.lastlessons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLessonActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button add;
    EditText lesName, lesTeacher, lesNum, lesAud, groupName;
    Lesson lesson;
    private CheckBox checkBox;
    private Switch oddOrEven;
    private String course;
    final private String firstCourse = "firstCourse";
    final private String secondCourse = "secondCourse";
    final private String thirdCourse = "thirdCourse";
    final private String four = "4";
    final private String five = "5";
    final private String six = "6";
    Spinner dayOfWeek;
    String dofw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        init();
        setListeners();
        firebaseDatabase = FirebaseDatabase.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dayOfWeek, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeek.setAdapter(adapter);


    }

    private void setListeners() {
        dayOfWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    oddOrEven.setVisibility(View.GONE);
                }else oddOrEven.setVisibility(View.VISIBLE);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNormal = true;
                char oddOrEvenChar;
                String name = String.valueOf(lesName.getText());
                String teacher = String.valueOf(lesTeacher.getText());
                int group = Integer.parseInt(String.valueOf(groupName.getText()));
                int time = Integer.parseInt(String.valueOf(lesNum.getText()));
                int audience = Integer.parseInt(String.valueOf(lesAud.getText()));
                setRef(dofw, time, group);
                if(checkBox.isChecked()){
                    isNormal = false;
                    if (oddOrEven.isChecked()) oddOrEvenChar = 'o';
                    else oddOrEvenChar ='e';
                    lesson = new Lesson(name, teacher, time, audience, isNormal, oddOrEvenChar);
                }
                lesson = new Lesson(name, teacher, time, audience, isNormal);

                databaseReference.setValue(lesson);

            }
        });
    }

    private void init() {
        add = (Button) findViewById(R.id.addButton);
        lesName = (EditText) findViewById(R.id.lessonName);
        lesTeacher = (EditText) findViewById(R.id.teacherLesson);
        lesNum = (EditText) findViewById(R.id.editText3);
        lesAud = (EditText) findViewById(R.id.editText4);
        groupName = (EditText) findViewById(R.id.groupEditText);
        dayOfWeek = (Spinner) findViewById(R.id.dayOfWeek);
        checkBox = (CheckBox) findViewById(R.id.oddEven);
        oddOrEven = (Switch)findViewById(R.id.oddOrEven);
        dayOfWeek.setSelection(0);
    }

    private void setRef(String dayOfWeek, int time, int group) {

        databaseReference = firebaseDatabase.getReference("lessons").child(String.valueOf(group))
                .child(dayOfWeek).child(String.valueOf(time));
    }
}
