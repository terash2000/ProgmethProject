package item;

import application.Main;

public class Sword extends Item {
	
	public Sword(int attackDamageBonus) {
		super( attackDamageBonus , 0, 0, 0 );
	}
	
	@Override
	public void applyBonuses() {
		Main.hero.setAttackDamage(Main.hero.getAttackDamage() + attackDamageBonus );
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setAttackDamage(Main.hero.getAttackDamage() - attackDamageBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Sword";
	}
}
