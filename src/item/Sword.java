package item;

import object.Hero;

public class Sword extends Item {
	private int attackDamageBonus;
	
	public Sword(int attackDamageBonus) {
		super( attackDamageBonus , 0, 0, 0 );
	}
	
	@Override
	public void applyBonuses(Hero hero) {
		hero.setAttackDamage(hero.getAttackDamage() + attackDamageBonus );
	}
	
	@Override
	public void unapplyBonuses(Hero hero) {
		hero.setAttackDamage(hero.getAttackDamage() - attackDamageBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Sword";
	}
}
