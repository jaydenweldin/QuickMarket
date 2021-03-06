package me.mrCookieSlime.QuickMarket.shop;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerMarket extends PlayerShop {
	
	public static final ItemStack placeholder = new CustomItem(Material.CHEST, "Shop", 0);
	String market;

	public PlayerMarket(String market, Block sign, Block chest, Player p, int amount, double price, ShopType type) {
		super(sign, chest, p, amount, price, type);
		this.market = market;
	}
	
	public PlayerMarket(Config cfg) {
		super(cfg);
		this.market = cfg.getString("marketstand");
	}
	
	@Override
	public void update() {
		MarketStand stand = MarketStand.map.get(market);
		
		this.owner = stand.owner;
		
		if (stand.timestamp == 0 && !isItemSimiliar(this.item, placeholder)) {
			this.owner = null;
			this.item = placeholder;
			this.type = ShopType.BUY;
			this.infinite = false;
			this.disabled = false;
			this.schedule = new boolean[24];
			this.used = 0L;
			this.amount = 1;
			this.price = 100.0D;
			this.respawnItem();
		}
	}
	
	@Override
	public void saveFile(Config cfg) {
		cfg.setValue("marketstand", this.market);
	}
	
	public String getMarket() {
		return this.market;
	}

}
