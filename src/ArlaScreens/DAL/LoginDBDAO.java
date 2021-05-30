package ArlaScreens.DAL;

import ArlaScreens.BE.User;
import ArlaScreens.BLL.UserManager;
import ArlaScreens.BLL.Utils.PasswordHashing;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class LoginDBDAO implements ILoginDBDAO {
    private UserManager userManager;

    public LoginDBDAO() {
        userManager = new UserManager();
    }

    /**
     * Allows the user to login with a given username and password
     * @param userName
     * @param password
     * @return
     * @throws SQLServerException
     */
    @Override
    public User login(String userName, String password) throws SQLServerException {
        User user = userManager.getUserByName(userName);

        if (user == null) {
            return null;
        }

        if (passwordMatches(user, password)) {
            return user;
        } else {
            return null;
        }
    }
}
