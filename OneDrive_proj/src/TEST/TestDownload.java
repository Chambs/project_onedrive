package TEST;
import java.sql.Connection;
import javax.swing.JOptionPane;
import DAO.*;
import DTO.*;

public class TestDownload {
    public static void main(String[] args){
        CommandsDB cBD = new CommandsDB();
        Connection conn = null;
        ConnFactory bd = new ConnFactory();
        String iName, aName, tName;
        int loop = 1;

        while(loop == 1){
            String op = JOptionPane.showInputDialog(null, ">> Download: "
                                + "\n [1] - IMAGEM"
                                + "\n [2] - AUDIO"
                                + "\n [3] - TEXTO"
                                + "\n [0] - SAIR\n");

            if(op.equals("1")){
            System.out.println(">> Baixar imagem");
            conn = bd.getConn();
            iName = JOptionPane.showInputDialog(null, "Nome: ");
            cBD.setImagename(iName);
            cBD.downloadImg(conn);

            }else if(op.equals("2")){
                System.out.println(">> Baixar audio");
                conn = bd.getConn();
                aName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setAudioname(aName);
                cBD.downloadAudio(conn);

            }else if(op.equals("3")){
                System.out.println(">> Baixar texto");
                conn = bd.getConn();
                tName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setTextname(tName);
                cBD.downloadTxt(conn);

            }else{
                JOptionPane.showMessageDialog(null, "Saindo...");
                System.out.println(">> Programa encerrado");
                //System.exit(0);
                loop = 0;
            }
        }
    }
}
