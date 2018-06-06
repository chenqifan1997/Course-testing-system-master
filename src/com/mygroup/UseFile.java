package com.mygroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class UseFile {
	String address = "E:/git/Course-testing-system-master/data/control.txt";
	String address2 = "E:/git/Course-testing-system-master/data/data.txt";
	String address3 = "E:/git/Course-testing-system-master/data/tdata.txt";
	String address4 = "E:/git/Course-testing-system-master/data/kumu3.txt";
	public void writeFile(String b){
		try {
			File file = new File(address);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(b);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public String ReadFile(){
		String s = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(address);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
			}
		}
		return s;
	}
	
	public void writestdFile(String b){
		try {
			File file = new File(address2);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(b);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public String stdMessageRead(){
		
		String s = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(address2);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
			}
		}
		return s;
		}
	
	
	public void writeteachFile(String b){
		try {
			File file = new File(address3);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(b);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public String ReadteachFile(){
		String s = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(address3);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
			}
		}
		return s;
	}
	
	public void writekemuFile(String b){
		try {
			File file = new File(address4);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			fw.write(b);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public String ReadkemuFile(){
		String s = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			File file = new File(address4);
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			String linetext = null;
			while ((linetext = br.readLine()) != null) {
				s += linetext;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
			} catch (Exception e) {
			}
		}
		return s;
	}
}
