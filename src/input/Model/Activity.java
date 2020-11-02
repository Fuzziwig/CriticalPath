package input.Model;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.Arrays;

public class Activity implements Comparable{

    private String name = "Generic Activity";
    private int level = 0;
    private int duration = 0;
    private int slack = 0;
    private int earlyStart = 0;
    private int earlyFinish = 0;
    private int LateStart = 0;
    private int LateFinish = 0;

    private ArrayList<Activity> input = new ArrayList<Activity>();
    private ArrayList<Activity> output = new ArrayList<Activity>();

    public Activity() {
    }

    public Activity(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void connect(Activity destinationActivity) {
        this.output.add(destinationActivity);
        destinationActivity.input.add(this);
    }

    public int getEarlyStart() {
        return earlyStart;
    }

    public void setEarlyStart(int earlyStart) {
        this.earlyStart = earlyStart;
    }

    public int getEarlyFinish() {
        return earlyFinish;
    }

    public void setEarlyFinish(int earlyFinish) {
        this.earlyFinish = earlyFinish;
    }

    public int getLateStart() {
        return LateStart;
    }

    public void setLateStart(int lateStart) {
        LateStart = lateStart;
    }

    public int getLateFinish() {
        return LateFinish;
    }

    public void setLateFinish(int lateFinish) {
        LateFinish = lateFinish;
    }

    public ArrayList<Activity> getInput() {
        return input;
    }

    public ArrayList<Activity> getOutput() {
        return output;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSlack() {
        return slack;
    }

    public void setSlack(int slack) {
        this.slack = slack;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", duration=" + duration +
                ", slack=" + slack +
                ", earlyStart=" + earlyStart +
                ", earlyFinish=" + earlyFinish +
                ", LateStart=" + LateStart +
                ", LateFinish=" + LateFinish +
                ", input=" + listToString(input) +
                ", output=" + listToString(output) +
                '}';
    }

    public String listToString(ArrayList<Activity> activities) {
        int size = activities.size();
        String output = "[";
        for (int i = 0; i<size; i++){
            output = output+activities.get(i).getName()+", ";
        }
        if (output.length()>2) {
            output=output.substring(0, output.length() - 2);
        }
        output = output+"]";
        return output;
    }

    @Override
    public int compareTo(Object a) {
        if (a instanceof Activity){
            return this.getLevel()-((Activity)a).getLevel();
        }
        return 0;
    }
}
