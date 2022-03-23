package in.co.iman.prometheusdeepdrive.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannelRabbitBinding {


    String NAME = "InputChannelRabbit";

    @Input(NAME)
    SubscribableChannel channel();

}
