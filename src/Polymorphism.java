
class Remote {
	void functions() {
		System.out.println("This is a remote");
	}
}

class TV extends Remote {
	@Override
	void functions() {
		System.out.println("This is a TV remote");
	}
}

class AC extends Remote {
	@Override
	void functions() {
		System.out.println("This is an AC remote");
	}

	void temperatureControl() {
		System.out.println("Temperature Controlled!");
	}
}

public class Polymorphism {

	public static void main(String[] args) {

		Remote tvRemote = new TV();
		Remote acRemote = new AC();
//		TV tv = (TV) new Remote(); NOT A GOOD THING
		AC ac = (AC) new Remote();
//		tv.functions();

		tvRemote.functions();
		ac.temperatureControl();
		acRemote.functions();
	}

}
