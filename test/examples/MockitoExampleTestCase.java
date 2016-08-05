package examples;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockitoExampleTestCase
{
    @InjectMocks
    private TestableClassThatReadsFromDatabase subject;

    @Mock
    private DatabaseAccessObject dao;

    @Mock
    private TestResources resources;

    @Test
    public void testDatabaseReading()
    {
        String string = "stubbed result";
        Object resultToStub = getResultToStub(string);

        when(dao.readFromDatabase()).thenReturn(resultToStub);

        Object result = subject.readObject();

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
    
    @Test
    public void inOrderTest()
    {
        TestableClass2 subject = new TestableClass2();

        NonFinalClass mockedInstance = Mockito.mock(NonFinalClass.class);

        subject.setNonFinalClass(mockedInstance);

        when(mockedInstance.getInt1()).thenReturn(20);
        when(mockedInstance.getInt2()).thenReturn(10);
        when(mockedInstance.getInt3()).thenReturn(6);

        int result = subject.sumSomeInts(4, 2);

        InOrder inOrder = inOrder(mockedInstance);

        inOrder.verify(mockedInstance).getInt1();
        inOrder.verify(mockedInstance).getInt2();
        inOrder.verify(mockedInstance).getInt3();

        assertThat(result, equalTo(42));
    }

    @Test
    public void consecutiveStubbingTest()
    {
        TestableClass2 subject = new TestableClass2();

        NonFinalClass mockedInstance = Mockito.mock(NonFinalClass.class);

        subject.setNonFinalClass(mockedInstance);

        when(mockedInstance.getInt()).thenReturn(36, 37, 38);

        int result1 = subject.sumThenSumNonFinalClassInteger(4, 2);
        int result2 = subject.sumThenSumNonFinalClassInteger(3, 2);
        int result3 = subject.sumThenSumNonFinalClassInteger(2, 2);

        verify(mockedInstance, times(3)).getInt();

        assertThat(result1, equalTo(42));
        assertThat(result2, equalTo(42));
        assertThat(result3, equalTo(42));
    }

    @Test(expected = IllegalStateException.class)
    @SuppressWarnings("unchecked")
    public void testThatFails()
    {
        TestableClass2 subject = new TestableClass2();

        NonFinalClass mockedInstance = Mockito.mock(NonFinalClass.class);

        subject.setNonFinalClass(mockedInstance);

        when(mockedInstance.getInt()).thenThrow(IllegalStateException.class);

        subject.sumThenSumNonFinalClassInteger(4, 2);
    }

    @Test
    public void testWithAnswer()
    {
        TestableClass2 subject = new TestableClass2();

        NonFinalClass mockedInstance = Mockito.mock(NonFinalClass.class);

        subject.setNonFinalClass(mockedInstance);

        Calculator calculator = new Calculator();

        when(mockedInstance.getInt()).thenAnswer(inv -> calculator.caculate());

        int result = subject.sumThenSumNonFinalClassInteger(4, 2);

        assertThat(result, lessThan(42));
    }

    @Test
    public void testThatCallRealMethod()
    {
        TestableClass2 subject = new TestableClass2();

        NonFinalClass mockedInstance = Mockito.mock(NonFinalClass.class);

        subject.setNonFinalClass(mockedInstance);

        when(mockedInstance.getInt()).thenCallRealMethod();

        int result = subject.sumThenSumNonFinalClassInteger(4, 2);

        assertThat(result, equalTo(6));
    }

    private static class Calculator
    {
        private Random random = new Random();

        private int caculate()
        {
            return random.nextInt(36);
        }
    }

}
