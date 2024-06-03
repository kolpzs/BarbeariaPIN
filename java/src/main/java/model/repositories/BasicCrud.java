package model.repositories;

import java.util.List;

public interface BasicCrud {

	Object create(Object object);
	
	Object findById(Long id);
	
	Object updateById(Object object);
	
	void delete(Long id);

    List<?> findAll(); 

}
