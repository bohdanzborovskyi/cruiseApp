package DAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  Parent DAO class for working with database.
 */

public interface DAOcommand <T>
{
	public void add(T t, String locale) throws SQLException;
	public void delete(String ID) throws SQLException;
	public void update(T t, String locale) throws SQLException;
	public CopyOnWriteArrayList<T> getAll(String locale) throws IOException;
	public T getByID(String id, String locale) throws IOException;
	abstract public T createEntity();
	
}
