package com.github.hisaichi5518.pusheventhandler.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.hisaichi5518.pusheventhandler.AppPushEventHandler_Dispatcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AppPushEventHandler_Dispatcher(new AppPushEventHandler()).dispatch(new AppPayload());
    }
}
