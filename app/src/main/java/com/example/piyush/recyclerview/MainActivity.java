package com.example.piyush.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{

    boolean is_in_action_mode=false;
    TextView counter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Toolbar toolbar;

    int [] img_id={R.drawable.shirt,R.drawable.link,R.drawable.lock,R.drawable.logo,
            R.drawable.mic,R.drawable.movie,
            R.drawable.notepad,R.drawable.profile,R.drawable.slide,R.drawable.todo};

    ArrayList<Contact> Contacts=new ArrayList<>();
    ArrayList<Contact> selection_list=new ArrayList<>();
    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        counter=findViewById(R.id.counter_text);
        counter.setVisibility(View.GONE);

        String [] Name,Email;
        Name=getResources().getStringArray(R.array.name);
        Email=getResources().getStringArray(R.array.email);
        int i=0;
        for (String NAME: Name)
        {
            Contact contact=new Contact(img_id[i],NAME,Email[i]);
            Contacts.add(contact);
            i++;
        }
        adapter=new ContactAdapater(Contacts,MainActivity.this,this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View view) {

        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counter.setVisibility(View.VISIBLE);
        is_in_action_mode=true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    public void prepareSelection(View view,int pos)
    {
        if(((CheckBox)view).isChecked())
        {
            selection_list.add(Contacts.get(pos));
            count=count+1;
            updateCounter(count);
        }
        else
        {
            selection_list.remove(Contacts.get(pos));
            count=count-1;
            updateCounter(count);
        }

    }

    public void updateCounter(int count)
    {
        if(count==0)
            counter.setText("0 Items Selected");
        else
            counter.setText(count+"items selected");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_delete)
        {
            ContactAdapater contactAdapater=(ContactAdapater)adapter;
            contactAdapater.updateAdapter(selection_list);
            clearActionMode();
        }
        else if(item.getItemId()==android.R.id.home)
        {
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionMode()
    {
        is_in_action_mode=false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter.setVisibility(View.GONE);
        counter.setText("0 item selected");
        count=0;
        selection_list.clear();

    }

    @Override
    public void onBackPressed()
    {
        if(is_in_action_mode)
        {
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        else {
            super.onBackPressed();
        }
    }
}
