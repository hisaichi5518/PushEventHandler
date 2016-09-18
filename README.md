このようなクラスを作ってくれる君です

```java
package com.github.hisaichi5518.pusheventhandler;

import com.github.hisaichi5518.pusheventhandler.entity.EventPushPayload;
import com.github.hisaichi5518.pusheventhandler.sample.AppPayload;
import com.github.hisaichi5518.pusheventhandler.sample.AppPushEventHandler;

public class AppPushEventHandler_Dispatcher {
  private final AppPushEventHandler handler;

  public AppPushEventHandler_Dispatcher(AppPushEventHandler handler) {
    this.handler = handler;
  }

  public void dispatch(EventPushPayload payload) {
    if (payload.getEvent() == "open_url") {
      this.handler.openUrl((AppPayload) payload);
      return;
    }
    if (payload.getEvent() == "event") {
      this.handler.openUrlNoArgs();
      return;
    }
  }
}
```
