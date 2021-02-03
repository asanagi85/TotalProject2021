package gui;

import java.util.ArrayList;
import java.util.Scanner;

import service.EmplyeeManager;
import service.Manager;
import vo.Employee;

public class EmployeeUI implements UI {
	
	Scanner scanner;
	Manager manager;
	
	public EmployeeUI() {
		scanner = new Scanner(System.in);
		manager = new EmplyeeManager();
		
		String select = null;
		
		while(true) {
			printMainMenu();//호출
			select = scanner.nextLine();//String으로 반환
			
			switch(select) {
			case "1":
				insertEmployee();//사원등록
				break;
			case "2":
				findEmployee();//사원검색
				break;
			case "4":
				deleteEmployee();//사원삭제
				break;
			case "5":
				manager.showAll();//등록사원 전부표시
				break;
			case "9":
				System.out.println("프로그램을 종료합니다.");
				System.out.println("파일로 모든 정보를 출력합니다. 그곳에 저장됩니다.");
				manager.saveFile();
				System.exit(0);
			}//switch
		}//while
	}//constructor

	@Override
	public void insertEmployee() {
		System.out.println("===============================");
		System.out.println("=======[ kita 직원 등록 ]========");
		System.out.println("===============================");
		System.out.println("1. 사원번호 : " + (Employee.serial + 1));
		System.out.println("2. 사원이름 : ");
		String name = scanner.nextLine();
		
		System.out.println("3. 사원 급여: ");
		int salary = scanner.nextInt();
		
		ArrayList<String> license = new ArrayList<String>();
		while(true) {
			Scanner scanner1 = new Scanner(System.in);
			System.out.println(" 4. >>>>> Licence : ");
			String temp = scanner1.nextLine();
			
			if(temp.length() == 0) {
//				scanner1.close();
				break;
			}
			license.add(temp);
//			scanner1.close();5
			
			
		}//while ..scanner를 통해서 사원의 모든 정보를 다 입력 받았다.
		System.out.println("입력받은 자격증: " + license);
		Employee employee = new Employee(name, salary , license);
		
		/*
		 * employee 객체를 arraylist에 저장하기 위한
		 * 서비스 클래스의 add() 기능을 호출
		 */
		manager.insertEmployee(employee);
	}

	@Override
	public void deleteEmployee() {
		System.out.println(" > 삭제하려는 사원의 사원번호 : ");
		String eid = scanner.nextLine();
		/*
		 * 이때 사원의 삭제가 진행되기 전에 삭제 대상이 있는지의 
		 * 여부를 확인 하는 서비스 기능을 호출해준다.
		 * 
		 * 서비스 클래스의 사원을 삭제하는 기능의 인자값으로 eid를
		 * passing 해야한다.
		 */
		
		Employee e = manager.findEmployee(eid);
		if(e == null) {
			System.out.println(e.getName() + " :: 삭제하고자 하는 사람이 없습니다.");
			return;
		}
		
		System.out.println(e.getName() + "님을 삭제 하시겠습니까? (Y/N)");
		String answer = scanner.nextLine();
		if(answer.equalsIgnoreCase("y")) {
			manager.deleteEmployee(eid);
			return;
		}//if
		
		System.out.println(e.getName() + " 님의 삭제 작업을 취소합니다.");
	}

	@Override
	public void findEmployee() {
		System.out.println(" > 검색하려는 사원의 사원번호 : ");
		String eid = scanner.nextLine();
		
		Employee e = manager.findEmployee(eid);
		System.out.println(e);
		
	}

	@Override
	public void printMainMenu() {
		System.out.println("=====================================");
		System.out.println("=======[ kita 사원 관리 프로그램 ]========");
		System.out.println("=====================================");
		System.out.println(" 1. 사원 등록");
		System.out.println(" 2. 사원 검색");
		System.out.println(" 4. 퇴사 처리");
		System.out.println(" 5. 전체 출력");
		System.out.println(" 9. 종료");
		System.out.println("=====================================");
		System.out.println("** 메뉴 번호선택 : ");
	}//printMainMenu
}// class 닫고
