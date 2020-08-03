package com.example.myapplication18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class EventBusTest001Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test001);

        //BuildConfig.
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        EventBus.getDefault().post(new MessageEvent("Hello everyone!"));
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onEvent(MessageEvent event){
        Toast.makeText(EventBusTest001Activity.this, event.message, Toast.LENGTH_SHORT).show();
    }

    // This method will be called when a SomeOtherEvent is posted
    /*public void onEvent(SomeOtherEvent event){
        doSomethingWith(event);
    }*/
}
