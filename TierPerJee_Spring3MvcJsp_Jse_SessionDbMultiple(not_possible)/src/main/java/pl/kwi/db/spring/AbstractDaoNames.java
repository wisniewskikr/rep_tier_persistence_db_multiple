package pl.kwi.db.spring;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Abstract class for daos using hibernate template. It implements methods of CRUD.
 * 
 * @author Krzysztof Wisniewski
 *
 * @param <T> object implements Serializable
 */
public class AbstractDaoNames < T extends Serializable >{

		
		@Autowired
		@Qualifier("sessionFactoryNames")
		public SessionFactory sessionFactory;
		private Class< T > clazz;

			   
	   public void setClazz( final Class< T > clazzToSet){
	      this.clazz = clazzToSet;
	   }
	   
	   
	   public T findOne( final Long id ){
	      return (T)sessionFactory.getCurrentSession().get( clazz, id );
	   }
	   
	   @SuppressWarnings("unchecked")
	   public List< T > findAll(){
		   String query = "from " + clazz.getName();
		   Query hibQuery = sessionFactory.getCurrentSession().createQuery(query);
	      return hibQuery.list();
	   }
	   
	   public void create( final T entity ){
		   sessionFactory.getCurrentSession().persist( entity );
	   }
	   
	   public void update( final T entity ){
		   sessionFactory.getCurrentSession().merge( entity );
	   }
	   
	   public void delete( final T entity ){
		   sessionFactory.getCurrentSession().delete( entity );
	   }
	   public void deleteById( final Long entityId ){
	      final T entity = findOne( entityId );
	      
	      delete( entity );
	   }

}
