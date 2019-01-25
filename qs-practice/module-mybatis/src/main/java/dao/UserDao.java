package dao;

import com.qs.p2p.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by scq on 2018-03-08 13:17:33
 */
public interface UserDao {
	User findUserById(Integer id);

	Integer insertUsers(List<User> users);
}
