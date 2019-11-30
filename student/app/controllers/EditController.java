/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student.app.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import student.app.dao.StudentDAO;
import student.app.model.Student;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class EditController implements Initializable {

    @FXML
    private ToggleGroup gender;
    
    @FXML
    private TextField editNameTf;
    @FXML
    private TextField editEmailTf;
    @FXML
    private DatePicker editDatePicker;
    @FXML
    private Button editSaveBtn;
    @FXML
    private RadioButton editMaleRadio;
    @FXML
    private RadioButton editFemaleRadio;
    
    private StudentDAO dao;
    
    private int studentId;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        editMaleRadio.setUserData("Male");
        editFemaleRadio.setUserData("Female");
        dao = new StudentDAO();
        
    }
    
     public void setStudentData(Student student) throws SQLException{
        
        this.studentId = student.getId();
        editNameTf.setText(student.getName());
        editEmailTf.setText(student.getEmail());
        String genderText = student.getGender();
       if(genderText.equals("Male")){
           editMaleRadio.setSelected(true);
           }
       else{
            editFemaleRadio.setSelected(true);
       }
//            Date dob = student.getDob();
//            LocalDate localDate = dob.toLocalDate();
            editDatePicker.setValue(student.getDob().toLocalDate());
    }

    @FXML
    private void updateStudent(ActionEvent event) {
        String name = editNameTf.getText();
        String email = editEmailTf.getText();
        String userdata = (String) gender.getSelectedToggle().getUserData();
        
        LocalDate date = editDatePicker.getValue();
        Date dob = Date.valueOf(date);
        
        Student editedStudent = new Student(studentId,name,email,userdata,dob);
        
        try {
            dao.updateStudent(editedStudent);
            Stage currentStage = (Stage)editNameTf.getScene().getWindow();
            currentStage.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }

    

