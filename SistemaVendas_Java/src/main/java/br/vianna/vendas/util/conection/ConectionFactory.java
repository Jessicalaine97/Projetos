package br.vianna.vendas.util.conection;

import br.vianna.vendas.model.Categoria;
import br.vianna.vendas.model.dao.CategoriaDAO;
import br.vianna.vendas.model.dao.ClienteDAO;
import br.vianna.vendas.model.vendas.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ConectionFactory {
    //classe responsavel por conectar em um banco de dados

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        //JDBC
        //Definir o driver (tradutor)
        //try e catch so funciona no console
        Class.forName("com.mysql.cj.jdbc.Driver");

        //conectar no banco
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306", "aluno", "aluno");
    }
//psgm tab -> cria main
// sout tab -> cria print
//main nao aproveita -> tudo bem tratar
    public static void main(String[] args) {
        //ConectionFactory cf = new ConectionFactory();
        try {
            //System.out.println("Conectou "+cf.getConnection());

//            Cliente c = new Cliente("Zezim", "jkkl", "ze", "123",
//                    LocalDate.now().minusYears(15), null);
//            new ClienteDAO().inserir(c);

   //         Categoria c1 = new Categoria(0,"Alimento", true);
    //        Categoria c2 = new Categoria(1, "Bebida", false);
    //        CategoriaDAO cDao = new CategoriaDAO();
     //       cDao.inserir(c1);
     //       cDao.inserir(c2);

            Cliente c = new ClienteDAO().buscarPelaChave(1);
            System.out.println(c.getNome());

            Cliente c1 = new ClienteDAO().buscarPelaChave(50);
            if(c1==null){
                System.out.println("Usuario nao encontrado!");
            }
            ArrayList<Cliente> cli = new ClienteDAO().buscarTodos();
            for (Cliente c2:cli){
                System.out.println(c.getNome());
            }




            //c.gravar();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
