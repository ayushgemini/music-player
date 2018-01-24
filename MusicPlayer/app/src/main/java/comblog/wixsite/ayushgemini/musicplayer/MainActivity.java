package comblog.wixsite.ayushgemini.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private List<String> list;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.list_item);
        list =new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for (int i=0; i< fields.length; i++){
            list.add(fields[i].getName());
        }


        listAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mediaPlayer !=null){
                    mediaPlayer.release();
                }
                int resId = getResources().getIdentifier(list.get(position),"raw",getPackageName());
                mediaPlayer = MediaPlayer.create(MainActivity.this,resId);
                mediaPlayer.start();
            }

        });


    }
}
