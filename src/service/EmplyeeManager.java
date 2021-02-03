package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vo.Employee;

public class EmplyeeManager implements Manager {
	BufferedReader br;
	BufferedWriter bw;
	FileReader fr;
	FileWriter fw;
	List<Employee> eList; //필드로 선언
	File file;
	
	public EmplyeeManager() {
		//D:\test text\employee.txt
		file = new File("D:\\test text\\employee.txt");
		if(file.exists()) {
			eList = new ArrayList<>();
			getFile();
		}
		else {
			try {
				file.createNewFile();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void getFile() {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String temp = null;
			while(true) {
				temp = br.readLine();
				if(temp == null) {
					break;
				}
				List<String> license = new ArrayList<>();
				String[] data = temp.split(",");
				Employee emp = new Employee();
				emp.setEid(data[0]);
				Employee.serial = Integer.parseInt(data[0]);//왜 있을까? txt파일에서 가져오는 단계에서는 일단 죄다 문자열이라서 원래 형태인 int로 형변환
				//문자열에서 정수로 변환해주어야, 다음에 사원등록할때, 기존 시리얼넘버에 +1을 해줄수 있으니까.
				emp.setName(data[1]);
				emp.setSalary(Integer.parseInt(data[2]));
				
				for(int i = 3; i < data.length; ++i) {
					license.add(data[i]);
				}//for
				if(license.size() != 0) {
					emp.setLicense(license);
				}
				eList.add(emp);
			}//while
			
		}//try
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			}
			catch (Exception e) {
			}
		}//finally
	}
	
	/*
	 * 프로그램 종료시 ArrayList에 저장된 모든 사원에 대한 정보를 파일에 출력한다.
	 */

	@Override
	public void saveFile() {
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			for(int i = 0; i < eList.size(); ++i) {
				Employee e = eList.get(i);
				bw.write(e.toString() + "\n");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
//입력된 Employee 객체 하나를 List에 추가하는 로직...
	@Override
	public boolean insertEmployee(Employee employee) {
		return eList.add(employee);
	}

	@Override
	public boolean deleteEmployee(String eid) {
		for(int i = 0; i < eList.size(); i++) {
			if(eList.get(i).getEid().equals(eid)) {
				eList.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Employee findEmployee(String eid) {
		for(int i = 0; i < eList.size(); i++) {
			if(eList.get(i).getEid().equals(eid)) {
				return eList.get(i);
			}
		}
		return null;
	}

	@Override
	public void showAll() {
		System.out.println(eList.size());
		for(Employee e : eList) {
			System.out.println(e.toString());
		}
		
	}

}
