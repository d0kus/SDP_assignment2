# RPG Equipment System — Abstract Factory

## 1. Architecture Overview
This project implements the **Abstract Factory** design pattern to create consistent equipment sets for RPG character classes (Warrior and Mage), preventing incompatible combinations (such as a Mage wielding heavy plate armor).

* **Abstract Products**:
  - `Weapon`: common interface with `dealDamage()`, `getBaseDamage()`, and `getName()`
  - `Armor`: common interface with `absorbDamage(int incomingDamage)` and `getDefenseRating()`
  - `SpecialSkill`: common interface with `castSkill()`
* **Families**:
  - **Warrior**:
    - Weapon: `ClaymoreSword` (Default: *"Crystalis"*, Damage: 120)
    - Armor: `HeavyPlateArmor` (Defense: 80)
    - Skill: `ShieldBashSkill` (*"Warrior executes shield bash!, enemy stunned"*)
  - **Mage**:
    - Weapon: `ArcaneStaff` (Default: *"Dagon"*, Damage: 150)
    - Armor: `SilkRobeArmor` (Defense: 40)
    - Skill: `MeteorSpellSkill` (*"Mage summons meteor! massive AoE damage"*)
* **Abstract Factory**: `HeroEquipmentFactory`
* **Concrete Factories**: `WarriorEquipmentFactory`, `MageEquipmentFactory`
* **Client**: `HeroLoadout` (operates strictly through product interfaces, no concrete class dependencies)
* **Entry Point**: `Main`

---

## 2. Clean Code Justification (Annotated Code Excerpts)

### 1. Meaningful, Intention-Revealing Names
Identifiers and parameters explicitly convey their domain intent without requiring the reader to inspect the implementation body.

```java
// File: src/rpg/client/HeroLoadout.java

// The method name 'battle' conveys initiating a combat action.
// The parameter name 'incomingEnemyDamage' explicitly communicates what the integer represents.
public void battle(int incomingEnemyDamage){
    weapon.dealDamage();
    armor.absorbDamage(incomingEnemyDamage);
    skill.castSkill();
}
```

### 2. No Magic Numbers or Strings
Hardcoded numerical stats and string literals are encapsulated in descriptive constants across factories.

```java
// File: src/rpg/factory/WarriorEquipmentFactory.java

public class WarriorEquipmentFactory implements HeroEquipmentFactory {
    // Descriptive constants replace raw numbers and literals across object creation
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
```

### 3. Validated Construction (Fail Fast)
Constructors enforce domain invariants upon initialization and throw specialized exceptions (`InvalidEquipmentException` or `IllegalArgumentException`) immediately on invalid state.

```java
// File: src/rpg/model/weapon/ClaymoreSword.java

public ClaymoreSword(String name, int damage){
    // Guard clause: rejects null or blank weapon names immediately
    if (name == null || name.isBlank()){
        throw new InvalidEquipmentException("Weapon cannot be empty");
    }
    // Guard clause: prevents non-positive damage values
    if (damage <= 0 ){
        throw new InvalidEquipmentException("Weapon damage must be greater than zero");
    }
    this.name = name;
    this.damage = damage;
}
```

```java
// File: src/rpg/model/armor/HeavyPlateArmor.java

public HeavyPlateArmor(int defenseRating){
    // Guard clause: defense rating must be positive
    if (defenseRating <= 0){
        throw new InvalidEquipmentException("Defense rating must be greater than zero");
    }
    this.defenseRating = defenseRating;
}
```

### 4. Small Methods & Single Responsibility Principle (SRP)
Methods are compact and focused on a single responsibility: data presentation is strictly decoupled from combat simulation.

```java
// File: src/rpg/client/HeroLoadout.java

// Responsibility 1: Formatting and printing equipment specifications
public void displayLoadout(){
    System.out.println("Equipment Status:");
    System.out.println("- Weapon: " + weapon.getName() + " (Power: " + weapon.getBaseDamage() + ")");
    System.out.println("- Armor Defense Rating: " + armor.getDefenseRating());
}

// Responsibility 2: Executing combat interaction
public void battle(int incomingEnemyDamage){
    weapon.dealDamage();
    armor.absorbDamage(incomingEnemyDamage);
    skill.castSkill();
}
```

### 5. Client Isolation & Dependency Inversion Principle (DIP)
The client (`HeroLoadout`) depends entirely on abstractions (`HeroEquipmentFactory`, `Weapon`, `Armor`, `SpecialSkill`), receiving its factory via constructor injection without referencing any concrete classes.

```java
// File: src/rpg/client/HeroLoadout.java

public class HeroLoadout {
    // Fields reference abstract product interfaces, never concrete classes
    private final Weapon weapon;
    private final Armor armor;
    private final SpecialSkill skill;

    // Abstract factory is injected; client has zero coupling to specific implementations
    public HeroLoadout(HeroEquipmentFactory factory){
        if (factory == null){
            throw new IllegalArgumentException("Equipment factory cannot be null");
        }
        this.weapon = factory.createWeapon();
        this.armor = factory.createArmor();
        this.skill = factory.createSkill();
    }
}
```

---

## 3. Project Structure

```text
src/rpg/
├── Main.java
├── client/
│   └── HeroLoadout.java
├── exception/
│   └── InvalidEquipmentException.java
├── factory/
│   ├── HeroEquipmentFactory.java
│   ├── MageEquipmentFactory.java
│   └── WarriorEquipmentFactory.java
└── model/
    ├── armor/
    │   ├── Armor.java
    │   ├── HeavyPlateArmor.java
    │   └── SilkRobeArmor.java
    ├── skill/
    │   ├── MeteorSpellSkill.java
    │   ├── ShieldBashSkill.java
    │   └── SpecialSkill.java
    └── weapon/
        ├── ArcaneStaff.java
        ├── ClaymoreSword.java
        └── Weapon.java
```

---

## 4. How to Compile & Run

### Windows (PowerShell)
```powershell
# Compilation
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName

# Execution
java -cp out rpg.Main
```

### Linux / macOS (Bash)
```bash
# Compilation
javac -d out $(find src -name "*.java")

# Execution
java -cp out rpg.Main
```

### Sample Output
```text
1. Warrior Equipment: 
Equipment Status:
- Weapon: Crystalis (Power: 120)
- Armor Defense Rating: 80
Swinging Crystalis: 120 damage dealt!
Heavy plate: 40 damage taken!
Skill: Warrior executes shield bash!, enemy stunned

2. Mage Equipment: 
Equipment Status:
- Weapon: Dagon (Power: 150)
- Armor Defense Rating: 40
Channeling Dagon: 150 damage dealt!
Magic barrier: 40 damage taken!
Skill: Mage summons meteor! massive AoE damage
```
