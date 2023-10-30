/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;
import java.sql.Blob;

/**
 *
 * @author Chambs
 */
public class GeneralData {
    private String username;
    private String email;
    private String password;
    
    private String audioname;
    private Blob audiofile;
    
    private String textname;

    
    public GeneralData(){
    }
    
    public GeneralData(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }
    
    public GeneralData(String audioname, Blob audiofile){
        this.audioname = audioname;
        this.audiofile = audiofile;
    }
    
    public GeneralData(String textname){
        this.textname = textname;
    }
    
   
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAudioname() {
        return audioname;
    }
    public void setAudioname(String audioname) {
        this.audioname = audioname;
    }

    public Blob getAudiofile() {
        return audiofile;
    }
    public void setAudiofile(Blob audiofile) {
        this.audiofile = audiofile;
    }
    
    public String getTextname() {
        return textname;
    }
    public void setTextname(String textname) {
        this.textname = textname;
    }
    
    @Override
    public String toString() {
        return ">> Data: \nUser = " + getUsername() + " \nEmail = " + getEmail() + " \nSenha = " + getPassword();
    }

    
    
}
