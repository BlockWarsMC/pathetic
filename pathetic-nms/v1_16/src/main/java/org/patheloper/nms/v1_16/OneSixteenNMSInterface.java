package org.patheloper.nms.v1_16;

import net.minecraft.server.v1_16_R3.ChunkStatus;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.CraftChunk;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.patheloper.api.snapshot.NMSInterface;

public class OneSixteenNMSInterface implements NMSInterface {

    @Override
    public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
        try {

            WorldServer server = ((CraftWorld) world).getHandle();
            CraftChunk newCraftChunk = ((CraftChunk) world.getChunkAt(chunkX, chunkZ));

            server.getChunkProvider().getChunkAt(chunkX, chunkZ, ChunkStatus.FULL, true);

            return newCraftChunk.getChunkSnapshot();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
