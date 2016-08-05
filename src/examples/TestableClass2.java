package examples;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestableClass2
{
    private FinalClass finalClassInstance;
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
        this.finalClassInstance = finalClass;
    }

    public FinalClass getFinalClass()
    {
        return finalClassInstance;
    }

    public int sumSomeInts(int a, int b)
    {
        return a + b + nonFinalClass.getInt1() + nonFinalClass.getInt2() + nonFinalClass.getInt3();
    }

    public int sumThenSumFinalClassInteger(int a, int b)
    {
        return a + b + finalClassInstance.getInt();
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

    public String getBundleString(String bundleName, String key)
    {
        return ResourceBundle.getBundle(bundleName, Locale.ENGLISH).getString(key);
    }

    public int sumUsingLocalVariableInstance(int a, int b)
    {
        NonFinalClass localVariable = new NonFinalClass();
        return a + b + localVariable.getInt();
    }

    public double calculateAverage(double... d)
    {
        return finalClassInstance.calculateAverage(d);
    }
}
