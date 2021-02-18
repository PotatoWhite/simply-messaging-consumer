package io.easywalk.simplymessagingconsumer.user;

import io.easywalk.simply.eventable.kafka.SimplyEventableMessage;
import io.easywalk.simply.eventable.kafka.consumer.AbstractSimplyConsumer;
import io.easywalk.simplymessagingconsumer.user.entities.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserListener extends AbstractSimplyConsumer<User, Long> {
    private static String         TOPIC = "user";
    private final  UserRepository repository;

    protected UserListener(UserRepository repository) {
        super(TOPIC, User.class);
        this.repository = repository;
    }


    @SneakyThrows
    @Override
    public void on(SimplyEventableMessage<User> message) {
        switch (message.getEventType()) {
            case "CREATE":
            case "UPDATE":
                User user = convertToEntity(message.getPayload(), User.class);
                repository.save(user);
                break;
            case "DELETE":
                repository.deleteById(Long.valueOf(message.getKey()));
                break;
            default:
                log.error("messsage {}", message);
        }
    }
}
