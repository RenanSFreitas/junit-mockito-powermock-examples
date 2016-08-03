package examples;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import examples.TestResources;
import examples.TestableClass;

public class JUnitBasicExampleTestCase
{
    private TestResources testResources;

    private TestableClass subject;

    @Before
    public void setUp()
    {
        subject = new TestableClass();
        testResources = TestResources.prepareTestResources();
    }

    @After
    public void tearDown()
    {
        TestResources.releaseTestResources(testResources);
    }

    @Test
    public void testSum()
    {
        int result = subject.sum(2, 2);

        assertEquals(4, result);
    }

    @Test(expected = NullPointerException.class)
    public void testException()
    {
        Object[] invalidInput = { new Object(), null };

        subject.printObjects(invalidInput);
    }

    @Test(timeout = 100)
    public void testTimeoutMethod()
    {
        subject.timeoutTest();
    }
}
