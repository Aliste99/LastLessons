package com.example.thirtyseven.lastlessons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLessonActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference refMonday510;
    Button add;
    EditText lesName, lesTeacher, lesNum, lesAud;
    Lesson lesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);

        firebaseDatabase = FirebaseDatabase.getInstance();
        refMonday510 = firebaseDatabase.getReference("lessons").child("firstCourse").child("510").child("monday");

        add = (Button) findViewById(R.id.addButton);
        lesName = (EditText) findViewById(R.id.lessonName);
        lesTeacher = (EditText) findViewById(R.id.teacherLesson);
        lesNum = (EditText) findViewById(R.id.editText3);
        lesAud = (EditText) findViewById(R.id.editText4);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lesson = new Lesson();
                String name = String.valueOf(lesName.getText());
                String teacher = String.valueOf(lesTeacher.getText());
                int time = Integer.parseInt(String.valueOf(lesNum.getText()));
                int audience = Integer.parseInt(String.valueOf(lesAud.getText()));
                lesson.setLessonName(name);
                lesson.setTeacher(teacher);
                lesson.setTime(time);
                lesson.setAudience(audience);

                refMonday510.setValue(lesson);

            }
        });

    }
}
