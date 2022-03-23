package in.co.iman.prometheusdeepdrive.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannelRabbitBinding {

    String NAME = "OutputChannelRabbit";

    @Output(NAME)
    MessageChannel channel();
}
