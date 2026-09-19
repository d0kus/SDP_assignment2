package rpg;

import rpg.client.HeroLoadout;
import rpg.factory.HeroEquipmentFactory;
import rpg.factory.MageEquipmentFactory;
import rpg.factory.WarriorEquipmentFactory;

public class Main {
    public void main(String[] args){

        System.out.println("1. Warrior Equipment: ");
        HeroEquipmentFactory warriorFactory = new WarriorEquipmentFactory();
        HeroLoadout warrior = new HeroLoadout(warriorFactory);
        warrior.displayLoadout();
        warrior.battle(120);

        System.out.println();

        System.out.println("2. Mage Equipment: ");
        HeroEquipmentFactory mageFactory = new MageEquipmentFactory();
        HeroLoadout mage = new HeroLoadout(mageFactory);
        mage.displayLoadout();
        mage.battle(80);
    }
}
