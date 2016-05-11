package com.example.patrick.testbeacon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.Visit;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.gimbal.android.CommunicationManager;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> listAdapter;
    private PlaceEventListener placeEventListener;
    private PlaceManager placeManager;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);

        Gimbal.setApiKey(this.getApplication(), "1c2bcf1d-2e7d-43a9-94e0-bc1e59ede78c");

        listAdapter.add(String.format("List Test"));
        listAdapter.notifyDataSetChanged();

        placeEventListener = new PlaceEventListener() {
            @Override
            public void onVisitStart(Visit visit) {

                // This will be invoked when a place is entered
                listAdapter.add(String.format("Start Visit for %s", visit.getPlace().getName()));
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onVisitEnd(Visit visit) {
                listAdapter.add(String.format("End Visit for %s", visit.getPlace().getName()));
                listAdapter.notifyDataSetChanged();
            }

        };

        placeManager = PlaceManager.getInstance();
        placeManager.addListener(placeEventListener);
        placeManager.startMonitoring();

        CommunicationManager.getInstance().startReceivingCommunications();


    }

}

