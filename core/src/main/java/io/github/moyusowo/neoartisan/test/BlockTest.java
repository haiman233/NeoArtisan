package io.github.moyusowo.neoartisan.test;

import io.github.moyusowo.neoartisan.NeoArtisan;
import io.github.moyusowo.neoartisanapi.api.NeoArtisanAPI;
import io.github.moyusowo.neoartisanapi.api.block.block.ArtisanCropBlock;
import io.github.moyusowo.neoartisanapi.api.block.block.ArtisanSimpleBlock;
import io.github.moyusowo.neoartisanapi.api.block.state.*;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.common.NoteBlockAppearance;
import io.github.moyusowo.neoartisanapi.api.block.state.appearance.crop.TripwireAppearance;
import io.github.moyusowo.neoartisanapi.api.item.ItemGenerator;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import java.util.List;

import static io.github.moyusowo.neoartisan.test.ItemTest.namespace;

public final class BlockTest {

    static final NamespacedKey magic_crop = new NamespacedKey(namespace, "magic_crop");

    public static void register() {
        if (NeoArtisan.isDebugMode()) {
            NeoArtisanAPI.getBlockRegistry().register(
                    ArtisanCropBlock.builder()
                            .blockId(magic_crop)
                            .states(
                                    List.of(
                                            ArtisanCropState.builder()
                                                    .appearance(
                                                            new TripwireAppearance(
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false
                                                            )
                                                    )
                                                    .generators(
                                                            new ItemGenerator[]{
                                                                    ItemGenerator.simpleGenerator(
                                                                            ItemTest.broken_stick,
                                                                            1
                                                                    ),
                                                                    ItemGenerator.rangedGenerator(
                                                                            Material.WHEAT.getKey(),
                                                                            1,
                                                                            3
                                                                    )
                                                            }
                                                    )
                                                    .build(),
                                            ArtisanCropState.builder()
                                                    .appearance(
                                                            new TripwireAppearance(
                                                                    true,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false
                                                            )
                                                    )
                                                    .generators(
                                                            new ItemGenerator[0]
                                                    )
                                                    .build(),
                                            ArtisanCropState.builder()
                                                    .appearance(
                                                            new TripwireAppearance(
                                                                    false,
                                                                    true,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false,
                                                                    false
                                                            )
                                                    )
                                                    .generators(
                                                            new ItemGenerator[]{
                                                                    ItemGenerator.simpleGenerator(
                                                                            Material.IRON_SWORD.getKey(),
                                                                            1
                                                                    ),
                                                                    ItemGenerator.rangedGenerator(
                                                                            ItemTest.magic_diamond,
                                                                            1,
                                                                            5
                                                                    )
                                                            }
                                                    )
                                                    .build()
                                    )
                            )
                            .boneMealMinGrowth(0)
                            .boneMealMaxGrowth(2)
                            .build()
            );
            NeoArtisanAPI.getBlockRegistry().register(
                    ArtisanSimpleBlock.builder()
                            .blockId(ItemTest.magic_block)
                            .state(
                                    ArtisanCommonState.builder()
                                            .appearance(new NoteBlockAppearance(NoteBlockAppearance.NoteBlockSoundProperty.SKELETON, 1, true))
                                            .generators(
                                                    new ItemGenerator[] {
                                                            ItemGenerator.simpleGenerator(ItemTest.magic_block, 1)
                                                    }
                                            )
                                            .hardness(5.0f)
                                            .build()
                            )
                            .build()
            );
            NeoArtisanAPI.getBlockRegistry().register(
                    ArtisanSimpleBlock.builder()
                            .blockId(ItemTest.soup_block)
                            .state(
                                    ArtisanSkullState.builder()
                                            .textureUrl(ItemTest.soup_block_skull, true)
                                            .generators(
                                                    new ItemGenerator[] {
                                                            ItemGenerator.simpleGenerator(ItemTest.soup_block, 1)
                                                    }
                                            )
                                            .hardness(1.5f)
                                            .build()
                            )
                            .build()
            );
        }
    }
}
