package examples;

public final class SomeUtils
{
    private SomeUtils()
    {
        throw new AssertionError("Utility classes shouldn't be instantiated");
    }

    public static String generatePublicKey(int a, int b)
    {
        return "String" + a + b;
    }
}
