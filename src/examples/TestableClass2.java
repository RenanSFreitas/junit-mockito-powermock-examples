package examples;

public class TestableClass2
{
    private FinalClass finalClass;
    private NonFinalClass nonFinalClass;
    private String publicKey;

    public void setNonFinalClass(NonFinalClass nonFinalClass)
    {
        this.nonFinalClass = nonFinalClass;
    }

    public NonFinalClass getNonFinalClass()
    {
        return nonFinalClass;
    }

    public void setFinalClass(FinalClass finalClass)
    {
        this.finalClass = finalClass;
    }

    public FinalClass getFinalClass()
    {
        return finalClass;
    }

    public int sumThenSumFinalClassInteger(int a, int b)
    {
        return a + b + finalClass.getInt();
    }

    public int sumThenSumNonFinalClassInteger(int a, int b)
    {
        return a + b + nonFinalClass.getInt();
    }

    public String getPublicKey(int a, int b)
    {
        if (publicKey == null)
        {
            publicKey = SomeUtils.generatePublicKey(a, b);
        }
        return publicKey;
    }

    public int sumUsingLocalVariableInstance(int a, int b)
    {
        NonFinalClass localVariable = new NonFinalClass();
        return a + b + localVariable.getInt();
    }
}
