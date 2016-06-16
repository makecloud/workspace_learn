package ch17_ssh_combine.action;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch14.ORM.HibernateSessionFactory;
import ch17_ssh_combine.entity.Book;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * struts��ʹ��hibernate����
 * @author yihui
 *
 */
public class AddBookAction extends ActionSupport implements ModelDriven<Book> {
	
	private String result;
	private Book book=new Book();
	@Override
	public Book getModel() {
		return book;
	}
	public String getResult() {
		return result;
	}
	@Override
	public String execute() throws Exception {
		Session session=HibernateSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.save(book);
		tx.commit();
		session.close();
		result="��Ӽ�¼�ɹ�";
		return SUCCESS;
	}
}
