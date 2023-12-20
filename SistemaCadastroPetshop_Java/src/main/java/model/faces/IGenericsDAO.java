package model.faces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericsDAO<C> {
    public void inserir(C cli)
            throws SQLException, ClassNotFoundException;
    public void alterar(C cli)
            throws SQLException, ClassNotFoundException;
    public void apagar(int key)
            throws SQLException, ClassNotFoundException;
    public C buscarPelaChave(int key)
            throws SQLException, ClassNotFoundException;
    public ArrayList<C> buscarTodos()
            throws SQLException, ClassNotFoundException;
}
