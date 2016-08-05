package examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class MockitNoAnnotationsExampleTestCase
{
    private TestableClassThatReadsFromDatabase subject;

    @Test
    public void testDatabaseReading()
    {
        DatabaseAccessObject dao = Mockito.mock(DatabaseAccessObject.class);

        String string = "stubbed result";
        Object resultToStub = getResultToStub(string);

        when(dao.readFromDatabase()).thenReturn(resultToStub);

        subject = new TestableClassThatReadsFromDatabase(dao);

        Object result = subject.readObject();

        verify(dao, times(1)).readFromDatabase();

        assertThat(result.toString(), equalTo(string));
    }

    @Test
    public void flawedTestDatabaseReading()
    {
        DatabaseAccessObject dao = Mockito.mock(DatabaseAccessObject.class);

        String string = "stubbed result";
        Object resultToStub = getResultToStub(string);

        when(dao.readFromDatabase()).thenReturn(resultToStub);

        subject = new TestableClassThatReadsFromDatabase();

        Object result = subject.readObject();

        // Local dao mock not set into subject, so this will fail
        verify(dao, times(1)).readFromDatabase();

        assertThat(result.toString(), equalTo(string));
    }

    private Object getResultToStub(String string)
    {
        Object result = new Object()
        {
            @Override
            public String toString()
            {
                return string;
            }
        };
        return result;
    }
}
