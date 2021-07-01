package polymorphism;

public interface TV {
	//클래스 구성멤버
	//1. 필드
	//2. 생성자
	//3. 메소드
	
	
	//interface 구성멤버
	//1. 상수
	//2. 추상 메소드
	//3. 디폴트 메소드 = 내용이 있음
	//4. 정적 메소드
	
    public void powerOn(); // 메소드의 내용이 없기 때문에 추상 메소드
    
    public void powerOff();
    
    public void volumeUp();
    
    public void volumeDown();

}
