package rpg.model.skill;

public class ShieldBashSkill implements SpecialSkill{
    @Override
    public void castSkill(){
        System.out.println("Skill: Warrior executes shield bash!, enemy stunned");
    }
}
