package service;

import vo.Employee;

public interface Manager {
	public void getFile();//생성시
	public void saveFile();//프로그램 종료시
	public boolean insertEmployee(Employee employee);//직원등록
	public boolean deleteEmployee(String eid);//직원삭제
	public Employee findEmployee(String eid);//직원검색
	public void showAll();//등록직원 전부 보이기

}
