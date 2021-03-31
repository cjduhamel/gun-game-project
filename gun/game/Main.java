package gun.game;
import java.util.Scanner;

import gun.entities.*;

import java.util.ArrayList;

class Main {
  public static Scanner input = new Scanner(System.in);
  public static ArrayList<Soldier> myTeam = new ArrayList<Soldier>();

  public static ArrayList<Soldier> enemyTeam = new ArrayList<Soldier>();

  public static void main(String[] args) {
    System.out.println("Hello world!");

    createTeam();

    initiateBattle();
  }


  //adds the selected soldiers to each team
  public static void createTeam(){
    System.out.println("Select the two members you would like in your squad:");
    System.out.println("0) MK-III");
    System.out.println("1) Glock 26");

    for(int i = 0; i < 2; i++){
      int userSelect = input.nextInt();
      input.nextLine();
      if(userSelect == 0){
        myTeam.add(new MkIII());
      }else if(userSelect == 1){
        myTeam.add(new Glock26());
      }
    }
    

    enemyTeam.add(new MkIII());
    enemyTeam.add(new MkIII());
  }

  //initiates and carries out the battle
  public static void initiateBattle(){
    System.out.println("Battle Beginning");
    int enemySelect = -1;
    while(true){
      //user Selection
      System.out.println("What would you like to do?");
      System.out.println("0) Attack");
      System.out.println("1) Repair Kit");

      int userSelect = input.nextInt();
      input.nextLine();

      Soldier selected = null;
      Soldier mySelected = null;

      //user actions
      //attack
      if(userSelect == 0){
    	System.out.println("Who would you like to attack with?");
    	for(int i = 0; i < myTeam.size(); i++){
    		System.out.println(i + ") " + myTeam.get(i).getName());
    	}
    	
    	int mySelect = input.nextInt();
    	input.nextLine();
    	mySelected = myTeam.get(mySelect);
    	
        System.out.println("Who would you like to fire upon?");
        for(int i = 0; i < enemyTeam.size(); i++){
        	System.out.println(i + ") " + enemyTeam.get(i).getName());
        }

        enemySelect = input.nextInt();
        input.nextLine();

        selected = enemyTeam.get(enemySelect);
  
        System.out.println("Firing!");
        int attack = mySelected.attack();
        selected.damage(attack);
        System.out.println("Enemy Now has " + selected.getCurrentHp() + "hp");
      }

      //win condition
      if(selected.getCurrentHp() < 0){
        enemyTeam.remove(enemySelect);
      }
      
      if(enemyTeam.size() == 0){
        System.out.println("You have won");
      }


      //enemy actions
      
      //pick random enemy to attack
      int attackingEnemyInt = (int)(Math.random() * enemyTeam.size());
      Soldier attackingEnemy = enemyTeam.get(attackingEnemyInt);
      
      //pick ally with most health to attack
      Soldier mostHealth = null;
      int mostCurrentHealth = 0;
      for(int i = 0; i < myTeam.size(); i++) {
    	  if(myTeam.get(i).getCurrentHp() > mostCurrentHealth) {
    		  mostHealth = myTeam.get(i);
    	  }
      }
      
      //enemy attack
      System.out.println("Enemy Firing! Incoming!");
      int enemyAttack = attackingEnemy.attack();
      mostHealth.damage(enemyAttack);
      System.out.println(mostHealth.getName() + " now has " + mostHealth.getCurrentHp() + "hp.");


      //win condition
      if(myTeam.get(0).getCurrentHp() < 0){
        System.out.println("You have Lost");
        break;
      }
    }
   
  }
}