package com.example.thirtyseven.lastlessons;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ThirtySeven on 28.04.2017.
 */

public class CustomAdapter extends ArrayAdapter<Lesson> {
    private Context context;

    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList flat) {
        super(context, 0, flat);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.custom_adapter, parent, false);
        }
        Lesson lesson = getItem(position);

        TextView lessonName = (TextView) listViewItem.findViewById(R.id.lessonName);
        TextView teacherLesson = (TextView) listViewItem.findViewById(R.id.teacherLesson);
        TextView timeLesson = (TextView) listViewItem.findViewById(R.id.timeLesson);
        TextView audienceLesson = (TextView) listViewItem.findViewById(R.id.audienceLesson);



        lessonName.setText(String.valueOf(lesson.getLessonName()));
        teacherLesson.setText(String.valueOf(lesson.getTeacher()));
        timeLesson.setText(String.valueOf(lesson.getTime()));
        audienceLesson.setText(String.valueOf(lesson.getAudience()));

       /* address.setTextSize(23);
        address.setText(String.valueOf(lesson.getAddress()));
        area.setText(String.valueOf(lesson.getArea()));
        roomCount.setText(String.valueOf(lesson.getRoomCount()));
        description.setText(String.valueOf(lesson.getDescription()));
        price.setText(String.valueOf(lesson.getPrice()));*/



        return listViewItem;
    }
}
