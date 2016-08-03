package examples.exceptions;

public final class ExceptionWithStatusField extends NullPointerException
{
    private static final long serialVersionUID = 514393057615649906L;

    private int status;

    public ExceptionWithStatusField(int status, String message) 
    {
        super(message);
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }
}
