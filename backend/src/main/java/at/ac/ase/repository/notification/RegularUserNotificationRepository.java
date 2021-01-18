package at.ac.ase.repository.notification;

import at.ac.ase.entities.RegularUserNotification;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RegularUserNotificationRepository extends NotificationRepository {

    List<RegularUserNotification> findAllByReceiverId(Long userId);

}
