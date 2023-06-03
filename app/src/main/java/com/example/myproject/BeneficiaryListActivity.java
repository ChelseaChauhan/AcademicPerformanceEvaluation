package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.adapter.BeneficiaryRecyclerAdapter;
import com.example.myproject.model.Beneficiary;
import com.example.myproject.sql.DatabaseHelperr;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BeneficiaryListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<Beneficiary> recordModalArraylist;
    private DatabaseHelperr databaseHelper;
    private BeneficiaryRecyclerAdapter beneficiaryRecyclerAdapter;
    private RecyclerView recyclerViewBeneficiary;
    FloatingActionButton backHome;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_list);
        getSupportActionBar().setTitle("All Records");

        backHome = findViewById(R.id.idbackHome);
        // on below line adding a click listener for our floating action button.
        backHome.setOnClickListener(v -> {
            // opening a new activity for adding a course.
            Intent i = new Intent(BeneficiaryListActivity.this, FacultyDashone.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        recordModalArraylist = new ArrayList<>();
        databaseHelper = new DatabaseHelperr(BeneficiaryListActivity.this);

        // getting our course array
        // list from db handler class.
        recordModalArraylist = databaseHelper.readBeneficiary();

        // on below line passing our array lost to our adapter class.
        beneficiaryRecyclerAdapter = new BeneficiaryRecyclerAdapter(recordModalArraylist, BeneficiaryListActivity.this);
        recyclerViewBeneficiary = findViewById(R.id.recyclerViewBeneficiary);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BeneficiaryListActivity.this, RecyclerView.VERTICAL, false);
        recyclerViewBeneficiary.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerViewBeneficiary.setAdapter(beneficiaryRecyclerAdapter);


    }



    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem search = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Beneficiary> newList = new ArrayList<>();
        for(Beneficiary beneficiary : recordModalArraylist) {
            String enroll= beneficiary.getEnroll().toLowerCase();
            if(enroll.contains(newText)) {
                newList.add(beneficiary);
            }
        }
        beneficiaryRecyclerAdapter.setFilter(newList);
        return true;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    } 
}
