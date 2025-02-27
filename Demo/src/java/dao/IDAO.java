
package dao;

import java.util.List;

public interface IDAO<T, K> {

    public T readById(K id);

    public boolean create(T entity);

    public boolean update(T entity);

    public boolean delete(K id);

    public List<T> search(String searchTerm);
    
    public List<T> readAll();

}
