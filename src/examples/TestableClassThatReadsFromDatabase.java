package examples;

public class TestableClassThatReadsFromDatabase
{
    private DatabaseAccessObject databaseAccessObject;

    private TestResources resources;

    public TestableClassThatReadsFromDatabase()
    {
    }

    public TestableClassThatReadsFromDatabase(DatabaseAccessObject databaseAccessObject)
    {
        this.databaseAccessObject = databaseAccessObject;
    }

    public DatabaseAccessObject getDatabaseAccessObject()
    {
        if (databaseAccessObject == null)
        {
            databaseAccessObject = new DatabaseAccessObject();
        }

        return databaseAccessObject;
    }

    public Object readObject()
    {

        return getDatabaseAccessObject().readFromDatabase();
    }

    public TestResources getResources()
    {
        return resources;
    }

}
