package net.alpha.diStudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageRenderer {

/* 1단계
 * 의존성 주입전 코드 : MessageRenderer에서 MessageProvider와 커플링 발생으로 원하는 메시지 출력시 render를 수정해야한다. 이를 해결해보자.

	public void render(){
		MessageProvider mp = new HelloMessageProvider();
//		MessageProvider mp = new HiMessageProvider();
		System.out.println(mp.getMessage());
	}

	public static void main(String[] args) {
		MessageRenderer renderer = new MessageRenderer();
		renderer.render();
	}
*/
	
/*
	//의존성 주입을 통해 Renderer은 Provider와 커플링이 생기지 않아 Renderer를 사용하는 main에서 Provider를 설정해 줄수 있다.
	//스프링 프레임웍의 설정파일을 통해 main의 중복코드를 제거할수 있다. 이를 구현해보자.
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider){
		this.messageProvider = messageProvider;
	}
	public void render(){
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {
		MessageRenderer renderer = new MessageRenderer();
		renderer.setMessageProvider(new HelloMessageProvider());
		renderer.render();
		
		renderer.setMessageProvider(new HiMessageProvider());
		renderer.render();
	}
*/
	
	private MessageProvider messageProvider;
	
	public void setMessageProvider(MessageProvider messageProvider){
		this.messageProvider = messageProvider;
	}
	public void render(){
		System.out.println(messageProvider.getMessage());
	}
	
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("di.xml");
		MessageRenderer renderer = (MessageRenderer)ac.getBean("messageRenderer");
		renderer.render();
		
	}
}
