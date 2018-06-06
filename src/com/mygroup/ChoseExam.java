package com.mygroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ChoseExam {
	String s = "";
    String ss = GetS();
    String Exam ;
    String DeleteExam;
    String AddExam;
    
    //String Exam1 = "exam2";
	public String GetS(){
		UseFile gs = new UseFile();
        return gs.ReadFile();
	}
	
	public String getExam() {
		if(ss.equals("1")){
			return "select * from exam";
		}else if(ss.equals("2")){
			return "select * from exam2";
		}else {
			return "select * from exam3";
		}
		//return Exam;
	}
	
	public String getDeleteExam() {
		if(ss.equals("1")){
			DeleteExam ="delete from exam where no = ";
		}else if(ss.equals("2")){
			DeleteExam = "delete from exam2 where no = ";
		}else{
			DeleteExam = "delete from exam3 where no = ";
		}
		return DeleteExam;
	}
	
	public String getAddExam() {
		if(ss.equals("1")){
			AddExam ="insert into exam(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
		}else if(ss.equals("2")){
			AddExam = "insert into exam2(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
		}else {
			AddExam = "insert into exam3(no,content,option1,option2,option3,option4,answer) values(?,?,?,?,?,?,?)";
		}
		return AddExam;
	}
	
	

}
