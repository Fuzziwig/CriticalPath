package input.Model;

import java.util.ArrayList;
import java.util.Collections;

public class Project {

    private ArrayList<Activity> activities = new ArrayList<Activity>();
    private ArrayList<Path> pathList = new ArrayList<Path>();

    public Project(){

        Activity a = new Activity("A", 5);
        Activity b = new Activity("B", 2);
        a.connect(b);
        Activity c = new Activity("C", 3);
        b.connect(c);
        Activity f = new Activity("F", 5);
        c.connect(f);
        Activity d = new Activity("D", 7);
        a.connect(d);
        Activity e = new Activity("E", 2);
        d.connect(e);
        e.connect(f);

        activities.add(a);
        activities.add(b);
        activities.add(c);
        activities.add(d);
        activities.add(e);
        activities.add(f);

        calcValues();
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public Activity get(int index) {
        return activities.get(index);
    }

    public void add(Activity activity) {
        this.activities.add(activity);
    }

    private void calcEarlyValues(Activity a) {
        //check if we are at start
        if (a.getInput().isEmpty()){
            a.setEarlyStart(0);
            a.setEarlyFinish(a.getDuration());
        }
        //find highest prior value for when this activity a can be started
        int maxEarly = 0;
        for (Activity priorActivity : a.getInput()) {
            if (priorActivity.getEarlyFinish()>maxEarly){
                maxEarly = priorActivity.getEarlyFinish();
            }
        }
        a.setEarlyStart(maxEarly);
        a.setEarlyFinish(maxEarly+a.getDuration());
    }

    private void calcLateValues(Activity a){
        int actlowindex = 0;
        //check if we are at stop
        if (a.getOutput().isEmpty()){
            a.setLateFinish(a.getEarlyFinish());
            a.setLateStart(a.getEarlyStart());
        }
        else if (a.getInput().isEmpty()){
            a.setLateFinish(a.getEarlyFinish());
            a.setLateStart(a.getEarlyStart());
        }
        else {
            int size = a.getOutput().size();
            for (int i = 0; i<size; i++){
                if (a.getOutput().get(i).getLateStart()<a.getOutput().get(actlowindex).getLateStart()){
                    actlowindex=i;
                }
            }
            a.setLateFinish(a.getOutput().get(actlowindex).getLateStart());
            a.setLateStart(a.getLateFinish() - a.getDuration());
            a.setSlack(a.getLateFinish() - a.getEarlyFinish());
        }
    }

    public void calcValues(){
        //setting levels
        setLevels(findStart());
        //sort by levels
        Collections.sort(activities);
        int size = activities.size();
        //calc early values forward
        for (int i = 0; i<size; i++){
            calcEarlyValues(activities.get(i));
        }
        //calc late values backward
        for (int i = size - 1; i >= 0; i--){
            calcLateValues(activities.get(i));
        }
    }

    public void findCriticalPath(){
        Collections.sort(activities);
        pathList.clear();
        findPath(activities.get(0), new Path());
        Collections.sort(pathList);
        System.out.println("Critical path is : "+pathList.get(0).getPath()+ " with a duration of : "+pathList.get(0).getDuration());
    }

    private void findPath(Activity a, Path p){
        p.setPath(p.getPath()+ a.getName());
        p.setDuration(p.getDuration()+a.getDuration());
        if (a.getOutput().isEmpty()){
            this.pathList.add(p);
            return;
        }
        else {
            for (Activity subact : a.getOutput()){
                findPath(subact, new Path(p.getDuration(), p.getPath()));
            }
        }
    }

    private Activity findStart(){
        Activity someact = new Activity();
        for (Activity act : this.activities){
            if (act.getInput().isEmpty() && !act.getOutput().isEmpty()) {
                return act;
            }
        }
        return someact;
    }

    private void setLevels(Activity currentActivity){
        for (Activity act : currentActivity.getOutput()){
            act.setLevel(currentActivity.getLevel()+1);
            setLevels(act);
            }
        }
}
