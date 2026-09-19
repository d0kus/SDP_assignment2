package rpg.factory;

import rpg.model.armor.Armor;
import rpg.model.armor.SilkRobeArmor;
import rpg.model.skill.MeteorSpellSkill;
import rpg.model.skill.SpecialSkill;
import rpg.model.weapon.ArcaneStaff;
import rpg.model.weapon.Weapon;

public class MageEquipmentFactory implements HeroEquipmentFactory {
    private static final int BASE_STAFF_DAMAGE = 150;
    private static final int BASE_ARMOR_DEFENSE = 40;
    private static final String DEFAULT_WEAPON_NAME = "Dagon";

    @Override
    public Weapon createWeapon() {
        return new ArcaneStaff(DEFAULT_WEAPON_NAME, BASE_STAFF_DAMAGE);
    }

    @Override
    public Armor createArmor() {
        return new SilkRobeArmor(BASE_ARMOR_DEFENSE);
    }

    @Override
    public SpecialSkill createSkill() {
        return new MeteorSpellSkill();
    }
}