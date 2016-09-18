package com.github.hisaichi5518.pusheventhandler.sample;

import com.github.hisaichi5518.pusheventhandler.annotation.OnEvent;
import com.github.hisaichi5518.pusheventhandler.annotation.PushEventHandler;

@PushEventHandler
public class AppPushEventHandler {

    @OnEvent("open_url")
    public void openUrl(AppPayload payload) {
    }

    @OnEvent("open_url_no_args")
    public void openUrlNoArgs() {
    }
}
