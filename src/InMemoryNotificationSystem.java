import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Notification implements Comparable<Notification> {
	enum NotificationStatus {
		READ, UNREAD
	}

	enum NotificationPriority {
		LOW, MEDIUM, HIGH
	}

	private final Long notificationId;
	private final Long userId;
	private final String message;
	private final Long timeStamp;
	private NotificationStatus status;
	private final NotificationPriority notificationPriority;

	public Notification(Long notificationId, Long userId, String message, NotificationPriority priority) {
		this.notificationId = notificationId;
		this.userId = userId;
		this.message = message;
		this.timeStamp = System.currentTimeMillis();
		this.status = NotificationStatus.UNREAD;
		this.notificationPriority = priority;
	}

	public NotificationStatus getStatus() {
		return status;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void markAsRead() {
		this.status = NotificationStatus.READ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(notificationId, timeStamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Notification other = (Notification) obj;
		return Objects.equals(notificationId, other.notificationId) && Objects.equals(timeStamp, other.timeStamp);
	}

	@Override
	public int compareTo(Notification other) {
		return other.notificationPriority.ordinal() - this.notificationPriority.ordinal();
	}
}

class User {
	private final Long userId;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public User(Long userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}

}

class UserService {
	Map<Long, User> users = new HashMap<>();

	public void registerUser(User user) {
		if (!users.containsKey(user.getUserId())) {
			users.put(user.getUserId(), user);
		} else {
			// Raise an error
		}
	}

}

class NotificationService {

	private final Map<Long, Notification> notifications = new HashMap<>();

	private final Map<Long, List<Notification>> userNotifications = new HashMap<>();

	private final Set<Long> unreadNotificationIds = new HashSet<>(); // store IDs in set and not whole objects

	private final Queue<Notification> priorityNotifications = new PriorityQueue<>();

	private final LinkedList<Notification> recentReadNotifications = new LinkedList<>();

	public void sendNotification(Notification notification) {
		notifications.put(notification.getNotificationId(), notification);

		userNotifications.computeIfAbsent(notification.getUserId(), k -> new LinkedList<>()).add(notification);

		unreadNotificationIds.add(notification.getNotificationId());

		priorityNotifications.offer(notification);
	}

	public void markAsRead(Long notificationId) throws NotificationNotFoundException {

		Notification notification = notifications.get(notificationId);
		if (notification == null) {
			throw new NotificationNotFoundException("Notification not found for id: " + notificationId);
		}
		notification.markAsRead();

		unreadNotificationIds.remove(notificationId);

		recentReadNotifications.addFirst(notification);

		if (recentReadNotifications.size() > 10) {
			recentReadNotifications.removeLast();
		}
	}

	public List<Notification> getUnreadNotificationForUser(Long userId) {

		List<Notification> notifs = userNotifications.getOrDefault(userId, new LinkedList<>());

		List<Notification> res = new LinkedList<>();

		for (Notification n : notifs) {
			if (n.getStatus() == Notification.NotificationStatus.UNREAD) {
				res.add(n);
			}
		}
		return res;
	}

	public void cleanupOldNotifications() {
		Long now = System.currentTimeMillis();
		Iterator<Notification> iterator = notifications.values().iterator();

		while (iterator.hasNext()) {
			Notification n = iterator.next();

			if (now - n.getTimeStamp() > 15_000) {
				iterator.remove(); // removes from notifications
				removeNotification(n);
			}
		}
//		Iterator<Map.Entry<Long, Notification>> entry = notifications.entrySet().iterator();
//		while (entry.hasNext()) {
//			Map.Entry<Long, Notification> entry2 = entry.next();
//
//			if (now - entry2.getValue().getTimeStamp() > 60_000) {
//				entry.remove();
//				removeNotification(entry.next());
////				notifications.remove(entry.getKey()); // wrong way of removing as concurrentmodificationexception will
//				// arise
//			}
//		}

	}

	private void removeNotification(Notification n) {
		// TODO Auto-generated method stub
		notifications.remove(n.getNotificationId());

		List<Notification> userList = userNotifications.get(n.getUserId());
		if (userList != null) {
			userList.remove(n);
		}
		unreadNotificationIds.remove(n.getNotificationId());
		priorityNotifications.remove(n);

	}
}

public class InMemoryNotificationSystem {

	public static void main(String[] args) throws InterruptedException {
		UserService userService = new UserService();
		NotificationService notificationService = new NotificationService();

		userService.registerUser(new User(1l, "Varun"));
		userService.registerUser(new User(2l, "Viral"));
		userService.registerUser(new User(3l, "Vaibhav"));
		userService.registerUser(new User(4l, "Virat"));

		Notification oldNotif = new Notification(101L, 1L, "Old message", Notification.NotificationPriority.HIGH// 2 min
																												// old
		);
		Thread.sleep(16_000);

		Notification newNotif = new Notification(102L, 1L, "New message", Notification.NotificationPriority.LOW);

		notificationService.sendNotification(newNotif);
		notificationService.sendNotification(oldNotif);

		// Print unread notifications
		System.out.println("Unread notifications for user 1:");
		List<Notification> unread = notificationService.getUnreadNotificationForUser(1L);
		for (Notification n : unread) {
			System.out.println(n.getNotificationId() + " : " + n.getStatus());
		}

		// Mark one as read
		System.out.println("\nMarking notification 101 as READ");
		try {
			notificationService.markAsRead(103L);
		} catch (NotificationNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Some error caused:\n " + e);
		}

		// Check unread again
		System.out.println("\nUnread notifications after marking read:");
		unread = notificationService.getUnreadNotificationForUser(1L);
		for (Notification n : unread) {
			System.out.println(n.getNotificationId() + " : " + n.getStatus());
		}

		// Wait to simulate old notifications
		System.out.println("\nWaiting 6 seconds to test cleanup...");

		// Cleanup old notifications
		notificationService.cleanupOldNotifications();

		// Final check
		System.out.println("\nUnread notifications after cleanup:");
		unread = notificationService.getUnreadNotificationForUser(1L);
		System.out.println("Count: " + unread.size());

	}

}
