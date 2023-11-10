package TEST;
import DAO.*;
import DTO.*;
import java.sql.Connection;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class TestImage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String iName, iAutor;
        int loop = 0;

        CommandsDB cBD = new CommandsDB();
        Connection conn = null;
        ConnFactory bd = new ConnFactory();

        while (loop == 0) {
            String op = JOptionPane.showInputDialog(null, "Escolha o que deseja fazer: "
                                                + "\n [1] - ADICIONAR"
                                                + "\n [2] - CARREGAR"
                                                + "\n [3] - DELETAR"
                                                + "\n [0] - SAIR\n");

            if(op.equals("1")){
                System.out.println(">> Upload imagem");
                conn = bd.getConn();
                iAutor = JOptionPane.showInputDialog(null, "Autor: "); //// O AUTOR SERA AQUELE QUE TIVER FEITO LOGIN
                cBD.setAutor(iAutor);
                iName = JOptionPane.showInputDialog(null, "Salvar como: ");
                cBD.setImagename(iName);
                cBD.uploadImg(conn, iName);
                JOptionPane.showMessageDialog(null, "Imagem inserida com sucesso!");

            }else if(op.equals("2")){
                System.out.println(">> Carregar imagem");
                conn = bd.getConn();
                iName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setImagename(iName);
                cBD.loadImg(conn, iName);

            }else if(op.equals("3")){
                System.out.println(">> Excluir imagem");
                conn = bd.getConn();
                iName = JOptionPane.showInputDialog(null, "Nome: ");
                cBD.setImagename(iName);
                cBD.deleteImg(conn);
                JOptionPane.showMessageDialog(null, "Imagem excluida com sucesso!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Saindo...");
                System.out.println(">> Programa encerrado");
                //System.exit(0);  // as imagens carregadas fecham quando o programa fecha
                loop = 1;
            }
        }
    }
}