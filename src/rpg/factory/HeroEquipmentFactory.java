package rpg.factory;

import rpg.model.weapon.Weapon;
import rpg.model.armor.Armor;
import rpg.model.skill.SpecialSkill;

public interface HeroEquipmentFactory {
    Weapon createWeapon();
    Armor createArmor();
    SpecialSkill createSkill();
}
