package rpg.model.weapon;
import rpg.exception.InvalidEquipmentException;

public class ClaymoreSword implements Weapon{
    private final String name;
    private final int damage;

    public ClaymoreSword(String name, int damage){
        if (name == null || name.isBlank()){
            throw new InvalidEquipmentException("Weapon cannot be empty");
        }
        if (damage <=0 ){
            throw new InvalidEquipmentException("Weapon damage must be greater than zero");
        }
        this.name = name;
        this.damage = damage;
    }

    @Override
    public void dealDamage(){
        System.out.println("Swinging "+ name +": "+ damage +" damage dealt!" );
    }

    @Override
    public int getBaseDamage(){
        return damage;
    }

    @Override
    public String getName(){
        return name;
    }
}
