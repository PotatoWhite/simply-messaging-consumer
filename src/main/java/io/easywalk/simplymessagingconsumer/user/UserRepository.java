package io.easywalk.simplymessagingconsumer.user;

import io.easywalk.simplymessagingconsumer.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
