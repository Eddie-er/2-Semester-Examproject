package ArlaScreens.DAL;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.Utils.PasswordHashing;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public interface ILoginDBDAO {
    User login(String userName, String password) throws SQLServerException;

    default boolean passwordMatches(User user, String password) {
        return PasswordHashing.verifyPassword(password, user.getPassword(), user.getSalt());
    }
}
