package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorderingapp.Adapters.OrdersAdapter;
import com.example.foodorderingapp.Models.OrdersModel;
import com.example.foodorderingapp.databinding.ActivityMainBinding;
import com.example.foodorderingapp.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        dbhelper helper = new dbhelper(this);
        ArrayList<OrdersModel> list = helper.getorders();





        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderrecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderrecyclerview.setLayoutManager(layoutManager);


    }
}