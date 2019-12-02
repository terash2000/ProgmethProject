package item;

import application.Main;

public class Armor extends Item {
	
	public Armor(int maxHpBonus) {
		super( 0, 0, maxHpBonus, 0);
	}

	@Override
	public void applyBonuses() {
		Main.hero.setMaxHp(Main.hero.getMaxHp() + maxHpBonus);
	}
	
	@Override
	public void unapplyBonuses() {
		Main.hero.setMaxHp(Main.hero.getMaxHp() - maxHpBonus );
	}
	
	@Override
	public String getTypeOfItem() {
		return "Armor";
	}

}
