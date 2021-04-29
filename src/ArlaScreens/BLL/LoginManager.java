package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.DAL.LoginDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class LoginManager {
    private LoginDBDAO loginDBDAO;

    public LoginManager() {
        loginDBDAO = new LoginDBDAO();
    }

    public User login(String userName, String password) throws SQLServerException {
        return loginDBDAO.login(userName, password);
    }
}
