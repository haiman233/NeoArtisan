package io.github.moyusowo.neoartisan.attribute;

import io.github.moyusowo.neoartisan.NeoArtisan;
import io.github.moyusowo.neoartisan.RegisterManager;
import io.github.moyusowo.neoartisan.util.init.InitMethod;
import io.github.moyusowo.neoartisan.util.init.InitPriority;
import io.github.moyusowo.neoartisanapi.api.attribute.GlobalAttributeRegistry;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.ServicePriority;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class GlobalAttributeRegistryImpl implements GlobalAttributeRegistry {

    private static GlobalAttributeRegistryImpl instance;

    @InitMethod(priority = InitPriority.REGISTRY_LOAD)
    public static void init() {
        new GlobalAttributeRegistryImpl();
    }

    public static GlobalAttributeRegistryImpl getInstance() {
        return instance;
    }

    private GlobalAttributeRegistryImpl() {
        instance = this;
        globalAttributeRegistry = new HashMap<>();
        Bukkit.getServicesManager().register(
                GlobalAttributeRegistry.class,
                GlobalAttributeRegistryImpl.getInstance(),
                NeoArtisan.instance(),
                ServicePriority.Normal
        );
    }

    private final Map<NamespacedKey, PersistentDataType<?, ?>> globalAttributeRegistry;


    @Override
    public void registerAttribute(@NotNull NamespacedKey attributeKey, @NotNull PersistentDataType<?, ?> pdcType) {
        if (RegisterManager.isOpen()) {
            globalAttributeRegistry.put(attributeKey, pdcType);
            NeoArtisan.logger().info("successfully register global item attribute: " + attributeKey.asString());
        } else {
            throw RegisterManager.REGISTRY_CLOSED;
        }
    }

    @Override
    public boolean hasAttribute(@NotNull NamespacedKey attributeKey) {
        return globalAttributeRegistry.containsKey(attributeKey);
    }

    @Override
    public @NotNull Class<?> getAttributeJavaType(@NotNull NamespacedKey attributeKey) {
        if (!globalAttributeRegistry.containsKey(attributeKey)) throw new IllegalArgumentException("You must check if attribute exists before get!");
        return globalAttributeRegistry.get(attributeKey).getComplexType();
    }

    @Override
    public @NotNull PersistentDataType<?, ?> getAttributePDCType(@NotNull NamespacedKey attributeKey) {
        if (!globalAttributeRegistry.containsKey(attributeKey)) throw new IllegalArgumentException("You must check if attribute exists before get!");
        return globalAttributeRegistry.get(attributeKey);
    }
}
