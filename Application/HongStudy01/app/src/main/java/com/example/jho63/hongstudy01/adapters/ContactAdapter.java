package com.example.jho63.hongstudy01.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jho63.hongstudy01.R;
import com.example.jho63.hongstudy01.activities.DetailActivity;
import com.example.jho63.hongstudy01.data.Person;


import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Person> contactList;
    private Context context;


    public ContactAdapter(Context context, ArrayList<Person> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Person contact = contactList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("position", position);
                context.startActivity(i);
            }
        });

        if(contact != null) {
            holder.name.setText(contact.getName());
            holder.callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+contact.getPhoneNum()));
                    context.startActivity(callIntent);
                }
            });

            holder.msgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:"+contact.getPhoneNum()));
                    context.startActivity(msgIntent);
                }
            });
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImg;
        ImageButton callBtn, msgBtn;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImg = (ImageView)itemView.findViewById(R.id.contact_ProfileImg);
            name = (TextView)itemView.findViewById(R.id.contace_Name);
            callBtn = (ImageButton)itemView.findViewById(R.id.contact_CallBtn);
            msgBtn = (ImageButton)itemView.findViewById(R.id.contact_MsgBtn);
        }
    }

    public int getItemCount() {return contactList.size();}

}
