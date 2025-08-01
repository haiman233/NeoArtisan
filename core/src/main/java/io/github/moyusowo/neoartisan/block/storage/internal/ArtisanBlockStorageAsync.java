package io.github.moyusowo.neoartisan.block.storage.internal;

import io.github.moyusowo.neoartisan.block.data.ArtisanBlockDataView;
import io.github.moyusowo.neoartisan.block.util.BlockPos;
import io.github.moyusowo.neoartisan.block.util.ChunkPos;
import io.github.moyusowo.neoartisan.util.multithread.SpecificThreadUse;
import io.github.moyusowo.neoartisan.util.multithread.Threads;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.UUID;

public interface ArtisanBlockStorageAsync {
    static ArtisanBlockStorageAsync getAsync() {
        return Bukkit.getServicesManager().load(ArtisanBlockStorageAsync.class);
    }

    @SpecificThreadUse(thread = Threads.STORAGE)
    @NotNull
    ArtisanBlockDataView getArtisanBlockDataView(@NotNull BlockPos blockPos);

    @SpecificThreadUse(thread = Threads.STORAGE)
    @NotNull
    @UnmodifiableView
    List<ChunkPos> getWorldArtisanBlockChunks(UUID worldUID);

    @SpecificThreadUse(thread = Threads.STORAGE)
    @NotNull
    @UnmodifiableView
    List<ArtisanBlockDataView> getChunkArtisanBlockDataViews(ChunkPos chunkPos);

    @SpecificThreadUse(thread = Threads.STORAGE)
    boolean isArtisanBlock(BlockPos blockPos);

    @SpecificThreadUse(thread = {Threads.STORAGE, Threads.MAIN})
    boolean hasArtisanBlockInWorld(UUID worldUID);

    @SpecificThreadUse(thread = {Threads.STORAGE, Threads.MAIN})
    boolean hasArtisanBlockInChunk(ChunkPos chunkPos);

    @SpecificThreadUse(thread = Threads.STORAGE)
    boolean checkAndCleanDirtyChunk(ChunkPos chunkPos);
}
