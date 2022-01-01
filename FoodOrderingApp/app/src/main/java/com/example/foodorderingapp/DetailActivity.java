package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderingapp.databinding.ActivityDetailBinding;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightYellow)));
        final dbhelper helper = new dbhelper(this);

        if(getIntent().getIntExtra("type",0)== 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailimage.setImageResource(image);
            binding.pricelable.setText(String.format("%d", price));
            binding.detailfoodname.setText(name);
            binding.detaildescription.setText(description);



            binding.insertbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isinserted = helper.insertorder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );

                    if (isinserted) {
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getorderbyid(id);
            final int image = cursor.getInt(4);

            binding.detailimage.setImageResource(cursor.getInt(4));
            binding.pricelable.setText(String.format("%d", cursor.getInt(3)));
            binding.detailfoodname.setText(cursor.getString(6));
            binding.detaildescription.setText(cursor.getString(5));
            binding.namebox.setText(cursor.getString(1));
            binding.phonebox.setText(cursor.getString(2));
            binding.insertbtn.setText("Update Now");
            binding.insertbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  boolean isupdated=  helper.updateorder(
                            binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.pricelable.getText().toString()),
                            image,
                            binding.detaildescription.getText().toString(),
                            binding.detailfoodname.getText().toString(),
                            1,
                            id);
                  if(isupdated)
                      Toast.makeText(DetailActivity.this,"updated",Toast.LENGTH_SHORT).show();

                  else
                      Toast.makeText(DetailActivity.this,"failed",Toast.LENGTH_SHORT).show();
                }
            });

           // Toast.makeText(this,cursor.getString(1),Toast.LENGTH_SHORT).show();
        }





    }
}