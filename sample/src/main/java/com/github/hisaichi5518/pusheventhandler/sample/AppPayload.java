package com.github.hisaichi5518.pusheventhandler.sample;

import com.github.hisaichi5518.pusheventhandler.entity.EventPushPayload;

public class AppPayload implements EventPushPayload {
    @Override
    public String getEvent() {
        return "event";
    }
}
