package ch15.consoleexample;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch14.ORM.HibernateSessionFactory;
import ch15.entity.Product;
import ch15.entity.ProductDetail;

/**
 * ��д����������һ��һ��ϵ�Ŀ���̨����onetoonekey��һ������̨���򡣸���ʹ�û��������һ��һ��ϵ�־û�
 * product��productDetail���󣬲�װ������������
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
		Object obj=session.get(Product.class, id);//����ָ����idװ��Product����
		//�ɹ�װ��Product����
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
