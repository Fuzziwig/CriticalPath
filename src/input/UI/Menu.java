package input.UI;

import input.Model.Activity;
import input.Model.Project;
import input.Model.Project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    static int rowpadding = 18;
    static String HeaderSpacer = String.join("", Collections.nCopies(rowpadding*5, "*"));
    static String SectionSpacer = "\n";
    static Input input = new Input();
    Project1 p1 = new Project1();
    private ArrayList<Activity> projectActivities = p1.getActivities();//new ArrayList<Activity>();

    public Menu() {

        boolean isRunning = true;
        while (isRunning) {
            Collections.sort(projectActivities);

            //Start menuen
            System.out.println(HeaderSpacer);
            System.out.println("*  Critical Path Project Menu");
            System.out.println(HeaderSpacer);
            System.out.println("1. Show Activities");
            System.out.println("2. Create Activity");
            System.out.println("3. Connect Activities");
            System.out.println("4. Edit Activities");
            System.out.println("5. Delete Activity");
            System.out.println("6. Show Critical Path");
            System.out.println("7. Exit");
            System.out.println(HeaderSpacer);
            System.out.println("Choose a menu item : ");

            //input
            Scanner sc = new Scanner(System.in);
            int menuChoice = sc.nextInt();
            switch(menuChoice) {
                case 1:
                    showActivities();
                    break;
                case 2:
                    createActivity();
                    break;
                case 3: connectActivies(); break;
                case 4: editActivities(); break;
                case 5: deleteActivity(); break;
                case 6: p1.findCriticalPath(); break;
                case 7: isRunning = false;
                        break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    //show all activities
    private void showActivities(){
            //print out the activities
        System.out.println("Activities :");
        int size = projectActivities.size();
        for (int i = 0; i<size; i++){
            System.out.println(i+1 + " " +projectActivities.get(i).toString());
        }
    }

    //create activities
    private void createActivity(){
        System.out.println("Creating new activity");
        Activity myactivity = new Activity();
        myactivity.setName(input.readName("Type name of activity", "Invalid input try again", 255));;
        myactivity.setDuration(input.readNumber("type in duration for activity", "Invalid input try again"));
        System.out.println("Activity created");
        this.projectActivities.add(myactivity);
    }

    private void connectActivies() {
        boolean isRunning = true;
        int size = 0;
        int activityConnect1 = 0;
        int activityConnect2 = 0;
        while (isRunning) {
            //connect with
            System.out.println("Choose an activity to connect with");
            showActivities();
            Scanner sc = new Scanner(System.in);
            int menuChoice = sc.nextInt();
            size = projectActivities.size();
            menuChoice--;
            if (menuChoice<size && menuChoice>-1) {
                activityConnect1=menuChoice;
            }
            //connect to
            System.out.println("Choose an activity to connect to");
            showActivities();
            menuChoice = sc.nextInt();
            menuChoice--;
            if (menuChoice<size && menuChoice>-1) {
                activityConnect2=menuChoice;
            }
            //lets connect the two
            projectActivities.get(activityConnect1).getOutput().add(projectActivities.get(activityConnect2));
            projectActivities.get(activityConnect2).getInput().add(projectActivities.get(activityConnect1));
            System.out.println("Activities connected");
            isRunning = false;
        }
    }

    //edit activities
    private void editActivities() {
        boolean isRunning = true;
        int size = 0;
        int activityToEdit = 0;
        while (isRunning) {
            //connect with
            System.out.println("Choose an activity to edit");
            size = projectActivities.size();
            showActivities();
            Scanner sc = new Scanner(System.in);
            System.out.println(1+size+" to Exit");
            int menuChoice = sc.nextInt();
            if (menuChoice==(size+1)) { return; }
            menuChoice--;
            if (menuChoice < size && menuChoice > -1) {
                activityToEdit = menuChoice;
                System.out.println("Choose a field to change");
                System.out.println("1. Change name");
                System.out.println("2. Change duration");
                menuChoice = sc.nextInt();
                switch (menuChoice) {
                    case 1:
                        projectActivities.get(activityToEdit).setName(input.readName("Type name of activity", "Invalid input try again", 255));
                        break;
                    case 2:
                        projectActivities.get(activityToEdit).setDuration(input.readNumber("type in duration for activity", "Invalid input try again"));
                        break;
                }
                p1.calcValues();
            } else{
                    System.out.println("invalid choice");
                }

            }

        }

    //delete activities
    private void deleteActivity(){
        //print out the activities
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Activities :");
            int size = projectActivities.size();
            for (int i = 0; i<size; i++){
                System.out.println(i+1 + " " +projectActivities.get(i).toString());
            }
            System.out.println("Choose activity to delete");
            //input
            Scanner sc = new Scanner(System.in);
            int menuChoice = sc.nextInt();
            menuChoice--;
            if (menuChoice<size && menuChoice>-1) {
                projectActivities.remove(menuChoice);
            }
            else { isRunning=false;}
        }
    }


}
