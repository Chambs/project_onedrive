/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.*;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.JFrame;

public class CommandsDB extends GeneralData {
    
    /// inserir usuario
    public void insertUser(Connection conn){
        String sqlInsert = "INSERT INTO USER_LOG(username, email, password) VALUES(?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = ConnFactory.getConn().prepareStatement(sqlInsert);
            stmt.setString(1, getUsername());
            stmt.setString(2, getEmail());
            stmt.setString(3, getPassword());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao incluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// excluir usuario
    public void deleteUser(Connection conn){
        String sqlDelete = "DELETE FROM USER_LOG WHERE email = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getEmail());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao excluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// validar email
    public static String emailVerify(Connection conn, String uEmail) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String check = null;
        try {
            String sqlSelect = "SELECT email FROM USER_LOG WHERE email = ?";
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uEmail);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    check = res.getString("email");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar os dados " + e.toString());
            throw new RuntimeException(e);
        }
        return check;
    }
    
    /// validar senha
    public static String passVerify(Connection conn, String uEmail){
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String check = null;
        try {
            String sqlSelect = "SELECT password FROM USER_LOG WHERE email = ?";
            stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, uEmail);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    check = res.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar os dados " + e.toString());
            throw new RuntimeException(e);
        }
        return check;
    }
    
    /// inserir audio (((certo)))
    public static void uploadAudio(Connection conn, String audioname) throws IOException {
        FileInputStream stream = null;
        try {
            JFileChooser fc = new JFileChooser();
            int res = fc.showOpenDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File arq = fc.getSelectedFile();
                //String audioname = arq.getName();
                stream = new FileInputStream(arq);
                String sqlInsert = "INSERT INTO audio_files (audioname, audiofile) VALUES (?,?)";
                PreparedStatement stmt = conn.prepareStatement(sqlInsert);
                stmt.setString(1, audioname);
                stmt.setBinaryStream(2, stream, (int) arq.length());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    /// tocar audio
    public static void playAudio(Connection conn, String audioname) {
        try {
            String sqlSelect = "SELECT audiofile FROM audio_files WHERE audioname = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, audioname);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] audioBytes = rs.getBytes("audiofile");
                InputStream stream = new ByteArrayInputStream(audioBytes);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(stream);
                AudioFormat format = audioInputStream.getFormat();
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.addLineListener(new LineListener() {
                    @Override
                    public void update(LineEvent event) {
                        if (event.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    }
                });
                clip.start();
                Thread.sleep(clip.getMicrosecondLength()/1000);
                clip.close();
            } else {
                JOptionPane.showMessageDialog(null, "Audio nao encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /// excluir audio
    public void deleteAudio(Connection conn){
        String sqlDelete = "DELETE FROM audio_files WHERE audioname = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getAudioname());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao excluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// inserir texto
    public void uploadTxt(Connection connection) {
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(new JFrame());

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fc.getSelectedFile().getAbsolutePath();
                BufferedReader reader = new BufferedReader(new FileReader(path));
                StringBuilder conteudo = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    conteudo.append(linha).append("\n");
                }
                String sqlInsert = "INSERT INTO text_files (textname, textfile) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, getTextname());
                preparedStatement.setString(2, conteudo.toString());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Arquivo de texto inserido com sucesso!");
            } catch (Exception e) {
                System.out.println("Erro ao adicionar dados " + e.toString());
                e.printStackTrace();
            }
        }
    }
    
    /// excluir texto
    public void deleteTxt(Connection conn){
        String sqlDelete = "DELETE FROM text_files WHERE textname = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sqlDelete);
            stmt.setString(1, getTextname());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.setAutoCommit(false);
                conn.rollback();
            } catch (SQLException e1) {
                System.out.println("Erro ao excluir os dados " + e1.toString());
                throw new RuntimeException(e1);
            }
        }
    }
    
    /// carregar texto
    public void loadTxt(Connection conn) {
        try {
            String sqlSelect = "SELECT textfile FROM text_files WHERE textname = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlSelect);
            stmt.setString(1, getTextname());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String conteudo = rs.getString("textfile");

                String ntemp = "openfile.txt";
                File temp = new File(ntemp);
                FileWriter writer = new FileWriter(temp);
                writer.write(conteudo);
                writer.close();

                Desktop.getDesktop().open(temp);
            } else {
                JOptionPane.showMessageDialog(null, "Arquivo nao encontrado!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
