package su.nightexpress.nexshop.shop.chest.command;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import su.nightexpress.nexshop.Perms;
import su.nightexpress.nexshop.config.Lang;
import su.nightexpress.nexshop.module.command.ShopModuleCommand;
import su.nightexpress.nexshop.shop.chest.ChestShop;

import java.util.ArrayList;
import java.util.List;

public class SearchCmd extends ShopModuleCommand<ChestShop> {

    private static final List<String> MATERIALS = new ArrayList<>();

    static {
        for (Material m : Material.values()) {
            MATERIALS.add(m.name());
        }
    }

    public SearchCmd(@NotNull ChestShop module) {
        super(module, new String[]{"search"}, Perms.CHEST_CMD_SEARCH);
    }

    @Override
    @NotNull
    public String getDescription() {
        return plugin.getMessage(Lang.Command_Search_Desc).getLocalized();
    }

    @Override
    @NotNull
    public String getUsage() {
        return plugin.getMessage(Lang.Command_Search_Usage).getLocalized();
    }

    @Override
    public boolean isPlayerOnly() {
        return true;
    }

    @Override
    @NotNull
    public List<@NotNull String> getTab(@NotNull Player p, int i, @NotNull String[] args) {
        if (i == 1) {
            return MATERIALS;
        }
        return super.getTab(p, i, args);
    }

    @Override
    public void onExecute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (args.length != 2) {
            this.printUsage(sender);
            return;
        }

        Player player = (Player) sender;
        Material mat = Material.getMaterial(args[1].toUpperCase());
        if (mat == null) {
            return;
        }

        this.module.getListSearchMenu().searchProduct(player, mat);
        this.module.getListSearchMenu().open(player, 1);
    }
}
