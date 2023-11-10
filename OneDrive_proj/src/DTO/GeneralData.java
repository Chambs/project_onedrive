
package DTO;

public class GeneralData {
    private String username;
    private String email;
    private String password;
    
    private String audioname;
    private String textname;
    private String imagename;
    
    private String autor;

    public GeneralData(){
    }
    
    public GeneralData(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
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

    public String getTextname() {
        return textname;
    }
    public void setTextname(String textname) {
        this.textname = textname;
    }

    public String getImagename() {
        return imagename;
    }
    public void setImagename(String imagename){
        this.imagename = imagename;
    }
    
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    
    @Override
    public String toString() {
        return ">> Data: \nUser = " + getUsername() + " \nEmail = " + getEmail() + " \nSenha = " + getPassword();
    }
}