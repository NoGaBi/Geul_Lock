package com.example.jho63.hongstudy01;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<Person> contactList;
    private Context context;


    public ContactAdapter(Context context, ArrayList<Person> contactList) {
        this.contactList = contactList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater?
        // Class required to make View instance as layout defined in xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Person contact = contactList.get(position);/** contact data corresponding to the position **/

        if (contact != null) { // error handling

            holder.name.setText(contact.getName());
            if (contact.getProfileImg() != null)
                Glide.with(this.context).load(contact.getProfileImg()).into(holder.profileImg);
            holder.callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact.getPhoneNum()));

                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 1000);
                    }
                    context.startActivity(callIntent);
                }
            });

            holder.msgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent msgIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + contact.getPhoneNum()));
                    context.startActivity(msgIntent);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("cid", contact.getCid());
                    context.startActivity(i);
                }
            });


        }
    }
    @Override
    public int getItemCount() { return contactList.size(); }

    /** define custom viewholder class**/
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImg;
        ImageButton callBtn, msgBtn;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            profileImg = itemView.findViewById((R.id.contact_profileImage));
            name = itemView.findViewById(R.id.contact_Name);
            callBtn = itemView.findViewById(R.id.contact_callBtn);
            msgBtn = itemView.findViewById(R.id.contact_msgBtn);
        }
    }

}
