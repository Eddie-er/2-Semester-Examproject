package ArlaScreens.BLL;

import ArlaScreens.BE.User;
import ArlaScreens.DAL.ILoginDBDAO;
import ArlaScreens.DAL.LoginDBDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class LoginManager {
    private ILoginDBDAO iLoginDBDAO;

    public LoginManager() {
        iLoginDBDAO = new LoginDBDAO();
    }

    public User login(String userName, String password) throws SQLServerException {
        return iLoginDBDAO.login(userName, password);
    }
}
