package examples;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FinalClass.class, SomeUtils.class, TestableClass2.class })
public class PowerMockExampleTestCase
{
    @InjectMocks
    private TestableClass2 subject;

    @Mock
    private FinalClass finalClass;

    @Test
    public void testSum()
    {
        when(finalClass.getInt()).thenReturn(38);

        int result = subject.sumThenSumFinalClassInteger(2, 2);

        verify(finalClass).getInt();

        assertThat(result, equalTo(42));
    }

    @Test
    public void simpleTest()
    {
        TestableClass2 subject = new TestableClass2();

        FinalClass mockedInstance = PowerMockito.mock(FinalClass.class);

        subject.setFinalClass(mockedInstance);

        when(mockedInstance.getInt()).thenReturn(36, 37, 38);

        int result1 = subject.sumThenSumFinalClassInteger(4, 2);

        verify(mockedInstance).getInt();

        assertThat(result1, equalTo(42));
    }

    @Test(expected = IllegalStateException.class)
    @SuppressWarnings("unchecked")
    public void testThatFails()
    {
        TestableClass2 subject = new TestableClass2();

        FinalClass mockedInstance = PowerMockito.mock(FinalClass.class);

        subject.setFinalClass(mockedInstance);

        when(mockedInstance.getInt()).thenThrow(IllegalStateException.class);

        subject.sumThenSumFinalClassInteger(4, 2);
    }

    @Test
    public void testStaticUtilityMethod()
    {
        mockStatic(SomeUtils.class, inv -> "MockedKey" + inv.getArguments()[0] + inv.getArguments()[1]);
        String result = subject.getPublicKey(4, 2);
        assertThat(result, equalTo("MockedKey42"));
    }

    @Test
    public void testMockingNew() throws Exception
    {
        NonFinalClass mockedInstance = mock(NonFinalClass.class);

        whenNew(NonFinalClass.class).withNoArguments().thenReturn(mockedInstance);
        when(mockedInstance.getInt()).thenReturn(36);

        int result = subject.sumUsingLocalVariableInstance(4, 2);

        assertThat(result, equalTo(42));
    }
}
