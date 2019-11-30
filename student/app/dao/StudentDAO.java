/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import student.app.database.Database;
import student.app.model.Student;

/**
 *
 * @author Lenovo
 */
public class StudentDAO {
    
    public int saveStudent(Student sd) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "insert into students(name,email,gender,dob) values (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, sd.getName());
        stmt.setString(2, sd.getEmail());
        stmt.setString(3, sd.getGender());
        stmt.setDate(4, sd.getDob());
        return stmt.executeUpdate();
    }
    
    public Student getStudentById(int id) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "select * from students where id = ?";
        Student sd = null;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        if(result.next()){
            String name = result.getString("name");
            String email = result.getString("email");
            String gender = result.getString("gender");
            Date dob = result.getDate("dob");
            sd = new Student(id,name,email,gender,dob);
        }
        return sd;
    }
    
    public List<Student> getStudents() throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        List<Student> list = new ArrayList<>();
        Student sd = null;
       
        String sql = "select * from students";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        while(result.next()){
            int id = result.getInt("id");
            String name = result.getString("name");
            String email = result.getString("email");
            String gender = result.getString("gender");
            Date dob = result.getDate("dob");
            sd = new Student(id,name,email,gender,dob);
            list.add(sd);
        }
        return list;
    }
    
    public int updateStudent(Student sd) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        
        int id = sd.getId();
        String name = sd.getName();
        String email = sd.getEmail();
        String gender = sd.getGender();
        Date dob = sd.getDob();
        
        String sql = "update students set name=?,email=?,gender=?,dob=? where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.setString(3, gender);
        stmt.setDate(4, dob);
        stmt.setInt(5, id);
        return stmt.executeUpdate();
    }
    
    public int deleteStudent(int id) throws SQLException{
        Connection conn = Database.getInstance().getConnection();
        String sql = "delete from students where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate();
    }

    
    
}
