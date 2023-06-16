import java.util.Random;

public class Process implements Cloneable {
    private int position;
    private boolean isRealTime;
    private int deadLineTime;
    private int _deadLineTime;
    private int appearTime;
    private int releaseTime;
    private boolean ifExecuted;
    public Process(int position,int appearTime,boolean isRealTime, int deadLineTime){
        this.position = position;
        this.isRealTime = isRealTime;
        this.appearTime = appearTime;
        if(isRealTime){
            this.deadLineTime = deadLineTime;
            this._deadLineTime = deadLineTime;
        }
        this.releaseTime = 1;
    }
    public Process(boolean isRealTime){
        Random random = new Random();
        this.appearTime = random.nextInt(30000);
        this.position = random.nextInt(100);
        this.isRealTime = isRealTime;
        if(isRealTime){
            this.deadLineTime = random.nextInt(200);
            this._deadLineTime = deadLineTime;
        }
    }
    @Override
    public Process clone() {
        try {
            Process clone = (Process) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public int get_deadLineTime() {
        return _deadLineTime;
    }

    public void set_deadLineTime(int _deadLineTime) {
        this._deadLineTime = _deadLineTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

    public void setRealTime(boolean realTime) {
        isRealTime = realTime;
    }

    public int getDeadLineTime() {
        return deadLineTime;
    }

    public void setDeadLineTime(int deadLineTime) {
        this.deadLineTime = deadLineTime;
    }

    public int getAppearTime() {
        return appearTime;
    }

    public void setAppearTime(int appearTime) {
        this.appearTime = appearTime;
    }

    @Override
    public String toString() {
        return "Process{" +
                "position=" + position +
                ", isRealTime=" + isRealTime +
                ", deadLineTime=" + deadLineTime +
                ", appearTime=" + appearTime +
                '}';
    }
    public String toString1() {
        return "Process{" +
                "position=" + position +
                ", isRealTime=" + isRealTime +
                ", deadLineTime=" + deadLineTime +
                ", appearTime=" + appearTime +
                ", releaseTime=" + releaseTime+
                '}';
    }
    public String toString2() {
        return "Process{" +
                "position=" + position +
                ", isRealTime=" + isRealTime +
                ", deadLineTime=" + deadLineTime +
                ", appearTime=" + appearTime +
                ", releaseTime=" + releaseTime+
                ", ifExecuted=" + ifExecuted+
                '}';
    }

    public boolean isIfExecuted() {
        return ifExecuted;
    }

    public void setIfExecuted(boolean ifExecuted) {
        this.ifExecuted = ifExecuted;
    }

    public int getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(int releaseTime) {
        this.releaseTime = releaseTime;
    }

}
