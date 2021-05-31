package ArlaScreens.DAL;


import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;




public class UserDBDAOTest extends TestCase {

    @Test
   public void testGetUserByID() {
        //Arrange
        UserDBDAO udb = new UserDBDAO();
        //Act
        String realName = udb.getUserByID(21).getUsername();
        String expectedName = "Cocio";
        //Assert
        Assertions.assertEquals(realName, expectedName);
    }

    @Test
   public void testCheckIfUserExist(){
        UserDBDAO udb = new UserDBDAO();

        boolean actual = udb.checkIfUserExist("Cocio");
        boolean expectFalse = false;
        boolean expectTrue = true;

        Assertions.assertEquals(actual, expectTrue);
    }
}