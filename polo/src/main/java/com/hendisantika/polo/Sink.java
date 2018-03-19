package com.hendisantika.polo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by IntelliJ IDEA.
 * Project : polo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/03/18
 * Time: 20.42
 * To change this template use File | Settings | File Templates.
 */
public interface Sink {

    String INPUT = "poloStreamInput";

    @Input(INPUT)
    SubscribableChannel input();

}