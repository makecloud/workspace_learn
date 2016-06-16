package ch15.consoleexample;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch14.ORM.HibernateSessionFactory;
import ch15.entity.Product;
import ch15.entity.ProductDetail;

/**
 * 编写基于主键的一对一关系的控制台程序。onetoonekey是一个控制台程序。负责使用基于外键的一对一关系持久化
 * product和productDetail对象，并装载这两个对象。
 * @author yihui
 *
 */
public class OneToOneKey {
	public static void main(String[] args) {
		Session session=HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		ProductDetail productDetail=new ProductDetail("product detail");
		Product product=new Product("car");
		productDetail.setProduct(product);
		product.setProductDetail(productDetail);
		
		session.save(product);
		tx.commit();
		int id=product.getId();
		Object obj=session.get(Product.class, id);//根据指定的id装载Product对象
		//成功装载Product对象
		if(null!=obj)
		{
			product=(Product)obj;
			System.out.println("Product.id="+product.getId());
			System.out.println("Product.name="+product.getName());
			System.out.println("ProductDetail.id="+product.getProductDetail().getId());
			System.out.println("ProductDetail.detail="+product.getProductDetail().getDetail());
			
		}
		session.close();
	}
}
