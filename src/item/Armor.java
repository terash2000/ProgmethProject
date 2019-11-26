package item;

import object.Hero;

public class Armor extends Item {
	private int maxHpBonus;
	
	public Armor(int maxHpBonus) {
		super( 0, 0, maxHpBonus, 0);
	}

	@Override
	public void applyBonuses(Hero hero) {
		hero.setMaxHp(hero.getMaxHp() + maxHpBonus );
	}
	
	@Override
	public void unapplyBonuses(Hero hero) {
		hero.setMaxHp(hero.getMaxHp() - maxHpBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Armor";
	}

}
