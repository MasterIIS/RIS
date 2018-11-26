package singleton;

public class Database {
	private static Database instance;
	private static Database instanceLazy;

	private Database() {

	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}
	
	public static Database getInstanceLazy() {
		if (instanceLazy == null)
			synchronized (Database.class) {
				instanceLazy = new Database();
			}
		return instanceLazy;
	}
	
	public void connect() {
		System.out.println("connected!");
	}
	
	public void disconnect() {
		System.out.println("disconnected!");
	}
}
