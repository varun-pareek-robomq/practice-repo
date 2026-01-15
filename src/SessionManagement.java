//import java.util.HashMap;
//import java.util.LinkedHashSet;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Set;
//
////Least Recently Used
//
//class ActiveSessionTracker {
//	private final Set<Session> activeSessions = new LinkedHashSet<>();
//
//	// When user logs in
//	public void addSession(Session session, UserService userService) {
//		if (!userService.userExists(session.getUserId())) {
//			System.out.println("Invalid user, cannot create a session");
//			return;
//		}
//		activeSessions.add(session);
//	}
//
//	public void removeSession(Session session) {
//		activeSessions.remove(session);
//	}
//
//	public void printActiveSessions() {
//		for (Session s : activeSessions) {
//			System.out.println("The user " + s.getUserId() + " is active on Session " + s.getSessionId());
//		}
//	}
//}
//
//// I can have a mapping 
//
//class Session {
//	private final String sessionId;
//	private final String userId;
//	private final Long loginTime;
//
//	public Long getLoginTime() {
//		return loginTime;
//	}
//
//	public String getSessionId() {
//		return sessionId;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public Session(String sessionId, String userId) {
//		this.sessionId = sessionId;
//		this.userId = userId;
//		this.loginTime = System.currentTimeMillis();
//	}
//
//	// equals and Hashcode so that when we create our custom data structure we know
//	// that what kind of comparison we would be using
//	@Override
//	public int hashCode() {
//		return sessionId.hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj) {
//			return true;
//		}
//		if (obj == null) {
//			return false;
//		}
//		if (getClass() != obj.getClass()) {
//			return false;
//		}
//		Session other = (Session) obj;
//		return Objects.equals(sessionId, other.sessionId) && Objects.equals(userId, other.userId);
//	}
//
//}
//
//class User {
//	private final String userId;
//	private final String username;
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public User(String userId, String username) {
//		super();
//		this.userId = userId;
//		this.username = username;
//	}
//
//}
//
//class UserService {
//
//	Map<String, User> users = new HashMap<>();
//
//	public void registerUser(String userId, String username) {
//		if (users.containsKey(username)) {
//			System.out.println("User already exists with id " + userId);
//			return;
//		}
//		users.put(userId, new User(userId, username));
//	}
//
//	public User getUser(String userId) {
//		if (!users.containsKey(userId)) {
//			System.out.println("User does not exist!");
//			return null;
//		}
//		return users.get(userId);
//	}
//
//	public boolean userExists(String userId) {
//		return users.containsKey(userId);
//	}
//}
//
//public class SessionManagement {
//
//	public static void main(String[] args) {
//
//		UserService userService = new UserService();
//		ActiveSessionTracker tracker = new ActiveSessionTracker();
//
//		userService.registerUser("user123", "Varun");
//		userService.registerUser("user123", "Varun Pareek");
//		userService.registerUser("6798", "Rohit Sharma");
//		userService.registerUser("jdfn", "MS Dhoni");
//		userService.registerUser("98", "Titu Badmash");
//
//		Session s1 = new Session("1", "user123");
//		Session s2 = new Session("2", "6798");
//		Session s3 = new Session("3", "jdfn");
//		Session s4 = new Session("4", "jdfn");
//		Session s5 = new Session("5", "98");
//		Session s6 = new Session("6", "user123");
//
//		tracker.addSession(s1, userService);
//		tracker.addSession(s2, userService);
//
//		tracker.addSession(s1, userService);
//		// duplicate, ignored
//		tracker.addSession(s1, userService);
//		tracker.addSession(s3, userService);
//		tracker.addSession(s4, userService);
//		tracker.addSession(s5, userService);
//		tracker.addSession(s6, userService);
//		System.out.println("Active sessions:");
//		tracker.printActiveSessions();
//
//		tracker.removeSession(s2);
//		tracker.removeSession(s6);
//
//		System.out.println("\nAfter UserB logs out:");
//		tracker.printActiveSessions();
//
//	}
//
//}
