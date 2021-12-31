package com.example.foodorderingapp;
import com.example.foodorderingapp.Adapters.MainAdapter;
import com.example.foodorderingapp.Models.MainModel;
import com.example.foodorderingapp.databinding.ActivityMainBinding;
//import

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list =  new ArrayList<>();
        list.add(new MainModel(R.drawable.bug,"Burger","5","ChickenBurger with extra cheese"));
        list.add(new MainModel(R.drawable.pizza,"Pizza","7","Extra Chessy Pizza For your Hunger"));
        list.add(new MainModel(R.drawable.fries,"Fries","4","Crispy Ptateo Fries"));
        list.add(new MainModel(R.drawable.chinese,"Chinese","10","Spicy Chinese Noodles"));
        list.add(new MainModel(R.drawable.shwarma,"Shawarma","8","Spicy Chicken Shawarma"));
        list.add(new MainModel(R.drawable.chiken,"Chicken Piece","9","Crispy fried Chicken"));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recycleview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleview.setLayoutManager(layoutManager);




    }
}