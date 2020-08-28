package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
// In order to not be dependent on the actual state of the database
// We will clear datas for now .. This @Sql annotation works adding a new dependency into the pom.xml (spring-jdbc)
@Sql(scripts = "classpath:/clear.sql")
public class EmployeeDaoTest {


    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testSaveAndList(){
        employeeDao.saveEmployee(" Test Name   ");
        List<String> names = employeeDao.getEmployees();

        assertEquals(List.of(" Test Name   "), names);

    }


}
