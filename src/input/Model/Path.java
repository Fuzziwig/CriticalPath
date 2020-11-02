package input.Model;

public class Path implements Comparable{

    private int Duration = 0;
    private String Path = "";

    public Path() {
    }

    public Path(int duration, String path) {
        Duration = duration;
        Path = path;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    @Override
    public int compareTo(Object a) {
        if (a instanceof Path){
            return ((Path)a).getDuration()-this.getDuration();
        }
        return 0;
    }

}


