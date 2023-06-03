package com.example.myproject.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.R;
import com.example.myproject.UpdateCourseActivity;
import com.example.myproject.model.Beneficiary;

import java.util.ArrayList;

public class BeneficiaryRecyclerAdapter extends RecyclerView.Adapter<BeneficiaryRecyclerAdapter.ViewHolder>  {

    private ArrayList<Beneficiary> recordModalArrayList;
    private Context context;


    public BeneficiaryRecyclerAdapter(ArrayList<Beneficiary> recordModalArrayList, Context context) {
        this.recordModalArrayList = recordModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beneficiary_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Beneficiary modal = recordModalArrayList.get(position);
        holder.textViewEnrollment.setText(modal.getEnroll());
        holder.textViewSemester.setText(modal.getSem());
        holder.textViewEmail.setText(modal.getEmail());
        holder.textViewSubject1.setText(modal.getSub1());
        holder.textViewSubject2.setText(modal.getSub2());
        holder.textViewSubject3.setText(modal.getSub3());
        holder.textViewSubject4.setText(modal.getSub4());
        holder.textViewSubject5.setText(modal.getSub5());
        holder.textViewMarks1.setText(modal.getMar1());
        holder.textViewMarks2.setText(modal.getMar2());
        holder.textViewMarks3.setText(modal.getMar3());
        holder.textViewMarks4.setText(modal.getMar4());
        holder.textViewMarks5.setText(modal.getMar5());
        holder.textViewPract.setText(modal.getMarprac());
        holder.textViewAttd.setText(modal.getMarattd());
        holder.textViewPerc.setText(modal.getPerc());
        holder.textViewGrad.setText(modal.getGrad());

        if(Integer.parseInt(modal.getMar1())<33) {
            holder.textViewpf1.setText("F");
        }
        else
            holder.textViewpf1.setText("P");

        if(Integer.parseInt(modal.getMar2())<33) {
            holder.textViewpf2.setText("F");
        }
        else
            holder.textViewpf2.setText("P");

        if(Integer.parseInt(modal.getMar3())<33) {
            holder.textViewpf3.setText("F");
        }
        else
            holder.textViewpf3.setText("P");

        if(Integer.parseInt(modal.getMar4())<33) {
            holder.textViewpf4.setText("F");
        }
        else
            holder.textViewpf4.setText("P");

        if(Integer.parseInt(modal.getMar5())<33) {
            holder.textViewpf5.setText("F");
        }
        else
            holder.textViewpf5.setText("P");

        if(Integer.parseInt(modal.getMarprac())<16.5) {
            holder.textViewpf6.setText("F");
        }
        else
            holder.textViewpf6.setText("P");

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, UpdateCourseActivity.class);
            // below we are passing all our values.
            i.putExtra("EMAIL", modal.getEmail());
            i.putExtra("ENROLLMENT", modal.getEnroll());
            i.putExtra("SEMESTER", modal.getSem());
            i.putExtra("SUBJECT1", modal.getSub1());
            i.putExtra("SUBJECT2", modal.getSub2());
            i.putExtra("SUBJECT3", modal.getSub3());
            i.putExtra("SUBJECT4", modal.getSub4());
            i.putExtra("SUBJECT5", modal.getSub5());
            i.putExtra("MARKS1", modal.getMar1());
            i.putExtra("MARKS2", modal.getMar2());
            i.putExtra("MARKS3", modal.getMar3());
            i.putExtra("MARKS4", modal.getMar4());
            i.putExtra("MARKS5", modal.getMar5());
            i.putExtra("PRACTICAL", modal.getMarprac());
            i.putExtra("ATTENDANCE", modal.getMarattd());
            i.putExtra("PERCENT", modal.getPerc());
            i.putExtra("GRADE", modal.getGrad());

            // starting our activity.
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return recordModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        private TextView textViewEmail, textViewSemester, textViewEnrollment, textViewSubject1, textViewSubject2, textViewSubject3, textViewSubject4,
         textViewSubject5, textViewMarks1, textViewMarks2, textViewMarks3, textViewMarks4, textViewMarks5, textViewPract, textViewAttd, textViewPerc,
                textViewGrad, textViewpf1, textViewpf2,textViewpf3, textViewpf4, textViewpf5, textViewpf6;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewEnrollment = itemView.findViewById(R.id.textViewEnrollment);
            textViewSemester = itemView.findViewById(R.id.textViewSemester);
            textViewSubject1 = itemView.findViewById(R.id.textViewSubject1);
            textViewSubject2 = itemView.findViewById(R.id.textViewSubject2);
            textViewSubject3 =  itemView.findViewById(R.id.textViewSubject3);
            textViewSubject4 = itemView.findViewById(R.id.textViewSubject4);
            textViewSubject5 =  itemView.findViewById(R.id.textViewSubject5);
            textViewMarks1 = itemView.findViewById(R.id.textViewMarks1);
            textViewMarks2 =  itemView.findViewById(R.id.textViewMarks2);
            textViewMarks3 =  itemView.findViewById(R.id.textViewMarks3);
            textViewMarks4 =  itemView.findViewById(R.id.textViewMarks4);
            textViewMarks5 =  itemView.findViewById(R.id.textViewMarks5);
            textViewPract =  itemView.findViewById(R.id.textViewPract);
            textViewAttd =  itemView.findViewById(R.id.textViewAttd);
            textViewPerc =  itemView.findViewById(R.id.textViewPerc);
            textViewGrad =  itemView.findViewById(R.id.textViewGrad);

            textViewpf1 = itemView.findViewById(R.id.textViewpf1);
            textViewpf2 = itemView.findViewById(R.id.textViewpf2);
            textViewpf3 = itemView.findViewById(R.id.textViewpf3);
            textViewpf4 = itemView.findViewById(R.id.textViewpf4);
            textViewpf5 = itemView.findViewById(R.id.textViewpf5);
            textViewpf6 = itemView.findViewById(R.id.textViewpf6);

        }
    }



    public void setFilter(ArrayList<Beneficiary>newList) {
        recordModalArrayList = new ArrayList<>();
        recordModalArrayList.addAll(newList);
        notifyDataSetChanged();
    }


}
