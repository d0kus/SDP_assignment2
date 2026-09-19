package rpg.client;

import rpg.factory.HeroEquipmentFactory;
import rpg.model.armor.Armor;
import rpg.model.skill.SpecialSkill;
import rpg.model.weapon.Weapon;

public class HeroLoadout {
    private final Weapon weapon;
    private final Armor armor;
    private final SpecialSkill skill;

    public HeroLoadout(HeroEquipmentFactory factory){
        if (factory == null){
            throw new IllegalArgumentException("Equipment factory cannot be null");
        }
        this.weapon = factory.createWeapon();
        this.armor = factory.createArmor();
        this.skill = factory.createSkill();
    }

    public void displayLoadout(){
        System.out.println("Equipment Status:");
        System.out.println("- Weapon: " + weapon.getName() + " (Power: " + weapon.getBaseDamage() + ")");
        System.out.println("- Armor Defense Rating: " + armor.getDefenseRating());
    }

    public void battle(int incomingEnemyDamage){
        weapon.dealDamage();
        armor.absorbDamage(incomingEnemyDamage);
        skill.castSkill();
    }
}
