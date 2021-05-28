package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.DAL.ILoginDBDAO;
import ArlaScreens.DAL.LoginDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class LoginManager {
    private ILoginDBDAO ILoginDBDAO;

    public LoginManager() {
        ILoginDBDAO = new LoginDBDAO();
    }

    public User login(String userName, String password) throws SQLServerException {
        return ILoginDBDAO.login(userName, password);
    }
}
