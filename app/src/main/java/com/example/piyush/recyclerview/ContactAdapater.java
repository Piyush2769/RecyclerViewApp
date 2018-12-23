package com.example.piyush.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapater extends RecyclerView.Adapter<ContactAdapater.ContactViewHolder> {

    ArrayList<Contact> adapter_list=new ArrayList<>();
    MainActivity mainActivity;
    Context ctx;

    public ContactAdapater(ArrayList<Contact> adapter_list, MainActivity mainActivity, Context ctx) {
        this.adapter_list = adapter_list;
        this.mainActivity = mainActivity;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,viewGroup,false);
        ContactViewHolder contactViewHolder=new ContactViewHolder(view,mainActivity);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder viewHolder, int i) {

        viewHolder.img.setImageResource(adapter_list.get(i).getImg_id());
        viewHolder.name.setText(adapter_list.get(i).getName());
        viewHolder.email.setText(adapter_list.get(i).getEmail());

        if(!mainActivity.is_in_action_mode)
        {
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        else
        {
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            viewHolder.checkBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return adapter_list.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img;
        TextView name,email;
        CheckBox checkBox;
        MainActivity mainActivity;
        CardView cardView;

        public ContactViewHolder(@NonNull View itemView, MainActivity mainActivity) {
            super(itemView);
            img=itemView.findViewById(R.id.img_id);
            name=itemView.findViewById(R.id.name);
            email=itemView.findViewById(R.id.email);
            checkBox=itemView.findViewById(R.id.check_list_item);
            cardView=itemView.findViewById(R.id.cardView);
            this.mainActivity=mainActivity;
            cardView.setOnLongClickListener(mainActivity);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            mainActivity.prepareSelection(view,getAdapterPosition());

        }
    }

    public void updateAdapter(ArrayList<Contact> list)
    {
        for(Contact contact: list)
        {
            adapter_list.remove(contact);
        }
        notifyDataSetChanged();
    }
}
