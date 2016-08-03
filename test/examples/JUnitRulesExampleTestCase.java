package examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

import examples.TestResources;
import examples.TestableClass;
import examples.exceptions.ExceptionWithStatusField;

public class JUnitRulesExampleTestCase
{
    private static final Object[] INVALID_INPUT = { new Object(), null };

    private TestResources testResources;

    private TestableClass subject;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Rule
    public Timeout timeout = Timeout.builder().withTimeout(2, TimeUnit.SECONDS).build();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

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
    public void testCustomFileReading() throws IOException
    {
        String fileContent = "First line\nSecond line\nThird line";

        File file = tempFolder.newFile("test_file.txt");
        Files.write(Paths.get(file.getPath()), fileContent.getBytes());
        List<String> lines = subject.readLines(file.getPath());

        assertThat(lines, contains("First line", "Second line", "Third line"));
    }

    @Test
    public void testExceptionWithRule1()
    {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("The object in position 1 is null");

        subject.printObjects(INVALID_INPUT);
    }

    @Test
    public void testExceptionWithRule2()
    {
        expectedException.expect(ExceptionWithStatusField.class);
        expectedException.expectMessage("The object in position 1 is null");
        expectedException.expect(hasProperty("status", equalTo(404)));

        subject.printObjects2(INVALID_INPUT);
    }
}
