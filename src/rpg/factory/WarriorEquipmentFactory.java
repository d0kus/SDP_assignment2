package rpg.factory;

import rpg.model.armor.Armor;
import rpg.model.armor.HeavyPlateArmor;
import rpg.model.skill.ShieldBashSkill;
import rpg.model.skill.SpecialSkill;
import rpg.model.weapon.ClaymoreSword;
import rpg.model.weapon.Weapon;

public class WarriorEquipmentFactory implements HeroEquipmentFactory {
    private static final int BASE_SWORD_DAMAGE = 120;
    private static final int BASE_ARMOR_DEFENSE = 80;
    private static final String DEFAULT_WEAPON_NAME = "Crystalis";

    @Override
    public Weapon createWeapon() {
        return new ClaymoreSword(DEFAULT_WEAPON_NAME, BASE_SWORD_DAMAGE);
    }

    @Override
    public Armor createArmor() {
        return new HeavyPlateArmor(BASE_ARMOR_DEFENSE);
    }

    @Override
    public SpecialSkill createSkill() {
        return new ShieldBashSkill();
    }
}