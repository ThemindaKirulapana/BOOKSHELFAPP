package com.example.myappamaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Bookadapter extends RecyclerView.Adapter<Bookadapter.booklist>{
    Context context;
    ArrayList<BookClass> list;

    public Bookadapter(Context context, ArrayList<BookClass> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public booklist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.itembooklist,parent,false);
        return new booklist(v);
    }

    @Override
    public void onBindViewHolder(@NonNull booklist holder, int position) {
         BookClass bookClass=list.get(position);

         holder.id.setText(bookClass.getBookId());
         holder.name.setText(bookClass.getName());
         //holder.price.setText(bookClass.getPrice());
         holder.price.setText(String.valueOf(bookClass.getPrice()));
         holder.author.setText(bookClass.getAuthor());
         holder.catogery.setText(bookClass.getCategory());
         holder.branch.setText(bookClass.getBranch());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class booklist extends RecyclerView.ViewHolder{

        TextView id ,name ,price ,author,catogery,branch;
        public booklist(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.tct1);
            name=itemView.findViewById(R.id.tct2);
            price=itemView.findViewById(R.id.tct3);
            author=itemView.findViewById(R.id.tct4);
            catogery=itemView.findViewById(R.id.tct5);
            branch=itemView.findViewById(R.id.tct6);
        }
    }
}
