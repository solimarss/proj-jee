package br.com.solimar.persistence;

import javax.ejb.Stateless;

import br.com.solimar.domain.User;

@Stateless
public class UserDAO extends AbstractDao<User> {

	private static final long serialVersionUID = 1L;

	UserDAO() {
		super(User.class);
	}

	/*@SuppressWarnings("unchecked")
	public List<Pessoa> listAll() {
		try {
			Query query = 
					.createQuery("Select P from Pessoa P", Pessoa.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}*/

}
