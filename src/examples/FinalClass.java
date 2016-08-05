package examples;

import java.util.concurrent.TimeUnit;
import java.util.stream.DoubleStream;

public final class FinalClass
{
    private boolean shouldExecute = true;

    public int getInt()
    {
        return 0;
    }

    public double calculateAverage(double... d)
    {
        if (shouldExecute)
        {
            timeConsumingMethod();
            shouldExecute = false;
        }

        return DoubleStream.of(d).average().getAsDouble();
    }

    private boolean timeConsumingMethod()
    {
        try
        {
            TimeUnit.SECONDS.sleep(120);
        }
        catch (InterruptedException e)
        {
        }

        return true;
    }
}
