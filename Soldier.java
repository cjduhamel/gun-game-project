public class Soldier extends Entity{
  
  private int hp;
  private int currentHp;
  private int at;
  private int ac;

  public Soldier(String n, int hp, int at, int ac){
    super(n);
    this.hp = hp;
    this.at = at;
    this.ac = ac;
    currentHp = hp;
  }

  public int attack(){
    int att = at;

    if (Math.random() > .5){
      att = (int)(att + ((att/10.0) * Math.random()));
    }else{
      att = (int)(att - ((att/10.0) * Math.random()));
    }

    return att;
  }

  public void damage(int incoming){
    currentHp -= incoming; 
  }

  public int getCurrentHp(){
    return currentHp;
  }
}