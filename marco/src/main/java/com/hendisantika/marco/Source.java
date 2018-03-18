package com.hendisantika.marco;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by IntelliJ IDEA.
 * Project : marco
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 18/03/18
 * Time: 12.57
 * To change this template use File | Settings | File Templates.
 */
public interface Source {
    String OUTPUT = "poloStreamInput";

    @Output(OUTPUT)
    MessageChannel output();
}
