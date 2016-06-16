import java.util.Random;


public class HelloWorldService {
	public String sayHelloWorld(String name){
		return name + " Ëµ :hello world axis2°¡";
	}
	public int ggetAge(int age){
		return age + new Random().nextInt(10);
	}
}
