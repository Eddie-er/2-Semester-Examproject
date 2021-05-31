package ArlaScreens.DAL;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ScreenSetupDBDAOTest extends TestCase {

    @Test
    public void testCheckIfScreenSetupExist() {
            //Importerer de klasser vi skal bruge
            ScreenSetupDBDAO ssDB = new ScreenSetupDBDAO();
            UserDBDAO udb = new UserDBDAO();

            //værdigen vores metode retunerer
            boolean realValue = ssDB.checkIfScreenSetupExist(udb.getUserByID(21));
            //resultatet vi forventer
            boolean expectedTrue = true;
            //forberedt for de tilfælde hvor vi regner med metoden returnerer "false"
            boolean expectedFalse = false;

            //sammenligner værdigerne
            //Assertions.assertEquals(realValue, expectedFalse);
            Assert.assertEquals(realValue, expectedTrue);
        }
    }
