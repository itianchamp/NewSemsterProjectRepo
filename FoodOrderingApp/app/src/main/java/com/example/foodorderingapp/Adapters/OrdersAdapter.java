package com.example.foodorderingapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.DetailActivity;
import com.example.foodorderingapp.Models.OrdersModel;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.dbhelper;

import java.util.ArrayList;

public class OrdersAdapter  extends RecyclerView.Adapter<OrdersAdapter.viewHolder>{

    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final OrdersModel model = list.get(position);
        holder.orderimage.setImageResource(model.getOrderimage());
        holder.solditemname.setText(model.getSolditemname());
        holder.ordernumber.setText(model.getOrdernumbers());
        holder.npricce.setText(model.getOrdersampleprice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(model.getOrdernumbers()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure to delete this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbhelper helper = new dbhelper(context);
                                if (helper.deleteorder(model.getOrdernumbers()) > 0) {
                                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                                }
                            }

                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                 return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {


        ImageView orderimage;
        TextView solditemname, ordernumber, npricce;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

           orderimage = itemView.findViewById(R.id.orderimage);
           solditemname = itemView.findViewById(R.id.orderitemname);
           ordernumber= itemView.findViewById(R.id.orderNumber);
           npricce=itemView.findViewById(R.id.ordersampleprice);

        }
    }
}
