package examples;

public class DatabaseAccessObject
{
    public Object readFromDatabase()
    {
        return new Object()
        {
            @Override
            public String toString()
            {
                return "Object read from database";
            }
        };
    }
}
