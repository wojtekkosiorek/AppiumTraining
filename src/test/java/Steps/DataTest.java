package Steps;

import Framework.ComplexPerson;
import Framework.TestHooks;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DataTest extends TestHooks {

    @Test
    public void dataTest() throws FileNotFoundException {
        Gson g = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Person person1 = g.fromJson("{\"name\": \"John\"}", Person.class);
        Person person2 = gson.fromJson("{\"name\": \"John\"}", Person.class);

        System.out.println(person1.getName());
        System.out.println(g.toJson(person1));
        System.out.println(gson.toJson(person2));

        BufferedReader br = new BufferedReader(new FileReader("./src/test/resources/data.json"));
        ComplexPerson complexPerson = g.fromJson(br, ComplexPerson.class);
        System.out.println(complexPerson.getLanguage());
        System.out.println(complexPerson.getPersonal().getAge());

    }

    public class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
