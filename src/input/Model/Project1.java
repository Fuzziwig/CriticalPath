package input.Model;

import java.util.ArrayList;

public class Project1 extends Project{

    public Project1() {
        //Activities
        Activity a = new Activity("A", 1);
        Activity b = new Activity("B", 4);
        Activity c = new Activity("C", 4);
        Activity d = new Activity("D", 3);
        Activity e = new Activity("E", 3);
        Activity f = new Activity("F", 5);
        Activity g = new Activity("G", 4);
        Activity h = new Activity("H", 2);
        Activity i = new Activity("I", 2);
        //Activity connections
        a.connect(b);
        a.connect(c);
        e.connect(c);
        e.connect(d);
        g.connect(a);
        g.connect(e);
        d.connect(f);
        c.connect(h);
        f.connect(h);
        h.connect(i);
        b.connect(i);
        //adding activities to our project
        ArrayList<Activity> projectactivities = new ArrayList<Activity>();
        projectactivities.add(a);
        projectactivities.add(b);
        projectactivities.add(c);
        projectactivities.add(d);
        projectactivities.add(e);
        projectactivities.add(f);
        projectactivities.add(g);
        projectactivities.add(h);
        projectactivities.add(i);
        this.setActivities(projectactivities);
        //calculating early, late start
        calcValues();
    }
}
