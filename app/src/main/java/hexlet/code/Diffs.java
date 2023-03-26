package hexlet.code;

public final class Diffs {
    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";
    private final String changeType;
    private Object oldValue;
    private Object newValue;

    public Diffs(String changeType, Object oldValue, Object newValue) {
        this.changeType = changeType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public Diffs(String changeType1, Object someValue) throws Exception {
        this.changeType = changeType1;
        if (changeType.equals(ADDED)) {
            this.newValue = someValue;
        } else if ((changeType.equals(DELETED)) || (changeType.equals(UNCHANGED))) {
            this.oldValue = someValue;
        } else {
            throw new Exception("wrong changeType" + changeType);
        }


    }

    public String getChangeType() {
        return changeType;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }
}
