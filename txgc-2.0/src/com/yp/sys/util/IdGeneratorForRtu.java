package com.yp.sys.util;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

import org.hibernate.HibernateException;
//import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGeneratorForRtu implements IdentifierGenerator {

	private static AtomicLong id = new AtomicLong(System.currentTimeMillis());

	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
	if (object instanceof Persistent) {
		Persistent p = (Persistent) object;
		if (p.getId() != null)
		
			return Long.parseLong(p.getId());
		
	}
	return  Long.valueOf(String.valueOf(id.incrementAndGet()).substring(3, 11))-Math.round(Math.random()*Math.random()*10000);

	}
	/**
	 * 返回一个跟id生成策略一样的long值
	 * @Description 
	 * @return Long
	 */
	public static Long getLongValue(){
		//return id.incrementAndGet();
		return  Long.valueOf(String.valueOf(id.incrementAndGet()).substring(3, 11))-Math.round(Math.random()*Math.random()*10000);
	}
	
	
	public static void main(String[] arg){
		for(int i=0;i<1;i++){
			System.out.println(Long.valueOf(String.valueOf(id.incrementAndGet()).substring(3, 11))-Math.round(Math.random()*Math.random()*10000));
		}
		
	}
	
	
}
