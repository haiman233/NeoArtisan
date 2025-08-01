package io.github.moyusowo.neoartisanapi.api.block.event;

import io.github.moyusowo.neoartisanapi.api.block.block.base.ArtisanBaseBlock;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 当玩家破坏自定义方块时触发的事件。
 * <p>
 * 继承自 {@link BlockBreakEvent}，包含被破坏的自定义方块实例。
 * 可用于取消破坏行为或修改掉落物（通过父类方法）。
 * </p>
 *
 * @see ArtisanBaseBlock 自定义方块类型
 */
public class ArtisanBlockBreakEvent extends BlockBreakEvent {

    private static final HandlerList handlers = new HandlerList();

    protected ArtisanBaseBlock artisanBaseBlock;

    /**
     * 构造自定义方块破坏事件
     *
     * @param theBlock 被破坏的方块实例（非null）
     * @param player 破坏方块的玩家（非null）
     * @param artisanBaseBlock 关联的自定义方块定义（非null）
     */
    public ArtisanBlockBreakEvent(@NotNull Block theBlock, @NotNull Player player, @NotNull ArtisanBaseBlock artisanBaseBlock) {
        super(theBlock, player);
        this.artisanBaseBlock = artisanBaseBlock;
    }

    /**
     * 获取被破坏的自定义方块定义
     *
     * @return 自定义方块实例
     */
    public @NotNull ArtisanBaseBlock getArtisanBlock() {
        return this.artisanBaseBlock;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

}
