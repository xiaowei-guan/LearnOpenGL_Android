package com.guanxw.android.learnopengl;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        Gson gson = new Gson();
        Lists lists = gson.fromJson(loadJSONFromAsset(getApplicationContext()), Lists.class);
        MyCustomAdapter adapter = new MyCustomAdapter(this,lists.getItems());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item = adapter.getItem(position);
                assert item != null;
                Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

    class MyCustomAdapter extends ArrayAdapter<Item> {

        private final List<Item> list;
        private final Activity context;

        public MyCustomAdapter (Activity context, List<Item> list) {
            super(context, R.layout.item, list);
            this.context = context;
            this.list = list;
        }

        static class ViewHolder {
            protected TextView title;
        }

        @SuppressLint({"ViewHolder", "InflateParams"})
        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //View view = null;
            View view = context.getLayoutInflater().inflate(R.layout.item, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.textView);

            view.setTag(viewHolder);

            ViewHolder holder = (ViewHolder) view.getTag();
            holder.title.setText(list.get(position).getTitle());

            return view;
        }
    }