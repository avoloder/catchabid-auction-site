package at.ac.ase.repository.notification;

import at.ac.ase.entities.AuctionHouseNotification;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionHouseNotificationRepository extends NotificationRepository {
    List<AuctionHouseNotification> findAllByReceiverId(Long userId);
}
