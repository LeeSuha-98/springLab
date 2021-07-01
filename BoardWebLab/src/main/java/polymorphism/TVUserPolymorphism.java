package polymorphism;

public class TVUserPolymorphism {
	public static void main(String[] args) {

		TV tv = new SamsungTV(); // 자동형 변환, 인터페이스 = 구현 객체
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

		
		// 하나의 변수에 다른것도 넣고 이름도 통일시킬 수 있다. 유지보수가 쉬워진다.
		
		
		TV tv1 = new LgTV();
		tv1.powerOn();
		tv1.volumeUp();
		tv1.volumeDown();
		tv1.powerOff();

	}
}
