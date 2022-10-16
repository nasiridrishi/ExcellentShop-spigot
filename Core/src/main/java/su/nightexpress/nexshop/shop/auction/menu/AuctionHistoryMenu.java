package su.nightexpress.nexshop.shop.auction.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nexmedia.engine.api.config.JYML;
import su.nexmedia.engine.api.menu.IMenuClick;
import su.nexmedia.engine.api.menu.IMenuItem;
import su.nexmedia.engine.api.menu.MenuItemType;
import su.nightexpress.nexshop.shop.auction.AuctionManager;
import su.nightexpress.nexshop.shop.auction.listing.AuctionCompletedListing;

import java.util.List;
import java.util.UUID;

public class AuctionHistoryMenu extends AbstractAuctionMenu<AuctionCompletedListing> {

    public AuctionHistoryMenu(@NotNull AuctionManager auctionManager, @NotNull JYML cfg) {
        super(auctionManager, cfg);

        IMenuClick click = (p, type, e) -> {

            if (type instanceof MenuItemType type2) {
                if (type2 == MenuItemType.RETURN) {
                    this.auctionManager.getMainMenu().open(p, 1);
                }
                else this.onItemClickDefault(p, type2);
            }
        };

        for (String sId : cfg.getSection("Content")) {
            IMenuItem menuItem = cfg.getMenuItem("Content." + sId, MenuItemType.class);

            if (menuItem.getType() != null) {
                menuItem.setClick(click);
            }
            this.addItem(menuItem);
        }
    }

    @Override
    @NotNull
    protected List<AuctionCompletedListing> getObjects(@NotNull Player player) {
        UUID id = this.seeOthers.getOrDefault(player, player.getUniqueId());
        return this.auctionManager.getHistoryListings(id);
    }

    @Override
    @NotNull
    protected IMenuClick getObjectClick(@NotNull Player player, @NotNull AuctionCompletedListing item) {
        return (player1, type, e) -> {

        };
    }
}
