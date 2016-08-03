package examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import examples.exceptions.ExceptionWithStatusField;

public class TestableClass
{
    public int sum(int a, int b)
    {
        return a + b;
    }

    public void printObjects(Object... objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            Object object = objects[i];
            if (object == null)
            {
                throw new NullPointerException("The object in position " + i + " is null");
            }

            System.out.println(object);
        }
    }

    public void printObjects2(Object... objects)
    {

        for (int i = 0; i < objects.length; i++)
        {
            Object object = objects[i];
            if (object == null)
            {
                throw new ExceptionWithStatusField(404, "The object in position " + i + " is null");
            }

            System.out.println(object);
        }
    }

    public void timeoutTest()
    {
        try
        {
            // Thread.sleep(150);
            Thread.sleep(50);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public List<String> readLines(String fileName)
    {
        try
        {
            return Files.readAllLines(Paths.get(fileName));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
