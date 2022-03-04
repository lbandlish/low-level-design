package com.sal.models.jump;

public abstract class AbstractJumpable implements Jumpable {
    private int start;
    private int end;

    AbstractJumpable (int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int getStart() {
        return start;
    }

    @Override
    public int getEnd() {
        return end;
    }
}
