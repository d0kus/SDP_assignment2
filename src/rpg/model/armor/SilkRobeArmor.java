package rpg.model.armor;
import rpg.exception.InvalidEquipmentException;

public class SilkRobeArmor implements Armor {
    private final int defenseRating;

    public SilkRobeArmor(int defenseRating){
        if (defenseRating <=0){
            throw new InvalidEquipmentException("Defense rating must be greater than zero");
        }
        this.defenseRating = defenseRating;
    }

    @Override
    public void absorbDamage(int incomingDamage){
        int finalDamage = Math.max(0, incomingDamage - defenseRating);
        System.out.println("Magic barrier: "+ finalDamage+ " damage taken!");
    }

    @Override
    public int getDefenseRating(){
        return defenseRating;
    }
}
