package com.example.rnduddn.mylist;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SingerAdapter adapter = new SingerAdapter();

    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);


        ListView listView = findViewById(R.id.listView);

        adapter = new SingerAdapter();
        adapter.addItem(new SingerItem("소녀시대","010-1111-1111",R.drawable.manual));
        adapter.addItem(new SingerItem("소녀시대2","010-2222-2222",R.drawable.market));
        adapter.addItem(new SingerItem("소녀시대3","010-3333-3333",R.drawable.message));
        adapter.addItem(new SingerItem("소녀시대4","010-4444-4444",R.drawable.notification));
        adapter.addItem(new SingerItem("소녀시대5","010-5555-5555",R.drawable.person));



        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SingerItem item = (SingerItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"선택 : "+item.getName(), Toast.LENGTH_LONG).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= editText.getText().toString();
                String mobile=editText2.getText().toString();

                adapter.addItem(new SingerItem(name,mobile, R.drawable.message));
                adapter.notifyDataSetChanged();
            }
        });

    }

    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItem> items = new ArrayList<SingerItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(SingerItem item){
            items.add(item);
        }


        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingerItemView view=null;
            if(convertView==null){
                view=new SingerItemView(getApplicationContext());
            } else{
                view=(SingerItemView) convertView;
            }

            SingerItem item = items.get(position);
            view.setName(item.getName());
            view.setMobile(item.getMobile());
            view.setImage(item.getResId());

            return view;
        }
    }

}
