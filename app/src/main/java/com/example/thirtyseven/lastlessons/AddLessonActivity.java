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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLessonActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button add, addSecondAud;
    EditText lesName, lesTeacher, lesNum, lesAud, groupName, lesAud2;
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
    boolean keyIsSeconAud = false;
    Spinner dayOfWeek;
    String dofw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        init();
        setListeners();
        oddOrEven.setText("Знаменатель");
        oddOrEven.setVisibility(View.GONE);
        lesAud2.setVisibility(View.GONE);
        dayOfWeek.setSelection(0);
        firebaseDatabase = FirebaseDatabase.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dayOfWeek, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayOfWeek.setAdapter(adapter);


    }

    private void setListeners() {

        addSecondAud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyIsSeconAud = true;
                lesAud2.setVisibility(View.VISIBLE);
                addSecondAud.setVisibility(View.GONE);
            }
        });

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
                if (!isChecked) {
                    oddOrEven.setVisibility(View.GONE);
                } else oddOrEven.setVisibility(View.VISIBLE);
            }
        });

        oddOrEven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) oddOrEven.setText("Числитель");
                else oddOrEven.setText("Знаменатель");
            }
        });

        dayOfWeek.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dayOfWeek.setSelection(position);
                switch (position) {
                    case 0:
                        dofw = "monday";
                        break;
                    case 1:
                        dofw = "tuesday";
                        break;
                    case 2:
                        dofw = "wednesday";
                        break;
                    case 3:
                        dofw = "thursday";
                        break;
                    case 4:
                        dofw = "friday";
                        break;
                    case 5:
                        dofw = "saturday";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkAll()) {
                    boolean isNormal = true;
                    String oddOrEvenChar;
                    String name = String.valueOf(lesName.getText());
                    String teacher = String.valueOf(lesTeacher.getText());
                    int group = Integer.parseInt(String.valueOf(groupName.getText()));
                    int time = Integer.parseInt(String.valueOf(lesNum.getText()));
                    String audience;
                    if (keyIsSeconAud && !lesAud2.getText().equals("")) {
                        audience = String.valueOf(lesAud.getText()) + "/" + String.valueOf(lesAud2.getText());
                    } else audience = String.valueOf(lesAud.getText());
                    setRef(dofw, time, group);
                    if (checkBox.isChecked()) {
                        isNormal = false;
                        if (oddOrEven.isChecked()) {
                            oddOrEvenChar = "odd";

                        } else {
                            oddOrEvenChar = "even";
                        }
                        setNotNormalRef(dofw, time, group, oddOrEvenChar);
                        lesson = new Lesson(name, teacher, time, audience, isNormal, oddOrEvenChar);
                    } else lesson = new Lesson(name, teacher, time, audience, isNormal);

                    databaseReference.setValue(lesson);
                }else
                    Toast.makeText(AddLessonActivity.this, "Один из пунктов не заполнен", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private boolean checkAll() {
        if(lesName.getText().toString().trim().length() == 0
                || lesNum.getText().toString().trim().length() == 0
                || lesTeacher.getText().toString().trim().length() == 0
                || lesAud.getText().toString().trim().length() == 0
                || groupName.getText().toString().trim().length() == 0){
            return false;
        }else return true;

    }

    private void setNotNormalRef(String dayOfWeek, int time, int group, String oddOrEvenChar) {
        String timeSTR = String.valueOf(time);
        databaseReference = firebaseDatabase.getReference("lessons").child(String.valueOf(group))
                .child(dayOfWeek).child(timeSTR).child(oddOrEvenChar);
    }

    private void init() {
        add = (Button) findViewById(R.id.addButton);
        addSecondAud = (Button) findViewById(R.id.addAudienceButton);
        lesName = (EditText) findViewById(R.id.lessonName);
        lesTeacher = (EditText) findViewById(R.id.teacherLesson);
        lesNum = (EditText) findViewById(R.id.editText3);
        lesAud = (EditText) findViewById(R.id.editText4);
        lesAud2 = (EditText) findViewById(R.id.editText);
        groupName = (EditText) findViewById(R.id.groupEditText);
        dayOfWeek = (Spinner) findViewById(R.id.dayOfWeek);
        checkBox = (CheckBox) findViewById(R.id.oddEven);
        oddOrEven = (Switch) findViewById(R.id.oddOrEven);
        dayOfWeek.setSelection(0);
    }

    private void setRef(String dayOfWeek, int time, int group) {
        String timeSTR = String.valueOf(time);
        databaseReference = firebaseDatabase.getReference("lessons").child(String.valueOf(group))
                .child(dayOfWeek).child(timeSTR);
    }
}
