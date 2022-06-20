package com.ezen.demo;

public class Test {
	
	@FunctionalInterface //Functional Interface 인지 검사해줌
	public interface Sum {
		public int add(int a, int b);
	}
	
	public static void main(String[] args) {
		// 일반 클래스
		// 익명 클래스
		// Lamnda식
		
//==================================================================================//
		// 1번째 방식
		class MyRun implements Runnable	{
			@Override
			public void run() { //스레드 오브젝트가 실행 가능하다
				System.out.println("1. run()");
			}
		}
		
		MyRun mr = new MyRun();
		Thread t1 = new Thread(mr);
		t1.start();
		
		//위 코드가 결국 이렇게 줄어듬 
		new Thread( () -> System.out.println("5. run()")).start();
		
//==================================================================================//		
		//2번째 방식
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("2. run()");
			}
		});
		t2.start();
		
		
//==================================================================================//
		//3번째 방식
		new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("3. run()");
			}
		}).start();

		
//==================================================================================//
		//4번째 방식
		new Thread( () -> {
			System.out.println("4. run()");
			}).start();
		
		
//==================================================================================//
		//5번째 방식
		new Thread( () -> System.out.println("5. run()")).start();
		

//==================================================================================//		
		//Functional Interface 선언하고 사용하기
		// add 메소드를 Override 한 것
		// 파라미터가 없으면 괄호도 생략 가능함
		lambda_test((a, b)->{
			return a+b;
		}); 
		
		getResult((a, b)->{
			int total = 0;
			for(int i=a; i<=b; i++) {
				total += i;
			}
			return total;
		}, 2, 5);
	}
	
//	@FunctionalInterface //Functional Interface 인지 검사해줌
//	public interface Sum {
//		public int add(int a, int b);
//	}
	
	public static void lambda_test(Sum sum) {
		int res = sum.add(3, 5);
		System.out.println("res=" + res);
	}
	
	
	
	
	@FunctionalInterface
	public interface Getsum {
		public int add(int a, int b);
	}
	
	public static void getResult(Getsum getsum, int a, int b) { // 가장 간단하게 하려면 이 메소드를 호출하는 곳에서 정의
		int res = getsum.add(a, b);
		System.out.println("res=" + res);
	}
}	