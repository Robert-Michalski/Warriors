package model;

public enum DefaultWeapons {
    SWORD(5,5,0,0,0),
    SHIELD(20,-1,2,0,0),
    GREAT_AXE(-15,5,-2,10,0),
    KATANA(-20,6,-5,50,0),
    MAGIC_WAND(30,3,0,0,3);

    DefaultWeapons(int health, int attack, int defense, int vampirism, int healPower){
        Weapon.builder()
                .health(health)
                .attack(attack)
                .defense(defense)
                .vampirism(vampirism)
                .healPower(healPower)
                .build();
    }
}
