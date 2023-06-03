package com.example.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudentRVadapter extends RecyclerView.Adapter<StudentRVadapter.ViewHolder> {
    // creating variables for our list, context, interface and position.
    private ArrayList<StudentModal> studentRVModalArrayList;
    private Context context;
    private StudentClickInterface studentClickInterface;
    int lastPos = -1;

    // creating a constructor.
    public StudentRVadapter(ArrayList<StudentModal> studentRVModalArrayList, Context context, StudentClickInterface studentClickInterface) {
        this.studentRVModalArrayList = studentRVModalArrayList;
        this.context = context;
        this.studentClickInterface = studentClickInterface;
    }
    @NonNull
    @Override
    public StudentRVadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.student_rv, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull StudentRVadapter.ViewHolder holder, int position) {
        // setting data to our recycler view item on below line.
        StudentModal studentRVModal = studentRVModalArrayList.get(position);
        holder.studName.setText(studentRVModal.getStudentName());
        holder.studPhone.setText(studentRVModal.getStudentPhone());
        holder.studEmail.setText(studentRVModal.getStudentEmail());
        holder.studEnroll.setText(studentRVModal.getStudentEnroll());
        Picasso.get().load(studentRVModal.getStudentImage()).into(holder.studImg);
        // adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.studImg.setOnClickListener(v -> studentClickInterface.onStudentClick(position));
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            // on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return studentRVModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // creating variable for our image view and text view on below line.
        private ImageView studImg;
        private TextView studName, studPhone, studEmail, studEnroll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing all our variables on below line.
            studImg = itemView.findViewById(R.id.idStudentImg);
            studName = itemView.findViewById(R.id.idStudentName);
            studEnroll = itemView.findViewById(R.id.idStudentEnroll);
            studPhone = itemView.findViewById(R.id.idStudentPhone);
            studEmail = itemView.findViewById(R.id.idStudentEmail);
        }
    }

    // creating a interface for on click
    public interface StudentClickInterface {
        void onStudentClick(int position);
    }
}
